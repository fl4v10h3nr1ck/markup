package classes.estoque.cadastro.grupos_fornecedores;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

import classes.componentes.tabelas.MotorDePesquisa;
import classes.comuns.Mensagens;
import classes.dao.DAO;
import classes.estoque.beans.Grupo_fornecedor;
import classes.principal.FormDeGestaoBase;





public class GestaoDeGruposDeFornecedores extends FormDeGestaoBase<Grupo_fornecedor>{

	

private static final long serialVersionUID = 1L;





	public GestaoDeGruposDeFornecedores(){
		
	super("Gestão de Fornecedores");
	
	this.motorDePesquisa = new MotorDePesquisa<Grupo_fornecedor>("Grupos de Fornecedores", Grupo_fornecedor.class);
	this.motorDePesquisa.modelo.setOrderBy("order by g_forn.id_grupo_fornecedor DESC");
	this.motorDePesquisa.modelo.setWhere("g_forn.ativo='S'");
	
	
	adicionarComponentes();	
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(0, 0, 0, 0);
	this.motorDePesquisa.painel_menu_de_opcoes.add(new JLabel("") , cons);
	
	motorDePesquisa.atualizar();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoGrupoDeFornecedor novo = new FormNovoGrupoDeFornecedor();
	novo.mostrar();
		
	motorDePesquisa.atualizar();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;

	Grupo_fornecedor selecionado= (Grupo_fornecedor) this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterGrupoDeFornecedor alterForm = new FormAlterGrupoDeFornecedor(selecionado);
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de grupos para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Grupo_fornecedor selecionado= (Grupo_fornecedor) this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
					
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de grupos para exclusão.");
		else{
		
		selecionado.setAtivo("N");
		
		new DAO<Grupo_fornecedor>(Grupo_fornecedor.class).altera(selecionado);
					
		motorDePesquisa.atualizar();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}

