package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="parcelas", prefixo="par")
public class Parcela {

@Anot_BD_Campo(nome = "id_parcela", tipo=int.class, set = "setId_parcela", get = "getId_parcela", ehId=true)			
private int id_parcela;

@Anot_BD_Campo(nome = "fk_entrada_saida", tipo=int.class, set = "setFk_entrada_saida", get = "getFk_entrada_saida")			
private int fk_entrada_saida;

@Anot_BD_Campo(nome = "data_vencimento", tipo=Date.class, set = "setData_vencimento", get = "getData_vencimento")			
private Date data_vencimento;

@Anot_BD_Campo(nome = "valor_parcela", set = "setValor_parcela", get = "getValor_parcela")			
private String valor_parcela;

@Anot_BD_Campo(nome = "valor_final", set = "setValor_final", get = "getValor_final")			
private String valor_final;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")			
private String status;

@Anot_BD_Campo(nome = "data_pagamento", tipo=Date.class, set = "setData_pagamento", get = "getData_pagamento")			
private Date data_pagamento;

@Anot_BD_Campo(nome = "indice", tipo=int.class, set = "setIndice", get = "getIndice")			
private int indice;



public int getFk_entrada_saida() {	return fk_entrada_saida;}
public void setFk_entrada_saida(int fk_pagamento) {	this.fk_entrada_saida = fk_pagamento;}

public String getValor_parcela() {	return valor_parcela;}
public void setValor_parcela(String valor_parcela) {	this.valor_parcela = valor_parcela;}


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
	



}
