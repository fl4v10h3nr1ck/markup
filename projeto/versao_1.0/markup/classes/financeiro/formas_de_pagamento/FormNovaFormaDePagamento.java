package classes.financeiro.formas_de_pagamento;

import classes.dao.DAO;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;





public class FormNovaFormaDePagamento extends FormFormaDePagamentoBase {

	
	
private static final long serialVersionUID = 1L;




	public FormNovaFormaDePagamento() {
	
	this(null);
	}



	
	
	public FormNovaFormaDePagamento(FormaDePagamento retorno) {
			
	super("Nova Forma De Pagamento", retorno);
	}

	
	

	
	@Override
	public boolean acaoPrincipal() {
	
	if(!validacao())
	return false;
	
	if(this.retorno == null)
	this.retorno = new FormaDePagamento();
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setPadrao(this.padrao.getSelectedIndex()==0?"N":"S");
	this.retorno.setTipo(this.tipo.getSelectedItem().toString());
	this.retorno.setTipo_parcela(this.tipo_parcela.getSelectedItem().toString());
	this.retorno.setNum_max_parcelas(this.tf_num_max_parcelas.getText().length() > 0? Integer.parseInt(this.tf_num_max_parcelas.getText()):0);
	this.retorno.setAtivo("S");	
	
	DAO<FormaDePagamento> dao =  new DAO<FormaDePagamento>(FormaDePagamento.class);
	
	
	int id = dao.novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_forma_pag(id);
	
	this.setFormaPadrao(this.retorno, dao);
	
	return true;
	}




	
	
}
