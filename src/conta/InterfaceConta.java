package conta;

public interface InterfaceConta {
    void sacar(double valor, int senha);
    void depositar(double valor);
    void transferir(double valor, InterfaceConta contaDestino, int senha);
    void imprimirExtrato(int senha);
}
