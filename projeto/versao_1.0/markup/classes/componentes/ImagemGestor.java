package classes.componentes;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import classes.comuns.Comuns;
import classes.comuns.Mensagens;




public class ImagemGestor extends JPanel{

	
	
private static final long serialVersionUID = 1L;


private File arquivo;

private Area_De_Imagem area;


private JButton bt_menos_zoom;
private JButton bt_mais_zoom;




	public ImagemGestor(){
		
	this(0, 0);
	}

	
		
	
	
	public ImagemGestor(int comprimento, int lagura){
					
	this.setLayout(null);
	
	this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	
	this.area = new Area_De_Imagem(comprimento, lagura);
	this.add(area);

	JButton bt_carregar = new JButton(new ImageIcon(getClass().getResource("/icons/upload.png"))); 
	bt_carregar.setToolTipText("Carregar imagem do produto");
	bt_carregar.setSize(45, 28);
	bt_carregar.setLocation(this.area.getX(), this.area.getY()+this.area.getHeight());
	this.add(bt_carregar);
	
	JButton bt_remover = new JButton(new ImageIcon(getClass().getResource("/icons/remover.png"))); 
	bt_remover.setToolTipText("Remover imagem do produto");
	bt_remover.setSize(45, 28);
	bt_remover.setLocation(bt_carregar.getX()+bt_carregar.getWidth()+2, bt_carregar.getY());
	this.add(bt_remover);
	
	JButton bt_ajuda = new JButton(new ImageIcon(getClass().getResource("/icons/ajuda.png"))); 
	bt_ajuda.setToolTipText("Ajuda sobre corregamento de imagens.");
	bt_ajuda.setSize(45, 28);
	bt_ajuda.setLocation(bt_remover.getX()+bt_remover.getWidth()+2, bt_carregar.getY());
	this.add(bt_ajuda);
	
	
	bt_mais_zoom = new JButton(new ImageIcon(getClass().getResource("/icons/mais.png"))); 
	bt_mais_zoom.setToolTipText("Aumentar imagem em proporção.");
	bt_mais_zoom.setSize(45, 28);
	bt_mais_zoom.setLocation(this.area.getX()+this.area.getWidth()+2, this.area.getY());
	bt_mais_zoom.setEnabled(false);
	this.add(bt_mais_zoom);
	
	bt_menos_zoom = new JButton(new ImageIcon(getClass().getResource("/icons/menos.png"))); 
	bt_menos_zoom.setToolTipText("Diminuir imagem em proporção.");
	bt_menos_zoom.setSize(45, 28);
	bt_menos_zoom.setLocation(this.bt_mais_zoom.getX(), this.bt_mais_zoom.getY()+this.bt_mais_zoom.getHeight()+2);
	bt_menos_zoom.setEnabled(false);
	this.add(bt_menos_zoom);
	
	
	
		bt_ajuda.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		Mensagens.msgDeInformação(" - O sistema fará uma cópia da imagem, não usuará a imagem original.\n - Imagens maiores que 200x200 pixels serão recortadas.\n - Arraste a imagem carregada pela área de corte pra melhor posicioná-la.\n - Extensões de imagens permitidas: JPG, PNG e GIF.");
		}});
		
		
		
		bt_remover.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		removeImagem();
			
		area.repaint();
		}});
	
		
			
		bt_carregar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		selecionarArquivo();
		}});
	
	

		
		bt_menos_zoom.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		area.addMenosZoom();
		}});
	
	
		
		bt_mais_zoom.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		area.addMaisZoom();
		}});
	
	}
	
	
	
	
	
	
	
	private void selecionarArquivo(){
		
	JFileChooser fc = new JFileChooser();
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		fc.setFileFilter(new javax.swing.filechooser.FileFilter(){
			      
			public boolean accept(File f){
			    
			if (f.isDirectory()) {return true;}	
			
			
			return  f.getName().toLowerCase().endsWith(".jpg") || 
				    f.getName().toLowerCase().endsWith(".png") ||
					f.getName().toLowerCase().endsWith(".gif") ||
					f.getName().toLowerCase().endsWith(".JPG") || 
					f.getName().toLowerCase().endsWith(".PNG") ||
					f.getName().toLowerCase().endsWith(".GIF");
			}

			public String getDescription() {
			
			return "Arquivos de imagens (.jpg, .png ou .gif)";
			}
		});
			
	int returnVal = fc.showOpenDialog(this);

	if (returnVal == JFileChooser.APPROVE_OPTION)
	this.addImagem(fc.getSelectedFile());	
	}
	
	
	
	
	
	private void removeImagem(){
		
	area.img_carregada = false;
	
	area.posicao_inicial = true;
	
	arquivo = null;	
	
	this.bt_menos_zoom.setEnabled(false);
	
	this.bt_mais_zoom.setEnabled(false);
	
	this.area.zoom = 1;
	}
	
	
	
	
	
	private void setValoresIniciais(){
		
	area.img_carregada = true;
		
	area.posicao_inicial = true;
		
	this.bt_menos_zoom.setEnabled(true);
		
	this.bt_mais_zoom.setEnabled(true);
		
	this.area.zoom = 1;	
	}
	
	
	
	
	
	
	public void addImagem(File arquivo){
		
		if(arquivo!=null && arquivo.exists()){
		
		this.arquivo = arquivo;
					
		this.setValoresIniciais();
			
		this.area.repaint();	
		}		
	}
	
	
	
	
	
	
	public String salvaImagem(){
	
		if(this.area.img_carregada && 
				this.arquivo!=null && 
					this.arquivo.exists() &&
					Comuns.config_do_sistema.getPath_imgs_produtos()!=null &&
						Comuns.config_do_sistema.getPath_imgs_produtos().length()>0){

		BufferedImage imagem = this.area.getImagem();
		
		Random gerador = new Random();
	    
		String nome = 
					  Comuns.addPaddingAEsquerda(""+gerador.nextInt(9999), 4, "0")+
					  Comuns.addPaddingAEsquerda(""+gerador.nextInt(9999), 4, "0")+
				      Comuns.addPaddingAEsquerda(""+gerador.nextInt(9999), 4, "0")+
				      Comuns.addPaddingAEsquerda(""+gerador.nextInt(9999), 4, "0")+
				      Comuns.addPaddingAEsquerda(""+gerador.nextInt(9999), 4, "0")+".jpg";
		
		try {
		ImageIO.write(imagem, "JPG", new File(Comuns.config_do_sistema.getPath_imgs_produtos()+nome));} 
			catch (IOException e) {
			e.printStackTrace();
			return null;
			}
		
		return nome;
		}
	
	return null;
	}
	
	
	
	
	
	
	private class Area_De_Imagem extends Painel{
	
	private static final long serialVersionUID = 1L;

	private int posicao_img_atual_x;
	private int posicao_img_atual_y;
	
	protected int posicao_mouse_atual_x;
	protected int posicao_mouse_atual_y;

	protected int posicao_mouse_nova_x;
	protected int posicao_mouse_nova_y;

	protected boolean posicao_inicial;
	
	protected boolean img_carregada;
	
	
	private int comprimento;
	private int altura;
	
	private int comprimento_imagem_total;
	
	private int altura_imagem_total;
	
	private float zoom;

	
	
	
		public Area_De_Imagem(int comprimento, int altura){
	
		super(true);	
		
		this.setBackground(Color.white);
		
		if(comprimento<150)
		comprimento = 150;
		
		this.comprimento = comprimento;
		
		if(altura<150)
		altura = 150;
		
		this.altura = altura;
		
		this.setSize(this.comprimento, this.altura);
		this.setLocation(5, 5);
		
		this.posicao_mouse_atual_x = 0;
		this.posicao_mouse_atual_y = 0;
		this.posicao_mouse_nova_x = 0;
		this.posicao_mouse_nova_y = 0;
		this.posicao_img_atual_x = 0;
		this.posicao_img_atual_y = 0;
		this.comprimento_imagem_total = 0;
		this.altura_imagem_total = 0;
		
		this.zoom = 1;
		
		this.posicao_inicial = true;
		
		this.img_carregada = false;
		
		
	
			this.addMouseListener(new MouseAdapter() {
			@Override
	        public void mousePressed(MouseEvent e) {
	        
				if(img_carregada){	
					
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				area.posicao_mouse_atual_x = e.getX();
				area.posicao_mouse_atual_y = e.getY();
				}
			}
			
			
			@Override
	        public void mouseReleased(MouseEvent e) {
	         
			if(img_carregada)	
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}});
		
		
			this.addMouseMotionListener(new MouseAdapter(){
	        @Override
	        public void mouseDragged(MouseEvent e) {
	
	        	if(img_carregada){	
	        	
		        if(area.posicao_inicial)
		        area.posicao_inicial = false;
		        	
		        area.posicao_mouse_nova_x= e.getX();
		        area.posicao_mouse_nova_y= e.getY();
		    		
		    	e.getComponent().repaint();
	        	}
	        }});
		}
	
	
	

	
		@Override
	    public void paintComponent(Graphics g) {
	       
		super.paintComponent(g);
		
			try { 

				if(!img_carregada){
				
				BufferedImage	imagem = ImageIO.read(getClass().getResource("/icons/foto.png"));	            		
				g.drawImage(imagem, (this.comprimento-imagem.getWidth())/2, (this.altura-imagem.getHeight())/2,  imagem.getWidth(), imagem.getHeight(), null);		
				
				}
				else{
					
				BufferedImage	imagem = null;
					
				if(arquivo!=null && arquivo.exists())	
				imagem = ImageIO.read(arquivo);	            
					else{
					
					removeImagem();	
						
					imagem = ImageIO.read(getClass().getResource("/icons/foto.png"));	            		
					g.drawImage(imagem, (this.comprimento-imagem.getWidth())/2, (this.altura-imagem.getHeight())/2,  imagem.getWidth(), imagem.getHeight(), null);			
					
					Mensagens.msgDeErro("Arquivo de imagem não encontrado.");
					return;
					}
				
				this.comprimento_imagem_total = imagem.getWidth();
				this.altura_imagem_total = imagem.getHeight();
					
				int comprimento_imagem = (int) (this.comprimento_imagem_total*this.zoom);
				int altura_imagem = (int) (this.altura_imagem_total*this.zoom);
				
				
					if(posicao_inicial){		
					
					if(comprimento_imagem>comprimento)
					posicao_img_atual_x  = (int)((comprimento_imagem- comprimento)/2)*(-1);	
					else
					posicao_img_atual_x  = (int)((comprimento - comprimento_imagem)/2);
					
					
					if(altura_imagem>altura)
					posicao_img_atual_y  = (int)((altura_imagem- altura)/2)*(-1);	
					else
					posicao_img_atual_y  = (int)((altura - altura_imagem)/2);
					}
					else{
					
					if(comprimento_imagem<=comprimento)	
					posicao_img_atual_x  = (int)((comprimento - comprimento_imagem)/2);
						else{	
							
						posicao_img_atual_x += posicao_mouse_nova_x - posicao_mouse_atual_x;
						
						if(posicao_img_atual_x>0)
						posicao_img_atual_x = 0;
						
						if(posicao_img_atual_x< ((comprimento_imagem - comprimento)*(-1)))
						posicao_img_atual_x = (comprimento_imagem - comprimento)*(-1);
						}
					
					if(altura_imagem<=altura)
					posicao_img_atual_y  = (int)((altura - altura_imagem)/2);
						else{
						posicao_img_atual_y += posicao_mouse_nova_y - posicao_mouse_atual_y; 
						
						if(posicao_img_atual_y>0)
						posicao_img_atual_y =0;
	
						if(posicao_img_atual_y< ((altura_imagem - altura)*(-1)))
						posicao_img_atual_y = (altura_imagem - altura)*(-1);
						}
					}
						
				g.drawImage(imagem, this.posicao_img_atual_x, this.posicao_img_atual_y,  comprimento_imagem, altura_imagem, null);	
				}
			}
			catch (IOException e) {e.printStackTrace();}
	    }
	
	
	
	
		
		private void addMaisZoom(){
		
		if(this.zoom<1)
		this.zoom += 0.1;
		
		this.repaint();
		}
		
		
	
		
		
		
		private void addMenosZoom(){
		
		if(this.zoom>0.1)
		this.zoom -= 0.1;
		
		this.repaint();
		}
		
		
		
	
		
		
		
		private BufferedImage getImagem() {

		BufferedImage buffer = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);	
				
		Graphics g = buffer.createGraphics();

		SwingUtilities.paintComponent(g, this, new JFrame().getContentPane(), 0, 0, buffer.getWidth(), buffer.getHeight());

		g.dispose();
		
		return buffer;
		}
	
	
	
	}	







}
