package br.com.mtonon.parkingapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.parkingapi.entity.Usuario;
import br.com.mtonon.parkingapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		Usuario user = this.usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
