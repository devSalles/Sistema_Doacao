package Sistema_de_Doacoes.core.exceptions;

public class CnpjNaoEncontradoException extends RuntimeException {
    public CnpjNaoEncontradoException(String message) {
        super(message);
    }
    public CnpjNaoEncontradoException() {
        super("Cnpj não encontrado");
    }
}
