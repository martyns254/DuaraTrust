package com.duaratrust.duaratrust.repository;


import com.duaratrust.duaratrust.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
