package principal.compras;


import java.util.Date;

import DAO.DAO;

import componentes.beans.Compra;
import componentes.beans.Item;
import componentes.beans.ItemDeCompra;






public class FormNovaCompra extends FormCompraBase{


private static final long serialVersionUID = 1L;



	
	
	
	public FormNovaCompra(){
		
	this(null);
	}
	
	
	
	
	
	public FormNovaCompra(Compra compra){
		
	super("Nova Compra",  700, 550, compra);
		
	adicionarComponentes();
	}
	
	

	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	this.calculaValores();	
	
	if(this.retorno==null)
	this.retorno = new Compra();

	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setQuant_total(this.tf_quant_total.getText().length() ==0? 0:Integer.parseInt(this.tf_quant_total.getText()));
	this.retorno.setValor_total(this.tf_val_total.getText());
	this.retorno.setData_cadastro(new Date());
	this.retorno.setStatus("PENDENTE");
	
	
	int id = new DAO<Compra>(Compra.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_compra(id);
	
	DAO<ItemDeCompra> item_DAO = new DAO<ItemDeCompra>(ItemDeCompra.class);
	
	
	
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
