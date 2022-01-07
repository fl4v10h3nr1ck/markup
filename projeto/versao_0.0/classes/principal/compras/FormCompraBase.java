package principal.compras;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import principal.despesas.FormNovaDespesa;
import principal.estoque.FormNovoProduto;
import principal.inventario.FormNovoInventario;
import DAO.DAO;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Compra;
import componentes.beans.Despesa;
import componentes.beans.Fornecedor;
import componentes.beans.Fornecedor_X_Produto;
import componentes.beans.Inventario;
import componentes.beans.Inventario_Fornecedor;
import componentes.beans.Item;
import componentes.beans.Produto;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormCompraBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_descricao;
protected CampoMoeda tf_val_total;
protected CampoLimitadoSoDigito tf_quant_total;
protected JComboBox<String> tipo;


protected CampoLimitado tf_item;
protected JButton bt_add_item;
protected int idItem;
protected CampoLimitadoSoDigito tf_quant;
protected CampoMoeda tf_valor;

protected CampoLimitado tf_fornecedor;
protected JButton bt_add_fornecedor;
protected int idFornecedor;

protected JButton bt_save;
protected JButton bt_adicionar;


protected JTable tb_itens;		
protected DefaultTableModel modelo;	

protected List<Item> lista_itens;


protected JButton bt_remover;
protected JButton bt_alterar;


protected JButton bt_novo_item;


protected Compra retorno;






	public FormCompraBase(String title, int width, int height, Compra retorno) {
		
	super(title, width, height);

	this.modelo = new DefaultTableModel(null, new String[]{"Item", "Tipo", "QTDe", "Fornecedor", "Preço (R$)"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	
	this.retorno = retorno;   
	   
	this.lista_itens = new ArrayList<Item>();
			
	
	this.tb_itens = new JTable(this.modelo);
	this.tb_itens.setRowHeight(20); 
	
	this.tb_itens.getColumnModel().getColumn(0).setPreferredWidth(200);
	this.tb_itens.getColumnModel().getColumn(1).setPreferredWidth(120);
	this.tb_itens.getColumnModel().getColumn(2).setPreferredWidth(120);	
	this.tb_itens.getColumnModel().getColumn(3).setPreferredWidth(200);	
	this.tb_itens.getColumnModel().getColumn(4).setPreferredWidth(120);	
	
	}

	
	
	

	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);	
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Descrição:<font color=red>*</font></html>"), cons);
	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_descricao = new CampoLimitado(250);
	p1.add(this.tf_descricao, cons);
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("Tipo:"), cons);
	p1.add(new Rotulo("QTDe Total:"), cons);
	p1.add(new Rotulo("Valor Total (R$):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo(""), cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tipo = new JComboBox<String>(new String[]{"INVENTARIO", "ESTOQUE", "DESPESA"});
	p1.add(this.tipo, cons);
	
	this.tf_quant_total = new CampoLimitadoSoDigito(8, "0");
	p1.add(this.tf_quant_total, cons);
	this.tf_quant_total.setEnabled(false);
	
	this.tf_val_total = new CampoMoeda(8, "0,00");
	p1.add(this.tf_val_total, cons);
	this.tf_val_total.setEnabled(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel(""), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.3;
	p2.add(new Rotulo("<html>Item:<font color=red>*</font></html>"), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	p2.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.3;
	p2.add(new Rotulo("Fornecedor:"), cons);
	cons.weightx = 0.2;
	p2.add(new Rotulo("<html>QTDe:<font color=red>*</font></html>"), cons);
	p2.add(new Rotulo("<html>Preço (R$):<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	p2.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.3;
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_item = new CampoLimitado(200);
	p2.add(this.tf_item, cons);
	this.tf_item.setEditable(false);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.ipadx = 0;
	this.bt_novo_item  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_novo_item.setToolTipText("Adicionar novo item");
	p2.add(bt_novo_item, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.3;
	this.tf_fornecedor = new CampoLimitado(200);
	p2.add(this.tf_fornecedor, cons);
	this.tf_fornecedor.setEditable(false);
	
	cons.weightx = 0.2;
	this.tf_quant = new CampoLimitadoSoDigito(4);
	p2.add(this.tf_quant, cons);
	this.tf_quant.setText("1");
	
	this.tf_valor = new CampoMoeda(8);
	p2.add(this.tf_valor, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.ipadx = 15;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.bt_adicionar  = new JButton("Adicionar");
	bt_adicionar.setToolTipText("Adicionar item à compra");
	p2.add(bt_adicionar, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty = 1;
	cons.ipadx = 0;
	p2.add(new JScrollPane(this.tb_itens, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);

	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(0, 2, 2, 2);	
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p2.add(p3, cons);
	

	cons.gridwidth  = 1;	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.ipadx = 0;
	cons.weighty  = 0;
	cons.weightx  = 0;
	this.bt_alterar  = new JButton(new ImageIcon(getClass().getResource("/icons/alterar_mini.png")));
	bt_alterar.setToolTipText("Alterar item selecionado");
	p3.add(bt_alterar, cons);
	
	this.bt_remover  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_remove_mini.png")));
	bt_remover.setToolTipText("Remover item selecionado");
	p3.add(bt_remover, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new Rotulo(""), cons);
	
	
	cons.weighty = 0;
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	this.bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar Fornecedor");
	this.add(bt_save, cons);
		
		
	
		bt_alterar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		atualizaItem();
		}});
	
		
		
		bt_remover.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		int index = tb_itens.getSelectedRow();

			if(index >= 0){
						
			lista_itens.remove(index);
			atualizaTabelaDeItem();
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de itens para remoção.");		
		}});
		
		


		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		

			
		
		this.tf_item.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
			
		Comuns.removeIndicadoresDeErro(tf_item);	
		tf_quant.requestFocusInWindow();
		addItem();
		}});
		
		
		

		
		
		this.tf_fornecedor.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
						
		Comuns.removeIndicadoresDeErro(tf_fornecedor);	
		tf_quant.requestFocusInWindow();
		addFornecedor();
		}});

		
		
			
		this.bt_adicionar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					    	
		addItemACompra();
		}});
				
			
		
		
		this.tipo.addItemListener( new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent arg0){
		
		tf_item.setText("");
		idItem= 0;
		tf_quant.setText("");
		tf_valor.setText("");
		tf_fornecedor.setText("");
		idFornecedor = 0;
		
		
		if(tipo.getSelectedItem().toString().compareTo("DESPESA")==0)
		tf_fornecedor.setEnabled(false);
		else
		tf_fornecedor.setEnabled(true);	
		
		lista_itens.clear();

		atualizaTabelaDeItem();
		}});
		
	
		
		
		tb_itens.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
				
		if(e.getClickCount()==2){
		
		if(retorno!=null && 
				retorno.getStatus()!=null && 
				 (retorno.getStatus().compareTo("FECHADO")==0 ||
				 retorno.getStatus().compareTo("CANCELADO")==0))
		return;
		
		atualizaItem();
		}
				
		}}); 	
		
		
		
		this.bt_novo_item.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		addNovoItem();
		}});
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	

	textFieldList.add(tf_quant);
	textFieldList.add(this.tf_item);
	textFieldList.add(tf_descricao);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	

	
	protected boolean validation(){

		if(this.tf_descricao.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe uma descrição para a compra.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;
		}		
	
		if(this.lista_itens.size() == 0){
			
		Mensagens.msgDeErro("Selecione ao menos um item para alista de compra.");
		return false;
		}	
	
	return true;	
	}
	
	
	

	
	
	protected void addItem(){
	
		if(String.valueOf(this.tipo.getSelectedItem()).compareTo("ESTOQUE")==0){
	
		Produto retorno = new Produto();
			
		FormDeSelecao<Produto> selectionItemForm = 
								new FormDeSelecao<Produto>("Adicionar Produto de Estoque", retorno, Produto.class, "prod.status='ATIVO'");
		selectionItemForm.mostrar();
							
			if(retorno != null && retorno.getId_produto() > 0){
										
			this.idItem = retorno.getId_produto();
			this.tf_item.setText(retorno.getDescricao_abreviada());
			}	
		
		return;
		}
		
		if(String.valueOf(this.tipo.getSelectedItem()).compareTo("INVENTARIO")==0){
			
		Inventario retorno = new Inventario();
			
		FormDeSelecao<Inventario> selectionItemForm = 
									new FormDeSelecao<Inventario>("Adicionar Item de Inventário", retorno, Inventario.class, "inv.status='ATIVO'");
		selectionItemForm.mostrar();
								
			if(retorno != null && retorno.getId_inventario() > 0){
											
			this.idItem = retorno.getId_inventario();
			this.tf_item.setText(retorno.getNome());
			}	
		
		return;
		}
		
		if(String.valueOf(this.tipo.getSelectedItem()).compareTo("DESPESA")==0){
		
		Despesa retorno = new Despesa();
			
		FormDeSelecao<Despesa> selectionItemForm = 
										new FormDeSelecao<Despesa>("Adicionar Despesa", retorno, Despesa.class, "desp.status='ATIVO'");
		selectionItemForm.mostrar();
									
			if(retorno != null && retorno.getId_despesa() > 0){
												
			this.idItem = retorno.getId_despesa();
			this.tf_item.setText(retorno.getDescricao());
			
			this.tf_valor.setText(retorno.getValor_base());
			}	
			
		return;
		}
	}
	
	
	

	
		
	protected void addFornecedor(){

		if(this.idItem==0){
		
		Mensagens.msgDeErro("Selecione primeiramente o item/produto.");
		Comuns.textFieldErroMode(this.tf_item);
		return;	
		}
		
		
	String subquery = "";
	
	if(String.valueOf(this.tipo.getSelectedItem()).compareTo("ESTOQUE")==0)
	subquery = " and forn.id_fornecedor IN (select fk_fornecedor FROM fornecedor_x_produto WHERE fk_produto="+this.idItem+")";	
	else if(String.valueOf(this.tipo.getSelectedItem()).compareTo("INVENTARIO")==0)
	subquery = " and forn.id_fornecedor IN (select fk_fornecedor FROM inventario_x_fornecedor WHERE fk_inventario="+this.idItem+")";	
	
			
	Fornecedor retorno = new Fornecedor();
		
	FormDeSelecao<Fornecedor> selectionItemForm = 
								new FormDeSelecao<Fornecedor>("Adicionar Fornecedor", retorno, Fornecedor.class, "forn.status='ATIVO'"+subquery);
	selectionItemForm.mostrar();
							
		if(retorno != null && retorno.getId_fornecedor() > 0){
										
		this.idFornecedor = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		
		
			if(String.valueOf(this.tipo.getSelectedItem()).compareTo("ESTOQUE")==0){
			
			List<Fornecedor_X_Produto> lista = new 
					DAO<Fornecedor_X_Produto>(Fornecedor_X_Produto.class).get(null, "fornXprod.fk_fornecedor="+retorno.getId_fornecedor()+" and fornXprod.fk_produto="+this.idItem, null);	
				
			if(lista.size()>0)
			this.tf_valor.setText(lista.get(0).getPreco());
							
			}	
			else if(String.valueOf(this.tipo.getSelectedItem()).compareTo("INVENTARIO")==0){
				
			List<Inventario_Fornecedor> lista = new 
						DAO<Inventario_Fornecedor>(Inventario_Fornecedor.class).get(null, "invXforn.fk_fornecedor="+retorno.getId_fornecedor()+" and invXforn.fk_inventario="+this.idItem, null);	
					
			if(lista.size()>0)
			this.tf_valor.setText(lista.get(0).getValor());	
			}
		}		
	}
	
	
	
	
	
	
	protected void addItemACompra(){
	
		
		if(this.idItem==0){
			
		Mensagens.msgDeErro("Selecione um item para adicionar à lista de compra.");
		Comuns.textFieldErroMode(this.tf_item);
		return;		
		}	
		
		if(this.tf_quant.getText().length() == 0 || Integer.parseInt(this.tf_quant.getText()) == 0){
				
		Mensagens.msgDeErro("Informe uma quantidade maior que zero.");
		Comuns.textFieldErroMode(this.tf_quant);
		return;	
		}
			
	Item item  = new Item();
	
	item.addParamentro("id_fornecedor", this.idFornecedor);	
	item.addParamentro("quantidade", this.tf_quant.getText());
	item.addParamentro("tipo", this.tipo.getSelectedItem().toString());
	item.addParamentro("valor", this.tf_valor.getText());
	item.addParamentro("id_item", this.idItem);
	item.addParamentro("item_descricao", this.tf_item.getText());
	
	this.lista_itens.add(item);	
		
	this.atualizaTabelaDeItem();
	}
		
		
	
	
	
	protected void calculaValores(){

	String  quant = "0";
	String  valor = "0,00";
	
		for(Item aux: this.lista_itens){
		
		quant = Calculo.soma(quant, aux.getParamentro("quantidade").toString());
		valor = Calculo.soma(valor, Calculo.multiplica(aux.getParamentro("quantidade").toString(), aux.getParamentro("valor").toString()));
		}
			
	this.tf_quant_total.setText(quant);
	this.tf_val_total.setText(Calculo.formataValor(valor));
	}
	
	
	
	
	

	protected void atualizaTabelaDeItem(){
		
	this.modelo.setNumRows(0);
	String[] dados = new String[5];
	
		for(Item aux: this.lista_itens){
	
		dados[0] = aux.getParamentro("item_descricao").toString();
		dados[1] = aux.getParamentro("tipo").toString();
		dados[2] = aux.getParamentro("quantidade").toString();
		dados[3] = Comuns.getCod(Fornecedor.class, Integer.parseInt(aux.getParamentro("id_fornecedor").toString()));
		dados[4] = "R$: "+Calculo.formataValor(aux.getParamentro("valor").toString());
		
		this.modelo.addRow(dados);	
		}
		
	this.calculaValores();
	}		
			
	
	
	
	
	
	public void atualizaItem(){
	
		
	int  linha = this.tb_itens.getSelectedRow();
		if(linha >= 0 && linha < this.lista_itens.size()){
		
		FormAlteraItemDeCompra	 form = new FormAlteraItemDeCompra(lista_itens.get(linha));
		form.mostrar();
		
		atualizaTabelaDeItem();	
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de itens para alteração.");	
	
	}
	
	
	
	
	
	
	
	private void addNovoItem(){
		
		if(String.valueOf(this.tipo.getSelectedItem()).compareTo("ESTOQUE")==0){
			
		Produto retorno = new Produto();
			
		FormNovoProduto form  = new FormNovoProduto(retorno);
		form.mostrar();
								
			if(retorno != null && retorno.getId_produto() > 0){
											
			this.idItem = retorno.getId_produto();
			this.tf_item.setText(retorno.getDescricao_abreviada());
			}		
		}	
		else if(String.valueOf(this.tipo.getSelectedItem()).compareTo("INVENTARIO")==0){
		
		Inventario retorno = new Inventario();
			
		FormNovoInventario form = new FormNovoInventario(retorno); 
		form.mostrar();
									
			if(retorno != null && retorno.getId_inventario() > 0){
												
			this.idItem = retorno.getId_inventario();
			this.tf_item.setText(retorno.getNome());
			}	
	
		}
		else if(String.valueOf(this.tipo.getSelectedItem()).compareTo("DESPESA")==0){
		
		Despesa retorno = new Despesa();
			
		FormNovaDespesa form = new FormNovaDespesa(retorno);
		form.mostrar();
										
			if(retorno != null && retorno.getId_despesa() > 0){
													
			this.idItem = retorno.getId_despesa();
			this.tf_item.setText(retorno.getDescricao());
				
			this.tf_valor.setText(retorno.getValor_base());
			}			
		}
	}
	
	
	
	
	
	
}
