package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;

@Anot_BD_Tabela(nome="formas_de_pagamento", prefixo="f_pag")
public class FormaDePagamento {

@Anot_BD_Campo(nome = "id_forma_pag", tipo=int.class, set = "setId_forma_pag", get = "getId_forma_pag", ehId=true)			
private int id_forma_pag;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=25)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 20)
@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")			
private String codigo;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=75)
@Anot_TB_Coluna(posicao=1, rotulo="Descrição", comprimento = 45)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")			
private String descricao;

@Anot_TB_Coluna(posicao=2, rotulo="Padrão", comprimento = 15)
@Anot_BD_Campo(nome = "padrao", set = "setPadrao", get = "getPadrao", getTab="getPadraoTab")			
private String padrao;

@Anot_TB_Coluna(posicao=3, rotulo="Tipo", comprimento = 20)
@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")			
private String tipo;


@Anot_BD_Campo(nome = "tipo_parcela", set = "setTipo_parcela", get = "getTipo_parcela")			
private String tipo_parcela;

@Anot_BD_Campo(nome = "num_max_parcelas", tipo=int.class, set = "setNum_max_parcelas", get = "getNum_max_parcelas")			
private int num_max_parcelas;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")			
private String status;




public int getId_forma_pag() {	return id_forma_pag;}
public void setId_forma_pag(int id_forma_pag) {	this.id_forma_pag = id_forma_pag;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public String getPadrao() {	return padrao;}
public void setPadrao(String padrao) {	this.padrao = padrao;}
public String getPadraoTab() { return "<html><font color="+(this.padrao.compareTo("SIM")==0?"green>":"blue>")+this.padrao+"</font></html>";}

public String getTipo_parcela() {	return tipo_parcela;}
public void setTipo_parcela(String tipo_parcela) {	this.tipo_parcela = tipo_parcela;}

public int getNum_max_parcelas() {	return num_max_parcelas;}
public void setNum_max_parcelas(int num_max_parcelas) {	this.num_max_parcelas = num_max_parcelas;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}	
	
	




}
