package principal.colaboradores;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import principal.colaboradores.cargos.FormNovoCargo;
import classes.CampoCPF;
import classes.CampoLimitado;
import classes.CampoMoeda;
import classes.CampoTEL;
import componentes.FormDeSelecao;
import componentes.beans.Cargo;
import componentes.beans.Colaborador;
import componentes.beans.Usuario;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormColaboradorBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoCPF tf_cpf;
protected CampoTEL tf_tel_interno;
protected CampoTEL tf_tel_pessoal;
protected CampoLimitado tf_email;
protected CampoMoeda tf_valor_comissao;

protected CampoLimitado tf_cargo;
protected int idCargo;		
private JButton bt_novo_cargo;


protected CampoLimitado tf_user;
protected int idUser;



protected Colaborador retorno;


	




	public FormColaboradorBase(String title, int width, int height, Colaborador retorno) {
		
	super(title, width, height);
	
	this.retorno = retorno;
	}
	

	
	

	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
		
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 2;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Nome Completo:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	p1.add(new JLabel("CPF:"), cons);
	
	cons.gridwidth  = 2;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_nome = new CampoLimitado(200);
	p1.add(this.tf_nome, cons);
		
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_cpf = new CampoCPF();
	p1.add(this.tf_cpf, cons);
		
	cons.gridwidth  = 1;	
	cons.weightx = 0.3;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("Ramal Interno:"), cons);
	p1.add(new JLabel("Tel. Pessoal:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.4;
	p1.add(new JLabel("E-mail:"), cons);
		
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);	
	cons.weightx = 0.3;
	this.tf_tel_interno = new CampoTEL();
	p1.add(this.tf_tel_interno, cons);
		
	this.tf_tel_pessoal = new CampoTEL();
	p1.add(this.tf_tel_pessoal, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.4;
	this.tf_email = new CampoLimitado(150);
	p1.add(this.tf_email, cons);
	
		
	cons.gridwidth  = 1;	
	cons.weightx = 0.2;
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new JLabel("Valor Comissão: (%)"), cons);
	cons.gridwidth  = 2;
	cons.weightx = 0.4;
	p2.add(new JLabel("Cargo:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.4;
	p2.add(new JLabel("Usuário Vinculado:"), cons);
	
	cons.gridwidth  = 1;
	cons.weightx = 0.2;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_valor_comissao = new CampoMoeda(4);
	p2.add(this.tf_valor_comissao, cons);
	this.tf_valor_comissao.setText(Comuns.porcentComissaoPadrao);

	cons.weightx = 0.4;
	this.tf_cargo = new CampoLimitado(200);
	this.tf_cargo.setEditable(false);
	p2.add(this.tf_cargo, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_novo_cargo  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_novo_cargo.setToolTipText("Adicionar um cargo ao colaborador");
	p2.add(bt_novo_cargo, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.weightx = 0.2;
	this.tf_user = new CampoLimitado(200);
	this.tf_user.setEditable(false);
	p2.add(this.tf_user, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	cons.weightx = 0;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar cliente");
	this.add(bt_save, cons);
		
		

		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		
	
		bt_novo_cargo.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		addNovoCargo();
		}});
			
		
		this.tf_cargo.addFocusListener( new FocusAdapter(){
				
		@Override 
		public void focusGained(FocusEvent e) {
				
		bt_novo_cargo.requestFocusInWindow();
		addCargo();
		}});
		
		
			
		this.tf_user.addFocusListener( new FocusAdapter(){			
		@Override 
		public void focusGained(FocusEvent e) {
					
		bt_save.requestFocusInWindow();
		addUsuario();
		}});
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome);
	textFieldList.add(tf_cpf);
	textFieldList.add(tf_tel_interno);
	textFieldList.add(tf_tel_pessoal);
	textFieldList.add(tf_email);

	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	

	
	protected boolean validation(){
	
	
		if(this.tf_nome.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome do colaborador.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}
		
		if(this.tf_cpf.getText().length() > 0){
			if(!this.tf_cpf.validacao()){
			
			Mensagens.msgDeErro("CPF é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_cpf);
			return false;	
			}
		}	
		
		
		if(this.tf_tel_interno.getText().length() > 0){
			if(!this.tf_tel_interno.validacao()){
		
			Mensagens.msgDeErro("TEL interno é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_tel_interno);
			return false;
			}
		}
		
		
		if(this.tf_tel_pessoal.getText().length() > 0){
			if(!this.tf_tel_pessoal.validacao()){
		
			Mensagens.msgDeErro("TEL pessoal é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_tel_pessoal);
			return false;
			}
		}					
		
		if(this.tf_email.getText().length() > 0){			
			if(!Comuns.emailValidation(tf_email.getText())){
				
			Mensagens.msgDeErro("E-mail é opcional, caso queira informá-lo, informe um endereço válido.");
			Comuns.textFieldErroMode(this.tf_email);
			return false;	
			}
		}
		

	return true;	
	}
	
	


	
	private void addCargo(){
		
	Cargo retorno = new Cargo();
		
	FormDeSelecao<Cargo> selectionItemForm = 
									new FormDeSelecao<Cargo>("Adicionar Cargo", retorno, Cargo.class, null);
	selectionItemForm.mostrar();
								
		if(retorno != null && retorno.getId_cargo() > 0){
											
		this.idCargo = retorno.getId_cargo();
		this.tf_cargo.setText(retorno.getDescricao());
		}
	}
	
	

	
	
	
	private void addNovoCargo(){
		
	Cargo retorno = new Cargo();
		
	FormNovoCargo novo = new FormNovoCargo(retorno);
	novo.mostrar();
									
		if(retorno != null && retorno.getId_cargo() > 0){
												
		this.idCargo = retorno.getId_cargo();
		this.tf_cargo.setText(retorno.getDescricao());
		}
	}
	
	
	
	
	
	private void addUsuario(){
	
	Usuario retorno = new Usuario();
		
	FormDeSelecao<Usuario> selectionItemForm = 
								new FormDeSelecao<Usuario>("Adicionar Usuário", retorno, Usuario.class, "user.status='ATIVO'");
	selectionItemForm.mostrar();
							
		if(retorno != null && retorno.getId() > 0){
										
		this.idUser = retorno.getId();
		this.tf_user.setText(retorno.getUsuario());
		}		
	}
	
	
	
	
	

	
}
