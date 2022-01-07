
package classes.componentes.janelas;


import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.componentes.Painel;
import classes.comuns.Configuracoes;





public abstract class Dialogo extends JDialog implements Windows{

	
private static final long serialVersionUID = 1L;




	public Dialogo( String title, int width, int height){
	
	super();
	
	this.setTitle(title);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(width , height);
	this.setLocationRelativeTo(null);
	this.setLayout(new GridBagLayout());
	this.setModal(true);
	this.getContentPane().setBackground(Configuracoes.preferencias.cor_fundo_janela);  
	
	try {setIconImage(ImageIO.read(getClass().getResource("/icons/favicon.png")));} catch (IOException e) {e.printStackTrace();} 
	
	}

	
	
	
	
	public abstract void adicionarComponentes();
	
	
	
	


	@Override
	public void mostrar() {
	
	this.addOuvinte(this);			
	
	this.getContentPane().setBackground(Configuracoes.preferencias.cor_fundo_janela);  
	
	this.setVisible(true);
	}



	
	@Override
	public void esconder() {
		
	this.setVisible(false);
	}
	
	
	
	
	private void addOuvinte(Container container) {  
		
	for (Component c : container.getComponents())       
	addOuvinte((Container)c); 

		if(container instanceof Painel){
			
		Painel p = (Painel) container;
		
		if(p.preferencias_personalizadas)
		return;
		}
	
	
	if(container instanceof JPanel)
	container.setBackground(Configuracoes.preferencias.cor_fundo_painel_janela);
	
		
		if(container instanceof JLabel){
		
		container.setForeground(Configuracoes.preferencias.cor_fonte_janela);	
		container.setFont(Configuracoes.preferencias.fonte_janela );
		}
		
	//container.addKeyListener(new OuvinteTecladoDialogos(this));
	} 

	
	
	
	
	public abstract boolean acaoPrincipal();
	
	
	
	
	
	
	
	
}
