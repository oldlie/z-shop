package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findOneByCellphone(String cellphone);
}
