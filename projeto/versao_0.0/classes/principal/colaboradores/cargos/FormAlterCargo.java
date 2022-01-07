package principal.colaboradores.cargos;


import DAO.DAO;
import componentes.beans.Cargo;





public class FormAlterCargo extends FormCargoBase {

	
	
private static final long serialVersionUID = 1L;



	public FormAlterCargo(Cargo retorno) {
			
	super("Alterar Informações de Cargo", retorno);
	
	
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
						
	return new DAO<Cargo>(Cargo.class).altera(this.retorno);
	}




}
