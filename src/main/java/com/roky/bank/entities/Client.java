package com.roky.bank.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entité Client
 * @author ROKY
 *
 */
@Entity
public class Client implements Serializable{

  @Id @GeneratedValue
  private long code;
  private String nom;
  private String adresse;
  @OneToMany(mappedBy="client",fetch=FetchType.LAZY , cascade = CascadeType.ALL)
  private Collection<Compte> comptes;
 
  public Client() {
    super();
  }

  public Client(String nom, String adresse) {
    super();
    this.nom = nom;
    this.adresse = adresse;
  }
  
  public Client(long code, String nom, String adresse) {
    super();
    this.code = code;
    this.nom = nom;
    this.adresse = adresse;
  }

  public long getCode() {
    return code;
  }

  public void setCode(long code) {
    this.code = code;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public Collection<Compte> getComptes() {
    return comptes;
  }

  public void setComptes(Collection<Compte> comptes) {
    this.comptes = comptes;
  }
  
}
