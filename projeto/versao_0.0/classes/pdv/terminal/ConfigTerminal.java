package pdv.terminal;

import java.util.Random;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import componentes.beans.Item;
import comuns.Comuns;



public class ConfigTerminal {

	
public  final String COD_TERMINAL_PATH = "markup_terminal_info";		
public  final String COD_TERMINAL_CHAVE = "codterminal";		
public  final String COD_TERMINAL_NOME = "nometerminal";		
	



	
	public Item getCredenciais(){
	
	Item item = new Item();	
	Preferences preferences	= null;
		
		try {
				
		preferences = Preferences.userRoot().node("com/example/app/prefs/"+this.COD_TERMINAL_PATH);
				
		if(!preferences.nodeExists(""))
		return null;
		

		item.addParamentro(COD_TERMINAL_CHAVE, preferences.get(this.COD_TERMINAL_CHAVE, ""));
		item.addParamentro(COD_TERMINAL_NOME, preferences.get(this.COD_TERMINAL_NOME, ""));
			
		return item;
		}
		catch (BackingStoreException e) {return null;}
	
	}
		
		
		
	
	
	
	


	protected boolean setCodTerminal(String codigo, String nome){
		
	Preferences preferences	= null;
	
		try {
				
		preferences = Preferences.userRoot().node("com/example/app/prefs/"+this.COD_TERMINAL_PATH);
				
		if(!preferences.nodeExists(""))
		return false;
			
		preferences.put(this.COD_TERMINAL_CHAVE, codigo);
		preferences.put(this.COD_TERMINAL_NOME, nome);
		
		}
		catch (BackingStoreException e) {return false;}

	return true;	
	}




	
	
	
	
	protected String geraCodigo(){
		
	Random gerador = new Random();
	  
	return Comuns.addPaddingAEsquerda(""+gerador.nextInt(10000), 4, "0")+
				Comuns.addPaddingAEsquerda(""+gerador.nextInt(10000), 4, "0")+
					Comuns.addPaddingAEsquerda(""+gerador.nextInt(10000), 4, "0")+
						Comuns.addPaddingAEsquerda(""+gerador.nextInt(10000), 4, "0")+
						 	Comuns.addPaddingAEsquerda(""+gerador.nextInt(10000), 4, "0");
	
	}
	
	
	
	
}
