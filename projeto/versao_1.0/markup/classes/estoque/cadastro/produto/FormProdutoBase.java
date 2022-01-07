package classes.estoque.cadastro.produto;

import java.awt.Color;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import classes.componentes.FormDeSelecao;
import classes.componentes.ImagemGestor;
import classes.componentes.TabbedPaneModel;
import classes.componentes.beans.Item;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.comuns.Configuracoes;
import classes.comuns.Mensagens;
import classes.estoque.beans.Categoria;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.Produto;
import classes.estoque.beans.Subcategoria;
import classes.estoque.cadastro.categoria.FormNovaCategoria;
import classes.estoque.cadastro.fornecedores.FormNovoFornecedor;
import classes.estoque.cadastro.subcategoria.FormNovaSubcategoria;






public abstract class FormProdutoBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


public static final String[] medidas = {"UN", "Kg", "L"};




/*** infos gerais ***/

protected CampoLimitado tf_nome;
protected CampoLimitado tf_descricao_abreviada;
protected CampoLimitado tf_marca;
protected CampoLimitado tf_especificacao;
protected CampoLimitado tf_secao;

protected CampoLimitadoSoDigito tf_cod_ean;
protected CampoLimitadoSoDigito tf_cod_ncm;
protected CampoLimitadoSoDigito tf_cod_cfop;

protected CampoLimitado tf_categoria;
protected int idCategoria;

protected CampoLimitado tf_subcategoria;
protected int idSubCategoria;


protected JPanel painel_infos_gerais;



/******************* medidas ***********************/


protected CampoMoeda tf_medida;
protected JComboBox<String> tf_unid_medida;
protected CampoLimitadoSoDigito tf_peso;
protected JComboBox<String> venda_fracionada;
protected CampoLimitadoSoDigito unidades_pacote;
protected CampoLimitadoSoDigito tf_garantia;
protected CampoLimitadoSoDigito tf_quant_min_estoque;

protected JCheckBox usa_balanca;

protected CampoLimitadoSoDigito tf_cod_balança;


protected JPanel painel_pesos_medidas;

/******************* preco ***********************/


protected CampoMoeda tf_custo;
protected CampoMoeda lucro;
protected CampoMoeda valor_fracionado;
protected CampoMoeda valor_final;


protected JPanel painel_preco;



/****************** fornecedores ************************/


protected JTable tb_fornecedores;		
protected DefaultTableModel modelo;		
protected CampoLimitado tf_fornecedor;
protected CampoMoeda tf_valor_fornecedor;
protected int idFornecedorTemp;
protected List<Item> lista_fornecedores;


protected JPanel painel_fornecedores;



/****************** tributacao ************************/




protected JPanel painel_tributos;


protected Produto retorno;


private JTabbedPane tabbedPane;


protected ImagemGestor imagemGestor;




	public FormProdutoBase(String titulo) {

	this(titulo, null);
	}


	
	
	

	public FormProdutoBase(String titulo, Produto retorno) {
		
	super(titulo, 700, 480);
	
	this.retorno = retorno;
	}
	

	
	
	

	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	
	this.painel_infos_gerais = new JPanel(new GridBagLayout());
	this.painel_pesos_medidas = new JPanel(new GridBagLayout());
	this.painel_preco = new JPanel(new GridBagLayout());
	this.painel_fornecedores = new JPanel(new GridBagLayout());
	this.painel_tributos = new JPanel(new GridBagLayout());
	
	
	cons.fill = GridBagConstraints.BOTH;	
	cons.weightx  = 0.48;
	cons.weighty  = 1;
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 5, 2, 0);
	this.painel_infos_gerais.add(imagemGestor  = new ImagemGestor(200, 200), cons);
	
	cons.weightx  = 0.52;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(2, 0, 2, 0);
	JPanel aux0 = new JPanel(new GridBagLayout()); 
	this.painel_infos_gerais.add(aux0, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;	
	cons.weightx  = 1;
	cons.weighty  = 0;
	JPanel aux3 = new JPanel(new GridBagLayout()); 
	this.painel_infos_gerais.add(aux3, cons);
	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;	
	cons.weightx  = 1;
	cons.weighty  = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	
	cons.insets = new Insets(2, 2, 0, 2);
	aux0.add(new JLabel("<html>Nome:<font color=red>*</font></html>"), cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	aux0.add(this.tf_nome = new CampoLimitado(100), cons);
	
	cons.insets = new Insets(2, 2, 0, 2);
	aux0.add(new JLabel("<html>Descrição Abreviada:<font color=red>*</font></html>"), cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	aux0.add(this.tf_descricao_abreviada = new CampoLimitado(Comuns.TAM_DESCRICAO_ABREVIADA_PRODUTO), cons);
	
	cons.insets = new Insets(2, 2, 0, 2);
	aux0.add(new JLabel("Marca:"), cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	aux0.add(this.tf_marca = new CampoLimitado(150), cons);
	
	cons.insets = new Insets(2, 2, 0, 2);
	aux0.add(new JLabel("Especificações:"), cons);
	
	cons.insets = new Insets(2, 2, 2, 2);
	aux0.add(this.tf_especificacao = new CampoLimitado(150), cons);
	
	cons.insets = new Insets(2, 2, 0, 2);
	aux0.add(new JLabel("Seção:"), cons);
	
	cons.insets = new Insets(2, 2, 2, 2);
	aux0.add(this.tf_secao = new CampoLimitado(150), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	aux3.add(new JLabel("<html>EAN:<font color=red>*</font></html>"), cons);
	aux3.add(new JLabel("<html>NCM:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	aux3.add(new JLabel("<html>CFOP:<font color=red>*</font></html>"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	aux3.add(this.tf_cod_ean = new CampoLimitadoSoDigito(13), cons);
	aux3.add(this.tf_cod_ncm = new CampoLimitadoSoDigito(10), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	aux3.add(this.tf_cod_cfop = new CampoLimitadoSoDigito(4), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel aux1 = new JPanel(new GridBagLayout()); 
	aux3.add(aux1, cons);
	
	
	cons.gridwidth  = 2;
	cons.insets = new Insets(2, 2, 0, 2);
	aux1.add(new JLabel("Categoria:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	aux1.add(new JLabel("Subcategoria:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	aux1.add(this.tf_categoria = new CampoLimitado(150), cons);
	this.tf_categoria.setEditable(false);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.WEST;
	JButton bt_categoria  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_categoria.setToolTipText("Cadastrar nova categoria de produto");
	aux1.add(bt_categoria, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	aux1.add(this.tf_subcategoria = new CampoLimitado(150), cons);
	this.tf_subcategoria.setEditable(false);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	JButton bt_subcategoria  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_subcategoria.setToolTipText("Cadastrar nova subcategoria de produto");
	aux1.add(bt_subcategoria, cons);

	
	cons.fill = GridBagConstraints.BOTH;	
	cons.weightx  = 1;
	cons.weighty  = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	this.painel_infos_gerais.add(new JLabel(""), cons);
	
	
/*************************************************************/
	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;	
	cons.weightx  = 1;
	cons.weighty  = 0;
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	this.painel_pesos_medidas.add(new JLabel("<html>Unidade de Medida:<font color=red>*</font></html>"), cons);
	this.painel_pesos_medidas.add(new JLabel("Peso/volume/QTDe:"), cons);
	this.painel_pesos_medidas.add(new JLabel("QTDe mínima em estoque:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_pesos_medidas.add(new JLabel("Garantia (em dias):"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	this.painel_pesos_medidas.add(this.tf_unid_medida = new JComboBox<String>(FormProdutoBase.medidas), cons);
	this.painel_pesos_medidas.add(this.tf_medida = new CampoMoeda(5), cons);
	this.painel_pesos_medidas.add(this.tf_quant_min_estoque = new CampoLimitadoSoDigito(3, Comuns.config_do_sistema.getQuant_min_estoque_padrao()), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_pesos_medidas.add(this.tf_garantia = new CampoLimitadoSoDigito(3), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	this.painel_pesos_medidas.add(new JLabel("Venda Fracionada:"), cons);
	this.painel_pesos_medidas.add(new JLabel("QTDe por Pacote/fardo:"), cons);
	this.painel_pesos_medidas.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_pesos_medidas.add(new JLabel("Código da Balança:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	this.painel_pesos_medidas.add(this.venda_fracionada = new JComboBox<String>(new String[]{"NAO", "SIM"}), cons);
	this.painel_pesos_medidas.add(this.unidades_pacote = new CampoLimitadoSoDigito(5), cons);
	this.painel_pesos_medidas.add(this.usa_balanca = new JCheckBox("Usa Balança de medida.", false), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_pesos_medidas.add(this.tf_cod_balança = new CampoLimitadoSoDigito(7), cons);

	cons.fill = GridBagConstraints.BOTH;	
	cons.weightx  = 1;
	cons.weighty  = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	this.painel_pesos_medidas.add(new JLabel(""), cons);
	
	
	
/*************************************************************/	
	
	

	cons.fill = GridBagConstraints.HORIZONTAL;	
	cons.weightx  = 1;
	cons.weighty  = 0;
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	this.painel_preco.add(new JLabel("Custo médio (R$):"), cons);
	this.painel_preco.add(new JLabel("Lucro (%):"), cons);
	this.painel_preco.add(new JLabel("Preço Final (R$):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_preco.add(new JLabel("Preço Final fração (R$):"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	this.painel_preco.add(this.tf_custo = new CampoMoeda(5, "0"), cons);
	this.painel_preco.add(this.lucro = new CampoMoeda(5, Comuns.config_do_sistema.getLucro_padrao()), cons);
	this.painel_preco.add(this.valor_final = new CampoMoeda(5), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.painel_preco.add(this.valor_fracionado = new CampoMoeda(5), cons);
	
	this.tf_custo.setEditable(false);
	this.valor_final.setEditable(false);
	this.valor_fracionado.setEditable(false);
	
	
	cons.fill = GridBagConstraints.BOTH;	
	cons.weightx  = 1;
	cons.weighty  = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	this.painel_preco.add(new JLabel(""), cons);
	

	
	cons.fill = GridBagConstraints.HORIZONTAL;	
	cons.weighty  = 0;
	cons.insets = new Insets(0, 5, 0, 5);
	this.painel_preco.add(new JLabel("<html><font color=red>O preço final do produto é calculado baseado no seu custo médio. Este valor é atualizado a cada reposição de estoque.</font></html>"), cons);
	
	
	
	
/*************************************************************/	

	
	
	this.modelo = new DefaultTableModel(null, new String[]{"Fornecedor", "Preço (R$)"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	     
	this.lista_fornecedores = new ArrayList<Item>();
			
	this.tb_fornecedores = new JTable(this.modelo);
	this.tb_fornecedores.setRowHeight(20); 
	
	this.tb_fornecedores.getColumnModel().getColumn(0).setPreferredWidth(500);
	this.tb_fornecedores.getColumnModel().getColumn(1).setPreferredWidth(150);	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.7;
	this.painel_fornecedores.add(new JLabel("<html>Fornecedor:<font color=red>*</font></html>"), cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.painel_fornecedores.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.3;
	this.painel_fornecedores.add(new JLabel("<html>Preço (R$):<font color=red>*</font></html>"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	cons.weightx = 0.7;
	this.tf_fornecedor = new CampoLimitado(200);
	this.tf_fornecedor.setEditable(false);
	this.painel_fornecedores.add(this.tf_fornecedor, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	JButton bt_cadastrar_novo_fornecedor  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_cadastrar_novo_fornecedor.setToolTipText("Cadastrar novo fornecedor");
	this.painel_fornecedores.add(bt_cadastrar_novo_fornecedor, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.3;
	this.tf_valor_fornecedor = new CampoMoeda(6);
	this.painel_fornecedores.add(this.tf_valor_fornecedor, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_add_fornecedor  = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/novo.png")));
	bt_add_fornecedor.setToolTipText("Adicionar fornecedor escolhido");
	this.painel_fornecedores.add(bt_add_fornecedor, cons);


	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	this.painel_fornecedores.add(new JScrollPane(this.tb_fornecedores, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx  = 1;
	cons.insets = new Insets(2, 2, 2, 0);
	JPanel aux2 = new JPanel(new GridBagLayout()); 
	this.painel_fornecedores.add(aux2, cons);
	
	
	cons.gridwidth  = 1;	
	cons.fill = GridBagConstraints.NONE;
	cons.ipadx = 0;
	cons.weighty  = 0;
	cons.weightx  = 0;
	JButton bt_alterar_fornecedor  = new JButton(new ImageIcon(getClass().getResource("/icons/alterar.png")));
	bt_alterar_fornecedor.setToolTipText("Alterar Preço de fornecedor selecionado");
	aux2.add(bt_alterar_fornecedor, cons);
	
	JButton bt_remover_fornecedor  = new JButton(new ImageIcon(getClass().getResource("/icons/remover.png")));
	bt_remover_fornecedor.setToolTipText("Remover fornecedor selecionado");
	aux2.add(bt_remover_fornecedor, cons);
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	aux2.add(new JLabel(""), cons);
	
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
				
	TabbedPaneModel tabbedPaneModel = new TabbedPaneModel( new Color(190,190,190) , 
															Configuracoes.preferencias.cor_fundo_painel_janela, 
															Color.black , Color.black);
	this.tabbedPane = new JTabbedPane();
	tabbedPane.setUI( tabbedPaneModel );
	tabbedPane.addTab("Informeções Gerais", null, this.painel_infos_gerais);
	tabbedPane.addTab("Pesos e Medidas", null, this.painel_pesos_medidas);
	tabbedPane.addTab("Fornecedores", null, this.painel_fornecedores);
	tabbedPane.addTab("Preço", null, this.painel_preco);
	tabbedPane.addTab("Tributos", null, this.painel_tributos);
	this.add(tabbedPane, cons);	
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.ipadx = 10;
	cons.weighty  = 0;
	cons.weightx  = 0;
	cons.insets = new Insets(2, 2, 5, 5);
	cons.anchor  = GridBagConstraints.EAST;
	JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_salvar.setToolTipText("Salvar dados do produto");
	this.add(bt_salvar, cons);
	
	
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
			if(acaoPrincipal()){
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}
		}});
		
		
	
	
	
		bt_categoria.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		addNovaCategoria();
		}});
		
		
		
		
		this.tf_categoria.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
		
		if(!tf_categoria.isEnabled())
		return;	
	
		addCategoria();
		}});
		
		
	
		
	
		this.tf_subcategoria.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
					
		if(!tf_subcategoria.isEnabled())
		return;	
			
		addSubCategoria();
		}});
			
			
			
		
		
		bt_subcategoria.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					
		addNovaSubCategoria();
		}});
				
				
		

		this.venda_fracionada.addItemListener(new ItemListener(){
	
		@Override
		public void itemStateChanged(ItemEvent arg0) {
		
		setTipoDeVenda();
		}});
		
	
		
		

		
		
		this.tf_fornecedor.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
					
		if(!tf_fornecedor.isEnabled())
		return;	
			
		selecionarFornecedor();
		}});
			
		
		
		
		bt_cadastrar_novo_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							    	
		addNovoFornecedor();
		}});
						
				

		
		bt_add_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						    	
		addFornecedor();
		}});
				
		
		
		
		bt_alterar_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		atualizaPrecoDeFornecedor();
		}});
		
		
		
		tb_fornecedores.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
				
		if(e.getClickCount()==2)
		atualizaPrecoDeFornecedor();	
		}}); 
			

		
		
		bt_remover_fornecedor.addActionListener( new ActionListener(){
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
			
		
		
		
		this.lucro.addFocusListener(new FocusAdapter(){
		@Override
		public void focusLost( FocusEvent event ){
								
		setValorFinal();		
		}});
		
		
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome);
	textFieldList.add(tf_descricao_abreviada);
	
	textFieldList.add(tf_cod_ean);
	textFieldList.add(tf_cod_ncm);
	textFieldList.add(tf_cod_cfop);
	
	textFieldList.add(this.tf_quant_min_estoque);
	textFieldList.add(unidades_pacote);
	
	textFieldList.add(this.lucro);
	
	textFieldList.add(this.tf_fornecedor);
	textFieldList.add(this.tf_valor_fornecedor);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.setTipoDeVenda();
	}
	
	
	
	
	
	
	private void setTipoDeVenda(){
	
		if(String.valueOf(venda_fracionada.getSelectedItem()).compareTo("SIM")==0){
		
		unidades_pacote.setEnabled(true);

		unidades_pacote.setText("");
		valor_fracionado.setText("");
		
		this.usa_balanca.setEnabled(true);
		
		this.tf_cod_balança.setEnabled(true);
		}
		else{
		
		unidades_pacote.setEnabled(false);
		unidades_pacote.setText("");
		valor_fracionado.setText("");
		
		this.usa_balanca.setEnabled(false);
		
		this.usa_balanca.setSelected(false);
		
		this.tf_cod_balança.setEnabled(false);
		this.tf_cod_balança.setText("");
		}	
	}
	
	
	


	
	protected boolean validacao(){

		if(this.tf_nome.getText().length() == 0){
		
		this.tabbedPane.setSelectedIndex(0);
					
		Mensagens.msgDeErro("Informe o nome do produto.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}
		
		
		if(this.tf_descricao_abreviada.getText().length() == 0){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Informe a descrição abreviada do produto.");
		Comuns.textFieldErroMode(this.tf_descricao_abreviada);
		return false;	
		}
		
		
		if(this.tf_cod_ean.getText().length() != 13){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Informe o código EAN do produto (Código de barras).");
		Comuns.textFieldErroMode(this.tf_cod_ean);
		return false;	
		}
		
		if(this.tf_cod_ncm.getText().length() == 0){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Informe o código NCM do produto.");
		Comuns.textFieldErroMode(this.tf_cod_ean);
		return false;	
		}
			
			
		if(this.tf_cod_cfop.getText().length() != 4){
		
		this.tabbedPane.setSelectedIndex(0);		
			
		Mensagens.msgDeErro("Informe o código CFOP do produto.");
		Comuns.textFieldErroMode(this.tf_cod_cfop);
		return false;	
		}	
		
		
/***************************************/		
		
		
		if(this.tf_quant_min_estoque.getText().length() ==0){
		
		this.tabbedPane.setSelectedIndex(1);
					
		Mensagens.msgDeErro("Informe a quantidade mínima em estoque.");
		Comuns.textFieldErroMode(this.tf_quant_min_estoque);
		return false;	
		}
		
		
		
		if(String.valueOf(this.venda_fracionada.getSelectedItem()).compareTo("SIM")==0){
		
			if(this.unidades_pacote.getText().length() == 0){
			
			this.tabbedPane.setSelectedIndex(1);
					
			Mensagens.msgDeErro("Informe a quantidade por pacote do produto.");
			Comuns.textFieldErroMode(this.unidades_pacote);
			return false;	
			}
		}

		
/***************************************/		
		
		
		
		if(this.lucro.getText().length() == 0){
		
		this.tabbedPane.setSelectedIndex(3);		
			
		Mensagens.msgDeErro("Informe o valor do lucro sobre o produto.");
		Comuns.textFieldErroMode(this.lucro);
		return false;	
		}


	return true;	
	}
	
	
	

	
	
	private void addCategoria(){
	
	Categoria retorno = new Categoria();
		
	FormDeSelecao<Categoria> selectionItemForm = 
						new FormDeSelecao<Categoria>("Adicionar Categoria", retorno, Categoria.class, "cat.ativo='S'");
	selectionItemForm.mostrar();
					
		if(retorno != null && retorno.getId_categoria() > 0){
								
		this.idCategoria = retorno.getId_categoria();
		this.tf_categoria.setText(retorno.getDescricao());
		}		
	}
	
	

	
	
	
	private void addNovaCategoria(){
	
	Categoria retorno = new Categoria();
		
	FormNovaCategoria novo = new FormNovaCategoria(retorno);
	novo.mostrar();		
	
		if(retorno != null && retorno.getId_categoria() > 0){
									
		this.idCategoria = retorno.getId_categoria();
		this.tf_categoria.setText(retorno.getDescricao());
		}	
	}	
	
	
	
	
	
	
	
	private void addSubCategoria(){
		
	Subcategoria retorno = new Subcategoria();
		
	FormDeSelecao<Subcategoria> selectionItemForm = 
							new FormDeSelecao<Subcategoria>("Adicionar Subcategoria", retorno, Subcategoria.class, "subcat.ativo='S'");
	selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_subcategoria() > 0){
									
		this.idSubCategoria = retorno.getId_subcategoria();
		this.tf_subcategoria.setText(retorno.getDescricao());
		}	
	}
	
	

	

	
	private void addNovaSubCategoria(){
		
	Subcategoria retorno = new Subcategoria();
		
	FormNovaSubcategoria form = new FormNovaSubcategoria(retorno);
	form.mostrar();	
	
		if(retorno != null && retorno.getId_subcategoria() > 0){
		
		this.idSubCategoria = retorno.getId_subcategoria();
		this.tf_subcategoria.setText(retorno.getDescricao());
		}
	}	
	
	
	
	

	
	

	private void selecionarFornecedor(){
	
	Fornecedor retorno = new Fornecedor();
		
	FormDeSelecao<Fornecedor> selectionItemForm = 
								new FormDeSelecao<Fornecedor>("Adicionar Fornecedor", retorno, Fornecedor.class, "forn.ativo ='S'");
	selectionItemForm.mostrar();
							
		if(retorno != null && retorno.getId_fornecedor() > 0){
			
			for(Item aux: this.lista_fornecedores){
						
				if(Integer.parseInt(aux.getValor("id").toString()) == retorno.getId_fornecedor() ){
							
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
		
		
	
	
	

	
	private void addNovoFornecedor(){
		
	Fornecedor retorno = new Fornecedor();	
		
	FormNovoFornecedor form = new FormNovoFornecedor(retorno);
	form.mostrar();		
		
		if(retorno != null && retorno.getId_fornecedor() > 0){
			
		this.idFornecedorTemp = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		}	
	}		
				
	
	
	
	

	
	private void addFornecedor(){
		
	
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
	item.setValor("id", this.idFornecedorTemp);
	item.setValor("nome", this.tf_fornecedor.getText());
	item.setValor("valor", this.tf_valor_fornecedor.getText());	
	this.lista_fornecedores.add(item);
	
	this.atualizaTabelaDeFornecedores();
	
	this.idFornecedorTemp = 0;
	this.tf_fornecedor.setText("");
	this.tf_valor_fornecedor.setText("");
	}
	
	
	
	
	

	
	protected void atualizaTabelaDeFornecedores(){
		
	this.modelo.setNumRows(0);
	
	String[] dados = new String[2];
	
		for(Item aux: this.lista_fornecedores){
	
		dados[0] = ""+aux.getValor("nome").toString();
		dados[1] = Calculo.formataValor(aux.getValor("valor").toString());

		this.modelo.addRow(dados);	
		}
	}		
			
	

	
	
	

	
	private void atualizaPrecoDeFornecedor(){
			
	int  linha = tb_fornecedores.getSelectedRow();
		if(linha >= 0 && linha < lista_fornecedores.size()){
		
		FormAlteraPrecoDeFornecedor	 form = new FormAlteraPrecoDeFornecedor(this.lista_fornecedores.get(linha));
		form.mostrar();
		
		atualizaTabelaDeFornecedores();	
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para alteração.");
	}

	
	
	
	
	
	protected void setValorFinal(){
		
	String valor_final = Calculo.formataValor(Calculo.soma(this.tf_custo.getText(), 
									Calculo.calcPorcentagem(this.tf_custo.getText(), 
									this.lucro.getText(), 3)));
	
	if(this.venda_fracionada.getSelectedIndex()==1)	
	this.valor_fracionado.setText(Calculo.formataValor(Calculo.dividi(valor_final, this.unidades_pacote.getText(), 3)));	
	
	this.valor_final.setText(valor_final);	
	}

	
	
	
}
