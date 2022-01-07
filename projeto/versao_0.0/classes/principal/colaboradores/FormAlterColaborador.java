package principal.colaboradores;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.DAO;
import componentes.beans.Colaborador;
import componentes.beans.Usuario;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterColaborador extends FormColaboradorBase{




private static final long serialVersionUID = 1L;

	



	public FormAlterColaborador(Colaborador retorno) {
	
	super("Dados de Colaborador", 650, 260, retorno);
	
	
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
	infoPanel.add(new JLabel("<html>Cadastrado em: <b>"+Data.converteDataParaString(this.retorno.getData_cadastro())+"</b> | Código: <b>"+Comuns.getCod(Colaborador.class, this.retorno.getId_colaborador())+"</b></html>"), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	infoPanel.add(new JLabel(""), cons);
	}
	
	
	
	
	

	public void setValores(){
	
	this.tf_nome.setText(this.retorno.getNome());
	this.tf_cpf.setText(this.retorno.getCpf());	
	
	this.tf_tel_interno.setText(this.retorno.getTel_interno());
	this.tf_tel_pessoal.setText(this.retorno.getTel_pessoal());
	this.tf_email.setText(this.retorno.getEmail());
	
	this.tf_valor_comissao.setText(this.retorno.getValor_comissao());
	
	//
	//this.idCargo = this.retorno.getFk_cargo();
	
	
		if(this.retorno.getFk_usuario()>0){
		
		Usuario usuario = new DAO<Usuario>(Usuario.class).get(this.retorno.getFk_usuario());
		
			if(usuario!=null){
			
			this.idUser = usuario.getId();
			this.tf_user.setText(usuario.getUsuario());
			}
		}
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	this.retorno.setNome(tf_nome.getText());
	this.retorno.setCpf(this.tf_cpf.getText());
	this.retorno.setTel_interno(this.tf_tel_interno.getText());
	this.retorno.setTel_pessoal(this.tf_tel_pessoal.getText());
	this.retorno.setEmail(this.tf_email.getText());
	this.retorno.setValor_comissao(this.tf_valor_comissao.getText());
	this.retorno.setFk_usuario(this.idUser);
	this.retorno.setFk_cargo(this.idCargo);
	
	
	return new DAO<Colaborador>(Colaborador.class).altera(this.retorno);
	}




	
	
}
