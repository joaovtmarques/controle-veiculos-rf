package com.informatica.controle_veiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "vehicles")
public class Vehicle {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private Long id;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "plate", nullable = false)
  private String plate;

  @Column(name = "color", nullable = false)
  private String color;

  @Column(name = "type", nullable = false)
  private String type;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

}
