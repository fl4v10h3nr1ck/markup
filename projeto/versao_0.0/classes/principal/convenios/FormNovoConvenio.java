package principal.convenios;

import DAO.DAO;
import componentes.beans.Convenio;
import comuns.Comuns;





public class FormNovoConvenio extends FormConvenioBase{


private static final long serialVersionUID = 1L;


	
	
	public FormNovoConvenio(){
		
	this(null);
	}
	
	

	public FormNovoConvenio(Convenio convenio){
		
	super("Novo Convênio", 650, 190, convenio);	
		
	adicionarComponentes();
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	if(this.convenio==null)
	this.convenio = new Convenio();
	
	this.convenio.setDescricao(tf_descricao.getText());
	this.convenio.setTipo_desconto(String.valueOf(tipo_desconto.getSelectedItem()));
	this.convenio.setValor_desconto(tf_valor_desconto.getText().length()==0? "0,00":tf_valor_desconto.getText());
	this.convenio.setCodigo(this.tf_codigo.getText());
	this.convenio.setInicio(Comuns.converteStringParaData(tf_data_inicio.getText()));
	this.convenio.setFim(Comuns.converteStringParaData(tf_data_fim.getText())); 
	this.convenio.setStatus("ATIVO");
	
	int id = new DAO<Convenio>(Convenio.class).novo(this.convenio);
	
	if(id==0)
	return false;
	
	this.convenio.setId_convenio(id);
	
	return true;
	}





	
	
}
