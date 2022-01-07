package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;


@Anot_BD_Tabela(nome="colaboradores", prefixo="cola")
public class Colaborador {

	
@Anot_BD_Campo(nome = "id_colaborador", tipo=int.class, set = "setId_colaborador", get = "getId_colaborador", ehId=true)	
private int id_colaborador;

@Anot_TB_Coluna(posicao=0, rotulo="Nome", comprimento = 60)
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")	
private String nome;

@Anot_BD_Campo(nome = "cpf", set = "setCpf", get = "getCpf")
private String cpf;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;

@Anot_TB_Coluna(posicao=1, rotulo="TEL (interno)", comprimento = 20)
@Anot_BD_Campo(nome = "tel_interno", set = "setTel_interno", get = "getTel_interno")
private String tel_interno;

@Anot_TB_Coluna(posicao=2, rotulo="TEL (pessoal)", comprimento = 20)
@Anot_BD_Campo(nome = "tel_pessoal", set = "setTel_pessoal", get = "getTel_pessoal")
private String tel_pessoal;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "valor_comissao", set = "setValor_comissao", get = "getValor_comissao")
private String valor_comissao;

@Anot_BD_Campo(nome = "fk_cargo", tipo=int.class, set = "setFk_cargo", get = "getFk_cargo")	
private int fk_cargo;

@Anot_BD_Campo(nome = "fk_usuario", tipo=int.class, set = "setFk_usuario", get = "getFk_usuario")	
private int fk_usuario;




public int getId_colaborador() {	return id_colaborador;}
public void setId_colaborador(int id_colaborador) {	this.id_colaborador = id_colaborador;}

public String getNome() {	return nome;}
public void setNome(String nome) {	this.nome = nome;}

public String getCpf() {	return cpf;}
public void setCpf(String cpf) {	this.cpf = cpf;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public String getTel_interno() {	return tel_interno;}
public void setTel_interno(String tel_interno) {	this.tel_interno = tel_interno;}

public String getTel_pessoal() {	return tel_pessoal;}
public void setTel_pessoal(String tel_pessoal) {	this.tel_pessoal = tel_pessoal;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public String getValor_comissao() {	return valor_comissao;}
public void setValor_comissao(String valor_comissao) {	this.valor_comissao = valor_comissao;}

public int getFk_cargo() {	return fk_cargo;}
public void setFk_cargo(int fk_cargo) {	this.fk_cargo = fk_cargo;}

public int getFk_usuario() {	return fk_usuario;}
public void setFk_usuario(int fk_usuario) {	this.fk_usuario = fk_usuario;}









}
