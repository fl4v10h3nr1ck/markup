package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;




@Anot_BD_Tabela(nome="grupos_fornecedores", prefixo="g_forn")
public class Grupo_fornecedor {

	
@Anot_BD_Campo(nome = "id_grupo_fornecedor", tipo=int.class, set = "setId_grupo_fornecedor", get = "getId_grupo_fornecedor", ehId=true)		
private int id_grupo_fornecedor;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=25)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 25)
@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")		
private String codigo;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=75)
@Anot_TB_Coluna(posicao=1, rotulo="Descrição", comprimento = 75)
@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")		
private String descricao;






public int getId_grupo_fornecedor() {	return id_grupo_fornecedor;}
public void setId_grupo_fornecedor(int id_grupo_fornecedor) {	this.id_grupo_fornecedor = id_grupo_fornecedor;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}






}
