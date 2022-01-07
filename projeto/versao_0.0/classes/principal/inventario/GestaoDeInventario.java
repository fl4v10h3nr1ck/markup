package principal.inventario;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Inventario;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;



public class GestaoDeInventario extends FormDeGestaoBase<Inventario>{




private static final long serialVersionUID = 1L;






	public GestaoDeInventario(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Inventario>("Itens de Inventário", Inventario.class);
	this.motorDePesquisa.tableModel.setWhere("inv.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by inv.data_cadastro DESC");
	
	adicionarComponentes();	
	


	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(5, 5, 0, 5);
	JButton btRetirada = new JButton(new ImageIcon(getClass().getResource("/icons/transferencia.png")));
	btRetirada.setToolTipText("Retirada de inventário");
	this.painelNovosItens.add(btRetirada, cons);	
	
	cons.insets = new Insets(0, 5, 0, 5);
	this.painelNovosItens.add(new JLabel("Retirada") , cons);
	
	
	
		btRetirada.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		FormDeRetirada form = new FormDeRetirada();
		form.mostrar();
			
		motorDePesquisa.update();	
		}});
	

	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoInventario novo = new FormNovoInventario();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	
	
	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Inventario selectedItem= (Inventario) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterInventario alterForm = new FormAlterInventario( new DAO<Inventario>(Inventario.class).get(selectedItem.getId_inventario()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de inventário para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Inventario selectedItem= (Inventario) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
					
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de inventário para exclusão.");
		else{
		
		if( new DAO<Inventario>(Inventario.class).desativar(selectedItem.getId_inventario()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
