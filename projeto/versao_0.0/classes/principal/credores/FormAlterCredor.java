package principal.credores;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import DAO.DAO;
import componentes.Rotulo;
import componentes.beans.ContaBancaria;
import componentes.beans.Credor;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterCredor extends FormCredorBase{




private static final long serialVersionUID = 1L;

	



	public FormAlterCredor(Credor credor) {
	
	super("Dados de Credor", 700, 540);
	
	this.credorRetorno = credor;	
	
	this.setInfosAdicionais();
	
	this.adicionarComponentes();
	
	this.setValores();
	}


	
	
	
	private void setInfosAdicionais(){
		
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel infoPanel = new JPanel(new GridBagLayout());
	infoPanel.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(infoPanel, cons);
			
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	infoPanel.add(new Rotulo("<html><b>Cadastrado em:</b> "+Data.converteDataParaString(this.credorRetorno.getData_cadastro())+" | <b>Código: </b>"+Comuns.getCod(Credor.class, this.credorRetorno.getId_credor())+"</html>"), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	infoPanel.add(new JLabel(""), cons);
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 2, 0);
	infoPanel.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	}
	
	
	
	

	public void setValores(){
	
	this.tf_nome.setText(this.credorRetorno.getNome_razao());
	this.tipo.setSelectedItem(this.credorRetorno.getTipo());	
	
	if(this.tipo.getSelectedIndex()==0)
	this.tf_cpf.setText(this.credorRetorno.getCpf_cnpj());	
	else
	this.tf_cnpj.setText(this.credorRetorno.getCpf_cnpj());	
	
	this.tf_tel_1.setText(this.credorRetorno.getTel_1());
	this.tf_tel_2.setText(this.credorRetorno.getTel_2());
	this.tf_email.setText(this.credorRetorno.getEmail());
	
	this.p_contas.addContas(new DAO<ContaBancaria>(ContaBancaria.class).get(null, "cont_b.fk_credor="+this.credorRetorno.getId_credor(), null));
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	this.credorRetorno.setNome_razao(tf_nome.getText());
	this.credorRetorno.setTipo(String.valueOf(this.tipo.getSelectedItem()));
	this.credorRetorno.setCpf_cnpj(this.tipo.getSelectedIndex()==0?tf_cpf.getText():tf_cnpj.getText());
	this.credorRetorno.setTel_1(tf_tel_1.getText());
	this.credorRetorno.setTel_2(tf_tel_2.getText());
	this.credorRetorno.setEmail(tf_email.getText());
	
	if(!new DAO<Credor>(Credor.class).altera(this.credorRetorno))
	return false;
	
	DAO<ContaBancaria> contas_DAO  = new DAO<ContaBancaria>(ContaBancaria.class);
	
	if(!contas_DAO.removeSemConfirmacao("fk_credor="+this.credorRetorno.getId_credor()))
	return false;
	
	List<ContaBancaria> contas = this.p_contas.getContas();
	
		for(ContaBancaria item: contas){
			
		item.setFk_credor(this.credorRetorno.getId_credor());	
		
		new DAO<ContaBancaria>(ContaBancaria.class).novo(item);
		}

	return true;
	}




	
	
}
