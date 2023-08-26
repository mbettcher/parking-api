package br.com.mtonon.parkingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.parkingapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
