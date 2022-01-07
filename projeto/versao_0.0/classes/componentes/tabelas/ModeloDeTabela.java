package componentes.tabelas;

import java.lang.reflect.Field;
import java.util.Date;

import DAO.DAO;
import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import comuns.Comuns;



public class ModeloDeTabela<T>  extends ModeloDeTabelaBase<T>{

	

private static final long serialVersionUID = 1L;




	public ModeloDeTabela(Class<?> tipo){
	
	this(tipo, true);
	}




	public ModeloDeTabela(Class<?> tipo, boolean paginacao){
		
	super(tipo, paginacao);
	}

	
	
	
	public void pesquisa(String termos, String local){
		
	this.linhas.clear();
	
	String sub_query_filtro = null;
	

		if(local.length() > 0){	
				
			for (Field field : this.classe.getDeclaredFields()) {
			
				if (field.isAnnotationPresent(Anot_TB_Coluna.class) &&
						field.isAnnotationPresent(Anot_BD_Campo.class) && 
							field.getAnnotation(Anot_TB_Coluna.class).rotulo().compareTo(local) == 0){
					
				if(field.getAnnotation(Anot_BD_Campo.class).tipo().equals(Integer.class) || field.getAnnotation(Anot_BD_Campo.class).tipo().equals(Date.class))
				termos = termos.replaceAll("\\D", " ");	
				
				sub_query_filtro = Comuns.searchSubQuery(termos, 
										(field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?classe.getAnnotation(Anot_BD_Tabela.class).prefixo():field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome());	
				break;
				}
			}	
		}
	
	
	
	String sub_query = 
		(this.query_where!= null && this.query_where.length()>0? this.query_where+" and ":"")+
		(sub_query_filtro != null && sub_query_filtro.length()> 0? sub_query_filtro:"1");
		
	
	DAO<T> dao = new DAO<T>(this.classe); 
		
		
	this.countItens = dao.getCont(null, sub_query, null);
		
		
	this.linhas.addAll(dao.get(null, sub_query+" "+
										(this.query_order_by==null?"":this.query_order_by)+
											" LIMIT "+(paginacao?this.MAXPAGEITENS+" OFFSET "+this.currentIndex:"50"), null));	
	
	fireTableDataChanged();
	}	

	
	
	
	
}
