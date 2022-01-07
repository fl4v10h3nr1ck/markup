package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="retiradas", prefixo="reti")
public class Retirada {

@Anot_BD_Campo(nome = "id_retirada", tipo=int.class, set = "setId_retirada", get = "getId_retirada", ehId=true)	
private int id_retirada;

@Anot_BD_Campo(nome = "fk_colaborador", tipo=int.class, set = "setFk_colaborador", get = "getFk_colaborador")	
private int fk_colaborador;

@Anot_BD_Campo(nome = "data", tipo=Date.class, set = "setData", get = "getData")	
private Date data;

@Anot_BD_Campo(nome = "hora", tipo=int.class, set = "setHora", get = "getHora")	
private int hora;

@Anot_BD_Campo(nome = "min", tipo=int.class, set = "setMin", get = "getMin")	
private int min;

@Anot_BD_Campo(nome = "quant_total", tipo=int.class, set = "setQuant_total", get = "getQuant_total")	
private int quant_total;



public int getId_retirada() {	return id_retirada;}
public void setId_retirada(int id_retirada) {	this.id_retirada = id_retirada;}

public int getFk_colaborador() {	return fk_colaborador;}
public void setFk_colaborador(int fk_colaborador) {	this.fk_colaborador = fk_colaborador;}

public Date getData() {	return data;}
public void setData(Date data) {	this.data = data;}

public int getHora() {	return hora;}
public void setHora(int hora) {	this.hora = hora;}

public int getMin() {	return min;}
public void setMin(int min) {	this.min = min;}

public int getQuant_total() {	return quant_total;}
public void setQuant_total(int quant_total) {	this.quant_total = quant_total;}







	
	
	
}
