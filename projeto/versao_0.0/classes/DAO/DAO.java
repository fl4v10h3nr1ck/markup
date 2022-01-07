package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import comuns.Configuracao;
import comuns.Mensagens;




public class DAO <T>{

	
	
private Class<?> tipo;
	
	

	
	
	public DAO(Class<?> tipo){
		
	this.tipo = tipo;
	}
	
	
	
	
	
	
	public int novo( T aux){
		
		try {
				
		if(aux == null || !this.tipo.isAnnotationPresent(Anot_BD_Tabela.class)){	
		
		Mensagens.msgDeErroAoSalvar();
		return 0;
		}
		
		String tabela = this.tipo.getAnnotation(Anot_BD_Tabela.class).nome();
		
		StringBuilder query = new StringBuilder("INSERT INTO "+tabela+" (");	
		StringBuilder subquery = new StringBuilder(") VALUES (");	
		
		
		Field[] fields = this.tipo.getDeclaredFields();
		
			for (Field field : fields) {
				
				if (field.isAnnotationPresent(Anot_BD_Campo.class) &&
						!field.getAnnotation(Anot_BD_Campo.class).ehId() && 
						  !field.getAnnotation(Anot_BD_Campo.class).select_apenas()){
				
				query.append(field.getAnnotation(Anot_BD_Campo.class).nome()+", ");	
				
				subquery.append("?, ");
				}
			}		
		
	
		query.delete(query.length() -2, query.length());
		subquery.delete(subquery.length() -2, subquery.length());
			

		PreparedStatement statement	= (PreparedStatement) Configuracao.connector.prepareStatement( 			      		
				query.append(subquery).append(")").toString(), Statement.RETURN_GENERATED_KEYS);   
		
		System.out.println(query);
		
			for (int i = 0, cont=1; i < fields.length ; i++){
				
			Field field = fields[i];

				if (field.isAnnotationPresent(Anot_BD_Campo.class) &&
						!field.getAnnotation(Anot_BD_Campo.class).ehId() &&
							!field.getAnnotation(Anot_BD_Campo.class).select_apenas()){
				
				
				Object valor = this.tipo.getDeclaredMethod(field.getAnnotation(Anot_BD_Campo.class).get()).
						invoke(aux);
				
					if(valor ==null){
					
					statement.setObject( cont++, null);
					continue;
					}
					
					if(field.getAnnotation(Anot_BD_Campo.class).tipo() == int.class){
					
					String aux_val = valor.toString();
						
					if(aux_val.length() == 0 || Integer.parseInt(aux_val)==0)		
					statement.setObject( cont++,null);
					else
					statement.setInt( cont++, Integer.parseInt(aux_val));
					
					continue;
					}
					
					
				if(field.getAnnotation(Anot_BD_Campo.class).tipo() == Date.class)
				statement.setDate( cont++, new java.sql.Date(((Date)valor).getTime()));
				else
				statement.setString( cont++, valor.toString());
				
				}	
			}
			
		statement.execute();
			
		ResultSet resultSet = statement.getGeneratedKeys();
		
		return resultSet.next()?resultSet.getInt(1):0;
		}
		catch (	SQLException |
				IllegalAccessException |
				IllegalArgumentException |
				InvocationTargetException |
				NoSuchMethodException |
				SecurityException e) {
		
		Mensagens.msgDeErroAoSalvar();
		
		return 0;
		}
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<T> get(String join, String where, String orderBy){
		
	List<T> lista = new ArrayList<T>();
							
		try{
			
		String tabela = this.tipo.getAnnotation(Anot_BD_Tabela.class).nome();

		
		StringBuilder query = new StringBuilder("SELECT ");	
	
		Field[] fields = this.tipo.getDeclaredFields();
		
			for (Field field : fields) {
				
				if (field.isAnnotationPresent(Anot_BD_Campo.class)){
				
				query.append(
						(field.getAnnotation(Anot_BD_Campo.class).prefixo().length()>0?
								field.getAnnotation(Anot_BD_Campo.class).prefixo():
								this.tipo.getAnnotation(Anot_BD_Tabela.class).prefixo())
								+"."+field.getAnnotation(Anot_BD_Campo.class).nome()+", ");	

				}
			}		
		
	
		query.delete(query.length() -2, query.length());
		
		query.append(" FROM "+tabela+" as "+this.tipo.getAnnotation(Anot_BD_Tabela.class).prefixo()+
				" "+this.tipo.getAnnotation(Anot_BD_Tabela.class).left_join() +
				(join != null && join.length()>0?" "+join:"")+
				(where != null && where.length()>0?" WHERE "+where:"")+
				(orderBy != null && orderBy.length()>0?" ORDER BY "+orderBy:""));
		
		System.out.println(query.toString());
		
		Statement st = Configuracao.connector.createStatement();				
		ResultSet resultSet = st.executeQuery(query.toString());
				
			while(resultSet.next()){

			T aux = (T) this.tipo.newInstance();
							
				for (Field field : this.tipo.getDeclaredFields()){		
									
											
					if (field.isAnnotationPresent(Anot_BD_Campo.class)){
				
					Object valor = resultSet.getObject((field.getAnnotation(Anot_BD_Campo.class).prefixo().length()>0?
							field.getAnnotation(Anot_BD_Campo.class).prefixo():
							this.tipo.getAnnotation(Anot_BD_Tabela.class).prefixo())+"."+field.getAnnotation(Anot_BD_Campo.class).nome());
					
					
					if(field.getAnnotation(Anot_BD_Campo.class).tipo() == int.class)
					aux.getClass().getDeclaredMethod(field.getAnnotation(Anot_BD_Campo.class).set(), field.getAnnotation(Anot_BD_Campo.class).tipo()).
												invoke(aux, valor == null?0:(Integer)valor);
					else
					aux.getClass().getDeclaredMethod(field.getAnnotation(Anot_BD_Campo.class).set(), field.getAnnotation(Anot_BD_Campo.class).tipo()).
						invoke(aux, valor);

					
					}
				}
								
								
			lista.add(aux);
			}	
		}
		catch (	SQLException |
				InstantiationException |
				IllegalAccessException |
				IllegalArgumentException |
				InvocationTargetException |
				NoSuchMethodException |
				SecurityException e) {
		e.printStackTrace();	
		Mensagens.msgDeErroAoObter();
		return new ArrayList<T>();
		}
		
	
	return lista;
	}
	
	
	
	
	

	public T get(int id){

	String where = this.tipo.getAnnotation(Anot_BD_Tabela.class).prefixo()+".";
		for (Field field : this.tipo.getDeclaredFields()) {
			
			if (field.getAnnotation(Anot_BD_Campo.class).ehId()){
			where +=field.getAnnotation(Anot_BD_Campo.class).nome();
			break;
			}
		}		
	
	List<T> aux = this.get("", where+"="+id, "");
	
	return aux!=null && aux.size()>0? aux.get(0):null;
	}
	
	
	
	
	

	
	public boolean remove(int id){
	
	if(!Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja excluir este registro?"))
	return false;			
		
	return this.removeSemConfirmacao(id);
	}
	
	
	
	

	public boolean removeSemConfirmacao(int id){
		
		try{
			
		String tabela = this.tipo.getAnnotation(Anot_BD_Tabela.class).nome();
	
		StringBuilder query = new StringBuilder("DELETE FROM "+tabela+" WHERE ");	
	
		Field[] fields = this.tipo.getDeclaredFields();
		
			for (Field field : fields) {
				
				if (field.isAnnotationPresent(Anot_BD_Campo.class) && field.getAnnotation(Anot_BD_Campo.class).ehId()){
				
				query.append(field.getAnnotation(Anot_BD_Campo.class).nome()+" = "+id);	
				break;
				}
			}		
		
		System.out.println(query.toString());	
			
		Statement stmt = Configuracao.connector.createStatement();
		stmt.executeUpdate(query.toString());	
				
		}
		catch (	SQLException |
				IllegalArgumentException |
				SecurityException e) {
		
		Mensagens.msgDeErroAoDeletar();
		return false;
		}
		
	return true;
	}
	
	
	
	
	public boolean removeSemConfirmacao(String where){
	
	if(where==null || where.length()==0)	
	return false;
		
		try{
			
		String tabela = this.tipo.getAnnotation(Anot_BD_Tabela.class).nome();
	
		StringBuilder query = new StringBuilder("DELETE FROM "+tabela+" WHERE "+where);	
	
		System.out.println(query.toString());	
			
		Statement stmt = Configuracao.connector.createStatement();
		stmt.executeUpdate(query.toString());	
				
		}
		catch (	SQLException |
				IllegalArgumentException |
				SecurityException e) {
		
		Mensagens.msgDeErroAoDeletar();
		return false;
		}
		
	return true;
	}
	
	
	
	
	
	public boolean altera(T aux){
		
		try {
			
		if(aux == null || !this.tipo.isAnnotationPresent(Anot_BD_Tabela.class)){
		
		Mensagens.msgDeErroAoAlterar();	
		return false;
		}	
			
		String tabela = this.tipo.getAnnotation(Anot_BD_Tabela.class).nome();
			
		StringBuilder query = new StringBuilder("UPDATE "+tabela+" SET ");	
		String id = null;
		
		Field[] fields = this.tipo.getDeclaredFields();
			
			for (Field field : fields) {
				
				if (field.isAnnotationPresent(Anot_BD_Campo.class) && !field.getAnnotation(Anot_BD_Campo.class).select_apenas()){
				
					if(field.getAnnotation(Anot_BD_Campo.class).ehId()){
					
					id = field.getAnnotation(Anot_BD_Campo.class).nome()+" = "+
									this.tipo.getDeclaredMethod(field.getAnnotation(Anot_BD_Campo.class).get()).invoke(aux);
					continue;
					}

				query.append(field.getAnnotation(Anot_BD_Campo.class).nome()+"=?, ");	
				}
			}		
			
		
			query.delete(query.length() -2, query.length());
			
			PreparedStatement statement	= (PreparedStatement) Configuracao.connector.prepareStatement( 			      		
					query.append(" WHERE "+id).toString());   
			
			
			System.out.println(query);
			
			for (int i = 0, cont=1; i < fields.length ; i++){
					
			Field field = fields[i];
					
				if (field.isAnnotationPresent(Anot_BD_Campo.class) && 
						!field.getAnnotation(Anot_BD_Campo.class).select_apenas() &&
							!field.getAnnotation(Anot_BD_Campo.class).ehId()){
					
					
				Object valor = this.tipo.getDeclaredMethod(field.getAnnotation(Anot_BD_Campo.class).get()).invoke(aux);
					
					if(valor ==null){
						
					statement.setObject( cont++,null);
					continue;
					}					
					
					if(field.getAnnotation(Anot_BD_Campo.class).tipo() == int.class){
						
					String aux_val = valor.toString();
							
					if(aux_val.length() == 0 || Integer.parseInt(aux_val)==0)		
					statement.setObject( cont++,null);
					else
					statement.setInt( cont++, Integer.parseInt(aux_val));
						
					continue;
					}
					
				if(field.getAnnotation(Anot_BD_Campo.class).tipo() == Date.class)
				statement.setDate( cont++,new java.sql.Date(((Date) valor).getTime()));
				else
				statement.setString( cont++, valor.toString());
					
				}	
			}
				
			statement.executeUpdate(); 				

			
		}
		catch (	SQLException |
				IllegalAccessException |
				IllegalArgumentException |
				InvocationTargetException |
				NoSuchMethodException |
				SecurityException e) {
			
		Mensagens.msgDeErroAoDeletar();
		return false;
		}
			
			
	
	return true;
	}
	
	
	
	
	
	public boolean desativar(int id){
	
		if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja excluir este registro?")){
		
		String where_id = null;
			
			for (Field field : this.tipo.getDeclaredFields()) {
				
				if (field.getAnnotation(Anot_BD_Campo.class).ehId()){
				where_id =field.getAnnotation(Anot_BD_Campo.class).nome()+"="+id;
				break;
				}
			}		
			
			
			try {
				
					
			PreparedStatement statement	= (PreparedStatement) Configuracao.connector.prepareStatement( 			      		
					"UPDATE "+
							this.tipo.getAnnotation(Anot_BD_Tabela.class).nome()+
							" SET status = ? WHERE "+where_id);   
					
			statement.setString( 1, "INATIVO");
			statement.executeUpdate(); 				
	
					
			}
			catch (	SQLException |
					IllegalArgumentException |
					SecurityException e) {
			
			Mensagens.msgDeErroAoAlterar();	
			return false;
			}
		}	

	return true;	
	}
	
	
	
	
	

	public boolean desativarSemConfirmacao(int id){
	
		
	String where_id = null;
		
		for (Field field : this.tipo.getDeclaredFields()) {
			
			if (field.getAnnotation(Anot_BD_Campo.class).ehId()){
			where_id =field.getAnnotation(Anot_BD_Campo.class).nome()+"="+id;
			break;
			}
		}		
		
		
		try {
			
				
		PreparedStatement statement	= (PreparedStatement) Configuracao.connector.prepareStatement( 			      		
				"UPDATE "+
						this.tipo.getAnnotation(Anot_BD_Tabela.class).nome()+
						" SET status = ? WHERE "+where_id);   
				
		statement.setString( 1, "INATIVO");
		statement.executeUpdate(); 				

				
		}
		catch (	SQLException |
				IllegalArgumentException |
				SecurityException e) {
		
		Mensagens.msgDeErroAoAlterar();	
		return false;
		}
				

	return true;	
	}
	
	
	
	
	
	
	public int getCont(String join, String where, String orderBy){
		
	int cont = 0;
							
		try{
			
		String tabela = this.tipo.getAnnotation(Anot_BD_Tabela.class).nome();

		StringBuilder query = new StringBuilder("SELECT count("+
				this.tipo.getAnnotation(Anot_BD_Tabela.class).prefixo()+".");
		
			for (Field field : this.tipo.getDeclaredFields()) {
			
			if (field.getAnnotation(Anot_BD_Campo.class).ehId()){
			query.append(field.getAnnotation(Anot_BD_Campo.class).nome()+") ");
			break;
			}
		}	
		
		
		query.append(" FROM "+tabela+" as "+this.tipo.getAnnotation(Anot_BD_Tabela.class).prefixo()+
				" "+this.tipo.getAnnotation(Anot_BD_Tabela.class).left_join() +
				(join != null && join.length()>0?" "+join:"")+
				(where != null && where.length()>0?" WHERE "+where:"")+
				(orderBy != null && orderBy.length()>0?" ORDEBY "+orderBy:""));
		
		System.out.println(query.toString());
		
		Statement st = Configuracao.connector.createStatement();				
		ResultSet resultSet = st.executeQuery(query.toString());
				
		while(resultSet.next())
		cont	 = resultSet.getInt(1);
		
		}
		catch (	SQLException |
				IllegalArgumentException |
				SecurityException e) {
		
		Mensagens.msgDeErroAoObter();
		return 0;
		}
		
	
	return cont;
	}
	
	
	
	
	public Class<?> getTipo(){
		
	return this.tipo;	
	}

	
	
	
}	
	