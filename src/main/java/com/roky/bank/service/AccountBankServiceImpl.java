package com.roky.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roky.bank.dao.CompteRepository;
import com.roky.bank.entities.Compte;
import com.roky.bank.service.exception.AccountNotFoundException;

/** 
 * Service gestion compte Bancaire
 * @author ROKY
 *
 */
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService{
  
  @Autowired
  private CompteRepository compteRepository;

  
  /**
   * Methode pour creer un Compte 
   * @param Compte
   */
  @Override
  public Compte saveCompte(Compte compte) {
    
    return compteRepository.save(compte);
  }

  /**
   * Methode pour consulter un compte
   * @param codeCpte
   * @return Compte
   * @throws AccountNotFoundException 
   */
  @Override
  public Compte recupererCompte(String codeCpte) throws AccountNotFoundException {

    return compteRepository.findById(codeCpte).
        orElseThrow(() -> new AccountNotFoundException("Account not found"));    
  }
  
  /**
   * Methode pour supprimer un Compte 
   * @param codeCpte
   */
  public boolean deleteCompte(String codeCpte) {
    
    compteRepository.deleteById(codeCpte);
    return true;
  }

}
