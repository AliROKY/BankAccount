package com.roky.Bank;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.roky.bank.entities.Client;
import com.roky.bank.service.ClientBankService;
import com.roky.bank.service.exception.ClientNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

/**
 * Classe test des operations sur le Client
 * @author ROKY
 *
 */
public class ClientBankTest {
  
  
  @Autowired
  private ClientBankService clientBankService;
  
  /**
   * Methode test creation client
   * @throws Exception
   */
  @Test
  public void testCreerNouveauClient() {
      
      Client client = new Client("Ali", "adresse1");
      clientBankService.saveClient(client);
      assertNotNull(client.getCode());
  }

  /**
   * Methode test pour recuperer Client
   * @throws Exception
   */
  @Test
  public void testRecupererClient() throws ClientNotFoundException {
      
      Client client = clientBankService.recupererClient("Ali");
      assertNotNull(client.getCode());
  }
  
  /**
   * Methode test pour supprimer un client
   * @throws Exception
   */
  @Test
  public void testSupprimerClient() {
      
      Boolean isDeleted = clientBankService.deleteClient("Ali");
      assertTrue(isDeleted);
  }
}
