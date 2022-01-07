package principal.empresa;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentes.janelas.Dialogo;
import comuns.Configuracao;






public class FormSobre extends Dialogo{

	


private static final long serialVersionUID = 1L;



	public FormSobre() {
		
	super("Informações do Sistema", 550, 300);
	
	this.adicionarComponentes();
	}

	
	
	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  
	cons.fill = GridBagConstraints.BOTH;
	cons.weightx = 0.4;
	cons.weighty = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Color.WHITE);
	this.add(p1, cons);
	
	cons.weightx = 0.6;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	JPanel p2 = new JPanel(new GridBagLayout());
	this.add(p2, cons);
	
	cons.weighty = 0;
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.insets = new Insets(0, 0, 10, 0);
	cons.anchor = GridBagConstraints.CENTER;
	p1.add(new JLabel(new ImageIcon(getClass().getResource("/icons/logo_msc.png"))), cons);	
	
	final JLabel link  = new JLabel("www.mscsolucoes.com.br");
	p1.add(link, cons);			
	
	cons.weighty = 0;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(0, 5, 5, 0);
	cons.anchor = GridBagConstraints.CENTER;
	p2.add(new JLabel("<html><b>Software: </b>MARCKUP</html>"), cons);	
	p2.add(new JLabel("<html><b>Versão: </b>1.0</html>"), cons);	
	p2.add(new JLabel("<html><b>Compilação: </b>"+Configuracao.num_compilacao+"</html>"), cons);	
	p2.add(new JLabel("<html><b>Sobre: </b>Sistema de Automação comercial</html>"), cons);	
	p2.add(new JLabel("<html><b>Software Code: </b>4910</html>"), cons);	
	p2.add(new JLabel("<html><b>Resp. Técnico: </b>Eng. Flávio H. P. Sousa</html>"), cons);	
	p2.add(new JLabel("<html><b>CPNJ: </b>21.614.991/0001-72</html>"), cons);	
	
	
	
		link.addMouseListener( new MouseAdapter(){
			
		@Override
		public void mouseClicked(MouseEvent e) {
			
		Desktop desk = java.awt.Desktop.getDesktop();       
		try {desk.browse(new java.net.URI( "http://"+link.getText()));}catch (Exception ex) {System.out.print("erro");}
		}
		
		
		@Override
		public void mouseEntered(MouseEvent evt) {  
		
		link.setForeground(Color.BLUE);
		}  
		
		@Override     
		public void mouseExited(MouseEvent evt) {  
		
		link.setForeground(Color.BLACK);	
	    }   

		});
	
		
		
		
	}



	@Override
	public boolean acaoPrincipal() {return false;}

	
	
	
}
