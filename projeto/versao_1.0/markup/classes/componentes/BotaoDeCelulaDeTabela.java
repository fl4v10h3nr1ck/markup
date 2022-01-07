package classes.componentes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;




public abstract class BotaoDeCelulaDeTabela extends 	  AbstractCellEditor 
										   implements TableCellRenderer, 
													  TableCellEditor, 
													  ActionListener{
 
private static final long serialVersionUID = 1L;


@SuppressWarnings("unused")
private JTable  tabela;
private JButton botao;
private JButton botao_editado;
private String  titulo = "ver";
    

private ImageIcon icon;



	public BotaoDeCelulaDeTabela(String titulo, 
	    							 ImageIcon icon, 
	    							 JTable tabela, 
	    							 int coluna){        
	super();
	        
	this.tabela = tabela;
	
	this.titulo = titulo;
	
	this.icon = icon;
	
	botao = new JButton();
	
	if(titulo!=null)
	botao.setText(this.titulo);
	
	if(this.icon!=null)
	botao.setIcon(this.icon);
	
	botao_editado = new JButton();
	
	if(titulo!=null)
	botao_editado.setText(this.titulo);
		
	if(this.icon!=null)
	botao_editado.setIcon(this.icon);
	
	
	//botao_editado.setFocusPainted( false );
	
	botao_editado.addActionListener( this );
	        
	TableColumnModel columnModel = tabela.getColumnModel();
	columnModel.getColumn(coluna).setCellRenderer( this );
	columnModel.getColumn(coluna).setCellEditor( this );
	}
	    
	    
	    
	    
	    
	    
	    
	public Component getTableCellRendererComponent(JTable table, 
	    												Object value, 
	    												boolean isSelected, 
	    												boolean hasFocus, 
	    												int row, 
	    												int column){
			
	if(mostrarBotao(row))
	return this.botao;
	
	return new JLabel("");
	}
	    
	    

	
	
	
	
	public Component getTableCellEditorComponent( JTable table,
											    		Object value, 
											    		boolean isSelected, 
											    		int row, 
											    		int column){
	
	if(mostrarBotao(row))	
	return this.botao_editado;
		
	return new JLabel("");
	}
	    
	

	    
	    
	    
	    
	    
	public Object getCellEditorValue(){return this.titulo;}
	    

	 
	
	
	
	
	
	public void actionPerformed(ActionEvent e){
	    
	this.fireEditingStopped();
	
	this.processa();
	}
	






	public abstract void processa();


	
	
	
	public abstract boolean mostrarBotao(int linha);


	
	
}

