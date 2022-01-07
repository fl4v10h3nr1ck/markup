package principal.colaboradores;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import DAO.DAO;
import principal.FormDeGestaoBase;
import principal.colaboradores.cargos.GestaoDeCargos;
import componentes.Rotulo;
import componentes.beans.Colaborador;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;



public class GestaoDeColaboradores extends FormDeGestaoBase<Colaborador>{




private static final long serialVersionUID = 1L;






	public GestaoDeColaboradores(){
		
	super("Gestão de Colaboradores");
	
	this.motorDePesquisa = new MotorDePesquisa<Colaborador>("Colaboradores", Colaborador.class);
	this.motorDePesquisa.tableModel.setWhere("cola.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by cola.id_colaborador DESC");
	
	adicionarComponentes();	
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.insets = new Insets(5, 5, 0, 5);
	JButton btCargos = new JButton(new ImageIcon(getClass().getResource("/icons/cargos.png")));
	btCargos.setToolTipText("Gerenciar cargos");
	this.painelNovosItens.add(btCargos, cons);
	
	
	
	cons.insets = new Insets(0, 5, 0, 5);
	cons.gridwidth = 1;
	this.painelNovosItens.add(new Rotulo("Cargos") , cons);
	
	
	
		btCargos.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		GestaoDeCargos form = new GestaoDeCargos();
		form.mostrar();
			
		motorDePesquisa.update();	
		}});

	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoColaborador novo = new FormNovoColaborador();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Colaborador selectedItem= (Colaborador) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterColaborador alterForm = new FormAlterColaborador( new DAO<Colaborador>(Colaborador.class).get(selectedItem.getId_colaborador()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de colaboradores para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Colaborador selectedItem= (Colaborador) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
					
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de colaboradores para exclusão.");
		else{
		
		if( new DAO<Colaborador>(Colaborador.class).desativar(selectedItem.getId_colaborador()))		
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

