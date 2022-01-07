package principal.entrada_saida;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import DAO.DAO;
import componentes.Rotulo;
import componentes.beans.Credor;
import componentes.beans.Entrada_Saida;
import componentes.beans.Fornecedor;
import componentes.beans.Parcela;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterPagamento extends FormEntrada_SaidaBase{




private static final long serialVersionUID = 1L;

private JPanel infoPanel;	



	public FormAlterPagamento(Entrada_Saida retorno, int tipo) {
	
	super("Dados de Pagamento", 780, 550, retorno, tipo);	
	
	this.setInfosAdicionais();
	
	this.adicionarComponentes();
	
	this.setValores();
	
	this.bloquearEdicao();
	}


	
	
	
	
	private void setInfosAdicionais(){
		
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	cons.insets = new Insets(0, 2, 2, 2);
	this.infoPanel = new JPanel(new GridBagLayout());
	infoPanel.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(infoPanel, cons);	
			
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	infoPanel.add(this.lb_info = new Rotulo("<html><b>Cadastrado em:</b> "+Data.converteDataParaString(this.retorno.getData_cadastro())+" | <b>Código: </b>"+(this.tipo==0?"PAG":"REC")+String.format("%06d", this.retorno.getId_entrada_saida())+" | <b>Status: </b>"+this.retorno.getStatus()+"</html>"), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	infoPanel.add(new JLabel(""), cons);
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 2, 0);
	infoPanel.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	}
	
	
	
	

	public void setValores(){
	
	this.tf_referente.setText(this.retorno.getReferente());	
		
		if(retorno.getFk_credor() > 0){
		
		Credor credor = new DAO<Credor>(Credor.class).get(retorno.getFk_credor());	
			
			if(credor!=null){
			
			this.idCredor = credor.getId_credor();
			this.tf_credor.setText(credor.getNome_razao());	
			}
		}
		else if(retorno.getFk_fornecedor() > 0){
		
		Fornecedor fornecedor = new DAO<Fornecedor>(Fornecedor.class).get(retorno.getFk_fornecedor());	
			
			if(fornecedor!=null){
			
			this.idFornecedor = fornecedor.getId_fornecedor();
			this.tf_fornecedor.setText(fornecedor.getNome_razao());	
			}	
		}
	
	this.cb_tipo.setSelectedItem(this.retorno.getTipo_pagamento());
	
	this.tf_val_total.setText(this.retorno.getValor_total());
	this.tf_multa.setText(this.retorno.getPorcento_multa());
	this.tf_juros.setText(this.retorno.getPorcento_juros());
	
	this.tf_entrada.setText(this.retorno.getValor_entrada());
	
		if(this.retorno.getTipo_pagamento().compareTo("PARCELADO")==0){
		this.parcelas = new DAO<Parcela>(Parcela.class).get(null, 
					"par.fk_entrada_saida="+this.retorno.getId_entrada_saida(), null); 
		
		this.tf_num_parcelas.setText(""+this.retorno.getNum_de_parcelas());
		this.tf_val_parcela.setText(this.parcelas.size()>0?this.parcelas.get(0).getValor_parcela():"");
		}

	this.tf_venci.setText(Data.converteDataParaString(this.retorno.getPrimeiro_vencimento()));

	this.setInfoDeParcelas();
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
			
	if(!calculaParcela())
	return false;
	
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
	
	if(this.cb_tipo.getSelectedIndex() == 1)
	this.retorno.setNum_de_parcelas(Integer.parseInt(this.tf_num_parcelas.getText()));	
	else
	this.retorno.setNum_de_parcelas(1);	
		
	
	if(!new DAO<Entrada_Saida>(Entrada_Saida.class).altera(this.retorno))
	return false;	
			

	DAO<Parcela> parcela_DAO = new DAO<Parcela>(Parcela.class);
	
	
	if(!parcela_DAO.removeSemConfirmacao("fk_entrada_saida="+this.retorno.getId_entrada_saida()))
	return false;
	
		for(Parcela parcela: this.parcelas){
			
		parcela.setFk_entrada_saida(this.retorno.getId_entrada_saida());
		parcela_DAO.novo(parcela);
		}
			
	return true;
	}



	
}
