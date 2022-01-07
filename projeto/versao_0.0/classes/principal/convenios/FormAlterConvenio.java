package principal.convenios;


import DAO.DAO;
import componentes.beans.Convenio;
import comuns.Comuns;
import comuns.Data;




public class FormAlterConvenio extends FormConvenioBase{




private static final long serialVersionUID = 1L;

	

	public FormAlterConvenio(Convenio convenio) {
	
	super("Dados de Convênio", 650, 190, convenio);
	

	this.adicionarComponentes();
	
	this.setValores();
	}



	

	public void setValores(){
	
	this.tf_descricao.setText(this.convenio.getDescricao());
	this.tf_valor_desconto.setText(this.convenio.getValor_desconto());	
	this.tipo_desconto.setSelectedItem(this.convenio.getTipo_desconto());
	this.tf_codigo.setText(this.convenio.getCodigo());
	
	this.tf_data_inicio.setText(Data.converteDataParaString(this.convenio.getInicio()));
	this.tf_data_fim.setText(Data.converteDataParaString(this.convenio.getFim()));
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	this.convenio.setDescricao(tf_descricao.getText());
	this.convenio.setTipo_desconto(String.valueOf(tipo_desconto.getSelectedItem()));
	this.convenio.setValor_desconto(tf_valor_desconto.getText().length()==0? "0,00":tf_valor_desconto.getText());
	this.convenio.setCodigo(this.tf_codigo.getText());
	this.convenio.setInicio(Comuns.converteStringParaData(tf_data_inicio.getText()));
	this.convenio.setFim(Comuns.converteStringParaData(tf_data_fim.getText())); 
	
	return new DAO<Convenio>(Convenio.class).altera(this.convenio);
	}



	
	
}
