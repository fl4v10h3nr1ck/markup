package classes.principal;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import classes.componentes.janelas.Janela;
import classes.compras.GestaoDeCompras;
import classes.comuns.Configuracoes;
import classes.estoque.GestaoDeEstoque;
import classes.financeiro.formas_de_pagamento.GestaoDeFormasDePagamento;
import classes.principal.empresa.FormEmpresa;




public class Principal extends Janela{

	
	
	
private static final long serialVersionUID = 1L;





	public Principal() {
		
	super("Markup - principal", 
					GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
					GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
	
	

		addWindowListener(new WindowAdapter(){  
	    public void windowClosing (WindowEvent e){  
	    
	    System.exit(0);  	           
	    }});  


	this.getContentPane().setBackground(Configuracoes.preferencias.cor_fundo_janela);	
	
	adicionarComponentes();
	}

	
	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 0;
	cons.weightx = 0;
	
	cons.ipadx = 20;
	cons.ipady = 20;
	cons.insets = new Insets(0, 0, 0, 0);
	
	JButton estoque = new JButton("Estoque");
	this.add(estoque, cons);
	
	
	JButton empresa = new JButton("Empresa");
	this.add(empresa, cons);
	
	JButton compras = new JButton("Compras");
	this.add(compras, cons);
	

	JButton f_pag = new JButton("Formas de pagamento");
	this.add(f_pag, cons);
	
	
	
		estoque.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		GestaoDeEstoque form = new GestaoDeEstoque();
		form.mostrar();
		}});	
		
		
		empresa.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					
		FormEmpresa form = new FormEmpresa();
		form.mostrar();
		}});
		
		
		
		compras.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						
		GestaoDeCompras form = new GestaoDeCompras();
		form.mostrar();
		}});
		
		
		
		f_pag.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						
		GestaoDeFormasDePagamento form = new GestaoDeFormasDePagamento();
		form.mostrar();
		}});
	}
}
