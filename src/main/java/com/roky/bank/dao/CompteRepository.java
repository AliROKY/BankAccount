package com.roky.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roky.bank.entities.Compte;

/**
 * Utilitaire d'accés aux données Compte
 * @author ROKY
 *
 */
public interface CompteRepository extends JpaRepository<Compte, String>{
  
  Optional<Compte> findById(String codeCpte);

}
