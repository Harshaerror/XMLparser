package com.example.repository;

import com.example.model.VastModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VastRepository extends JpaRepository<VastModel, Long> {
    
}
