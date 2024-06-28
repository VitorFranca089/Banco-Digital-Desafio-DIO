package conta;

import cliente.Cliente;

public class ContaCorrente extends Conta{

    public ContaCorrente(Cliente cliente, int senha) {
        super(cliente, senha);
    }

    @Override
    public void imprimirExtrato(int senha) {
        if(verificaSenha(senha)){
            System.out.println("=== Extrato Conta Corrente ===");
            super.imprimirInfoComum();
        }
    }
}
