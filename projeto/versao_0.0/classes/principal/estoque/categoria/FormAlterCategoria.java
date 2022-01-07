package principal.estoque.categoria;


import DAO.DAO;
import componentes.beans.Categoria;




public class FormAlterCategoria extends FormCategoriaBase {

	
	
private static final long serialVersionUID = 1L;






	public FormAlterCategoria(Categoria retorno) {
			
	super("Alterar Categoria de Produto", 650, 140, retorno);
	
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
	
	return  new DAO<Categoria>(Categoria.class).altera(this.retorno);
	}




	
	
	
	
	

	
	
}
