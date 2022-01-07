package classes.financeiro.formas_de_pagamento;

import classes.dao.DAO;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;






public class FormAlterFormaDePagamento extends FormFormaDePagamentoBase {

	
	
private static final long serialVersionUID = 1L;





	public FormAlterFormaDePagamento(FormaDePagamento retorno) {
			
	super("Alterar Forma de Pagamento", retorno);
	
	this.tf_descricao.setText(this.retorno.getDescricao());
	this.padrao.setSelectedIndex(this.retorno.getPadrao()!=null && this.retorno.getPadrao().compareTo("S")==0?1:0);
	this.tipo.setSelectedItem(this.retorno.getTipo());
	this.tf_num_max_parcelas.setText(""+this.retorno.getNum_max_parcelas());
	this.tipo_parcela.setSelectedItem(this.retorno.getTipo_parcela());
	this.tf_codigo.setText(this.retorno.getCodigo());
	
	if(this.padrao.getSelectedIndex()==1)
	this.padrao.setEnabled(true);
	}



	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setPadrao(this.padrao.getSelectedIndex()==0?"N":"S");
	this.retorno.setTipo(this.tipo.getSelectedItem().toString());
	this.retorno.setTipo_parcela(this.tipo_parcela.getSelectedItem().toString());
	this.retorno.setNum_max_parcelas(this.tf_num_max_parcelas.getText().length() > 0? Integer.parseInt(this.tf_num_max_parcelas.getText()):0);
	
	DAO<FormaDePagamento> dao =  new DAO<FormaDePagamento>(FormaDePagamento.class);
	
	if(!dao.altera(this.retorno))
	return false;
	
	
	this.setFormaPadrao(this.retorno, dao);
	
	return true;
	}	

	
	
}
