package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hiep.models.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {}
