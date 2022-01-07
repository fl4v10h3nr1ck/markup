package principal.clientes;


import DAO.DAO;
import principal.FormDeGestaoBase;
import componentes.beans.Cliente;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;




public class GestãoDeClientes extends FormDeGestaoBase<Cliente>{

	

private static final long serialVersionUID = 1L;





	public GestãoDeClientes(){
		
	super("Gestão de Clientes");
	
	this.motorDePesquisa = new MotorDePesquisa<Cliente>("Clientes", Cliente.class);
	this.motorDePesquisa.tableModel.setWhere("cl.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by cl.data_cadastro DESC");
	
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoCliente novo = new FormNovoCliente();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Cliente selectedItem= (Cliente) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterCliente alterForm = new FormAlterCliente( new DAO<Cliente>(Cliente.class).get(selectedItem.getId_cliente()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de clientes para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Cliente selectedItem= (Cliente) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
			
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de clientes para exclusão.");
		else{
		
		if( new DAO<Cliente>(Cliente.class).desativar(selectedItem.getId_cliente()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}

