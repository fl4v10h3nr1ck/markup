package classes.estoque.cadastro.fornecedores;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import classes.componentes.tabelas.MotorDePesquisa;
import classes.comuns.Mensagens;
import classes.dao.DAO;
import classes.estoque.beans.Fornecedor;
import classes.estoque.cadastro.grupos_fornecedores.GestaoDeGruposDeFornecedores;
import classes.principal.FormDeGestaoBase;






public class GestaoDeFornecedores extends FormDeGestaoBase<Fornecedor>{

	

private static final long serialVersionUID = 1L;





	public GestaoDeFornecedores(){
		
	super("Gestão de Fornecedores");
	
	this.motorDePesquisa = new MotorDePesquisa<Fornecedor>("Fornecedores", Fornecedor.class);
	this.motorDePesquisa.modelo.setWhere("forn.ativo='S'");
	this.motorDePesquisa.modelo.setOrderBy("order by forn.id_fornecedor DESC");
	
	adicionarComponentes();	
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.weighty  = 0;
	cons.ipadx = 0;
	cons.gridwidth = 1;
	cons.insets = new Insets(0, 5, 0, 5);
	JButton grupo_fornecedores = new JButton(new ImageIcon(getClass().getResource("/icons/grupo_fornecedor.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(grupo_fornecedores, cons);
	grupo_fornecedores.setToolTipText("Gestão de grupos de fornecedores");
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.ipadx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.motorDePesquisa.painel_menu_de_opcoes.add(new JLabel(""), cons);
	
	
	
		grupo_fornecedores.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						  
		GestaoDeGruposDeFornecedores gestao = new GestaoDeGruposDeFornecedores();
		gestao.mostrar();
		}});
		
	
	this.motorDePesquisa.atualizar();
	}
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoFornecedor novo = new FormNovoFornecedor();
	novo.mostrar();
		
	motorDePesquisa.atualizar();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;

	Fornecedor selecionado= (Fornecedor) this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterFornecedor alterForm = new FormAlterFornecedor( new DAO<Fornecedor>(Fornecedor.class).get(selecionado.getId_fornecedor()));
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Fornecedor selecionado= (Fornecedor) this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
				
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de fornecedores para exclusão.");
		else{
			
		selecionado.setAtivo("N");	
			
		if( new DAO<Fornecedor>(Fornecedor.class).altera(selecionado))
		motorDePesquisa.atualizar();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}

