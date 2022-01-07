package principal;



import inicio.BarraDeInformacoes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;






import pagueOaluguel.PagueOAluguel;
import principal.caixa.GestaoDeCaixas;
import principal.clientes.GestãoDeClientes;
import principal.colaboradores.GestaoDeColaboradores;
import principal.compras.GestaoDeCompras;
import principal.convenios.GestaoDeConvenios;
import principal.credores.GestaoDeCredores;
import principal.despesas.GestaoDeDespesas;
import principal.empresa.FormEmpresa;
import principal.empresa.FormSobre;
import principal.entrada_saida.GestaoDeEntrada_Saida;
import principal.estoque.GestaoDeEstoque;
import principal.forma_de_pagamento.GestaoDeFormasDePagamento;
import principal.fornecedores.GestaoDeFornecedores;
import principal.inventario.GestaoDeInventario;
import voceNaoVaiPassar.VoceNaoVaiPassar;
import componentes.janelas.Frame;
import comuns.Configuracao;





public class FormPrincipal extends Frame{
	

private static final long serialVersionUID = 1L;



private JPanel painel_fundo;




	public FormPrincipal(){
	
    super("Markup - automação comercial", 
			    		GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
			   			GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
				
 
	
    	addWindowListener(new WindowAdapter(){  
        public void windowClosing (WindowEvent e){  
        
        
        System.exit(0);  	           
        }});  
  
    
    
    adicionarComponentes();
	}
	
	
	
	
	
	
	public void adicionarComponentes(){
		
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
	
	
		this.add(this.painel_fundo = new JPanel(new GridBagLayout()){
	
	
		private static final long serialVersionUID = 1L;
	
	
		
			public void paintComponent(Graphics g){
				
			super.paintComponent(g);
				
			Graphics2D g2 = (Graphics2D) g.create();	
			  	    	
			int altura 		      = this.getHeight();
			int comprimento       = this.getWidth();
			
			
			g2.setPaint(Color.white);  
			g2.fillRect(0,0,comprimento,altura);
			  
			/*
				try {
				
				BufferedImage	imagem = ImageIO.read(getClass().getResource("/icons/bg_1.jpg"));	            
				g2.drawImage(imagem, 0, (altura - imagem.getHeight())/2,   imagem.getWidth(), imagem.getHeight(), null);
				
				
				BufferedImage imagem2 = ImageIO.read(getClass().getResource("/icons/markup.png"));	            
				g2.drawImage(imagem2, 5, altura -imagem2.getHeight()-5 ,   imagem2.getWidth(), imagem2.getHeight(), null);
				
				}
				catch (IOException e) {e.printStackTrace();} 
				*/            
			}   
		}, cons);
	
	
	cons.gridwidth  = 1;	
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2 );
	JPanel painel_fundo_esquerdo = new JPanel(new GridBagLayout()){
	
	
		private static final long serialVersionUID = 1L;
	
	
			public void paintComponent(Graphics g){
				
			super.paintComponent(g);
				
			Graphics2D g2 = (Graphics2D) g.create();	
			  	    	
			int altura 		      = this.getHeight();
			int comprimento       = this.getWidth();
			
			
			g2.setPaint(Color.white);  
			g2.fillRect(0,0,comprimento,altura);
			  
			
				try {
					
				BufferedImage	imagem = ImageIO.read(getClass().getResource("/icons/logo_cliente.jpg"));	            
				g2.drawImage(imagem, (comprimento - imagem.getWidth())/2, (altura - imagem.getHeight())/2,   imagem.getWidth(), imagem.getHeight(), null);	
				}
				catch (IOException e) {e.printStackTrace();} 
			          
			}
		};
	this.painel_fundo.add(painel_fundo_esquerdo, cons);
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0;	
	cons.insets = new Insets(2, 0, 2, 2 );
	JPanel painel_fundo_direito = new JPanel(new GridBagLayout());
	painel_fundo_direito.setBackground(Color.GRAY);
	this.painel_fundo.add(painel_fundo_direito, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(0, 0, 0, 0 );
	this.add(Configuracao.barraDeInformacoes = new BarraDeInformacoes(), cons);
	
	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.fill = GridBagConstraints.NONE;
	cons.weighty =0;
	cons.weightx = 0;
	cons.ipadx =0;
	
	
	/**    1 linha **/
	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = 1;	
	JButton bt_clientes = new JButton(new ImageIcon(getClass().getResource("/icons/clientes.png")));
	bt_clientes.setToolTipText("Gestão de clientes.");
	painel_fundo_direito.add(bt_clientes, cons);
	
	
	JButton bt_credores = new JButton(new ImageIcon(getClass().getResource("/icons/credores_30.png")));
	bt_credores.setToolTipText("Gestão de credores.");
	painel_fundo_direito.add(bt_credores, cons);
	
	JButton bt_fornecedores = new JButton(new ImageIcon(getClass().getResource("/icons/fornecedores.png")));
	bt_fornecedores.setToolTipText("Gestão de fornecedores.");
	painel_fundo_direito.add(bt_fornecedores, cons);
	
	
	JButton bt_colaboradores = new JButton(new ImageIcon(getClass().getResource("/icons/colaboradores.png")));
	bt_colaboradores.setToolTipText("Gestão de colaboradores.");
	painel_fundo_direito.add(bt_colaboradores, cons);
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	cons.insets = new Insets(0, 20, 20, 20);
	cons.gridwidth  = 1;
	painel_fundo_direito.add(new JLabel("Clientes"), cons);
	painel_fundo_direito.add(new JLabel("Credores"), cons);
	painel_fundo_direito.add(new JLabel("Fornecedores"), cons);
	painel_fundo_direito.add(new JLabel("Colaboradores"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	/**    2 linha **/
	
	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = 1;
	JButton bt_caixas = new JButton(new ImageIcon(getClass().getResource("/icons/caixa.png")));
	bt_caixas.setToolTipText("Gestão de caixas.");
	painel_fundo_direito.add(bt_caixas, cons);
	
	JButton bt_estoque = new JButton(new ImageIcon(getClass().getResource("/icons/estoque.png")));
	bt_estoque.setToolTipText("Gestão de estoque.");
	painel_fundo_direito.add(bt_estoque, cons);
	
	JButton bt_inventario = new JButton(new ImageIcon(getClass().getResource("/icons/inventario.png")));
	bt_inventario.setToolTipText("Gestão de inventário.");
	painel_fundo_direito.add(bt_inventario, cons);
	
	
	painel_fundo_direito.add(new JLabel(""), cons);
	painel_fundo_direito.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	cons.insets = new Insets(0, 20, 20, 20);
	cons.gridwidth  = 1;
	painel_fundo_direito.add(new JLabel("Caixas"), cons);
	painel_fundo_direito.add(new JLabel("Estoque"), cons);
	painel_fundo_direito.add(new JLabel("Inventário"), cons);
	painel_fundo_direito.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	
	
	/**    3 linha **/
	

	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = 1;	
	JButton bt_compras = new JButton(new ImageIcon(getClass().getResource("/icons/compra.png")));
	bt_compras.setToolTipText("Gestão de compras.");
	painel_fundo_direito.add(bt_compras, cons);
	
	JButton bt_despesas = new JButton(new ImageIcon(getClass().getResource("/icons/despesas.png")));
	bt_despesas.setToolTipText("Gestão de despesas.");
	painel_fundo_direito.add(bt_despesas, cons);
	
	JButton bt_convenios = new JButton(new ImageIcon(getClass().getResource("/icons/convenio.png")));
	bt_convenios.setToolTipText("Gestão de convênios.");
	painel_fundo_direito.add(bt_convenios, cons);
	
	
	JButton bt_f_pag = new JButton(new ImageIcon(getClass().getResource("/icons/f_pagamentos.png")));
	bt_f_pag.setToolTipText("Gestão de formas de pagamento.");
	painel_fundo_direito.add(bt_f_pag, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	cons.insets = new Insets(0, 20, 20, 20);
	cons.gridwidth  = 1;
	painel_fundo_direito.add(new JLabel("Compras"), cons);
	painel_fundo_direito.add(new JLabel("Despesas"), cons);
	painel_fundo_direito.add(new JLabel("Convênios"), cons);
	painel_fundo_direito.add(new JLabel("Form. Pag."), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	
	/**    4 linha **/
	

	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = 1;	
	JButton bt_pagamentos = new JButton(new ImageIcon(getClass().getResource("/icons/pagamentos.png")));
	bt_pagamentos.setToolTipText("Gestão de pagamentos.");
	painel_fundo_direito.add(bt_pagamentos, cons);
	
	JButton bt_recebimentos = new JButton(new ImageIcon(getClass().getResource("/icons/recebimentos.png")));
	bt_recebimentos.setToolTipText("Gestão de recebimentos.");
	painel_fundo_direito.add(bt_recebimentos, cons);
	
	painel_fundo_direito.add(new JLabel(""), cons);
	painel_fundo_direito.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	cons.insets = new Insets(0, 20, 20, 20);
	cons.gridwidth  = 1;
	painel_fundo_direito.add(new JLabel("Pagamentos"), cons);
	painel_fundo_direito.add(new JLabel("Recebimentos"), cons);
	painel_fundo_direito.add(new JLabel(""), cons);
	painel_fundo_direito.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
/**    5 linha **/
	

	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.gridwidth  = 1;
	
	JButton btUsuarios = new JButton(new ImageIcon(getClass().getResource("/icons/bt_add_user.png")));
	btUsuarios.setToolTipText("Gerenciar Usuários"); 
	painel_fundo_direito.add(btUsuarios, cons);	

	JButton bt_unidade = new JButton(new ImageIcon(getClass().getResource("/icons/icon_empresa.png")));
	bt_unidade.setToolTipText("Informações da unidade empresarial.");
	painel_fundo_direito.add(bt_unidade, cons);
	
	JButton bt_registro = new JButton(new ImageIcon(getClass().getResource("/icons/registro.png")));
	bt_registro.setToolTipText("Gestão de registro e licença de uso de software");
	painel_fundo_direito.add(bt_registro, cons);
	
	JButton bt_sobre = new JButton(new ImageIcon(getClass().getResource("/icons/icon_sobre.png")));
	bt_sobre.setToolTipText("Informações do Sistema.");
	painel_fundo_direito.add(bt_sobre, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	cons.insets = new Insets(0, 20, 20, 20);
	cons.gridwidth  = 1;
	painel_fundo_direito.add(new JLabel("Usuários"), cons);
	painel_fundo_direito.add(new JLabel("Unidade"), cons);
	painel_fundo_direito.add(new JLabel("Licença"), cons);
	painel_fundo_direito.add(new JLabel("Sobre"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	painel_fundo_direito.add(new JLabel(""), cons);
	
	
	
	
	
	
	
	
		bt_clientes.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		GestãoDeClientes form = new GestãoDeClientes();
		form.mostrar();
		}});
		
		
		bt_credores.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		GestaoDeCredores form = new GestaoDeCredores();
		form.mostrar();
		}});
		
		
		bt_fornecedores.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					
		GestaoDeFornecedores form = new GestaoDeFornecedores();
		form.mostrar();
		}});
	
	
		bt_colaboradores.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						
		GestaoDeColaboradores form = new GestaoDeColaboradores();
		form.mostrar();
		}});
		
		
		
		
		
		
	
		bt_caixas.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		GestaoDeCaixas form = new GestaoDeCaixas();
		form.mostrar();
		}});
	
	
		bt_estoque.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						
		GestaoDeEstoque form = new GestaoDeEstoque();
		form.mostrar();
		}});
	
	
		bt_inventario.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		GestaoDeInventario form = new GestaoDeInventario();
		form.mostrar();
		}});
	
		
		
		
		
		bt_compras.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		GestaoDeCompras form = new GestaoDeCompras();
		form.mostrar();
		}});
		
		
		bt_despesas.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
								
		GestaoDeDespesas form = new GestaoDeDespesas();
		form.mostrar();
		}});
		
		
		bt_convenios.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
										
		GestaoDeConvenios form = new GestaoDeConvenios();
		form.mostrar();
		}});
		
		
		bt_f_pag.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
											
		GestaoDeFormasDePagamento form = new GestaoDeFormasDePagamento();
		form.mostrar();
		}});
		
		
		
		
		
		
		
		
		bt_pagamentos.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
									
		GestaoDeEntrada_Saida form = new GestaoDeEntrada_Saida(0);
		form.mostrar();
		}});
		
		
		bt_recebimentos.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
									
		GestaoDeEntrada_Saida form = new GestaoDeEntrada_Saida(1);
		form.mostrar();
		}});
		
		
		
		
		
		
		
		btUsuarios.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		VoceNaoVaiPassar form = new VoceNaoVaiPassar(Configuracao.connector);
		form.formGestaoDeUsuarios(Configuracao.usuarioAtual);	
		}});
		

		bt_sobre.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
										
		FormSobre form = new FormSobre();
		form.mostrar();
		}});
			
		
		bt_registro.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		PagueOAluguel registro = new PagueOAluguel();
		registro.showRegisterForm(Configuracao.currentRecord);
			
		Configuracao.barraDeInformacoes.atualizaInfo();
		}});
		
		
		bt_unidade.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
										
		FormEmpresa form = new FormEmpresa();
		form.mostrar();
		}});
			
	
	
	}


	
	


	
	
}
