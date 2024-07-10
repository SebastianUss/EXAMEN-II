package com.Gutierrez.infraccionservice.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Gutierrez.infraccionservice.entity.Infraccion;

@Repository
public interface InfraccionRepository extends JpaRepository<Infraccion, Integer> {
    List<Infraccion> findByDniContaining(String dni, Pageable page);
    Infraccion findByDni(String dni);
}
