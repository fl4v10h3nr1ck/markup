package classes.estoque.cadastro.categoria;

import classes.dao.DAO;
import classes.estoque.beans.Categoria;





public class FormNovaCategoria extends FormCategoriaBase {

	
	
private static final long serialVersionUID = 1L;



	
	public FormNovaCategoria() {
			
	this(null);
	}

	
	
	
	
	public FormNovaCategoria(Categoria retorno) {
		
	super("Nova Categoria de Produto", 650, 140, retorno);
		
	this.adicionarComponentes();
	}
	
	

	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	if(this.retorno == null)
	this.retorno = new Categoria();
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setAtivo("S");
	
	int id =  new DAO<Categoria>(Categoria.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_categoria(id);
	
	return true;
	}




	
	
	
}
