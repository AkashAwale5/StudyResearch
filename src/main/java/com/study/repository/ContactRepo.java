package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.entity.v2.ContactDetails;


@Repository
public interface ContactRepo extends JpaRepository<ContactDetails, Integer> {

}
