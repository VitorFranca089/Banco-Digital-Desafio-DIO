package conta;

import cliente.Cliente;

public abstract class Conta implements InterfaceConta{

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo = 0;
    protected Cliente cliente;
    protected int senha;

    public Conta(Cliente cliente, int senha){
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.senha = senha;
    }

    @Override
    public void sacar(double valor, int senha) {
        if(verificaSenha(senha)){
            saldo -= valor;
            System.out.printf("Saque realizado com sucesso. Seu saldo atual: R$ %.2f\n", saldo);
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        System.out.printf("Deposito realizado com sucesso. Seu saldo atual: R$ %.2f\n", saldo);
    }

    @Override
    public void transferir(double valor, InterfaceConta contaDestino, int senha) {
        if(verificaSenha(senha)) {
            this.sacar(valor, senha);
            contaDestino.depositar(valor);
        }
    }

    protected void imprimirInfoComum() {
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Agência: " + this.agencia);
        System.out.println("Número da conta: " + this.numero);
        System.out.println("Saldo atual: R$ " + String.format("%.2f", this.saldo));
    }

    protected boolean verificaSenha(int senha){
        boolean verificacao = this.senha == senha;
        return verificacao;
    }

}
