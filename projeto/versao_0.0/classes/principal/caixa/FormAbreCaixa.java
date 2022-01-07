package principal.caixa;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO;
import classes.CampoLimitado;
import classes.CampoMoeda;
import componentes.beans.Caixa;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Data;
import comuns.Mensagens;
import comuns.Preferencias;





public class FormAbreCaixa extends Dialogo{


private static final long serialVersionUID = 1L;


private CampoLimitado tf_nome;
private CampoMoeda tf_saldo_inicial;


private Item retorno;


	

	
	public FormAbreCaixa( Item retorno){
		
	super("Novo Caixa", 500, 150);	
	
	this.retorno = retorno;
	
	adicionarComponentes();
	}
	
	

	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
			
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Identificador:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("Saldo Inicial:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_nome = new CampoLimitado(200, this.geraIdentificadorDeCaixa()), cons);
	this.tf_nome.setEditable(false);
	
	
	cons.gridwidth  = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(this.tf_saldo_inicial = new CampoMoeda(8), cons);
	this.tf_saldo_inicial.setText(Comuns.saldoPadraoInicialDeCaixa);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Abrir caixa");
	p1.add(bt_save, cons);
			
			

		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		if(acaoPrincipal())
		dispose();
				
		}});
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	textFieldList.add(this.tf_saldo_inicial);
	textFieldList.add(this.tf_nome);
	
		
	Comuns.addEventoDeFoco(textFieldList);
		
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	


	
	protected boolean validacao(){
		
		if(this.tf_nome.getText().length() == 0){
			
		Mensagens.msgDeErro("Um erro ocorreu ao definir o Identificador do caixa.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}
		
		
		if(this.tf_saldo_inicial.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o salvo inicial do caixa.");
		Comuns.textFieldErroMode(this.tf_saldo_inicial);
		return false;	
		}

	return true;	
	}




	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;

	Caixa caixa = new Caixa();
	caixa.setCodigo(this.tf_nome.getText());
	
	GregorianCalendar calendar = new GregorianCalendar();
	calendar.setTime(new Date());
	caixa.setData_abertura(calendar.getTime());
	caixa.setHora_abertura(calendar.get(Calendar.HOUR_OF_DAY));
	caixa.setMin_abertura(calendar.get(Calendar.MINUTE));
	
	caixa.setFk_user_abertura(Comuns.iDVendedor);
	caixa.setValor_inicial(this.tf_saldo_inicial.getText());
	caixa.setStatus("ABERTO");
	caixa.setCodigo_terminal(Comuns.cod_terminal);

	int id_caixa  = new DAO<Caixa>(Caixa.class).novo(caixa);	

	if(id_caixa == 0)
	return false;
	
	this.retorno.addParamentro("id", id_caixa);
			
	return true;
	}
	
	
	
	
	
	
	private String geraIdentificadorDeCaixa(){
		
	Random gerador = new Random();	

	return Data.getDataAtual()+"-"+Comuns.cod_terminal+"-"+Comuns.iDVendedor+"-"+gerador.nextInt(10000);
	}
	
	

}
