package com.roky.Bank;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.roky.bank.entities.Client;
import com.roky.bank.entities.Compte;
import com.roky.bank.entities.CompteCourant;
import com.roky.bank.entities.Operation;
import com.roky.bank.entities.Retrait;
import com.roky.bank.entities.Versement;
import com.roky.bank.service.AccountBankService;
import com.roky.bank.service.ClientBankService;
import com.roky.bank.service.OperationBankService;
import com.roky.bank.service.exception.AccountNotFoundException;
import com.roky.bank.service.exception.OperationNotAllowedException;

/**
 * Classe test des transactions bancaires
 * @author ROKY
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperationBankTest {

  @Autowired
  private AccountBankService accountBankService;

  @Autowired
  private OperationBankService operationBankService;

  @Autowired
  private ClientBankService clientBankService;

  /**
   * Methode test pour retrait
   * @throws AccountNotFoundException 
   * @throws Exception
   */
  @Test
  public void testOfRetrievingMoney() throws AccountNotFoundException {
      
      Client ali = clientBankService.saveClient(new Client(10, "ALI", "Adresse Ali"));
      Compte initialCompte = accountBankService.saveCompte(new CompteCourant("C1", new Date(), 1000, ali, 0));
      double AccountBalance = initialCompte.getSolde();
      double amount = 500.0;
      operationBankService.retrieveMoney(initialCompte, amount);
      Compte newCompte = accountBankService.recupererCompte("C1");

      assertEquals(AccountBalance - amount, newCompte.getSolde(), 0.0);
  }
  
  /**
   * Methode test operation not allowed
   * @throws AccountNotFoundException 
   */
  @Test(expected = OperationNotAllowedException.class)
  public void testOfRetrievingMoneyNotAllowedException() throws AccountNotFoundException {
      
      Compte compte = accountBankService.recupererCompte("C1");
      double amount = 1500.0;
      operationBankService.retrieveMoney(compte, amount);
  }
	/**
	 * Methode test pour verser
	 * @throws AccountNotFoundException 
	 */
    @Test
    public void testOfSavingMoney() throws AccountNotFoundException {

    Compte initialCompte = accountBankService.recupererCompte("C1");

    double AccountBalance = initialCompte.getSolde();
    double amount = 100.0;
    operationBankService.saveMoney(initialCompte, amount);
    Compte newCompte = accountBankService.recupererCompte("C1");
    assertEquals(amount + AccountBalance, newCompte.getSolde(), 0.0);
    }
    
    /**
     * Methode test pour historique operations
     */
    @Test
    public void testOperationHistory() {
    	      
      List<Operation> retraitOperations = operationBankService.operationHistory("C1")
          .stream().filter(op -> op instanceof Retrait)
          .sorted(Comparator.comparing(Operation::getDateOperation))
          .collect(Collectors.toList());
      
      List<Operation> versementOperations = operationBankService.operationHistory("C1")
          .stream().filter(op -> op instanceof Versement)
          .sorted(Comparator.comparing(Operation::getDateOperation))
          .collect(Collectors.toList());
    	
    	assertEquals(versementOperations.size(), 1 , 0.0);
    	assertEquals(retraitOperations.size(), 1 , 0.0);
    	
    }    

}
