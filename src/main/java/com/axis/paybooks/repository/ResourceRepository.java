package com.axis.paybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.paybooks.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {

}
