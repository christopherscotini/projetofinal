msgsOcampo = 'O campo';
 msgObrigatorio = 'é obrigatório';
 pulaLinha = '\n';

function validateForm(form){
	
	var txtFormaPagto = document.getElementById(form.name+":txtFormaPagto");
	var chkPossuiParcela = document.getElementById(form.name+":chkPossuiParcela_input");
	var txtNumParcelas = document.getElementById(form.name+":txtNumParcelas_input");
	
	var msg = '';
	
	if(txtFormaPagto.value == null || txtFormaPagto.value == ''){
		msg+=msgsOcampo+" FORMA DE PAGAMENTO "+msgObrigatorio+pulaLinha;
	}
	
	if(chkPossuiParcela.checked == true){
		if(txtNumParcelas.value == null || txtNumParcelas.value == ''){
			msg+=msgsOcampo+" N° DE PARCELAS "+msgObrigatorio+pulaLinha;
		}
	}
	
	if(msg != ''){
		alert(msg);
		return false;
	}else{
		return true;
	}
	


}