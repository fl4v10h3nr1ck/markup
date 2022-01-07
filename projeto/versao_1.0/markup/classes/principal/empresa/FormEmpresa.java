package classes.principal.empresa;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.Mensagens;
import classes.CampoCNPJ;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoTEL;
import classes.componentes.endereco.PainelDeEndereco;
import classes.componentes.endereco.beans.Endereco;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Comuns;
import classes.dao.DAO;




public class FormEmpresa extends Dialogo{

	

private static final long serialVersionUID = 1L;

protected CampoLimitado tf_codigo;
protected CampoLimitado tf_razao;
protected CampoLimitado tf_fantasia;
protected CampoLimitado tf_frase;

protected CampoCNPJ tf_cnpj;
protected CampoLimitadoSoDigito tf_cnae;
protected CampoLimitado tf_ie;
protected CampoLimitadoSoDigito tf_im;


protected CampoTEL tf_tel_1;
protected CampoTEL tf_tel_2;
protected CampoLimitado tf_site;
protected CampoLimitado tf_email;

private PainelDeEndereco p_endereco;


protected Empresa empresa;
	






	public FormEmpresa() {
		
	super("Informações da Empresa", 800, 300);
	
	this.empresa = new DAO<Empresa>(Empresa.class).getPrimeiroOuNada(null, null, null);
	
	this.adicionarComponentes();
	
		if(this.empresa != null ){
			
		tf_codigo.setText(Comuns.addPaddingAEsquerda(""+this.empresa.getId_empresa(), 8, "0"));
		tf_razao.setText(empresa.getRazao_social());
		tf_fantasia.setText(empresa.getNome_fantasia());
		tf_frase.setText(empresa.getFrase_lema());
		
		tf_cnpj.setText(empresa.getCnpj());
		tf_cnae.setText(empresa.getCnae());
		tf_ie.setText(empresa.getInscricao_estadual());
		tf_im.setText(empresa.getInscricao_municipal());
		
		tf_tel_1.setText(empresa.getTel_1());
		tf_tel_2.setText(empresa.getTel_2());
		tf_site.setText(empresa.getSite());
		tf_email.setText(empresa.getEmail());

		if(this.empresa.getFk_endereco()>0)
		p_endereco.setValores(new DAO<Endereco>(Endereco.class).get(this.empresa.getFk_endereco()));	
		}
	}
	

	
	
	
	
	public void adicionarComponentes(){
			
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
		
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	this.add(p1, cons);
	
	this.add(this.p_endereco = new PainelDeEndereco(), cons);
	this.p_endereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Código:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html>Razão Social:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html>Nome Fantasia:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Frase/Lema:"), cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_codigo = new CampoLimitado(25), cons);
	this.tf_codigo.setEditable(false);
	p1.add(this.tf_razao = new CampoLimitado(150), cons);
	p1.add(this.tf_fantasia = new CampoLimitado(150), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(this.tf_frase = new CampoLimitado(200), cons);
	
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>CNPJ:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("CNAE:"), cons);
	p1.add(new JLabel("<html>Ins. Estadual:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Ins. Municipal:"), cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_cnpj = new CampoCNPJ(), cons);
	p1.add(this.tf_cnae = new CampoLimitadoSoDigito(15), cons);
	p1.add(this.tf_ie = new CampoLimitado(15), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(this.tf_im = new CampoLimitadoSoDigito(15), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("Tel:"), cons);
	p1.add(new JLabel("CEL (Op):"), cons);
	p1.add(new JLabel("Site:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("E-mail:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_tel_1 = new CampoTEL(), cons);	
	p1.add(this.tf_tel_2 = new CampoTEL(), cons);
	p1.add(this.tf_site = new CampoLimitado(150), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(this.tf_email = new CampoLimitado(150), cons);	

	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.ipadx = 15;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_salvar.setToolTipText("Salvar dados da empresa");
	this.add(bt_salvar, cons);
		
	
	
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		

		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(this.tf_razao);
	textFieldList.add(this.tf_fantasia);
	textFieldList.add(this.tf_cnpj);
	textFieldList.add(this.tf_ie);
	textFieldList.add(tf_tel_1);
	textFieldList.add(tf_tel_2);
	textFieldList.add(tf_email);
	
	Comuns.addEventoDeFoco(textFieldList);
	}
	
	
	
	
	

	protected boolean validacao(){
	
		
		if(this.tf_razao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe a razão social da empresa.");
		Comuns.textFieldErroMode(this.tf_razao);
		return false;	
		}
		
		if(this.tf_fantasia.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o nome fantasia da empresa.");
		Comuns.textFieldErroMode(this.tf_fantasia);
		return false;	
		}
		
		
		if(!this.tf_cnpj.validacao()){
			
		Mensagens.msgDeErro("Informe um CNPJ válido.");
		Comuns.textFieldErroMode(this.tf_cnpj);
		return false;	
		}
		
		
		if(this.tf_ie.getText().length()==0 || 
				(this.tf_ie.getText().compareTo("ISENTO")!=0 &&
						this.tf_ie.getText().replaceAll("\\D", "").length()!=this.tf_ie.getText().length())){
			
		Mensagens.msgDeErro("Informe um número de inscrição estadual válido (informe ISENTO se não houver).");
		Comuns.textFieldErroMode(this.tf_ie);
		return false;	
		}
		
		
		
		if(this.tf_tel_1.getText().length() > 0){
			if(!this.tf_tel_1.validacao()){
				
			Mensagens.msgDeErro("Informe um telefone 1 válido.");
			Comuns.textFieldErroMode(this.tf_tel_1);
			return false;	
			}
		}
		
		
		if(this.tf_tel_2.getText().length() > 0){
			if(!this.tf_tel_2.validacao()){
		
			Mensagens.msgDeErro("Telefone 2 é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_tel_2);
			return false;
			}
		}
		
		
		if(this.tf_email.getText().length() > 0){
			if(!this.tf_email.validacao()){
		
			Mensagens.msgDeErro("Informe um endereço de e-mail válido.");
			Comuns.textFieldErroMode(this.tf_email);
			return false;
			}
		}
						
			
	return this.p_endereco.validacao();	
	}
	
	
	
	
	
	
	
	public  boolean acaoPrincipal(){
		
	if(!this.validacao())	
	return false;
	
	Empresa aux = new Empresa();
	
	aux.setRazao_social(this.tf_razao.getText());
	aux.setNome_fantasia(this.tf_fantasia.getText());
	aux.setFrase_lema(this.tf_frase.getText());
	
	aux.setCnpj(this.tf_cnpj.getText());
	aux.setCnae(this.tf_cnae.getText());
	aux.setInscricao_estadual(this.tf_ie.getText());
	aux.setInscricao_municipal(this.tf_im.getText());
	
	
	aux.setTel_1(this.tf_tel_1.getText());
	aux.setTel_2(this.tf_tel_2.getText());
	aux.setSite(this.tf_site.getText());
	aux.setEmail(this.tf_email.getText());
	
	
	boolean retorno = false;
	
	DAO<Empresa> dao =  new DAO<Empresa>(Empresa.class);
	DAO<Endereco> dao_endereco =  new DAO<Endereco>(Endereco.class);
	
		if(this.empresa == null){
		
		int id_endereco  = 	dao_endereco.novo(this.p_endereco.getValores());
		
		aux.setFk_endereco(id_endereco);
		
		retorno = dao.novo(aux)>0?true:false;
		}
		else{
		
		aux.setFk_endereco(this.empresa.getFk_endereco());	
			
		Endereco endereco  = this.p_endereco.getValores();
		
		endereco.setId_endereco(aux.getFk_endereco());
		
		dao_endereco.altera(endereco);
		
		aux.setId_empresa(this.empresa.getId_empresa());
		
		retorno = dao.altera(aux);
		}
	
	if(retorno)
	Comuns.setInfosDeEmpresa();
	
	return retorno;
	}
	
	
	
	
	

	
	
	
}
