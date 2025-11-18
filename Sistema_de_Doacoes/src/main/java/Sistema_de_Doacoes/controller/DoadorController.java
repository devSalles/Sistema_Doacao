package Sistema_de_Doacoes.controller;

import Sistema_de_Doacoes.dto.doador.DoadorRequestDTO;
import Sistema_de_Doacoes.dto.doador.DoadorRequestUpdateDTO;
import Sistema_de_Doacoes.service.DoadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doador")
@RequiredArgsConstructor
@Tag(name = "Doador")
public class DoadorController {

    private final DoadorService doadorService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@Valid @RequestBody DoadorRequestDTO doadorRequestDTO)
    {
        return ResponseEntity.ok(this.doadorService.postDoador(doadorRequestDTO));
    }

    @PutMapping("/atualizar-cpf/{cpf}")
    public ResponseEntity<Object> putCPF(@PathVariable String cpf, @Valid @RequestBody DoadorRequestUpdateDTO doadorRequestDTO)
    {
        return ResponseEntity.ok(this.doadorService.putDoador(cpf,doadorRequestDTO));
    }

    @GetMapping("/exibir-id/{id}")
    public ResponseEntity<Object> getID(@PathVariable Long id)
    {
        return ResponseEntity.ok(this.doadorService.listarPorID(id));
    }

    @GetMapping("/exibir-cpf/{cpf}")
    public ResponseEntity<Object> getCPF(@PathVariable String cpf)
    {
        return ResponseEntity.ok(this.doadorService.listarPorCPF(cpf));
    }

    @GetMapping("/exibir-todos")
    public ResponseEntity<Object> getAll()
    {
        return ResponseEntity.ok(this.doadorService.listarTodos());
    }

    @DeleteMapping("/deletar-cpf/{cpf}")
    public ResponseEntity<Object> deleteByCpf(@PathVariable String cpf)
    {
        return ResponseEntity.ok(this.doadorService.excluirDoador(cpf));
    }
}
