package Sistema_de_Doacoes.core.exceptions;

public class CpfNaoEncontradoException extends RuntimeException {
    public CpfNaoEncontradoException(String message) {
        super(message);
    }
    public CpfNaoEncontradoException() {
        super("CPF não encontrado");
    }
}
