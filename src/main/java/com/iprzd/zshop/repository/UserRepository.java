package com.iprzd.zshop.repository;

import com.iprzd.zshop.repository.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

    Users findByUsername(String username);
}
