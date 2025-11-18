package Sistema_de_Doacoes.controller;

import Sistema_de_Doacoes.dto.instituicao.InstituicaoRequestDTO;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoRequestUpdateDTO;
import Sistema_de_Doacoes.service.InstituicaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicao")
@RequiredArgsConstructor
@Tag(name = "Instituição")
public class InstituicaoController {


    private final InstituicaoService instituicaoService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@Valid @RequestBody InstituicaoRequestDTO instituicaoRequestDTO)
    {
        return ResponseEntity.ok(this.instituicaoService.newInstituicao(instituicaoRequestDTO));
    }

    @PutMapping("/atualizar-cnpj/{cnpj}")
    public ResponseEntity<Object> atualizarPorCnpj(@PathVariable String cnpj,@Valid @RequestBody InstituicaoRequestUpdateDTO instituicaoRequestDTO)
    {
        return ResponseEntity.ok(this.instituicaoService.putInstituicao(cnpj,instituicaoRequestDTO));
    }

    @GetMapping("/exibir-cnpj/{cnpj}")
    public ResponseEntity<Object> listarPorCnpj(@PathVariable String cnpj)
    {
        return ResponseEntity.ok(this.instituicaoService.listarPorCNPJ(cnpj));
    }

    @GetMapping("/exibir-nome/{nome}")
    public ResponseEntity<Object> listarPorNome(@PathVariable String nome)
    {
        return ResponseEntity.ok(this.instituicaoService.listarPorNome(nome));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Object> listarTodos()
    {
        return ResponseEntity.ok(this.instituicaoService.listarTodos());
    }

    @DeleteMapping("/deletar-cnpj/{cnpj}")
    public ResponseEntity<Object> deletarCnpj(String cnpj)
    {
        return ResponseEntity.ok(this.instituicaoService.deletarInstituicao(cnpj));
    }

}
