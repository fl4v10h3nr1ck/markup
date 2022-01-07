package principal.credores;

import DAO.DAO;
import principal.FormDeGestaoBase;
import componentes.beans.Credor;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;



public class GestaoDeCredores extends FormDeGestaoBase<Credor>{

	

private static final long serialVersionUID = 1L;





	public GestaoDeCredores(){
		
	super("Gestão de Credores");
	
	this.motorDePesquisa = new MotorDePesquisa<Credor>("Credores", Credor.class);
	this.motorDePesquisa.tableModel.setWhere("cred.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by cred.data_cadastro DESC");
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoCredor novo = new FormNovoCredor();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Credor selectedItem= (Credor) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterCredor alterForm = new FormAlterCredor( new DAO<Credor>(Credor.class).get(selectedItem.getId_credor()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de credores para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Credor selectedItem= (Credor) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
			
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de credores para exclusão.");
		else{
				
		if( new DAO<Credor>(Credor.class).desativar(selectedItem.getId_credor()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return true;}
	
	
	
}

