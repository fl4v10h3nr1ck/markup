package classes.compras.beans;

import java.util.Date;

import classes.comuns.Calculo;
import classes.comuns.Data;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;



@Anot_BD_Tabela(nome="compras", prefixo="comp")
public class Compra {

	
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 12)
@Anot_BD_Campo(nome = "id_compra", tipo=int.class, set = "setId_compra", get = "getId_compra", ehId=true)	
private int id_compra;

@Anot_TB_Coluna(posicao=1, rotulo="Status", comprimento = 13)
@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus", getTab="getStatusTab")
private String status;

@Anot_TB_Coluna(posicao=2, rotulo="Valor (R$)", comprimento = 13)
@Anot_BD_Campo(nome = "valor_total", set = "setValor_total", get = "getValor_total", getTab="getValor_totalTab")
private String valor_total;

@Anot_TB_Coluna(posicao=3, rotulo="QTDe Total", comprimento = 13)
@Anot_BD_Campo(nome = "quant_total", tipo=int.class, set = "setQuant_total", get = "getQuant_total")
private int quant_total;

@Anot_TB_Coluna(posicao=4, rotulo="Cadastrado em", comprimento = 12)
@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro", getTab="getData_cadastroTab")
private Date data_cadastro;

@Anot_TB_Coluna(posicao=5, rotulo="Fechado em", comprimento = 12)
@Anot_BD_Campo(nome = "data_compra", tipo=Date.class, set = "setData_compra", get = "getData_compra", getTab="getData_compraTab")
private Date data_compra;

@Anot_TB_Coluna(posicao=6, rotulo="St. Entrega", comprimento = 13)
@Anot_BD_Campo(nome = "status_entrega", set = "setStatus_entrega", get = "getStatus_entrega")
private String status_entrega;	

@Anot_TB_Coluna(posicao=7, rotulo="Data Entrega", comprimento = 12)
@Anot_BD_Campo(nome = "data_entrega", tipo=Date.class, set = "setData_entrega", get = "getData_entrega", getTab="getData_entregaTab")
private Date data_entrega;

@Anot_BD_Campo(nome = "fk_pagamento", tipo=int.class, set = "setFk_pagamento", get = "getFk_pagamento")	
private int fk_pagamento;





public int getId_compra() {	return id_compra;}
public void setId_compra(int id_compra) {	this.id_compra = id_compra;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}
public String getStatusTab() { return "<html><font color="+(this.status.compareTo("ABERTO")==0?"red>":(this.status.compareTo("CANCELADO")==0?"gray>":"green>"))+this.status+"</font></html>";}


public String getValor_total() {	return valor_total;}
public void setValor_total(String valor_total) {	this.valor_total = valor_total;}
public String getValor_totalTab(){return Calculo.formataValor(this.valor_total);}

public int getQuant_total() {	return quant_total;}
public void setQuant_total(int quant_total) {	this.quant_total = quant_total;}

public Date getData_compra() {	return data_compra;}
public void setData_compra(Date data_compra) {	this.data_compra = data_compra;}
public String getData_compraTab(){return this.data_compra==null?"":Data.converteDataParaString(this.data_compra);}


public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}
public String getData_cadastroTab(){return this.data_cadastro==null?"":Data.converteDataParaString(this.data_cadastro);}


public String getStatus_entrega() {	return status_entrega;}
public void setStatus_entrega(String status_entrega) {	this.status_entrega = status_entrega;}

public Date getData_entrega() {	return data_entrega;}
public void setData_entrega(Date data_entrega) {	this.data_entrega = data_entrega;}
public String getData_entregaTab(){return this.data_entrega==null?"":Data.converteDataParaString(this.data_entrega);}

public int getFk_pagamento() {	return fk_pagamento;}
public void setFk_pagamento(int fk_pagamento) {	this.fk_pagamento = fk_pagamento;}





}
