package principal.fornecedores;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import DAO.DAO;
import principal.FormDeGestaoBase;
import principal.fornecedores.grupos.GestaoDeGrupos;
import componentes.Rotulo;
import componentes.beans.Fornecedor;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeFornecedores extends FormDeGestaoBase<Fornecedor>{

	

private static final long serialVersionUID = 1L;





	public GestaoDeFornecedores(){
		
	super("Gestão de Fornecedores");
	
	this.motorDePesquisa = new MotorDePesquisa<Fornecedor>("Fornecedores", Fornecedor.class);
	this.motorDePesquisa.tableModel.setWhere("forn.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by forn.id_fornecedor DESC");
	
	adicionarComponentes();	
	
	
	GridBagConstraints cons = new GridBagConstraints();  


	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(5, 5, 0, 5);
	JButton bt_grupos = new JButton(new ImageIcon(getClass().getResource("/icons/grupos.png")));
	bt_grupos.setToolTipText("Gestão de Grupos.");
	painelNovosItens.add(bt_grupos, cons);
	
	cons.insets = new Insets(0, 5, 0, 5);
	painelNovosItens.add(new Rotulo("Grupos") , cons);
	
	
	
	
		bt_grupos.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		GestaoDeGrupos form  = new GestaoDeGrupos();
		form.mostrar();
		}});

	
	
	
	
	this.motorDePesquisa.update();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoFornecedor novo = new FormNovoFornecedor();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;

	Fornecedor selectedItem= (Fornecedor) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterFornecedor alterForm = new FormAlterFornecedor( new DAO<Fornecedor>(Fornecedor.class).get(selectedItem.getId_fornecedor()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Fornecedor selectedItem= (Fornecedor) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
			
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para exclusão.");
		else{
				
		if( new DAO<Fornecedor>(Fornecedor.class).desativarSemConfirmacao(selectedItem.getId_fornecedor()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}

