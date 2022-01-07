package classes.financeiro.beans;

import java.util.Date;

import classes.comuns.Calculo;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;



@Anot_BD_Tabela(nome="entrada_saida", prefixo="ent_sai")
public class Entrada_Saida {

	
@Anot_BD_Campo(nome = "id_entrada_saida", tipo=int.class, set = "setId_entrada_saida", get = "getId_entrada_saida", ehId=true)			
private int id_entrada_saida;	
	
@Anot_TB_Coluna(posicao=0, rotulo="Referente", comprimento = 25)
@Anot_BD_Campo(nome = "referente", set = "setReferente", get = "getReferente")
private String referente;	

@Anot_TB_Coluna(posicao=2, rotulo="Val. Total (R$)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_total", set = "setValor_total", get = "getValor_total", getTab="getValor_totalTab")
private String valor_total;

@Anot_BD_Campo(nome = "porcento_comissao", set = "setPorcento_comissao", get = "getPorcento_comissao")
private String porcento_comissao;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "porcento_multa", set = "setPorcento_multa", get = "getPorcento_multa")
private String porcento_multa;

@Anot_BD_Campo(nome = "porcento_juros", set = "setPorcento_juros", get = "getPorcento_juros")
private String porcento_juros;

@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")
private String tipo;

@Anot_TB_Coluna(posicao=4, rotulo="Status Pag.", comprimento = 15)
@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus", getTab="getStatusTab")
private String status;

@Anot_BD_Campo(nome = "fk_cliente", tipo=int.class, set = "setFk_cliente", get = "getFk_cliente")			
private int fk_cliente;

@Anot_BD_Campo(nome = "fk_colaborador", tipo=int.class, set = "setFk_colaborador", get = "getFk_colaborador")
private int fk_colaborador;






public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getReferente() {return referente;}
public void setReferente(String referente) {	this.referente = referente;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}

public String getValor_total() {return valor_total;}
public void setValor_total(String valor_total) {this.valor_total = valor_total;}
public String getValor_totalTab(){return Calculo.formataValor(this.valor_total);}

public String getPorcento_multa() {return porcento_multa;}
public void setPorcento_multa(String porcento_multa) {this.porcento_multa = porcento_multa;}

public String getPorcento_juros() {return porcento_juros;}
public void setPorcento_juros(String porcento_juros) {this.porcento_juros = porcento_juros;}

public String getStatus() {return status;}
public void setStatus(String status) {this.status = status;}
public String getStatusTab() { return "<html><font color="+(this.status.compareTo("ABERTO")==0?"red>":(this.status.compareTo("EM PAGAMENTO")==0?"green>":"blue>"))+this.status+"</font></html>";}

public int getFk_cliente() {return fk_cliente;}
public void setFk_cliente(int fk_cliente) {this.fk_cliente = fk_cliente;}

public String getPorcento_comissao() {return porcento_comissao;}
public void setPorcento_comissao(String porcento_comissao) {this.porcento_comissao = porcento_comissao;}
	
public int getId_entrada_saida() {	return id_entrada_saida;}
public void setId_entrada_saida(int id_entrada_saida) {	this.id_entrada_saida = id_entrada_saida;}

public int getFk_colaborador() {	return fk_colaborador;}
public void setFk_colaborador(int fk_colaborador) {	this.fk_colaborador = fk_colaborador;}




}
