package principal.estoque;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.DAO;
import componentes.Rotulo;
import componentes.beans.Categoria;
import componentes.beans.Fornecedor;
import componentes.beans.Fornecedor_X_Produto;
import componentes.beans.Item;
import componentes.beans.Produto;
import componentes.beans.Subcategoria;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterProduto extends FormProdutoBase{




private static final long serialVersionUID = 1L;




	public FormAlterProduto(Produto retorno) {
	
	super("Dados de Produto", 850, 620, retorno);
	
	this.setInfosAdicionais();
	
	this.adicionarComponentes();
	
	this.setValores();
	

	List<Fornecedor_X_Produto> lista_aux= 
			new DAO<Fornecedor_X_Produto>(Fornecedor_X_Produto.class).get(null, "fornXprod.fk_produto="+retorno.getId_produto(), null);

	DAO<Fornecedor> fornecedor_dao = new DAO<Fornecedor>(Fornecedor.class);		
			for(Fornecedor_X_Produto aux: lista_aux){
			
			Fornecedor fornecedor = fornecedor_dao.get(aux.getFk_fornecedor());
			
				if(fornecedor !=null){
					
				Item item = new Item();
					
				item.addParamentro("id", fornecedor.getId_fornecedor());
				
				item.addParamentro("descricao", fornecedor.getNome_razao());
				item.addParamentro("valor", aux.getPreco());
			
				this.lista_fornecedores.add(item);
				}
			}
			
			
	this.atualizaTabelaDeFornecedores();
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
		
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	infoPanel.add(new Rotulo("<html><b>Cadastrado em:</b> "+Data.converteDataParaString(this.retorno.getData_cadastro())+" | <b>Código: </b>"+Comuns.getCod(Produto.class, this.retorno.getId_produto())+"</html>"), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 1;
	infoPanel.add(new JLabel(""), cons);
	}
	
	
	
	

	public void setValores(){
	
	this.tf_cod_ean.setText(this.retorno.getCodigo_ean());
	this.tf_nome.setText(this.retorno.getNome());
	this.tf_descricao_abreviada.setText(this.retorno.getDescricao_abreviada());	
	this.tf_descricao.setText(this.retorno.getDescricao());	
	
	this.tf_marca.setText(this.retorno.getMarca());
	this.tf_especificacao.setText(this.retorno.getEspecificacao());
	this.tf_cod_ncm.setText(this.retorno.getCod_ncm());
	
	this.venda_fracionada.setSelectedItem(this.retorno.getVenda_fracionada());
	this.tipo_venda_fracionada.setSelectedItem(this.retorno.getTipo_venda_fracionada());
	this.unidades_pacote.setText(""+this.retorno.getUnidades_pacote());
	this.tf_peso.setText(""+this.retorno.getPeso_em_gramas());
	
	this.tf_secao.setText(this.retorno.getSecao());
	this.tf_garantia.setText(""+this.retorno.getGarantia_da_loja_em_dias());
	this.tf_volume.setText(this.retorno.getVolume_m3());
	
	this.tf_custo.setText(this.retorno.getValor_de_custo());
	this.lucro.setText(this.retorno.getLucro());
	this.valor_final.setText(this.retorno.getValor_final());
	this.valor_fracionado.setText(this.retorno.getValor_fracionado());
	
	this.valor_icms_porcento.setText(this.retorno.getValor_icms_porcento());
	this.valor_pis_porcento.setText(this.retorno.getValor_pis_porcento());
	this.valor_cofins_porcento.setText(this.retorno.getValor_cofins_porcento());
	this.valor_ipi_porcento.setText(this.retorno.getValor_ipi_porcento());
	this.valor_csll_porcento.setText(this.retorno.getValor_csll_porcento());
	this.valor_irpj_porcento.setText(this.retorno.getValor_irpj_porcento());
	this.valor_comissao_porcento.setText(this.retorno.getValor_comissao_porcento());
	this.valor_administrativa_porcento.setText(this.retorno.getValor_administrativa_porcento());
	this.valor_issqn_porcento.setText(this.retorno.getValor_issqn_porcento());
	
	this.tf_cod_cfop.setText(this.retorno.getCodigo_cfop());
	
	
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
	}
	
	
	

	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;	
			
	this.calculaValor();	
					
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

	
	if(!new DAO<Produto>(Produto.class).altera(this.retorno))
	return false;	


	DAO<Fornecedor_X_Produto> x_DAO = new DAO<Fornecedor_X_Produto>(Fornecedor_X_Produto.class);
	
	x_DAO.removeSemConfirmacao("fk_produto="+this.retorno.getId_produto());
	
	
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
