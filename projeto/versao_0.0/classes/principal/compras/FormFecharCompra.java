package principal.compras;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;
import classes.CampoLimitado;
import componentes.beans.Compra;
import componentes.beans.Despesa;
import componentes.beans.Entrada_Saida;
import componentes.beans.Fornecedor;
import componentes.beans.Inventario;
import componentes.beans.ItemDeCompra;
import componentes.beans.Parcela;
import componentes.beans.Produto;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import comuns.Preferencias;





public class FormFecharCompra extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoLimitado tf_codigo;
private CampoLimitado tf_descricao;
private CampoLimitado tf_quant_total;
private CampoLimitado tf_valor_total;

private JTable tb_itens;		
private DefaultTableModel modelo;	


private Compra compra;





	public FormFecharCompra(Compra compra) {
		
	super("Fechamento de Compra", 650, 500);

	this.compra = compra;
	
	this.adicionarComponentes();
	}

	
	
	
	

	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	
	cons.insets = new Insets(2, 2, 0, 2);
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;	
	cons.weightx = 0.2;
	p1.add(new JLabel("Código:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.8;
	p1.add(new JLabel("Descrição:"), cons);

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_codigo = new CampoLimitado(200, Comuns.getCod(Compra.class, this.compra.getId_compra())), cons);
	this.tf_codigo.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(this.tf_descricao = new CampoLimitado(200, this.compra.getDescricao()), cons);
	this.tf_descricao.setEditable(false);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;	
	cons.weightx = 1;
	p1.add(new JLabel("QTDe Total:"), cons);
	p1.add(new JLabel("Valor Total (R$):"), cons);
	p1.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel(""), cons);

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p2.add(this.tf_quant_total = new CampoLimitado(200, this.compra.getQuant_total()), cons);
	this.tf_quant_total.setEditable(false);
	
	p2.add(this.tf_valor_total = new CampoLimitado(200, Calculo.formataValor(this.compra.getValor_total())), cons);
	this.tf_valor_total.setEditable(false);
	
	p2.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new JLabel(""), cons);
	
	
		this.modelo = new DefaultTableModel(null, new String[]{"Item", "Tipo", "QTDe", "Fornecedor", "Preço (R$)"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	
	   
	this.tb_itens = new JTable(this.modelo);
	this.tb_itens.setRowHeight(20); 
	
	this.tb_itens.getColumnModel().getColumn(0).setPreferredWidth(200);
	this.tb_itens.getColumnModel().getColumn(1).setPreferredWidth(120);
	this.tb_itens.getColumnModel().getColumn(2).setPreferredWidth(120);	
	this.tb_itens.getColumnModel().getColumn(3).setPreferredWidth(200);	
	this.tb_itens.getColumnModel().getColumn(4).setPreferredWidth(120);	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	this.add(new JScrollPane(this.tb_itens, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);

	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.weighty  = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Fechar Compra", new ImageIcon(getClass().getResource("/icons/icon_confirma_mini.png")));
	bt_save.setToolTipText("Fechar compra");
	this.add(bt_save, cons);
		
		
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}		
		}});
		

	this.getItens();
	}
	
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
	
	
	this.compra.setStatus("FECHADO");
	this.compra.setData_compra(new Date());
	
	if(!new DAO<Compra>(Compra.class).altera(this.compra))
	return false;
	

	Entrada_Saida pagamento = new Entrada_Saida();
	
	
	pagamento.setFk_colaborador(Comuns.iDVendedor);
	pagamento.setFk_compra(this.compra.getId_compra());
	pagamento.setTipo_pagamento("UNICO");
	pagamento.setTipo("SAIDA");
	pagamento.setReferente("COMPRA "+Comuns.getCod(Compra.class, this.compra.getId_compra())+ " "+Data.getDataAtual().replace("-", "/"));
	pagamento.setValor_total(this.compra.getValor_total());
	pagamento.setPorcento_multa(Comuns.porcentMultaPadrao);
	pagamento.setPorcento_juros(Comuns.porcentjurosPadraoAoMes);
	pagamento.setValor_entrada("0.00");
	pagamento.setPorcento_comissao(Comuns.porcentoComissaoVendedor);
	
	pagamento.setPrimeiro_vencimento(this.compra.getData_compra()); 
	
	pagamento.setData_cadastro(new Date());
	pagamento.setStatus("ABERTO");
	pagamento.setNum_de_parcelas(1);
	
	int id = new DAO<Entrada_Saida>(Entrada_Saida.class).novo(pagamento);
	
	if(id== 0)
	return false;	
			
	Parcela parcela = new	Parcela();
	parcela.setData_vencimento(pagamento.getPrimeiro_vencimento());
	parcela.setValor_parcela(pagamento.getValor_total()); 
	parcela.setIndice(1);
	parcela.setFk_entrada_saida(id);
	parcela.setStatus("ABERTO");
	
	return new DAO<Parcela>(Parcela.class).novo(parcela)>0?true:false;
	}
	
	
	
	


	
	
	
	private void getItens(){
	
	List<ItemDeCompra> itens = new DAO<ItemDeCompra>(ItemDeCompra.class).get(null, "it_comp.fk_compra="+this.compra.getId_compra(), null);
			
	this.modelo.setNumRows(0);
	String[] dados = new String[5];
	
		for(ItemDeCompra aux: itens){
	
		String descricao ="";	
		
			if(aux.getTipo()!=null && aux.getTipo().compareTo("ESTOQUE")==0){
					
			Produto prod = new DAO<Produto>(Produto.class).get(aux.getFk_produto());
				
			if(prod!=null)
			descricao = prod.getDescricao_abreviada();
				
			}
			if(aux.getTipo()!=null && aux.getTipo().compareTo("INVENTARIO")==0){
					
			Inventario inventario = new DAO<Inventario>(Inventario.class).get(aux.getFk_inventario());
				
			if(inventario!=null)
			descricao = inventario.getDescricao();
				
			}
			if(aux.getTipo()!=null && aux.getTipo().compareTo("DESPESA")==0){
					
			Despesa despesa = new DAO<Despesa>(Despesa.class).get(aux.getFk_despesa());
				
			if(despesa!=null)
			descricao = despesa.getDescricao();
			}
						
	
		dados[0] = descricao;
		dados[1] = aux.getTipo();
		dados[2] = ""+aux.getQuantidade();
		dados[3] = aux.getFk_fornecedor()>0?Comuns.getCod(Fornecedor.class, aux.getFk_fornecedor()):"";
		dados[4] = "R$: "+Calculo.formataValor(aux.getPreco());
		
		this.modelo.addRow(dados);	
		}
	}
	
	
	
	
	
	
}
