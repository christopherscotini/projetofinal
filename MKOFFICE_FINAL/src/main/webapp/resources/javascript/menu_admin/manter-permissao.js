msgsOcampo = 'O campo';
 msgObrigatorio = '� obrigat�rio';
 pulaLinha = '\n';

function validateForm(form){
	
	var txtPermissao = document.getElementById(form.name+":txtPermissao");
	
	var msg = '';
	
	if(txtPermissao.value == null || txtPermissao.value == ''){
		msg+=msgsOcampo+" PERMISS�O "+msgObrigatorio+pulaLinha;
	}
	
	if(msg != ''){
		alert(msg);
		return false;
	}else{
		return true;
	}
	


}