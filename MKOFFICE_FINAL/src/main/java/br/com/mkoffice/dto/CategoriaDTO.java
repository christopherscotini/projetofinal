/**
 * 
 */
package br.com.mkoffice.dto;

import br.com.mkoffice.model.admin.SecaoEntity;

/**
 * @author christopher.rozario
 * 
 */
public class CategoriaDTO {

	private Long codCategoria;
	private String descCategoria;
	private SecaoEntity codSecao;
	
	public CategoriaDTO() {
		codSecao = new SecaoEntity();
	}

	public CategoriaDTO(Long codCategoria, String descCategoria,
			SecaoEntity codSecao) {
		super();
		this.codCategoria = codCategoria;
		this.descCategoria = descCategoria;
		this.codSecao = codSecao;
	}



	public Long getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(Long codCategoria) {
		this.codCategoria = codCategoria;
	}
	public String getDescCategoria() {
		return descCategoria;
	}
	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}
	public SecaoEntity getCodSecao() {
		return codSecao;
	}
	public void setCodSecao(SecaoEntity codSecao) {
		this.codSecao = codSecao;
	}
	
}
