package principal.entrada_saida;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import DAO.DAO;
import componentes.beans.Entrada_Saida;
import componentes.beans.Parcela;
import comuns.Comuns;





public class FormNovaEntrada_Saida extends FormEntrada_SaidaBase{


private static final long serialVersionUID = 1L;





	public FormNovaEntrada_Saida(int tipo){
		
	this(null, tipo);
	}
	
	
	
	
	
	
	public FormNovaEntrada_Saida(Entrada_Saida retorno, int tipo){
		
	super("Novo "+(tipo==0?"Pagamento":"Recebimento"), 780, 550, retorno, tipo);	
	
	adicionarComponentes();
	
	this.bt_fechar_parcela.setEnabled(false);
	}
	
	

	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	if(!calculaParcela())
	return false;
	
	if(this.retorno==null)
	this.retorno = new Entrada_Saida();
	
	
	this.retorno.setFk_credor(this.idCredor);	
	this.retorno.setFk_fornecedor(this.idFornecedor);
	this.retorno.setFk_colaborador(Comuns.iDVendedor);
	this.retorno.setTipo_pagamento(cb_tipo.getSelectedItem().toString());
	this.retorno.setTipo(this.tipo==0?"SAIDA":"ENTRADA");
	this.retorno.setReferente(this.tf_referente.getText());
	
	this.retorno.setValor_total(this.tf_val_total.getText());
	this.retorno.setPorcento_multa(this.tf_multa.getText());
	this.retorno.setPorcento_juros(this.tf_juros.getText());
	this.retorno.setValor_entrada(this.tf_entrada.getText());
	this.retorno.setPorcento_comissao(Comuns.porcentoComissaoVendedor);
	
	try {this.retorno.setPrimeiro_vencimento(new SimpleDateFormat("dd/MM/yyyy").parse(tf_venci.getText()));} 
	catch (ParseException e1) {e1.printStackTrace();}
	
	this.retorno.setData_cadastro(new Date());
	this.retorno.setStatus("ABERTO");


	if(this.cb_tipo.getSelectedIndex() == 1)
	this.retorno.setNum_de_parcelas(Integer.parseInt(this.tf_num_parcelas.getText()));	
	else
	this.retorno.setNum_de_parcelas(1);	
	
	
	int id = new DAO<Entrada_Saida>(Entrada_Saida.class).novo(this.retorno);
	
	if(id== 0)
	return false;	
		
	DAO<Parcela> parcela_DAO = new DAO<Parcela>(Parcela.class);
	
		for(Parcela parcela: this.parcelas){
		
		parcela.setFk_entrada_saida(id);
		parcela_DAO.novo(parcela);
		}
		
	return true;
	}




	


	
	
}
