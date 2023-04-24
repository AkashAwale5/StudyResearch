package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.entity.v2.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
