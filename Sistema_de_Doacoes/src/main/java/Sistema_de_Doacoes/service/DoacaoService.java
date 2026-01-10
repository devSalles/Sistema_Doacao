package Sistema_de_Doacoes.service;

import Sistema_de_Doacoes.core.exceptions.BancoVazioException;
import Sistema_de_Doacoes.core.exceptions.CnpjNaoEncontradoException;
import Sistema_de_Doacoes.core.exceptions.CpfNaoEncontradoException;
import Sistema_de_Doacoes.core.exceptions.IdNaoEncontradoException;
import Sistema_de_Doacoes.dto.doacao.DoacaoRequestDTO;
import Sistema_de_Doacoes.dto.doacao.DoacaoResponseDTO;
import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import Sistema_de_Doacoes.model.Instituicao;
import Sistema_de_Doacoes.repository.DoacaoRepository;
import Sistema_de_Doacoes.repository.DoadorRepository;
import Sistema_de_Doacoes.repository.InstituicaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final DoadorRepository doadorRepository;
    private final InstituicaoRepository instituicaoRepository;

    //Metodo responsável por adicionar doação
    @Transactional
    public DoacaoResponseDTO novaDoacao(DoacaoRequestDTO doacaoRequestDTO,String cpf, String cnpj)
    {
        Doador doadorCPF = buscarCPF(cpf);
        Instituicao instituicaoCNPJ = buscarCNPJ(cnpj);

        Doacao doacao = doacaoRequestDTO.toDoacao(doadorCPF,instituicaoCNPJ);

        this.doadorRepository.save(doadorCPF);
        this.instituicaoRepository.save(instituicaoCNPJ);
        this.doacaoRepository.save(doacao);

        return DoacaoResponseDTO.fromDoacao(doacao);
    }

    //Metodo responsável por atualizar doação
    @Transactional
    public DoacaoResponseDTO atualizarDoacao(Long id, DoacaoRequestDTO doacaoRequestDTO, String cpf, String cnpj)
    {
        Doacao doacaoID = this.doacaoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);

        Doador doadorCpf = buscarCPF(cpf);
        Instituicao instituicaoCnpj = buscarCNPJ(cnpj);
        doacaoRequestDTO.updateDoacao(doacaoID,doadorCpf,instituicaoCnpj);

        this.doacaoRepository.save(doacaoID);
        return DoacaoResponseDTO.fromDoacao(doacaoID);
    }

    public DoacaoResponseDTO buscarID(Long id)
    {
        Doacao doacao = this.doacaoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);
        return DoacaoResponseDTO.fromDoacao(doacao);
    }

    //Metodo responsável por exibir doações por CPF individuais
    public List<Doacao> buscarDoador(String cpf)
    {
        Doador doador = buscarCPF(cpf);
        return this.doacaoRepository.findByDoador(doador);
    }

    //Metodo responsável por exibir doações por CNPJ individuais
    public List<Instituicao> buscarInst(String cnpj)
    {
        Instituicao instituicao = buscarCNPJ(cnpj);
        return this.doacaoRepository.findByInstituicao(instituicao);
    }

    //Metodo responsável por exibir todas as doações
    public List<DoacaoResponseDTO> listarTodos()
    {
        List<Doacao> doacao = this.doacaoRepository.findAll();
        if(doacao.isEmpty())
        {
            throw new BancoVazioException();
        }

        return doacao.stream().map(DoacaoResponseDTO::fromDoacao).toList();
    }

    public List<Doacao> listarPorPeriodo(String cpf, LocalDate DataInicial, LocalDate DataFinal)
    {
        Doador doador = buscarCPF(cpf);
        return this.doacaoRepository.findByDoadorAndDataBetween(doador, DataInicial, DataFinal);
    }

     public Double listarSomaTotalPorInstituicao(String cnpj)
    {
        Instituicao instituicaoCNPJ = buscarCNPJ(cnpj);
        return this.doacaoRepository.somaTotalPorInstitucao(instituicaoCNPJ);
    }

    //Metodo responsável por exclusão
    public DoacaoResponseDTO deletarDoacao(Long id)
    {
        Doacao doacao = this.doacaoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);
        this.doacaoRepository.delete(doacao);
        return DoacaoResponseDTO.fromDoacao(doacao);
    }

    //---------------------- METODOS AUXILIARES ----------------------

    //Metodo responsável por realizar busca por CPF
    public Doador buscarCPF(String cpf)
    {
        Doador doador = this.doadorRepository.findByCpf(cpf);
        if(doador == null)
        {
            throw new CpfNaoEncontradoException();
        }
        return doador;
    }

    //Metodo responsável por realizar busca por CNPJ
    public Instituicao buscarCNPJ(String cnpj)
    {
        Instituicao instituicao = this.instituicaoRepository.findByCnpj(cnpj);
        if(instituicao == null)
        {
            throw new CnpjNaoEncontradoException();
        }

        return instituicao;
    }
}