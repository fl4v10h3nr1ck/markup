package classes.estoque.cadastro.fornecedores;

import java.util.Date;
import java.util.List;

import classes.componentes.endereco.beans.Endereco;
import classes.dao.DAO;
import classes.estoque.beans.ContaBancaria;
import classes.estoque.beans.Fornecedor;







public class FormNovoFornecedor extends FormFornecedorBase{


	
private static final long serialVersionUID = 1L;


	
	public FormNovoFornecedor(){
		
	this(null);
	}
	
	

	
	public FormNovoFornecedor(Fornecedor retorno){
		
	super("Novo Fornecedor", retorno);	
		
	adicionarComponentes();
		
	this.p_contas.addConta(new ContaBancaria());
	}
	

	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	if(this.retorno==null)
	this.retorno = new Fornecedor();
	
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
	this.retorno.setData_cadastro(new Date());
	this.retorno.setAtivo("S");
	
	Endereco  endereco = this.p_endereco.getValores();
	
	int id = new DAO<Endereco>(Endereco.class).novo(endereco);
	if(id == 0)
	return false;
	
	this.retorno.setFk_endereco(id);
	
	id = new DAO<Fornecedor>(Fornecedor.class).novo(this.retorno);
	if(id == 0)
	return false;
	
	this.retorno.setId_fornecedor(id);

		
	List<ContaBancaria> contas = this.p_contas.getContas();
		
		for(ContaBancaria item: contas){
				
		item.setFk_fornecedor(this.retorno.getId_fornecedor());	
			
		new DAO<ContaBancaria>(ContaBancaria.class).novo(item);
		}
	

	return true;
	}





	
	
}
