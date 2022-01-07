package principal.endereco;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO;
import classes.CampoCEP;
import classes.CampoLimitado;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Endereco;
import componentes.beans.Municipio;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public class PainelDeEndereco  extends JPanel{


private static final long serialVersionUID = 1L;


public CampoLimitado tf_logradouro;
public CampoLimitado tf_num;
public CampoLimitado tf_bairro;

public int idMunicipio;
public CampoLimitado tf_municipal;

public CampoLimitado tf_uf;
public CampoCEP tf_cep;




	public PainelDeEndereco(){
	
	this.setLayout(new GridBagLayout());	
	this.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;	
	add(new Rotulo("<html>Logradouro:<font color=red>*</font></html>"), cons);
	add(new Rotulo("Número:"), cons);
	add(new Rotulo("<html>Bairro:<font color=red>*</font></html>"), cons);
	add(new Rotulo("<html>Município:<font color=red>*</font></html>"), cons);
	add(new Rotulo("UF:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	add(new Rotulo("CEP:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_logradouro = new CampoLimitado(250);
	add(this.tf_logradouro, cons);
	
	this.tf_num = new CampoLimitado(40);
	add(this.tf_num, cons);
		
	this.tf_bairro = new CampoLimitado(200);
	add(this.tf_bairro, cons);
		
	this.tf_municipal = new CampoLimitado(200);
	add(this.tf_municipal, cons);
	this.tf_municipal.setEditable(false);
	
	this.tf_uf = new CampoLimitado(200);
	add(this.tf_uf, cons);
	this.tf_uf.setEditable(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_cep = new CampoCEP();
	add(this.tf_cep, cons);
	

	

		this.tf_municipal.addFocusListener( new FocusAdapter(){	
		@Override 
		public void focusGained(FocusEvent e) {
				
		tf_cep.requestFocusInWindow();
		
		Comuns.removeIndicadoresDeErro(tf_municipal);
		
		Municipio retorno = new Municipio();
				
		FormDeSelecao<Municipio> selectionItemForm = 
						new FormDeSelecao<Municipio>("Adicionar Município", retorno, Municipio.class, null);
		selectionItemForm.mostrar();
					
			if(retorno != null && retorno.getCodigo() > 0){
								
			idMunicipio = retorno.getCodigo();
			tf_municipal.setText(retorno.getMunicipio());
			tf_uf.setText(retorno.getUf());
			}
		}});

		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(this.tf_logradouro);
	textFieldList.add(this.tf_cep);
	textFieldList.add(this.tf_bairro);
	textFieldList.add(this.tf_municipal);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	}
	
	
	
	
	
	public boolean validacao(){
	
		if(this.tf_logradouro.getText().length()==0){
				
		Mensagens.msgDeErro("Informe o logradouro");
		Comuns.textFieldErroMode(this.tf_logradouro);
		return false;		
		}
		
		
		if(this.tf_bairro.getText().length()==0){
			
		Mensagens.msgDeErro("Informe o bairro");
		Comuns.textFieldErroMode(this.tf_bairro);
		return false;		
		}
		
		
		if(this.idMunicipio ==0){
			
		Mensagens.msgDeErro("Selecione o município.");
		Comuns.textFieldErroMode(this.tf_municipal);
		return false;		
		}
		
		
		if(this.tf_cep.getText().length()> 0){
			if(!this.tf_cep.validacao()){
				
			Mensagens.msgDeErro("CEP é opcional, caso queira informá-lo, informe o número válido");
			Comuns.textFieldErroMode(this.tf_cep);
			return false;	
			}
		}
	
	return true;
	}
	

	
	
	

	public void setValores(Endereco endereco){
	
	if(endereco == null)
	return;
	
	this.tf_logradouro.setText(endereco.getLogradouro());
		
	Municipio municipio = new DAO<Municipio>(Municipio.class).get(endereco.getFk_municipio());
	
		if(municipio!= null){
		
		this.tf_municipal.setText(municipio.getMunicipio());
		this.tf_uf.setText(municipio.getUf());
		this.idMunicipio = municipio.getCodigo();
		}
	

	this.tf_num.setText(endereco.getNum());
	this.tf_bairro.setText(endereco.getBairro());
	this.tf_cep.setText(endereco.getCep());
	}
	
	
	
	
	

	public Endereco getValores(){
	
	Endereco endereco = new Endereco();
		
	endereco.setLogradouro(this.tf_logradouro.getText());
	endereco.setNum(this.tf_num.getText());
	endereco.setBairro(this.tf_bairro.getText());
	endereco.setPais("Brasil");
	endereco.setCep(this.tf_cep.getText());
	endereco.setFk_municipio(this.idMunicipio);

	return endereco;
	}
	
	
}



