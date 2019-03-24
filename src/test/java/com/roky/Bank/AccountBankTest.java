package com.roky.Bank;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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
import com.roky.bank.service.AccountBankService;
import com.roky.bank.service.ClientBankService;
import com.roky.bank.service.exception.AccountNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

/**
 * Classe test des operations sur les comptes
 * @author ROKY
 *
 */
public class AccountBankTest {
  
  
  @Autowired
  private ClientBankService clientBankService;
  
  @Autowired
  private AccountBankService accountBankService;
  
  /**
   * Methode test creation Compte
   * @throws Exception
   */
  @Test
  public void testCreerNouveauAccount() {
      
      Client ali = clientBankService.saveClient(new Client(10, "ALI", "Adresse Ali"));
      Compte compte = accountBankService. saveCompte(new CompteCourant("C1", new Date(), 1000, ali, 0));
      assertNotNull(compte.getCodeCompte());
  }

  /**
   * Methode test pour recuperer Compte
   * @throws Exception
   */
  @Test
  public void testRecupererCompte() throws AccountNotFoundException {
      
      Compte compte = accountBankService.recupererCompte("C1");
      assertNotNull(compte.getCodeCompte());
  }
  
  /**
   * Methode test pour supprimer un compte
   * @throws Exception
   */
  @Test
  public void testSupprimerAccount() {
      
      Boolean isDeleted = accountBankService.deleteCompte("C1");
      assertTrue(isDeleted);
  }

}
