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



public class FormDeValorRecebido extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoMoeda tf_valor;
private Item item;

private JButton bt_save;

private String valor_total;





	public FormDeValorRecebido(Item item, String valor_total) {
	
	super("Informe o valor Recebido", 400, 150);
	
	this.item = item;
	
	this.valor_total = valor_total;
	
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
	p1.add(new Rotulo("<html>Valor Recebido:<font color=red></font></html>"), cons);

	p1.add(this.tf_valor= new CampoMoeda(8, this.valor_total), cons);
	

	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 20;
	bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("adicionar valor recebido");
	p1.add(bt_save, cons);
		
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		if(acaoPrincipal())
		dispose();
		
		}});
	
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
		
	textFieldList.add(this.tf_valor);

			
	Comuns.addEventoDeFoco(textFieldList);
	
	
	this.addAcoesDeTeclas();
	}

	
	
	
	
	
	


	@Override
	public boolean acaoPrincipal() {
	
		
		if(Calculo.stringParaDouble(this.tf_valor.getText()) < Calculo.stringParaDouble(this.valor_total)){
			
		Mensagens.msgDeErro("Valor recebido inferior ao valor da compra.");
		Comuns.textFieldErroMode(this.tf_valor);
		return false;			
		}
				
	item.addParamentro("valor", this.tf_valor.getText());
	return true;		
	}



	
	
	
	
	

	
	private void addAcoesDeTeclas(){
		
		
		this.tf_valor.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
			if(ek.getKeyCode() == KeyEvent.VK_ENTER)
			bt_save.requestFocus();
					
			}
		});
	
	

		bt_save.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
				if(ek.getKeyCode() == KeyEvent.VK_ENTER){
					
				if(acaoPrincipal())
				dispose();
				}
			}
		});
	}
	
	
	
}

