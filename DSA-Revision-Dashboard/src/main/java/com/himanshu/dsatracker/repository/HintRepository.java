package com.himanshu.dsatracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.himanshu.dsatracker.entity.Hint;

@Repository
public interface HintRepository extends JpaRepository<Hint, Long> {

}
