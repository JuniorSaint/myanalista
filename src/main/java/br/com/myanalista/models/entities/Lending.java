package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "Lending")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lending implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String territory;
  private String dealer;
  private String customerRegistration;
  private String cpf;
  private String cnpj;
  private String companyName;
  private String fantasyName;
  private String gec;
  private String SubChannel;
  private String city;
  private String equipmentNumber;
  private String serie;
  private Integer amountDoors;
  private String logo;
  private String contract;
  private String productCode;
  private String product;
  private Integer amount;
  private LocalDate dateSend;
  private LocalDate dueDate;
  private String sellerCode;
  private String route;
  private String nf;
  private String conservation;
}
