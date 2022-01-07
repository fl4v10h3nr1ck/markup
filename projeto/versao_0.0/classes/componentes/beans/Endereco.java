package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;

@Anot_BD_Tabela(nome="enderecos", prefixo="end")
public class Endereco {

@Anot_BD_Campo(nome = "id_endereco", tipo=int.class, set = "setId_endereco", get = "getId_endereco", ehId=true)		
private int id_endereco;

@Anot_BD_Campo(nome = "logradouro", set = "setLogradouro", get = "getLogradouro")		
private String logradouro;

@Anot_BD_Campo(nome = "num", set = "setNum", get = "getNum")		
private String num;

@Anot_BD_Campo(nome = "bairro", set = "setBairro", get = "getBairro")		
private String bairro;

@Anot_BD_Campo(nome = "pais", set = "setPais", get = "getPais")		
private String pais;

@Anot_BD_Campo(nome = "cep", set = "setCep", get = "getCep")		
private String cep;

@Anot_BD_Campo(nome = "fk_municipio", tipo=int.class, set = "setFk_municipio", get = "getFk_municipio")		
private int fk_municipio;




public String getNum() {	return num;}
public void setNum(String num) {	this.num = num;}

public String getPais() {	return pais;}
public void setPais(String pais) {	this.pais = pais;}

public String getCep() {	return cep;}
public void setCep(String cep) {	this.cep = cep;}

public int getId_endereco() {	return id_endereco;}
public void setId_endereco(int id_endereco) {	this.id_endereco = id_endereco;}

public String getLogradouro() {	return logradouro;}
public void setLogradouro(String logradouro) {	this.logradouro = logradouro;}

public String getBairro() {	return bairro;}
public void setBairro(String bairro) {	this.bairro = bairro;}

public int getFk_municipio() {	return fk_municipio;}
public void setFk_municipio(int fk_municipio) {	this.fk_municipio = fk_municipio;}






}
