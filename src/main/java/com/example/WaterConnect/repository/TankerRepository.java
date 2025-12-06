package com.example.WaterConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WaterConnect.model.Tanker;

public interface TankerRepository extends JpaRepository<Tanker, Long> {}