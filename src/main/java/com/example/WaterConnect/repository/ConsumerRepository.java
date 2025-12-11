package com.example.WaterConnect.repository;

import com.example.WaterConnect.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    List<Consumer> findByFullNameContainingIgnoreCase(String name);

    List<Consumer> findByAddressContainingIgnoreCase(String address);
}
