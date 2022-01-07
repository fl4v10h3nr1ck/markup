package principal.fornecedores.grupos;

import java.util.List;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Fornecedor;
import componentes.beans.Grupo_fornecedor;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;



public class GestaoDeGrupos extends FormDeGestaoBase<Grupo_fornecedor>{

	

private static final long serialVersionUID = 1L;





	public GestaoDeGrupos(){
		
	super("Gestão de Fornecedores");
	
	this.motorDePesquisa = new MotorDePesquisa<Grupo_fornecedor>("Grupos de Fornecedores", Grupo_fornecedor.class);
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoGrupoDeFornecedor novo = new FormNovoGrupoDeFornecedor();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;

	Grupo_fornecedor selectedItem= (Grupo_fornecedor) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterGrupoDeFornecedor alterForm = new FormAlterGrupoDeFornecedor( new DAO<Grupo_fornecedor>(Grupo_fornecedor.class).get(selectedItem.getId_grupo_fornecedor()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de grupos para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Grupo_fornecedor selectedItem= (Grupo_fornecedor) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
				
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de grupos para exclusão.");
		else{
		
		DAO<Fornecedor> fornecedor_DAO = new DAO<Fornecedor>(Fornecedor.class);
		
		List<Fornecedor> fornecedores_do_grupo = fornecedor_DAO.get(null, "forn.fk_grupo="+selectedItem.getId_grupo_fornecedor(), null);
		
			for(Fornecedor fornecedor: fornecedores_do_grupo){
			
			fornecedor.setFk_grupo(0);
			fornecedor_DAO.altera(fornecedor);
			}
		
		new DAO<Grupo_fornecedor>(Grupo_fornecedor.class).removeSemConfirmacao(selectedItem.getId_grupo_fornecedor());
				
			
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}

