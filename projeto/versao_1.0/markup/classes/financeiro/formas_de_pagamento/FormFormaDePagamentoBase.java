package classes.financeiro.formas_de_pagamento;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;







import util.Mensagens;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Comuns;
import classes.dao.DAO;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;






public abstract class FormFormaDePagamentoBase extends Dialogo {

	
	
private static final long serialVersionUID = 1L;


protected CampoLimitado tf_descricao;
protected JComboBox<String> padrao;
protected JComboBox<String> tipo;
protected JComboBox<String> tipo_parcela;
protected CampoLimitadoSoDigito tf_num_max_parcelas;
protected CampoLimitado tf_codigo;


protected FormaDePagamento retorno;




	public FormFormaDePagamentoBase(String titulo) {
			
	this(titulo, null);
	}
	
	
	
	




	public FormFormaDePagamentoBase(String titulo, FormaDePagamento retorno) {
			
	super(titulo, 700, 200);
	
	this.retorno = retorno;
	
	this.adicionarComponentes();
	}



	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	JPanel p1 = new JPanel(new GridBagLayout());
	this.add(p1, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p2 = new JPanel(new GridBagLayout());
	this.add(p2, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty = 0;
	cons.gridwidth  = 1;
	cons.weightx = 0.2;
	cons.insets = new Insets( 2, 2, 0, 2);
	p1.add(new JLabel("<html>Código:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.8;
	p1.add(new JLabel("<html>Descrição:<font color=red>*</font></html>"), cons);
	
	
	cons.gridwidth  = 1;	
	cons.weightx = 0.2;
	cons.insets = new Insets( 2, 2, 2, 2);
	this.tf_codigo = new CampoLimitado(40);
	p1.add(this.tf_codigo, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.8;
	this.tf_descricao = new CampoLimitado(200);
	p1.add(this.tf_descricao, cons);
	
	cons.weightx = 1;
	cons.gridwidth  = 1;
	cons.insets = new Insets( 2, 2, 0, 2);
	p2.add(new JLabel("Tipo:"), cons);
	p2.add(new JLabel("Parcelamento:"), cons);
	p2.add(new JLabel("Padrão:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(new JLabel("<html>Num. Max. Parcelas:<font color=red>*</font></html>"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets( 2, 2, 2, 2);
	this.tipo = new  JComboBox<String>(new String[]{"DINHEIRO", "CREDITO", "DEBITO", "CREDIARIO"});
	p2.add(this.tipo, cons);
	
	this.tipo_parcela = new  JComboBox<String>(new String[]{"UNICO", "PARCELADO"});
	p2.add(this.tipo_parcela, cons);

	this.padrao = new  JComboBox<String>(new String[]{"NAO", "SIM"});
	p2.add(this.padrao, cons);	
	
	List<FormaDePagamento> formas_padrao  =  new DAO<FormaDePagamento>(FormaDePagamento.class).get(null, "f_pag.padrao='SIM' and f_pag.ativo='S'", null);
	if(formas_padrao.size()>0)
	this.padrao.setEnabled(false);	
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_num_max_parcelas = new CampoLimitadoSoDigito(2);
	p2.add(this.tf_num_max_parcelas, cons);
	this.tf_num_max_parcelas.setEnabled(false);

	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	cons.weightx = 0;
	cons.insets = new Insets( 4, 2, 2, 2);
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_save.setToolTipText("Salvar");
	this.add(bt_save, cons);
			
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}		
		}});
		

		this.tipo_parcela.addItemListener( new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(tipo_parcela.getSelectedItem().toString().compareTo("UNICO")==0){
				
			tf_num_max_parcelas.setEnabled(false);
			tf_num_max_parcelas.setText("");
			}
			else	
			tf_num_max_parcelas.setEnabled(true);
	
		}});
		
		
		this.tipo.addItemListener( new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent e) {
				
		setTipo();
		}});
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_descricao);
	textFieldList.add(tf_num_max_parcelas);
	textFieldList.add(this.tf_codigo);
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.setTipo();
	}



	
	
	private void setTipo(){
		
	if(tipo.getSelectedItem().toString().compareTo("CREDITO")==0 || 
				tipo.getSelectedItem().toString().compareTo("CREDIARIO")==0)		
	tipo_parcela.setEnabled(true);	
		else{
			
		tipo_parcela.setEnabled(false);	
		tipo_parcela.setSelectedItem("UNICO");
		}
		
	}
	
	
	
	
	


	protected boolean validacao(){
		
		if(this.tf_codigo.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe um código para a forma de pagamento.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
					
					
		if(!Comuns.codigoPermitido(new DAO<FormaDePagamento>(FormaDePagamento.class), this.tf_codigo.getText(), this.retorno!=null?this.retorno.getId_forma_pag():0)){
						
		Mensagens.msgDeErro("O código informado já está sendo usado por outra forma de pagamento.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
		
		
		
		if(this.tf_descricao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe a descrição.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;	
		}
		
		if(this.tipo_parcela.getSelectedItem().toString().compareTo("PARCELADO")==0){
			if(this.tf_num_max_parcelas.getText().length() ==0 || Integer.parseInt(this.tf_num_max_parcelas.getText()) == 0){
				
			Mensagens.msgDeErro("Informe o número máximo de parcelas.");
			Comuns.textFieldErroMode(this.tf_num_max_parcelas);
			return false;	
			}
		}
		
	return true;	
	}
	
	

	
	
	
	protected void setFormaPadrao(FormaDePagamento forma_pag, DAO<FormaDePagamento> dao){
		
		if(forma_pag.getPadrao()!=null && forma_pag.getPadrao().compareTo("S")==0){
			
		List<FormaDePagamento> formas = dao.get(null, "f_pag.id_forma_pag<>"+forma_pag.getId_forma_pag(), null);
			
			for(FormaDePagamento forma: formas){
				
			forma.setPadrao("N");
			dao.altera(forma);
			}
		}	
	}
	
	
	
}	
