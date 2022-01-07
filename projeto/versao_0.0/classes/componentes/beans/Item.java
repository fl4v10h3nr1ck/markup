package componentes.beans;

import java.util.HashMap;
import java.util.Map;



public class Item {

	
private Map<String, Object> parametros;




	public Item(){
		
	parametros = new 	HashMap<String, Object>();
	}




	public Map<String, Object> getParametros() {
		
	return parametros;
	}



	public void setParametros(Map<String, Object> parametros) {
	
	this.parametros = parametros;
	}
	


	public Object getParamentro(String chave){
		
	return this.parametros.get(chave);	
	}



	public Object addParamentro(String chave, Object valor){
		
	return this.parametros.put(chave, valor);	
	}

	
	
	public void setParamentro(String chave, Object novo_valor){
	
	parametros.remove(chave);	
		
	this.addParamentro(chave, novo_valor);
	}



}
