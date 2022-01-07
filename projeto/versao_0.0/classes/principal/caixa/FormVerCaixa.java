package principal.caixa;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;
import classes.CampoLimitado;
import componentes.Rotulo;
import componentes.beans.Caixa;
import componentes.beans.Colaborador;
import componentes.beans.FormaDePagamento;
import componentes.beans.Movimento;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;





public class FormVerCaixa extends Dialogo{




private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoLimitado tf_codigo;
protected CampoLimitado tf_val_inicial;
protected CampoLimitado tf_val_fechamento;
protected CampoLimitado tf_val_arrecadado;

protected CampoLimitado tf_status;
protected CampoLimitado tf_data_abetura;
protected CampoLimitado tf_data_fechamento;
protected CampoLimitado tf_resp_abertura;
protected CampoLimitado tf_resp_fechamento;
protected Rotulo val_parcial;


private Caixa caixa;


protected JTable tabela_movimentos;		
private DefaultTableModel modelo;		

protected List<Movimento> movimentos;



protected JTable tabela_pagamentos;		
private DefaultTableModel modelo_pagamentos;		

protected List<Movimento> Tipos;






	public FormVerCaixa(Caixa caixa) {
	
	super("Dados de Caixa", 780, 580);
	
	this.caixa = caixa;	
	
	this.adicionarComponentes();
	
	
		if(this.caixa.getFk_user_abertura()>0){
		
		Colaborador colaborador = new DAO<Colaborador>(Colaborador.class).get(this.caixa.getFk_user_abertura());
		
		if(colaborador!=null)
		this.tf_resp_abertura.setText(colaborador.getNome());
		}
		
		if(this.caixa.getFk_user_fechamento()>0){
			
		Colaborador colaborador = new DAO<Colaborador>(Colaborador.class).get(this.caixa.getFk_user_fechamento());
			
		if(colaborador!=null)
		this.tf_resp_fechamento.setText(colaborador.getNome());
		}	
	}


	
		
	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p5 = new JPanel(new GridBagLayout());
	p5.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p5, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p3, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p4 = new JPanel(new GridBagLayout());
	p4.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p4, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("ID:"), cons);
	p1.add(new Rotulo("Código:"), cons);
	p1.add(new Rotulo("Saldo Inicial:"), cons);
	p1.add(new Rotulo("Saldo de Fechamento:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	p1.add(new Rotulo("Valor Arrecadado:"), cons);
		
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_codigo = new CampoLimitado(200, Comuns.getCod(Caixa.class, this.caixa.getId_caixa()));
	p1.add(this.tf_codigo, cons);
	this.tf_codigo.setEditable(false);
	
	this.tf_nome = new CampoLimitado(200, this.caixa.getCodigo()); 
	p1.add(this.tf_nome, cons);
	this.tf_nome.setEditable(false);	
	
	this.tf_val_inicial = new CampoLimitado(200, this.caixa.getValor_inicial() != null ?Calculo.formataValor(this.caixa.getValor_inicial()):"0,00");
	p1.add(this.tf_val_inicial, cons);
	this.tf_val_inicial.setEditable(false);
		
	this.tf_val_fechamento = new CampoLimitado(200, this.caixa.getValor_fechamento() != null ?Calculo.formataValor(this.caixa.getValor_fechamento()):"");
	p1.add(this.tf_val_fechamento, cons);
	this.tf_val_fechamento.setEditable(false);
		
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_val_arrecadado = new CampoLimitado(200, this.caixa.getValor_fechamento() != null ?Calculo.formataValor(Calculo.subtrai(this.caixa.getValor_fechamento(), this.caixa.getValor_inicial())):"");
	p1.add(this.tf_val_arrecadado, cons);
	this.tf_val_arrecadado.setEditable(false);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p5.add(new Rotulo("Status:"), cons);
	p5.add(new Rotulo("Abertura:"), cons);
	p5.add(new Rotulo("Fechamento:"), cons);
	p5.add(new Rotulo("Aberto por:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	p5.add(new Rotulo("Fechado por:"), cons);
			

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_status = new CampoLimitado(200, this.caixa.getStatus());
	p5.add(this.tf_status, cons);
	this.tf_status.setEditable(false);
		
	this.tf_data_abetura = new CampoLimitado(200, 
			this.caixa.getData_abertura() != null?Data.converteDataParaString(this.caixa.getData_abertura()) +" "+ this.caixa.getHora_abertura()+":"+this.caixa.getMin_abertura():"");
	p5.add(this.tf_data_abetura, cons);
	this.tf_data_abetura.setEditable(false);
	
	this.tf_data_fechamento = new CampoLimitado(200, 
			this.caixa.getData_fechamento() != null?Data.converteDataParaString(this.caixa.getData_fechamento()) +" "+  this.caixa.getHora_fechamento()+":"+this.caixa.getMin_fechamento():"");
	p5.add(this.tf_data_fechamento, cons);
	this.tf_data_fechamento.setEditable(false);	

	this.tf_resp_abertura = new CampoLimitado(200);
	p5.add(this.tf_resp_abertura, cons);
	this.tf_resp_abertura.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_resp_fechamento = new CampoLimitado(200);
	p5.add(this.tf_resp_fechamento, cons);
	this.tf_resp_fechamento.setEditable(false);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new JLabel("<html><b>Valor Parcial Total: </b></html>"), cons);
	p2.add(this.val_parcial = new Rotulo(""), cons);

	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = 1;	
	cons.anchor = GridBagConstraints.EAST;
	JButton btAtualizar  = new JButton(new ImageIcon(getClass().getResource("/icons/alterar_mini.png")));
	btAtualizar.setToolTipText("Atualizar movimentos de caixa");
	p2.add(btAtualizar, cons);
	
	
		btAtualizar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		setDadosDeMovimentos();
		setDadosDePagamentos();
		}});
		
	
	
	
		this.modelo = new DefaultTableModel(null, new String[]{"Código", "Entrada", "Saída", "Valor Final"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	
	
	this.tabela_movimentos = new JTable(this.modelo);
	this.tabela_movimentos.setRowHeight(20); 		
	this.tabela_movimentos.getColumnModel().getColumn(0).setPreferredWidth(110);
	this.tabela_movimentos.getColumnModel().getColumn(1).setPreferredWidth(150);
	this.tabela_movimentos.getColumnModel().getColumn(2).setPreferredWidth(150);
	this.tabela_movimentos.getColumnModel().getColumn(3).setPreferredWidth(110);	
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.insets = new Insets(1, 1, 1, 1);
	this.tabela_movimentos.setBackground(Color.white); 
    p3.add(new JScrollPane(this.tabela_movimentos, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
	
    
    
		
		this.modelo_pagamentos = new DefaultTableModel(null, new String[]{"Forma de Pagamento", "Num. Atend.", "Valor Parcial"}){
	
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 


	this.tabela_pagamentos = new JTable(this.modelo_pagamentos);
	this.tabela_pagamentos.setRowHeight(20); 		
	this.tabela_pagamentos.getColumnModel().getColumn(0).setPreferredWidth(350);
	this.tabela_pagamentos.getColumnModel().getColumn(1).setPreferredWidth(150);
	this.tabela_pagamentos.getColumnModel().getColumn(2).setPreferredWidth(200);
	


	this.tabela_pagamentos.setBackground(Color.white); 
	p4.add(new JScrollPane(this.tabela_pagamentos, 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
	
	 
	this.setDadosDeMovimentos();
	this.setDadosDePagamentos();
	}
		
	
		
	
	
	private void setDadosDeMovimentos(){
			
	List<Movimento> movimentos = new DAO<Movimento>(Movimento.class).get(null, 
			"mov.fk_caixa="+this.caixa.getId_caixa(), null);

	this.modelo.setNumRows(0);
	
	String valor_parcial = "0,00";
	
	String[] dados = new String[4];
	
		for(Movimento movimento: movimentos){
	
		dados[0] = Comuns.getCod(Movimento.class, movimento.getId_movimento());
			
		dados[1] = movimento.getData_abertura() != null? 
				Data.converteDataParaString(movimento.getData_abertura())+" "+movimento.getHora_abertura()+" :"+ movimento.getMin_abertura():"";
				
		dados[2] = movimento.getData_fechamento() != null? 
				Data.converteDataParaString(movimento.getData_fechamento())+" "+movimento.getHora_fechamento()+" :"+ movimento.getMin_fechamento():"";
						
		dados[3] = Calculo.formataValor(movimento.getValor_final());
		
		valor_parcial = Calculo.soma(valor_parcial, movimento.getValor_final());
		
		this.modelo.addRow(dados);	
		}
		
	this.val_parcial.setText("R$: "+valor_parcial);
	}
	
	
	
	
	
	
	private void setDadosDePagamentos(){
		
	
	this.modelo_pagamentos.setNumRows(0);	
	
	List<FormaDePagamento> formas = new DAO<FormaDePagamento>(FormaDePagamento.class).get(null, "f_pag.status='ATIVO'", "f_pag.tipo DESC");
	
	String[] dados = new String[3];
	
	DAO<Movimento> dao = new DAO<Movimento>(Movimento.class);
	
		for(FormaDePagamento aux: formas){
		
		List<Movimento> movs = dao.get("INNER JOIN movimentos_x_formas_de_pagamento as x on x.fk_movimento=mov.id_movimento", 
				"mov.fk_caixa="+this.caixa.getId_caixa()+" and x.fk_forma_de_pagamento="+aux.getId_forma_pag()+" and mov.status_pagamento='FECHADO'", 
				null);
		
		String valor = "0.00";
		for( Movimento mov: movs)
		valor	 = Calculo.soma(mov.getValor_final(), valor);
			
		dados[0] = aux.getDescricao();
		dados[1] = ""+movs.size();
		dados[2] = Calculo.formataValor(valor);
			
		this.modelo_pagamentos.addRow(dados);	
		}
			
	}




	
	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
