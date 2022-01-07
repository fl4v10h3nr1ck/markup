package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;


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

@Anot_BD_Campo(nome = "fk_inventario", tipo=int.class, set = "setFk_inventario", get = "getFk_inventario")
private int fk_inventario;

@Anot_BD_Campo(nome = "fk_despesa", tipo=int.class, set = "setFk_despesa", get = "getFk_despesa")
private int fk_despesa;

@Anot_BD_Campo(nome = "preco", set = "setPreco", get = "getPreco")
private String preco;

@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade")
private int quantidade;

@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")
private String tipo;





public int getId_item_compra() {	return id_item_compra;}
public void setId_item_compra(int id_item_compra) {	this.id_item_compra = id_item_compra;}

public int getFk_compra() {	return fk_compra;}
public void setFk_compra(int fk_compra) {	this.fk_compra = fk_compra;}

public int getFk_fornecedor() {	return fk_fornecedor;}
public void setFk_fornecedor(int fk_fornecedor) {	this.fk_fornecedor = fk_fornecedor;}

public int getFk_produto() {	return fk_produto;}
public void setFk_produto(int fk_produto) {	this.fk_produto = fk_produto;}

public int getFk_inventario() {	return fk_inventario;}
public void setFk_inventario(int fk_inventario) {	this.fk_inventario = fk_inventario;}

public String getPreco() {	return preco;}
public void setPreco(String preco) {this.preco = preco;}

public int getQuantidade() {	return quantidade;}
public void setQuantidade(int quantidade) {	this.quantidade = quantidade;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}

public int getFk_despesa() {	return fk_despesa;}
public void setFk_despesa(int fk_despesa) {	this.fk_despesa = fk_despesa;}
	
	



}
