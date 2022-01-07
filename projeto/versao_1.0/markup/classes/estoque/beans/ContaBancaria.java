package classes.estoque.beans;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="contas_bancarias", prefixo="cont_b")
public class ContaBancaria {

@Anot_BD_Campo(nome = "id_conta", tipo=int.class, set = "setId_conta", get = "getId_conta", ehId=true)		
private int id_conta;

@Anot_BD_Campo(nome = "banco", set = "setBanco", get = "getBanco")	
private String banco;

@Anot_BD_Campo(nome = "agencia", set = "setAgencia", get = "getAgencia")	
private String agencia;

@Anot_BD_Campo(nome = "conta", set = "setConta", get = "getConta")	
private String conta;

@Anot_BD_Campo(nome = "titular", set = "setTitular", get = "getTitular")	
private String titular;

@Anot_BD_Campo(nome = "fk_credor", tipo=int.class, set = "setFk_credor", get = "getFk_credor")		
private int fk_credor;

@Anot_BD_Campo(nome = "fk_fornecedor", tipo=int.class, set = "setFk_fornecedor", get = "getFk_fornecedor")		
private int fk_fornecedor;



public String getBanco() {	return banco;}
public void setBanco(String banco) {	this.banco = banco;}

public String getAgencia() {	return agencia;}
public void setAgencia(String agencia) {this.agencia = agencia;}

public String getConta() {	return conta;}
public void setConta(String conta) {	this.conta = conta;}

public String getTitular() {	return titular;}
public void setTitular(String titular) {	this.titular = titular;}

public int getId_conta() {	return id_conta;}
public void setId_conta(int id_conta) {	this.id_conta = id_conta;}

public int getFk_credor() {	return fk_credor;}
public void setFk_credor(int fk_credor) {	this.fk_credor = fk_credor;}

public int getFk_fornecedor() {	return fk_fornecedor;}
public void setFk_fornecedor(int fk_fornecedor) {	this.fk_fornecedor = fk_fornecedor;}
	
	
	
}
