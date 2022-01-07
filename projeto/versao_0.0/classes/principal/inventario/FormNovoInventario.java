package principal.inventario;



import java.util.Date;

import DAO.DAO;
import componentes.beans.Inventario;
import componentes.beans.Inventario_Fornecedor;
import componentes.beans.Item;
import comuns.Comuns;





public class FormNovoInventario extends FormInventarioBase{


private static final long serialVersionUID = 1L;





	public FormNovoInventario(){
	
	this(null);
	}


	
	
	
	
	public FormNovoInventario(Inventario inventario){
		
	super("Novo Item de Inventário", 650, 500, inventario);	
	
	adicionarComponentes();
	}
	
	

	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	if(this.inventario ==null)
	this.inventario = new Inventario();

	this.inventario.setNome(tf_nome.getText());
	this.inventario.setDescricao(this.tf_descricao.getText());
	this.inventario.setCod_ean(this.tf_ean.getText());
	this.inventario.setStatus("ATIVO");
	this.inventario.setData_cadastro(new Date());
	this.inventario.setQuantidade(0);
	this.inventario.setQuant_min_inventario(Comuns.quantMinPadraoInventario);
	this.inventario.setValor_custo(this.tf_valor_custo.getText());
	
	int id = new DAO<Inventario>(Inventario.class).novo(this.inventario);
	
	if(id == 0)
	return false;

	this.inventario.setId_inventario(id);
	
	DAO<Inventario_Fornecedor> x_DAO = new DAO<Inventario_Fornecedor>(Inventario_Fornecedor.class);
	
		for(Item item: this.lista_fornecedores){
			
		Inventario_Fornecedor aux = new Inventario_Fornecedor();
			
		aux.setFk_fornecedor(Integer.parseInt(item.getParamentro("id").toString()));
		aux.setFk_inventario(this.inventario.getId_inventario());
		aux.setValor(item.getParamentro("valor").toString());
		
		x_DAO.novo(aux);
		}
	
	return true;
	}






	
	
}
