package principal.compras;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Fornecedor;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;



public class FormAlteraItemDeCompra extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_cod;
protected CampoLimitadoSoDigito tf_quant;
protected CampoMoeda tf_valor;
protected CampoLimitado tf_fornecedor;
protected int idFornecedor;


public Item item;




	public FormAlteraItemDeCompra(Item item) {
		
	super("Atualizar Item de Compra", 650, 150);
	
	this.item = item;
	
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
	cons.weighty  = 0;
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("Código:"), cons);
	p1.add(new Rotulo("<html>QTDe:<font color=red>*</font></html>"), cons);
	p1.add(new Rotulo("<html>Valor:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo("Fornecedor:"), cons);
	
		
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_cod = new CampoLimitado(200, this.item.getParamentro("item_descricao").toString());
	p1.add(this.tf_cod, cons);
	this.tf_cod.setEditable(false);
	
	this.tf_quant = new CampoLimitadoSoDigito(4, this.item.getParamentro("quantidade").toString());
	p1.add(this.tf_quant, cons);
	
	this.tf_valor = new CampoMoeda(8, this.item.getParamentro("valor").toString());
	p1.add(this.tf_valor, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_fornecedor = new CampoLimitado(200);
	p1.add(this.tf_fornecedor, cons);
	this.tf_fornecedor.setEditable(false);
	
		if(this.item.getParamentro("tipo")!=null && this.item.getParamentro("tipo").toString().compareTo("DESPESA")!=0){
	
			if(this.item.getParamentro("id_fornecedor")!=null){
			
			this.idFornecedor = Integer.parseInt(this.item.getParamentro("id_fornecedor").toString());
			this.tf_fornecedor.setText(Comuns.getCod(Fornecedor.class, Integer.parseInt(this.item.getParamentro("id_fornecedor").toString())));	
			}
		}
		else
		this.tf_fornecedor.setEnabled(false);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar cliente");
	p1.add(bt_save, cons);
			
			

		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		if(acaoPrincipal())
		dispose();	
		}});

		
		
			
			
		this.tf_fornecedor.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
							
		Comuns.removeIndicadoresDeErro(tf_fornecedor);	
		bt_save.requestFocusInWindow();
		addFornecedor();
		}});

		
	List<JTextField> textFieldList = new ArrayList<JTextField>();	

	textFieldList.add(this.tf_quant);
	
	Comuns.addEventoDeFoco(textFieldList);	
	}
	
	
	
	

	
	
	protected void addFornecedor(){
	
	String tipo = this.item.getParamentro("tipo").toString();	
			
	String subquery = "";
	
	if(tipo.compareTo("ESTOQUE")==0)
	subquery = " and forn.id_fornecedor IN (select fk_fornecedor FROM fornecedor_x_produto WHERE fk_produto="+this.item.getParamentro("id_item").toString()+")";	
	else if(tipo.compareTo("INVENTARIO")==0)
	subquery = " and forn.id_fornecedor IN (select fk_fornecedor FROM inventario_x_fornecedor WHERE fk_inventario="+this.item.getParamentro("id_item").toString()+")";	
	
			
	Fornecedor retorno = new Fornecedor();
		
	FormDeSelecao<Fornecedor> selectionItemForm = 
								new FormDeSelecao<Fornecedor>("Adicionar Fornecedor", retorno, Fornecedor.class, "forn.status='ATIVO'"+subquery);
	selectionItemForm.mostrar();
							
		if(retorno != null && retorno.getId_fornecedor() > 0){
										
		this.idFornecedor = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		}		
	}
	
	

	
	

	
	protected boolean validation(){
	

		if(this.tf_quant.getText().length() == 0 || Integer.parseInt(this.tf_quant.getText()) == 0){
			
		Mensagens.msgDeErro("Informe uma quantidade maior que zero.");
		Comuns.textFieldErroMode(this.tf_quant);
		return false;	
		}
		
	return true;	
	}





	@Override
	public boolean acaoPrincipal() {
	
	item.addParamentro("id_fornecedor", this.idFornecedor);	
	item.addParamentro("quantidade", this.tf_quant.getText());
	item.addParamentro("valor", this.tf_valor.getText());
	return true;
	}
	
	
	

	
	
}

