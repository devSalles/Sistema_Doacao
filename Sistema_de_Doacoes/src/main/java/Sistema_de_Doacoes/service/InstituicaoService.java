package Sistema_de_Doacoes.service;

import Sistema_de_Doacoes.core.exceptions.BancoVazioException;
import Sistema_de_Doacoes.core.exceptions.CnpjNaoEncontradoException;
import Sistema_de_Doacoes.core.exceptions.CnpjRepetidoException;
import Sistema_de_Doacoes.core.exceptions.EmailRepetidoException;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoRequestDTO;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoResponseDTO;
import Sistema_de_Doacoes.model.Instituicao;
import Sistema_de_Doacoes.repository.InstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    //Metodo responsável por adicionar nova instituição
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

        Instituicao instituicao = instituicaoRequestDTO.toInstituicao();
        return this.instituicaoRepository.save(instituicao);
    }
    //Metodo responsável por atualizar instituição
    public Instituicao putInstituicao(String cnpj, InstituicaoRequestDTO instituicaoRequestDTO)
    {
        Instituicao instituicaoCnpj= buscarCNPJ(cnpj);

        instituicaoRequestDTO.updateInstituicao(instituicaoCnpj);
        return instituicaoCnpj;
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
