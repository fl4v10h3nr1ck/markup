package classes.compras;

import java.util.Date;
import java.util.List;

import classes.componentes.beans.Item;
import classes.compras.beans.Compra;
import classes.compras.beans.ItemDeCompra;
import classes.dao.DAO;
import classes.financeiro.beans.Entrada_Saida;
import classes.financeiro.beans.Parcela;





public class FormNovaCompra extends FormDeCompraBase{


private static final long serialVersionUID = 1L;





	public FormNovaCompra(){

	this(null);
	}


	
	
	
	public FormNovaCompra(Compra retorno){
		
	super("Cadastrar Nova Compra", retorno);	
	}
	
	


	
	
	@Override
	public boolean acaoPrincipal() {
	
	this.setValorFinal();	
		
		
	if(!validacao())
	return false;	
	
	this.setValorFinal();
	
	Entrada_Saida entrada_saida = new Entrada_Saida();
	entrada_saida.setReferente("REPOSICAO DE ESTOQUE");
	entrada_saida.setData_cadastro(new Date());
	entrada_saida.setPorcento_comissao("0.00");
	entrada_saida.setPorcento_juros("0.00");
	entrada_saida.setStatus("ABERTO");
	entrada_saida.setTipo("SAIDA");
	entrada_saida.setValor_total(this.tf_total.getText());
	
	int aux = new DAO<Entrada_Saida>(Entrada_Saida.class).novo(entrada_saida);
	
	if(aux==0)
	return false;
	
	
	DAO<Parcela> dao_parcela = new DAO<Parcela>(Parcela.class);	
	
	List<Parcela> parcelas = this.painel_pagamento.gerarParcelas(aux);
	
	for(Parcela parcela: parcelas)
	dao_parcela.novo(parcela);
	
	
	if(this.compra == null)
	this.compra = new Compra();
	
	this.compra.setFk_pagamento(aux);

	aux = 0;
	for(Item item: this.listas_prod_adicionados)
	aux += Integer.parseInt(item.getValor("quant"));

	this.compra.setQuant_total(aux);
	this.compra.setValor_total(this.tf_total.getText());
	this.compra.setData_cadastro(new Date());
	this.compra.setStatus("PENDENTE");
	this.compra.setStatus_entrega("PENDENTE");
	
	aux = new DAO<Compra>(Compra.class).novo(this.compra);
	
	if(aux==0)
	return false;
	
	this.compra.setId_compra(aux);
	
	DAO<ItemDeCompra> item_DAO = new DAO<ItemDeCompra>(ItemDeCompra.class);
	
		for(Item item: this.listas_prod_adicionados){
			
		ItemDeCompra item_aux = new ItemDeCompra();
		item_aux.setFk_compra(this.compra.getId_compra());
		item_aux.setFk_fornecedor(Integer.parseInt(item.getValor("id_fornecedor")));
		item_aux.setFk_produto(Integer.parseInt(item.getValor("id_produto")));
		item_aux.setPreco(item.getValor("preco"));
		item_aux.setQuantidade(Integer.parseInt(item.getValor("quant")));
		
		item_DAO.novo(item_aux);
		}
	
	return true;
	}






	
	
}
