package Sistema_de_Doacoes.core.exceptions;

public class CnpjRepetidoException extends RuntimeException {
    public CnpjRepetidoException(String message) {
        super(message);
    }
    public CnpjRepetidoException() {
        super("CNPJ já cadastrado no sistema");
    }
}
