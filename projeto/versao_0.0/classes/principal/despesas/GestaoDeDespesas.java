package principal.despesas;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Despesa;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeDespesas extends FormDeGestaoBase<Despesa>{




private static final long serialVersionUID = 1L;






	public GestaoDeDespesas(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Despesa>("Despesas", Despesa.class);
	this.motorDePesquisa.tableModel.setWhere("desp.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by desp.id_despesa DESC");
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaDespesa novo = new FormNovaDespesa();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Despesa selectedItem= (Despesa) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterDespesa alterForm = new FormAlterDespesa( new DAO<Despesa>(Despesa.class).get(selectedItem.getId_despesa()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de despesas para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Despesa selectedItem= (Despesa) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
					
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de compras para exclusão.");
		else{
		
		if( new DAO<Despesa>(Despesa.class).desativar(selectedItem.getId_despesa()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

