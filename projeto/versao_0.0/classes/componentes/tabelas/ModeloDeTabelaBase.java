package componentes.tabelas;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_TB_Coluna;



public abstract class ModeloDeTabelaBase<T>  extends AbstractTableModel{

	

private static final long serialVersionUID = 1L;


public int currentIndex = 0;
public int MAXPAGEITENS;
public int countItens = 0;


protected String query_join;
protected String query_where;
protected String query_order_by;


protected List<T> linhas;

protected Class<?> classe;


protected boolean paginacao;



	public ModeloDeTabelaBase(Class<?> tipo){
	
	this(tipo, true);
	}




	public ModeloDeTabelaBase(Class<?> tipo, boolean paginacao){
		
	MAXPAGEITENS = 25;
	classe = tipo;
	
	this.linhas = new ArrayList<T>();
	
	this.query_join  ="";
	this.query_where  ="";
	this.query_order_by  ="";
	
	this.paginacao =paginacao;
	}

	
	
	
	@Override
	public int getColumnCount() {
		
	int colunas = 0;
	/*
		for (Method metodo : classe.getDeclaredMethods()) {
	       
		if (metodo.isAnnotationPresent(Anot_TB_Coluna.class))
	    colunas++;
	    }
	 */
	
		for (Field field : classe.getDeclaredFields()) {
		
		if (field.isAnnotationPresent(Anot_TB_Coluna.class))
		colunas++;
	    }
	return colunas;
	}
	
	
	
	
	@Override
	public int getRowCount() {
	
	return this.linhas.size();
	}

	
	
	
	@Override
	public Object getValueAt(int linha, int coluna) {

		try {
		  
			for (Field field : classe.getDeclaredFields()) {
				
				if (field.isAnnotationPresent(Anot_TB_Coluna.class) &&
							field.isAnnotationPresent(Anot_BD_Campo.class) && 
								field.getAnnotation(Anot_TB_Coluna.class).posicao() == coluna)
				return classe.getMethod(field.getAnnotation(Anot_BD_Campo.class).getTab().length()>0?
											field.getAnnotation(Anot_BD_Campo.class).getTab():
												field.getAnnotation(Anot_BD_Campo.class).get()).invoke(this.linhas.get(linha));
					
			}		
			
		/*	
			for (Method metodo : classe.getDeclaredMethods()) {
				
				if (metodo.isAnnotationPresent(Anot_TB_Coluna.class)) {
				
				Anot_TB_Coluna anotacao = metodo.getAnnotation(Anot_TB_Coluna.class);
				
				if (anotacao.posicao() == coluna)
				return metodo.invoke(this.linhas.get(linha));
					
				}
		    }
		*/
		} 
		catch (Exception e) {return "Erro";}
		
	return "";
	}	
		
	

	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
	
	
	
	
	@Override
	public String getColumnName(int columnIndex) {
	/*
		for (Method metodo : classe.getDeclaredMethods()) {
	
		if (metodo.isAnnotationPresent(Anot_TB_Coluna.class) && 
					metodo.getAnnotation(Anot_TB_Coluna.class).posicao() == columnIndex)	
		return metodo.getAnnotation(Anot_TB_Coluna.class).rotulo();
		}
	*/	
		
		for (Field field : classe.getDeclaredFields()) {
			
		if (field.isAnnotationPresent(Anot_TB_Coluna.class)&& 
				field.getAnnotation(Anot_TB_Coluna.class).posicao() == columnIndex)
		return field.getAnnotation(Anot_TB_Coluna.class).rotulo();
		  
		}

	return "";
	}
	
	
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	
	return String.class;
	}
	
	
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}
	
	
	
	
	public void atualiza(){
		
	this.atualiza("", "");
	}
	
	
	
	
	public void atualiza(String termos, String local){
		
	this.currentIndex = 0;
	this.countItens = 0;
			
	this.pesquisa(termos, local);		
	}
		



	public T getLinha(int index){
	
	if(index< 0 || index >= this.linhas.size())
	return null;
	
	return this.linhas.get(index);	
	}

	
	

	
	public abstract void pesquisa(String termos, String local);
	
	
	

	
	protected Object[] getLocais(){
		
	List<String>aux = new ArrayList<String>();
	
	boolean control;
		for (int i = 0; i < 20; i++) {
			
		control	 = false;
			for (Field field : classe.getDeclaredFields()) {
		
				if ( field.isAnnotationPresent(Anot_TB_Coluna.class) && 
						field.getAnnotation(Anot_TB_Coluna.class).posicao()== i){
				
				aux.add(field.getAnnotation(Anot_TB_Coluna.class).rotulo());
				control = true;
				break;
				}
			}	
		
		if(!control)
		break;
		}
		
	return aux.toArray();
	}
	
	
	

	
	
/************************ paginacao *********************************/	
	
	
	
	

	public void nextPage(String termos, String local){
		
	
	if(this.currentIndex <= (this.countItens - this.MAXPAGEITENS))
	this.currentIndex += this.MAXPAGEITENS; 	

	
	this.pesquisa(termos, local);	
	}
	


	
	public void prevPage(String termos, String local){
	
	if((this.currentIndex - this.MAXPAGEITENS) >= 0)
	this.currentIndex -= this.MAXPAGEITENS; 	

	
	this.pesquisa(termos, local);	
	}
		
	
	
	
	public void firstPage(String termos, String local){
		
	this.currentIndex = 0;
	
	this.pesquisa(termos, local);	
	}
			
	
	
	
	public void lastPage(String termos, String local){
		
		if(this.countItens>= this.MAXPAGEITENS){
			
		if(this.countItens%this.MAXPAGEITENS == 0)
		this.currentIndex = this.countItens - this.MAXPAGEITENS;
		else
		this.currentIndex = this.countItens-(this.countItens%this.MAXPAGEITENS);	
			
		}	
	
	this.pesquisa(termos, local);	
	}
			
	
	
	
/************************ paginacao *********************************/	

	
	
	
	public void setJoin(String join){this.query_join = join;}
	public void setWhere(String where){this.query_where = where;}
	public void setOrderBy(String order){this.query_order_by = order;}
}
