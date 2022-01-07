package principal.despesas;

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

import classes.CampoLimitado;
import classes.CampoMoeda;
import componentes.beans.Despesa;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;





public abstract class FormDespesaBase extends Dialogo {

	
	



private static final long serialVersionUID = 1L;


public CampoLimitado tf_descricao;
public CampoMoeda tf_valor_base;


protected Despesa retorno;





	public FormDespesaBase(String title, Despesa retorno) {
			
	super(title, 600, 140);
	
	this.retorno = retorno;
	}



	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);	
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;
	cons.weightx = 0.8;
	p1.add(new JLabel("<html>Descrição:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.2;
	p1.add(new JLabel("Valor Base (R$):"), cons);
	
	
	cons.gridwidth  = 1;	
	cons.weightx = 0.8;
	cons.insets = new Insets( 2, 2, 2, 2);
	this.tf_descricao = new CampoLimitado(200);
	p1.add(this.tf_descricao, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.2;
	this.tf_valor_base = new CampoMoeda(200);
	p1.add(this.tf_valor_base, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	cons.weightx = 0;
	cons.insets = new Insets( 4, 2, 0, 2);
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar");
	this.add(bt_save, cons);
			
	
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
	
	Comuns.addEventoDeFoco(textFieldList);
	}





	protected boolean validation(){
		
		
		if(this.tf_descricao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe a descrição do grupo.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;	
		}
		
	return true;	
	}
	
	


	
}	
