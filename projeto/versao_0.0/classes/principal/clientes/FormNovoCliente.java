package principal.clientes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.DAO;
import componentes.beans.Cliente;
import componentes.beans.Endereco;





public class FormNovoCliente extends FormClienteBase{


private static final long serialVersionUID = 1L;





	public FormNovoCliente(){
		
	this(null);
	}
	
	
	
	
	
	public FormNovoCliente(Cliente cliente){
		
	super("Novo Cliente", 800, 350, cliente);	
	
	adicionarComponentes();
	}
	
	

	
	
	@Override
	public boolean acaoPrincipal(){
	
	if(!validation())
	return false;

	
	if(this.clienteRetorno==null)
	this.clienteRetorno = new Cliente();
	
	this.clienteRetorno.setNome_razao(tf_nome_razao.getText());
	this.clienteRetorno.setTipo(String.valueOf(this.tipo.getSelectedItem()));
	
		if(this.clienteRetorno.getTipo().compareTo("PF") ==0 ){
	
		this.clienteRetorno.setSexo(String.valueOf(combo_sexo.getSelectedItem()));
			
			if(this.tf_nascimento.getText().length() > 0){
			
			try {this.clienteRetorno.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(tf_nascimento.getText()));} 
			catch (ParseException e) {}
			}	
			
			
		this.clienteRetorno.setCpf_cnpj(tf_cpf.getText());	
		this.clienteRetorno.setRg_ie(this.tf_rg.getText());
		}
		else{
		
		this.clienteRetorno.setNome_fantasia(this.tf_nome_fantasia.getText());		
		this.clienteRetorno.setCpf_cnpj(tf_cnpj.getText());	
		this.clienteRetorno.setRg_ie(this.tf_ie.getText());			
		this.clienteRetorno.setInscricao_municipal(this.tf_insc_municipal.getText());	
		}
		
		
	this.clienteRetorno.setTel_1(tf_tel.getText());
	this.clienteRetorno.setTel_2(tf_cel.getText());
	this.clienteRetorno.setEmail(tf_email.getText());
	this.clienteRetorno.setStatus("ATIVO");
	this.clienteRetorno.setData_cadastro(new Date());

	Endereco  endereco = this.p_endereco.getValores();
	
	int id = new DAO<Endereco>(Endereco.class).novo(endereco);
	if(id == 0)
	return false;
	
	this.clienteRetorno.setFk_endereco(id);
	
	id = new DAO<Cliente>(Cliente.class).novo(this.clienteRetorno);
	if(id == 0)
	return false;
	
	this.clienteRetorno.setId_cliente(id);

	return true;
	}







	
	
}
