package principal.clientes;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import DAO.DAO;
import componentes.Rotulo;
import componentes.beans.Cliente;
import componentes.beans.Endereco;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterCliente extends FormClienteBase{




private static final long serialVersionUID = 1L;

	




	public FormAlterCliente(Cliente cliente) {
	
	super("Dados de Cliente", 800, 380);
	
	this.clienteRetorno = cliente;	
	
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
	infoPanel.add(new Rotulo("<html><b>Cadastrado em:</b> "+Data.converteDataParaString(this.clienteRetorno.getData_cadastro())+" | <b>Código: </b>"+Comuns.getCod(Cliente.class, this.clienteRetorno.getId_cliente())+"</html>"), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	infoPanel.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 2, 0);
	infoPanel.add(new JSeparator(SwingConstants.HORIZONTAL), cons);

	}
	
	
	
	

	public void setValores(){
	
	this.tf_nome_razao.setText(this.clienteRetorno.getNome_razao());
	this.tipo.setSelectedItem(this.clienteRetorno.getTipo());
	
		if(this.clienteRetorno.getTipo().compareTo("PF")==0){
	
		this.tf_nascimento.setText(Data.converteDataParaString(this.clienteRetorno.getNascimento()));	
		this.combo_sexo.setSelectedItem(this.clienteRetorno.getSexo());
		this.tf_rg.setText(clienteRetorno.getRg_ie());
		this.tf_cpf.setText(this.clienteRetorno.getCpf_cnpj());	
		}
		else{
			
		this.tf_nome_fantasia.setText(this.clienteRetorno.getNome_fantasia());	
		this.tf_ie.setText(clienteRetorno.getRg_ie());
		this.tf_cnpj.setText(this.clienteRetorno.getCpf_cnpj());	
		this.tf_insc_municipal.setText(this.clienteRetorno.getInscricao_municipal());
		}
	
		
	this.tf_tel.setText(this.clienteRetorno.getTel_1());
	this.tf_cel.setText(this.clienteRetorno.getTel_2());
	this.tf_email.setText(this.clienteRetorno.getEmail());
	
	Endereco endereco = new DAO<Endereco>(Endereco.class).get(this.clienteRetorno.getFk_endereco());
	
	if(endereco!=null)
	this.p_endereco.setValores(endereco);
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	this.clienteRetorno.setNome_razao(tf_nome_razao.getText());
	this.clienteRetorno.setTipo(String.valueOf(this.tipo.getSelectedItem()));
	
		if(this.clienteRetorno.getTipo().compareTo("PF") ==0 ){
	
		this.clienteRetorno.setSexo(String.valueOf(combo_sexo.getSelectedItem()));
			
			if(this.tf_nascimento.getText().length() > 0){
			
			try {this.clienteRetorno.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(tf_nascimento.getText()));} 
			catch (ParseException e) {}
			}	
			
			
		this.clienteRetorno.setCpf_cnpj(tf_cpf.getText());	
		this.clienteRetorno.setRg_ie(this.tf_rg.getText());
		}
		else{
		
		this.clienteRetorno.setNome_fantasia(this.tf_nome_fantasia.getText());		
		this.clienteRetorno.setCpf_cnpj(tf_cnpj.getText());	
		this.clienteRetorno.setRg_ie(this.tf_ie.getText());			
		this.clienteRetorno.setInscricao_municipal(this.tf_insc_municipal.getText());	
		}
		
		
	this.clienteRetorno.setTel_1(tf_tel.getText());
	this.clienteRetorno.setTel_2(tf_cel.getText());
	this.clienteRetorno.setEmail(tf_email.getText());
	
	Endereco endereco = this.p_endereco.getValores();
	endereco.setId_endereco(this.clienteRetorno.getFk_endereco());
	
	if(!new DAO<Endereco>(Endereco.class).altera(endereco))
	return false;
	
	return new DAO<Cliente>(Cliente.class).altera(this.clienteRetorno);
	}




}