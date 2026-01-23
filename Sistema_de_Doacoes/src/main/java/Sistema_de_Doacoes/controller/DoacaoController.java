package Sistema_de_Doacoes.controller;

import Sistema_de_Doacoes.dto.doacao.DoacaoRequestDTO;
import Sistema_de_Doacoes.dto.doacao.DoacaoUpdateRequestDTO;
import Sistema_de_Doacoes.service.DoacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/doacao")
@RequiredArgsConstructor
@Tag(name = "Doação")
public class DoacaoController {

    private final DoacaoService doacaoService;

    @PostMapping("/nova-doacao")
    public ResponseEntity<Object> adicionarDoacao(@Valid @RequestBody DoacaoRequestDTO doacaoRequestDTO)
    {
        return ResponseEntity.ok(this.doacaoService.novaDoacao(doacaoRequestDTO));
    }

    @PutMapping("/atualizar-doacao/{id}")
    public ResponseEntity<Object> atualizarDoacao(@PathVariable Long id, @Valid @RequestBody DoacaoUpdateRequestDTO doacaoRequestDTO)
    {
        return ResponseEntity.ok(this.doacaoService.atualizarDoacao(id,doacaoRequestDTO));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarID(@PathVariable Long id)
    {
        return ResponseEntity.ok(this.doacaoService.buscarID(id));
    }

    @GetMapping("/buscar-cpf/{cpf}")
    public ResponseEntity<Object> mostrarCpf(@PathVariable String cpf)
    {
        return ResponseEntity.ok(this.doacaoService.buscarDoador(cpf));
    }

    @GetMapping("/buscar-cnpj/{cnpj}")
    public ResponseEntity<Object> mostarCnpj(@PathVariable String cnpj)
    {
        return ResponseEntity.ok(this.doacaoService.buscarInst(cnpj));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Object> mostarTodos()
    {
        return ResponseEntity.ok(this.doacaoService.listarTodos());
    }

    @GetMapping("/listar-por-periodo/{cpf}/{dataInicio}/{dataFinal}")
    public ResponseEntity<Object> mostrarPorPeriodo(@PathVariable String cpf, @PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFinal)
    {
        return ResponseEntity.ok(this.doacaoService.listarPorPeriodo(cpf,dataInicio,dataFinal));
    }

    @GetMapping("soma-total-instituicao-doacao/{cnpj}")
    public ResponseEntity<Object> somaTotal(@PathVariable String cnpj)
    {
        return ResponseEntity.ok(this.doacaoService.listarSomaTotalPorInstituicao(cnpj));
    }

    @DeleteMapping("/excluir-id/{id}")
    public ResponseEntity<Object> deletarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(this.doacaoService.deletarDoacao(id));
    }
}