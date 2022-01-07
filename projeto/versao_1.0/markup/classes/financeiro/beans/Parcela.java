package classes.financeiro.beans;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="parcelas", prefixo="parc")
public class Parcela implements Comparable<Parcela>{

@Anot_BD_Campo(nome = "id_parcela", tipo=int.class, set = "setId_parcela", get = "getId_parcela", ehId=true)			
private int id_parcela;

@Anot_BD_Campo(nome = "indice", tipo=int.class, set = "setIndice", get = "getIndice")			
private int indice;

@Anot_BD_Campo(nome = "vencimento_dia", tipo=int.class, set = "setVencimento_dia", get = "getVencimento_dia")			
private int vencimento_dia;

@Anot_BD_Campo(nome = "vencimento_mes", tipo=int.class, set = "setVencimento_mes", get = "getVencimento_mes")			
private int vencimento_mes;

@Anot_BD_Campo(nome = "vencimento_ano", tipo=int.class, set = "setVencimento_ano", get = "getVencimento_ano")			
private int vencimento_ano;

@Anot_BD_Campo(nome = "data_vencimento", tipo=Date.class, set = "setData_vencimento", get = "getData_vencimento")			
private Date data_vencimento;

@Anot_BD_Campo(nome = "valor", set = "setValor", get = "getValor")			
private String valor;

@Anot_BD_Campo(nome = "valor_final", set = "setValor_final", get = "getValor_final")			
private String valor_final;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")			
private String status;

@Anot_BD_Campo(nome = "fk_entrada_saida", tipo=int.class, set = "setFk_entrada_saida", get = "getFk_entrada_saida")			
private int fk_entrada_saida;

@Anot_BD_Campo(nome = "tipo_parcela", set = "setTipo_parcela", get = "getTipo_parcela")			
private String tipo_parcela;

@Anot_BD_Campo(nome = "data_pagamento", tipo=Date.class, set = "setData_pagamento", get = "getData_pagamento")			
private Date data_pagamento;

@Anot_BD_Campo(nome = "fk_forma_de_pagamento", tipo=int.class, set = "setFk_forma_de_pagamento", get = "getFk_forma_de_pagamento")			
private int fk_forma_de_pagamento;





public int getFk_entrada_saida() {	return fk_entrada_saida;}
public void setFk_entrada_saida(int fk_pagamento) {	this.fk_entrada_saida = fk_pagamento;}

public String getValor_final() {	return valor_final;}
public void setValor_final(String valor_final) {	this.valor_final = valor_final;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public int getIndice() {	return indice;}
public void setIndice(int indice) {this.indice = indice;}

public int getId_parcela() {	return id_parcela;}
public void setId_parcela(int id_parcela) {	this.id_parcela = id_parcela;}

public Date getData_vencimento() {	return data_vencimento;}
public void setData_vencimento(Date data_vencimento) {	this.data_vencimento = data_vencimento;}

public Date getData_pagamento() {	return data_pagamento;}
public void setData_pagamento(Date data_pagamento) {	this.data_pagamento = data_pagamento;}

public int getVencimento_dia() {	return vencimento_dia;}
public void setVencimento_dia(int vencimento_dia) {	this.vencimento_dia = vencimento_dia;}

public int getVencimento_mes() {	return vencimento_mes;}
public void setVencimento_mes(int vencimento_mes) {	this.vencimento_mes = vencimento_mes;}

public int getVencimento_ano() {	return vencimento_ano;}
public void setVencimento_ano(int vencimento_ano) {	this.vencimento_ano = vencimento_ano;}

public String getValor() {	return valor;}
public void setValor(String valor) {	this.valor = valor;}

public String getTipo_parcela() {	return tipo_parcela;}
public void setTipo_parcela(String tipo_parcela) {	this.tipo_parcela = tipo_parcela;}

public int getFk_forma_de_pagamento() {	return fk_forma_de_pagamento;}
public void setFk_forma_de_pagamento(int fk_forma_de_pagamento) {	this.fk_forma_de_pagamento = fk_forma_de_pagamento;}



	
	@Override
	public int compareTo(Parcela o) {
	
	if(this.getFk_forma_de_pagamento() == o.getFk_forma_de_pagamento())
	return 0;
						
	return this.getFk_forma_de_pagamento() < o.getFk_forma_de_pagamento()?-1:1;		
	}
	



}
