package com.xiaobai.springbootdemo.repository;

import com.xiaobai.springbootdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserJpaRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
}
