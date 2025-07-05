package com.example.contabancaria.service;

import com.example.contabancaria.model.ContaBancaria;
import com.example.contabancaria.repository.ContaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaService {
    @Autowired
    private ContaBancariaRepository repository;

    public ContaBancaria findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public ContaBancaria depositar(Long id, float valor) {
        ContaBancaria conta = repository.findById(id).orElseThrow();
        conta.deposita(valor);
        return repository.save(conta);
    }

    @Transactional
    public ContaBancaria retirar(Long id, float valor) {
        ContaBancaria conta = repository.findById(id).orElseThrow();
        conta.retirada(valor);
        return repository.save(conta);
    }

    public ContaBancaria save(ContaBancaria conta) {
        return repository.save(conta);
    }
}