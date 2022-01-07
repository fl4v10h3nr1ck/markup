package classes.componentes.beans;

import java.util.HashMap;
import java.util.Map;



public class Item {

	
private Map<String, Object> parametros;




	public Item(){parametros = new 	HashMap<String, Object>();}




	public Map<String, Object> getParametros() {return parametros;}
	public void setParametros(Map<String, Object> parametros) {this.parametros = parametros;}
	

	

	public String getValor(String chave){
		
	Object aux =  this.parametros.get(chave);	
		
	return aux==null?"":aux.toString();
	}



	public void setValor(String chave, String valor){
	
	parametros.remove(chave);	
		
	this.parametros.put(chave, valor);
	}

	
	public void setValor(String chave, int valor){this.setValor(chave, String.valueOf(valor));}

	
	

}
