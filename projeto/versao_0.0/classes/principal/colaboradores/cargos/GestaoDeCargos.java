package principal.colaboradores.cargos;

import java.util.List;

import principal.FormDeGestaoBase;
import DAO.DAO;
import componentes.beans.Cargo;
import componentes.beans.Colaborador;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestaoDeCargos extends FormDeGestaoBase<Cargo>{




private static final long serialVersionUID = 1L;






	public GestaoDeCargos(){
		
	super("Gestão de Cargos");
	
	this.motorDePesquisa = new MotorDePesquisa<Cargo>("Cargos", Cargo.class);
	this.motorDePesquisa.tableModel.setOrderBy("order by carg.id_cargo DESC");
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoCargo novo = new FormNovoCargo();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Cargo selectedItem= (Cargo) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterCargo alterForm = new FormAlterCargo( new DAO<Cargo>(Cargo.class).get(selectedItem.getId_cargo()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de cargos para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Cargo selectedItem= (Cargo) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
						
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de cargos para exclusão.");
		else{
		
		DAO<Colaborador> colaborador_DAO = new DAO<Colaborador>(Colaborador.class);
			
		List<Colaborador> colaboradores_do_cargo = colaborador_DAO.get(null, "cola.fk_cargo="+selectedItem.getId_cargo(), null);
			
			for(Colaborador colaborador: colaboradores_do_cargo){
				
			colaborador.setFk_cargo(0);
			colaborador_DAO.altera(colaborador);
			}
			
		new DAO<Cargo>(Cargo.class).removeSemConfirmacao(selectedItem.getId_cargo());
							
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

