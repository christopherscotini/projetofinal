package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.utils.Adapter;

public class CategoriaRepository extends JpaGenericDao<CategoriaEntity, Long>{

	
	public List<CategoriaDTO> findAllCategorias() {
		List<CategoriaEntity> listaRetornada = getEntityManager().createQuery("SELECT c FROM CategoriaEntity c ORDER BY c.codSecao.codSecao, c.descCategoria ASC", CategoriaEntity.class).getResultList();
		List<CategoriaDTO> returnzz = new ArrayList<CategoriaDTO>();
		for (int i = 0; i < listaRetornada.size(); i++) {
			returnzz.add(Adapter.entityToDto(listaRetornada.get(i)));
		}
		return returnzz;
	}

	
	public List<CategoriaDTO> findByFilter(String descCategoria, Integer codSecao) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM CategoriaEntity c");
		query.append(_ESPACE);
		query.append("WHERE c.descCategoria LIKE '"+descCategoria+"%'");
		query.append(_ESPACE);
		if(codSecao > 0){
			query.append("AND c.codSecao.codSecao = "+codSecao);
			query.append(_ESPACE);
		}
		query.append("ORDER BY c.descCategoria ASC");
		List<CategoriaEntity> listaRetornada = getEntityManager().createQuery(query.toString(), CategoriaEntity.class).getResultList();
		List<CategoriaDTO> returnzz = new ArrayList<CategoriaDTO>();
		for (int i = 0; i < listaRetornada.size(); i++) {
			returnzz.add(Adapter.entityToDto(listaRetornada.get(i)));
		}
		return returnzz;
	}
	
	
	public boolean existCategoria(CategoriaDTO dto) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT c FROM CategoriaEntity c");
			builder.append(_ESPACE);
			builder.append("WHERE c.descCategoria = '"+dto.getDescCategoria()+"'");
			builder.append(_ESPACE);
			builder.append("AND c.codSecao.codSecao = "+dto.getCodSecao().getCodSecao()+"");
			builder.append(_ESPACE);
			builder.append("AND c.codCategoria <> "+dto.getCodCategoria()+"");
			builder.append(_ESPACE);
			builder.append("");
			
			try{
				if(getEntityManager().createQuery(builder.toString()).getSingleResult() == null){
					return false;
				}
				return true;
			}catch(NoResultException nre){
				return false;
			}
			
	}
}
