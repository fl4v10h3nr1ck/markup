package principal.fornecedores.grupos;

import DAO.DAO;
import componentes.beans.Grupo_fornecedor;



public class FormNovoGrupoDeFornecedor extends FormGrupoBase {

	
	
private static final long serialVersionUID = 1L;



	
	public FormNovoGrupoDeFornecedor() {
			
	this(null);
	}
	



	
	public FormNovoGrupoDeFornecedor(Grupo_fornecedor retorno) {
			
	super("Novo Grupo de Fornecedores", 650, 140, retorno);
	
	this.adicionarComponentes();
	}

	
	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	if(this.retorno ==null)
	this.retorno = new Grupo_fornecedor();
	
	this.retorno.setCodigo(this.tf_codigo.getText());
	this.retorno.setDescricao(this.tf_descricao.getText());
	
	int id = new DAO<Grupo_fornecedor>(Grupo_fornecedor.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_grupo_fornecedor(id);
	
	return true;
	}




	
	
	
}
