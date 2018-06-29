package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

    List<UploadFile> findAllByIdIn(List<Long> ids);
}
