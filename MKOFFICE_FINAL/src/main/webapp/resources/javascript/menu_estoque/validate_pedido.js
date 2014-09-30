function validaQtdeCarrinho(form){
	var qtde = getElementById(form.name+":txtQtdeSpinner");
	
	if(qtde.value == 0){
		alert('A QUANTIDADE deve ser MAIOR que ZERO.');
	}
}

function alertaRevisarPedido(form) {
	var cboDesconto = document.getElementById(form.name+":txtPercDesconto_input");
	var vlrFrete = document.getElementById(form.name+":txtValorFrete");
	var msgs = "";
	if(cboDesconto.value == 0){
		msgs+="O Seu pedido não contém descontos.\n";
	}
	if(vlrFrete.value == '0,00'){
		msgs+="O Seu pedido não contém valor de frete.\n";
	}
	if(msgs != ""){
		msgs+="Deseja continuar mesmo assim ?\n";
		return confirm(msgs);
	}else{
		return true;
	}
}

