package componentes;

import javax.swing.JLabel;

import comuns.Preferencias;




public class Rotulo extends JLabel{


private static final long serialVersionUID = 1L;

	
	public Rotulo(String texto){
		
	this.setText(texto);
	this.setForeground(Preferencias.COR_DE_ROTULO);
	}
	
	
	
	
}
