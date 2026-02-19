package com.himanshu.dsatracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.himanshu.dsatracker.entity.Question;
import com.himanshu.dsatracker.entity.Status;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

    // Spring Boot is so smart, it will automatically write the SQL query 
    // to count questions just based on the name of this method!

    long countByStatus(Status status);

}
