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

import br.com.mtonon.parkingapi.entity.Usuario;
import br.com.mtonon.parkingapi.entity.dto.UsuarioCreateDTO;
import br.com.mtonon.parkingapi.entity.dto.UsuarioResponseDTO;
import br.com.mtonon.parkingapi.entity.dto.mapper.UsuarioMapper;
import br.com.mtonon.parkingapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioCreateDTO objDTO) {
		Usuario user = this.usuarioService.save(UsuarioMapper.toUsuario(objDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
		Usuario user = this.usuarioService.find(id);
		return ResponseEntity.ok().body(UsuarioMapper.toDTO(user));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario user = this.usuarioService.editPassword(id, usuario);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> listagem = this.usuarioService.buscarTodos();
		return ResponseEntity.ok().body(listagem);
	}

}
