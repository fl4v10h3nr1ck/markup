package classes.estoque.cadastro.fornecedores;


import java.util.List;

import classes.componentes.endereco.beans.Endereco;
import classes.dao.DAO;
import classes.estoque.beans.ContaBancaria;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.Grupo_fornecedor;






public class FormAlterFornecedor extends FormFornecedorBase{



private static final long serialVersionUID = 1L;




	public FormAlterFornecedor(Fornecedor fornecedor) {
	
	super("Dados de Fornecedor", fornecedor);
	
	this.adicionarComponentes();
	
	this.setValores();
	}


	
	

	

	public void setValores(){
	
	this.tf_nome_razao.setText(this.retorno.getNome_razao());
	this.tipo.setSelectedItem(this.retorno.getTipo());
	
	if(this.tipo.getSelectedIndex()==0)
	this.tf_cpf.setText(this.retorno.getCpf_cnpj());	
	else
	this.tf_cnpj.setText(this.retorno.getCpf_cnpj());	
	
	this.tf_nome_fantasia.setText(this.retorno.getNome_fantasia_apelido());	
	this.tf_resp.setText(this.retorno.getNome_responsavel());	
	this.tf_rg_ie.setText(this.retorno.getRg_ie());	
	
	this.tf_fax.setText(this.retorno.getFax());	
	this.tf_tel_1.setText(this.retorno.getTel_1());
	this.tf_tel_2.setText(this.retorno.getTel_2());
	this.tf_email.setText(this.retorno.getEmail());
	
		if(this.retorno.getFk_grupo()>0){
			
		Grupo_fornecedor grupo  = new DAO<Grupo_fornecedor>(Grupo_fornecedor.class).get(this.retorno.getFk_grupo());
	
			if(grupo!=null){
		
			this.idGrupo = grupo.getId_grupo_fornecedor();
			this.tf_grupo.setText(grupo.getDescricao());
			}
		}
	
	Endereco endereco = new DAO<Endereco>(Endereco.class).get(this.retorno.getFk_endereco());	
	
	if(endereco!=null)
	this.p_endereco.setValores(endereco);
	
	this.p_contas.addContas(new DAO<ContaBancaria>(ContaBancaria.class).get(null, "cont_b.fk_fornecedor= "+this.retorno.getId_fornecedor(), null));
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	this.retorno.setNome_razao(tf_nome_razao.getText());
	this.retorno.setNome_fantasia_apelido(this.tf_nome_fantasia.getText());
	this.retorno.setNome_responsavel(this.tf_resp.getText());
	this.retorno.setTipo(String.valueOf(tipo.getSelectedItem()));
	this.retorno.setCpf_cnpj(this.tipo.getSelectedIndex()==0?this.tf_cpf.getText():this.tf_cnpj.getText());
	this.retorno.setFk_grupo(this.idGrupo);
	this.retorno.setRg_ie(this.tf_rg_ie.getText());
	this.retorno.setFax(tf_fax.getText());
	this.retorno.setTel_1(tf_tel_1.getText());
	this.retorno.setTel_2(tf_tel_2.getText());
	this.retorno.setEmail(tf_email.getText());
	
	Endereco endereco = this.p_endereco.getValores();
	endereco.setId_endereco(this.retorno.getFk_endereco());
	
	if(!new DAO<Endereco>(Endereco.class).altera(endereco))
	return false;
	
	if(!new DAO<Fornecedor>(Fornecedor.class).altera(this.retorno))
	return false;
	
	DAO<ContaBancaria> contas_DAO  = new DAO<ContaBancaria>(ContaBancaria.class);
	
	if(!contas_DAO.removeSemConfirmacao("fk_fornecedor="+this.retorno.getId_fornecedor()))
	return false;
	
	List<ContaBancaria> contas = this.p_contas.getContas();
	
		for(ContaBancaria item: contas){
			
		item.setFk_fornecedor(this.retorno.getId_fornecedor());	
		
		new DAO<ContaBancaria>(ContaBancaria.class).novo(item);
		}

	return true;
	}





	
	
}
