package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;
import comuns.Comuns;


@Anot_BD_Tabela(nome="inventario", prefixo="inv")
public class Inventario {

@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)
@Anot_BD_Campo(nome = "id_inventario", tipo=int.class, set = "setId_inventario", get = "getId_inventario", ehId=true)		
private int id_inventario;	

@Anot_BD_Campo(nome = "cod_ean", set = "setCod_ean", get = "getCod_ean")	
private String cod_ean;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=75)
@Anot_TB_Coluna(posicao=1, rotulo="Nome", comprimento = 50)
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")
private String nome;

@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")
private String descricao;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=25)
@Anot_TB_Coluna(posicao=3, rotulo="QTDe Disponível", comprimento = 20)
@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade", getTab = "getQuantidadeTab")
private int quantidade;

@Anot_TB_Coluna(posicao=2, rotulo="Preço (R$)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_custo", set = "setValor_custo", get = "getValor_custo")
private String valor_custo;

@Anot_BD_Campo(nome = "quant_min_inventario", tipo=int.class, set = "setQuant_min_inventario", get = "getQuant_min_inventario")
private int quant_min_inventario;




public int getId_inventario() {	return id_inventario;}
public void setId_inventario(int id_inventario) {	this.id_inventario = id_inventario;}

public String getCod_ean() {	return cod_ean;}
public void setCod_ean(String cod_ean) {	this.cod_ean = cod_ean;}

public String getNome() {	return nome;}
public void setNome(String nome) {	this.nome = nome;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public int getQuantidade() {	return quantidade;}
public void setQuantidade(int quantidade) {	this.quantidade = quantidade;}
public String getQuantidadeTab() { return "<html><font color="+(this.quantidade<= Comuns.quantMinPadraoInventario?"red>":"green>")+this.quantidade+"</font></html>";}


public String getValor_custo() {	return valor_custo;}
public void setValor_custo(String valor_custo) {	this.valor_custo = valor_custo;}

public int getQuant_min_inventario() {	return quant_min_inventario;}
public void setQuant_min_inventario(int quant_min_inventario) {	this.quant_min_inventario = quant_min_inventario;}






}
