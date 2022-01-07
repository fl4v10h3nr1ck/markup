package classes.estoque.cadastro.fornecedores;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.CampoCNPJ;
import classes.CampoCPF;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoTEL;
import classes.componentes.FormDeSelecao;
import classes.componentes.endereco.PainelDeEndereco;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Comuns;
import classes.comuns.Mensagens;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.Grupo_fornecedor;
import classes.estoque.cadastro.contas_bancarias.PainelDeContasBancarias;
import classes.estoque.cadastro.grupos_fornecedores.FormNovoGrupoDeFornecedor;





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



	public FormFornecedorBase(String titulo, Fornecedor retorno) {
		
	super(titulo, 800, 500);
	
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
	this.add(p1, cons);
	JPanel p2 = new JPanel(new GridBagLayout());
	this.add(p2, cons);
	JPanel p3 = new JPanel(new GridBagLayout());
	this.add(p3, cons);
	this.p_endereco = new PainelDeEndereco();
	this.add(p_endereco, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	this.add(this.p_contas=new PainelDeContasBancarias(3), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Nome/Razão Social:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html>Nome Fantasia/Apelido:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Responsável:"), cons);
	
	
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
	p2.add(new JLabel("Tipo:"), cons);
	p2.add(new JLabel("CPF:"), cons);
	p2.add(new JLabel("CNPJ:"), cons);
	cons.gridwidth  = 2;
	p2.add(new JLabel("Grupo:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new JLabel("RG/IE:"), cons);
	

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
	p3.add(new JLabel("<html>Tel.:<font color=red>*</font></html>"), cons);
	p3.add(new JLabel("Cel.:"), cons);
	p3.add(new JLabel("Fax:"), cons);	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new JLabel("E-mail:"), cons);
	
	
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
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
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
		
	
		this.tf_grupo.addMouseListener( new MouseAdapter(){	
		@Override 
		public void mouseClicked(MouseEvent e) {
			
		if(!tf_grupo.isEnabled())	
		return;
			
		addGrupo();
		}});
		
	
		this.tipo.addItemListener( new ItemListener(){	
		@Override
		public void itemStateChanged(ItemEvent arg0){
		
		setTipoDePessoa();	
		}});
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome_razao);
	textFieldList.add(this.tf_nome_fantasia);
	textFieldList.add(tf_cpf);
	textFieldList.add(tf_cnpj);
	textFieldList.add(tf_tel_1);
	textFieldList.add(tf_tel_2);
	textFieldList.add(tf_email);
	textFieldList.add(tf_grupo);
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.setTipoDePessoa();
	}
	
	
	
		
	
	
	
	protected void setTipoDePessoa(){
		
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
			
		Mensagens.msgDeErro("Informe o nome do fornecedor.");
		Comuns.textFieldErroMode(this.tf_nome_razao);
		return false;	
		}
		
		if(this.tf_nome_fantasia.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o nome fantasia do fornecedor.");
		Comuns.textFieldErroMode(this.tf_nome_fantasia);
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
		
		
	return this.p_endereco.validacao();	
	}
	
	
	

	
	private void addGrupo(){
		
	Grupo_fornecedor retorno = new Grupo_fornecedor();
		
	FormDeSelecao<Grupo_fornecedor> selectionItemForm = 
					new FormDeSelecao<Grupo_fornecedor>("Adicionar Grupo", retorno, Grupo_fornecedor.class, "g_forn.ativo='S'");
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
