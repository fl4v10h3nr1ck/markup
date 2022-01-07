package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="movimentos", prefixo="mov")
public class Movimento {

	
@Anot_BD_Campo(nome = "id_movimento", tipo=int.class, set = "setId_movimento", get = "getId_movimento", ehId=true)			
private int id_movimento;

@Anot_BD_Campo(nome = "fk_cliente", tipo=int.class, set = "setFk_cliente", get = "getFk_cliente")			
private int fk_cliente;

@Anot_BD_Campo(nome = "fk_vendedor", tipo=int.class, set = "setFk_vendedor", get = "getFk_vendedor")			
private int fk_vendedor;

@Anot_BD_Campo(nome = "fk_convenio", tipo=int.class, set = "setFk_convenio", get = "getFk_convenio")			
private int fk_convenio;

@Anot_BD_Campo(nome = "fk_caixa", tipo=int.class, set = "setFk_caixa", get = "getFk_caixa")			
private int fk_caixa;

@Anot_BD_Campo(nome = "fk_recebimento_crediario", tipo=int.class, set = "setFk_recebimento_crediario", get = "getFk_recebimento_crediario")			
private int fk_recebimento_crediario;

@Anot_BD_Campo(nome = "data_abertura", tipo=Date.class, set = "setData_abertura", get = "getData_abertura")			
private Date data_abertura;

@Anot_BD_Campo(nome = "hora_abertura", tipo=int.class, set = "setHora_abertura", get = "getHora_abertura")			
private int hora_abertura;

@Anot_BD_Campo(nome = "min_abertura", tipo=int.class, set = "setMin_abertura", get = "getMin_abertura")			
private int min_abertura;

@Anot_BD_Campo(nome = "data_fechamento", tipo=Date.class, set = "setData_fechamento", get = "getData_fechamento")			
private Date data_fechamento;

@Anot_BD_Campo(nome = "hora_fechamento", tipo=int.class, set = "setHora_fechamento", get = "getHora_fechamento")			
private int hora_fechamento;

@Anot_BD_Campo(nome = "min_fechamento", tipo=int.class, set = "setMin_fechamento", get = "getMin_fechamento")			
private int min_fechamento;

@Anot_BD_Campo(nome = "valor_final", set = "setValor_final", get = "getValor_final")			
private String valor_final;

@Anot_BD_Campo(nome = "desconto", set = "setDesconto", get = "getDesconto")			
private String desconto;

@Anot_BD_Campo(nome = "status_pagamento", set = "setStatus_pagamento", get = "getStatus_pagamento")			
private String status_pagamento;

@Anot_BD_Campo(nome = "valor_bruto", set = "setValor_bruto", get = "getValor_bruto")			
private String valor_bruto;

@Anot_BD_Campo(nome = "motivo_cancelamento", set = "setMotivo_cancelamento", get = "getMotivo_cancelamento")			
private String motivo_cancelamento;

@Anot_BD_Campo(nome = "desconto_convenio", set = "setDesconto_convenio", get = "getDesconto_convenio")			
private String desconto_convenio;

@Anot_BD_Campo(nome = "valor_comissao", set = "setValor_comissao", get = "getValor_comissao")			
private String valor_comissao;

@Anot_BD_Campo(nome = "cpf_cliente", set = "setCpf_cliente", get = "getCpf_cliente")			
private String cpf_cliente;






public String getCpf_cliente() {	return cpf_cliente;}
public void setCpf_cliente(String cpf_cliente) {	this.cpf_cliente = cpf_cliente;}

public String getValor_comissao() {	return valor_comissao;}
public void setValor_comissao(String valor_comissao) {	this.valor_comissao = valor_comissao;}

public String getDesconto_convenio() {	return desconto_convenio;}
public void setDesconto_convenio(String desconto_convenio) {	this.desconto_convenio = desconto_convenio;}

public String getMotivo_cancelamento() {	return motivo_cancelamento;}
public void setMotivo_cancelamento(String motivo_cancelamento) {	this.motivo_cancelamento = motivo_cancelamento;}

public String getValor_bruto() {	return valor_bruto;}
public void setValor_bruto(String valor_bruto) {	this.valor_bruto = valor_bruto;}

public String getStatus_pagamento() {	return status_pagamento;}
public void setStatus_pagamento(String status_pagamento) {	this.status_pagamento = status_pagamento;}

public String getDesconto() {	return desconto;}
public void setDesconto(String desconto) {	this.desconto = desconto;}

public String getValor_final() {	return valor_final;}
public void setValor_final(String valor_final) {	this.valor_final = valor_final;}

public int getMin_fechamento() {	return min_fechamento;}
public void setMin_fechamento(int min_fechamento) {	this.min_fechamento = min_fechamento;}

public int getHora_fechamento() {	return hora_fechamento;}
public void setHora_fechamento(int hora_fechamento) {	this.hora_fechamento = hora_fechamento;}

public Date getData_fechamento() {	return data_fechamento;}
public void setData_fechamento(Date data_fechamento) {	this.data_fechamento = data_fechamento;}

public int getMin_abertura() {	return min_abertura;}
public void setMin_abertura(int min_abertura) {	this.min_abertura = min_abertura;}

public int getHora_abertura() {	return hora_abertura;}
public void setHora_abertura(int hora_abertura) {	this.hora_abertura = hora_abertura;}

public Date getData_abertura() {	return data_abertura;}
public void setData_abertura(Date data_abertura) {	this.data_abertura = data_abertura;}

public int getFk_caixa() {	return fk_caixa;}
public void setFk_caixa(int fk_caixa) {	this.fk_caixa = fk_caixa;}

public int getFk_convenio() {	return fk_convenio;}
public void setFk_convenio(int fk_convenio) {	this.fk_convenio = fk_convenio;}

public int getFk_vendedor() {	return fk_vendedor;}
public void setFk_vendedor(int fk_vendedor) {	this.fk_vendedor = fk_vendedor;}

public int getFk_cliente() {	return fk_cliente;}
public void setFk_cliente(int fk_cliente) {	this.fk_cliente = fk_cliente;}

public int getFk_recebimento_crediario() {	return fk_recebimento_crediario;}
public void setFk_recebimento_crediario(int fk_recebimento_crediario) {	this.fk_recebimento_crediario = fk_recebimento_crediario;}

public int getId_movimento() {	return id_movimento;}
public void setId_movimento(int id_movimento) {	this.id_movimento = id_movimento;}
	
	
	
	
	
	
}
