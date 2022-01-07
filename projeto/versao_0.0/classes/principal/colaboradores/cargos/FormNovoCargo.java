package principal.colaboradores.cargos;


import DAO.DAO;
import componentes.beans.Cargo;



public class FormNovoCargo extends FormCargoBase {

	
	
private static final long serialVersionUID = 1L;



	
	public FormNovoCargo() {
			
	this(null);
	}

	
	
	
	public FormNovoCargo(Cargo retorno ) {
		
	super("Novo Cargo", retorno);
		
	this.adicionarComponentes();
	}
	
	
	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	if(this.retorno==null)
	this.retorno = new Cargo();
						
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setCodigo(this.tf_codigo.getText());
			
	int id =  new DAO<Cargo>(Cargo.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_cargo(id);
	
	return true;
	}





	
	
	
	
}
