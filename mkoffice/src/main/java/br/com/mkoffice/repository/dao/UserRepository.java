package br.com.mkoffice.repository.dao;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.model.UserEntity;
import br.com.mkoffice.repository.jpa.JpaGenericDao;

public class UserRepository  extends JpaGenericDao<UserEntity, Long>{
	
	public UserEntity findUserByLogin(String login, String password) {
		try {
			return getEntityManager().createQuery("SELECT u FROM UserEntity u WHERE u.username = '"+login.toLowerCase()+"' AND u.password = '"+password+"'" , UserEntity.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean existsUserWithUsername(UserEntity usuario) {
			StringBuilder query = new StringBuilder();
			query.append("FROM UserEntity u WHERE u.username = '"+usuario.getUsername().toLowerCase()+"'");
			if(usuario.getId() != null){
				query.append(" AND u.id <> "+usuario.getId());
			}
			
		try{
			getEntityManager().createQuery(query.toString(), UserEntity.class).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}
	
	public boolean existsUserWithCpf(UserEntity usuario) {

		StringBuilder query = new StringBuilder();
		query.append("FROM UserEntity u WHERE u.cpf = "+usuario.getCpf()+" ");
		if(usuario.getId() != null){
			query.append(" AND u.id <> "+usuario.getId());
		}
		
		try{
			getEntityManager().createQuery(query.toString(), UserEntity.class).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}
	
	public List<UserEntity> findUserbyFilter(String username) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT u FROM UserEntity u").append(" ");
		query.append("WHERE u.username LIKE '"+username.toLowerCase()+"%'");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}

}
