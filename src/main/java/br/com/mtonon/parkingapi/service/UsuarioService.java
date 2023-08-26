package br.com.mtonon.parkingapi.service;

import org.springframework.stereotype.Service;

import br.com.mtonon.parkingapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

}
