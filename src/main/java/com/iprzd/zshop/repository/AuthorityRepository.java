package com.iprzd.zshop.repository;

import com.iprzd.zshop.repository.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authorities, String> {

    List<Authorities> findAllByUsername(String username);
}
