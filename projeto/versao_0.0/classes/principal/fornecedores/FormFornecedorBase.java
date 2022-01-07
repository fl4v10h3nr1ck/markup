package principal.fornecedores;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.CampoCNPJ;
import classes.CampoCPF;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoTEL;
import principal.contas_bancarias.PainelDeContasBancarias;
import principal.endereco.PainelDeEndereco;
import principal.fornecedores.grupos.FormNovoGrupoDeFornecedor;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Fornecedor;
import componentes.beans.Grupo_fornecedor;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormFornecedorBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome_razao;
protected CampoLimitado tf_nome_fantasia;
protected CampoLimitado tf_resp;
protected JComboBox<Object> tipo;
protected CampoCPF tf_cpf;
protected CampoCNPJ tf_cnpj;
protected CampoLimitadoSoDigito tf_rg_ie;

protected CampoTEL tf_tel_1;
protected CampoTEL tf_tel_2;
protected CampoLimitado tf_fax;
protected CampoLimitado tf_email;

protected CampoLimitado tf_grupo;
protected int idGrupo;			
protected JButton bt_novo_grupo;


protected PainelDeEndereco p_endereco;
protected PainelDeContasBancarias p_contas;


protected Fornecedor retorno;



	public FormFornecedorBase(String title, int width, int height, Fornecedor retorno) {
		
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
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p3, cons);
	this.p_endereco = new PainelDeEndereco();
	this.add(p_endereco, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	this.add(this.p_contas=new PainelDeContasBancarias(), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Nome/Razão Social:<font color=red>*</font></html>"), cons);
	p1.add(new Rotulo("Nome Fantasia/Apelido:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo("Responsável:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_nome_razao = new CampoLimitado(200);
	p1.add(this.tf_nome_razao, cons);
	
	this.tf_nome_fantasia = new CampoLimitado(200);
	p1.add(this.tf_nome_fantasia, cons);
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_resp = new CampoLimitado(200);
	p1.add(this.tf_resp, cons);
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new Rotulo("Tipo:"), cons);
	p2.add(new Rotulo("CPF:"), cons);
	p2.add(new Rotulo("CNPJ:"), cons);
	cons.gridwidth  = 2;
	p2.add(new Rotulo("Grupo:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new Rotulo("RG/IE:"), cons);
	

	cons.insets = new Insets(2, 2, 2, 2);
	cons.gridwidth  = 1;
	this.tipo = new JComboBox<Object>(new String[]{"PF", "PJ"});
	p2.add(this.tipo, cons);
	
	this.tf_cpf = new CampoCPF();
	p2.add(this.tf_cpf, cons);
	
	this.tf_cnpj = new CampoCNPJ();
	p2.add(this.tf_cnpj, cons);
	
	this.tf_grupo = new CampoLimitado(200);
	this.tf_grupo.setEditable(false);
	p2.add(this.tf_grupo, cons);
	
	cons.anchor  = GridBagConstraints.WEST;	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_novo_grupo  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_novo_grupo.setToolTipText("Cadastrar um novo grupo de fornecedores");
	p2.add(bt_novo_grupo, cons);
	
	
	cons.fill  = GridBagConstraints.HORIZONTAL;	
	cons.weightx = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_rg_ie = new CampoLimitadoSoDigito(15);
	p2.add(this.tf_rg_ie, cons);
	

	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new Rotulo("<html>Tel.:<font color=red>*</font></html>"), cons);
	p3.add(new Rotulo("Tel.:"), cons);
	p3.add(new Rotulo("Fax:"), cons);	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new Rotulo("E-mail:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_tel_1 = new CampoTEL();
	p3.add(this.tf_tel_1, cons);
		
	this.tf_tel_2 = new CampoTEL();
	p3.add(this.tf_tel_2, cons);
		
	this.tf_fax = new CampoLimitado(20);
	p3.add(this.tf_fax, cons);

	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_email = new CampoLimitado(100);
	p3.add(this.tf_email, cons);

	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar Fornecedor");
	this.add(bt_save, cons);
		
		

		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		

		bt_novo_grupo.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		novoGrupo();
		}});
		
	
		this.tf_grupo.addFocusListener( new FocusAdapter(){
			
		@Override 
		public void focusGained(FocusEvent e) {
			
		tf_rg_ie.requestFocusInWindow();
		
		Comuns.removeIndicadoresDeErro(tf_grupo);
		addGrupo();
		}});
		
	
		this.tipo.addItemListener( new ItemListener(){	
		@Override
		public void itemStateChanged(ItemEvent arg0){
		
		setPessoa();	
		}});
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome_razao);
	textFieldList.add(tf_cpf);
	textFieldList.add(tf_cnpj);
	textFieldList.add(tf_tel_1);
	textFieldList.add(tf_tel_2);
	textFieldList.add(tf_email);
	textFieldList.add(tf_grupo);
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.setPessoa();
	}
	
	
	
		
	
	
	
	protected void setPessoa(){
		
		if(tipo.getSelectedIndex() == 0){
			
		tf_cpf.setEnabled(true);
		
		tf_cnpj.setEnabled(false);
		tf_cnpj.setText("");
		}
		else{
				
		tf_cnpj.setEnabled(true);
			
		tf_cpf.setEnabled(false);
		tf_cpf.setText("");				
		}	
	} 
	

	
	

	
	protected boolean validation(){
	
	
		if(this.tf_nome_razao.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o nome do cliente.");
		Comuns.textFieldErroMode(this.tf_nome_razao);
		return false;	
		}
		
		if(this.tipo.getSelectedIndex() == 0){
			if(this.tf_cpf.getText().length()>0){
				if(!this.tf_cpf.validacao()){
				
				Mensagens.msgDeErro("Informe um CPF válido.");
				Comuns.textFieldErroMode(this.tf_cpf);
				return false;	
				}
			}
		}	
		else{
			if(this.tf_cnpj.getText().length()>0){
				if(!this.tf_cnpj.validacao()){
					
				Mensagens.msgDeErro("Informe um CNPJ válido.");
				Comuns.textFieldErroMode(this.tf_cnpj);
				return false;	
				}
			}
		}
		
		
	
		if(!this.tf_tel_1.validacao()){
			
		Mensagens.msgDeErro("Informe um telefone válido.");
		Comuns.textFieldErroMode(this.tf_tel_1);
		return false;	
		}
		
		if(this.tf_tel_2.getText().length() > 0){
			if(!this.tf_tel_2.validacao()){
		
			Mensagens.msgDeErro("Celular é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_tel_2);
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
	
	
	

	
	private void addGrupo(){
		
	Grupo_fornecedor retorno = new Grupo_fornecedor();
		
	FormDeSelecao<Grupo_fornecedor> selectionItemForm = 
					new FormDeSelecao<Grupo_fornecedor>("Adicionar Grupo", retorno, Grupo_fornecedor.class, null);
	selectionItemForm.mostrar();
				
		if(retorno != null && retorno.getId_grupo_fornecedor() > 0){
							
		this.idGrupo = retorno.getId_grupo_fornecedor();
		this.tf_grupo.setText(retorno.getDescricao());
		}
	}



	
	
	

	private void novoGrupo(){
		
	Grupo_fornecedor retorno = new Grupo_fornecedor();
		
	FormNovoGrupoDeFornecedor	form = new FormNovoGrupoDeFornecedor(retorno);
	form.mostrar();
	
		if(retorno != null && retorno.getId_grupo_fornecedor() > 0){
		
		this.idGrupo = retorno.getId_grupo_fornecedor();
		this.tf_grupo.setText(retorno.getDescricao());
		}
	}

	
}
