package principal.entrada_saida;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.joda.time.DateTime;
import org.joda.time.Days;

import DAO.DAO;
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoMoeda;
import componentes.Rotulo;
import componentes.beans.Entrada_Saida;
import componentes.beans.Parcela;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import comuns.Preferencias;





public class FormFecharItem extends Dialogo{

	

private static final long serialVersionUID = 1L;

		
private CampoLimitado tf_id_pagamento;
private CampoLimitado tf_parcela;
private CampoLimitado tf_vencimento;
private CampoData 		tf_pagamento;
private CampoLimitado tf_val_parcela;
private CampoMoeda tf_val_ajustado;


private JCheckBox nao_ajustar;


private JButton bt_save;


private Parcela parcela;
private Entrada_Saida item;





	public FormFecharItem(Entrada_Saida item,  Parcela parcela) {
		
	super("Fechamento de Parcela", 620, 230);
	
	this.item = item;
	
	this.parcela = parcela;
	
	this.adicionarComponentes();
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
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(5, 2, 0, 2);
	p1.add(new Rotulo("Pagamento:"), cons);
	p1.add(new Rotulo("Parcela:"), cons);		
	p1.add(new Rotulo("Date de Vencimento:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new Rotulo("<html>Data de Pagamento:<font color=red>*</font></html>"), cons);

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_id_pagamento = new CampoLimitado(200, (this.item.getTipo().compareTo("SAIDA")==0?"PAG":"REC")+String.format("%06d", this.item.getId_entrada_saida()));
	p1.add(this.tf_id_pagamento, cons);
	this.tf_id_pagamento.setEditable(false);
	
	this.tf_parcela = new CampoLimitado(200, this.parcela.getIndice()+"/"+this.item.getNum_de_parcelas());
	p1.add(this.tf_parcela, cons);
	this.tf_parcela.setEditable(false);

	this.tf_vencimento = new CampoLimitado(200, Data.converteDataParaString(this.parcela.getData_vencimento()));
	p1.add(this.tf_vencimento, cons);
	this.tf_vencimento.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_pagamento = new CampoData(Data.converteDataParaString(new Date()));
	p1.add(this.tf_pagamento, cons);
	
	
	cons.insets = new Insets(12, 2, 12, 2);
	this.nao_ajustar = new JCheckBox("Não adicionar tarifas.");
	this.nao_ajustar.setForeground(Preferencias.COR_DE_ROTULO);
	p1.add(this.nao_ajustar, cons);
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new Rotulo("Valor Parcela (R$):"), cons);
	p1.add(new Rotulo("<html>Valor Ajustado (R$):<font color=red>*</font></html>"), cons);
	p1.add(new Rotulo(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;		
	p1.add(new Rotulo(""), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 5, 2);
	this.tf_val_parcela = new CampoLimitado(200, Calculo.formataValor(this.parcela.getValor_parcela()));
	p1.add(this.tf_val_parcela, cons);
	this.tf_val_parcela.setEditable(false);
	
	this.tf_val_ajustado = new CampoMoeda(8);
	p1.add(this.tf_val_ajustado, cons);
	
	p1.add(new Rotulo(""), cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel(""), cons);

	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	cons.insets = new Insets(2, 2, 2, 2);
	this.bt_save  = new JButton("Fechar Item", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Fechar item descrito");
	this.add(bt_save, cons);
		
	
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
	
	
		
		nao_ajustar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		calculaParcela();
		}});
		
		
		
		this.tf_pagamento.addFocusListener( new FocusAdapter(){
		@Override
		public void focusLost(FocusEvent arg0) {
		calculaParcela();
		}});
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	textFieldList.add(this.tf_pagamento);
	textFieldList.add(this.tf_val_ajustado);
	
	Comuns.addEventoDeFoco(textFieldList);	
		
		
	this.calculaParcela();
		
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	

	
	protected boolean validation(){
	
		if(!this.tf_pagamento.validacao()){
				
		Mensagens.msgDeErro("informe uma data de pagamento válida.");
		Comuns.textFieldErroMode(this.tf_pagamento);
		return false;	
		}
	
		if(this.tf_val_ajustado.getText().length()==0){
			
		Mensagens.msgDeErro("informe o valor ajustado do item.");
		Comuns.textFieldErroMode(this.tf_val_ajustado);
		return false;	
		}
		
		
	return true;
	}
	
	


	
	
	
	@Override
	public boolean acaoPrincipal() {
	
	if(!validation())	
	return false; 
	
	if(!calculaParcela())
	return false;
	
	Date data_pagamento = null;
		try {
		data_pagamento =new SimpleDateFormat("dd/MM/yyyy").parse(this.tf_pagamento.getText());} 
		catch (ParseException e) { data_pagamento = new Date();}
	
		
	this.parcela.setValor_final(this.tf_val_ajustado.getText());
	this.parcela.setData_pagamento(data_pagamento);
	this.parcela.setStatus("FECHADO");	
	
	DAO<Parcela> parcela_DAO = new DAO<Parcela>(Parcela.class);
	
	if(!parcela_DAO.altera(this.parcela))
	return false;
	
	boolean concluido = true;
	
	List<Parcela> parcelas = parcela_DAO.get(null, "par.fk_entrada_saida="+this.item.getId_entrada_saida(), null);
	
		for(Parcela parcela: parcelas){
			
			if(parcela.getStatus().compareTo("ABERTO")==0){
			concluido = false;
			break;
			}
		}
	
	this.item.setStatus(concluido?"FECHADO":"EM PAGAMENTO");
	
	return new DAO<Entrada_Saida>(Entrada_Saida.class).altera(this.item);
	}
	
	
	
	
	
	
	protected boolean calculaParcela(){
	
		if(!this.tf_pagamento.validacao()){
			
		Mensagens.msgDeErro("informe uma data de pagamento válida.");
		Comuns.textFieldErroMode(this.tf_pagamento);
		return false;	
		}	
		
		
	if(this.nao_ajustar.isSelected())
	this.tf_val_ajustado.setText(this.parcela.getValor_parcela());		
		else{
		
		Date pagamento = null;	
		try {pagamento = new SimpleDateFormat("dd/MM/yyyy").parse(tf_pagamento.getText());} catch (ParseException e) {return false;}
			
			
		
		this.tf_val_ajustado.setText(this.calculaReajuste(this.parcela.getValor_parcela(), 
																this.item.getPorcento_juros(),
																	this.item.getPorcento_multa(),
																		this.parcela.getData_vencimento(),
																			pagamento));			
		}
		
	return true;
	}



	

	
	
	private String calculaReajuste(String valor, String juros, String multa, Date vencimento, Date pagamento){
		
	DateTime data_vencimento = new DateTime(vencimento);	
	DateTime data_pagamento = new DateTime(pagamento);	
			
	int dias = Days.daysBetween(data_vencimento, data_pagamento).getDays();
		
	if(dias <= 0)
	return valor;
		
	String tarifa = 
			Calculo.soma(
					Calculo.multiplica(
							Calculo.calcPorcentagem(valor, 
									Calculo.dividi(juros, ""+Comuns.numDeDiasDeMesComercial)), ""+dias),
									Calculo.calcPorcentagem(valor, multa));
		
	return Calculo.soma(valor, tarifa);	
	}
		
	
	
	
}
