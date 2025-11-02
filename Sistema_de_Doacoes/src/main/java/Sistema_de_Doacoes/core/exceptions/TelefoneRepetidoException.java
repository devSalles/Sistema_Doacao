package Sistema_de_Doacoes.core.exceptions;

public class TelefoneRepetidoException extends RuntimeException {
    public TelefoneRepetidoException(String message) {
        super(message);
    }
    public TelefoneRepetidoException() {
        super("Telefone já cadastrado");
    }
}
