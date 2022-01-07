package principal.colaboradores;


import java.util.Date;

import DAO.DAO;
import componentes.beans.Colaborador;





public class FormNovoColaborador extends FormColaboradorBase{


private static final long serialVersionUID = 1L;



	public FormNovoColaborador(){
		
	this(null);
	}
	
	
	
	
	public FormNovoColaborador(Colaborador retorno){
		
	super("Novo Colaborador", 650, 240, retorno);	
	
	adicionarComponentes();
	}
	
	

	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;

	if(this.retorno==null)
	this.retorno = new Colaborador();
	
	this.retorno.setNome(tf_nome.getText());
	this.retorno.setCpf(this.tf_cpf.getText());
	this.retorno.setTel_interno(this.tf_tel_interno.getText());
	this.retorno.setTel_pessoal(this.tf_tel_pessoal.getText());
	this.retorno.setEmail(this.tf_email.getText());
	this.retorno.setValor_comissao(this.tf_valor_comissao.getText());
	this.retorno.setFk_usuario(this.idUser);
	this.retorno.setFk_cargo(this.idCargo);
	this.retorno.setStatus("ATIVO");
	this.retorno.setData_cadastro(new Date());
	
	
	int id = new DAO<Colaborador>(Colaborador.class).novo(this.retorno);
	
	if(id==0)
	return false;
	
	this.retorno.setId_colaborador(id);
	
	return true;
	}




	



	
	
}
