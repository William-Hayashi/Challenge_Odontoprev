package com.example.C_Odontoprev.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENTS")
public class ClientModel extends RepresentationModel<ClientModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String CPF;
    private String CEP;
    private String primeiroTratamento;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getPrimeiroTratamento() {
        return primeiroTratamento;
    }

    public void setPrimeiroTratamento(String primeiroTratamento) {
        this.primeiroTratamento = primeiroTratamento;
    }
}
