package principal.estoque.subcategoria;


import java.util.List;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Produto;
import componentes.beans.Subcategoria;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeSubCategorias extends FormDeGestaoBase<Subcategoria>{




private static final long serialVersionUID = 1L;




	public GestaoDeSubCategorias(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Subcategoria>("Subcategorias", Subcategoria.class);
	this.motorDePesquisa.tableModel.setOrderBy("order by subcat.id_subcategoria DESC");
	
	adicionarComponentes();	
	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaSubcategoria novo = new FormNovaSubcategoria();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Subcategoria selectedItem= (Subcategoria) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterSubcategoria alterForm = new FormAlterSubcategoria( new DAO<Subcategoria>(Subcategoria.class).get(selectedItem.getId_subcategoria()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de subcategorias para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Subcategoria selectedItem= (Subcategoria) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
						
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de subcategorias para exclusão.");
		else{
		
		DAO<Produto> produto_DAO = new DAO<Produto>(Produto.class);
			
		List<Produto> produtos_por_categoria = produto_DAO.get(null, "prod.fk_sub_categoria="+selectedItem.getId_subcategoria(), null);
			
			for(Produto produto: produtos_por_categoria){
				
			produto.setFk_sub_categoria(0);
			produto_DAO.altera(produto);
			}
			
		new DAO<Subcategoria>(Subcategoria.class).removeSemConfirmacao(selectedItem.getId_subcategoria());
				
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
