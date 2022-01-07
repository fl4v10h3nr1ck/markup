package classes.estoque.cadastro.produto;



import java.util.Date;

import classes.componentes.beans.Item;
import classes.dao.DAO;
import classes.estoque.beans.Fornecedor_x_Produto;
import classes.estoque.beans.Produto;





public class FormNovoProduto extends FormProdutoBase{


private static final long serialVersionUID = 1L;





	public FormNovoProduto(){

	this(null);
	}


	
	
	
	public FormNovoProduto(Produto retorno){
		
	super("Novo Produto", retorno);	
		
	adicionarComponentes();
	}
	
	


	
	
	@Override
	public boolean acaoPrincipal() {
	
	if(!validacao())
	return false;	
	
	this.setValorFinal();
	
	if(this.retorno == null)
	this.retorno = new Produto();
	
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
		this.retorno.setCodigo_balanca(null);	
		}
		else{
		
		this.retorno.setUnidades_pacote(this.unidades_pacote.getText().length()>0?Integer.parseInt(this.unidades_pacote.getText()):0);	
		this.retorno.setVenda_fracionada("S");	
		
			if(this.usa_balanca.isSelected()){
			this.retorno.setUsa_balanca("S");
			this.retorno.setCodigo_balanca(this.tf_cod_balança.getText());	
			}
			else{
				
			this.retorno.setUsa_balanca("N");		
			this.retorno.setCodigo_balanca(null);	
			}
		}
		
		
	this.retorno.setValor_fracionado(this.valor_fracionado.getText());
	this.retorno.setLucro_porcento(this.lucro.getText());
	this.retorno.setValor_final(this.valor_final.getText());
	this.retorno.setValor_custo_medio("0,00");
		
	this.retorno.setAtivo("S");
	this.retorno.setData_cadastro(new Date());
	this.retorno.setQuantidade(0);
	this.retorno.setImg(this.imagemGestor.salvaImagem());
	
	int id = new DAO<Produto>(Produto.class).novo(this.retorno);
	
	if(id == 0)
	return false;

	this.retorno.setId_produto(id);
	
	DAO<Fornecedor_x_Produto> x_DAO = new DAO<Fornecedor_x_Produto>(Fornecedor_x_Produto.class);
	
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
