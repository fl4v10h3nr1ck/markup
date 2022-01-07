package classes.compras;

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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import util.Mensagens;
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import classes.componentes.FormDeSelecao;
import classes.componentes.TabbedPaneModel;
import classes.componentes.beans.Item;
import classes.componentes.janelas.Dialogo;
import classes.compras.beans.Compra;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.comuns.Configuracoes;
import classes.dao.DAO;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.FornecedorComPreco;
import classes.estoque.beans.Fornecedor_x_Produto;
import classes.estoque.beans.Produto;
import classes.financeiro.PainelDeFormasDePagamento;




public abstract class FormDeCompraBase extends Dialogo{


	
private static final long serialVersionUID = 1L;


private JPanel painel_produtos;


protected JCheckBox somente_prod_em_vermelho;	
protected CampoLimitado tf_produto;
protected CampoLimitado tf_quant_atual;
protected int id_produto;

protected CampoLimitado tf_fornecedor;
protected int id_fornecedor;
protected CampoMoeda tf_preco;
protected CampoLimitadoSoDigito tf_quantidade;


protected JTable tb_produtos;		
protected DefaultTableModel modelo_produto;	

protected List<Item> listas_prod_adicionados;

protected JButton bt_add_prod;

protected JButton bt_remover_prod;

protected JButton bt_alterar_prod;

	
/*****************************************************************************/



protected PainelDeFormasDePagamento painel_pagamento;


	
/*****************************************************************************/


private JPanel painel_outras_infos;


protected CampoLimitado tf_status_compra;
protected CampoLimitado tf_status_entrega;
protected CampoData tf_data_compra;
protected CampoData tf_data_entrega;



/*****************************************************************************/


protected CampoLimitado tf_val_parcial;
protected CampoMoeda tf_outras_despesas;
protected CampoLimitado tf_total;

protected JTabbedPane tabbedPane;



protected Compra compra;	
	




	
	public FormDeCompraBase(String titulo) {
		
	this(titulo, null);
	}

	
	
	
	
	
	
	public FormDeCompraBase(String titulo, Compra compra) {
	
	super(titulo, 700, 550);

	this.compra  =compra;
	
	this.adicionarComponentes();
	}


	
	


	@Override
	public void adicionarComponentes() {
	
	painel_produtos	 = new JPanel(new GridBagLayout());	
	this.painel_pagamento = new PainelDeFormasDePagamento();
	this.painel_outras_infos = new JPanel(new GridBagLayout());	
		
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	
	cons.insets = new Insets(2, 2, 2, 2);	
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.add(this.somente_prod_em_vermelho= new JCheckBox("Selecionar somente produtos com estoque baixo.", true), cons);
	p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	
	this.painel_produtos.add(p1, cons);
	
	cons.gridwidth  = 3;
	cons.weightx = 0.8;
	cons.insets = new Insets(2, 2, 0, 2);	
	p1.add(new JLabel("<html>Selecione o produto:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.2;
	p1.add(new JLabel("QTDe Atual/QTDe Mín.:"), cons);
	
	cons.gridwidth  = 3;
	cons.weightx = 0.8;
	cons.insets = new Insets(2, 2, 2, 2);	
	p1.add(this.tf_produto= new CampoLimitado(100), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.2;
	p1.add(this.tf_quant_atual= new CampoLimitado(100), cons);
	
	this.tf_produto.setEditable(false);
	this.tf_quant_atual.setEditable(false);
	

	cons.gridwidth  = 1;
	cons.weightx = 0.4;
	cons.insets = new Insets(2, 2, 0, 2);	
	p1.add(new JLabel("<html>Fornecedor:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.2;
	p1.add(new JLabel("<html>Preço (R$):<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html>QTDe:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	

	cons.gridwidth  = 1;
	cons.weightx = 0.4;
	cons.insets = new Insets(2, 2, 2, 2);	
	p1.add(this.tf_fornecedor= new CampoLimitado(100), cons);
	cons.weightx = 0.2;
	p1.add(this.tf_preco= new CampoMoeda(5), cons);
	p1.add(this.tf_quantidade= new CampoLimitadoSoDigito(5), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;

	this.bt_add_prod = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_add_prod.setToolTipText("Adicionar produto selecionado");
	p1.add(bt_add_prod, cons);
	

	this.tf_fornecedor.setEditable(false);
	

	this.modelo_produto = new DefaultTableModel(null, new String[]{"Produto", "Fornecedor", "Preço (R$)", "QTDe", "Total (R$)"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	     
	this.listas_prod_adicionados = new ArrayList<Item>();
	
	this.tb_produtos = new JTable(this.modelo_produto);
	this.tb_produtos.setRowHeight(20); 
	this.tb_produtos.getColumnModel().getColumn(0).setPreferredWidth(180);
	this.tb_produtos.getColumnModel().getColumn(1).setPreferredWidth(250);	
	this.tb_produtos.getColumnModel().getColumn(2).setPreferredWidth(90);	
	this.tb_produtos.getColumnModel().getColumn(3).setPreferredWidth(90);	
	this.tb_produtos.getColumnModel().getColumn(4).setPreferredWidth(90);	
	

	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	this.painel_produtos.add(new JScrollPane(this.tb_produtos), cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx  = 1;
	cons.insets = new Insets(0, 2, 0, 2);	
	JPanel p1_1 = new JPanel(new GridBagLayout());
	this.painel_produtos.add(p1_1, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx  = 0;
	cons.gridwidth  = 1;
	this.bt_remover_prod = new JButton(new ImageIcon(getClass().getResource("/icons/fechar.png")));
	bt_remover_prod.setToolTipText("Remover produto selecionado");
	p1_1.add(bt_remover_prod, cons);
	
	this.bt_alterar_prod = new JButton(new ImageIcon(getClass().getResource("/icons/alterar.png")));
	bt_alterar_prod.setToolTipText("Alterar dados do produto selecionado");
	p1_1.add(bt_alterar_prod, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx  = 1;	
	p1_1.add(new JLabel(""), cons);
	
	
	/***********************************************************************************/

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);	
	this.painel_outras_infos.add(new JLabel("Status da Compra:"), cons);
	this.painel_outras_infos.add(new JLabel("Data Fechamento:"), cons);
	this.painel_outras_infos.add(new JLabel("Status Entrega:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_outras_infos.add(new JLabel("Data Entrega:"), cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);	
	this.painel_outras_infos.add(this.tf_status_compra= new CampoLimitado(100), cons);
	this.painel_outras_infos.add(this.tf_data_compra= new CampoData(), cons);
	this.painel_outras_infos.add(this.tf_status_entrega= new CampoLimitado(100), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_outras_infos.add(this.tf_data_entrega= new CampoData(), cons);
	
	this.tf_status_compra.setEditable(false);
	this.tf_status_entrega.setEditable(false);
	this.tf_data_compra.setEditable(false);
	this.tf_data_entrega.setEditable(false);
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;	
	this.painel_outras_infos.add(new JLabel(""), cons);
	
	
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 0, 0);			
	TabbedPaneModel tabbedPaneModel = new TabbedPaneModel( new Color(190,190,190) , 
															Configuracoes.preferencias.cor_fundo_painel_janela, 
															Color.black , Color.black);
	this.tabbedPane = new JTabbedPane();
	tabbedPane.setUI( tabbedPaneModel );
	tabbedPane.addTab("Adicionar Produtos", null, this.painel_produtos);
	tabbedPane.addTab("Formas de Pagamento", null, this.painel_pagamento);
	tabbedPane.addTab("Status e Entrega", null, this.painel_outras_infos);
	this.add(tabbedPane, cons);	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 0, 2, 0);	
	this.add(new JSeparator(SwingConstants.HORIZONTAL),cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);	
	this.add(new JLabel("<html>Valor Parcial (R$):<font color=red>*</font></html>"), cons);
	cons.weightx = 0.2;
	this.add(new JLabel("<html>Outras Despesas (R$):<font color=red>*</font></html>"), cons);
	this.add(new JLabel("<html>Valor Total (R$):<font color=red>*</font></html>"), cons);
	this.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.add(new JLabel(""), cons);
	

	
	
	
	cons.gridwidth  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);	
	this.add(this.tf_val_parcial= new CampoLimitado(25), cons);
	this.add(this.tf_outras_despesas= new CampoMoeda(6), cons);
	this.add(this.tf_total= new CampoLimitado(25), cons);
	this.add(new JLabel(""), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	
	JButton bt_salvar = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_salvar.setToolTipText("Salvar informações de compra");
	this.add(bt_salvar, cons);
	
	
	this.tf_val_parcial.setEditable(false);
	this.tf_total.setEditable(false);
	
	
	

		this.tf_produto.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
					
		if(!tf_produto.isEnabled())
		return;	
			
		addProduto();
		}});
		
		
		
		
		this.tf_fornecedor.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
						
		if(!tf_fornecedor.isEnabled())
		return;	
				
		addFornecedor();
		}});
	


			
		
		bt_add_prod.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		addNovoProduto();	
		}});
			
		
		
		
		
		bt_remover_prod.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
								
		int index = tb_produtos.getSelectedRow();

			if(index >= 0){
						
			listas_prod_adicionados.remove(index);
			atualizaTabelaDeProdutos();
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para remoção.");		
		}});
		
		
	
		
		
		
		bt_alterar_prod.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		alterarProduto();	
		}});
			
		
		
		
		
		
		
		this.tf_outras_despesas.addFocusListener( new FocusAdapter(){			
		@Override 
		public void focusLost(FocusEvent e) {
		
		setValorFinal();
		}});
	


	
		

		this.tb_produtos.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount()==2)	
		alterarProduto();	
		}}); 	
		
		
		

		

		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
			if(acaoPrincipal()){
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}
		}});
		
		
		
		
		
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	textFieldList.add(this.tf_fornecedor);
	textFieldList.add(this.tf_preco);
	textFieldList.add(this.tf_produto);
	textFieldList.add(this.tf_quantidade);
	textFieldList.add(this.tf_data_entrega);
	textFieldList.add(this.tf_data_compra);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	}



	
	
	
	
	private void addProduto(){
		
	String subquery = "";	
	if(this.somente_prod_em_vermelho.isSelected())
	subquery = " AND (prod.quantidade IS NULL || prod.quantidade<prod.quant_min_estoque)";
		
	Produto retorno = new Produto();
		
	FormDeSelecao<Produto> selectionItemForm = 
							new FormDeSelecao<Produto>("Adicionar Produto", retorno, Produto.class, "prod.ativo='S'"+subquery);
	selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_produto() > 0){
			
			for(Item item: this.listas_prod_adicionados){
					
				if(Integer.parseInt(item.getValor("id_produto"))==retorno.getId_produto()){
					
				Mensagens.msgDeErro("Você já adicionou este produto.");
				Comuns.textFieldErroMode(this.tf_produto);
				return;
				}	
			}	
			
		Produto produto = new DAO<Produto>(Produto.class).get(retorno.getId_produto());
			
			if(produto!=null){
			
			this.id_produto = produto.getId_produto();	
			this.tf_produto.setText(produto.getDescricao_abreviada());
			this.tf_quant_atual.setText(produto.getQuantidade()+"/"+produto.getQuant_min_estoque());
			
			this.tf_preco.setText("");
			
			this.id_fornecedor = 0;	
			this.tf_fornecedor.setText("");
			}
		}			
	}
	
	
	
	
	
	
	private void addFornecedor(){
		
		if(this.id_produto==0){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Você precisa primeiro selecionar um produto.");
		Comuns.textFieldErroMode(this.tf_produto);
		return;
		}	
	
	DAO<Fornecedor_x_Produto> fXp_dao =  new DAO<Fornecedor_x_Produto>(Fornecedor_x_Produto.class);
		
	List<Fornecedor_x_Produto> lista_fornecedores = fXp_dao.get(null, "fornXprod.fk_produto="+this.id_produto, null);
			
	String subquery = "";
	
		if(lista_fornecedores.size()>0){
		
		subquery = " AND (";	
			
		for(int i =0;  i < lista_fornecedores.size(); i++)	
		subquery += "forn.id_fornecedor="+lista_fornecedores.get(i).getFk_fornecedor()+(i<lista_fornecedores.size()-1?" OR ":"");
		
		subquery += ")";
		
		FornecedorComPreco retorno = new FornecedorComPreco();
		
		FormDeSelecao<FornecedorComPreco> selectionItemForm = 
									new FormDeSelecao<FornecedorComPreco>("Adicionar Fornecedor", retorno, FornecedorComPreco.class, "forn.ativo='S'"+subquery);
			selectionItemForm.mostrar();
								
			if(retorno != null && retorno.getId_fornecedor() > 0){
			
			this.id_fornecedor = retorno.getId_fornecedor();	
			this.tf_fornecedor.setText(retorno.getNome_fantasia_apelido());
			this.tf_preco.setText(retorno.getPreco());
			}	
		
		}		
		else{
		
		Fornecedor retorno = new Fornecedor();
				
		FormDeSelecao<Fornecedor> selectionItemForm = 
									new FormDeSelecao<Fornecedor>("Adicionar Fornecedor", retorno, Fornecedor.class, "forn.ativo='S'");
			selectionItemForm.mostrar();
								
			if(retorno != null && retorno.getId_fornecedor() > 0){
			
			this.id_fornecedor = retorno.getId_fornecedor();	
			this.tf_fornecedor.setText(retorno.getNome_fantasia_apelido());
			this.tf_preco.setText("");
			}
		}
	}
	
	
	
	
	

	private void addNovoProduto(){
		
		if(this.id_produto==0){
		
		this.tabbedPane.setSelectedIndex(0);
				
			
		Mensagens.msgDeErro("Você precisa primeiro selecionar um produto.");
		Comuns.textFieldErroMode(this.tf_produto);
		return;
		}	
		
		if(this.id_fornecedor==0){
		
		this.tabbedPane.setSelectedIndex(0);
					
		Mensagens.msgDeErro("Nenhum fornecedor selecionado.");
		Comuns.textFieldErroMode(this.tf_fornecedor);
		return;
		}
		
		if(Calculo.stringZero(this.tf_preco.getText())){
		
		this.tabbedPane.setSelectedIndex(0);
					
		Mensagens.msgDeErro("Informe o preço de custo do produto.");
		Comuns.textFieldErroMode(this.tf_preco);
		return;
		}
			
			
		if(Calculo.stringZero(this.tf_quantidade.getText())){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Informe a quantidade a ser adquirida do produto.");
		Comuns.textFieldErroMode(this.tf_quantidade);
		return;
		}
		
	Item item = new Item();
	
	item.setValor("id_produto", this.id_produto);	
	item.setValor("nome_produto", this.tf_produto.getText());	
		
	item.setValor("id_fornecedor", this.id_fornecedor);	
	item.setValor("nome_fornecedor", this.tf_fornecedor.getText());	
		
	item.setValor("preco", this.tf_preco.getText());	
	item.setValor("quant", this.tf_quantidade.getText());	
	
	this.listas_prod_adicionados.add(item);
	
	this.id_produto = 0;	
	this.tf_produto.setText("");	
	this.id_fornecedor = 0;	
	this.tf_fornecedor.setText("");	
	this.tf_preco.setText("");	
	this.tf_quantidade.setText("");
	this.tf_quant_atual.setText("");
	
	this.atualizaTabelaDeProdutos();
	}
	
	
	
	
	
	
	private void alterarProduto(){
		
	int linha = tb_produtos.getSelectedRow();

		if(linha >= 0 && linha < listas_prod_adicionados.size()){
		
		FormAlteraDadosDeProduto	 form = new FormAlteraDadosDeProduto(listas_prod_adicionados.get(linha));
		form.mostrar();
		
		atualizaTabelaDeProdutos();	
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para alteração.");		
		
	}
	
	
	
	
	
	
	
	protected void atualizaTabelaDeProdutos(){
		
	this.modelo_produto.setNumRows(0);
		
	String[] dados = new String[5];
	
	String valor = "0.00";
	
		for(Item item: this.listas_prod_adicionados){
		
		dados[0] = item.getValor("nome_produto");
		dados[1] = item.getValor("nome_fornecedor");
		dados[2] = item.getValor("preco");
		dados[3] = item.getValor("quant");
		
		String aux = Calculo.multiplica(dados[2], dados[3]);
		
		dados[4] = Calculo.formataValor(aux);
		
		valor = Calculo.soma(aux, valor);

		this.modelo_produto.addRow(dados);	
		}
		
	this.tf_val_parcial.setText(Calculo.formataValor(valor));
	
	this.setValorFinal();
	}
	
	
	
	

	
	protected void setValorFinal(){
	
	String aux = Calculo.formataValor(Calculo.soma(tf_val_parcial.getText(),
	   									tf_outras_despesas.getText()));	
			
	tf_total.setText(aux);	
	
	this.painel_pagamento.setValorTotal(aux);
	
	this.painel_pagamento.setValorDePagamentoRestante();
	}
	
	
	
	
	
	
	protected boolean validacao(){
		
		if(this.listas_prod_adicionados.size()==0){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Selecione ao menos um produto.");
		Comuns.textFieldErroMode(this.tf_produto);
		return false;	
		}
		
		
	String aux = "0.00";

	for(Item item: this.painel_pagamento.getItens())
	aux = Calculo.soma(aux, item.getValor("valor"));

		
		if(Calculo.stringParaDouble(aux)>
		Calculo.stringParaDouble(this.tf_total.getText())){
		
		this.tabbedPane.setSelectedIndex(1);	
			
		Mensagens.msgDeErro("A soma total dos valores de pagamento é maior que o valor total da compra. \nAjuste as formas de pagamento.");
		return false;
		}
		
		
		if(Calculo.stringParaDouble(aux)<
		Calculo.stringParaDouble(this.tf_total.getText())){
		
		this.tabbedPane.setSelectedIndex(1);	
			
		Mensagens.msgDeErro("A soma total dos valores de pagamento é menor que o valor total da compra. \nAjuste as formas de pagamento.");
		return false;
		}
		
		
	return true;
	}
	
	

	
	
	
}
