package classes.compras.beans;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="itens_de_compra", prefixo="it_comp")
public class ItemDeCompra {

@Anot_BD_Campo(nome = "id_item_compra", tipo=int.class, set = "setId_item_compra", get = "getId_item_compra", ehId=true)	
private int id_item_compra;

@Anot_BD_Campo(nome = "fk_compra", tipo=int.class, set = "setFk_compra", get = "getFk_compra")
private int fk_compra;

@Anot_BD_Campo(nome = "fk_fornecedor", tipo=int.class, set = "setFk_fornecedor", get = "getFk_fornecedor")
private int fk_fornecedor;

@Anot_BD_Campo(nome = "fk_produto", tipo=int.class, set = "setFk_produto", get = "getFk_produto")
private int fk_produto;

@Anot_BD_Campo(nome = "preco", set = "setPreco", get = "getPreco")
private String preco;

@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade")
private int quantidade;






public int getId_item_compra() {	return id_item_compra;}
public void setId_item_compra(int id_item_compra) {	this.id_item_compra = id_item_compra;}

public int getFk_compra() {	return fk_compra;}
public void setFk_compra(int fk_compra) {	this.fk_compra = fk_compra;}

public int getFk_fornecedor() {	return fk_fornecedor;}
public void setFk_fornecedor(int fk_fornecedor) {	this.fk_fornecedor = fk_fornecedor;}

public int getFk_produto() {	return fk_produto;}
public void setFk_produto(int fk_produto) {	this.fk_produto = fk_produto;}

public String getPreco() {	return preco;}
public void setPreco(String preco) {this.preco = preco;}

public int getQuantidade() {	return quantidade;}
public void setQuantidade(int quantidade) {	this.quantidade = quantidade;}
	



}
