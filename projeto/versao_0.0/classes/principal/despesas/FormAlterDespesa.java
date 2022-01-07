package principal.despesas;


import DAO.DAO;
import componentes.beans.Despesa;




public class FormAlterDespesa extends FormDespesaBase {

	
	
private static final long serialVersionUID = 1L;




	public FormAlterDespesa(Despesa despesa) {
			
	super("Alterar Despesa", despesa);
	

	this.adicionarComponentes();
	
	this.tf_descricao.setText(this.retorno.getDescricao());
	this.tf_valor_base.setText(this.retorno.getValor_base());
	}
	



	
	

	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setValor_base(this.tf_valor_base.getText());
	
	return new DAO<Despesa>(Despesa.class).altera(this.retorno);
	}






	
}
