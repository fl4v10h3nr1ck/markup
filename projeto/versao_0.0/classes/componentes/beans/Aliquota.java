package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;

@Anot_BD_Tabela(nome="aliquotas", prefixo="aliq")
public class Aliquota {

@Anot_BD_Campo(nome = "id_aliquota", tipo=int.class, set = "setId_aliquota", get = "getId_aliquota", ehId=true)	
private int id_aliquota;

@Anot_BD_Campo(nome = "valor_icms_porcento", set = "setValor_icms_porcento", get = "getValor_icms_porcento")	
private String valor_icms_porcento;

@Anot_BD_Campo(nome = "valor_pis_porcento", set = "setValor_pis_porcento", get = "getValor_pis_porcento")
private String valor_pis_porcento;

@Anot_BD_Campo(nome = "valor_cofins_porcento", set = "setValor_cofins_porcento", get = "getValor_cofins_porcento")
private String valor_cofins_porcento;

@Anot_BD_Campo(nome = "valor_ipi_porcento", set = "setValor_ipi_porcento", get = "getValor_ipi_porcento")
private String valor_ipi_porcento;

@Anot_BD_Campo(nome = "valor_csll_porcento", set = "setValor_csll_porcento", get = "getValor_csll_porcento")
private String valor_csll_porcento;

@Anot_BD_Campo(nome = "valor_irpj_porcento", set = "setValor_irpj_porcento", get = "getValor_irpj_porcento")
private String valor_irpj_porcento;

@Anot_BD_Campo(nome = "valor_comissao_porcento", set = "setValor_comissao_porcento", get = "getValor_comissao_porcento")
private String valor_comissao_porcento;

@Anot_BD_Campo(nome = "valor_administrativa_porcento", set = "setValor_administrativa_porcento", get = "getValor_administrativa_porcento")
private String valor_administrativa_porcento;

@Anot_BD_Campo(nome = "valor_issqn_porcento", set = "setValor_issqn_porcento", get = "getValor_issqn_porcento")
private String valor_issqn_porcento;

@Anot_BD_Campo(nome = "regime_tributario", set = "setRegime_tributario", get = "getRegime_tributario")
private String regime_tributario;




public String getValor_administrativa_porcento() {	return valor_administrativa_porcento;}
public void setValor_administrativa_porcento(String valor_administrativa_porcento) {	this.valor_administrativa_porcento = valor_administrativa_porcento;}

public String getValor_comissao_porcento() {	return valor_comissao_porcento;}
public void setValor_comissao_porcento(String valor_comissao_porcento) {	this.valor_comissao_porcento = valor_comissao_porcento;}

public String getValor_irpj_porcento() {	return valor_irpj_porcento;}
public void setValor_irpj_porcento(String valor_irpj_porcento) {	this.valor_irpj_porcento = valor_irpj_porcento;}

public String getValor_csll_porcento() {	return valor_csll_porcento;}
public void setValor_csll_porcento(String valor_csll_porcento) {	this.valor_csll_porcento = valor_csll_porcento;}

public String getValor_ipi_porcento() {	return valor_ipi_porcento;}
public void setValor_ipi_porcento(String valor_ipi_porcento) {	this.valor_ipi_porcento = valor_ipi_porcento;}

public String getValor_cofins_porcento() {	return valor_cofins_porcento;}
public void setValor_cofins_porcento(String valor_cofins_porcento) {	this.valor_cofins_porcento = valor_cofins_porcento;}

public String getValor_pis_porcento() {	return valor_pis_porcento;}
public void setValor_pis_porcento(String valor_pis_porcento) {	this.valor_pis_porcento = valor_pis_porcento;}

public String getValor_icms_porcento() {	return valor_icms_porcento;}
public void setValor_icms_porcento(String valor_icms_porcento) {	this.valor_icms_porcento = valor_icms_porcento;}

public String getValor_issqn_porcento() {	return valor_issqn_porcento;}
public void setValor_issqn_porcento(String valor_issqn_porcento) {	this.valor_issqn_porcento = valor_issqn_porcento;}

public String getRegime_tributario() {	return regime_tributario;}
public void setRegime_tributario(String regime_tributario) {	this.regime_tributario = regime_tributario;}

public int getId_aliquota() {	return id_aliquota;}
public void setId_aliquota(int id_aliquota) {	this.id_aliquota = id_aliquota;}



}
