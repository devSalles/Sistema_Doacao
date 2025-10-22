package Sistema_de_Doacoes.core.exceptions;

public class IdNaoEncontrado extends RuntimeException {
    public IdNaoEncontrado(String message) {
        super(message);
    }
    public IdNaoEncontrado() {
        super("Id não encontrado");
    }
}
