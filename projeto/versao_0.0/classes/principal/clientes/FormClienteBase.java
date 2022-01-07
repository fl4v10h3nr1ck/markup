package principal.clientes;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoTEL;
import principal.endereco.PainelDeEndereco;
import componentes.Rotulo;
import componentes.beans.Cliente;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormClienteBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome_razao;
protected JComboBox<String> tipo;
protected CampoCPF tf_cpf;
protected CampoCNPJ tf_cnpj;
protected CampoLimitadoSoDigito tf_insc_municipal;

protected CampoData tf_nascimento;
protected CampoLimitado tf_nome_fantasia;

protected JComboBox<String> combo_sexo;

protected CampoLimitadoSoDigito tf_rg;
protected CampoLimitadoSoDigito tf_ie;

protected CampoTEL tf_tel;
protected CampoTEL tf_cel;
protected CampoLimitado tf_email;
	
protected Cliente clienteRetorno;
	
protected PainelDeEndereco p_endereco;






	public FormClienteBase(String title, int width, int height) {
		
	this(title, width, height, null);
	}

	
	
	
	
	public FormClienteBase(String title, int width, int height, Cliente clienteRetorno) {
		
	super(title, width, height);
	
	this.clienteRetorno = clienteRetorno;
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
	this.p_endereco = new PainelDeEndereco();
	this.add(p_endereco, cons);
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p3, cons);
	JPanel convenioPanel = new JPanel(new GridBagLayout());
	this.add(convenioPanel, cons);
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	
	cons.weightx = 0.8;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;
	p1.add(new Rotulo("<html>Nome/Razão Social:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.2;
	p1.add(new Rotulo("Tipo:"), cons);
	
	cons.gridwidth  = 1;
	cons.weightx = 0.8;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_nome_razao = new CampoLimitado(250), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.2;
	p1.add(this.tipo = new JComboBox<String>(new String[]{"PF", "PJ"}), cons);
	
	
	
	cons.weightx = 1;
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new Rotulo("CNPJ:"), cons);
	p2.add(new Rotulo("Nome Fantasia:"), cons);
	p2.add(new Rotulo("Insc. Estadual:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(new Rotulo("Insc. Municipal:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p2.add(this.tf_cnpj = new CampoCNPJ(), cons);
	
	p2.add(this.tf_nome_fantasia = new CampoLimitado(200), cons);
	
	p2.add(this.tf_ie = new CampoLimitadoSoDigito(15), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(this.tf_insc_municipal = new CampoLimitadoSoDigito(15), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new Rotulo("CPF:"), cons);
	p2.add(new Rotulo("Sexo:"), cons);
	p2.add(new Rotulo("RG:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(new Rotulo("Nascimento:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p2.add(this.tf_cpf = new CampoCPF(), cons);

	p2.add(this.combo_sexo= new JComboBox<String>(new String[]{"", "FEM", "MASC"}), cons);
	
	p2.add(this.tf_rg = new CampoLimitadoSoDigito(8), cons);

	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(this.tf_nascimento = new CampoData(), cons);
	
	
	

	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new Rotulo("<html>TEL:<font color=red>*</font></html>"), cons);
	p3.add(new Rotulo("TEL (op):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new Rotulo("E-mail:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p3.add(this.tf_tel = new CampoTEL(), cons);
		
	cons.gridwidth  = 1;
	p3.add(this.tf_cel = new CampoTEL(), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(this.tf_email = new CampoLimitado(200), cons);
		
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 40;
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
		

		
		this.tipo.addItemListener(new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent arg0) {
		
	
		setPessoa();	
		}});
		
		
		
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome_razao);
	textFieldList.add(this.tf_nascimento);
	textFieldList.add(this.tf_cnpj);
	textFieldList.add(this.tf_cpf);
	textFieldList.add(tf_cpf);
	textFieldList.add(tf_tel);
	textFieldList.add(tf_cel);
	textFieldList.add(tf_email);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.setPessoa();
	}
	
	
	
	

	
	
	private void setPessoa(){
		
		if(String.valueOf(tipo.getSelectedItem()).compareTo("PF")==0){
			
		tf_cpf.setEnabled(true);
		combo_sexo.setEnabled(true);
		tf_nascimento.setEnabled(true);
		tf_rg.setEnabled(true);
			
			
		tf_cnpj.setEnabled(false);
		tf_nome_fantasia.setEnabled(false);
		tf_ie.setEnabled(false);
		tf_insc_municipal.setEnabled(false);
			
			
		tf_cnpj.setText("");
		tf_nome_fantasia.setText("");
		tf_ie.setText("");
		tf_insc_municipal.setText("");
			
		}	
		else{	
			
		tf_cnpj.setEnabled(true);
		tf_nome_fantasia.setEnabled(true);
		tf_ie.setEnabled(true);
		tf_insc_municipal.setEnabled(true);
			

		tf_cpf.setEnabled(false);
		combo_sexo.setEnabled(false);
		tf_nascimento.setEnabled(false);
		tf_rg.setEnabled(false);
			
			
		tf_cpf.setText("");
		combo_sexo.setSelectedIndex(0);
		tf_nascimento.setText("");
		tf_rg.setText("");
		}		
	}
	
	
	
	
	
	protected boolean validation(){
	

		if(this.tf_nome_razao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome/razão social do cliente.");
		Comuns.textFieldErroMode(this.tf_nome_razao);
		return false;	
		}
	
		if(String.valueOf(tipo.getSelectedItem()).compareTo("PF")==0){
		
			if(this.tf_cpf.getText().length() > 0){
				if(!this.tf_cpf.validacao()){
				
				Mensagens.msgDeErro("CPF é opcional, caso queira informá-lo, informe um número válido.");
				Comuns.textFieldErroMode(this.tf_cpf);
				return false;	
				}
			}
			
			if(this.tf_nascimento.getText().length() > 0){
				if(!this.tf_nascimento.validacao()){
				
				Mensagens.msgDeErro("Nascimento é opcional, caso queira informá-lo, informe uma data válida.");
				Comuns.textFieldErroMode(this.tf_nascimento);
				return false;	
				}
			}	
		}
		else{
			
			if(this.tf_cnpj.getText().length() > 0){
				if(!this.tf_cnpj.validacao()){
				
				Mensagens.msgDeErro("CNPJ é opcional, caso queira informá-lo, informe um número válido.");
				Comuns.textFieldErroMode(this.tf_cnpj);
				return false;	
				}
			}	
		}
		
		
		if(!this.tf_tel.validacao()){
			
		Mensagens.msgDeErro("Informe um telefone válido.");
		Comuns.textFieldErroMode(this.tf_tel);
		return false;	
		}
		
		if(this.tf_cel.getText().length() > 0){
			if(!this.tf_cel.validacao()){
		
			Mensagens.msgDeErro("Celular é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_cel);
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
	
	

	
}
