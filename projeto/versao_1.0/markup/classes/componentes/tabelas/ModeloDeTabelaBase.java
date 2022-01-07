package classes.componentes.tabelas;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;





import classes.componentes.anotacoes.Anot_ParametrosDePesquisa;
import classes.comuns.Comuns;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;



public abstract class ModeloDeTabelaBase<T>  extends AbstractTableModel{

	

private static final long serialVersionUID = 1L;


public int currentIndex = 0;
public int MAXPAGEITENS;
public int countItens = 0;

protected String query_join;
protected String query_where;
protected String query_order_by;



public List<T> linhas;

protected Class<?> classe;


protected boolean paginacao;



	public ModeloDeTabelaBase(Class<?> tipo){
	
	this(tipo, true);
	}




	public ModeloDeTabelaBase(Class<?> tipo, boolean paginacao){
		
	MAXPAGEITENS = Comuns.config_do_sistema.getItens_por_pagina()==0?25:Comuns.config_do_sistema.getItens_por_pagina();
	classe = tipo;
	
	this.linhas = new ArrayList<T>();
	
	this.query_join = "";
	this.query_where = "";
	this.query_order_by = "";
	
	this.paginacao =paginacao;
	}

	
	
	
	@Override
	public int getColumnCount() {
		
	int colunas = 0;
	
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
		} 
		catch (Exception e) {return "Erro";}
		
	return "";
	}	
		
	

	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
	
	
	
	
	@Override
	public String getColumnName(int columnIndex) {
	
		for (Field field : classe.getDeclaredFields()) {
			
		if (field.isAnnotationPresent(Anot_TB_Coluna.class)&& 
				field.getAnnotation(Anot_TB_Coluna.class).posicao() == columnIndex)
		return "<html><b>"+field.getAnnotation(Anot_TB_Coluna.class).rotulo()+"</b></html>";
		  
		}

	return "";
	}
	
	
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	
	return String.class;
	}
	
	
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}
	
	
	
	
	
	public void limpaPesquisa(){
		
	this.currentIndex = 0;
	this.countItens = 0;
	
	this.pesquisa("");
	}
	
	
	

	public abstract void pesquisa(String termos);
	
	



	public T getLinha(int index){
	
	if(index< 0 || index >= this.linhas.size())
	return null;
	
	return this.linhas.get(index);	
	}

	
	
	

	

	public Class<?> getTipo(){
		
	return this.classe;	
	}
	
	

	
/*	
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
	
*/
	
	
	
	
	
/************************ paginacao *********************************/	
	
	
	
	

	public void proximaPagina(String termos){
		
	if(this.currentIndex < (this.countItens - this.MAXPAGEITENS))
	this.currentIndex += this.MAXPAGEITENS;
	
	this.pesquisa(termos);	
	}
	


	
	
	public void paginaAnterior(String termos){
	
	if((this.currentIndex - this.MAXPAGEITENS) >= 0)
	this.currentIndex -= this.MAXPAGEITENS; 	

	this.pesquisa(termos);	
	}
		
	
	
	
	
	public void primeiraPagina(String termos){
		
	this.currentIndex = 0;
	
	this.pesquisa(termos);	
	}
			
	
	
	
	public void ultimaPagina(String termos){
		
		if(this.countItens>= this.MAXPAGEITENS){
			
		if(this.countItens%this.MAXPAGEITENS == 0)
		this.currentIndex = this.countItens - this.MAXPAGEITENS;
		else
		this.currentIndex = this.countItens-(this.countItens%this.MAXPAGEITENS);	
		}	
	
	this.pesquisa(termos);	
	}
			
	
	

	public void setJoin(String join){this.query_join = join;}
	
	public void setWhere(String where){this.query_where = where;}
	
	public void setOrderBy(String order){this.query_order_by = order;}







	public String subQueryDePesquisa(String termo){
		
	
	if(termo == null || termo.length() == 0) 
	return "";
	
	String aux = termo.replace(" ", "");	
	
	boolean pesquisa_como_numero = false;
	
	if(aux.length() == termo.replaceAll("\\D", "").length())
	pesquisa_como_numero = true;
	
	List<String> locais = new ArrayList<String>();
	
	String tabela_prefixo = classe.getAnnotation(Anot_BD_Tabela.class).prefixo();
	
	
		if(pesquisa_como_numero){
	
			for (Field field : classe.getDeclaredFields()) {
			
				if ( field.isAnnotationPresent(Anot_BD_Campo.class) && 
						field.isAnnotationPresent(Anot_ParametrosDePesquisa.class)){
					
					
				if(field.getAnnotation(Anot_ParametrosDePesquisa.class).pesquisaComoNumero())
				locais.add((field.getAnnotation(Anot_BD_Campo.class).prefixo()==null || 
							field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?
							tabela_prefixo:field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome());
				}
			}	
		}
		else{
			
			for (Field field : classe.getDeclaredFields()) {
				
				if ( field.isAnnotationPresent(Anot_BD_Campo.class) && 
						field.isAnnotationPresent(Anot_ParametrosDePesquisa.class)){
					
					
				if(field.getAnnotation(Anot_ParametrosDePesquisa.class).pesquisaComoString())
				locais.add((field.getAnnotation(Anot_BD_Campo.class).prefixo()==null || 
							field.getAnnotation(Anot_BD_Campo.class).prefixo().length()==0?
							tabela_prefixo:field.getAnnotation(Anot_BD_Campo.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome());
				}
			}		
		}
		

	StringBuilder subquery = new StringBuilder();	
			
	String[] tokens = termo.split("\\s");
			
		for (int i=0; i<locais.size(); i++){
			    
		subquery.append(" ( ");	 
			    
			for (int j=0; j<tokens.length; j++){
			
			if(tokens[j]==null || tokens[j].length()==0)
			continue;
				
			subquery.append(locais.get(i)+ " like '%"+tokens[j]+"%' ");
					    			 
			if(j< tokens.length-1)
			subquery.append(" AND ");	
			}
				
		subquery.append(" ) ");	
				
		if(i< locais.size()-1)
		subquery.append(" OR ");
		}
	
	return subquery.toString();
	}
	


}
