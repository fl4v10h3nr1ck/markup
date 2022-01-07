package classes.estoque.beans;

import classes.componentes.anotacoes.Anot_ParametrosDePesquisa;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;


@Anot_BD_Tabela(nome="subcategorias", prefixo="subcat")
public class Subcategoria {

	
@Anot_BD_Campo(nome = "id_subcategoria", tipo=int.class, set = "setId_subcategoria", get = "getId_subcategoria", ehId=true)			
private int id_subcategoria;

@Anot_ParametrosDePesquisa(pesquisaComoNumero = true, pesquisaComoString=true)
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=15)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)
@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")			
private String codigo;

@Anot_ParametrosDePesquisa(pesquisaComoNumero = true, pesquisaComoString=true)
@Anot_TB_Coluna_Selecao(posicao=1, comprimento=85)
@Anot_TB_Coluna(posicao=1, rotulo="Nome", comprimento = 85)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")			
private String descricao;

@Anot_BD_Campo(nome = "ativo", set = "setAtivo", get = "getAtivo")	
private String ativo;



public int getId_subcategoria() {	return id_subcategoria;}
public void setId_subcategoria(int id_subcategoria) {	this.id_subcategoria = id_subcategoria;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}

public String getAtivo() {	return ativo;}
public void setAtivo(String ativo) {	this.ativo = ativo;}
	





}

