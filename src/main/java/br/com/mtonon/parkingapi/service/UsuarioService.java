package br.com.mtonon.parkingapi.service;

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

}
