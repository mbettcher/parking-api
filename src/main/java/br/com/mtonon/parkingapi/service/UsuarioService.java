package br.com.mtonon.parkingapi.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mtonon.parkingapi.entity.Usuario;
import br.com.mtonon.parkingapi.repository.UsuarioRepository;
import br.com.mtonon.parkingapi.service.exception.EntityNotFoundException;
import br.com.mtonon.parkingapi.service.exception.UsernameUniqueViolationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario save(Usuario usuario) {
		try {
		return this.usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException ex) {
			throw new UsernameUniqueViolationException(String.format("Username '%s' já cadastrado.", usuario.getUsername()));
		}
	}
	
	@Transactional(readOnly = true)
	public Usuario find(Long id) {
		return this.usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuario com Id=%s não encontrado.", id)));
	}

	@Transactional
	public Usuario editPassword(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
		if(!novaSenha.equals(confirmaSenha)) {
			throw new RuntimeException("A nova senha é diferente de confirmação de senha!");
		}
		
		Usuario user = find(id);
		
		if(!senhaAtual.equals(user.getPassword())) {
			throw new RuntimeException("A senha atual não confere!");
		}

		user.setPassword(novaSenha);
		return user;
	}

	public List<Usuario> buscarTodos() {
		return this.usuarioRepository.findAll();
	}

}
