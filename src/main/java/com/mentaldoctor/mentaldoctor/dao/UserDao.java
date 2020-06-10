package com.mentaldoctor.mentaldoctor.dao;

import com.mentaldoctor.mentaldoctor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUuid(long uuid);
}
