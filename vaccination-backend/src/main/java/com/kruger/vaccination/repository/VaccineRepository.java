package com.kruger.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruger.vaccination.model.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

}