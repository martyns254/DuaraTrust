package com.duaratrust.duaratrust.repository;

import com.duaratrust.duaratrust.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);

}

