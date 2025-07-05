package com.example.contabancaria.service;

import com.example.contabancaria.model.ContaBancariaVersionada;
import com.example.contabancaria.repository.ContaBancariaVersionadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaVersionadaService {
    @Autowired
    private ContaBancariaVersionadaRepository repository;

    public ContaBancariaVersionada findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public ContaBancariaVersionada depositar(Long id, float valor) {
        ContaBancariaVersionada conta = repository.findById(id).orElseThrow();
        conta.deposita(valor);
        return repository.save(conta);
    }

    @Transactional
    public ContaBancariaVersionada retirar(Long id, float valor) {
        ContaBancariaVersionada conta = repository.findById(id).orElseThrow();
        conta.retirada(valor);
        return repository.save(conta);
    }

    public ContaBancariaVersionada save(ContaBancariaVersionada conta) {
        return repository.save(conta);
    }
}