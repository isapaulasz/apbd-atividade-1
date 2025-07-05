package com.example.contabancaria;

import com.example.contabancaria.model.ContaBancariaVersionada;
import com.example.contabancaria.service.ContaBancariaVersionadaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class VersionedConcurrencyTest {
    @Autowired
    private ContaBancariaVersionadaService contaService;

    @Test
    public void testConcorrenciaComVersionamento() throws InterruptedException {
        // Cria uma conta com saldo inicial de 1000
        final ContaBancariaVersionada conta = new ContaBancariaVersionada();
        conta.setNomeCliente("Cliente Teste Versionado");
        conta.setSaldo(1000);
        final ContaBancariaVersionada contaSalva = contaService.save(conta);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 50 operações de depósito de 10
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    contaService.depositar(conta.getId(), 10);
                } catch (Exception e) {
                    System.out.println("Erro em depósito: " + e.getMessage());
                }
            });
        }

        // 50 operações de retirada de 10
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    contaService.retirar(conta.getId(), 10);
                } catch (Exception e) {
                    System.out.println("Erro em retirada: " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Verifica o saldo final (deveria ser 1000)
        ContaBancariaVersionada contaAtualizada = contaService.findById(conta.getId());
        System.out.println("Saldo final (com versionamento): " + contaAtualizada.getSaldo());
    }
}