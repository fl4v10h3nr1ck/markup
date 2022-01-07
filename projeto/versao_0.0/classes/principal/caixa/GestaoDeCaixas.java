package principal.caixa;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import DAO.DAO;
import principal.FormDeGestaoBase;
import componentes.Rotulo;
import componentes.beans.Caixa;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;







public class GestaoDeCaixas extends FormDeGestaoBase<Caixa>{




private static final long serialVersionUID = 1L;






	public GestaoDeCaixas(){
		
	super("Gestão de Caixas");
	
	this.motorDePesquisa = new MotorDePesquisa<Caixa>("Caixas", Caixa.class);
	this.motorDePesquisa.tableModel.setOrderBy("order by Cai.data_abertura DESC");
	
	adicionarComponentes();	
	
	this.bt_novo.setEnabled(false);
	this.bt_alterar.setEnabled(false);
	this.bt_deletar.setEnabled(false);
	
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.insets = new Insets(5, 5, 0, 5);
	JButton btver = new JButton(new ImageIcon(getClass().getResource("/icons/ver.png")));
	btver.setToolTipText("Ver caixa Selecionado");
	this.painelNovosItens.add(btver, cons);
	
	
	cons.insets = new Insets(0, 5, 0, 5);
	cons.gridwidth = 1;
	this.painelNovosItens.add(new Rotulo("Detalhes") , cons);
	
	
	
		btver.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		alterarForm();	
		}});
	
	
	
	
	
	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
		
	Caixa selectedItem= (Caixa) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
			
		if(selectedItem != null){
			
		FormVerCaixa alterForm = new FormVerCaixa( new DAO<Caixa>(Caixa.class).get(selectedItem.getId_caixa()));
		alterForm.mostrar();
			
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de caixas para ver detalhes.");		
		
	}





	@Override
	public void deletarForm() {}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	

}

