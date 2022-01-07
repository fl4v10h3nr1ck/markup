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



public class FormAlteraDadosDeProduto extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_produto;
protected CampoLimitado tf_fornecedor;
protected CampoLimitadoSoDigito   tf_nova_quant;
protected CampoMoeda   tf_novo_valor;


public Item item;




	public FormAlteraDadosDeProduto(Item item) {
		
	super("Atualizar Dados de Produto", 700, 150);
	
	this.item  = item;
	
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
	cons.weightx = 0.3;
	p1.add(new JLabel("Produto:"), cons);
	p1.add(new JLabel("Fornecedor:"), cons);
	cons.weightx = 0.2;
	p1.add(new JLabel("QTDe:"), cons);	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Preço (R$):"), cons);	
		
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.3;		
	p1.add(this.tf_produto = new CampoLimitado(200, this.item.getValor("nome_produto")), cons);
	this.tf_produto.setEnabled(false);
	
	p1.add(this.tf_fornecedor = new CampoLimitado(200, this.item.getValor("nome_fornecedor")), cons);
	this.tf_fornecedor.setEnabled(false);
	cons.weightx = 0.2;
	p1.add(this.tf_nova_quant = new CampoLimitadoSoDigito(4, this.item.getValor("quant")), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(this.tf_novo_valor = new CampoMoeda(8, this.item.getValor("preco").toString()), cons);
		
	
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
	textFieldList.add(this.tf_nova_quant);

	Comuns.addEventoDeFoco(textFieldList);
		
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	
	
	

	
	protected boolean validacao(){
	
		if(Calculo.stringZero(this.tf_nova_quant.getText())){
			
		Mensagens.msgDeErro("Informe a nova quantidade do produto para este fornecedor.");
		Comuns.textFieldErroMode(this.tf_nova_quant);
		return false;	
		}
		

		if(Calculo.stringZero(this.tf_novo_valor.getText())){
		
		Mensagens.msgDeErro("Informe o novo preço do produto para este fornecedor.");
		Comuns.textFieldErroMode(this.tf_novo_valor);
		return false;	
		}

		
	return true;	
	}
	
	
	

	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;

	item.setValor("preco", tf_novo_valor.getText());	
	item.setValor("quant", tf_nova_quant.getText());	
	
	return true;
	}




	
	
}


