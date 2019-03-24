package com.roky.bank.service;

import com.roky.bank.entities.Client;
import com.roky.bank.service.exception.ClientNotFoundException;

public interface ClientBankService {

  /**
   * Methode pour creer un Client 
   * @param client
   */
  public Client saveClient(Client client);
  
  /**
   * Methode pour récupérer un Client 
   * @param codeClient
   * @return Client
   * @throws ClientNotFoundException 
   */
  public Client  recupererClient(String nomClient) throws ClientNotFoundException;
  
  /**
   * Methode pour supprimer un Client 
   * @param codeClient
   */
  public boolean deleteClient(String nomClient);

}
