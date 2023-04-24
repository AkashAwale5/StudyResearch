package com.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.entity.v2.Study;


@Repository
public interface StudyRepository extends JpaRepository<Study, Integer>
{
	Optional<Study> findByName(String name);
}
