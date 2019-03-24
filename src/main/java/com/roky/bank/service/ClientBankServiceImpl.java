package com.roky.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roky.bank.dao.ClientRepository;
import com.roky.bank.entities.Client;
import com.roky.bank.service.exception.ClientNotFoundException;

@Service
@Transactional
public class ClientBankServiceImpl implements ClientBankService{

  @Autowired
  private ClientRepository clientRepository;
  
  /**
   * Methode pour creer un Client 
   * @param client
   */
  @Override
  public Client saveClient(Client client) {
    
    return clientRepository.save(client);
  }

  /**
   * Methode pour récupérer un Client 
   * @param codeClient
   * @return Client
   * @throws ClientNotFoundException 
   */
  @Override
  public Client recupererClient(String nomClient) throws ClientNotFoundException {
    
    return clientRepository.findByNom(nomClient).
        orElseThrow(() -> new ClientNotFoundException("Client not found"));

  }

  /**
   * Methode pour supprimer un Client 
   * @param codeClient
   */

  @Override
  public boolean deleteClient(String nomClient) {
    
      clientRepository.deleteByNom(nomClient);
      return true;

  }

  
}
