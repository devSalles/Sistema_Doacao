package Sistema_de_Doacoes.core.exceptions;

public class DoacaoAssociada extends RuntimeException {
    public DoacaoAssociada(String message) {
        super(message);
    }
    public DoacaoAssociada() {
        super("Instuição possui doação associada");
    }
}
