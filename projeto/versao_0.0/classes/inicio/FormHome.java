package inicio;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import pdv.FormPDVPrincipal;
import principal.FormPrincipal;
import componentes.janelas.Frame;
import comuns.Comuns;
import comuns.Configuracao;
import comuns.Mensagens;





public class FormHome extends Frame{
	

private static final long serialVersionUID = 1L;





private JPanel painel_fundo;

private int largura_total;



	public FormHome(){
	
    super("Markup - automação comercial", 
			    		GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
			   			GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
				
			   
    largura_total =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    
	
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
			  
			
				try {
				
				BufferedImage	imagem = ImageIO.read(getClass().getResource("/icons/bg_1.jpg"));	            
				g2.drawImage(imagem, 0, (altura - imagem.getHeight())/2,   imagem.getWidth(), imagem.getHeight(), null);
				
				
				BufferedImage imagem2 = ImageIO.read(getClass().getResource("/icons/markup.png"));	            
				g2.drawImage(imagem2, 5, altura -imagem2.getHeight()-5 ,   imagem2.getWidth(), imagem2.getHeight(), null);
				
				}
				catch (IOException e) {e.printStackTrace();} 
				            
			}   
		}, cons);
	
	
		
	cons.insets = new Insets(0, largura_total - 350, 0, 0);
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weighty =0;
	cons.weightx = 0;
	cons.ipadx =100;
	cons.ipady =10;	
	JButton bt_caixa = new JButton("Abrir Caixa (F1)", new ImageIcon(getClass().getResource("/icons/chave.png")));
	bt_caixa.setToolTipText("Abrir novo caixa ou retomar caixa aberto");
	bt_caixa.addKeyListener(new Ouvinte());
	painel_fundo.add(bt_caixa, cons);
		    
		
	
	cons.insets = new Insets(20, largura_total - 400, 0, 0);
	cons.ipadx =25;	
	JButton bt_inter = new JButton("Abrir Interface de Gestão (F2)", new ImageIcon(getClass().getResource("/icons/chave.png")));
	bt_inter.setToolTipText("Acesso à interface de gestão de venda");
	bt_inter.addKeyListener(new Ouvinte());
	
	painel_fundo.add(bt_inter, cons);
			    
	
	if(Configuracao.barraDeInformacoes==null)
	Configuracao.barraDeInformacoes = new BarraDeInformacoes();
	
	cons.insets = new Insets(0, 0, 0, 0);
	cons.ipadx =0;
	cons.ipady =0;	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty =0;
	cons.weightx = 1;
	//Configuracao.barraDeInformacoes.setSize(this.largura_total, 30);
	//Configuracao.barraDeInformacoes.setLocation(0, this.altura-Configuracao.barraDeInformacoes.getHeight());
	this.add(Configuracao.barraDeInformacoes, cons);	
		
	
	
	
		bt_caixa.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
			
		abrirCaixa();
		}});
	
	
	
		bt_inter.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		
		acessarInterfaceDeGestao();
		}});
		
		
	}






	
	private void abrirCaixa(){
		
		if(Comuns.validaParaAbrirCaixa()){	
			
		FormPDVPrincipal form = new FormPDVPrincipal();	
		form.mostrar();
		
		this.dispose();
		}	
	}
	
	
	
	
	
	
	private void acessarInterfaceDeGestao(){
		
	FormPrincipal form = new FormPrincipal();
	form.mostrar();
	
	this.dispose();
	}
	
	
	
	
	
	
	private void finalizar(){
		
	if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja sair?"))
	System.exit(0);  	
	}
	
	
	
	
	
	
	
	
	private class Ouvinte extends KeyAdapter{
		
		
		public void keyPressed(KeyEvent ek){
		
			
		if(ek.getKeyCode() == KeyEvent.VK_F1)
		abrirCaixa();
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F2)
		acessarInterfaceDeGestao();
	
		
		if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
		finalizar();
		}
		
	}
	
	
	
	
	
}
