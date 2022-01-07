package principal.inventario;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import principal.estoque.FormNovoPrecoDeFornecedor;
import principal.fornecedores.FormNovoFornecedor;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Fornecedor;
import componentes.beans.Inventario;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormInventarioBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoLimitadoSoDigito tf_ean;
protected CampoLimitado tf_descricao;
protected CampoMoeda tf_valor_custo;

protected JTable tb_fornecedores;		
private DefaultTableModel modelo;		

protected CampoLimitado tf_fornecedor;
protected CampoMoeda tf_valor_fornecedor;
private JButton bt_add_fornecedor;
private JButton bt_add_novo_fornecedor;
private JButton bt_remover;
protected JButton bt_alterar;

protected int idFornecedorTemp;
	

protected List<Item> lista_fornecedores;


protected Inventario inventario;





	public FormInventarioBase(String title, int width, int height, Inventario inventario) {
		
	super(title, width, height);
	
	this.modelo = new DefaultTableModel(null, new String[]{"Código", "Fornecedor", "Preço"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	
	   
	this.inventario = inventario;   
	   
	   
	this.lista_fornecedores = new ArrayList<Item>();
			
	
	this.tb_fornecedores = new JTable(this.modelo);
	this.tb_fornecedores.setRowHeight(20); 
	
	this.tb_fornecedores.getColumnModel().getColumn(0).setPreferredWidth(110);
	this.tb_fornecedores.getColumnModel().getColumn(1).setPreferredWidth(400);
	this.tb_fornecedores.getColumnModel().getColumn(2).setPreferredWidth(120);	
	
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
	cons.weightx = 1;
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 0.2;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("EAN:"), cons);
	cons.weightx = 0.4;
	p1.add(new Rotulo("<html>Nome:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.4;
	p1.add(new Rotulo("Descricão:"), cons);	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.2;
	this.tf_ean = new CampoLimitadoSoDigito(13);
	p1.add(this.tf_ean, cons);
	
	cons.weightx = 0.4;
	this.tf_nome = new CampoLimitado(200);
	p1.add(this.tf_nome, cons);
		
	cons.weightx = 0.4;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_descricao = new CampoLimitado(250);
	p1.add(this.tf_descricao, cons);
	
	
	cons.gridwidth  = 1;	
	cons.weightx = 1;
	cons.weightx = 0.2;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Preço (R$):<font color=red>*</font></html>"), cons);
	cons.weightx = 0.4;
	p1.add(new Rotulo(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.4;
	p1.add(new Rotulo(""), cons);	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.2;
	this.tf_valor_custo = new CampoMoeda(6);
	p1.add(this.tf_valor_custo, cons);
	
	cons.weightx = 0.4;
	p1.add(new Rotulo(""), cons);
		
	cons.weightx = 0.4;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo(""), cons);
	
	
	
	
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 2;
	cons.weightx = 1;
	p2.add(new Rotulo("<html>Fornecedor:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = 1;
	p2.add(new Rotulo("<html>Preço Uni. (R$):<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new Rotulo(""), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_fornecedor = new CampoLimitado(200);
	this.tf_fornecedor.setEditable(false);
	p2.add(this.tf_fornecedor, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_add_fornecedor  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_add_fornecedor.setToolTipText("Escolher fornecedor");
	p2.add(bt_add_fornecedor, cons);
	

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	this.tf_valor_fornecedor = new CampoMoeda(6);
	p2.add(this.tf_valor_fornecedor, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.ipadx=30;
	cons.insets = new Insets(1, 1, 1, 1);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.bt_add_novo_fornecedor  = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/up.png")));
	bt_add_novo_fornecedor.setToolTipText("Adicionar fornecedor escolhido");
	p2.add(bt_add_novo_fornecedor, cons);

	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	this.tb_fornecedores.setBackground(Color.white); 
	p2.add(new JScrollPane(this.tb_fornecedores, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
		

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	cons.weighty  = 0;
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
	bt_alterar.setToolTipText("Alterar Preço de fornecedor selecionado");
	p3.add(bt_alterar, cons);
	
	this.bt_remover  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_remove_mini.png")));
	bt_remover.setToolTipText("Remover fornecedor selecionado");
	p3.add(bt_remover, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	p3.add(new Rotulo(""), cons);
	

	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar cliente");
	this.add(bt_save, cons);
		
	
		bt_alterar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		atualizaPrecoDeFornecedor();
		}});
	
	

		tf_ean.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		tf_nome.requestFocusInWindow();
		}});
	
	
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		

					
		this.tf_fornecedor.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
				
		Comuns.removeIndicadoresDeErro(tf_fornecedor);	
		bt_add_fornecedor.requestFocusInWindow();
		addFornecedor();
		}});	
			
			
		
		bt_add_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							    	
		addNovoFornecedor();
		}});
				
		
		
		

		bt_add_novo_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							    	
		addFornecedorNaLista();
		}});
				
		
		
	
		
		bt_remover.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		int index = tb_fornecedores	.getSelectedRow();
			
		
			if(index >= 0){
			
			lista_fornecedores.remove(index);
			atualizaTabelaDeFornecedores();
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para remoção.");

			
		}});
		
		
		
		
		tb_fornecedores.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
				
		if(e.getClickCount()==2)
		atualizaPrecoDeFornecedor();
				
		}}); 
		
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome);
	textFieldList.add(this.tf_fornecedor);
	textFieldList.add(this.tf_valor_fornecedor);

	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	

	
	
	protected boolean validation(){
	
		
		if(this.tf_nome.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome do item de inventário.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}
					
	
		
		if(this.tf_valor_custo.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o preço do item de inventário.");
		Comuns.textFieldErroMode(this.tf_valor_custo);
		return false;	
		}
		
		
		
		if(this.lista_fornecedores.size() == 0){
				
		Mensagens.msgDeErro("Escolha ao menos um fornecedor.");
		return false;	
		}		
		
	
	return true;	
	}
	
	
	



	private void addFornecedor(){
	
	Fornecedor retorno = new Fornecedor();
		
	FormDeSelecao<Fornecedor> selectionItemForm = 
								new FormDeSelecao<Fornecedor>("Adicionar Fornecedor", retorno, Fornecedor.class, "forn.status ='ATIVO'");
	selectionItemForm.mostrar();
							
		if(retorno != null && retorno.getId_fornecedor() > 0){
			
			for(Item aux: this.lista_fornecedores){
						
				if(Integer.parseInt(aux.getParamentro("id").toString()) == retorno.getId_fornecedor() ){
							
				Mensagens.msgDeErro("Você já adicionou este fornecedor.");
							
				this.idFornecedorTemp = 0;
				this.tf_fornecedor.setText("");	
				return;
				}
			}		
				
				
		this.idFornecedorTemp = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		}		
	}
		
		

	
	
	

	
	protected void addNovoFornecedor(){
		
	Fornecedor retorno = new Fornecedor();	
		
	FormNovoFornecedor form = new FormNovoFornecedor(retorno);
	form.mostrar();		
		
		if(retorno != null && retorno.getId_fornecedor() > 0){
			
			for(Item aux: this.lista_fornecedores){
				
				if(Integer.parseInt(aux.getParamentro("id").toString()) == retorno.getId_fornecedor() ){
							
				Mensagens.msgDeErro("Você já adicionou este fornecedor.");
							
				this.idFornecedorTemp = 0;
				this.tf_fornecedor.setText("");	
				return;
				}
			}		
				
				
		this.idFornecedorTemp = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		}	
	}		
				
	
	
	
	

	private void addFornecedorNaLista(){
		
	
		if(this.idFornecedorTemp==0){
			
		Mensagens.msgDeErro("Nenhum fornecedor selecionado.");	
		Comuns.textFieldErroMode(this.tf_fornecedor);
		return;	
		}
		
		
		if(Calculo.stringZero(this.tf_valor_fornecedor.getText())){
			
		Mensagens.msgDeErro("Informe o preço do item para o fornecedor selecionado.");
		Comuns.textFieldErroMode(this.tf_valor_fornecedor);
		return;	
		}
		
	
	Item item = new Item();	
	item.addParamentro("id", this.idFornecedorTemp);
	item.addParamentro("descricao", this.tf_fornecedor.getText());
	item.addParamentro("valor", this.tf_valor_fornecedor.getText());	
	this.lista_fornecedores.add(item);
	
	
	this.atualizaTabelaDeFornecedores();
	
	this.idFornecedorTemp = 0;
	this.tf_fornecedor.setText("");
	this.tf_valor_fornecedor.setText("");
	}
	
	
	
	
	
	
	protected void atualizaTabelaDeFornecedores(){
		
	this.modelo.setNumRows(0);
	
	String[] dados = new String[4];
	
		for(Item aux: this.lista_fornecedores){
	
		dados[0] = Comuns.getCod(Fornecedor.class, Integer.parseInt(aux.getParamentro("id").toString()));
		dados[1] = ""+aux.getParamentro("descricao").toString();
		dados[2] = "R$: "+Calculo.formataValor(aux.getParamentro("valor").toString());

		this.modelo.addRow(dados);	
		}
	}		
			
	
	
	
	
	
	protected void atualizaPrecoDeFornecedor(){
			
	int  linha = tb_fornecedores.getSelectedRow();
		if(linha >= 0 && linha < lista_fornecedores.size()){
		
		FormNovoPrecoDeFornecedor	 form = new FormNovoPrecoDeFornecedor(this.lista_fornecedores.get(linha));
		form.mostrar();
		
		atualizaTabelaDeFornecedores();	
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para alteração.");
	}
	
	
	

	
	
}
