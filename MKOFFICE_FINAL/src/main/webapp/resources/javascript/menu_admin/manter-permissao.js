msgsOcampo = 'O campo';
 msgObrigatorio = 'é obrigatório';
 pulaLinha = '\n';

function validateForm(form){
	
	var txtPermissao = document.getElementById(form.name+":txtPermissao");
	
	var msg = '';
	
	if(txtPermissao.value == null || txtPermissao.value == ''){
		msg+=msgsOcampo+" PERMISSÃO "+msgObrigatorio+pulaLinha;
	}
	
	if(msg != ''){
		alert(msg);
		return false;
	}else{
		return true;
	}
	


}