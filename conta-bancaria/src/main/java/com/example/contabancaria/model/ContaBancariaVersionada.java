package com.example.contabancaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.time.LocalDateTime;

@Entity
public class ContaBancariaVersionada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private float saldo;
    
    @Version
    private LocalDateTime dataMovimento;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }
    public LocalDateTime getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDateTime dataMovimento) { this.dataMovimento = dataMovimento; }

    // Operacoes de negocio
    public void deposita(float valor) {
        this.saldo += valor;
    }

    public void retirada(float valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }
}