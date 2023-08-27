package br.com.mtonon.parkingapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.parkingapi.controller.exception.ErrorMessage;
import br.com.mtonon.parkingapi.entity.Usuario;
import br.com.mtonon.parkingapi.entity.dto.UsuarioCreateDTO;
import br.com.mtonon.parkingapi.entity.dto.UsuarioResponseDTO;
import br.com.mtonon.parkingapi.entity.dto.UsuarioSenhaDTO;
import br.com.mtonon.parkingapi.entity.dto.mapper.UsuarioMapper;
import br.com.mtonon.parkingapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição, exclusão e leitura de um usuário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Operation(summary = "Criar um novo usuário", description = "Recurso para criação de um novo usuário.",
			responses = {
					@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
					@ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos", 
						content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))		
			})
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> create(@RequestBody @Valid UsuarioCreateDTO objDTO) {
		Usuario user = this.usuarioService.save(UsuarioMapper.toUsuario(objDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
		Usuario user = this.usuarioService.find(id);
		return ResponseEntity.ok().body(UsuarioMapper.toDTO(user));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UsuarioSenhaDTO objDTO) {
		this.usuarioService.editPassword(id, objDTO.getSenhaAtual(), objDTO.getNovaSenha(), objDTO.getConfirmaSenha());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
		List<Usuario> listagem = this.usuarioService.buscarTodos();
		return ResponseEntity.ok().body(UsuarioMapper.toListDTO(listagem));
	}

}
