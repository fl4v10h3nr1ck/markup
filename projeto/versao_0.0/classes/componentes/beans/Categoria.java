package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;

@Anot_BD_Tabela(nome="categorias", prefixo="cat")
public class Categoria {

@Anot_BD_Campo(nome = "id_categoria", tipo=int.class, set = "setId_categoria", get = "getId_categoria", ehId=true)			
private int id_categoria;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=85)
@Anot_TB_Coluna(posicao=1, rotulo="Descrição", comprimento = 85)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")			
private String descricao;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=15)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)
@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")			
private String codigo;


public int getId_categoria() {	return id_categoria;}
public void setId_categoria(int id_categoria) {	this.id_categoria = id_categoria;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}
	





}
