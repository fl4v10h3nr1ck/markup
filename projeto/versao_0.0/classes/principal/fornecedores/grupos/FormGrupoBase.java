package principal.fornecedores.grupos;

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
import componentes.beans.Grupo_fornecedor;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;





public abstract class FormGrupoBase extends Dialogo {

	
	
private static final long serialVersionUID = 1L;


public CampoLimitado tf_descricao;
public CampoLimitado tf_codigo;


protected Grupo_fornecedor retorno;



	public FormGrupoBase(String title, int largura, int altura, Grupo_fornecedor retorno) {
			
	super(title, largura, altura);
	
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
	cons.insets = new Insets( 2, 2, 0, 2);
	cons.weightx = 0.2;
	p1.add(new Rotulo("C?digo:"), cons);
	cons.weightx = 0.8;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new Rotulo("<html>Descri??o:<font color=red>*</font></html>"), cons);

	
	cons.gridwidth  = 1;	
	cons.weightx = 0.2;
	cons.insets = new Insets( 2, 2, 2, 2);
	this.tf_codigo = new CampoLimitado(40);
	p1.add(this.tf_codigo, cons);
	
	cons.weightx = 0.8;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_descricao = new CampoLimitado(40);
	p1.add(this.tf_descricao, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	cons.weightx = 0;
	cons.insets = new Insets( 4, 2, 4, 2);
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
	
	textFieldList.add(this.tf_codigo);
	textFieldList.add(tf_descricao);
	
	Comuns.addEventoDeFoco(textFieldList);
	}



	


	protected boolean validation(){
		
		
		if(this.tf_codigo.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe um c?digo para o grupo.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
		
		
		if(!Comuns.codigoPermitido(new DAO<Grupo_fornecedor>(Grupo_fornecedor.class), this.tf_codigo.getText(), this.retorno!=null?this.retorno.getId_grupo_fornecedor():0)){
			
		Mensagens.msgDeErro("O c?digo informado j? est? sendo usado por outro grupo.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
		
		
		if(this.tf_descricao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe a descri??o do grupo.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;	
		}
		
		
		
	return true;	
	}
	
	

	
	
	
	
}	
