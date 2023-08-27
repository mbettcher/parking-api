package br.com.mtonon.parkingapi.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioSenhaDTO {
	
	private String senhaAtual;
	private String novaSenha;
	private String confirmaSenha;

}
