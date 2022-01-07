package classes.principal.empresa;


import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;



@Anot_BD_Tabela(nome="empresa", prefixo="emp")
public class Empresa {


@Anot_BD_Campo(nome = "id_empresa", tipo=int.class, set = "setId_empresa", get = "getId_empresa", ehId=true)			
private int id_empresa;

@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")			
private String codigo;

@Anot_BD_Campo(nome = "razao_social", set = "setRazao_social", get = "getRazao_social")
private String razao_social;

@Anot_BD_Campo(nome = "nome_fantasia", set = "setNome_fantasia", get = "getNome_fantasia")
private String nome_fantasia;

@Anot_BD_Campo(nome = "tel_1", set = "setTel_1", get = "getTel_1")
private String tel_1;

@Anot_BD_Campo(nome = "tel_2", set = "setTel_2", get = "getTel_2")
private String tel_2;

@Anot_BD_Campo(nome = "site", set = "setSite", get = "getSite")
private String site;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "fk_endereco", tipo=int.class, set = "setFk_endereco", get = "getFk_endereco")
private int fk_endereco;

@Anot_BD_Campo(nome = "frase_lema", set = "setFrase_lema", get = "getFrase_lema")
private String frase_lema;

@Anot_BD_Campo(nome = "cnpj", set = "setCnpj", get = "getCnpj")
private String cnpj;

@Anot_BD_Campo(nome = "cnae", set = "setCnae", get = "getCnae")
private String cnae;

@Anot_BD_Campo(nome = "inscricao_estadual", set = "setInscricao_estadual", get = "getInscricao_estadual")
private String inscricao_estadual;

@Anot_BD_Campo(nome = "inscricao_municipal", set = "setInscricao_municipal", get = "getInscricao_municipal")
private String inscricao_municipal;








public int getId_empresa() {	return id_empresa;}
public void setId_empresa(int id_empresa) {	this.id_empresa = id_empresa;}

public String getTel_1() {	return tel_1;}
public void setTel_1(String tel_1) {	this.tel_1 = tel_1;}

public String getTel_2() {	return tel_2;}
public void setTel_2(String tel_2) {	this.tel_2 = tel_2;}

public String getSite() {	return site;}
public void setSite(String site) {	this.site = site;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public String getCnpj() {	return cnpj;}
public void setCnpj(String cnpj) {	this.cnpj = cnpj;}

public int getFk_endereco() {	return fk_endereco;}
public void setFk_endereco(int fk_endereco) {	this.fk_endereco = fk_endereco;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}

public String getCnae() {return cnae;}
public void setCnae(String cnae) {	this.cnae = cnae;}

public String getInscricao_estadual() {	return inscricao_estadual;}
public void setInscricao_estadual(String inscricao_estadual) {	this.inscricao_estadual = inscricao_estadual;}

public String getInscricao_municipal() {	return inscricao_municipal;}
public void setInscricao_municipal(String inscricao_municipal) {	this.inscricao_municipal = inscricao_municipal;}

public String getNome_fantasia() {	return nome_fantasia;}
public void setNome_fantasia(String nome_fantasia) {	this.nome_fantasia = nome_fantasia;}

public String getRazao_social() {	return razao_social;}
public void setRazao_social(String razao_social) {	this.razao_social = razao_social;}

public String getFrase_lema() {	return frase_lema;}
public void setFrase_lema(String frase_lema) {	this.frase_lema = frase_lema;}
	
	
	
	
}
