package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="movimento_x_produto", prefixo="movXProd")
public class Movimento_X_Produto {

@Anot_BD_Campo(nome = "id_mov_x_prod", tipo=int.class, set = "setId_mov_x_prod", get = "getId_mov_x_prod", ehId=true)
private int id_mov_x_prod;

@Anot_BD_Campo(nome = "fk_movimento", tipo=int.class, set = "setFk_movimento", get = "getFk_movimento")
private int fk_movimento;

@Anot_BD_Campo(nome = "fk_produto", tipo=int.class, set = "setFk_produto", get = "getFk_produto")
private int fk_produto;

@Anot_BD_Campo(nome = "preco", set = "setPreco", get = "getPreco")
private String preco;

@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade")
private int quantidade;




public int getId_mov_x_prod() {	return id_mov_x_prod;}
public void setId_mov_x_prod(int id_mov_x_prod) {	this.id_mov_x_prod = id_mov_x_prod;}

public int getFk_movimento() {	return fk_movimento;}
public void setFk_movimento(int fk_movimento) {	this.fk_movimento = fk_movimento;}

public int getFk_produto() {	return fk_produto;}
public void setFk_produto(int fk_produto) {	this.fk_produto = fk_produto;}

public String getPreco() {	return preco;}
public void setPreco(String preco) {	this.preco = preco;}

public int getQuantidade() {	return quantidade;}
public void setQuantidade(int quantidade) {	this.quantidade = quantidade;}	
	




}
