function loadMasksJQuery() {
	jQuery.mask.masks = jQuery.extend(jQuery.mask.masks,{
		decimalBr8:{ mask : '99,999.999', type : 'reverse' }
	});

	(function(jQuery){jQuery(function(){jQuery('input:text').setMask();});})(jQuery);
	
	$(".rf-cal-inp").setMask('39/19/9999');
}

//function onlyNum() {
//	if (document.all) {// Internet Explorer
//		var tecla = event.keyCode;
//	} else if(document.layers) {// Nestcape
//		var tecla = event.which;
//	}
//	if (tecla > 47 && tecla < 58) {// numeros de 0 a 9
//		return true;
//	}	else {
//		event.keyCode = 0;
//		return false;
//	}
//}

function onlyNum(event) {
    if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {
        return false;
    }
}

function cleanClipboard() {
	clipboardData.clearData();
}

//trim completo
function trim(str) {
	return $.trim(str);
}

function isEmpty(entrada) {
 	return (entrada == null || trim(entrada) == '');
}

function verificarRadioListaSelecionado(rdoName) {
	var objRadio = document.getElementsByName(rdoName);

	if (objRadio != null) {
		for (var i = 0; i < objRadio.length; i++) {
			if (objRadio[i].checked) {
				return true;
			}
		}

		alert('Selecione um registro da lista.');
		return false;
	}

	return true;
}

function verificarRadioListaSelecionadoExcluir(rdoName) {
	var objRadio = document.getElementsByName(rdoName);
	if (objRadio != null) {
		for (var i = 0; i < objRadio.length; i++) {
			if (objRadio[i].checked) {
				confirm('Confirma Exclusão?');
				return true;
			}
		}

		alert('Selecione um registro da lista.');
		return false;
	}

	return confirm('Confirma Exclusão?');
}

function valida_cnpj(campo){
	var cnpj = campo.value;
	
	if(cnpj != ""){
		//Limpa pontos e Traços da string
		cnpj = cnpj.replace(/\./g, "");
		cnpj = cnpj.replace(/\-/g, "");
		cnpj = cnpj.replace(/\_/g, "");
		cnpj = cnpj.replace(/\//g, "");
		 
		if(cnpj.length!=14){ var result = false; };
		 
		pri = eval(cnpj.substring(0,2));
		seg = eval(cnpj.substring(3,6));
		ter = eval(cnpj.substring(7,10));
		qua = eval(cnpj.substring(11,15));
		qui = eval(cnpj.substring(16,18));
		 
		var i;
		var numero;
		var situacao = '';
		 
		numero = (pri+seg+ter+qua+qui);
		 
		s = numero;
		 
		c = cnpj.substr(0,12);
		var dv = cnpj.substr(12,2);
		var d1 = 0;
		 
		for (i = 0; i < 12; i++){
			d1 += c.charAt(11-i)*(2+(i % 8));
		}
		 
		if (d1 == 0){
			var result = false;
		}
		d1 = 11 - (d1 % 11);
		 
		if (d1 > 9) d1 = 0;
		 
		if (dv.charAt(0) != d1){
			var result = false;
		}
		 
		d1 *= 2;
		for (i = 0; i < 12; i++){
			d1 += c.charAt(11-i)*(2+((i+1) % 8));
		}
		 
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		 
		if (dv.charAt(1) != d1){
			var result = false;
		}
		 
		if (result == false) {
			alert("CNPJ inválido!");
			campo.focus();
		}
	}
}

//Função para mascarar campo de telefone quando for > 8 adicona o (11)9XXXX-XXXX

function mask9Phone(form){
	var elemento = document.getElementById(form.name + ":txtCelular");
	var telefone = elemento.value;
	telefone = telefone.replace("(", "");
	telefone = telefone.replace(")", "");
	telefone = telefone.replace("-", "");
	telefone = telefone.replace(" ", "");
	
	if (telefone.length >= 10 ) {
		$(elemento).setMask("(99) 99999-9999?9");  
	  } else {  
		  $(elemento).setMask("(99) 9999-9999?9");
		  elemento.focus();
	  }
}


//Validar data
function validaData(campo) {
	if (campo.value != "") {
		var barras = campo.value.split("/");
		
		if (barras.length == 3) {
			var dia = barras[0];
			var mes = barras[1];
			var ano = barras[2];
			var resultado = (!isNaN(dia) && (dia > 0) && (dia < 32))
					&& (!isNaN(mes) && (mes > 0) && (mes < 13))
					&& (!isNaN(ano) && (ano.length == 4) && (ano >= 1900));
			if (!resultado) {
				alert("Data inválida.");
				campo.focus();
				return false;
			}
		} else {
			alert("Data inválida.");
			campo.focus();
			return false;
		}
		return true;
	}
}

function compara_datas(form){   
    //Verifica se a data inicial é maior que a data final   
    
	var controle = 0;
    var data_inicial = document.getElementById(form.name +":calendarDataInicial_input").value;
    var data_final = document.getElementById(form.name +":calendarDataFinal_input").value;
    
    
    var erroData = '';

    if(data_inicial == ''){
    	erroData += 'Preencha a Data Inicial\n';
    }else{
    	if(!validaDataDigitada(data_inicial)){
    		erroData += 'Data Inicial Invalida \n';
    	}
    }
    
    if(data_final == ''){
    	erroData += 'Preencha a Data Final\n';
    }else{
    	if(!validaDataDigitada(data_final)){
        	erroData += 'Data Final Invalida';
        }
    }
    
    if(erroData != ''){
    	alert(erroData);
    	return false;
    }
    
    var dia_inicial = data_inicial.substr(0,2);   
    var dia_final = data_final.substr(0,2);   
    var mes_inicial = data_inicial.substr(3,2);   
    var mes_final = data_final.substr(3,2);   
    var ano_inicial = data_inicial.substr(6,4);   
    var ano_final = data_final.substr(6,4);
    
    if(ano_inicial > ano_final){   
        controle = controle + 1;
    }
    else{   
    	if(ano_inicial == ano_final){   
    		if(mes_inicial > mes_final){   
    			controle = controle + 1;
            }
            else{   
                if(mes_inicial == mes_final){   
                    if(dia_inicial > dia_final){   
                        controle = controle + 1;
                    }
                }
            }   
        }
    }

	if(controle > 0){
		alert('Data Final é maior que a Data Inicial');
		return false;
	}
	else{
		
		return true;
	}
}

//valida data
function validaDataDigitada(data){
    var exp = /\d{2}\/\d{2}\/\d{4}/;
    if(!exp.test(data)){
  	  return false;
    }else{
  	  
  	  return true;
    }
}

function bloquearDigitacaoCampoQtdeProduto(event){
	
	if((event.which >= 48) && (event.which <= 57)){ //bloqueia letras
		return false;
	}
	
    if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57)) {//bloqueia numeros
        return false;
    }
	
}

