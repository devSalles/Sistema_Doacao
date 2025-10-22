package Sistema_de_Doacoes.core.exceptions;

public class CpfNaoEncontrado extends RuntimeException {
    public CpfNaoEncontrado(String message) {
        super(message);
    }
    public CpfNaoEncontrado() {
        super("CPF não encontrado");
    }
}
