package com.company.quangan.repository;

import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory,Integer> {
}
