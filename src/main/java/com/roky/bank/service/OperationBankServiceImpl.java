package com.roky.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roky.bank.dao.CompteRepository;
import com.roky.bank.dao.OperationRepository;
import com.roky.bank.entities.Compte;
import com.roky.bank.entities.Operation;
import com.roky.bank.entities.Retrait;
import com.roky.bank.entities.Versement;
import com.roky.bank.service.exception.OperationNotAllowedException;

@Service
@Transactional
public class OperationBankServiceImpl implements OperationBankService{
  
  
  @Autowired
  private OperationRepository OperationRepository;
  
  @Autowired
  private CompteRepository compteRepository;
  
  /**
   * Methode pour verser une somme d'argent
   * @param codeCpte , montant
   */
  @Override
  public void saveMoney(Compte compte, double montant) {
    
      Versement versement = new Versement(new Date(), montant, compte);
      OperationRepository.save(versement);
      compte.setSolde(compte.getSolde() + montant);
      compteRepository.save(compte);

  }

  /**
   * Methode pour retrait d'une somme d'argent
   * @param codeCpte , montant
   * @throws OperationNotAllowedException 
   */
  @Override
  public void retrieveMoney(Compte compte, double montant) {
      
    if(compte.getSolde() - montant < 0)
    {
      throw new OperationNotAllowedException("Operation not allowed!");
    }
    else
    {
      Retrait retrait = new Retrait(new Date(), montant, compte);
      OperationRepository.save(retrait);
      compte.setSolde(compte.getSolde() - montant);
      compteRepository.save(compte); 
    }
    
  }

  /**
   * Methode pour lister l'historique des operations d'un compte
   * @param codeCpte
   * @return List<Operation>
   */
  @Override
  public List<Operation> operationHistory(String codeCpte) {
    
    return OperationRepository.operationHistory(codeCpte);
  }

}
