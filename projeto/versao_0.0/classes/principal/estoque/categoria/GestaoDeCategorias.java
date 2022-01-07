package principal.estoque.categoria;


import java.util.List;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Categoria;
import componentes.beans.Produto;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeCategorias extends FormDeGestaoBase<Categoria>{




private static final long serialVersionUID = 1L;




	public GestaoDeCategorias(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Categoria>("Categorias", Categoria.class);
	this.motorDePesquisa.tableModel.setOrderBy("order by cat.id_categoria DESC");
	
	adicionarComponentes();	
	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaCategoria novo = new FormNovaCategoria();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Categoria selectedItem= (Categoria) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterCategoria alterForm = new FormAlterCategoria( new DAO<Categoria>(Categoria.class).get(selectedItem.getId_categoria()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de categorias para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Categoria selectedItem= (Categoria) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
					
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de categorias para exclusão.");
		else{
		
		DAO<Produto> produto_DAO = new DAO<Produto>(Produto.class);
			
		List<Produto> produtos_por_categoria = produto_DAO.get(null, "prod.fk_categoria="+selectedItem.getId_categoria(), null);
			
			for(Produto produto: produtos_por_categoria){
				
			produto.setFk_categoria(0);
			produto_DAO.altera(produto);
			}
			
		new DAO<Categoria>(Categoria.class).removeSemConfirmacao(selectedItem.getId_categoria());
				
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
