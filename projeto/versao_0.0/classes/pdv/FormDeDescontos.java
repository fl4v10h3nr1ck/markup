package pdv;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.CampoMoeda;
import componentes.Rotulo;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;



public class FormDeDescontos extends Dialogo{

	

private static final long serialVersionUID = 1L;


private JComboBox<String> tipo;
private CampoMoeda tf_desconto;
private Item item;

private JButton bt_save;



	public FormDeDescontos(Item item) {
	
	super("Informe o Desconto", 400, 200, false);
	
	this.item = item;
	
	this.adicionarComponentes();
	}

	
	
	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();   
		
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2,2,2,2);
	JPanel	p1 = new JPanel(new GridBagLayout());	
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);	
		
	
	cons.weighty  = 0;
	cons.fill = GridBagConstraints.HORIZONTAL;
	p1.add(new Rotulo("<html>Tipo de Desconto:<font color=red></font></html>"), cons);

	this.tipo = new JComboBox<String>(new String[]{"PORCENTAGEM", "VALOR"});
	p1.add(this.tipo, cons);
	
	cons.weighty  = 0;
	cons.fill = GridBagConstraints.HORIZONTAL;
	p1.add(new Rotulo("<html>Valor:<font color=red></font></html>"), cons);

	this.tf_desconto = new CampoMoeda(8);
	p1.add(this.tf_desconto, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 20;
	bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("adicionar desconto informado");
	p1.add(bt_save, cons);
		
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		if(acaoPrincipal())
		dispose();
		
		}});
	
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
		
	textFieldList.add(this.tf_desconto);

		
	Comuns.addEventoDeFoco(textFieldList);
	
	
	this.addAcoesDeTeclas();
	}

	
	
	
	
	

	@Override
	public boolean acaoPrincipal() {
	
		
		if(this.tipo.getSelectedItem().toString().compareTo("PORCENTAGEM") == 0 &&
				Calculo.stringParaDouble(this.tf_desconto.getText()) > 100){
			
		Mensagens.msgDeErro("O valor máximo para descontos do tipo PORCENTAGEM é 100.");
		Comuns.textFieldErroMode(this.tf_desconto);
		return false;			
		}
			
	item.addParamentro("tipo", this.tipo.getSelectedItem().toString());
	item.addParamentro("valor", this.tf_desconto.getText().length()==0?"0,00":this.tf_desconto.getText());
	return true;		
	}





	
	private void addAcoesDeTeclas(){
		
		
		this.tipo.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
			if(ek.getKeyCode() == KeyEvent.VK_ENTER)
			tf_desconto.requestFocus();
					
			}
		});
	
		
		
		
		this.tf_desconto.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
			if(ek.getKeyCode() == KeyEvent.VK_DOWN || ek.getKeyCode() == KeyEvent.VK_ENTER)
			bt_save.requestFocus();
			
			if(ek.getKeyCode() == KeyEvent.VK_UP)
			tipo.requestFocus();
			}
		});
		
		
		this.bt_save.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
				
				if(ek.getKeyCode() == KeyEvent.VK_UP)
				tf_desconto.requestFocus();
				
				if(ek.getKeyCode() == KeyEvent.VK_DOWN)
				tipo.requestFocus();
				
				
				if(ek.getKeyCode() == KeyEvent.VK_ENTER){
					
				if(acaoPrincipal())
				dispose();
				}	
			}
		});
	}
	
	
	
	
	
}
