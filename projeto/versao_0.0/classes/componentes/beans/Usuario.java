package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;

@Anot_BD_Tabela(nome="usuarios", prefixo="user")
public class Usuario {

	
@Anot_BD_Campo(nome = "id", tipo=int.class, set = "setId", get = "getId", ehId=true)	
private int id;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=100)
@Anot_TB_Coluna(posicao=0, rotulo="Descrição", comprimento =100)
@Anot_BD_Campo(nome = "usuario", set = "setUsuario", get = "getUsuario")	
private String usuario;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")	
private String status;



public int getId() {	return id;}
public void setId(int id) {	this.id = id;}

public String getUsuario() {	return usuario;}
public void setUsuario(String usuario) {	this.usuario = usuario;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}
	



}
