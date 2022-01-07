package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;

@Anot_BD_Tabela(nome="municipios", prefixo="muni")
public class Municipio{

@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 20)
@Anot_BD_Campo(nome = "codigo", tipo=int.class, set = "setCodigo", get = "getCodigo", ehId=true)		
private int codigo;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=80)
@Anot_TB_Coluna(posicao=1, rotulo="Município", comprimento = 65)
@Anot_BD_Campo(nome = "municipio", set = "setMunicipio", get = "getMunicipio")
private String municipio;
	
@Anot_TB_Coluna_Selecao(posicao=1, comprimento=20)
@Anot_TB_Coluna(posicao=2, rotulo="UF", comprimento = 15)
@Anot_BD_Campo(nome = "uf", set = "setUf", get = "getUf")
private String uf;



public int getCodigo() {	return codigo;}
public void setCodigo(int codigo) {	this.codigo = codigo;}

public String getMunicipio() {	return municipio;}
public void setMunicipio(String municipio) {	this.municipio = municipio;}

public String getUf() {	return uf;}
public void setUf(String uf) {	this.uf = uf;}
	
	






	
}
