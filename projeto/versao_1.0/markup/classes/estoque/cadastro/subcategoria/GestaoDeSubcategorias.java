package classes.estoque.cadastro.subcategoria;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

import classes.componentes.tabelas.MotorDePesquisa;
import classes.comuns.Mensagens;
import classes.dao.DAO;
import classes.estoque.beans.Subcategoria;
import classes.principal.FormDeGestaoBase;





public class GestaoDeSubcategorias extends FormDeGestaoBase<Subcategoria>{




private static final long serialVersionUID = 1L;




	public GestaoDeSubcategorias(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Subcategoria>("Gestão de Subcategorias", Subcategoria.class);
	this.motorDePesquisa.modelo.setOrderBy("order by subcat.id_subcategoria DESC");
	this.motorDePesquisa.modelo.setWhere("subcat.ativo='S'");
	
	adicionarComponentes();	
	
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(0, 0, 0, 0);
	this.motorDePesquisa.painel_menu_de_opcoes.add(new JLabel("") , cons);
	
	this.motorDePesquisa.atualizar();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovaSubcategoria novo = new FormNovaSubcategoria();
	novo.mostrar();
		
	motorDePesquisa.atualizar();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Subcategoria selecionado= (Subcategoria) this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterSubcategoria alterForm = new FormAlterSubcategoria(selecionado);
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de subcategorias para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Subcategoria selecionado= (Subcategoria) this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de subcategorias para exclusão.");
		else{
			
		selecionado.setAtivo("N");
			
		new DAO<Subcategoria>(Subcategoria.class).altera(selecionado);
				
		motorDePesquisa.atualizar();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
