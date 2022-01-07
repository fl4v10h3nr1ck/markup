package classes.componentes.tabelas;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import classes.compras.beans.Compra;
import classes.comuns.Configuracoes;
import classes.estoque.beans.Produto;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;



public class ConstrutorDeCelulaDeTabela extends DefaultTableCellRenderer{

private static final long serialVersionUID = 1L;


private Class<?> tipoDeClasse;



	public ConstrutorDeCelulaDeTabela(Class<?> tipoDeClasse){
		
	this.tipoDeClasse = tipoDeClasse;
	}




	
	public Component getTableCellRendererComponent(JTable table,  
												   Object value,  
												   boolean isSelected,  
												   boolean hasFocus,  
												   int row,  
												   int column) {  	
		
	Component cell = super.getTableCellRendererComponent
			           (table, value, isSelected, hasFocus, row, column);	
			
	    
		if(cell instanceof DefaultTableCellRenderer){
			
		DefaultTableCellRenderer cellRender = (DefaultTableCellRenderer)cell;	
		
		cellRender.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 
		
		
		boolean controle = false;
			for(int aux: this.getCentralizados()){
			
				if(column == aux){
				controle =  true;
				break;
				}
			}
					
		if(controle)
		cellRender.setHorizontalAlignment(SwingConstants.CENTER);
		else
		cellRender.setHorizontalAlignment(SwingConstants.LEFT);
		
		cell.setForeground(Configuracoes.preferencias.tab_cor_fonte_linha);
		
		if(row%2==0)
		cell.setBackground(Configuracoes.preferencias.tab_cor_fraca_fundo_linha);
		else
		cell.setBackground(Configuracoes.preferencias.tab_cor_forte_fundo_linha);
		
			
		if(isSelected)
		cell.setBackground(Configuracoes.preferencias.tab_cor_fundo_linha_selecione);
		}
		
	  return cell;
	  }



	

	
	private int[] getCentralizados(){	
			
	if(tipoDeClasse == Produto.class)
	return new int[]{0, 2};
	
	if(tipoDeClasse == FormaDePagamento.class)
	return new int[]{0, 2, 3};
		
	if(tipoDeClasse == Compra.class)
	return new int[]{0, 1, 2, 3, 4, 5, 6};
		
	return new int[0];	
	}
	
} 

