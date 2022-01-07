package classes.financeiro.formas_de_pagamento;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import util.Mensagens;
import classes.componentes.tabelas.MotorDePesquisa;
import classes.dao.DAO;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;
import classes.principal.FormDeGestaoBase;






public class GestaoDeFormasDePagamento extends FormDeGestaoBase<FormaDePagamento>{

	
private static final long serialVersionUID = 1L;



	public GestaoDeFormasDePagamento(){
	
	super("Gestão de Formas de Pagamento", 800, 500);	
		
	this.motorDePesquisa = new MotorDePesquisa<FormaDePagamento>("Formas de Pagamento", FormaDePagamento.class);
	this.motorDePesquisa.modelo.setOrderBy("order by f_pag.id_forma_pag DESC");
	this.motorDePesquisa.modelo.setWhere("f_pag.ativo='S'");
	
	this.adicionarComponentes();
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.ipadx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.motorDePesquisa.painel_menu_de_opcoes.add(new JLabel(""), cons);
	
		
	motorDePesquisa.atualizar();
	}





	@Override
	public void novoForm() {
		
	FormNovaFormaDePagamento form = new FormNovaFormaDePagamento();	
	form.mostrar();
		
	motorDePesquisa.atualizar();	
	}





	@Override
	public void alterarForm() {
	
	FormaDePagamento selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterFormaDePagamento alterForm = new FormAlterFormaDePagamento(selecionado);
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para alteração.");	
		
	}





	@Override
	public void deletarForm() {
	
	FormaDePagamento selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
			
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de formas de pagamento para exclusão.");
		else{
			
		selecionado.setAtivo("N");	
				
		new DAO<FormaDePagamento>(FormaDePagamento.class).altera(selecionado);
					
		motorDePesquisa.atualizar();
		}		
	}





	@Override
	public boolean acaoPrincipal() {return false;}
	
	

	
	
	
	
	
	
	
}
