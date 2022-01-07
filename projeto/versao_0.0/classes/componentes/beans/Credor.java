package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="credores", prefixo="cred")
public class Credor {

@Anot_BD_Campo(nome = "id_credor", tipo=int.class, set = "setId_credor", get = "getId_credor", ehId=true)		
private int id_credor;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=75)
@Anot_TB_Coluna(posicao=0, rotulo="Nome/Razão Social", comprimento = 55)
@Anot_BD_Campo(nome = "nome_razao", set = "setNome_razao", get = "getNome_razao")
private String nome_razao;

@Anot_TB_Coluna(posicao=1, rotulo="Tipo", comprimento = 10)
@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")
private String tipo;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=25)
@Anot_TB_Coluna(posicao=2, rotulo="CPF/CNPJ", comprimento = 20)
@Anot_BD_Campo(nome = "cpf_cnpj", set = "setCpf_cnpj", get = "getCpf_cnpj")
private String cpf_cnpj;

@Anot_TB_Coluna(posicao=3, rotulo="TEL", comprimento = 15)
@Anot_BD_Campo(nome = "tel_1", set = "setTel_1", get = "getTel_1")
private String tel_1;

@Anot_BD_Campo(nome = "tel_2", set = "setTel_2", get = "getTel_2")
private String tel_2;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;





public int getId_credor() {	return id_credor;}
public void setId_credor(int id_credor) {	this.id_credor = id_credor;}

public String getNome_razao() {	return nome_razao;}
public void setNome_razao(String nome_razao) {	this.nome_razao = nome_razao;}

public String getCpf_cnpj() {	return cpf_cnpj;}
public void setCpf_cnpj(String cpf_cnpj) {	this.cpf_cnpj = cpf_cnpj;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}

public String getTel_1() {	return tel_1;}
public void setTel_1(String tel_1) {	this.tel_1 = tel_1;}

public String getTel_2() {	return tel_2;}
public void setTel_2(String tel_2) {	this.tel_2 = tel_2;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}
	
	



	
		
}
