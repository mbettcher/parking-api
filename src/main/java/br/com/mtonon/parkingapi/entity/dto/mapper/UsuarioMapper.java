package br.com.mtonon.parkingapi.entity.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import br.com.mtonon.parkingapi.entity.Usuario;
import br.com.mtonon.parkingapi.entity.dto.UsuarioCreateDTO;
import br.com.mtonon.parkingapi.entity.dto.UsuarioResponseDTO;

public class UsuarioMapper {
	
	public static Usuario toUsuario(UsuarioCreateDTO ojbDTO) {
		return new ModelMapper().map(ojbDTO, Usuario.class);
	}
	
	public static UsuarioResponseDTO toDTO(Usuario obj) {
		String role = obj.getRole().getDescricao();
		PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>() {
			
			@Override
			protected void configure() {
				map().setRole(role);
				
			}
		};
		
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(props);
		return mapper.map(obj, UsuarioResponseDTO.class);
	}
	
	/**
	 * Método responsável por  percorrer a lista de usuários recebiba como parâmetro 
	 * e pega cada usuário presente nesta lista e transforma do tipo Usuario para o tipo
	 * UsuarioResponseDTO e adiciona ao objeto de resposta, uma lista do tipo 
	 * UsuarioResponseDTO;
	 * 
	 * @param List de usuario
	 * @return List de UsuarioResponseDTO
	 */
	public static List<UsuarioResponseDTO> toListDTO(List<Usuario> usuarios) {
		return usuarios
				.stream()
				.map(obj -> toDTO(obj))
				.collect(Collectors.toList());
	}

}
