package principal.forma_de_pagamento;


import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.FormaDePagamento;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;



public class GestaoDeFormasDePagamento extends FormDeGestaoBase<FormaDePagamento>{

	

private static final long serialVersionUID = 1L;





	public GestaoDeFormasDePagamento(){
		
	super("Gestão de Formas de Pagamento");
	
	this.motorDePesquisa = new MotorDePesquisa<FormaDePagamento>("Formas de Pagamento", FormaDePagamento.class);
	this.motorDePesquisa.tableModel.setWhere("f_pag.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by f_pag.id_forma_pag DESC");
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaFormaDePagamento novo = new FormNovaFormaDePagamento();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	FormaDePagamento selectedItem= (FormaDePagamento) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterFormaDePagamento alterForm = new FormAlterFormaDePagamento( new DAO<FormaDePagamento>(FormaDePagamento.class).get(selectedItem.getId_forma_pag()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de formas de pagamento para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	FormaDePagamento selectedItem= (FormaDePagamento) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
				
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de formas de pagamento para exclusão.");
		else{
				
		if( new DAO<FormaDePagamento>(FormaDePagamento.class).desativar(selectedItem.getId_forma_pag()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return true;}
	
	
	
}

