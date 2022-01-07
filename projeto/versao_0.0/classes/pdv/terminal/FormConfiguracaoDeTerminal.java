package pdv.terminal;

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
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public class FormConfiguracaoDeTerminal extends Dialogo{


private static final long serialVersionUID = 1L;


private CampoLimitado tf_cod;
private CampoLimitado tf_descricao;
	

private ConfigTerminal config;




	
	public FormConfiguracaoDeTerminal(){
		
	super("Configuração de Terminal", 500, 150);	
	
	this.config = new ConfigTerminal();
	
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
	cons.weighty  = 0;
	cons.weightx = 0.3;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;
	p1.add(new JLabel("<html>Código:<font color=red>*</font></html>"), cons);	
	cons.weightx = 0.7;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("<html>Nome:<font color=red>*</font></html>"), cons);	
	
	cons.weightx = 0.3;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.gridwidth  = 1;
	p1.add(this.tf_cod = new CampoLimitado(100), cons);
	this.tf_cod.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 0.7;
	p1.add(this.tf_descricao = new CampoLimitado(200), cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar configuração de caixa");
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
		
	textFieldList.add(this.tf_descricao);
	textFieldList.add(this.tf_cod);
	
		
	Comuns.addEventoDeFoco(textFieldList);
		
	this.getRootPane().setDefaultButton(bt_save);
	
	
	this.setInfo();
	}
		
	


	
	
	private boolean validacao(){
	
		if(this.tf_descricao.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe do nome do terminal.");
		Comuns.textFieldErroMode(this.tf_descricao);
		return false;	
		}

		if(this.tf_cod.getText().length() == 0){
			
		Mensagens.msgDeErro("Um erro ocorreu ao gerar o códico deste terminal.");
		Comuns.textFieldErroMode(this.tf_cod);
		return false;	
		}
		
	
	return true;	
	}




	
	private void setInfo(){
		
	Item credenciais = this.config.getCredenciais();	
	
	if(credenciais==null)
	return;
	
	this.tf_cod.setText(credenciais.getParamentro(this.config.COD_TERMINAL_CHAVE).toString());
	this.tf_cod.setText(credenciais.getParamentro(this.config.COD_TERMINAL_NOME).toString());
	
	if(this.tf_cod.getText().length()==0)
	this.tf_cod.setText(this.config.geraCodigo());	
	}
	
	
	
	

	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;	
	
	if(!this.config.setCodTerminal(this.tf_cod.getText(), this.tf_descricao.getText()))
	return false;
	
	Item credenciais = this.config.getCredenciais();	
	
	if(credenciais==null)
	return false;
	
	Comuns.cod_terminal= credenciais.getParamentro(config.COD_TERMINAL_CHAVE).toString();
	Comuns.descricao_terminal= credenciais.getParamentro(config.COD_TERMINAL_NOME).toString();	
	
	return true;
	}
	
	
	

}

