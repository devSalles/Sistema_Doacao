package Sistema_de_Doacoes.core.infra;

import Sistema_de_Doacoes.core.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandlers {

    //Exceção para entrada inválida de dados
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageRestError> excecoes_de_entrada(MethodArgumentNotValidException ex)
    {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage()).findFirst().orElse("Erro de validação");
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST,errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }

    //Tratamento de exceções globais
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageRestError> excecoesGlobais(Exception ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageRestError);
    }

    //Exceção para nenhum registro cadastrado no banco de dados
    @ExceptionHandler(BancoVazioException.class)
    public ResponseEntity<MessageRestError> bancoVazio(BancoVazioException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }

    //Exceção para CPF repetido
    @ExceptionHandler(CpfRepetidoException.class)
    public ResponseEntity<MessageRestError> cpfRepetido(CpfRepetidoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageRestError);
    }

    //Exceção para CPF não encontrado
    @ExceptionHandler(CpfNaoEncontradoException.class)
    public ResponseEntity<MessageRestError> cpfNaoEncontrado(CpfNaoEncontradoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }

    //Exceção para CNPJ repetido
    @ExceptionHandler(CnpjRepetidoException.class)
    public ResponseEntity<MessageRestError> cnpjRepetido(CnpjRepetidoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageRestError);
    }

    //Exceção para CNPJ não encontrado
    @ExceptionHandler(CnpjNaoEncontradoException.class)
    public ResponseEntity<MessageRestError> cnpjNaoEncontrado(CnpjNaoEncontradoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }

    //Exceção para telefone repetido
    @ExceptionHandler(TelefoneRepetidoException.class)
    public ResponseEntity<MessageRestError> telefoneRepetidoException(TelefoneRepetidoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.CONFLICT,ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageRestError);
    }

    //Exceção para email repetido
    @ExceptionHandler(EmailRepetidoException.class)
    public ResponseEntity<MessageRestError> EmailRepetido(EmailRepetidoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageRestError);
    }

    //Exceção para ID não encontrado
    @ExceptionHandler(IdNaoEncontradoException.class)
    public ResponseEntity<MessageRestError> idNaoEncontrado(IdNaoEncontradoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }
}
