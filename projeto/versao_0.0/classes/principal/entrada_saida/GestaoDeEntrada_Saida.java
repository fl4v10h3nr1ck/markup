package principal.entrada_saida;


import DAO.DAO;
import principal.FormDeGestaoBase;
import componentes.beans.Entrada_Saida;
import componentes.beans.Parcela;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;



public class GestaoDeEntrada_Saida extends FormDeGestaoBase<Entrada_Saida>{



private static final long serialVersionUID = 1L;


private int tipo;





	public GestaoDeEntrada_Saida(int tipo){
		
	super("Gestão de "+(tipo==0?"Pagamentos":"Recebimentos"));
	
	this.tipo = tipo;
	
	this.motorDePesquisa = new MotorDePesquisa<Entrada_Saida>((tipo==0?"Pagamentos":"Recebimentos"), Entrada_Saida.class);
	this.motorDePesquisa.tableModel.setWhere("pag_rec.tipo='"+(tipo==0?"SAIDA":"ENTRADA")+"'");
	this.motorDePesquisa.tableModel.setOrderBy("order by pag_rec.data_cadastro DESC");
		
	adicionarComponentes();	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaEntrada_Saida novo = new FormNovaEntrada_Saida(this.tipo);
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Entrada_Saida selectedItem= (Entrada_Saida) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterPagamento alterForm = new FormAlterPagamento( new DAO<Entrada_Saida>(Entrada_Saida.class).get(selectedItem.getId_entrada_saida()), this.tipo);
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de "+(tipo==0?"pagamentos":"recebimentos")+" para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Entrada_Saida selectedItem= (Entrada_Saida) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
						
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de "+(tipo==0?"pagamentos":"recebimentos")+" para exclusão.");
		else{
			
		if(!new DAO<Parcela>(Parcela.class).removeSemConfirmacao("fk_entrada_saida="+selectedItem.getId_entrada_saida()))
		return;	
		
		
		if(new DAO<Entrada_Saida>(Entrada_Saida.class).removeSemConfirmacao(selectedItem.getId_entrada_saida()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

