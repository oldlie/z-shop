package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {

    List<Tag> findAllByIdIn(final List<Long> ids);
}
