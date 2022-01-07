package classes.estoque.cadastro.balanca;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import classes.CampoLimitadoSoDigito;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Comuns;
import classes.comuns.Mensagens;
import classes.dao.DAO;




public class FormBalanca extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected JComboBox<String> tipo_cod_barra;
protected JComboBox<String> tipo_valor_peso;
protected JComboBox<String> tipo_cod_prod;

protected CampoLimitadoSoDigito tf_prod_inicio;
protected CampoLimitadoSoDigito tf_prod_fim;
protected CampoLimitadoSoDigito tf_valor_inicio;
protected CampoLimitadoSoDigito tf_valor_fim;


protected Balanca balanca;
	






	public FormBalanca() {
		
	super("Informações da Balança", 700, 280);
	
	
	this.adicionarComponentes();
	
	this.balanca =  new DAO<Balanca>(Balanca.class).getPrimeiroOuNada(null, null, null);
	
		if(this.balanca != null ){
		
		this.tipo_cod_barra.setSelectedItem(this.balanca.getTipo_codigo_barra());
		this.tipo_valor_peso.setSelectedItem(this.balanca.getTipo_valor_peso());
		this.tipo_cod_prod.setSelectedItem(this.balanca.getTipo_codigo_produto());
		this.tf_prod_inicio.setText(this.balanca.getIndice_inicial_produto());
		this.tf_prod_fim.setText(this.balanca.getIndice_final_produto());
		this.tf_valor_inicio.setText(this.balanca.getIndice_inicial_valor());
		this.tf_valor_fim.setText(this.balanca.getIndice_final_valor());	
		}
	}
	

	
	
	
	
	public void adicionarComponentes(){
			
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 5, 2);
		
	JPanel p1 = new JPanel(new GridBagLayout());
	this.add(p1, cons);
	
	cons.gridwidth  = 1;	
	cons.weightx = 0.3;
	JPanel p2 = new JPanel(new GridBagLayout());
	this.add(p2, cons);

	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.7;
	JPanel p3 = new JPanel(new GridBagLayout());
	this.add(p3, cons);

	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Tipo de Código:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html>Tipo de Dados:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("<html>Tipo de ID de Produto:<font color=red>*</font></html>"), cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tipo_cod_barra = new JComboBox<String>(new String[]{"EAN13"}), cons);
	p1.add(this.tipo_valor_peso = new JComboBox<String>(new String[]{"PRECO", "PESO"}), cons);
	p1.add(this.tipo_cod_prod = new JComboBox<String>(new String[]{"ID SISTEMA", "EAN", "COD. BALANCA"}), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx  = 0.8;	
	p2.add(new JLabel("<html>Código do produto começa no dígito:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.2;
	p2.add(this.tf_prod_inicio = new CampoLimitadoSoDigito(2), cons);	
	
	cons.gridwidth  = 1;
	cons.weightx  = 0.8;
	p2.add(new JLabel("<html>Código do produto termina no dígito:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.2;
	p2.add(this.tf_prod_fim = new CampoLimitadoSoDigito(2), cons);	
	
	cons.gridwidth  = 1;
	cons.weightx  = 0.8;
	p2.add(new JLabel("<html>Preço/peso começa no dígito:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.2;
	p2.add(this.tf_valor_inicio = new CampoLimitadoSoDigito(2), cons);	
	
	cons.gridwidth  = 1;
	cons.weightx  = 0.8;
	p2.add(new JLabel("<html>Preço/peso termina no dígito:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.2;
	p2.add(this.tf_valor_fim = new CampoLimitadoSoDigito(2), cons);	
	

	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.ipadx = 15;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_salvar.setToolTipText("Salvar dados da balança.");
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
	
	textFieldList.add(tf_prod_inicio);
	textFieldList.add(tf_prod_fim);
	textFieldList.add(tf_valor_inicio);
	textFieldList.add(tf_valor_fim);
	
	Comuns.addEventoDeFoco(textFieldList);
	}
	
	
	
	

	protected boolean validacao(){
	

		if(this.tf_prod_inicio.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o dígito que começa o código do produto.");
		Comuns.textFieldErroMode(this.tf_prod_inicio);
		return false;	
		}

		
		if(this.tf_prod_fim.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o dígito que termina o código do produto.");
		Comuns.textFieldErroMode(this.tf_prod_fim);
		return false;	
		}
		
		
		if(Integer.parseInt(this.tf_prod_inicio.getText())>= Integer.parseInt(this.tf_prod_fim.getText())){
			
		Mensagens.msgDeErro("O dígito que começa o código do produto não pode ser maior ou iqual ao dígito que termina.");
		Comuns.textFieldErroMode(this.tf_prod_inicio);
		Comuns.textFieldErroMode(this.tf_prod_fim);
		return false;			
		}
		
		
		
		
		
		if(this.tf_valor_inicio.getText().length() == 0){
			
			Mensagens.msgDeErro("Informe o dígito que começa o preço/peso.");
			Comuns.textFieldErroMode(this.tf_valor_inicio);
			return false;	
			}
		
		
		if(this.tf_valor_fim.getText().length() == 0){
			
			Mensagens.msgDeErro("Informe o dígito que termina o preço/peso.");
			Comuns.textFieldErroMode(this.tf_valor_fim);
			return false;	
			}
		
		
		if(Integer.parseInt(this.tf_valor_inicio.getText())>= Integer.parseInt(this.tf_valor_fim.getText())){
			
		Mensagens.msgDeErro("O dígito que começa o preço/peso não pode ser maior ou iqual ao dígito que termina.");
		Comuns.textFieldErroMode(this.tf_valor_inicio);
		Comuns.textFieldErroMode(this.tf_valor_fim);
		return false;			
		}
		
	return true;
	}
	
	
	
	
	
	

	public  boolean acaoPrincipal(){
		
	if(!this.validacao())	
	return false;
	
	
	Balanca aux = new Balanca();
	aux.setTipo_codigo_barra(this.tipo_cod_barra.getSelectedItem().toString());
	aux.setTipo_codigo_produto(this.tipo_cod_prod.getSelectedItem().toString());
	aux.setTipo_valor_peso(this.tipo_valor_peso.getSelectedItem().toString());
	
	aux.setIndice_inicial_produto(Integer.parseInt(this.tf_prod_inicio.getText()));
	aux.setIndice_final_produto(Integer.parseInt(this.tf_prod_fim.getText()));
	aux.setIndice_inicial_valor(Integer.parseInt(this.tf_valor_inicio.getText()));
	aux.setIndice_final_valor(Integer.parseInt(this.tf_valor_fim.getText()));
	
	boolean retorno = false;
	
	DAO<Balanca> dao =  new DAO<Balanca>(Balanca.class);
	
	if(this.balanca == null)
	retorno = dao.novo(aux)>0?true:false;
	else
	retorno = dao.altera(aux);
	
	return retorno;
	}
	
	
	
	
	

}
