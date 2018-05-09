package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
