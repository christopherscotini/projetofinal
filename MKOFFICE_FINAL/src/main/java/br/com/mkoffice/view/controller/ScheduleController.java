package br.com.mkoffice.view.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.mkoffice.dto.AgendaDTO;
import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.agenda.AgendaEntity;
import br.com.mkoffice.model.agenda.TipoAgendaEntity;
import br.com.mkoffice.model.constants.TipoAgendaEnum;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class ScheduleController extends AbstractModelBean{

	private final String TELA_AGENDA = "/content/m-agenda/agenda.xhtml";
	
	private ScheduleModel eventModel;
	private List<AgendaDTO>listaAgenda;
	private AgendaDTO agenda;
	private List<ClienteEntity>clientes;
	
	private boolean habilitaPainelDetalhe;
	
	public ScheduleController() {
	}

	public String telaInicial() {
		inicializar();
		
		return TELA_AGENDA;
	}
    
	 	private void inicializar(){
	 		habilitaPainelDetalhe = false;
	        agenda = new AgendaDTO();
	        agenda.setUsuario(getLoginBean().getUsuario());
	        agenda.setTipoAgenda(getTipoAgendaLista().get(0));
	        carregarListaClientes();
	        eventModel = new DefaultScheduleModel();
	        
	        //recupera a lista de eventos
	        listaAgenda = agendaBO.buscarTodasPorUsuario(getLoginBean().getUsuario().getId());
	        
	        //percorre a lista de eventos e popula o calendario
	        for(AgendaDTO agenda : listaAgenda){
	            
	            DefaultScheduleEvent evento = new DefaultScheduleEvent();
	            evento.setAllDay(agenda.isDiaTodo());
	            evento.setEndDate(agenda.getDataFim());
	            evento.setStartDate(agenda.getDataInicio());
	            evento.setTitle(agenda.getDescricao());
	            evento.setData(agenda.getId());
	            evento.setId(String.valueOf(agenda.getId()));
	            evento.setEditable(true); //permitir que o usuario edite
	            
//	            aqui e setado um tipo especifico de css para cada tipo de evento
	            if(agenda.getTipoAgenda().getDescTipoAgenda().equalsIgnoreCase(TipoAgendaEnum.AGENDA_ACOMPANHAMENTO.toString())){
	                boolean primeiraFase = agenda.getDescricao().substring(0, agenda.getDescricao().lastIndexOf("FA")-1).equalsIgnoreCase("1�");
	                boolean segundaFase = agenda.getDescricao().substring(0, agenda.getDescricao().lastIndexOf("FA")-1).equalsIgnoreCase("2�");
	               
	                if (primeiraFase) {
	                	evento.setStyleClass("acomp-primeira-fase");
					}else{
						if (segundaFase) {
							evento.setStyleClass("acomp-segunda-fase");
						}else{
							evento.setStyleClass("acomp-terceira-fase");
						}
					}
	                
	            }else{
	            	
	            	if(agenda.getTipoAgenda().getDescTipoAgenda().equalsIgnoreCase(TipoAgendaEnum.PESSOAL.toString())){
	            		evento.setStyleClass("agenda-pessoal");
	            	}else{
	            		if(agenda.getTipoAgenda().getDescTipoAgenda().equalsIgnoreCase(TipoAgendaEnum.PROFISSIONAL.toString())){
	            			evento.setStyleClass("agenda-profissional");
	            		}
	            	}
	            	
	            }
	            
	            eventModel.addEvent(evento); //o evento e adicionado na lista
	        }
	    }
	    

		/**
     * Metodo para salvar o evento
	     */
    public void salvar(){
	        
	        //se o ID for zero tenho que incluir na lista
	        //caso contrario nao e necessario
	        if(agenda.getId() == null){ 
	            
	            //uma validacao basica para verificar se o usuario informou as datas corretamente
	            if(agenda.getDataInicio().getTime() <= agenda.getDataFim().getTime()){

	            	try{
	            		if(agenda.getTipoAgenda().getDescTipoAgenda().equalsIgnoreCase(TipoAgendaEnum.AGENDA_ACOMPANHAMENTO.toString())){
	            			adicionarAcompanhamento();
	            		}else{
	            			agenda.setUsuario(getLoginBean().getUsuario());
	            			agendaBO.adicionarEntidade(agenda);
	            		}
	                	FacesUtils.addMessageInclusaoSucesso();
	                }catch(BusinessException b){
	                	FacesUtils.addErrorMessage(b.getMessage());
	                }

	                inicializar();

	            }else{
	                //a data de inicio nao pode ser maior que a data final
	            }
	        
	        }else{
	        	try{
	        		agendaBO.editarEntidade(agenda);
                	FacesUtils.addMessageAlteracaoSucesso();
                }catch(BusinessException b){
                	FacesUtils.addErrorMessage(b.getMessage());
                }
	            inicializar();
	        }
	    }
	
	/**
     * Evento para quando o usuario clica em um espaco em branco no calendario
     * @param selectEvent 
     */
    public void quandoNovo(SelectEvent selectEvent) {  
        
        ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());  
        
        agenda = new AgendaDTO();
        agenda.setUsuario(getLoginBean().getUsuario());
        //recupero a data em q foi clicada
        agenda.setTipoAgenda(getTipoAgendaLista().get(0));
        agenda.setDataInicio(event.getStartDate());
        agenda.setDataFim(event.getEndDate());
        habilitaPainelDetalhe = false;
    }
    
    /**
     * Evento para quando usuario clica em um evento ja existente
     * @param selectEvent 
     */
    public void quandoSelecionado(SelectEvent selectEvent) {  
        
        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();  
        
        for(AgendaDTO agda : listaAgenda){
            if(agda.getId().equals(Long.valueOf(event.getData().toString()))){
                agenda = agda;
                habilitaPainelDetalhe = true;
                agenda.setTipoAgenda(agda.getTipoAgenda());
                break;
            }
        }
    }

    /**
     * Evento para quando o usuario 'move' um evento atraves de 'drag and drop'
     * @param event 
     */
    public void quandoMovido(ScheduleEntryMoveEvent event) {  
        
        for(AgendaDTO agda : listaAgenda){
            
            if(agda.getId().equals(Long.valueOf(event.getScheduleEvent().getData().toString()))){
                agenda = agda;
                try{
                	agendaBO.editarEntidade(agenda);
                	habilitaPainelDetalhe = false;
                	FacesUtils.addMessageAlteracaoSucesso();
                }catch(BusinessException b){
                	FacesUtils.addErrorMessage(b.getMessage());
                }
                break;
            }
        }
    }  
    
    /**
     * Evento para quando o usuario 'redimenciona' um evento
     * @param event 
     */
    public void quandoRedimensionado(ScheduleEntryResizeEvent event) {  
        
        for(AgendaDTO agda : listaAgenda){
            if(agda.getId().equals(Long.valueOf(event.getScheduleEvent().getData().toString()))){
                agenda = agda;
                try{
                	agendaBO.editarEntidade(agenda);
                	habilitaPainelDetalhe = false;
                	FacesUtils.addMessageAlteracaoSucesso();
                }catch(BusinessException b){
                	FacesUtils.addErrorMessage(b.getMessage());
                }
                break;
            }
        }
        
        
    }
	
	
    private void carregarListaClientes() {
    	clientes = new ArrayList<ClienteEntity>();
    	try{
    		List<ClienteDTO> dtos = clienteBO.buscarEntidadePorFiltro(null, null, null, null, getLoginBean().getUsuario().getId());
    		for (int i = 0; i < dtos.size(); i++) {
    			clientes.add(Adapter.dtoToEntity(dtos.get(i)));
    		}
    	}catch(BusinessException b){
    		
    	}
    }
	
    public Date getDataInicioCalendar(){
    	
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.DAY_OF_MONTH, 1);
    	
    	return c.getTime();
    }
    
	public List<TipoAgendaEntity> getTipoAgendaLista(){
		return tipoAgendaBO.listarTodos();
	}
	
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public List<AgendaDTO> getListaAgenda() {
		return listaAgenda;
	}

	public void setListaAgenda(List<AgendaDTO> listaAgenda) {
		this.listaAgenda = listaAgenda;
	}

	public AgendaDTO getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaDTO agenda) {
		this.agenda = agenda;
	}

	@Override
	public String iniciarTela() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limparCamposFiltro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String pesquisarFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isHabilitaPainelDetalhe() {
		return habilitaPainelDetalhe;
	}

	public void setHabilitaPainelDetalhe(boolean habilitaPainelDetalhe) {
		this.habilitaPainelDetalhe = habilitaPainelDetalhe;
	}

	public List<ClienteEntity> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteEntity> clientes) {
		this.clientes = clientes;
	}
	
	public boolean isTipoAgendamento(){
		if(agenda.getTipoAgenda() != null){
			if(agenda.getTipoAgenda().getDescTipoAgenda().equalsIgnoreCase(TipoAgendaEnum.AGENDA_ACOMPANHAMENTO.toString())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	private void adicionarAcompanhamento(){
	    Calendar dtInicio = new GregorianCalendar();  
	    Calendar dtPrimeiraFase = new GregorianCalendar();  
	    Calendar dtSegundaFase = new GregorianCalendar();  
	    Calendar dtTerceiraFase = new GregorianCalendar();  
	    
	    dtInicio.setTime(new Date()); 
	    dtPrimeiraFase.add(dtInicio.DAY_OF_MONTH, 3);  
	    dtSegundaFase.add(dtInicio.DAY_OF_MONTH, 14);  
	    dtTerceiraFase.add(dtInicio.MONTH, 2);  
		
		AgendaEntity agenda = new AgendaEntity();
		agenda.setUsuario(getLoginBean().getUsuario());
		agenda.setDataInicio(dtInicio.getTime()); 
		agenda.setDataFim(dtInicio.getTime()); 
		agenda.setDescricao("1� FA da cliente "+this.agenda.getCliente().getDadosPessoa().getNome()); 
		agenda.setDiaTodo(true); 
		agenda.setTipoAgenda(this.agenda.getTipoAgenda());
		
		AgendaEntity agenda2 = new AgendaEntity();
		agenda2.setUsuario(getLoginBean().getUsuario());
		agenda2.setDataInicio(dtSegundaFase.getTime()); 
		agenda2.setDataFim(dtSegundaFase.getTime()); 
		agenda2.setDescricao("2� FA da cliente "+this.agenda.getCliente().getDadosPessoa().getNome()); 
		agenda2.setTipoAgenda(this.agenda.getTipoAgenda());
		agenda2.setDiaTodo(true); 

		AgendaEntity agenda3 = new AgendaEntity();
		agenda3.setUsuario(getLoginBean().getUsuario());
		agenda3.setDataInicio(dtTerceiraFase.getTime()); 
		agenda3.setDataFim(dtTerceiraFase.getTime()); 
		agenda3.setDescricao("3� FA da cliente "+this.agenda.getCliente().getDadosPessoa().getNome()); 
		agenda3.setDiaTodo(true); 
		agenda3.setTipoAgenda(this.agenda.getTipoAgenda());
		
		agendaBO.adicionarEntidade(Adapter.entityToDto(agenda));
		agendaBO.adicionarEntidade(Adapter.entityToDto(agenda2));
		agendaBO.adicionarEntidade(Adapter.entityToDto(agenda3));
		
	}
	
	
}
