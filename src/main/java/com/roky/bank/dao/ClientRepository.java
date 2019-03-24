package com.roky.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roky.bank.entities.Client;

/**
 * Utilitaire d'accés aux données Client
 * @author ROKY
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
  
  Optional<Client> findByNom(String nomClient);
  
  void deleteByNom(String nomClient);

}
