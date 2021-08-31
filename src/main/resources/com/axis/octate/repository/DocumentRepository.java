package com.axis.octate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.octate.onboarding.model.Documents;

@Repository
public interface DocumentRepository extends JpaRepository<Documents, String> {

}
