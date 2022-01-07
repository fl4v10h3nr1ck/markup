package classes.estoque.cadastro.produto;


import java.io.File;
import java.util.List;

import classes.componentes.beans.Item;
import classes.comuns.Comuns;
import classes.dao.DAO;
import classes.estoque.beans.Categoria;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.Fornecedor_x_Produto;
import classes.estoque.beans.Produto;
import classes.estoque.beans.Subcategoria;




public class FormAlterProduto extends FormProdutoBase{




private static final long serialVersionUID = 1L;




	public FormAlterProduto(Produto retorno) {
	
	super("Dados de Produto", retorno);
	
	
	this.adicionarComponentes();
	
	this.setValores();
	
	List<Fornecedor_x_Produto> lista_aux= 
			new DAO<Fornecedor_x_Produto>(Fornecedor_x_Produto.class).get(null, "fornXprod.fk_produto="+retorno.getId_produto(), null);

	DAO<Fornecedor> fornecedor_dao = new DAO<Fornecedor>(Fornecedor.class);		
			for(Fornecedor_x_Produto aux: lista_aux){
			
			Fornecedor fornecedor = fornecedor_dao.get(aux.getFk_fornecedor());
			
				if(fornecedor !=null){
					
				Item item = new Item();
					
				item.setValor("id", fornecedor.getId_fornecedor());
				item.setValor("nome", fornecedor.getNome_razao());
				item.setValor("valor", aux.getPreco());
			
				this.lista_fornecedores.add(item);
				}
			}
			
			
	this.atualizaTabelaDeFornecedores();
	}


	
	
	


	public void setValores(){
	
	this.tf_nome.setText(this.retorno.getNome());
	this.tf_descricao_abreviada.setText(this.retorno.getDescricao_abreviada());	
	this.tf_marca.setText(this.retorno.getMarca());
	this.tf_especificacao.setText(this.retorno.getEspecificacao());
	this.tf_secao.setText(this.retorno.getSecao());
	
	this.tf_cod_ncm.setText(this.retorno.getCod_ncm());
	this.tf_cod_ean.setText(this.retorno.getCodigo_ean());
	this.tf_cod_cfop.setText(this.retorno.getCodigo_cfop());
	
	this.tf_unid_medida.setSelectedItem(this.retorno.getUnidade_de_medida());
	this.tf_medida.setText(this.retorno.getMedida());
	this.tf_garantia.setText(""+this.retorno.getGarantia_da_loja_em_dias());
	this.tf_quant_min_estoque.setText(this.retorno.getQuant_min_estoque());
	
	
	this.retorno.setQuant_min_estoque(Integer.parseInt(this.tf_quant_min_estoque.getText()));
	this.retorno.setGarantia_da_loja_em_dias(this.tf_garantia.getText().length()>0?Integer.parseInt(this.tf_garantia.getText()):0);
	
	if(this.retorno.getVenda_fracionada()!=null && this.retorno.getVenda_fracionada().compareTo("S")==0){
	
	this.venda_fracionada.setSelectedIndex(1);
	
	this.unidades_pacote.setText(this.retorno.getUnidades_pacote());
	
		if(this.retorno.getUsa_balanca()!=null && this.retorno.getUsa_balanca().compareTo("S")==0){
	
		this.usa_balanca.setSelected(true);
		this.tf_cod_balança.setText(retorno.getCodigo_balanca());
		}
		else	
		this.usa_balanca.setSelected(false);	
		
	}
	else
	this.venda_fracionada.setSelectedIndex(0);
	
	
	
	
	
	this.tf_custo.setText("");
	this.lucro.setText(this.retorno.getLucro_porcento());
	this.valor_final.setText(this.retorno.getValor_final());
	this.valor_fracionado.setText(this.retorno.getValor_fracionado());
	
		if(this.retorno.getFk_categoria()>0){
		
		Categoria categoria= new DAO<Categoria>(Categoria.class).get(this.retorno.getFk_categoria());
	
			if(categoria!=null){
			
			this.idCategoria = categoria.getId_categoria();
			this.tf_categoria.setText(categoria.getDescricao());
			}
		}
		
		
		if(this.retorno.getFk_sub_categoria()>0){
			
		Subcategoria subcategoria= new DAO<Subcategoria>(Subcategoria.class).get(this.retorno.getFk_sub_categoria());
		
			if(subcategoria!=null){
				
			this.idSubCategoria = subcategoria.getId_subcategoria();
			this.tf_subcategoria.setText(subcategoria.getDescricao());
			}
		}	
	
	
	if(this.retorno.getImg()!=null && 
			this.retorno.getImg().length()>0 &&
				Comuns.config_do_sistema.getPath_imgs_produtos()!=null &&
					Comuns.config_do_sistema.getPath_imgs_produtos().length()>0)
	this.imagemGestor.addImagem(new File(Comuns.config_do_sistema.getPath_imgs_produtos()+this.retorno.getImg()));
	}
	
	
	

	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;	
		
	this.setValorFinal();
	
	this.retorno.setNome(tf_nome.getText());
	this.retorno.setDescricao_abreviada(this.tf_descricao_abreviada.getText());
	this.retorno.setMarca(this.tf_marca.getText());
	this.retorno.setEspecificacao(this.tf_especificacao.getText());
	this.retorno.setSecao(this.tf_secao.getText());
	this.retorno.setCodigo_ean(this.tf_cod_ean.getText());
	this.retorno.setCod_ncm(this.tf_cod_ncm.getText());
	this.retorno.setCodigo_cfop(this.tf_cod_cfop.getText()); 		
	this.retorno.setFk_categoria(this.idCategoria);	
	this.retorno.setFk_sub_categoria(this.idSubCategoria);
	
	
	this.retorno.setUnidade_de_medida(this.tf_unid_medida.getSelectedItem().toString());
	this.retorno.setMedida(this.tf_medida.getText());
	this.retorno.setQuant_min_estoque(Integer.parseInt(this.tf_quant_min_estoque.getText()));
	this.retorno.setGarantia_da_loja_em_dias(this.tf_garantia.getText().length()>0?Integer.parseInt(this.tf_garantia.getText()):0);
	
	
		if(this.venda_fracionada.getSelectedIndex()==0){
		
		this.retorno.setVenda_fracionada("N");	
		this.retorno.setUnidades_pacote(0);	
		this.retorno.setUsa_balanca("N");
		}
		else{
		
		this.retorno.setUnidades_pacote(this.unidades_pacote.getText().length()>0?Integer.parseInt(this.unidades_pacote.getText()):0);	
		this.retorno.setVenda_fracionada("S");	
		this.retorno.setUsa_balanca(this.usa_balanca.isSelected()?"S":"N");
		}
		
		
	this.retorno.setValor_fracionado(this.valor_fracionado.getText());
	this.retorno.setLucro_porcento(this.lucro.getText());
	this.retorno.setValor_final(this.valor_final.getText());
	this.retorno.setValor_custo_medio(this.tf_custo.getText());	
	
	this.retorno.setQuant_min_estoque(Integer.parseInt(this.tf_quant_min_estoque.getText()));
	this.retorno.setUnidade_de_medida(this.tf_unid_medida.getSelectedItem().toString());
	
	if(!new DAO<Produto>(Produto.class).altera(this.retorno))
	return false;	

	DAO<Fornecedor_x_Produto> x_DAO = new DAO<Fornecedor_x_Produto>(Fornecedor_x_Produto.class);
	
	x_DAO.removeSemConfirmacao("fk_produto="+this.retorno.getId_produto());
	
	
		for(Item item: this.lista_fornecedores){
			
		Fornecedor_x_Produto aux = new Fornecedor_x_Produto();
			
		aux.setFk_fornecedor(Integer.parseInt(item.getValor("id")));
		aux.setFk_produto(this.retorno.getId_produto());
		aux.setPreco(item.getValor("valor"));
		
		x_DAO.novo(aux);
		}

	return true;	
	}


	
}
