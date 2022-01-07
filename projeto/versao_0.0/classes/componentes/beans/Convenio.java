package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;
import comuns.Calculo;
import comuns.Data;




@Anot_BD_Tabela(nome="convenios", prefixo="conv")
public class Convenio {

@Anot_BD_Campo(nome = "id_convenio", tipo=int.class, set = "setId_convenio", get = "getId_convenio", ehId=true)
private int id_convenio;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=25)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)
@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")
private String codigo;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=75)
@Anot_TB_Coluna(posicao=1, rotulo="Descrição", comprimento = 25)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")
private String descricao;

@Anot_TB_Coluna(posicao=2, rotulo="Tipo", comprimento = 15)
@Anot_BD_Campo(nome = "tipo_desconto", set = "setTipo_desconto", get = "getTipo_desconto", getTab="getTipoTab")
private String tipo_desconto;

@Anot_TB_Coluna(posicao=3, rotulo="Valor (R$/%)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_desconto", set = "setValor_desconto", get = "getValor_desconto", getTab = "getValor_descontoTab")
private String valor_desconto;

@Anot_TB_Coluna(posicao=4, rotulo="Início", comprimento = 15)
@Anot_BD_Campo(nome = "inicio", tipo=Date.class, set = "setInicio", get = "getInicio", getTab = "getInicioTab")
private Date inicio;

@Anot_TB_Coluna(posicao=5, rotulo="Fim", comprimento = 15)
@Anot_BD_Campo(nome = "fim", tipo=Date.class, set = "setFim", get = "getFim", getTab = "getFimTab")
private Date fim;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;



public int getId_convenio() {	return id_convenio;}
public void setId_convenio(int id_convenio) {	this.id_convenio = id_convenio;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public String getValor_desconto() {	return valor_desconto;}
public void setValor_desconto(String valor_desconto) {	this.valor_desconto = valor_desconto;}
public String getValor_descontoTab(){return Calculo.formataValor(this.valor_desconto);}

public String getTipo_desconto() {	return tipo_desconto;}
public void setTipo_desconto(String tipo_desconto) {	this.tipo_desconto = tipo_desconto;}
public String getTipoTab() { return "<html><font color="+(this.tipo_desconto.compareTo("VALOR")==0?"green>":"blue>")+this.tipo_desconto+"</font></html>";}

public Date getInicio() {	return inicio;}
public void setInicio(Date data_inicio) {	this.inicio = data_inicio;}
public String getInicioTab(){return this.inicio==null?"":Data.converteDataParaString(this.inicio);}

public Date getFim() {	return fim;}
public void setFim(Date data_fim) {	this.fim = data_fim;}
public String getFimTab(){return this.fim==null?"":Data.converteDataParaString(this.fim);}


public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}




	
	
}
