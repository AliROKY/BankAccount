package com.roky.bank.service;

import com.roky.bank.entities.Compte;
import com.roky.bank.service.exception.AccountNotFoundException;

/**
 * Service gestion compte Bancaire
 * @author ROKY Ali
 *
 */
public interface AccountBankService {

  
  /**
   * Methode pour creer un Compte 
   * @param compte
   */
  public Compte saveCompte(Compte compte);
  
  /**
   * Methode pour consulter un compte
   * @param codeCpte
   * @return Compte
   * @throws AccountNotFoundException 
   */
  public Compte recupererCompte(String codeCpte) throws AccountNotFoundException;
  
  /**
   * Methode pour supprimer un Compte 
   * @param codeCpte
   */
  public boolean deleteCompte(String codeCpte);
  
}
