
package classes.componentes.tabelas;



import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import classes.comuns.Configuracoes;
import anotacoes.Anot_TB_Coluna;




public class MotorDePesquisa<T> extends JPanel{


	

private static final long serialVersionUID = 1L;

		

public JTextField tf_termos;
public JButton bt_pesquisar;

public JButton bt_menu;


public JTable tabela;
public ModeloDeTabela<T> modelo;


public JLabel informacoes_de_tabela;

public JPanel painel_de_outras_opcoes_de_pesquisa;

public JPanel painel_menu_de_opcoes;


public JScrollPane scroll;





	public MotorDePesquisa(String titulo, Class<?> tipoDeClasse){
	
	this(titulo, tipoDeClasse, true);
	}
	

	
	
	
	public MotorDePesquisa(String titulo, Class<?> TipoDeclasse, boolean paginacao){
	
	this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), titulo));  
	this.setLayout(new GridBagLayout());
	this.setBackground(Configuracoes.preferencias.cor_fundo_painel_janela);
	
	
	this.modelo = new ModeloDeTabela<T>(TipoDeclasse, paginacao);
	
	this.adicionarComponentes();

		for (Field field : TipoDeclasse.getDeclaredFields()) {
		
		if (field.isAnnotationPresent(Anot_TB_Coluna.class))
		this.tabela.getColumnModel().getColumn(field.getAnnotation(Anot_TB_Coluna.class).posicao()).setPreferredWidth(field.getAnnotation(Anot_TB_Coluna.class).comprimento()*(1400/100));	
		}
	}
		

	
	
	
	
	public void adicionarComponentes(){
				    
	GridBagConstraints cons = new GridBagConstraints();   
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = 1;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.anchor = GridBagConstraints.WEST;
	cons.insets = new Insets(0, 0, 0, 0);
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Configuracoes.preferencias.cor_fundo_painel_janela);
	this.add(p1, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(0, 0, 0, 0);
	this.painel_de_outras_opcoes_de_pesquisa = new JPanel(new GridBagLayout());
	this.painel_de_outras_opcoes_de_pesquisa.setBackground(Configuracoes.preferencias.cor_fundo_painel_janela);	
	this.add(this.painel_de_outras_opcoes_de_pesquisa, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	
	cons.insets = new Insets(2, 0, 2, 0);
	JSeparator sepador = new JSeparator(SwingConstants.HORIZONTAL);
	this.add(sepador, cons);
	sepador.setVisible(false);
	
	cons.insets = new Insets(0, 0, 0, 0);
	this.painel_menu_de_opcoes = new JPanel(new GridBagLayout());
	this.painel_menu_de_opcoes.setBackground(Configuracoes.preferencias.cor_fundo_painel_janela);
	this.add(this.painel_menu_de_opcoes, cons);
	this.painel_menu_de_opcoes.setBorder(BorderFactory.createTitledBorder("Opções"));  
	this.painel_menu_de_opcoes.setVisible(false);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel(""), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("Termos para Pesquisa:"), cons);
	
	cons.gridwidth = 1;
	cons.insets = new Insets(0, 0, 0, 2);
	this.bt_menu = new JButton(new ImageIcon(getClass().getResource("/icons/menu.png")));
	p1.add(this.bt_menu, cons);
	this.bt_menu.setToolTipText("Abrir menu de opções");
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	this.tf_termos = new JTextField();
	p1.add(this.tf_termos, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.ipadx = 10;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.bt_pesquisar = new JButton("Pesquisar", new ImageIcon(getClass().getResource("/icons/pesquisa.png")));
	p1.add(this.bt_pesquisar, cons);
	this.bt_pesquisar.setToolTipText("Iniciar pesquisa baseada nos termos informados");
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 1;
	cons.weightx = 1;	
	cons.ipadx = 0;
	cons.insets = new Insets(2, 1, 0, 0);
	this.tabela = new JTable( modelo);
	this.tabela.setRowHeight(20);
	
	try {this.tabela.setDefaultRenderer( Class.forName( "java.lang.String" ), new ConstrutorDeCelulaDeTabela(this.modelo.getTipo()));}
	catch (ClassNotFoundException e) {e.printStackTrace();}
	
	
	scroll = new JScrollPane(this.tabela, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	this.add(scroll, cons);
	this.tabela.getParent().setBackground(new Color(221, 221, 221 ));	
	
	
	DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
	headerRenderer.setBackground(Configuracoes.preferencias.tab_cor_fundo_cabecalho);
	headerRenderer.setForeground(Configuracoes.preferencias.tab_cor_fonte_cabecalho);
	headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	
	for (int i = 0; i < this.tabela.getModel().getColumnCount(); i++){
	this.tabela.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
	}
	
	
	this.tabela.getTableHeader().setBorder(javax.swing.BorderFactory.createEtchedBorder());
	
	
    this.informacoes_de_tabela = new JLabel("");
    
    
    
    	if(this.modelo.paginacao){
    		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.insets = new Insets(2, 0, 0, -1);
			JPanel panel = new JPanel(){
			
			private static final long serialVersionUID = 1L;
	
			@Override   
	        public void paintComponent(Graphics g){    
	      
			
			Graphics2D g2 = (Graphics2D) g.create();	
	
			//GradientPaint paint = new GradientPaint(  getWidth()/2, 0, new Color(79, 149, 209),  getWidth()/2,  getHeight(), Color.black);					
			GradientPaint paint = new GradientPaint(  getWidth()/2, 0, new Color(255, 101, 55),  getWidth()/2,  getHeight(), new Color(201, 1, 42));					
			
			g2.setPaint( paint);
			g2.fillRect( 0 , 0 ,  getWidth(),  getHeight());		
	        }};
		panel.setLayout(new GridBagLayout());
		this.add(panel, cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = 1;
		cons.weighty  = 0;
		cons.weightx = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		JButton bt_prev = new JButton(new ImageIcon(getClass().getResource("/icons/anterior.png")));
		bt_prev.setToolTipText("Página Anterior");
		panel.add(bt_prev, cons);
		
		JButton bt_pri_page = new JButton(new ImageIcon(getClass().getResource("/icons/primeira_pag.png")));
		bt_pri_page.setToolTipText("Primeira Página");
		panel.add(bt_pri_page, cons);
		
		cons.insets = new Insets(0, 5, 0, 5);
		panel.add(informacoes_de_tabela, cons);
	
		cons.insets = new Insets(0, 0, 0, 0);
		JButton bt_utl_page = new JButton(new ImageIcon(getClass().getResource("/icons/ultima_pag.png")));
		bt_utl_page.setToolTipText("Última Página");
		panel.add(bt_utl_page, cons);
		
		JButton bt_next = new JButton(new ImageIcon(getClass().getResource("/icons/proximo.png")));
		bt_next.setToolTipText("Próxima Página");
		panel.add(bt_next, cons);
    	
    	
		bt_prev.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		paginaAnterior();
		}});
		
			
			
		bt_pri_page.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		primeiraPagina();
		}});

			
			
		bt_utl_page.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		ultimaPagina();
		}});

			
			
		bt_next.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		proximaPagina();	
		}});
    	
    	}
	
    	
    	
    	
		bt_pesquisar.addActionListener( new ActionListener(){
		public void actionPerformed( ActionEvent event ){
			    	
		pesquisa();
		}});
	
		
		
		
		
		this.bt_menu.addActionListener( new ActionListener(){
		public void actionPerformed( ActionEvent event ){
				    	
			if(painel_menu_de_opcoes.isVisible()){
			painel_menu_de_opcoes.setVisible(false);
			sepador.setVisible(false);
			}
			else{
			painel_menu_de_opcoes.setVisible(true);	
			sepador.setVisible(true);
			}
		}});
		

		
		
		this.tf_termos.addActionListener(new ActionListener (){
		@Override 	
		public void actionPerformed(ActionEvent e) {  
			
		pesquisa();
		}});
	}
	
	
	
	
	
	
	public void atualizar(){
			
	this.modelo.limpaPesquisa();
	this.setInfoTable();
	}
	
	
	
	
	
	public void pesquisa() {
			
	this.modelo.pesquisa(this.tf_termos.getText());	
	this.setInfoTable();
	}
	
	
	

	
	public void proximaPagina(){
		
	this.modelo.proximaPagina(this.tf_termos.getText());	
	this.setInfoTable();
	}
		


	
		
	public void paginaAnterior(){
		
	this.modelo.paginaAnterior(this.tf_termos.getText());	
	this.setInfoTable();
	}
			
		
		
	
	
	
	public void primeiraPagina(){
		
	this.modelo.primeiraPagina(this.tf_termos.getText());	
	this.setInfoTable();
	}
				
	
	
	
		
		
	public void ultimaPagina(){
		
	this.modelo.ultimaPagina(this.tf_termos.getText());	
	this.setInfoTable();
	}
	
	
	
	
	
	
	public void setInfoTable(){
	
	this.informacoes_de_tabela.setText("<html><font color=white><b>Mostrando "+this.modelo.currentIndex+" à "+(this.modelo.currentIndex+this.modelo.getRowCount())+" de "+this.modelo.countItens+" iten(s)</b></font></html>");	
	}
	
	
	
}		