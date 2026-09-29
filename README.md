# DuaraTrust

A backend platform for managing chamas (Kenyan group savings associations) that enforces dual authorization on every withdrawal: no single official can move group funds alone.

Most existing group-savings tools reduce a chama to a digital ledger, which does nothing to prevent the actual failure mode that causes many chamas to collapse: a treasurer or single official mismanaging funds unchecked. DuaraTrust is built around a different assumption: money should never move on one person's word.

Architecture: [`docs/ARCHITECTURE.md`](./docs/ARCHITECTURE.md) · Sequence diagram: [`docs/withdrawal-approval-flow.mmd`](./docs/withdrawal-approval-flow.mmd)

## How authorization works

A withdrawal request starts `PENDING`. Officials vote individually, one vote per official, enforced server-side. Once approvals reach the group's own configurable threshold (2-of-3, 3-of-5, whatever the group sets), the request moves to `APPROVED`; a single rejection moves it to `REJECTED` immediately. Every vote is a permanent, attributable record, not a counter that could be reset or edited.

## Identity

Real per-user authentication, not a shared secret: registration with BCrypt-hashed passwords, JWT issued on login, and every protected endpoint reads the caller's identity from a cryptographically verified token rather than trusting a value supplied in the request body. A client cannot forge being a different official.

## API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users/register` | Register a user |
| POST | `/api/users/login` | Authenticate, receive a JWT |
| POST | `/api/groups` | Create a group, with its own approval threshold and contribution schedule |
| POST | `/api/members` | Add a member to a group |
| POST | `/api/withdrawals` | Request a withdrawal |
| GET | `/api/withdrawals` | List withdrawal requests |
| POST | `/api/withdrawals/{id}/approvals` | Cast an approval or rejection vote |

## Engineering

- Single Spring Boot application, deliberately, not microservices; the domain is one cohesive product, not separable services, so the simpler architecture is the correct one, not a shortcut. See `ARCHITECTURE.md` for the reasoning.
- PostgreSQL, layered controller/service/repository/DTO structure throughout.
- CI on every push: GitHub Actions builds and tests against a real, ephemeral Postgres instance, not mocked out.

## Roadmap

Contribution tracking and a permanent ledger, automated fund disbursement on approval, scheduled deadline/penalty enforcement, RabbitMQ for withdrawal-status events, a Kotlin/Jetpack Compose Android client.

## Stack

Java 17 · Spring Boot · Spring Security · JWT · PostgreSQL · Maven · GitHub Actions

## Author

Martins Kosgei — [github.com/martyns254](https://github.com/martyns254)
