package componentes.janelas;


import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

import comuns.Preferencias;




public abstract class Dialogo extends JDialog implements Windows{

	
private static final long serialVersionUID = 1L;


private boolean ouvinte_padrao;



	public Dialogo( String title, int width, int height){
	
	this(  title,  width,  height, true);
	}

	
	
	
	public Dialogo( String title, int width, int height, boolean ouvinte_padrao){
		
	super();
		
	this.setTitle(title);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(width , height);
	this.setLocationRelativeTo(null);
	this.setLayout(new GridBagLayout());
	this.setModal(true);
	this.getContentPane().setBackground(Preferencias.COR_DE_FUNDO_FRAME);  

	try {setIconImage(ImageIO.read(getClass().getResource("/icons/favicon.png")));} catch (IOException e) {e.printStackTrace();} 
	
	this.ouvinte_padrao=  ouvinte_padrao;
	}
	
	
	
	
	
	
	
	public abstract void adicionarComponentes();
	
	
	
	


	@Override
	public void mostrar() {
	
	if(ouvinte_padrao)
	this.addOuvinte(this);			
	
	this.setVisible(true);
	}



	
	@Override
	public void esconder() {
		
	this.setVisible(false);
	}
	
	
	
	
	private void addOuvinte(Container container) {  
		
	for (Component c : container.getComponents())       
	addOuvinte((Container)c); 
		
	container.addKeyListener(new OuvinteTecladoDialogos(this));
	} 

	
	
	
	
	public abstract boolean acaoPrincipal();
	
	
	
	
	
	
	
	
}
