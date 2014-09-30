 msgsOcampo = 'O campo';
 msgObrigatorio = 'é obrigatório';
 pulaLinha = '\n';

function validateForm(form){
	
	var txtNome = document.getElementById(form.name+":txtNome");
	var cboSexo = document.getElementById(form.name+":cboSexo_input");
	var txtTelefone = document.getElementById(form.name+":txtTelefone");
	var txtCelular = document.getElementById(form.name+":txtTelefoneCel");
	var dataPrimeiraVenda = document.getElementById(form.name+":calendarDtFirstSale_input");
	
	var msg = '';
	
	if(txtNome.value == null || txtNome.value == ''){
		msg+=msgsOcampo+" NOME "+msgObrigatorio+pulaLinha;
	}
	
	if(cboSexo.value == null || cboSexo.value == ''){
		msg+=msgsOcampo+" SEXO "+msgObrigatorio+pulaLinha;
	}
	
	if(txtTelefone.value == null || txtTelefone.value == ''){
		msg+=msgsOcampo+" TELEFONE "+msgObrigatorio+pulaLinha;
	}
	
	if(txtCelular.value == null || txtCelular.value == ''){
		msg+=msgsOcampo+" CELULAR "+msgObrigatorio+pulaLinha;
	}

	if(dataPrimeiraVenda.value == null || dataPrimeiraVenda.value == ''){
		msg+=msgsOcampo+" DATA PRIMEIRA VENDA "+msgObrigatorio+pulaLinha;
	}

	if(msg != ''){
		alert(msg);
		return false;
	}else{
		return true;
	}

}