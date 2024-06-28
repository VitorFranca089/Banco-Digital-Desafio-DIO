package conta;

import cliente.Cliente;

public class ContaPoupanca extends Conta{

    public ContaPoupanca(Cliente cliente, int senha) {
        super(cliente, senha);
    }

    @Override
    public void imprimirExtrato(int senha) {
        if(verificaSenha(senha)){
            System.out.println("=== Extrato Conta Poupança ===");
            super.imprimirInfoComum();
        }
    }
}
