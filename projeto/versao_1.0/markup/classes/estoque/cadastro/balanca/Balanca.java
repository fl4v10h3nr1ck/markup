package classes.estoque.cadastro.balanca;


import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="balanca", prefixo="bal")
public class Balanca {

@Anot_BD_Campo(nome = "id_balanca", tipo=int.class, set = "setId_balanca", get = "getId_balanca", ehId=true)			
private int id_balanca;

@Anot_BD_Campo(nome = "tipo_codigo_barra", set = "setTipo_codigo_barra", get = "getTipo_codigo_barra")	
private String tipo_codigo_barra;

@Anot_BD_Campo(nome = "tipo_valor_peso", set = "setTipo_valor_peso", get = "getTipo_valor_peso")	
private String tipo_valor_peso;

@Anot_BD_Campo(nome = "tipo_codigo_produto", set = "setTipo_codigo_produto", get = "getTipo_codigo_produto")	
private String tipo_codigo_produto;

@Anot_BD_Campo(nome = "indice_inicial_produto", tipo=int.class, set = "setIndice_inicial_produto", get = "getIndice_inicial_produto")			
private int indice_inicial_produto;

@Anot_BD_Campo(nome = "indice_final_produto", tipo=int.class, set = "setIndice_final_produto", get = "getIndice_final_produto")
private int indice_final_produto;

@Anot_BD_Campo(nome = "indice_inicial_valor", tipo=int.class, set = "setIndice_inicial_valor", get = "getIndice_inicial_valor")
private int indice_inicial_valor;

@Anot_BD_Campo(nome = "indice_final_valor", tipo=int.class, set = "setIndice_final_valor", get = "getIndice_final_valor")
private int indice_final_valor;




public int getId_balanca() {	return id_balanca;}
public void setId_balanca(int id_balanca) {	this.id_balanca = id_balanca;}

public String getTipo_codigo_barra() {	return tipo_codigo_barra;}
public void setTipo_codigo_barra(String tipo_codigo_barra) {	this.tipo_codigo_barra = tipo_codigo_barra;}

public String getTipo_valor_peso() {	return tipo_valor_peso;}
public void setTipo_valor_peso(String tipo_valor_peso) {	this.tipo_valor_peso = tipo_valor_peso;}

public String getTipo_codigo_produto() {	return tipo_codigo_produto;}
public void setTipo_codigo_produto(String tipo_codigo_produto) {	this.tipo_codigo_produto = tipo_codigo_produto;}

public int getIndice_inicial_produto() {	return indice_inicial_produto;}
public void setIndice_inicial_produto(int indice_inicial_produto) {	this.indice_inicial_produto = indice_inicial_produto;}

public int getIndice_final_produto() {	return indice_final_produto;}
public void setIndice_final_produto(int indice_final_produto) {	this.indice_final_produto = indice_final_produto;}

public int getIndice_inicial_valor() {	return indice_inicial_valor;}
public void setIndice_inicial_valor(int indice_inicial_valor) {	this.indice_inicial_valor = indice_inicial_valor;}

public int getIndice_final_valor() {	return indice_final_valor;}
public void setIndice_final_valor(int indice_final_valor) {	this.indice_final_valor = indice_final_valor;}
	
	
	
	
}
