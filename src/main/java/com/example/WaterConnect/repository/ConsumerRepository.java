package com.example.WaterConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WaterConnect.model.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {}