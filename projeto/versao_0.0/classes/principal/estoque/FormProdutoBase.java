package principal.estoque;


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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import principal.estoque.categoria.FormNovaCategoria;
import principal.estoque.subcategoria.FormNovaSubcategoria;
import principal.fornecedores.FormNovoFornecedor;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Categoria;
import componentes.beans.Fornecedor;
import componentes.beans.Item;
import componentes.beans.Produto;
import componentes.beans.Subcategoria;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormProdutoBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitadoSoDigito tf_cod_ean;
protected CampoLimitado tf_nome;
protected CampoLimitado tf_descricao;

protected CampoLimitado tf_descricao_abreviada;
protected CampoLimitado tf_marca;
protected CampoLimitado tf_especificacao;
protected CampoLimitadoSoDigito tf_cod_ncm;

protected JComboBox<String> venda_fracionada;
protected JComboBox<String> tipo_venda_fracionada;
protected CampoLimitadoSoDigito unidades_pacote;
protected CampoLimitadoSoDigito tf_peso;

/******************************************/

protected CampoLimitado tf_categoria;
protected int idCategoria;
protected JButton bt_nova_categoria;

protected CampoLimitado tf_subcategoria;
protected int idSubCategoria;
protected JButton bt_add_subcategoria;

protected CampoLimitado tf_secao;
protected CampoLimitadoSoDigito tf_garantia;
protected CampoMoeda tf_volume;

/******************************************/


protected JTable tb_fornecedores;		
protected DefaultTableModel modelo;		
protected CampoLimitado tf_fornecedor;
protected CampoMoeda tf_valor_fornecedor;
protected JButton bt_add_fornecedor;
protected JButton bt_add_novo_fornecedor;
protected JButton bt_remover;
protected JButton bt_alterar;
protected int idFornecedorTemp;
protected List<Item> lista_fornecedores;

/******************************************/


protected CampoMoeda tf_custo;
protected CampoMoeda lucro;
protected CampoMoeda markup;
protected CampoMoeda valor_fracionado;
protected CampoMoeda valor_final;


protected CampoMoeda valor_icms_porcento;
protected CampoMoeda valor_pis_porcento;
protected CampoMoeda valor_cofins_porcento;

protected CampoMoeda valor_ipi_porcento;
protected CampoMoeda valor_csll_porcento;
protected CampoMoeda valor_irpj_porcento;

protected CampoMoeda valor_issqn_porcento;
protected CampoMoeda valor_comissao_porcento;
protected CampoMoeda valor_administrativa_porcento;

protected CampoLimitadoSoDigito tf_cod_cfop;


protected Produto retorno;





	public FormProdutoBase(String title, int width, int height, Produto retorno) {
		
	super(title, width, height);
	
	this.retorno = retorno;
	
	this.modelo = new DefaultTableModel(null, new String[]{"Código", "Fornecedor", "Custo (R$)"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	   
	   
	this.lista_fornecedores = new ArrayList<Item>();
			
	
	this.tb_fornecedores = new JTable(this.modelo);
	this.tb_fornecedores.setRowHeight(20); 
	
	this.tb_fornecedores.getColumnModel().getColumn(0).setPreferredWidth(110);
	this.tb_fornecedores.getColumnModel().getColumn(1).setPreferredWidth(300);
	this.tb_fornecedores.getColumnModel().getColumn(2).setPreferredWidth(120);	
	}
	

	
	
	

	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	JPanel p_geral_1 = new JPanel(new GridBagLayout());
	p_geral_1.setBackground(Preferencias.COR_DE_FUNDO_FRAME);
	this.add(p_geral_1, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	JPanel p_geral_2 = new JPanel(new GridBagLayout());
	p_geral_2.setBackground(Preferencias.COR_DE_FUNDO_FRAME);
	this.add(p_geral_2, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_geral_1.add(p1, cons);
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_geral_1.add(p2, cons);

	JPanel p6 = new JPanel(new GridBagLayout());
	p6.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_geral_1.add(p6, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty = 1;
	cons.weightx  = 0.4;
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 0);
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_geral_2.add(p3, cons);	
	
	cons.weighty = 1;
	cons.weightx  = 0.6;
	cons.insets = new Insets(2, 0, 2, 2);
	JPanel p4 = new JPanel(new GridBagLayout());
	p4.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p_geral_2.add(p4, cons);
	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weightx  = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Código EAN:<font color=red>*</font></html>"), cons);
	p1.add(new Rotulo("<html>Nome:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new Rotulo("Descrição:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_cod_ean = new CampoLimitadoSoDigito(13);
	p1.add(this.tf_cod_ean, cons);
	
	this.tf_nome = new CampoLimitado(200);
	p1.add(this.tf_nome, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_descricao = new CampoLimitado(400);
	p1.add(this.tf_descricao, cons);
		
		
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Descrição Abrev.:<font color=red>*</font></html>"), cons);
	p1.add(new Rotulo("Marca:"), cons);	
	p1.add(new Rotulo("Especificações:"), cons);	
	p1.add(new Rotulo("<html>Código CFOP:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new Rotulo("Código NCM:"), cons);	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_descricao_abreviada = new CampoLimitado( Comuns.tam_descrica_abreviada_produto);
	p1.add(this.tf_descricao_abreviada, cons);
	
	this.tf_marca = new CampoLimitado(50);
	p1.add(this.tf_marca, cons);
	
	this.tf_especificacao = new CampoLimitado(150);
	p1.add(this.tf_especificacao, cons);
	
	this.tf_cod_cfop = new CampoLimitadoSoDigito(4);
	p1.add(this.tf_cod_cfop, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_cod_ncm = new CampoLimitadoSoDigito(8);
	p1.add(this.tf_cod_ncm, cons);
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Venda Fracionada:<font color=red>*</font></html>"), cons);
	p1.add(new Rotulo("<html>Tipo de Fração:<font color=red>*</font></html>"), cons);	
	p1.add(new Rotulo("<html>Uni. Pacote:<font color=red>*</font></html>"), cons);	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new Rotulo("<html>Peso total (g):<font color=red>*</font></html>"), cons);	
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.venda_fracionada = new JComboBox<String>(new String[]{"NAO", "SIM"});
	p1.add(this.venda_fracionada, cons);
	
	this.tipo_venda_fracionada = new JComboBox<String>(new String[]{"SELECIONE", "UNIDADE", "KILO"});
	p1.add(this.tipo_venda_fracionada, cons);
	this.tipo_venda_fracionada.setEnabled(false);
	
	this.unidades_pacote = new CampoLimitadoSoDigito(6);
	p1.add(this.unidades_pacote, cons);
	this.unidades_pacote.setEnabled(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_peso = new CampoLimitadoSoDigito(8);
	p1.add(this.tf_peso, cons);
	this.tf_peso.setEnabled(false);
	
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 2;	
	p2.add(new Rotulo("Seção (estoque):"), cons);
	p2.add(new Rotulo("Garantia (em dias):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(new Rotulo("Volume (m³):"), cons);
	
	cons.gridwidth  = 2;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_secao = new CampoLimitado(200);
	p2.add(this.tf_secao, cons);
	
	this.tf_garantia = new CampoLimitadoSoDigito(3);
	p2.add(this.tf_garantia, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_volume = new CampoMoeda(5);
	p2.add(this.tf_volume, cons);
		
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new Rotulo("Categoria:"), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	p2.add(new Rotulo(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(new Rotulo("Subcategoria:"), cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_categoria = new CampoLimitado(200);
	this.tf_categoria.setEditable(false);
	p2.add(this.tf_categoria, cons);
		
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_nova_categoria  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_nova_categoria.setToolTipText("Adicionar uma categoria ao produto");
	p2.add(bt_nova_categoria, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	this.tf_subcategoria = new CampoLimitado(200);
	this.tf_subcategoria.setEditable(false);
	p2.add(this.tf_subcategoria, cons);
		
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.WEST;
	this.bt_add_subcategoria  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_add_subcategoria.setToolTipText("Adicionar uma subcategoria ao produto");
	p2.add(bt_add_subcategoria, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p6.add(new Rotulo("ICMS (%):"), cons);
	p6.add(new Rotulo("PIS (%):"), cons);
	p6.add(new Rotulo("COFINS (%):"), cons);
	p6.add(new Rotulo("IPI (%):"), cons);
	p6.add(new Rotulo("CSLL (%):"), cons);
	p6.add(new Rotulo("IRPJ (%):"), cons);
	p6.add(new Rotulo("ISSQN (%):"), cons);
	p6.add(new Rotulo("Comissão (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p6.add(new Rotulo("Custo Adm. (%):"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.valor_icms_porcento = new CampoMoeda(6);
	p6.add(this.valor_icms_porcento, cons);
	
	this.valor_pis_porcento = new CampoMoeda(6);
	p6.add(this.valor_pis_porcento, cons);
	
	this.valor_cofins_porcento = new CampoMoeda(6);
	p6.add(this.valor_cofins_porcento, cons);
		
	this.valor_ipi_porcento = new CampoMoeda(6);
	p6.add(this.valor_ipi_porcento, cons);
	
	
	this.valor_csll_porcento = new CampoMoeda(6);
	p6.add(this.valor_csll_porcento, cons);
	
	this.valor_irpj_porcento = new CampoMoeda(6);
	p6.add(this.valor_irpj_porcento, cons);
	
	
	this.valor_issqn_porcento = new CampoMoeda(6);
	p6.add(this.valor_issqn_porcento, cons);
	
	
	this.valor_comissao_porcento = new CampoMoeda(6);
	p6.add(this.valor_comissao_porcento, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.valor_administrativa_porcento = new CampoMoeda(6);
	p6.add(this.valor_administrativa_porcento, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 2;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.7;
	p4.add(new Rotulo("<html>Fornecedor:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = 1;
	cons.weightx = 0.3;
	p4.add(new Rotulo("<html>Preço (R$):<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0;
	p4.add(new Rotulo(""), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.7;
	this.tf_fornecedor = new CampoLimitado(200);
	this.tf_fornecedor.setEditable(false);
	p4.add(this.tf_fornecedor, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_add_fornecedor  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_add_fornecedor.setToolTipText("Escolher fornecedor");
	p4.add(bt_add_fornecedor, cons);
	

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.3;
	this.tf_valor_fornecedor = new CampoMoeda(6);
	p4.add(this.tf_valor_fornecedor, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.ipadx=10;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.bt_add_novo_fornecedor  = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/up.png")));
	bt_add_novo_fornecedor.setToolTipText("Adicionar fornecedor escolhido");
	p4.add(bt_add_novo_fornecedor, cons);

	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	p4.add(new JScrollPane(this.tb_fornecedores, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	JPanel p5 = new JPanel(new GridBagLayout());
	p5.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p4.add(p5, cons);

	cons.gridwidth  = 1;	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.ipadx = 0;
	cons.weighty  = 0;
	cons.weightx  = 0;
	this.bt_alterar  = new JButton(new ImageIcon(getClass().getResource("/icons/alterar_mini.png")));
	bt_alterar.setToolTipText("Alterar Preço de fornecedor selecionado");
	p5.add(bt_alterar, cons);
	
	this.bt_remover  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_remove_mini.png")));
	bt_remover.setToolTipText("Remover fornecedor selecionado");
	p5.add(bt_remover, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	p5.add(new Rotulo(""), cons);
	
	
	cons.gridwidth  = 1;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new Rotulo("<html>Custo Total (R$):<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new Rotulo("<html>Lucro (%):<font color=red>*</font></html>"), cons);

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_custo = new CampoMoeda(8);
	p3.add(this.tf_custo, cons);
		
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.lucro = new CampoMoeda(8);
	this.lucro.setText(Comuns.lucroPadraoDeProduto);
	p3.add(this.lucro, cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new Rotulo("Markup:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new Rotulo(""), cons);

	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.markup = new CampoMoeda(8);
	p3.add(this.markup, cons);
	this.markup.setEnabled(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	JButton bt_alter_markup  = new JButton(new ImageIcon(getClass().getResource("/icons/alterar_mini.png")));
	bt_alter_markup.setToolTipText("Atualizar constante markup");
	p3.add(bt_alter_markup, cons);
	
	cons.gridwidth  = 1;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new Rotulo("Valor Final (R$):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new Rotulo("Valor Fração (R$):"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.valor_final = new CampoMoeda(8);
	this.valor_final.setEditable(false);
	p3.add(this.valor_final, cons);
		
	this.valor_fracionado = new CampoMoeda(8);
	this.valor_fracionado.setEditable(false);
	p3.add(this.valor_fracionado, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	JButton bt_calcula  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_confirma_mini.png")));
	bt_calcula.setToolTipText("Calcula preço de venda");
	p3.add(bt_calcula, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weightx = 1;
	cons.weighty = 1;
	p3.add(new Rotulo(""), cons);
	

	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
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
	
	
	
		
		this.tf_cod_ean.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		tf_nome.requestFocusInWindow();
		}});
		
	

	
		this.venda_fracionada.addItemListener(new ItemListener(){

		@Override
		public void itemStateChanged(ItemEvent arg0) {
		
			if(String.valueOf(venda_fracionada.getSelectedItem()).compareTo("SIM")==0){
		
			tipo_venda_fracionada.setEnabled(true);
			unidades_pacote.setEnabled(false);
			tf_peso.setEnabled(false);
			
			tipo_venda_fracionada.setSelectedIndex(0);
			unidades_pacote.setText("");
			tf_peso.setText("");
			valor_fracionado.setText("");
			}
			else{
			
			tipo_venda_fracionada.setEnabled(false);
			tipo_venda_fracionada.setSelectedIndex(0);
			unidades_pacote.setEnabled(false);
			tf_peso.setEnabled(false);
			}	
		}});
	
	
		
		
		this.tipo_venda_fracionada.addItemListener(new ItemListener(){

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
			if(String.valueOf(tipo_venda_fracionada.getSelectedItem()).compareTo("UNIDADE")==0){
					
			unidades_pacote.setEnabled(true);
			tf_peso.setEnabled(false);
				
			tf_peso.setText("");
			}
			else if(String.valueOf(tipo_venda_fracionada.getSelectedItem()).compareTo("KILO")==0){
				
			unidades_pacote.setEnabled(false);
			tf_peso.setEnabled(true);
					
			unidades_pacote.setText("");
			}
			else{
			
			unidades_pacote.setEnabled(false);
			tf_peso.setEnabled(false);
						
			unidades_pacote.setText("");
			tf_peso.setText("");
			}
			
		}});
		
		
		
		
		bt_nova_categoria.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		addNovaCategoria();
		}});
		
		
		
		
		this.tf_categoria.addFocusListener( new FocusAdapter(){			
		@Override 
		public void focusGained(FocusEvent e) {
						
		Comuns.removeIndicadoresDeErro(tf_categoria);
		valor_icms_porcento.requestFocusInWindow();
		addCategoria();
		}});
		
		
		
		
		this.tf_subcategoria.addFocusListener( new FocusAdapter(){			
		@Override 
		public void focusGained(FocusEvent e) {
				
		Comuns.removeIndicadoresDeErro(tf_subcategoria);
		valor_icms_porcento.requestFocusInWindow();
		addSubCategoria();
		}});
		
		
		
		
		bt_add_subcategoria.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		addNovaSubCategoria();
		}});
			
			
			
			
		bt_add_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						    	
		addFornecedor();
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
		
		
		
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		

		
		
		bt_calcula.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		calculaValor();
		}});
			
	
	
		bt_alter_markup.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		markup.setText(calculaMarkup());
		}});
		
		
		
		
		tb_fornecedores.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
			
		if(e.getClickCount()==2)
		atualizaPrecoDeFornecedor();
			
		}}); 
		
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome);
	textFieldList.add(tf_cod_ean);
	textFieldList.add(tf_descricao_abreviada);
	textFieldList.add(unidades_pacote);
	textFieldList.add(tf_peso);
	
	textFieldList.add(this.lucro);
	textFieldList.add(this.tf_custo);
	
	textFieldList.add(this.tf_fornecedor);
	textFieldList.add(this.tf_valor_fornecedor);
	
	textFieldList.add(this.tf_cod_cfop);
	
	Comuns.addEventoDeFoco(textFieldList);
	}
	
	
	
	
	
	
	protected String getMarkup(){
	
	if(this.retorno == null)		
	return this.calculaMarkup();
	
	return this.retorno.getMarkup();
	}
	
	

	
	

	
	protected boolean validation(){

		if(this.tf_cod_ean.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o código EAN do produto (Código de barras).");
		Comuns.textFieldErroMode(this.tf_cod_ean);
		return false;	
		}
			
		if(this.tf_nome.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome do produto.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}	
		
		if(this.tf_cod_cfop.getText().length() != 4){
			
		Mensagens.msgDeErro("Informe o código CFOP do produto.");
		Comuns.textFieldErroMode(this.tf_cod_cfop);
		return false;	
		}	
		
		
		if(this.tf_descricao_abreviada.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe a descrição completa do produto.");
		Comuns.textFieldErroMode(this.tf_descricao_abreviada);
		return false;	
		}
	
		if(String.valueOf(this.venda_fracionada.getSelectedItem()).compareTo("SIM")==0){
		
			if(String.valueOf(this.tipo_venda_fracionada.getSelectedItem()).compareTo("SELECIONE")==0){
			
			Mensagens.msgDeErro("Selecione o tipo de venda fracionada.");
			return false;	
			}
			
			if(String.valueOf(this.tipo_venda_fracionada.getSelectedItem()).compareTo("PESO")==0){
				
				if(this.tf_peso.getText().length() == 0){
					
				Mensagens.msgDeErro("Informe o peso total do produto.");
				Comuns.textFieldErroMode(this.tf_peso);
				return false;	
				}
			}
			
			if(String.valueOf(this.tipo_venda_fracionada.getSelectedItem()).compareTo("UNIDADE")==0){
				
				if(this.unidades_pacote.getText().length() == 0){
					
				Mensagens.msgDeErro("Informe o número de unidades do produto.");
				Comuns.textFieldErroMode(this.unidades_pacote);
				return false;	
				}
			}
		}
		
		if(this.lucro.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o valor do lucro sobre o produto.");
		Comuns.textFieldErroMode(this.lucro);
		return false;	
		}

		if(this.tf_custo.getText().length() == 0){
				
		Mensagens.msgDeErro("Informe o valor do custo total do produto.");
		Comuns.textFieldErroMode(this.tf_custo);
		return false;	
		}

	return true;	
	}
	
	
	

	
	
	private void addCategoria(){
	
	Categoria retorno = new Categoria();
		
	FormDeSelecao<Categoria> selectionItemForm = 
						new FormDeSelecao<Categoria>("Adicionar Categoria", retorno, Categoria.class, null);
	selectionItemForm.mostrar();
					
		if(retorno != null && retorno.getId_categoria() > 0){
								
		this.idCategoria = retorno.getId_categoria();
		this.tf_categoria.setText(retorno.getDescricao());
		}		
	}
	
	

	
	
	
	private void addNovaCategoria(){
	
	Categoria retorno = new Categoria();
		
	FormNovaCategoria form = new FormNovaCategoria(retorno);
	form.mostrar();		
	
		if(retorno != null && retorno.getId_categoria() > 0){
									
		this.idCategoria = retorno.getId_categoria();
		this.tf_categoria.setText(retorno.getDescricao());
		}	
	}	
	
	
	
	
	
	
	
	private void addSubCategoria(){
		
	Subcategoria retorno = new Subcategoria();
		
	FormDeSelecao<Subcategoria> selectionItemForm = 
							new FormDeSelecao<Subcategoria>("Adicionar Subcategoria", retorno, Subcategoria.class, null);
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
	
	
	
	
	
	

	protected void calculaValor(){
	
	this.valor_final.setText("0,00");
	this.valor_fracionado.setText("0,00");
	
	
	if(this.tf_custo.getText().length() == 0 || this.lucro.getText().length() == 0)
	return;
	
	String pvp  = Calculo.soma(this.tf_custo.getText(), 
			Calculo.calcPorcentagem(this.tf_custo.getText(), this.lucro.getText()));
	
	String val_final = Calculo.soma(pvp, Calculo.multiplica(pvp, this.markup.getText()));
	
	this.valor_final.setText(Calculo.formataValor(val_final));
	
		if(String.valueOf(this.venda_fracionada.getSelectedItem()).compareTo("SIM")==0){	

			if(String.valueOf(this.tipo_venda_fracionada.getSelectedItem()).compareTo("UNIDADE")==0){
			
			if(Calculo.stringZero(this.unidades_pacote.getText()))	
			return;	
				
			this.valor_fracionado.setText(Calculo.formataValor(Calculo.dividi(val_final, this.unidades_pacote.getText())));	
			}
			
			if(String.valueOf(this.tipo_venda_fracionada.getSelectedItem()).compareTo("KILO")==0){
			
			if(Calculo.stringZero(this.tf_peso.getText()))	
			return;		
				
			this.valor_fracionado.setText(Calculo.formataValor(Calculo.dividi(val_final, 
					Calculo.dividi(this.tf_peso.getText(), "1000"))));	
			}
		}
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
	
	
	

	
	
	public String calculaMarkup(){
		
	String markup = Calculo.soma(this.valor_icms_porcento.getText(), 
						Calculo.soma(this.valor_pis_porcento.getText(),
								Calculo.soma(this.valor_cofins_porcento.getText(),
										Calculo.soma(this.valor_ipi_porcento.getText(), 
												Calculo.soma(this.valor_csll_porcento.getText(), 
														Calculo.soma(this.valor_irpj_porcento.getText(), 
																Calculo.soma(this.valor_issqn_porcento.getText(), 
																		Calculo.soma(this.valor_comissao_porcento.getText(), this.valor_administrativa_porcento.getText()))))))));
					
					
	return Calculo.formataValor(Calculo.dividi(markup, "100"));
	}
		

	
	
	
}
