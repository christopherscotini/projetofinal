msgsOcampo = 'O campo';
 msgObrigatorio = 'é obrigatório';
 pulaLinha = '\n';

function validateForm(form){
	
	var txtUsuario = document.getElementById(form.name+":txtUsuario");
	var txtSenha = document.getElementById(form.name+":txtPassword");
	var txtSenhaConfirm = document.getElementById(form.name+":txtPasswordConf");
	
	var msg = '';
	
	if(txtUsuario.value == null || txtUsuario.value == ''){
		msg+=msgsOcampo+" USUARIO "+msgObrigatorio+pulaLinha;
	}
	
	if(txtSenha.value == null || txtSenha.value == ''){
		msg+=msgsOcampo+" SENHA "+msgObrigatorio+pulaLinha;
	}
	
	if(txtSenhaConfirm.value == null || txtSenhaConfirm.value == ''){
		msg+=msgsOcampo+" CONFIRMAR SENHA "+msgObrigatorio+pulaLinha;
	}
	
	if(msg == ''){
		if(txtSenha.value != txtSenhaConfirm.value){
			msg+="As senhas não conferem.";
			
		}
	}
	
	if(msg != ''){
		alert(msg);
		return false;
	}else{
		return true;
	}
	


}