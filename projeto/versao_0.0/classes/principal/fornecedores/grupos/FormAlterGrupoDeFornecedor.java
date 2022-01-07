package principal.fornecedores.grupos;


import DAO.DAO;
import componentes.beans.Grupo_fornecedor;




public class FormAlterGrupoDeFornecedor extends FormGrupoBase {

	
	
private static final long serialVersionUID = 1L;




	public FormAlterGrupoDeFornecedor(Grupo_fornecedor retorno) {
			
	super("Alterar Informações de Grupo", 650, 140, retorno);
	
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
				
	return new DAO<Grupo_fornecedor>(Grupo_fornecedor.class).altera(this.retorno);
	}




	
	

	
	
}
