package classes.compras;



import java.util.List;

import util.Mensagens;
import classes.componentes.beans.Item;
import classes.compras.beans.Compra;
import classes.compras.beans.ItemDeCompra;
import classes.comuns.Comuns;
import classes.comuns.Data;
import classes.dao.DAO;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.Produto;
import classes.financeiro.beans.Entrada_Saida;
import classes.financeiro.beans.Parcela;





public class FormAlterCompra extends FormDeCompraBase{




private static final long serialVersionUID = 1L;

	
private Entrada_Saida dao_ent_sai;




	public FormAlterCompra(Compra compra) {
	
	super("Dados de Compra", compra);
	
	List<ItemDeCompra> itens = new DAO<ItemDeCompra>(ItemDeCompra.class).get(null, "it_comp.fk_compra="+this.compra.getId_compra(), null);
	
	DAO<Produto> dao_prod =new DAO<Produto>(Produto.class);
	DAO<Fornecedor> dao_forn =new DAO<Fornecedor>(Fornecedor.class);
	
		for(ItemDeCompra aux: itens){
			
		Item item = new Item();
		
		Produto produto = dao_prod.get(aux.getFk_produto());
		
			if(produto!=null){
		
			
			item.setValor("id_produto", produto.getId_produto());	
			item.setValor("nome_produto", produto.getDescricao_abreviada());	
		
			Fornecedor fornecedor = dao_forn.get(aux.getFk_fornecedor());
			
				if(fornecedor!=null){
		
				item.setValor("id_fornecedor", fornecedor.getId_fornecedor());
				item.setValor("nome_fornecedor", fornecedor.getNome_fantasia_apelido());	
				}
			
			item.setValor("quant", aux.getQuantidade());
			item.setValor("preco", aux.getPreco());
			
			this.listas_prod_adicionados.add(item);
			}
		}
	
	this.dao_ent_sai = new DAO<Entrada_Saida>(Entrada_Saida.class).get(this.compra.getFk_pagamento());	
	
	
	if(this.dao_ent_sai !=null)
	this.painel_pagamento.setFormasDePagamento(this.dao_ent_sai);	
		
			
	this.atualizaTabelaDeProdutos();		
		
	this.painel_pagamento.atualizaTabelaDePagamentos();
	
	
	this.tf_status_compra.setText(this.compra.getStatus());
	this.tf_status_entrega.setText(this.compra.getStatus_entrega());
	this.tf_data_compra.setText(this.compra.getData_compra()!=null?Data.converteDataParaString(this.compra.getData_compra()):"");	
	this.tf_data_entrega.setText(this.compra.getData_entrega()!=null?Data.converteDataParaString(this.compra.getData_entrega()):"");	
	
		if(this.compra.getStatus()!= null && this.compra.getStatus().compareTo("FECHADO")==0){
			
		this.tf_data_compra.setEditable(true);
		
		this.tf_data_entrega.setEditable(true);
		
		this.bt_add_prod.setEnabled(false);
		this.bt_remover_prod.setEnabled(false);
		this.bt_alterar_prod.setEnabled(false);
		
		this.painel_pagamento.bloquearAlteracao();
		}
	}


	
	
	
	@Override
	public boolean acaoPrincipal() {
	
		if(this.compra.getStatus()==null || this.compra.getStatus().compareTo("PENDENTE")==0){
		
		if(!validacao())
		return false;	
			
		this.setValorFinal();
		
			if(this.dao_ent_sai!=null &&
					(this.dao_ent_sai.getStatus()==null || this.dao_ent_sai.getStatus().compareTo("ABERTO")==0)){
		
			this.dao_ent_sai.setValor_total(this.tf_total.getText());
			new DAO<Entrada_Saida>(Entrada_Saida.class).altera(this.dao_ent_sai);
			}
				
		DAO<Parcela> dao_parcela = new DAO<Parcela>(Parcela.class);	
			
		dao_parcela.removeSemConfirmacao("fk_entrada_saida="+this.dao_ent_sai.getId_entrada_saida());
			
		List<Parcela> parcelas = this.painel_pagamento.gerarParcelas(this.dao_ent_sai.getId_entrada_saida());
			
		for(Parcela parcela: parcelas)
		dao_parcela.novo(parcela);
			
		
		int aux = 0;
		for(Item item: this.listas_prod_adicionados)
		aux += Integer.parseInt(item.getValor("quant"));
	
		this.compra.setQuant_total(aux);
		this.compra.setValor_total(this.tf_total.getText());
			
		new DAO<Compra>(Compra.class).altera(this.compra);
			
		DAO<ItemDeCompra> item_DAO = new DAO<ItemDeCompra>(ItemDeCompra.class);
			
		item_DAO.removeSemConfirmacao("fk_compra="+this.compra.getId_compra());
		
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
		else if(this.compra.getStatus().compareTo("FECHADO")==0){
			
			if(this.tf_data_entrega.getText().length()>0){
			
				if(!this.tf_data_entrega.validacao()){
				
				this.tabbedPane.setSelectedIndex(2);			
					
				Mensagens.msgDeErro("Informe uma data de entrega válida.");
				Comuns.textFieldErroMode(this.tf_data_entrega);	
				return false;	
				}
			}
			
			
			if(this.tf_data_compra.getText().length()>0){
				
				if(!this.tf_data_compra.validacao()){
				
				this.tabbedPane.setSelectedIndex(2);	
					
				Mensagens.msgDeErro("Informe uma data de compra válida.");
				Comuns.textFieldErroMode(this.tf_data_compra);	
				return false;	
				}
			}
			
			
		this.compra.setData_entrega(Data.converteStringParaData(this.tf_data_entrega.getText()));
		this.compra.setData_compra(Data.converteStringParaData(this.tf_data_compra.getText()));
				
		return new DAO<Compra>(Compra.class).altera(this.compra);
		}
		
	
	return true;	
	}



	
}

	
