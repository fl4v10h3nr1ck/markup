package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;
import comuns.Calculo;

@Anot_BD_Tabela(nome="despesas", prefixo="desp")
public class Despesa {

@Anot_BD_Campo(nome = "id_despesa", tipo=int.class, set = "setId_despesa", get = "getId_despesa", ehId=true)		
private int id_despesa;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=100)
@Anot_TB_Coluna(posicao=0, rotulo="Descrição", comprimento =75)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")
private String descricao;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;

@Anot_TB_Coluna(posicao=1, rotulo="Valor Base (R$)", comprimento =25)
@Anot_BD_Campo(nome = "valor_base", set = "setValor_base", get = "getValor_base", getTab="getValor_baseTab")
private String valor_base;


public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public int getId_despesa() {	return id_despesa;}
public void setId_despesa(int id_despesa) {	this.id_despesa = id_despesa;}

public String getValor_base() {	return valor_base;}
public void setValor_base(String valor_base) {	this.valor_base = valor_base;}	
public String getValor_baseTab(){return Calculo.formataValor(this.valor_base);}
	
	
}
