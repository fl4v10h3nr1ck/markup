package classes.estoque.cadastro.subcategoria;

import classes.dao.DAO;
import classes.estoque.beans.Subcategoria;




public class FormNovaSubcategoria extends FormSubcategoriaBase {

	
	
private static final long serialVersionUID = 1L;



	
	public FormNovaSubcategoria() {
			
	this(null);
	}

	
	
	
	public FormNovaSubcategoria(Subcategoria retorno) {
		
	super("Nova Subcategoria de Produto", 650, 140, retorno);
		
	this.adicionarComponentes();
	}
	
	

	
	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	if(this.retorno == null)
	this.retorno = new Subcategoria();
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setAtivo("S");
	
	int id =  new DAO<Subcategoria>(Subcategoria.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_subcategoria(id);
	
	return true;
	}




	
	
	
}
