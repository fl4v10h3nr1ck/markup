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

import classes.CampoCPF;
import componentes.Rotulo;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public class FormCPF extends Dialogo{

	

private static final long serialVersionUID = 1L;


private CampoCPF tf_cpf;
private Item item;





	public FormCPF(Item item) {
	
	super("Informe o CPF", 400, 150);
	
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
	p1.add(new Rotulo("<html>CPF:<font color=red></font></html>"), cons);

	this.tf_cpf = new CampoCPF("");
	p1.add(this.tf_cpf, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 20;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("adiconar CPF");
	p1.add(bt_save, cons);
		
	
		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		if(acaoPrincipal())
		dispose();
		
		}});
	
		
		bt_save.addKeyListener(new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent ek){
					
			if(ek.getKeyCode() == KeyEvent.VK_ENTER){
				
			if(acaoPrincipal())
			dispose();
			}
				
				
		if(ek.getKeyCode() == KeyEvent.VK_UP || ek.getKeyCode() == KeyEvent.VK_DOWN)
		tf_cpf.requestFocus();
					
		}});
	
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	textFieldList.add(this.tf_cpf);
		
	Comuns.addEventoDeFoco(textFieldList);
	}

	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
		if(this.tf_cpf.getText().length() < 14){
			
		Mensagens.msgDeErro("informe um número de CPF válido.");
		Comuns.textFieldErroMode(this.tf_cpf);
		this.tf_cpf.requestFocus();
		return false;	
		}
			
	item.addParamentro("cpf", this.tf_cpf.getText());
	return true;
	}





	
}
