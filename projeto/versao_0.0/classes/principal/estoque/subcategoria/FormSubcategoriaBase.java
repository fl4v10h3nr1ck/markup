package principal.estoque.subcategoria;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO;
import classes.CampoLimitado;
import componentes.Rotulo;
import componentes.beans.Subcategoria;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;





public abstract class FormSubcategoriaBase extends Dialogo {

	
	
private static final long serialVersionUID = 1L;

public CampoLimitado tf_descricao;
public CampoLimitado tf_codigo;

public Subcategoria retorno;




	public FormSubcategoriaBase(String title, int largura, int altura, Subcategoria retorno) {
			
	super(title, largura, altura);
	
	this.retorno = retorno;
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
	cons.insets = new Insets( 2, 2, 0, 2);
	cons.weightx = 0.2;
	p1.add(new Rotulo("<html>Código:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.8;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new Rotulo("<html>Descrição:<font color=red>*</font></html>"), cons);

	
	cons.gridwidth  = 1;	
	cons.weightx = 0.2;
	cons.insets = new Insets( 2, 2, 2, 2);
	this.tf_codigo = new CampoLimitado(40);
	p1.add(this.tf_codigo, cons);
	
	cons.weightx = 0.8;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_descricao = new CampoLimitado(200);
	p1.add(this.tf_descricao, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	cons.weightx = 0;
	cons.insets = new Insets( 4, 2, 0, 2);
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar");
	p1.add(bt_save, cons);
			
	
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
	textFieldList.add(this.tf_codigo);
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);	
	}




	

	protected boolean validation(){
		
		if(this.tf_codigo.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe um código para da subcategoria.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
			
			
		if(!Comuns.codigoPermitido(new DAO<Subcategoria>(Subcategoria.class), this.tf_codigo.getText(), this.retorno!=null?this.retorno.getId_subcategoria():0)){
				
		Mensagens.msgDeErro("O código informado já está sendo usado por outra subcategoria.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
		
		
	
		if(this.tf_descricao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe a descrição da subcategoria.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;	
		}
		
	return true;	
	}







	
	
}	
