package principal.compras;




import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import DAO.DAO;
import principal.FormDeGestaoBase;
import componentes.beans.Compra;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeCompras extends FormDeGestaoBase<Compra>{




private static final long serialVersionUID = 1L;






	public GestaoDeCompras(){
		
	super("Gestão de Compras");
	
	this.motorDePesquisa = new MotorDePesquisa<Compra>("Compras", Compra.class);
	this.motorDePesquisa.tableModel.setOrderBy("order by comp.data_cadastro DESC");
	
	adicionarComponentes();	
	


	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(5, 5, 0, 5);
	JButton btfechar = new JButton(new ImageIcon(getClass().getResource("/icons/icon_confirma.png")));
	btfechar.setToolTipText("Fechar Compra.");
	this.painelNovosItens.add(btfechar, cons);	
	
	cons.insets = new Insets(0, 5, 0, 5);
	this.painelNovosItens.add(new JLabel("Fechar") , cons);
	
	
		btfechar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		Compra selectedItem= (Compra) motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
			
			if(selectedItem != null){
			
				
				if(selectedItem.getStatus().compareTo("PENDENTE")==0){	
					
				FormFecharCompra alterForm = new FormFecharCompra( new DAO<Compra>(Compra.class).get(selectedItem.getId_compra()));
				alterForm.mostrar();
				
				motorDePesquisa.update();
				}
				else
				Mensagens.msgDeErro("A compra Seleciona "+(selectedItem.getStatus().compareTo("FECHADO")==0?"Já está fechada.":"foi cancelada."));
					
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de compras para fechá-la.");
			
		motorDePesquisa.update();	
		}});
	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaCompra novo = new FormNovaCompra();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Compra selectedItem= (Compra) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterCompra alterForm = new FormAlterCompra( new DAO<Compra>(Compra.class).get(selectedItem.getId_compra()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de compras para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Compra selectedItem= (Compra) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
				
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de compras para exclusão.");
		else{
		
		Compra compra  = new DAO<Compra>(Compra.class).get(selectedItem.getId_compra());
		compra.setStatus("CANCELADO");	
			
		new DAO<Compra>(Compra.class).altera(compra);
		
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

