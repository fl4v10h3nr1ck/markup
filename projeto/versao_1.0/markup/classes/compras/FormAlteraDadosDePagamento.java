package classes.compras;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import classes.componentes.beans.Item;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.comuns.Mensagens;
import classes.dao.DAO;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;



public class FormAlteraDadosDePagamento extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_pagamento;
protected CampoLimitadoSoDigito tf_num_parcelas;
protected CampoMoeda   tf_novo_valor;


public Item item;

public String valor_acumulado_pagamento;

public String valor_total;

private FormaDePagamento forma;

public boolean parcelado;






	public FormAlteraDadosDePagamento(Item item, String valor_acumulado_pagamento, String valor_total) {
		
	super("Atualizar Dados de Produto", 750, 150);
	
	this.item  = item;
	
	this.valor_acumulado_pagamento  =valor_acumulado_pagamento;
	
	this.valor_total = valor_total;
	
	
	this.forma = new DAO<FormaDePagamento>(FormaDePagamento.class).get(Integer.parseInt(item.getValor("id_forma_pagamento")));	
	
	
	if(forma.getTipo_parcela()!=null && forma.getTipo_parcela().compareTo("PARCELADO")==0)
	parcelado = true;
	else
	parcelado = false;
	
	this.adicionarComponentes();
	}
	

	
	

	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
			
	JPanel p1 = new JPanel(new GridBagLayout());
	this.add(p1, cons);
			
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.6;
	p1.add(new JLabel("Forma de Pagamento:"), cons);
	cons.weightx = 0.2;
	p1.add(new JLabel("Num. de Parcelas:"), cons);	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Valor (R$):"), cons);	
		
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.6;		
	p1.add(this.tf_pagamento = new CampoLimitado(200, this.item.getValor("nome_forma_pagamento")), cons);
	this.tf_pagamento.setEnabled(false);
	
	cons.weightx = 0.2;
	p1.add(this.tf_num_parcelas = new CampoLimitadoSoDigito(5, this.item.getValor("num_parcelas")), cons);
	
	if(parcelado)
	this.tf_num_parcelas.setEnabled(true);
	else
	this.tf_num_parcelas.setEnabled(false);
		

	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(this.tf_novo_valor = new CampoMoeda(9, this.item.getValor("valor").toString()), cons);
		
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_save.setToolTipText("Salvar novo preço");
	p1.add(bt_save, cons);
			
			

		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		if(acaoPrincipal())
		dispose();
					
		}});

		
			
	List<JTextField> textFieldList = new ArrayList<JTextField>();	

	textFieldList.add(this.tf_novo_valor);
	textFieldList.add(this.tf_num_parcelas);
	
	Comuns.addEventoDeFoco(textFieldList);
		
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	
	
	

	
	protected boolean validacao(){
	

		if(this.parcelado){
		
			if(this.tf_num_parcelas.getText().length()==0 || 
					Calculo.stringZero(this.tf_num_parcelas.getText())){
				
			Mensagens.msgDeErro("Informe o número de parcelas.");
			Comuns.textFieldErroMode(this.tf_num_parcelas);
			return false;
			}
			
			if(Integer.parseInt(this.tf_num_parcelas.getText())>this.forma.getNum_max_parcelas()){
				
			Mensagens.msgDeErro("O número máximo de parcelas para esta forma de pagamento é "+this.forma.getNum_max_parcelas()+".");
			Comuns.textFieldErroMode(this.tf_num_parcelas);
			return false;
			}	
		}
	
	
		if(Calculo.stringZero(this.tf_novo_valor.getText())){
			
		Mensagens.msgDeErro("Informe o valor de pagamento.");
		Comuns.textFieldErroMode(this.tf_novo_valor);
		return false;
		}
		

		String aux = Calculo.soma(
							Calculo.subtrai(this.valor_acumulado_pagamento, this.item.getValor("valor")),
							this.tf_novo_valor.getText());	
		
		
		
		if(Calculo.stringParaDouble(aux)>
		Calculo.stringParaDouble(this.valor_total)){
		
		Mensagens.msgDeErro("A soma total dos valores de pagamento não deve ser maior que o valor total da compra.");
		Comuns.textFieldErroMode(this.tf_novo_valor);
		return false;
		}
						
	return true;	
	}
	
	
	

	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;

	item.setValor("valor", tf_novo_valor.getText());	
	item.setValor("num_parcelas", this.tf_num_parcelas.getText());	
	
	return true;
	}




	
	
}


