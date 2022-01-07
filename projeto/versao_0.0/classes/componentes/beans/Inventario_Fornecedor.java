package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;





@Anot_BD_Tabela(nome="inventario_x_fornecedor", prefixo="invXforn")
public class Inventario_Fornecedor {

@Anot_BD_Campo(nome = "id_fornecedor_inventario", tipo=int.class, set = "setId_fornecedor_inventario", get = "getId_fornecedor_inventario", ehId=true)	
private int id_fornecedor_inventario;

@Anot_BD_Campo(nome = "fk_inventario", tipo=int.class, set = "setFk_inventario", get = "getFk_inventario")	
private int fk_inventario;

@Anot_BD_Campo(nome = "fk_fornecedor", tipo=int.class, set = "setFk_fornecedor", get = "getFk_fornecedor")	
private int fk_fornecedor;

@Anot_BD_Campo(nome = "valor", set = "setValor", get = "getValor")	
private String valor;





public int getId_fornecedor_inventario() {	return id_fornecedor_inventario;}
public void setId_fornecedor_inventario(int id_fornecedor_inventario) {	this.id_fornecedor_inventario = id_fornecedor_inventario;}

public int getFk_inventario() {	return fk_inventario;}
public void setFk_inventario(int fk_inventario) {	this.fk_inventario = fk_inventario;}

public int getFk_fornecedor() {	return fk_fornecedor;}
public void setFk_fornecedor(int fk_fornecedor) {	this.fk_fornecedor = fk_fornecedor;}

public String getValor() {	return valor;}
public void setValor(String valor) {	this.valor = valor;}
	
	



}
