package principal.despesas;


import DAO.DAO;
import componentes.beans.Despesa;



public class FormNovaDespesa extends FormDespesaBase {

	
	
private static final long serialVersionUID = 1L;






	public FormNovaDespesa() {
	
	this(null);
	}

	



	
	public FormNovaDespesa(Despesa retorno) {
			
	super("Nova Despesa", retorno);
	
	this.adicionarComponentes();
	}

	
	

	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	if(this.retorno==null)
	this.retorno = new Despesa();
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setValor_base(this.tf_valor_base.getText());
	this.retorno.setStatus("ATIVO");
	
	int id = new DAO<Despesa>(Despesa.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_despesa(id);
	
	return true;
	}






	
	
}
