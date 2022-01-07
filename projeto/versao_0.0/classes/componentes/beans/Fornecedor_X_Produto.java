package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="fornecedor_x_produto", prefixo="fornXprod")
public class Fornecedor_X_Produto {

	
@Anot_BD_Campo(nome = "id_fornecedor_x_prod", tipo=int.class, set = "setId_fornecedor_x_prod", get = "getId_fornecedor_x_prod", ehId=true)	
private int id_fornecedor_x_prod;

@Anot_BD_Campo(nome = "fk_fornecedor", tipo=int.class, set = "setFk_fornecedor", get = "getFk_fornecedor")	
private int fk_fornecedor;

@Anot_BD_Campo(nome = "fk_produto", tipo=int.class, set = "setFk_produto", get = "getFk_produto")	
private int fk_produto;

@Anot_BD_Campo(nome = "preco", set = "setPreco", get = "getPreco")	
private String preco;




public int getId_fornecedor_x_prod() {	return id_fornecedor_x_prod;}
public void setId_fornecedor_x_prod(int id_fornecedor_x_prod) {	this.id_fornecedor_x_prod = id_fornecedor_x_prod;}

public int getFk_fornecedor() {	return fk_fornecedor;}
public void setFk_fornecedor(int fk_fornecedor) {	this.fk_fornecedor = fk_fornecedor;}

public int getFk_produto() {	return fk_produto;}
public void setFk_produto(int fk_produto) {	this.fk_produto = fk_produto;}

public String getPreco() {	return preco;}
public void setPreco(String preco) {	this.preco = preco;}
	
	






}
