package classes.comuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Data {

	
	
	
	public static java.sql.Date getSqlData(java.util.Date data){
		
	return data == null? null: new java.sql.Date(data.getTime());	
	}
	
	
		
	
	
	public static String getDataAtual(String delimitador){
		
	return new SimpleDateFormat("dd"+delimitador+"MM"+delimitador+"yyyy").format(new Date());
	}
	
	
	
	
	
	
	public static String converteDataParaString(Date aux){
		
	return aux== null?"":new SimpleDateFormat("dd/MM/yyyy").format(aux);
	}
	
	
	
	
	

	public static String getAnoAtual(){
		
	return new SimpleDateFormat("yyyy").format(new Date());
	}
	
	
	
	
	

	
	public static Date converteStringParaData(String aux){
	
	if(aux==null)
	return null;
		
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
	try {return format.parse(aux);} 
	catch (ParseException e) {return null;}
	}
	
	
	
	
	
	
	
	public static int comparaDatas(Date d_inicial, Date d_final){
	
	if(d_inicial ==null || d_final==null)	
	return -2;	
	
	if(d_inicial.before(d_final))
	return -1;
	
	if(d_inicial.after(d_final))
	return 1;
	
	return 0;
	}
	
	

	
	

	public static int comparaDatas(String d_inicial, String d_final){
	
	return Data.comparaDatas(Data.converteStringParaData(d_inicial), Data.converteStringParaData(d_final));
	}
	
	
	
}
