package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="movimentos_x_formas_de_pagamento", prefixo="mov_fpag")
public class Movimentos_x_FormasPag {


@Anot_BD_Campo(nome = "id_mov_pag", tipo=int.class, set = "setId_mov_pag", get = "getId_mov_pag", ehId=true)			
private int id_mov_pag;

@Anot_BD_Campo(nome = "fk_movimento", tipo=int.class, set = "setFk_movimento", get = "getFk_movimento")			
private int fk_movimento;

@Anot_BD_Campo(nome = "fk_forma_de_pagamento", tipo=int.class, set = "setFk_forma_de_pagamento", get = "getFk_forma_de_pagamento")			
private int fk_forma_de_pagamento;

@Anot_BD_Campo(nome = "num_de_parcelas", tipo=int.class, set = "setNum_de_parcelas", get = "getNum_de_parcelas")			
private int num_de_parcelas;

@Anot_BD_Campo(nome = "data_primeiro_vencimento", set = "setData_primeiro_vencimento", get = "getData_primeiro_vencimento")			
private Date data_primeiro_vencimento;

@Anot_BD_Campo(nome = "valor_total", set = "setValor_total", get = "getValor_total")			
private String valor_total;





public int getId_mov_pag() {	return id_mov_pag;}
public void setId_mov_pag(int id_mov_pag) {	this.id_mov_pag = id_mov_pag;}

public int getFk_movimento() {	return fk_movimento;}
public void setFk_movimento(int fk_movimento) {	this.fk_movimento = fk_movimento;}

public int getFk_forma_de_pagamento() {	return fk_forma_de_pagamento;}
public void setFk_forma_de_pagamento(int fk_forma_de_pagamento) {	this.fk_forma_de_pagamento = fk_forma_de_pagamento;}

public int getNum_de_parcelas() {	return num_de_parcelas;}
public void setNum_de_parcelas(int num_de_parcelas) {	this.num_de_parcelas = num_de_parcelas;}

public Date getData_primeiro_vencimento() {	return data_primeiro_vencimento;}
public void setData_primeiro_vencimento(Date data_primeiro_vencimento) {	this.data_primeiro_vencimento = data_primeiro_vencimento;}

public String getValor_total() {	return valor_total;}
public void setValor_total(String valor_total) {	this.valor_total = valor_total;}
	
	




	
}
