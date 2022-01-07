package principal.convenios;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Convenio;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeConvenios extends FormDeGestaoBase<Convenio>{



private static final long serialVersionUID = 1L;




	public GestaoDeConvenios(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Convenio>("Convenios e Promoções", Convenio.class);
	this.motorDePesquisa.tableModel.setWhere("conv.status = 'ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by conv.id_convenio DESC");
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoConvenio novo = new FormNovoConvenio();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Convenio selectedItem= (Convenio) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterConvenio alterForm = new FormAlterConvenio( new DAO<Convenio>(Convenio.class).get(selectedItem.getId_convenio()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de convênios para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Convenio selectedItem= (Convenio) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
					
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de convênios para exclusão.");
		else{
		
		if( new DAO<Convenio>(Convenio.class).desativar(selectedItem.getId_convenio()))	
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

