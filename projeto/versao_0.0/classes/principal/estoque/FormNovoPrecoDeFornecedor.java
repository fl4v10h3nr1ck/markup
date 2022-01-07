package principal.estoque;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.CampoLimitado;
import classes.CampoMoeda;
import componentes.Rotulo;
import componentes.beans.Fornecedor;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;



public class FormNovoPrecoDeFornecedor extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_cod_item;
protected CampoLimitado tf_nome_fornecedor;
protected CampoMoeda   tf_novo_valor;


public Item item;




	public FormNovoPrecoDeFornecedor(Item item) {
		
	super("Atualizar Preço de Fornecedor", 600, 150);
	
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
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
			
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("Código:"), cons);
	p1.add(new Rotulo("Fornecedor:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo("Novo Preço Uni. (R$):"), cons);	
		
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_cod_item = new CampoLimitado(100, Comuns.getCod(Fornecedor.class, Integer.parseInt(this.item.getParamentro("id").toString())));
	p1.add(this.tf_cod_item, cons);
	this.tf_cod_item.setEditable(false);
			
	this.tf_nome_fornecedor = new CampoLimitado(200, this.item.getParamentro("descricao").toString());
	p1.add(this.tf_nome_fornecedor, cons);
	this.tf_nome_fornecedor.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_novo_valor = new CampoMoeda(8, this.item.getParamentro("valor").toString());
	p1.add(this.tf_novo_valor, cons);
		
		
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
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

		
	Comuns.addEventoDeFoco(textFieldList);
		
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	
	
	

	
	protected boolean validation(){
	

		if(this.tf_novo_valor.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o novo preço do item para este fornecedor.");
		Comuns.textFieldErroMode(this.tf_novo_valor);
		return false;	
		}
		
	return true;	
	}
	
	
	

	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	item.setParamentro("valor", tf_novo_valor.getText());	
	
	return true;
	}




	
	
}

