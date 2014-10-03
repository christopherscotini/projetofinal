/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.FluxoEstoqueBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.FluxoEstoqueRepository;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.view.constants.TipoFluxoEstoqueEnum;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class FluxoEstoqueBOImpl implements FluxoEstoqueBO{

	@Inject
	private FluxoEstoqueRepository dao = null;

	@Override
	public List<FluxoEstoqueEntity> listarTodos() {

		return dao.findByFilter(null);
	}

	@Override
	public List<FluxoEstoqueEntity> listarFluxosParaMovEstoque() {
		List<FluxoEstoqueEntity> lista = dao.findByFilter(null);
		List<FluxoEstoqueEntity> returnzz = new ArrayList<FluxoEstoqueEntity>();
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.ENTRADA_MANUAL.toString())
				|| lista.get(i).getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_DEMONSTRACAO.toString())
				|| lista.get(i).getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_MANUAL.toString()) 
				|| lista.get(i).getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_PRESENTE.toString())){ 
				returnzz.add(lista.get(i));
			}
		}
		
		return returnzz;
	}

	@Override
	public FluxoEstoqueEntity adicionarEntidade(FluxoEstoqueEntity entidade) {
		if(dao.existsFluxoEstoque(entidade)){
			throw new RegistroJaCadastradoException("Fluxo");
		}else{
			entidade.setDescFluxoEstoque(entidade.getDescFluxoEstoque().toUpperCase());
			
			return dao.insert(entidade);
		}
		
	}

	@Override
	public FluxoEstoqueEntity editarEntidade(FluxoEstoqueEntity entidade) {
		if(dao.existsFluxoEstoque(entidade)){
			throw new RegistroJaCadastradoException("Fluxo");
		}else{
			entidade.setDescFluxoEstoque(entidade.getDescFluxoEstoque().toUpperCase());
			
			return dao.update(entidade);
		}
	}

	@Override
	public List<FluxoEstoqueEntity> buscarEntidadesPorFiltro(String param) {
		return dao.findByFilter(param);
	}

	@Override
	public void validateForm(FluxoEstoqueEntity entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}

}
