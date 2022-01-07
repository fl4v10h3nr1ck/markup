package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;
import comuns.Data;


@Anot_BD_Tabela(nome="clientes", prefixo="cl")
public class Cliente {

@Anot_BD_Campo(nome = "id_cliente", tipo=int.class, set = "setId_cliente", get = "getId_cliente", ehId=true)	
private int id_cliente;

@Anot_TB_Coluna_Selecao(posicao=0, comprimento=75)
@Anot_TB_Coluna(posicao=0, rotulo="Nome/Razão Social", comprimento = 70)
@Anot_BD_Campo(nome = "nome_razao", set = "setNome_razao", get = "getNome_razao")
private String nome_razao;

@Anot_BD_Campo(nome = "nome_fantasia", set = "setNome_fantasia", get = "getNome_fantasia")
private String nome_fantasia;

@Anot_BD_Campo(nome = "sexo", set = "setSexo", get = "getSexo")
private String sexo;

@Anot_BD_Campo(nome = "nascimento", tipo=Date.class, set = "setNascimento", get = "getNascimentoBD")
private Date nascimento;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=25)
@Anot_TB_Coluna(posicao=2, rotulo="CPF/CNPJ", comprimento = 20)
@Anot_BD_Campo(nome = "cpf_cnpj", set = "setCpf_cnpj", get = "getCpf_cnpj")
private String cpf_cnpj;

@Anot_BD_Campo(nome = "tel_1", set = "setTel_1", get = "getTel_1")
private String tel_1;

@Anot_BD_Campo(nome = "tel_2", set = "setTel_2", get = "getTel_2")
private String tel_2;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro", getBD = "getData_cadastroBD", getTab = "getData_cadastroString")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;

@Anot_BD_Campo(nome = "rg_ie", set = "setRg_ie", get = "getRg_ie")
private String rg_ie;

@Anot_TB_Coluna(posicao=1, rotulo="Tipo", comprimento=10)
@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")
private String tipo;

@Anot_BD_Campo(nome = "inscricao_municipal", set = "setInscricao_municipal", get = "getInscricao_municipal")
private String inscricao_municipal;

@Anot_BD_Campo(nome = "fk_endereco", tipo=int.class, set = "setFk_endereco", get = "getFk_endereco")	
private int fk_endereco;




public String getNome_razao() {	return nome_razao;}
public void setNome_razao(String nome_razao) {	this.nome_razao = nome_razao;}

public String getNome_fantasia() {	return nome_fantasia;}
public void setNome_fantasia(String nome_fantasia) {	this.nome_fantasia = nome_fantasia;}

public String getSexo() {	return sexo;}
public void setSexo(String sexo) {	this.sexo = sexo;}

public Date getNascimento() {	return nascimento;}
public void setNascimento(Date nascimento) {	this.nascimento = nascimento;}
public java.sql.Date getNascimentoBD() {	return nascimento == null? null: new java.sql.Date(this.nascimento.getTime());}

public String getCpf_cnpj() {	return cpf_cnpj;}
public void setCpf_cnpj(String cpf_cnpj) {	this.cpf_cnpj = cpf_cnpj;}

public String getTel_1() {	return tel_1;}
public void setTel_1(String tel_1) {	this.tel_1 = tel_1;}

public String getTel_2() {	return tel_2;}
public void setTel_2(String tel_2) {	this.tel_2 = tel_2;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}
public java.sql.Date getData_cadastroBD() {	return data_cadastro == null? null: new java.sql.Date(this.data_cadastro.getTime());}
public String getData_cadastroString(){ return Data.converteDataParaString(getData_cadastro());}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}

public String getInscricao_municipal() {	return inscricao_municipal;}
public void setInscricao_municipal(String inscricao_municipal) {	this.inscricao_municipal = inscricao_municipal;}

public int getFk_endereco() {	return fk_endereco;}
public void setFk_endereco(int fk_endereco) {	this.fk_endereco = fk_endereco;}

public int getId_cliente() {	return id_cliente;}
public void setId_cliente(int id_cliente) {	this.id_cliente = id_cliente;}

public String getRg_ie() {	return rg_ie;}
public void setRg_ie(String rg_ie) {	this.rg_ie = rg_ie;}

	
	
	
}
