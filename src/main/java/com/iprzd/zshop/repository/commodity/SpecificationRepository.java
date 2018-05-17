package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpecificationRepository extends PagingAndSortingRepository<Specification, Long> {

    Page<Specification> findAllByTitle(String title, Pageable pageable);

    List<Specification> findAllByIdIn(final List<Long> ids);
}
