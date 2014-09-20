package br.com.mkoffice.repository.dao;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.model.CategoriaEntity;
import br.com.mkoffice.repository.jpa.JpaGenericDao;


public class CategoriaRepository extends JpaGenericDao<CategoriaEntity, Long>{

	public List<CategoriaEntity> findAllCategorias() {
		List<CategoriaEntity> listaRetornada = getEntityManager().createQuery("SELECT c FROM CategoriaEntity c ORDER BY c.codSecao.codSecao, c.descCategoria ASC", CategoriaEntity.class).getResultList();

		return listaRetornada;
	}

	
	public List<CategoriaEntity> findByFilter(String descCategoria, Integer codSecao) {
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
		
		return listaRetornada;
	}
	
	
	public boolean existCategoria(CategoriaEntity dto) {
			
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT c FROM CategoriaEntity c");
			builder.append(_ESPACE);
			builder.append("WHERE c.descCategoria = '"+dto.getDescCategoria()+"'");
			builder.append(_ESPACE);
			builder.append("AND c.codSecao.codSecao = "+dto.getCodSecao().getId()+"");
			builder.append(_ESPACE);
			builder.append("AND c.codCategoria <> "+dto.getId()+"");
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
