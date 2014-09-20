package br.com.mkoffice.mock;

import java.util.Date;
import java.util.Random;

import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.dto.ConsultoraDTO;
import br.com.mkoffice.dto.EnderecoDTO;
import br.com.mkoffice.dto.PessoaDTO;
import br.com.mkoffice.dto.UserDTO;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;

public class Mock {

	public static EnderecoDTO createAddress(){
		EnderecoDTO dto = new EnderecoDTO();
		dto.setBairro("JD. ESPERANÇA");
    	dto.setCep("06414-025");
    	dto.setCidade("BARUERI");
    	dto.setComplemento("BL. C AP. 55");
    	dto.setEstado("SP");
    	dto.setLogradouro("RUA WERNER GOLDBERG");
    	dto.setNumero("179");
    	dto.setTipoLogradouro("RUA");
		
		return dto;
		
	}
	
	public static PessoaDTO createPerson(){
		PessoaDTO dto = new PessoaDTO();
		dto.setDataNascimento(new Date());
		dto.setEmail("cliente.cliente@gmail.com");
		dto.setEndereco(createAddress());
		dto.setNome("CLIENTE COMPRADOR");
		dto.setNumCelular(generateRandomNumber());
		dto.setNumTelefone(generateRandomNumber());
		dto.setSexo("F");
		return dto;
	}

	public static UserDTO createUser() {
		UserDTO dto = new UserDTO();
		dto.setId(1L);
		return dto;
	}	
	
	public static ClienteDTO createClient(){
		ClienteDTO dto = new ClienteDTO();
		dto.setId(null);
		dto.setDadosPessoa(createPerson());
		dto.setDataPrimeiraVenda(new Date());
		dto.setDataUltimaVenda(new Date());
		dto.setDataInicioAcompanhamento(new Date());
		dto.setInfAdicional("TESTE TESTE TESTE TESTE TESTE");
		dto.setUsuario(createUser());
		
		return dto;
	}

	public static ConsultoraDTO createConsulting() {
		ConsultoraDTO dto = new ConsultoraDTO();
		dto.setId(null);
		dto.setDadosPessoa(createPerson());
		dto.setDataDesativacao(new Date());
		dto.setDataInicio(new Date());
		dto.setInfAdicional("");
		dto.setStatus(StatusConsultoraEnum.ATIVO);
		dto.setUsuario(createUser());
		
		return dto;
	}
	
	private static Long generateRandomNumber(){
		Random random = new Random();
		return random.nextLong();
	}

	
}
