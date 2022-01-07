package principal.entrada_saida;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;
import principal.credores.FormNovoCredor;
import principal.fornecedores.FormNovoFornecedor;
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Credor;
import componentes.beans.Entrada_Saida;
import componentes.beans.Fornecedor;
import componentes.beans.Parcela;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import comuns.Preferencias;




public abstract class FormEntrada_SaidaBase extends Dialogo{

	

private static final long serialVersionUID = 1L;

protected CampoLimitado tf_referente;

protected CampoLimitado tf_fornecedor;
protected JButton bt_novo_fornecedor;
protected int idFornecedor;


protected CampoLimitado tf_credor;
protected JButton bt_novo_credor;
protected int idCredor;

protected JComboBox<Object> cb_tipo;

protected CampoMoeda tf_val_total;
protected CampoMoeda tf_multa;
protected CampoMoeda tf_juros;

protected CampoMoeda tf_entrada;
protected CampoLimitadoSoDigito tf_num_parcelas;
protected CampoData tf_venci;
protected CampoMoeda tf_val_parcela;

protected JButton bt_save;
protected JButton bt_calc_parcela;
protected JButton bt_fechar_parcela;

protected Entrada_Saida retorno;
	
protected JLabel rot_data;


protected JTable parcelas_geradas;		
private DefaultTableModel modelo;	


protected int tipo;


protected List<Parcela> parcelas;


protected Rotulo lb_info;

	


	
	public FormEntrada_SaidaBase(String title, int width, int height, Entrada_Saida retorno, int tipo) {
		
	super(title, width, height);
	
	this.retorno = retorno;
	
	this.tipo = tipo;
	
	this.parcelas= new ArrayList<Parcela>();
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
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	JPanel p4 = new JPanel(new GridBagLayout());
	p4.setBackground(Color.white);
	this.add(p4, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("<html>Referente:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = 2;	
	p1.add(new Rotulo("<html>Fornecedor:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	p1.add(new Rotulo("Credor:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_referente = new CampoLimitado(200);
	p1.add(this.tf_referente, cons);
		
	this.tf_fornecedor = new CampoLimitado(200);
	p1.add(this.tf_fornecedor, cons);
	this.tf_fornecedor.setEditable(false);
	
	cons.anchor = GridBagConstraints.WEST;
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_novo_fornecedor  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_novo_fornecedor.setToolTipText("Adicionar Fornecedor");
	p1.add(bt_novo_fornecedor, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	this.tf_credor = new CampoLimitado(200);
	p1.add(this.tf_credor, cons);
	this.tf_credor.setEditable(false);

	
	cons.anchor = GridBagConstraints.WEST;
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.bt_novo_credor  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_novo_credor.setToolTipText("Adicionar novo credor");
	p1.add(bt_novo_credor, cons);
	
	
	cons.gridwidth  = 1;		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p2.add(new Rotulo("<html>Tipo de Pagamento:<font color=red>*</font></html>"), cons);
	p2.add(new Rotulo("<html>Valor Total (R$):<font color=red>*</font></html>"), cons);
	p2.add(new Rotulo("Multa (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	p2.add(new Rotulo("Juros (%) a.m.:"), cons);


	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.cb_tipo = new JComboBox<Object>(new String[]{"UNICO", "PARCELADO"});
	p2.add(this.cb_tipo, cons);
	
	this.tf_val_total = new CampoMoeda(6);
	p2.add(this.tf_val_total, cons);
	
	cons.gridwidth  = 1;
	this.tf_multa = new CampoMoeda(4);
	p2.add(this.tf_multa, cons);
	this.tf_multa.setText(Comuns.porcentMultaPadrao);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_juros = new CampoMoeda(4);
	p2.add(this.tf_juros, cons);
	this.tf_juros.setText(Comuns.porcentjurosPadraoAoMes);
	

	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(this.rot_data = new Rotulo("<html>Receber em:<font color=red>*</font></html>"), cons);
	p3.add(new Rotulo("Entrada (R$):"), cons);
	p3.add(new Rotulo("<html>Num. Parcelas:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new Rotulo("Valor Pacerla (R$):"), cons);
	

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_venci = new CampoData(Data.converteDataParaString(new Date()));
	p3.add(this.tf_venci, cons);
	
	this.tf_entrada = new CampoMoeda(6);
	this.tf_entrada.setEnabled(false);
	p3.add(this.tf_entrada, cons);
		
	this.tf_num_parcelas = new CampoLimitadoSoDigito(2);
	this.tf_num_parcelas.setEnabled(false);
	p3.add(this.tf_num_parcelas, cons);
	
	this.tf_val_parcela = new CampoMoeda(6);
	this.tf_val_parcela.setEditable(false);
	p3.add(this.tf_val_parcela, cons);
	tf_val_parcela.setEnabled(false);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	this.bt_calc_parcela  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_confirma_mini.png")));
	bt_calc_parcela.setToolTipText("Calcular valor de parcela");
	p3.add(bt_calc_parcela, cons);
	bt_calc_parcela.setEnabled(false);

	
	this.modelo = new DefaultTableModel(null, new String[]{"Índice", "Valor", "Vencimento", "Status"}){
		private static final long serialVersionUID = 1L;		
		public boolean isCellEditable(int row, int col ){  		            
		return false;
	   }}; 
	
	
	this.parcelas_geradas = new JTable(this.modelo);
	this.parcelas_geradas.setRowHeight(20); 
	
	this.parcelas_geradas.getColumnModel().getColumn(0).setPreferredWidth(120);
	this.parcelas_geradas.getColumnModel().getColumn(1).setPreferredWidth(200);
	this.parcelas_geradas.getColumnModel().getColumn(2).setPreferredWidth(200);	
	this.parcelas_geradas.getColumnModel().getColumn(2).setPreferredWidth(200);	
	

	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	this.parcelas_geradas.setBackground(Color.white); 
	p4.add(new JScrollPane(this.parcelas_geradas, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
			


	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.weighty  = 0;
	cons.ipadx = 25;
	cons.insets = new Insets(2, 2, 2, 2);
	this.bt_fechar_parcela  = new JButton("Fechar "+(this.tipo==0?"Pagamento":"Recebimento")+"/Parcela", new ImageIcon(getClass().getResource("/icons/up.png")));
	bt_fechar_parcela.setToolTipText("Fechar parcela selecionada");
	this.add(bt_fechar_parcela, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.ipadx = 0;
	this.add(new Rotulo(""), cons);
	

	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.ipadx = 25;
	this.bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar Recebimento");
	this.add(bt_save, cons);
		
	
	
		this.cb_tipo.addItemListener(new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent arg0) {
		
			
			if(cb_tipo.getSelectedIndex() == 0){
				
			tf_entrada.setText("");
			tf_num_parcelas.setText("");
			tf_val_parcela.setText("");
			
			
			tf_entrada.setEnabled(false);
			tf_num_parcelas.setEnabled(false);
			tf_val_parcela.setEnabled(false);
			bt_calc_parcela.setEnabled(false);
			
			rot_data.setText("<html>Receber em:<font color=red>*</font></html>");
			}
			else{
				
			tf_entrada.setText("");
			tf_num_parcelas.setText("");
			tf_val_parcela.setText("");
			
			tf_entrada.setEnabled(true);
			tf_num_parcelas.setEnabled(true);
			tf_val_parcela.setEnabled(true);
			bt_calc_parcela.setEnabled(true);
			
			rot_data.setText("<html>Primeiro Vencimento:<font color=red>*</font></html>");
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
		

		
		
		bt_calc_parcela.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					    	
		calculaParcela();
		}});
		



		this.bt_novo_fornecedor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		addNovoFornecedor();
		}});
			
		
		
		
		
		this.tf_fornecedor.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
							
		Comuns.removeIndicadoresDeErro(tf_fornecedor);	
		bt_novo_fornecedor.requestFocusInWindow();
		addFornecedor();
		}});
			
			
		
		
		
		this.tf_credor.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
							
		Comuns.removeIndicadoresDeErro(tf_credor);	
		bt_novo_credor.requestFocusInWindow();
		addCredor();
		}});
			
			

		
		
		this.bt_novo_credor.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					
		addNovoCredor();
		}});
				
	
		
		
		

		this.bt_fechar_parcela.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					
		fechar();
		}});
				
	
		
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	
	textFieldList.add(tf_referente);
	textFieldList.add(tf_entrada);
	textFieldList.add(tf_num_parcelas);
	textFieldList.add(this.tf_venci);
	textFieldList.add(tf_val_total);
	textFieldList.add(this.tf_credor);
	textFieldList.add(this.tf_fornecedor);
		
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	

	
	protected boolean validation(){
	
		if(this.tf_referente.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe a referência deste recebimento.");
		Comuns.textFieldErroMode(this.tf_referente);
		return false;	
		}
		
		if(this.tf_val_total.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o valor do recebimento.");
		Comuns.textFieldErroMode(this.tf_val_total);
		return false;	
		}
		
		
		if(!this.tf_venci.validacao()){
				
		Mensagens.msgDeErro("Os valores informados para o "+
						(this.cb_tipo.getSelectedIndex() == 1?"primeiro vencimento":"recebimento")+" formam uma data inválida.");
		Comuns.textFieldErroMode(this.tf_venci);
		return false;			
		}
				
		
		if(this.idCredor == 0 && this.idFornecedor == 0){
			
		Mensagens.msgDeErro("Escolha um credor ou fornecedor para receber este pagamento.");
		Comuns.textFieldErroMode(this.tf_credor);
		return false;		
		}
			
	return true;	
	}
	
	
		


	
	protected boolean validaValores(){
		
		
	String aux = Calculo.subtrai(this.tf_val_total.getText(), 	this.tf_entrada.getText());

		if(aux.charAt(0) == '-'){
		
		Mensagens.msgDeErro("O valor de entrada não deve ser maior que o valor total.");
		Comuns.textFieldErroMode(this.tf_entrada);
		return false;	
		}
	
		if(this.tf_num_parcelas.getText().length() == 0 || Integer.parseInt(this.tf_num_parcelas.getText()) == 0){
			
		Mensagens.msgDeErro("Informe o número de parcelas deste recebimento.");
		Comuns.textFieldErroMode(this.tf_num_parcelas);
		return false;	
		}
		
			
	return true;
	}
	
	
	
	
	
	
	protected boolean calculaParcela(){
	
	this.parcelas.clear();	
		
		
		if(this.cb_tipo.getSelectedIndex() == 1){
			
		if(!this.validaValores())
		return false;	

		if(this.tf_entrada.getText().length() == 0)
		this.tf_entrada.setText("0,00");
		
		this.tf_val_parcela.setText(Calculo.formataValor(
				Calculo.dividi(Calculo.subtrai(this.tf_val_total.getText(), this.tf_entrada.getText()),
						this.tf_num_parcelas.getText())));
		
		
		String valor_parcela = this.tf_val_parcela.getText();
		

		GregorianCalendar calendar = new GregorianCalendar();
		try {calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(tf_venci.getText()));} 
		catch (ParseException e1) {e1.printStackTrace();}
		
			for(int i = 0, 
					mes = calendar.get(Calendar.MONTH)+1, 
						ano = calendar.get(Calendar.YEAR); i < Integer.parseInt(this.tf_num_parcelas.getText()); i++){
				
			Parcela parcela = new	Parcela();
			
			try {parcela.setData_vencimento(new SimpleDateFormat("dd/MM/yyyy").parse(
					String.format("%02d/%02d/%04d", calendar.get(Calendar.DAY_OF_MONTH), mes, ano)));}
			catch (ParseException e) {return false;}
			
			parcela.setValor_parcela(valor_parcela); 
			parcela.setIndice(i+1);
			parcela.setFk_entrada_saida(0);
			parcela.setStatus("ABERTO");
			
			if(mes < 12)
			mes++;
				else{
				mes = 1;
				ano++;
				}
			
			this.parcelas.add(parcela);
			}		
		}
	
	this.setInfoDeParcelas();	
		
	return true;
	}
	
	
		
		
		
	
	
	protected void addFornecedor(){
	
	Fornecedor retorno = new Fornecedor();
		
	FormDeSelecao<Fornecedor> selectionItemForm = 
									new FormDeSelecao<Fornecedor>("Adicionar Fornecedor", retorno, Fornecedor.class, "forn.status='ATIVO'");
	selectionItemForm.mostrar();
								
		if(retorno != null && retorno.getId_fornecedor() > 0){
											
		this.idFornecedor = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		
		this.idCredor = 0;
		this.tf_credor.setText("");
		}
	}
	
	
	
	
	
	
	private void addNovoFornecedor(){
		
	Fornecedor retorno = new Fornecedor();
		
	FormNovoFornecedor form = new FormNovoFornecedor(retorno);
	form.mostrar();
									
		if(retorno != null && retorno.getId_fornecedor() > 0){
												
		this.idFornecedor = retorno.getId_fornecedor();
		this.tf_fornecedor.setText(retorno.getNome_razao());
		
		this.idCredor = 0;
		this.tf_credor.setText("");
		}	
	}

	
	
	
	
	
	protected void addCredor(){
	
	Credor retorno = new Credor();
		
	FormDeSelecao<Credor> selectionItemForm = 
										new FormDeSelecao<Credor>("Adicionar Credor", retorno, Credor.class, "cred.status='ATIVO'");
	selectionItemForm.mostrar();
									
		if(retorno != null && retorno.getId_credor() > 0){
												
		this.idCredor = retorno.getId_credor();
		this.tf_credor.setText(retorno.getNome_razao());
		
		this.idFornecedor = 0;
		this.tf_fornecedor.setText("");
		}	
	}
	
		
	
	
	
	
	protected void addNovoCredor(){
		
	Credor retorno = new Credor();
		
	FormNovoCredor form = new FormNovoCredor(retorno);
	form.mostrar();
										
		if(retorno != null && retorno.getId_credor() > 0){
		
		this.idCredor = retorno.getId_credor();
		this.tf_credor.setText(retorno.getNome_razao());
		
		this.idFornecedor = 0;
		this.tf_fornecedor.setText("");
		}	
	}
	
	
	
	
	
	

	protected void setInfoDeParcelas(){
		
	this.modelo.setNumRows(0);
	String[] dados = new String[4];	
	
		for(Parcela parcela: this.parcelas){
	
		dados[0] = ""+parcela.getIndice();
		dados[1] = Calculo.formataValor(parcela.getValor_parcela());	
		dados[2] = Data.converteDataParaString(parcela.getData_vencimento());
		
		String status = "<html><font color=";
		
		if(parcela.getStatus().compareTo("ABERTO")==0)
		status += "red";
		else
		status += "green";
		
		dados[3] = status+">"+parcela.getStatus()+"</font></html>";
		
		this.modelo.addRow(dados);	
		}		
	}
	
	
	
	
	
	
	private void fechar(){
		
	Parcela parcela = null;	
		
		if(this.cb_tipo.getSelectedItem().toString().compareTo("UNICO")==0){
	
		List<Parcela> parcelas= new DAO<Parcela>(Parcela.class).get(null, 
										"par.fk_entrada_saida="+this.retorno.getId_entrada_saida(), null); 
		
			if(parcelas.size()==0){
			
			Mensagens.msgDeErro("Informações deste item não estão disponíveis.");
			return;
			}
		
			
		parcela	 = parcelas.get(0);
		}
		else{
			
		int index = this.parcelas_geradas.getSelectedRow();
				
		if(index >= 0)
		parcela	 = this.parcelas.get(index);
			else{
			
			Mensagens.msgDeErro("Selecione uma parcela para fechá-la.");
			return;
			}
		}
	
	
		if(parcela.getStatus().compareTo("FECHADO")==0){
			
		Mensagens.msgDeErro("O item selecionado já está fechado.");
		return;	
		}
		
		
		
	FormFecharItem form  = new FormFecharItem(this.retorno, parcela);
	form.mostrar();
		
	this.setInfoDeParcelas();	

	this.bloquearEdicao();
	
	if(this.lb_info!=null)
	this.lb_info.setText("<html><b>Cadastrado em:</b> "+Data.converteDataParaString(this.retorno.getData_cadastro())+" | <b>Código: </b>"+(this.tipo==0?"PAG":"REC")+String.format("%06d", this.retorno.getId_entrada_saida())+" | <b>Status: </b>"+this.retorno.getStatus()+"</html>");
	}

	

	
	
	
	protected void bloquearEdicao(){
		
		if(this.retorno.getStatus().compareTo("ABERTO") != 0 ){
			
		this.tf_credor.setEnabled(false);
		this.tf_fornecedor.setEnabled(false);
		this.bt_novo_fornecedor.setEnabled(false);
		this.bt_novo_credor.setEnabled(false);
		tf_referente.setEditable(false);
		cb_tipo.setEnabled(false);

		tf_val_total.setEditable(false);
		tf_multa.setEditable(false);
		tf_juros.setEditable(false);

		tf_entrada.setEditable(false);
		tf_num_parcelas.setEditable(false);
		tf_venci.setEditable(false);
		tf_val_parcela.setEditable(false);
			
		bt_save.setEnabled(false);
		bt_calc_parcela.setEnabled(false);
		}
	
		
	if(this.retorno.getStatus().compareTo("FECHADO") == 0)
	this.bt_fechar_parcela.setEnabled(false);
		
	}
	
	
	
}
