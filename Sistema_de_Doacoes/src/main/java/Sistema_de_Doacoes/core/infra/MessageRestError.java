package Sistema_de_Doacoes.core.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MessageRestError {

    private HttpStatus status;
    private String message;
    private LocalDate timeStamp;

    //mapeamento de erros de validação
    private Map<String,String> fieldErros;

    public MessageRestError(HttpStatus status, String message)
    {
        this.status=status;
        this.message=message;
        this.timeStamp=LocalDate.now();
    }

    public MessageRestError(HttpStatus status, String message,Map<String,String> fieldErrors)
    {
        this.status=status;
        this.message=message;
        this.timeStamp=LocalDate.now();
        this.fieldErros=fieldErrors;
    }
}
