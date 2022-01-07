package classes.componentes;

import javax.swing.JPanel;



public abstract class Painel extends JPanel{


private static final long serialVersionUID = 1L;

	
public boolean preferencias_personalizadas;


	
	Painel(){
	
	this(false);
	}


	Painel(boolean preferencias_personalizadas){
		
	this.preferencias_personalizadas = preferencias_personalizadas;
	}



}
