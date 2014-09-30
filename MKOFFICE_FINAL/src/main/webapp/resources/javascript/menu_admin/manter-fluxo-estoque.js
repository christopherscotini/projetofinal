msgsOcampo = 'O campo';
 msgObrigatorio = 'é obrigatório';
 pulaLinha = '\n';

function validateForm(form){
	
	var txtFluxoEstoque = document.getElementById(form.name+":txtFluxoEstoque");
	
	var msg = '';
	
	if(txtFluxoEstoque.value == null || txtFluxoEstoque.value == ''){
		msg+=msgsOcampo+" FLUXO DE ESTOQUE "+msgObrigatorio+pulaLinha;
	}
	
	
	if(msg != ''){
		alert(msg);
		return false;
	}else{
		return true;
	}
	


}