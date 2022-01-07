package classes.estoque.cadastro.subcategoria;

import classes.dao.DAO;
import classes.estoque.beans.Subcategoria;




public class FormAlterSubcategoria extends FormSubcategoriaBase {

	
	
private static final long serialVersionUID = 1L;






	public FormAlterSubcategoria(Subcategoria retorno) {
			
	super("Alterar Subcategoria de Produto", 650, 140, retorno);
	
	this.adicionarComponentes();
	
	this.tf_codigo.setText(this.retorno.getCodigo());
	this.tf_descricao.setText(this.retorno.getDescricao());
	}



	
	


	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	
	return  new DAO<Subcategoria>(Subcategoria.class).altera(this.retorno);
	}




	
	
	
	
	

	
	
}
