package principal.estoque;


import java.util.Date;
import java.util.List;

import DAO.DAO;
import componentes.beans.Aliquota;
import componentes.beans.Fornecedor_X_Produto;
import componentes.beans.Item;
import componentes.beans.Produto;
import comuns.Comuns;



public class FormNovoProduto extends FormProdutoBase{


private static final long serialVersionUID = 1L;





	public FormNovoProduto(){

	this(null);
	}


	
	
	
	public FormNovoProduto(Produto retorno){
		
	super("Novo Produto", 850, 620, retorno);	
		
	adicionarComponentes();
	
	this.bt_alterar.setEnabled(false);
	
	List<Aliquota> aliquota = new DAO<Aliquota>(Aliquota.class).get(null, null, "aliq.id_aliquota DESC");
	
		if(aliquota.size() > 0){
			
		this.valor_icms_porcento.setText(aliquota.get(0).getValor_icms_porcento());
		this.valor_pis_porcento.setText(aliquota.get(0).getValor_pis_porcento());
		this.valor_cofins_porcento.setText(aliquota.get(0).getValor_cofins_porcento());
		this.valor_ipi_porcento.setText(aliquota.get(0).getValor_ipi_porcento());
		this.valor_csll_porcento.setText(aliquota.get(0).getValor_csll_porcento());
		this.valor_irpj_porcento.setText(aliquota.get(0).getValor_irpj_porcento());
		this.valor_comissao_porcento.setText(aliquota.get(0).getValor_comissao_porcento());
		this.valor_administrativa_porcento.setText(aliquota.get(0).getValor_administrativa_porcento());
		this.valor_issqn_porcento.setText(aliquota.get(0).getValor_issqn_porcento());
		}
		
	this.markup.setText(this.getMarkup());
	}
	
	


	
	
	@Override
	public boolean acaoPrincipal() {
	
	if(!validation())
	return false;	
	
	this.calculaValor();	
	
	if(this.retorno == null)
	this.retorno = new Produto();
	
	this.retorno.setCodigo_ean(this.tf_cod_ean.getText());
	this.retorno.setNome(tf_nome.getText());
	this.retorno.setDescricao(this.tf_descricao.getText());
	this.retorno.setDescricao_abreviada(this.tf_descricao_abreviada.getText());
	this.retorno.setMarca(this.tf_marca.getText());
	this.retorno.setEspecificacao(this.tf_especificacao.getText());
	this.retorno.setCod_ncm(this.tf_cod_ncm.getText());
	
	this.retorno.setVenda_fracionada(String.valueOf(this.venda_fracionada.getSelectedItem()));	
	this.retorno.setTipo_venda_fracionada(String.valueOf(this.tipo_venda_fracionada.getSelectedItem()));	
	this.retorno.setUnidades_pacote(this.unidades_pacote.getText().length()>0?Integer.parseInt(this.unidades_pacote.getText()):0);	
	this.retorno.setPeso_em_gramas(this.tf_peso.getText().length()>0?Integer.parseInt(this.tf_peso.getText()):0);	

	this.retorno.setFk_categoria(this.idCategoria);	
	this.retorno.setFk_sub_categoria(this.idSubCategoria);

	this.retorno.setSecao(this.tf_secao.getText());
	this.retorno.setGarantia_da_loja_em_dias(this.tf_garantia.getText().length()>0?Integer.parseInt(this.tf_garantia.getText()):0);
	this.retorno.setVolume_m3(this.tf_volume.getText());
	this.retorno.setCodigo_cfop(this.tf_cod_cfop.getText()); 		
	
	this.retorno.setValor_de_custo(this.tf_custo.getText());
	this.retorno.setValor_fracionado(this.valor_fracionado.getText());
	this.retorno.setLucro(this.lucro.getText());
	this.retorno.setValor_final(this.valor_final.getText());
	this.retorno.setMarkup(this.markup.getText());

	this.retorno.setValor_icms_porcento(this.valor_icms_porcento.getText()); 
	this.retorno.setValor_pis_porcento(this.valor_pis_porcento.getText()); 
	this.retorno.setValor_cofins_porcento(this.valor_cofins_porcento.getText());
	this.retorno.setValor_ipi_porcento(this.valor_ipi_porcento.getText());
	this.retorno.setValor_csll_porcento(this.valor_csll_porcento.getText());	
	this.retorno.setValor_irpj_porcento(this.valor_irpj_porcento.getText());
	this.retorno.setValor_comissao_porcento(this.valor_comissao_porcento.getText());
	this.retorno.setValor_administrativa_porcento(this.valor_administrativa_porcento.getText()); 		
	this.retorno.setValor_issqn_porcento(this.valor_issqn_porcento.getText()); 		
	this.retorno.setStatus("ATIVO");
	this.retorno.setData_cadastro(new Date());
	this.retorno.setQuantidade(0);
	this.retorno.setQuant_min_estoque(Comuns.quantMinPadraoEstoque);
	
	int id = new DAO<Produto>(Produto.class).novo(this.retorno);
	
	if(id == 0)
	return false;

	this.retorno.setId_produto(id);
	
	DAO<Fornecedor_X_Produto> x_DAO = new DAO<Fornecedor_X_Produto>(Fornecedor_X_Produto.class);
	
		for(Item item: this.lista_fornecedores){
			
		Fornecedor_X_Produto aux = new Fornecedor_X_Produto();
			
		aux.setFk_fornecedor(Integer.parseInt(item.getParamentro("id").toString()));
		aux.setFk_produto(this.retorno.getId_produto());
		aux.setPreco(item.getParamentro("valor").toString());
		
		x_DAO.novo(aux);
		}
	

	return true;
	}






	
	
}
