package Sistema_de_Doacoes.service;

import Sistema_de_Doacoes.core.exceptions.*;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoRequestDTO;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoRequestUpdateDTO;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoResponseDTO;
import Sistema_de_Doacoes.model.Instituicao;
import Sistema_de_Doacoes.repository.DoacaoRepository;
import Sistema_de_Doacoes.repository.InstituicaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;
    private final DoacaoRepository doacaoRepository;

    //Metodo responsável por adicionar nova instituição
    @Transactional
    public Instituicao newInstituicao(InstituicaoRequestDTO instituicaoRequestDTO)
    {
        Instituicao existeCNPJ = this.instituicaoRepository.findByCnpj(instituicaoRequestDTO.getCnpj());
        if(existeCNPJ != null && existeCNPJ.getCnpj().equals(instituicaoRequestDTO.getCnpj()))
        {
            throw new CnpjRepetidoException();
        }

        Instituicao existeEmail = this.instituicaoRepository.findByEmail(instituicaoRequestDTO.getEmail());
        if(existeEmail != null && existeEmail.getEmail().equals(instituicaoRequestDTO.getEmail()))
        {
            throw new EmailRepetidoException();
        }

        Instituicao existeTelefone = this.instituicaoRepository.findByTelefone(instituicaoRequestDTO.getTelefone());
        if(existeTelefone != null && existeTelefone.getTelefone().equals(instituicaoRequestDTO.getTelefone()))
        {
            throw new TelefoneRepetidoException("Telefone de instituição já cadastrado");
        }

        Instituicao instituicao = instituicaoRequestDTO.toInstituicao();
        return this.instituicaoRepository.save(instituicao);
    }

    @Transactional
    //Metodo responsável por atualizar instituição
    public Instituicao putInstituicao(String cnpj, InstituicaoRequestUpdateDTO instituicaoRequestDTO)
    {
        Instituicao instituicaoCnpj= buscarCNPJ(cnpj);

        Instituicao instEmail= this.instituicaoRepository.findByEmail(instituicaoRequestDTO.getEmail());
        if(instEmail !=null && instEmail.getEmail().equals(instituicaoRequestDTO.getEmail()))
        {
            throw new EmailRepetidoException("Email de instituição já cadastrado");
        }

        Instituicao instTelefone = this.instituicaoRepository.findByTelefone(instituicaoRequestDTO.getTelefone());
        if(instTelefone != null && instTelefone.getTelefone().equals(instituicaoRequestDTO.getTelefone()))
        {
            throw new TelefoneRepetidoException("Telefone de instituição já cadstrado");
        }

        instituicaoRequestDTO.updateInstituicao(instituicaoCnpj);
        return this.instituicaoRepository.save(instituicaoCnpj);
    }

    //Metodo responsável por listar por cnpj
    public InstituicaoResponseDTO listarPorCNPJ(String cnpj)
    {
        Instituicao instituicaoCNPJ = buscarCNPJ(cnpj);
        return InstituicaoResponseDTO.fromInstituicao(instituicaoCNPJ);
    }

    //Metodo responsável por listar por nome
    public InstituicaoResponseDTO listarPorNome(String nome)
    {
        Instituicao instituicaoNome = this.instituicaoRepository.findByNome(nome);

        if(instituicaoNome == null)
        {
            throw new CnpjNaoEncontradoException("Empresa não encontrada");
        }

        return InstituicaoResponseDTO.fromInstituicao(instituicaoNome);
    }

    public List<InstituicaoResponseDTO> listarTodos()
    {
        List<Instituicao> instituicaoList = this.instituicaoRepository.findAll();

        if(instituicaoList.isEmpty())
        {
            throw new BancoVazioException();
        }

        return instituicaoList.stream().map(i-> InstituicaoResponseDTO.fromInstituicao(i)).toList();
    }

    //Metodo responsável por exclusão
    public Instituicao deletarInstituicao(String cnpj)
    {
        Instituicao instituicao = buscarCNPJ(cnpj);

        boolean possuiInstuticao = doacaoRepository.existsByInstituicaoCnpj(cnpj);
        if(possuiInstuticao)
        {
            throw new RuntimeException("Instuição possui doação associada");
        }

        this.instituicaoRepository.delete(instituicao);
        return instituicao;
    }

    //Metodo responsável por buscar CNPJ
    public Instituicao buscarCNPJ(String cnpj)
    {
        Instituicao instituicaoCNPJ = this.instituicaoRepository.findByCnpj(cnpj);

        if(instituicaoCNPJ==null)
        {
            throw new CnpjNaoEncontradoException();
        }

        return instituicaoCNPJ;
    }
}