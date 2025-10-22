package Sistema_de_Doacoes.core.exceptions;

public class IdNaoEncontradoException extends RuntimeException {
    public IdNaoEncontradoException(String message) {
        super(message);
    }
    public IdNaoEncontradoException() {
        super("Id não encontrado");
    }
}
