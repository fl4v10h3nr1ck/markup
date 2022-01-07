package principal.forma_de_pagamento;


import DAO.DAO;
import componentes.beans.FormaDePagamento;




public class FormAlterFormaDePagamento extends FormFormaDePagamentoBase {

	
	
private static final long serialVersionUID = 1L;





	public FormAlterFormaDePagamento(FormaDePagamento retorno) {
			
	super("Alterar Informações", retorno);
	
	this.adicionarComponentes();
	
	this.tf_descricao.setText(this.retorno.getDescricao());
	this.padrao.setSelectedItem(this.retorno.getPadrao());
	this.tipo.setSelectedItem(this.retorno.getTipo());
	this.tf_num_max_parcelas.setText(""+this.retorno.getNum_max_parcelas());
	this.tipo_parcela.setSelectedItem(this.retorno.getTipo_parcela());
	this.tf_codigo.setText(this.retorno.getCodigo());
	
	if(this.padrao.getSelectedItem().toString().compareTo("SIM")==0)
	this.padrao.setEnabled(true);
	}



	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setPadrao(this.padrao.getSelectedItem().toString());
	this.retorno.setTipo(this.tipo.getSelectedItem().toString());
	this.retorno.setTipo_parcela(this.tipo_parcela.getSelectedItem().toString());
	this.retorno.setNum_max_parcelas(this.tf_num_max_parcelas.getText().length() > 0? Integer.parseInt(this.tf_num_max_parcelas.getText()):0);
		
	return new DAO<FormaDePagamento>(FormaDePagamento.class).altera(this.retorno);
	}	

	
	
}
