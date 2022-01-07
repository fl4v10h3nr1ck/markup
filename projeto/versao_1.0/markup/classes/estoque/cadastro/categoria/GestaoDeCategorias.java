package classes.estoque.cadastro.categoria;


import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import classes.componentes.tabelas.MotorDePesquisa;
import classes.comuns.Mensagens;
import classes.dao.DAO;
import classes.estoque.beans.Categoria;
import classes.principal.FormDeGestaoBase;






public class GestaoDeCategorias extends FormDeGestaoBase<Categoria>{




private static final long serialVersionUID = 1L;




	public GestaoDeCategorias(){
		
	super("Gestão de Categorias");
	
	this.motorDePesquisa = new MotorDePesquisa<Categoria>("Gestão de Categorias", Categoria.class);
	this.motorDePesquisa.modelo.setOrderBy("order by cat.id_categoria DESC");
	this.motorDePesquisa.modelo.setWhere("cat.ativo='S'");
	
	
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
	
	FormNovaCategoria novo = new FormNovaCategoria();
	novo.mostrar();	
	
	this.motorDePesquisa.atualizar();
	}





	@Override
	public void alterarForm() {
		
	////if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Categoria selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterCategoria alterForm = new FormAlterCategoria(selecionado);
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de categorias para alteração.");
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Categoria selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de categorias para exclusão.");
		else{
		
		selecionado.setAtivo("N");	
			
		new DAO<Categoria>(Categoria.class).altera(selecionado);
				
		motorDePesquisa.atualizar();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
