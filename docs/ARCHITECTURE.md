# Architecture notes

This covers the reasoning behind DuaraTrust's structure, not just what the components are. The component diagram (`component-diagram.png`) and the withdrawal approval sequence diagram (`withdrawal-approval-flow.mmd`) sit alongside this file in `/docs`.

## Monolith, not microservices

PesaFlow, an earlier project, is two independent Spring Boot services communicating over REST, because Payment and Wallet were genuinely separable concerns worth practicing distributed-systems trade-offs on: partial failure, service-to-service auth, graceful degradation.

DuaraTrust is deliberately a single application instead. Groups, members, contributions, and withdrawals aren't separate businesses, they're facets of one product, and splitting them would have added HTTP-call overhead without teaching anything new. A monolith with clean internal layering (controller, service, repository, DTO) is the better fit here. The choice was made on purpose, not by default.

## Identity: JWT instead of a static key

PesaFlow's two services authenticate each other with a single shared API key. That works when the only question is "is this caller allowed to call us at all," but it can't answer "which specific person is making this request."

DuaraTrust needs the second question answered, because the withdrawal approval flow depends on knowing exactly which official is voting. So authentication here is real per-user login: registration with BCrypt-hashed passwords, a signed JWT issued on login, and a filter that validates the token on every protected request and places the caller's identity into Spring Security's context. Every service method that needs to know who is acting reads that identity from the security context rather than trusting a value the client supplied in the request body, since a client could lie about a value in a request body but can't forge a validly signed token for someone else's phone number.

## The actual differentiator: dual authorization on withdrawals

Most simple group-savings tools let a single treasurer or admin move funds unilaterally. The core design decision in DuaraTrust is that no single official can do that.

A withdrawal request starts `PENDING`. Officials submit individual approval votes. Each vote is its own row (`Approval`), not a counter on the withdrawal, specifically so the system can tell whether a given official has already voted and reject a second vote from the same person. Once the number of `APPROVE` votes reaches the group's own `approvalThreshold` (configurable per group, not hardcoded), the request moves to `APPROVED`. A single `REJECT` vote moves it straight to `REJECTED`. See `withdrawal-approval-flow.mmd` for the exact sequence, including where each check happens.

## What's not built yet

- No `Contribution` or `LedgerEntry` entities yet, so there is no running balance or audit trail beyond the withdrawal/approval records themselves.
- Disbursement (actually moving funds once a withdrawal is `APPROVED`) is not implemented; the status transition happens, but nothing downstream acts on it yet.
- No automated deadline/fine enforcement job.
- No Android client yet; the API is the only interface.
- No message broker; an `APPROVED` status change doesn't notify anyone. RabbitMQ is the intended next step here, once local environment constraints allow it.
- No rate limiting, caching, or retry/circuit-breaker logic yet.
- No CI-driven integration tests against the dual-authorization logic specifically, only the request/response shape has been exercised manually so far.
