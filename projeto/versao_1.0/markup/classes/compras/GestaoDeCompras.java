package classes.compras;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.Mensagens;
import classes.componentes.impressao.FormDeExibicao;
import classes.componentes.tabelas.MotorDePesquisa;
import classes.compras.beans.Compra;
import classes.compras.relatorios.Rel__Compra;
import classes.principal.FormDeGestaoBase;





public class GestaoDeCompras extends FormDeGestaoBase<Compra>{

	
private static final long serialVersionUID = 1L;



	public GestaoDeCompras(){
	
	super("Gestão de Compras", 
			GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
			GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);	
		
	this.motorDePesquisa = new MotorDePesquisa<Compra>("Compras", Compra.class);
	this.motorDePesquisa.modelo.setOrderBy("order by comp.data_cadastro DESC");
	
	this.adicionarComponentes();
	
	GridBagConstraints cons = new GridBagConstraints();  

	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.weighty  = 0;
	cons.ipadx = 0;
	cons.gridwidth = 1;
	cons.insets = new Insets(0, 5, 0, 5);
	
	
	JButton rel_compra = new JButton(new ImageIcon(getClass().getResource("/icons/rel_compra.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(rel_compra, cons);
	rel_compra.setToolTipText("Gerar lista de compra");
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.ipadx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.motorDePesquisa.painel_menu_de_opcoes.add(new JLabel(""), cons);
	
	
		rel_compra.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		Compra selecionado= motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
			
			if(selecionado != null){
				
			FormDeExibicao form = new FormDeExibicao(new Rel__Compra(selecionado));
			form.mostrar();	
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de compras para gerar lista.");		
				
		}});
		
		
	motorDePesquisa.atualizar();
	}





	@Override
	public void novoForm() {
		
	FormNovaCompra form = new FormNovaCompra();	
	form.mostrar();
		
	motorDePesquisa.atualizar();	
	}





	@Override
	public void alterarForm() {
	
	Compra selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterCompra alterForm = new FormAlterCompra(selecionado);
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de compras para alteração.");	
	}





	@Override
	public void deletarForm() {
	/*	
	Compra selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
			
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de compras para exclusão.");
		else{
			
		selecionado.setAtivo("N");	
				
		new DAO<Produto>(Produto.class).altera(selecionado);
					
		motorDePesquisa.atualizar();
		}	*/	
	}





	@Override
	public boolean acaoPrincipal() {return false;}
	
	

	
	
	
	
	
	
	
}
