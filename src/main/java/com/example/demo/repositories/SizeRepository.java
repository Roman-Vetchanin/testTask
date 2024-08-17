package com.example.demo.repositories;

import com.example.demo.entyti.specification.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {
}