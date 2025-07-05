package com.example.contabancaria.controller;

import com.example.contabancaria.model.ContaBancaria;
import com.example.contabancaria.model.ContaBancariaVersionada;
import com.example.contabancaria.service.ContaBancariaService;
import com.example.contabancaria.service.ContaBancariaVersionadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaService;
    
    @Autowired
    private ContaBancariaVersionadaService contaVersionadaService;

    @PostMapping
    public ContaBancaria criarConta(@RequestBody ContaBancaria conta) {
        return contaService.save(conta);
    }

    @PostMapping("/versionada")
    public ContaBancariaVersionada criarContaVersionada(@RequestBody ContaBancariaVersionada conta) {
        return contaVersionadaService.save(conta);
    }

    @PostMapping("/{id}/depositar")
    public ContaBancaria depositar(@PathVariable Long id, @RequestParam float valor) {
        return contaService.depositar(id, valor);
    }

    @PostMapping("/{id}/retirar")
    public ContaBancaria retirar(@PathVariable Long id, @RequestParam float valor) {
        return contaService.retirar(id, valor);
    }

    @PostMapping("/versionada/{id}/depositar")
    public ContaBancariaVersionada depositarVersionada(@PathVariable Long id, @RequestParam float valor) {
        return contaVersionadaService.depositar(id, valor);
    }

    @PostMapping("/versionada/{id}/retirar")
    public ContaBancariaVersionada retirarVersionada(@PathVariable Long id, @RequestParam float valor) {
        return contaVersionadaService.retirar(id, valor);
    }

    @GetMapping("/{id}")
    public ContaBancaria getConta(@PathVariable Long id) {
        return contaService.findById(id);
    }

    @GetMapping("/versionada/{id}")
    public ContaBancariaVersionada getContaVersionada(@PathVariable Long id) {
        return contaVersionadaService.findById(id);
    }
}