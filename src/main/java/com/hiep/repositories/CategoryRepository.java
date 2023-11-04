package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hiep.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {}
