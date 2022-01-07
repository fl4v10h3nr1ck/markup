package principal.forma_de_pagamento;

import DAO.DAO;
import componentes.beans.FormaDePagamento;



public class FormNovaFormaDePagamento extends FormFormaDePagamentoBase {

	
	
private static final long serialVersionUID = 1L;




	public FormNovaFormaDePagamento() {
	
	this(null);
	}



	
	public FormNovaFormaDePagamento(FormaDePagamento retorno) {
			
	super("Novo Tipo De Pagamento", retorno);
	
	this.adicionarComponentes();
	}

	
	

	@Override
	public boolean acaoPrincipal() {
	
	if(!validation())
	return false;
	
	if(this.retorno == null)
	this.retorno = new FormaDePagamento();
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setPadrao(this.padrao.getSelectedItem().toString());
	this.retorno.setTipo(this.tipo.getSelectedItem().toString());
	this.retorno.setTipo_parcela(this.tipo_parcela.getSelectedItem().toString());
	this.retorno.setNum_max_parcelas(this.tf_num_max_parcelas.getText().length() > 0? Integer.parseInt(this.tf_num_max_parcelas.getText()):0);
	this.retorno.setStatus("ATIVO");	
	
	int id = new DAO<FormaDePagamento>(FormaDePagamento.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_forma_pag(id);
	
	return true;
	}




	
	
}
