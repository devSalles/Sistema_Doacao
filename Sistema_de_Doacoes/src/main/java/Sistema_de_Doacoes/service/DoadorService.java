package Sistema_de_Doacoes.service;

import Sistema_de_Doacoes.core.exceptions.*;
import Sistema_de_Doacoes.dto.doador.DoadorRequestDTO;
import Sistema_de_Doacoes.dto.doador.DoadorRequestUpdateDTO;
import Sistema_de_Doacoes.dto.doador.DoadorResponseDTO;
import Sistema_de_Doacoes.model.Doador;
import Sistema_de_Doacoes.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoadorService {

    private final DoadorRepository doadorRepository;


    public Doador postDoador(DoadorRequestDTO doadorRequestDTO)
    {
        //Veiricação de CPF repitido
        Doador existeCPF = this.doadorRepository.findByCpf(doadorRequestDTO.getCpf());
        if(existeCPF!= null && existeCPF.getCpf().equals(doadorRequestDTO.getCpf()))
        {
            throw new CpfRepetidoException();
        }

        //Veiricação de email repitido
        Doador existeEmail = this.doadorRepository.findByEmail(doadorRequestDTO.getEmail());
        if(existeEmail != null && existeEmail.getEmail().equals(doadorRequestDTO.getEmail()))
        {
            throw new EmailRepetidoException();
        }

        Doador doador = doadorRequestDTO.toDoador();
        return this.doadorRepository.save(doador);
    }

    public Doador putDoador(String cpf, DoadorRequestUpdateDTO doadorRequestDTO)
    {
        Doador doador = buscaPorCPF(cpf);

        doadorRequestDTO.updateDoador(doador);
        return this.doadorRepository.save(doador);
    }

    //Exibir por ID
    public DoadorResponseDTO listarPorID(Long id)
    {
        Doador doador = this.doadorRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);
        return DoadorResponseDTO.fromDoador(doador);
    }

    //Exibir registro por CPF
    public DoadorResponseDTO listarPorCPF(String cpf)
    {
        Doador doador = buscaPorCPF(cpf);
        return DoadorResponseDTO.fromDoador(doador);
    }

    //Exibir todos os registro
    public List<DoadorResponseDTO> listarTodos()
    {
        List<Doador> doadorList = this.doadorRepository.findAll();
        if(doadorList.isEmpty())
        {
            throw new BancoVazioException();
        }

        return doadorList.stream().map(d->DoadorResponseDTO.fromDoador(d)).toList();
    }


    //Metodo responsável pela busca do CPF
    public Doador buscaPorCPF(String cpf)
    {
        Doador doador = this.doadorRepository.findByCpf(cpf);
        if(doador.getCpf().isEmpty() || doador.getCpf().isBlank())
        {
            throw new CpfNaoEncontradoException();
        }

        return doador;
    }
}
