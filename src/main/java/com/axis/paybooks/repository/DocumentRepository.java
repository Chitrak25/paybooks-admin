package com.axis.paybooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.paybooks.onboarding.model.Documents;

@Repository
public interface DocumentRepository extends JpaRepository<Documents, String> {

}
