package br.com.mtonon.parkingapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mtonon.parkingapi.entity.Usuario;
import br.com.mtonon.parkingapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	@Transactional(readOnly = true)
	public Usuario find(Long id) {
		return this.usuarioRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado."));
	}

	@Transactional
	public Usuario editPassword(Long id, Usuario usuario) {
		Usuario user = find(id);
		user.setPassword(usuario.getPassword());
		return user;
	}

	public List<Usuario> buscarTodos() {
		return this.usuarioRepository.findAll();
	}

}
