package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import comuns.Calculo;
import comuns.Data;

@Anot_BD_Tabela(nome="caixas", prefixo="cai")
public class Caixa {

	
@Anot_BD_Campo(nome = "id_caixa", tipo=int.class, set = "setId_caixa", get = "getId_caixa", ehId=true)	
private int id_caixa;

@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 29)
@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")	
private String codigo;

@Anot_TB_Coluna(posicao=1, rotulo="Terminal", comprimento = 18)
@Anot_BD_Campo(nome = "codigo_terminal", set = "setCodigo_terminal", get = "getCodigo_terminal")	
private String codigo_terminal;


@Anot_TB_Coluna(posicao=2, rotulo="Status", comprimento = 15)
@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus", getTab="getStatusTab")	
private String status;

@Anot_TB_Coluna(posicao=3, rotulo="Abertura", comprimento = 14)
@Anot_BD_Campo(nome = "data_abertura", tipo=Date.class, set = "setData_abertura", get = "getData_abertura", getTab="getDataAberturaTab")	
private Date data_abertura;

@Anot_BD_Campo(nome = "hora_abertura", tipo=int.class, set = "setHora_abertura", get = "getHora_abertura")	
private int hora_abertura;

@Anot_BD_Campo(nome = "min_abertura", tipo=int.class, set = "setMin_abertura", get = "getMin_abertura")	
private int min_abertura;

@Anot_TB_Coluna(posicao=4, rotulo="Fechamento", comprimento = 14)
@Anot_BD_Campo(nome = "data_fechamento", tipo=Date.class, set = "setData_fechamento", get = "getData_fechamento", getTab="getDataFechamentoTab")	
private Date data_fechamento;

@Anot_BD_Campo(nome = "hora_fechamento", tipo=int.class, set = "setHora_fechamento", get = "getHora_fechamento")	
private int hora_fechamento;

@Anot_BD_Campo(nome = "min_fechamento", tipo=int.class, set = "setMin_fechamento", get = "getMin_fechamento")	
private int min_fechamento;

@Anot_BD_Campo(nome = "fk_user_abertura", tipo=int.class, set = "setFk_user_abertura", get = "getFk_user_abertura")	
private int fk_user_abertura;

@Anot_BD_Campo(nome = "fk_user_fechamento", tipo=int.class, set = "setFk_user_fechamento", get = "getFk_user_fechamento")	
private int fk_user_fechamento;

@Anot_BD_Campo(nome = "valor_inicial", set = "setValor_inicial", get = "getValor_inicial")	
private String valor_inicial;

@Anot_TB_Coluna(posicao=5, rotulo="Saldo (R$)", comprimento = 10)
@Anot_BD_Campo(nome = "valor_fechamento", set = "setValor_fechamento", get = "getValor_fechamento", getTab="getValor_fechamentoTab")	
private String valor_fechamento;





public int getId_caixa() {	return id_caixa;}
public void setId_caixa(int id_caixa) {	this.id_caixa = id_caixa;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}

public Date getData_abertura() {	return data_abertura;}
public void setData_abertura(Date data_abertura) {	this.data_abertura = data_abertura;}
public String getDataAberturaTab(){return this.data_abertura==null?"":String.format("%s %02d:%02d", Data.converteDataParaString(this.data_abertura), this.hora_abertura, this.min_abertura);}


public int getHora_abertura() {	return hora_abertura;}
public void setHora_abertura(int hora_abertura) {	this.hora_abertura = hora_abertura;}

public int getMin_abertura() {	return min_abertura;}
public void setMin_abertura(int min_abertura) {	this.min_abertura = min_abertura;}

public Date getData_fechamento() {	return data_fechamento;}
public void setData_fechamento(Date data_fechamento) {	this.data_fechamento = data_fechamento;}
public String getDataFechamentoTab(){return this.data_fechamento==null?"":String.format("%s %02d:%02d", Data.converteDataParaString(this.data_fechamento), this.hora_fechamento, this.min_fechamento);}

public int getHora_fechamento() {	return hora_fechamento;}
public void setHora_fechamento(int hora_fechamento) {	this.hora_fechamento = hora_fechamento;}

public int getMin_fechamento() {	return min_fechamento;}
public void setMin_fechamento(int min_fechamento) {	this.min_fechamento = min_fechamento;}

public int getFk_user_abertura() {	return fk_user_abertura;}
public void setFk_user_abertura(int fk_user_abertura) {	this.fk_user_abertura = fk_user_abertura;}

public int getFk_user_fechamento() {	return fk_user_fechamento;}
public void setFk_user_fechamento(int fk_user_fechamento) {	this.fk_user_fechamento = fk_user_fechamento;}

public String getValor_inicial() {	return valor_inicial;}
public void setValor_inicial(String valor_inicial) {	this.valor_inicial = valor_inicial;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}
public String getStatusTab() { return "<html><font color="+(this.status.compareTo("ABERTO")==0?"red>":"green>")+this.status+"</font></html>";}



public String getValor_fechamento() {	return valor_fechamento;}
public void setValor_fechamento(String valor_fechamento) {	this.valor_fechamento = valor_fechamento;}
public String getValor_fechamentoTab(){return this.status.compareTo("ABERTO")==0?"":Calculo.formataValor(this.valor_fechamento);}


public String getCodigo_terminal() {	return codigo_terminal;}
public void setCodigo_terminal(String codigo_terminal) {	this.codigo_terminal = codigo_terminal;}

	



}
