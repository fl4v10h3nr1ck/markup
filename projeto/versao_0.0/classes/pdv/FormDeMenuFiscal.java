package pdv;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bemajava.Bematech;
import classes.CampoData;
import classes.CampoLimitadoSoDigito;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import comuns.Preferencias;



public class FormDeMenuFiscal  extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoData tf_periodo_1;
private CampoData tf_periodo_2;

private CampoLimitadoSoDigito tf_reducao_1;
private CampoLimitadoSoDigito tf_reducao_2;



	public FormDeMenuFiscal() {
	
	super("Menu Fiscal", 700, 240);
		
	this.adicionarComponentes();
	}

	
	
	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();   
		
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = 1;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2,2,2,2);
	JPanel	p2 = new JPanel(new GridBagLayout());	
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	JPanel	p1 = new JPanel(new GridBagLayout());	
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JPanel p_periodo = new JPanel(new GridBagLayout());
	p_periodo.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p2.add(p_periodo, cons);
	
	cons.insets = new Insets(2,2, 0, 2);
	p_periodo.add(new JLabel("Período:"), cons);
	
	cons.gridwidth = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_periodo_1 = new CampoData();
	p_periodo.add(this.tf_periodo_1, cons);
		
	cons.fill = GridBagConstraints.NONE;
	cons.insets = new Insets(2, 5, 2, 5);
	cons.weighty  = 0;
	cons.weightx  = 0;
	p_periodo.add(new JLabel("à"), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_periodo_2 = new CampoData();
	p_periodo.add(this.tf_periodo_2, cons);
	this.tf_periodo_2.setText(Data.getDataAtual());
	
	
	
	p_periodo.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JPanel p_reducao = new JPanel(new GridBagLayout());
	p_reducao.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	p2.add(p_reducao, cons);
	
	cons.insets = new Insets(2,2, 0, 2);
	p_reducao.add(new JLabel("Redução:"), cons);
	
	cons.gridwidth = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_reducao_1 = new CampoLimitadoSoDigito(4);
	p_reducao.add(this.tf_reducao_1, cons);
		
	cons.fill = GridBagConstraints.NONE;
	cons.insets = new Insets(2, 5, 2, 5);
	cons.weighty  = 0;
	cons.weightx  = 0;
	p_reducao.add(new JLabel("à"), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_reducao_2 = new CampoLimitadoSoDigito(4);
	p_reducao.add(this.tf_reducao_2, cons);
	
	p_reducao.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	p2.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JButton bt_aliquota_icms  = new JButton("Programar Alíquota ICMS");
	bt_aliquota_icms.setToolTipText("Configura o valor de ICMS na impressora fiscal.");
	p2.add(bt_aliquota_icms, cons);
	
	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JButton bt_leitura_x  = new JButton("Leitura X");
	bt_leitura_x.setToolTipText("Gerar leitura X");
	p1.add(bt_leitura_x, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	JButton bt_leitura_x_serial  = new JButton("Leitura X Serial");
	bt_leitura_x_serial.setToolTipText("Gerar leitura X para arquivo texto");
	p1.add(bt_leitura_x_serial, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JButton bt_reducao_Z  = new JButton("Redução Z");
	bt_reducao_Z.setToolTipText("Gerar redução Z");
	p1.add(bt_reducao_Z, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	
	cons.insets = new Insets(5,2,2,2);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("Memória Fiscal:"), cons);
	cons.insets = new Insets(0,0,7,0);
	p1.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JButton bt_leitura_men_data  = new JButton("Por Data");
	bt_leitura_men_data.setToolTipText("Gerar leitura de memória fiscal por período");
	p1.add(bt_leitura_men_data, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	JButton bt_leitura_men_reducao  = new JButton("Por Redução");
	bt_leitura_men_reducao.setToolTipText("Gerar leitura de memória fiscal por intervalo de reduções");
	p1.add(bt_leitura_men_reducao, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,2,2);
	JButton bt_leitura_men_data_serial  = new JButton("Por Data via Serial");
	bt_leitura_men_data_serial.setToolTipText("Gerar leitura de memória fiscal por período");
	p1.add(bt_leitura_men_data_serial, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	JButton bt_leitura_men_reducao_serial  = new JButton("Por Redução via Serial");
	bt_leitura_men_reducao_serial.setToolTipText("Gerar leitura de memória fiscal por intervalo de reduções");
	p1.add(bt_leitura_men_reducao_serial, cons);
	
	
		bt_aliquota_icms.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
			if(Calculo.stringZero(Comuns.aliquota_ICMS)){
			
			Mensagens.msgDeErro("Valor de ICMS não definido.");
			return;	
			}
			
			
			if(Bematech.ProgramaAliquota(Comuns.aliquota_ICMS, 0)!=1){		 

			Mensagens.msgDeErro("Um erro ocorreu ao configurar o valor de ICMS.");
			return;	
			}
			
			
		Mensagens.msgDeSucessoAoSalvar();	
		}});
		
	
	
	
	
		bt_leitura_x.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		geraLeituraX();
		}});
		
		
		bt_leitura_x_serial.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		geraLeituraXSerial();
		}});
		
		
		bt_reducao_Z.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		geraReducaoZ();
		}});
		
		
	
		bt_leitura_men_data.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					
		geraMemoriaFiscalPorData();
		}});
		
		
		
		bt_leitura_men_reducao.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						
		geraMemoriaFiscalPorReducao();
		}});
		
		
		bt_leitura_men_data_serial.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		geraMemoriaFiscalPorDataSerial();
		}});
		
		
		
		bt_leitura_men_reducao_serial.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
								
		geraMemoriaFiscalPorReducaoSerial();
		}});
	
	
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
		
	textFieldList.add(this.tf_periodo_1);
	textFieldList.add(this.tf_periodo_2);
	textFieldList.add(this.tf_reducao_1);
	textFieldList.add(this.tf_reducao_2);

	Comuns.addEventoDeFoco(textFieldList);
	}

	
	
	
	private void geraLeituraX(){
		
	Bematech.LeituraX();	
	}
	
	
	
	
	private void geraLeituraXSerial(){
		
	Bematech.LeituraXSerial();
	}
	
	
	
	
	private void geraReducaoZ(){
		
	Bematech.ReducaoZ("", "");	
	}
	
	

	
	private void geraMemoriaFiscalPorData(){
		
	if(this.validaPeriodo())	
	Bematech.LeituraMemoriaFiscalData(this.tf_periodo_1.getText(), this.tf_periodo_2.getText());
	}
	
	
	
	
	private void geraMemoriaFiscalPorReducao(){
		
	if(this.validaReducao())	
	Bematech.LeituraMemoriaFiscalReducao(this.tf_reducao_1.getText(), this.tf_reducao_2.getText());		
	}
	
		
	
	
	private void geraMemoriaFiscalPorDataSerial(){
		
	if(this.validaPeriodo())	
	Bematech.LeituraMemoriaFiscalSerialData(this.tf_periodo_1.getText(), this.tf_periodo_2.getText());		
	}
	
	

	
	private void geraMemoriaFiscalPorReducaoSerial(){
		
	if(this.validaReducao())	
	Bematech.LeituraMemoriaFiscalSerialReducao(this.tf_reducao_1.getText(), this.tf_reducao_2.getText());		
	}
	
	
	

	private boolean validaPeriodo(){
		
		if(!this.tf_periodo_1.validacao()){
			
		Mensagens.msgDeErro("A data inicial é inválida.");
		Comuns.textFieldErroMode(this.tf_periodo_1);
		return false;	
		}
			

		if(!this.tf_periodo_2.validacao()){
					
		Mensagens.msgDeErro("A data final é inválida.");
		Comuns.textFieldErroMode(this.tf_periodo_2);
		return false;	
		}	
	
			
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");  
	
		try {	
			if(!df.parse(this.tf_periodo_1.getText()).before(df.parse(this.tf_periodo_2.getText()))){
			
			Mensagens.msgDeErro("O período informado é inválido.");
			Comuns.textFieldErroMode(this.tf_periodo_1);
			return false;		
			}
		} 
		catch (ParseException e) {
		
		Mensagens.msgDeErro("O período informado é inválido.");
		Comuns.textFieldErroMode(this.tf_periodo_1);
		return false;			
		}

	return true;
	}

	
	
	
	
	
	private boolean validaReducao(){
		
		if(this.tf_reducao_1.getText().length()==0  ||
			this.tf_reducao_2.getText().length()==0  || 
			Integer.parseInt(this.tf_reducao_1.getText()) >=  Integer.parseInt(this.tf_reducao_2.getText())){
			
		Mensagens.msgDeErro("O intervalo de redução é inválido.");
		Comuns.textFieldErroMode(this.tf_reducao_1);
		return false;		
		}
		
	return true;
	}





	@Override
	public boolean acaoPrincipal() {return false;}
	
	
}
