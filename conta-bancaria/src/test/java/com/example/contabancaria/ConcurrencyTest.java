package com.example.contabancaria;

import com.example.contabancaria.model.ContaBancaria;
import com.example.contabancaria.service.ContaBancariaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConcurrencyTest {
    @Autowired
    private ContaBancariaService contaService;

    @Test
    public void testConcorrenciaSemVersionamento() throws InterruptedException {
        // Cria uma conta com saldo inicial de 1000
        final ContaBancaria conta = new ContaBancaria();
        conta.setNomeCliente("Cliente Teste");
        conta.setSaldo(1000);
        final ContaBancaria contaSalva = contaService.save(conta);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 50 operações de depósito de 10
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> contaService.depositar(conta.getId(), 10));
        }

        // 50 operações de retirada de 10
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> contaService.retirar(conta.getId(), 10));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Verifica o saldo final (deveria ser 1000)
        ContaBancaria contaAtualizada = contaService.findById(conta.getId());
        System.out.println("Saldo final (sem versionamento): " + contaAtualizada.getSaldo());
    }
}