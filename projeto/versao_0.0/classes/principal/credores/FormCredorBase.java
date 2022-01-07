package principal.credores;


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
import classes.CampoLimitado;
import classes.CampoTEL;
import principal.contas_bancarias.PainelDeContasBancarias;
import componentes.Rotulo;
import componentes.beans.Credor;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormCredorBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoCPF tf_cpf;
protected CampoCNPJ tf_cnpj;

protected JComboBox<?> tipo;

protected CampoTEL tf_tel_1;
protected CampoTEL tf_tel_2;
protected CampoLimitado tf_email;


protected Credor credorRetorno;

protected PainelDeContasBancarias p_contas;





	public FormCredorBase(String title, int width, int height) {
		
	this(title, width, height, null);
	}

	
	
	
	
	public FormCredorBase(String title, int width, int height, Credor credorRetorno) {
		
	super(title, width, height);
	
	this.credorRetorno = credorRetorno;
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
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 0.4;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Nome Completo:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.1;
	p1.add(new Rotulo("Tipo:"), cons);
	cons.weightx = 0.25;
	p1.add(new Rotulo("<html>CPF:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo("<html>CNPJ:<font color=red>*</font></html>"), cons);
	
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.4;
	p1.add(this.tf_nome = new CampoLimitado(200), cons);
		
	cons.weightx = 0.1;
	this.tipo =  new JComboBox<Object>(new String[]{"PF", "PJ"});
	p1.add(this.tipo, cons);
	
	cons.weightx = 0.25;
	p1.add(this.tf_cpf = new CampoCPF(), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(this.tf_cnpj = new CampoCNPJ(), cons);
	
	

	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new Rotulo("E-mail:"), cons);
	p2.add(new Rotulo("<html>TEL:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(new Rotulo("TEL (op):"), cons);

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p2.add(this.tf_email = new CampoLimitado(100), cons);
	

	p2.add(this.tf_tel_1 = new CampoTEL(), cons);
		
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p2.add(this.tf_tel_2 = new CampoTEL(), cons);

	
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;	
	this.add(this.p_contas=new PainelDeContasBancarias(), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar Credor");
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
	
	textFieldList.add(tf_nome);
	textFieldList.add(tf_cpf);
	textFieldList.add(tf_cnpj);
	textFieldList.add(tf_tel_1);
	textFieldList.add(tf_tel_2);
	textFieldList.add(tf_email);
	
	Comuns.addEventoDeFoco(textFieldList);
	
	
	setPessoa();
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
	
	
		if(this.tf_nome.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome do cliente.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}

		
		if(this.tipo.getSelectedIndex() == 0){
			if(!this.tf_cpf.validacao()){
			
			Mensagens.msgDeErro("Informe um CPF válido.");
			Comuns.textFieldErroMode(this.tf_cpf);
			return false;	
			}
		}	
		else{
			
			if(!this.tf_cnpj.validacao()){
				
			Mensagens.msgDeErro("Informe um CNPJ válido.");
			Comuns.textFieldErroMode(this.tf_cnpj);
			return false;	
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
	


	
}
