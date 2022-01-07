package classes.principal;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import classes.componentes.janelas.Dialogo;
import classes.componentes.tabelas.MotorDePesquisa;
import classes.comuns.Configuracoes;




public abstract class FormDeGestaoBase<T> extends Dialogo{

	
	

private static final long serialVersionUID = 1L;



public MotorDePesquisa<T> motorDePesquisa;
	
	
public JButton bt_novo;
public JButton bt_alterar;
public JButton bt_deletar;


	public FormDeGestaoBase(String titulo){
	
	this(titulo, 980, 670);	
	}






	public FormDeGestaoBase(String titulo, int largura, int altura){
	
	super(titulo, largura, altura);	
		
	this.setLayout( new GridBagLayout());
	this.setBackground(Configuracoes.preferencias.cor_fundo_janela);
	}
	
	

	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = 1;	
	cons.insets = new Insets(0, 5, 0, 5);
	this.bt_novo = new JButton(new ImageIcon(getClass().getResource("/icons/novo.png")));
	bt_novo.setToolTipText("Cadastrar novo item");
	this.motorDePesquisa.painel_menu_de_opcoes.add(bt_novo, cons);
	
	this.bt_alterar = new JButton(new ImageIcon(getClass().getResource("/icons/alterar.png")));
	bt_alterar.setToolTipText("Alterar item selecionado na tabela");
	this.motorDePesquisa.painel_menu_de_opcoes.add(bt_alterar, cons);

	this.bt_deletar = new JButton(new ImageIcon(getClass().getResource("/icons/remover.png")));
	bt_deletar.setToolTipText("Excluir item selecionado na tabela");
	this.motorDePesquisa.painel_menu_de_opcoes.add(bt_deletar, cons);
	
	
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(1, 1, 1, 1);
	
	this.add(this.motorDePesquisa, cons);
	

		motorDePesquisa.tabela.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount()==2)
		alterarForm();
		
		}}); 
	
		
		bt_novo.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		novoForm();	
		}});

		
		
		bt_alterar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		alterarForm();
		}});	

		

		bt_deletar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				  
		deletarForm();
		}});			
	}
	
	
	
	
	public abstract void novoForm();
	
	
	
	
	public abstract void alterarForm();
	
	
	
	
	public abstract void deletarForm();
	
	
}

