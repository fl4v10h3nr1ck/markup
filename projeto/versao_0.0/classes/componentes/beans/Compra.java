package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import comuns.Calculo;
import comuns.Data;


@Anot_BD_Tabela(nome="compras", prefixo="comp")
public class Compra {

	
	
@Anot_BD_Campo(nome = "id_compra", tipo=int.class, set = "setId_compra", get = "getId_compra", ehId=true)	
private int id_compra;

@Anot_TB_Coluna(posicao=0, rotulo="Descrição", comprimento = 50)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")	
private String descricao;

@Anot_TB_Coluna(posicao=1, rotulo="Status", comprimento = 20)
@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus", getTab="getStatusTab")
private String status;

@Anot_TB_Coluna(posicao=2, rotulo="Valor (R$)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_total", set = "setValor_total", get = "getValor_total", getTab="getValor_totalTab")
private String valor_total;

@Anot_BD_Campo(nome = "quant_total", tipo=int.class, set = "setQuant_total", get = "getQuant_total")
private int quant_total;

@Anot_TB_Coluna(posicao=3, rotulo="Fechado em", comprimento = 15)
@Anot_BD_Campo(nome = "data_compra", tipo=Date.class, set = "setData_compra", get = "getData_compra", getTab="getData_compraTab")
private Date data_compra;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;





public int getId_compra() {	return id_compra;}
public void setId_compra(int id_compra) {	this.id_compra = id_compra;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

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





}
