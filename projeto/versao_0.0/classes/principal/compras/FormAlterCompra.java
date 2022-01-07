package principal.compras;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO;
import componentes.beans.Compra;
import componentes.beans.Despesa;
import componentes.beans.Inventario;
import componentes.beans.Item;
import componentes.beans.ItemDeCompra;
import componentes.beans.Produto;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterCompra extends FormCompraBase{




private static final long serialVersionUID = 1L;

	


	public FormAlterCompra(Compra compra) {
	
	super("Dados de Compra",  700, 590, compra);
	
	this.setInfosAdicionais();
	
	this.adicionarComponentes();
	
	this.tf_descricao.setText(this.retorno.getDescricao());
	
	List<ItemDeCompra> itens = new DAO<ItemDeCompra>(ItemDeCompra.class).get(null, "it_comp.fk_compra="+this.retorno.getId_compra(), null);
	
	
		for(ItemDeCompra aux: itens){
			
		Item item = new Item();
		
		item.addParamentro("id_fornecedor", aux.getFk_fornecedor());	
		item.addParamentro("quantidade", aux.getQuantidade());
		item.addParamentro("tipo", aux.getTipo());
		item.addParamentro("valor", aux.getPreco());
		
		String descricao ="";
		
			if(aux.getTipo()!=null && aux.getTipo().compareTo("ESTOQUE")==0){
			
			item.addParamentro("id_item", aux.getFk_produto());
			
			Produto prod = new DAO<Produto>(Produto.class).get(aux.getFk_produto());
			
			if(prod!=null)
			descricao = prod.getDescricao_abreviada();
			
			}
			if(aux.getTipo()!=null && aux.getTipo().compareTo("INVENTARIO")==0){
			
			item.addParamentro("id_item", aux.getFk_inventario());
			
			Inventario inventario = new DAO<Inventario>(Inventario.class).get(aux.getFk_inventario());
			
			if(inventario!=null)
			descricao = inventario.getDescricao();
			
			}
			if(aux.getTipo()!=null && aux.getTipo().compareTo("DESPESA")==0){
			
			item.addParamentro("id_item", aux.getFk_despesa());
			
			Despesa despesa = new DAO<Despesa>(Despesa.class).get(aux.getFk_despesa());
			
			if(despesa!=null)
			descricao = despesa.getDescricao();
			}
				

		item.addParamentro("item_descricao", descricao);
		this.lista_itens.add(item);
		}
	
	
		if(this.retorno.getStatus().compareTo("FECHADO") == 0 || 
				this.retorno.getStatus().compareTo("CANCELADO") == 0){
	
		this.bt_save.setEnabled(false);	
		this.bt_adicionar.setEnabled(false);
		this.bt_alterar.setEnabled(false);
		this.bt_remover.setEnabled(false);
		}
		
		
		
	this.atualizaTabelaDeItem();
	}


	
	
	
	private void setInfosAdicionais(){
		
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	JPanel infoPanel = new JPanel(new GridBagLayout());
	infoPanel.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(infoPanel, cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	infoPanel.add(new JLabel("Código:"), cons);
	infoPanel.add(new JLabel("Status:"), cons);
	infoPanel.add(new JLabel("Cadastrado:"), cons);
	infoPanel.add(new JLabel("QTDe Final:"), cons);
	infoPanel.add(new JLabel("Custo Final (R$):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	infoPanel.add(new JLabel("Fechamento:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	JTextField txt1 = new JTextField(Comuns.getCod(Compra.class, this.retorno.getId_compra()));
	infoPanel.add(txt1, cons);
	txt1.setEditable(false);
	
	JTextField txt6 = new JTextField(this.retorno.getStatus());
	infoPanel.add(txt6, cons);
	txt6.setEditable(false);
	
	JTextField txt2 = new JTextField(Data.converteDataParaString(this.retorno.getData_cadastro()));
	infoPanel.add(txt2, cons);
	txt2.setEditable(false);
	
	JTextField txt3= new JTextField(this.retorno.getQuant_total()== 0? "":""+this.retorno.getQuant_total());
	infoPanel.add(txt3, cons);
	txt3.setEditable(false);
	
	JTextField txt4 = new JTextField(this.retorno.getValor_total() != null && this.retorno.getValor_total().length() > 0?Calculo.formataValor(this.retorno.getValor_total()):"");
	infoPanel.add(txt4, cons);
	txt4.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JTextField txt5 = new JTextField(Data.converteDataParaString(this.retorno.getData_compra()));
	infoPanel.add(txt5, cons);
	txt5.setEditable(false);
	}
	
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	this.calculaValores();	
	
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setQuant_total(this.tf_quant_total.getText().length() ==0? 0:Integer.parseInt(this.tf_quant_total.getText()));
	this.retorno.setValor_total(this.tf_val_total.getText());
	
	if(!new DAO<Compra>(Compra.class).altera(this.retorno))
	return false;
	
	DAO<ItemDeCompra> item_DAO = new DAO<ItemDeCompra>(ItemDeCompra.class);
	
	
	if(!item_DAO.removeSemConfirmacao("fk_compra="+this.retorno.getId_compra()))
	return false;
	
	
	int id = 0;
		for(Item aux: this.lista_itens){
			
		ItemDeCompra	item = new ItemDeCompra();
		item.setFk_compra(this.retorno.getId_compra());
		
		String tipo = aux.getParamentro("tipo").toString();
		
		if(tipo.compareTo("DESPESA")!=0 && 
				aux.getParamentro("id_fornecedor").toString()!=null && 
				 aux.getParamentro("id_fornecedor").toString().length()>0)
		id = Integer.parseInt(aux.getParamentro("id_fornecedor").toString());
		else
		id = 0;
		
		item.setFk_fornecedor(id);
		
		
		if(tipo.compareTo("ESTOQUE")==0)
		item.setFk_produto(Integer.parseInt(aux.getParamentro("id_item").toString()));
		
		if(tipo.compareTo("INVENTARIO")==0)
		item.setFk_inventario(Integer.parseInt(aux.getParamentro("id_item").toString()));
		
		if(tipo.compareTo("DESPESA")==0)
		item.setFk_despesa(Integer.parseInt(aux.getParamentro("id_item").toString()));
			
		item.setTipo(tipo);
		item.setPreco(aux.getParamentro("valor").toString());
		item.setQuantidade(Integer.parseInt(aux.getParamentro("quantidade").toString()));
		
		item_DAO.novo(item);
		}

	return true;
	}





	
}
