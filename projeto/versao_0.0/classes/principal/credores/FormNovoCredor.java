package principal.credores;


import java.util.Date;
import java.util.List;

import DAO.DAO;
import componentes.beans.ContaBancaria;
import componentes.beans.Credor;





public class FormNovoCredor extends FormCredorBase{


private static final long serialVersionUID = 1L;





	public FormNovoCredor(){
		
	this(null);
	}
	
	
	
	
	
	public FormNovoCredor(Credor credor){
		
	super("Novo Credor", 700, 500, credor);	
	
	adicionarComponentes();
	
	this.p_contas.addConta(new ContaBancaria());
	}
	
	
	
	

	@Override
	public boolean acaoPrincipal(){
		
	if(!validation())
	return false;

	if(this.credorRetorno==null)
	this.credorRetorno = new Credor();
	
	this.credorRetorno.setNome_razao(tf_nome.getText());
	this.credorRetorno.setTipo(String.valueOf(this.tipo.getSelectedItem()));
	this.credorRetorno.setCpf_cnpj(this.tipo.getSelectedIndex()==0?tf_cpf.getText():tf_cnpj.getText());
	this.credorRetorno.setTel_1(tf_tel_1.getText());
	this.credorRetorno.setTel_2(tf_tel_2.getText());
	this.credorRetorno.setEmail(tf_email.getText());
	this.credorRetorno.setData_cadastro(new Date());
	this.credorRetorno.setStatus("ATIVO");
	
	int id = new DAO<Credor>(Credor.class).novo(this.credorRetorno);
	
	if(id == 0)
	return false;
	
	List<ContaBancaria> contas = this.p_contas.getContas();
	
		for(ContaBancaria item: contas){
			
		item.setFk_credor(id);	
		
		new DAO<ContaBancaria>(ContaBancaria.class).novo(item);
		}
	
	return true;
	}




	
	
}
