package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpecificationRepository extends PagingAndSortingRepository<Specification, Long> {

    Page<Specification> findAllByTitle(String title, Pageable pageable);
}
