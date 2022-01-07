package principal;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import componentes.janelas.Dialogo;
import componentes.tabelas.MotorDePesquisa;
import comuns.Preferencias;






public abstract class FormDeGestaoBase<T> extends Dialogo{

	
	

private static final long serialVersionUID = 1L;



public MotorDePesquisa<T> motorDePesquisa;
	
	
public JButton bt_novo;
public JButton bt_alterar;
public JButton bt_deletar;


public JPanel painelNovosItens;





	public FormDeGestaoBase(String titulo){
	
	super(titulo, 980, 670);	
		

	this.setLayout( new GridBagLayout());
	this.setBackground(Preferencias.COR_DE_FUNDO_FRAME);
	}
	
	

	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(1, 1, 1, 1);
	JPanel p_1 = new JPanel(new GridBagLayout());
	p_1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p_1, cons);
	p_1.setBorder(BorderFactory.createTitledBorder("Opções"));  
	
	
	cons.gridwidth = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	JPanel painelDeOpcoes = new JPanel(new GridBagLayout());
	painelDeOpcoes.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_1.add(painelDeOpcoes , cons);

	cons.gridwidth = GridBagConstraints.REMAINDER;	
	painelNovosItens = new JPanel(new GridBagLayout());
	painelNovosItens.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_1.add(painelNovosItens, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = 1;	
	cons.insets = new Insets(5, 5, 0, 5);
	this.bt_novo = new JButton(new ImageIcon(getClass().getResource("/icons/novo.png")));
	bt_novo.setToolTipText("Adicionar novo.");
	painelDeOpcoes.add(bt_novo, cons);
	
	this.bt_alterar = new JButton(new ImageIcon(getClass().getResource("/icons/alterar.png")));
	bt_alterar.setToolTipText("Alterar selecionado.");
	painelDeOpcoes.add(bt_alterar, cons);

	this.bt_deletar = new JButton(new ImageIcon(getClass().getResource("/icons/remover.png")));
	bt_deletar.setToolTipText("Excluir selecionado.");
	painelDeOpcoes.add(bt_deletar, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(0, 0, 0, 0);
	painelDeOpcoes.add(new JLabel("") , cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = 1;	
	cons.insets = new Insets(0, 5, 0, 5);
	painelDeOpcoes.add(new JLabel("Novo") , cons);
	painelDeOpcoes.add(new JLabel("Editar") , cons);
	painelDeOpcoes.add(new JLabel("Excluir") , cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(0, 0, 0, 0);
	painelDeOpcoes.add(new JLabel("") , cons);
	

	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(1, 1, 1, 1);
	
	this.add(this.motorDePesquisa, cons);
	

		motorDePesquisa.table.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount()==2)
		alterarForm();
		
		}}); 
	
		
		bt_novo.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		novoForm();	
		motorDePesquisa.tableModel.atualiza();
		}});

		
		
		bt_alterar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		alterarForm();
		motorDePesquisa.tableModel.atualiza();
		}});	

		

		bt_deletar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				  
		deletarForm();
		motorDePesquisa.tableModel.atualiza();
		}});			
	}
	
	
	
	
	public abstract void novoForm();
	
	
	
	
	public abstract void alterarForm();
	
	
	
	
	public abstract void deletarForm();
	
	
}

