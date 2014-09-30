function validarValorDigitado(objName, vMin, vMax) {

	var lstLetters = objName.value;
	var lstReplace = lstLetters.replace(/\,/g, '.');
	objName.value = lstReplace;

	if (objName.value < vMin) {
		objName.value = 1;
		objName.value.focus();
		alert('A Quantidade digitada é menor do que a permitida. Digite um valor maior que zero.');
	}
	if (objName.value > vMin) {
		objName.focus();
		objName.value = 1;
		alert('A Quantidade digitada é maior do que a permitida. Digite um valor menor ou igual a '
				+ vMax + '.');
	}

}

function validarConcluirVenda(form) {
	var formaPgto = document.getElementById(form.name + ":cboFormaPgto_input");

	if (formaPgto.value == 'CREDITO') {
		var parcela = document.getElementById(form.name
				+ ":listPArcelamento_input");
		if (parcela.value == '') {
			alert('Selecione um valor de parcelamento.');
			return false;
		}
	}
	
	
	return confirm('Deseja realmente efetuar essa venda?');
}

function validarPesquisaHistoricoVendas(form) {
	var dtInicialIsEmpty = isEmpty(document.getElementById(form.name+':calendarDataInicial_input').value);
	var dtFinalIsEmpty = isEmpty(document.getElementById(form.name+':calendarDataFinal_input').value);
	
	if (dtInicialIsEmpty && dtFinalIsEmpty) {
		alert("Preencha a Data Inicial e a Data Final.");
		return false;
	}else{
		return compara_datas(form);
	}
}


function validaDataPagamento(form){
	return validaDataDigitada(document.getElementById(form.name+':calendarDataPagamento_input').value);
}

