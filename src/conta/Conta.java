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
        if(verificaSenha(senha)) {
            if(saldo >= valor) {
                saldo -= valor;
            }
        }
    }

    @Override
    public void depositar(double valor) {
        if(valor > 0){
            saldo += valor;
        }
    }

    @Override
    public void transferir(double valor, InterfaceConta contaDestino, int senha) {
        double saldoAtual = saldo;
        this.sacar(valor, senha);
        if(saldo == saldoAtual){
            contaDestino.depositar(valor);
        }
    }

    protected void imprimirInfoComum() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
    }

    protected boolean verificaSenha(int senha){
        return this.senha == senha;
    }

}
