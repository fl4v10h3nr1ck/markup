package classes.principal.config;


import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="configuracoes", prefixo="config")
public class Config_do_Sistema {


@Anot_BD_Campo(nome = "id_configuracoes", tipo=int.class, set = "setId_configuracoes", get = "getId_configuracoes", ehId=true)			
private int id_configuracoes;

@Anot_BD_Campo(nome = "cidade_padrao", set = "setCidade_padrao", get = "getCidade_padrao")
private String cidade_padrao;

@Anot_BD_Campo(nome = "pais_padrao", set = "setPais_padrao", get = "getPais_padrao")
private String pais_padrao;

@Anot_BD_Campo(nome = "itens_por_pagina", tipo=int.class, set = "setItens_por_pagina", get = "getItens_por_pagina")
private int itens_por_pagina;

@Anot_BD_Campo(nome = "quant_min_estoque_padrao", tipo=int.class, set = "setQuant_min_estoque_padrao", get = "getQuant_min_estoque_padrao")
private int quant_min_estoque_padrao;

@Anot_BD_Campo(nome = "lucro_padrao", set = "setLucro_padrao", get = "getLucro_padrao")
private int lucro_padrao;

@Anot_BD_Campo(nome = "path_imgs_produtos", set = "setPath_imgs_produtos", get = "getPath_imgs_produtos")
private String path_imgs_produtos;




public int getId_configuracoes() {	return id_configuracoes;}
public void setId_configuracoes(int id_configuracoes) {	this.id_configuracoes = id_configuracoes;}

public String getCidade_padrao() {	return cidade_padrao;}
public void setCidade_padrao(String cidade_padrao) {	this.cidade_padrao = cidade_padrao;}

public String getPais_padrao() {	return pais_padrao;}
public void setPais_padrao(String pais_padrao) {	this.pais_padrao = pais_padrao;}

public int getItens_por_pagina() {	return itens_por_pagina;}
public void setItens_por_pagina(int itens_por_pagina) {	this.itens_por_pagina = itens_por_pagina;}

public int getQuant_min_estoque_padrao() {	return quant_min_estoque_padrao;}
public void setQuant_min_estoque_padrao(int quant_min_estoque_padrao) {	this.quant_min_estoque_padrao = quant_min_estoque_padrao;}

public int getLucro_padrao() {	return lucro_padrao;}
public void setLucro_padrao(int lucro_padrao) {	this.lucro_padrao = lucro_padrao;}

public String getPath_imgs_produtos() {	return path_imgs_produtos;}
public void setPath_imgs_produtos(String path_imgs_produtos) {	this.path_imgs_produtos = path_imgs_produtos;}


	
	
	
}
