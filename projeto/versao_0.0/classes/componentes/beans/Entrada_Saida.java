package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import comuns.Calculo;
import comuns.Data;

@Anot_BD_Tabela(nome="entrada_saida", prefixo="pag_rec")
public class Entrada_Saida {

@Anot_TB_Coluna(posicao=0, rotulo="Referente", comprimento = 25)
@Anot_BD_Campo(nome = "referente", set = "setReferente", get = "getReferente")
private String referente;	
	
	
@Anot_BD_Campo(nome = "id_entrada_saida", tipo=int.class, set = "setId_entrada_saida", get = "getId_entrada_saida", ehId=true)			
private int id_entrada_saida;

@Anot_BD_Campo(nome = "fk_credor", tipo=int.class, set = "setFk_credor", get = "getFk_credor")			
private int fk_credor;

@Anot_BD_Campo(nome = "fk_cliente", tipo=int.class, set = "setFk_cliente", get = "getFk_cliente")			
private int fk_cliente;

@Anot_BD_Campo(nome = "fk_compra", tipo=int.class, set = "setFk_compra", get = "getFk_compra")			
private int fk_compra;

@Anot_BD_Campo(nome = "fk_colaborador", tipo=int.class, set = "setFk_colaborador", get = "getFk_colaborador")
private int fk_colaborador;

@Anot_BD_Campo(nome = "fk_movimento", tipo=int.class, set = "setFk_movimento", get = "getFk_movimento")
private int fk_movimento;

@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")
private String tipo;

@Anot_TB_Coluna(posicao=1, rotulo="Tipo", comprimento = 15)
@Anot_BD_Campo(nome = "tipo_pagamento", set = "setTipo_pagamento", get = "getTipo_pagamento", getTab="getTipo_pagamentoTab")
private String tipo_pagamento;

@Anot_BD_Campo(nome = "valor_entrada", set = "setValor_entrada", get = "getValor_entrada")
private String valor_entrada;

@Anot_TB_Coluna(posicao=2, rotulo="Val. Total (R$)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_total", set = "setValor_total", get = "getValor_total", getTab="getValor_totalTab")
private String valor_total;

@Anot_BD_Campo(nome = "porcento_comissao", set = "setPorcento_comissao", get = "getPorcento_comissao")
private String porcento_comissao;

@Anot_BD_Campo(nome = "porcento_multa", set = "setPorcento_multa", get = "getPorcento_multa")
private String porcento_multa;

@Anot_BD_Campo(nome = "porcento_juros", set = "setPorcento_juros", get = "getPorcento_juros")
private String porcento_juros;

@Anot_TB_Coluna(posicao=3, rotulo="Num. Parcelas", comprimento = 15)
@Anot_BD_Campo(nome = "num_de_parcelas", tipo=int.class, set = "setNum_de_parcelas", get = "getNum_de_parcelas")
private int num_de_parcelas;

@Anot_TB_Coluna(posicao=4, rotulo="Status Pag.", comprimento = 15)
@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus", getTab="getStatusTab")
private String status;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_TB_Coluna(posicao=5, rotulo="Venci./Agend.", comprimento = 15)
@Anot_BD_Campo(nome = "primeiro_vencimento", tipo=Date.class, set = "setPrimeiro_vencimento", get = "getPrimeiro_vencimento", getTab="getPrimeiro_vencimentoTab")
private Date primeiro_vencimento;

@Anot_BD_Campo(nome = "fk_fornecedor", tipo=int.class, set = "setFk_fornecedor", get = "getFk_fornecedor")
private int fk_fornecedor;


public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getReferente() {return referente;}
public void setReferente(String referente) {	this.referente = referente;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}

public String getValor_entrada() {	return valor_entrada;}
public void setValor_entrada(String valor_entrada) {this.valor_entrada = valor_entrada;}

public int getNum_de_parcelas() {	return num_de_parcelas;}
public void setNum_de_parcelas(int num_de_parcelas) {	this.num_de_parcelas = num_de_parcelas;}

public String getValor_total() {return valor_total;}
public void setValor_total(String valor_total) {this.valor_total = valor_total;}
public String getValor_totalTab(){return Calculo.formataValor(this.valor_total);}

public String getPorcento_multa() {return porcento_multa;}
public void setPorcento_multa(String porcento_multa) {this.porcento_multa = porcento_multa;}

public String getPorcento_juros() {return porcento_juros;}
public void setPorcento_juros(String porcento_juros) {this.porcento_juros = porcento_juros;}

public String getStatus() {return status;}
public void setStatus(String status) {this.status = status;}
public String getStatusTab() { return "<html><font color="+(this.status.compareTo("ABERTO")==0?"red>":(this.status.compareTo("EM PAGAMENTO")==0?"green>":"blue>"))+this.status+"</font></html>";}

public int getFk_credor() {return fk_credor;}
public void setFk_credor(int fk_credor) {this.fk_credor = fk_credor;}

public int getFk_cliente() {return fk_cliente;}
public void setFk_cliente(int fk_cliente) {this.fk_cliente = fk_cliente;}

public int getFk_compra() {return fk_compra;}
public void setFk_compra(int fk_compra) {this.fk_compra = fk_compra;}

public String getTipo_pagamento() {return tipo_pagamento;}
public void setTipo_pagamento(String tipo_pagamento) {this.tipo_pagamento = tipo_pagamento;}
public String getTipo_pagamentoTab() { return "<html><font color="+(this.tipo_pagamento.compareTo("UNICO")==0?"green>":(this.tipo_pagamento.compareTo("PARCELADO")==0?"blue>":"gray>"))+this.tipo_pagamento+"</font></html>";}

public String getPorcento_comissao() {return porcento_comissao;}
public void setPorcento_comissao(String porcento_comissao) {this.porcento_comissao = porcento_comissao;}
	
public Date getPrimeiro_vencimento() {	return primeiro_vencimento;}
public void setPrimeiro_vencimento(Date primeiro_vencimento) {	this.primeiro_vencimento = primeiro_vencimento;}
public String getPrimeiro_vencimentoTab(){return this.primeiro_vencimento==null?"":Data.converteDataParaString(this.primeiro_vencimento);}

public int getId_entrada_saida() {	return id_entrada_saida;}
public void setId_entrada_saida(int id_entrada_saida) {	this.id_entrada_saida = id_entrada_saida;}

public int getFk_colaborador() {	return fk_colaborador;}
public void setFk_colaborador(int fk_colaborador) {	this.fk_colaborador = fk_colaborador;}

public int getFk_movimento() {	return fk_movimento;}
public void setFk_movimento(int fk_movimento) {	this.fk_movimento = fk_movimento;}

public int getFk_fornecedor() {	return fk_fornecedor;}
public void setFk_fornecedor(int fk_fornecedor) {	this.fk_fornecedor = fk_fornecedor;}



}
