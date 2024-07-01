package conta;

import banco.Banco;
import cliente.Cliente;

public abstract class Conta implements InterfaceConta{

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo = 0;
    protected Cliente cliente;

    public Conta(Cliente cliente){
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        Banco.adicionaConta(this);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void sacar(double valor) {
        if(saldo >= valor) {
            saldo -= valor;
        }
    }

    @Override
    public void depositar(double valor) {
        if(valor > 0){
            saldo += valor;
        }
    }

    @Override
    public void transferir(double valor, InterfaceConta contaDestino) {
        double saldoAntesSaque = saldo;
        this.sacar(valor);
        if(saldo < saldoAntesSaque){
            contaDestino.depositar(valor);
        }
    }

    protected void imprimirInfoComum() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
    }

    @Override
    public String toString() {
        return "Conta: " + agencia + ". Numero: " + numero + ". Titular: " + cliente;
    }

}
