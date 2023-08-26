package br.com.mtonon.parkingapi.entity.enums;

public enum RoleEnum {
	
	ROLE_ADMIN(1, "Administrador"),
	ROLE_CLIENTE(2, "Cliente");
	
	private Integer codigo;
	private String descricao;
	
	private RoleEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public RoleEnum toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(RoleEnum x : RoleEnum.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + codigo);
	}

}
