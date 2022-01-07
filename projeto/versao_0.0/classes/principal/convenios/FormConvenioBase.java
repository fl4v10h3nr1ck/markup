package principal.convenios;


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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO;
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoMoeda;
import componentes.beans.Convenio;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormConvenioBase extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_codigo;
protected CampoLimitado tf_descricao;
protected CampoMoeda tf_valor_desconto;
protected JComboBox<String> tipo_desconto;

protected CampoData tf_data_inicio;
protected CampoData tf_data_fim;


protected Convenio convenio;





	public FormConvenioBase(String title, int width, int height, Convenio convenio) {
		
	super(title, width, height);
	
	this.convenio = convenio;
	}

	
	
	

		
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
		
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 0.2;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Código:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.6;
	p1.add(new JLabel("<html>Descrição:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	cons.weightx = 0.2;
	p1.add(new JLabel("Tipo Desconto:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.2;
	this.tf_codigo = new CampoLimitado(200);
	p1.add(this.tf_codigo, cons);
	
	cons.weightx = 0.6;
	this.tf_descricao = new CampoLimitado(200);
	p1.add(this.tf_descricao, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 0.2;
	this.tipo_desconto = new JComboBox<String>(new String[]{"VALOR", "PORCENTAGEM", "TOTAL"});
	p1.add(this.tipo_desconto, cons);
		
	
	cons.gridwidth  = 2;	
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new JLabel("Valor Desconto:"), cons);
	p2.add(new JLabel("Vigência (Início):"), cons);
	cons.gridwidth  = 1;	
	p2.add(new JLabel("Vigência (Fim):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new JLabel(""), cons);
		

	cons.gridwidth  = 2;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_valor_desconto = new CampoMoeda(200);
	p2.add(this.tf_valor_desconto, cons);
	
	this.tf_data_inicio = new CampoData();
	p2.add(this.tf_data_inicio, cons);
		
	cons.gridwidth  = 1;	
	this.tf_data_fim = new CampoData();
	p2.add(this.tf_data_fim, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new JLabel(""), cons);

	cons.fill = GridBagConstraints.NONE;	
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar convênio");
	this.add(bt_save, cons);
		
		

	tipo_desconto.addItemListener( new ItemListener(){
	@Override
	public void itemStateChanged(ItemEvent arg0) {
	
		if(tipo_desconto.getSelectedItem().toString().compareTo("TOTAL") == 0){
			
		tf_valor_desconto.setEnabled(false);	
		tf_valor_desconto.setText("100,00");
		}
		else{
			
		tf_valor_desconto.setEnabled(true);	
		tf_valor_desconto.setText("0,00");	
		}
		
	}});
		
	
	
	
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		
	
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_descricao);
	textFieldList.add(tf_data_inicio);
	textFieldList.add(tf_data_fim);
	textFieldList.add(this.tf_valor_desconto);
	textFieldList.add(this.tf_codigo);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	

	
	protected boolean validation(){
	
		
		if(this.tf_codigo.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe um código para o convênio.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
			
			
		if(!Comuns.codigoPermitido(new DAO<Convenio>(Convenio.class), this.tf_codigo.getText(), this.convenio!=null?this.convenio.getId_convenio():0)){
				
		Mensagens.msgDeErro("O código informado já está sendo usado por outro convênio.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
			
	
		if(this.tf_descricao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome do convênio/promoção.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;	
		}
		
		if(this.tf_valor_desconto.getText().length()==0){
		
		Mensagens.msgDeErro("Informe um valor de desconto.");
		Comuns.textFieldErroMode(this.tf_valor_desconto);
		return false;	
		}
	
		
		if(this.tipo_desconto.getSelectedItem().toString().compareTo("TOTAL")==0){
			if(Calculo.stringParaDouble(this.tf_valor_desconto.getText())>100){
				
			Mensagens.msgDeErro("A porcentagem máxima de desconto é 100.");
			Comuns.textFieldErroMode(this.tf_valor_desconto);
			return false;	
			}
		}

		
		
		if(this.tf_data_inicio.getText().length() > 0){
			if(!this.tf_data_inicio.validacao()){
			
			Mensagens.msgDeErro("Data inicial é opcional, caso queira informá-lo, informe uma data válida.");
			Comuns.textFieldErroMode(this.tf_data_inicio);
			return false;	
			}
		}	
		
	
		if(this.tf_data_fim.getText().length() > 0){
			if(!this.tf_data_fim.validacao()){
			
			Mensagens.msgDeErro("Data final é opcional, caso queira informá-lo, informe uma data válida.");
			Comuns.textFieldErroMode(this.tf_data_fim);
			return false;	
			}
		}
		
		
		if(this.tf_data_fim.getText().length() > 0 && this.tf_data_inicio.getText().length() > 0){
		
			if(Comuns.converteStringParaData(this.tf_data_fim.getText()).before(Comuns.converteStringParaData(this.tf_data_inicio.getText()))){  
				  
			Mensagens.msgDeErro("A promoção não pode terminar antes de começar.");
			Comuns.textFieldErroMode(this.tf_data_fim);
			return false;
			} 
		}

	return true;	
	}
	
	
	

}
