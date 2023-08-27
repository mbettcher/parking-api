package br.com.mtonon.parkingapi.entity.dto.mapper;

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

}
