package classes.estoque.beans;

import java.util.Date;

import classes.componentes.anotacoes.Anot_ParametrosDePesquisa;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="fornecedores", prefixo="forn", join="left outer join fornecedor_x_produto as fornXprod on fornXprod.fk_fornecedor=forn.id_fornecedor")
public class FornecedorComPreco {

@Anot_BD_Campo(nome = "id_fornecedor", tipo=int.class, set = "setId_fornecedor", get = "getId_fornecedor", ehId=true)		
private int id_fornecedor;

@Anot_ParametrosDePesquisa(pesquisaComoNumero = true, pesquisaComoString=true)
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=80)
@Anot_TB_Coluna(posicao=0, rotulo="Nome", comprimento = 80)
@Anot_BD_Campo(nome = "nome_fantasia_apelido", set = "setNome_fantasia_apelido", get = "getNome_fantasia_apelido")
private String nome_fantasia_apelido;


@Anot_ParametrosDePesquisa(pesquisaComoNumero = true, pesquisaComoString=true)
@Anot_BD_Campo(nome = "nome_razao", set = "setNome_razao", get = "getNome_razao")
private String nome_razao;

@Anot_TB_Coluna(posicao=1, rotulo="Tipo", comprimento = 10)
@Anot_BD_Campo(nome = "tipo", set = "setTipo", get = "getTipo")
private String tipo;

@Anot_ParametrosDePesquisa(pesquisaComoNumero = true, pesquisaComoString=true)
@Anot_BD_Campo(nome = "cpf_cnpj", set = "setCpf_cnpj", get = "getCpf_cnpj")
private String cpf_cnpj;

@Anot_BD_Campo(nome = "nome_responsavel", set = "setNome_responsavel", get = "getNome_responsavel")
private String nome_responsavel;

@Anot_BD_Campo(nome = "tel_1", set = "setTel_1", get = "getTel_1")
private String tel_1;


@Anot_BD_Campo(nome = "fk_grupo", tipo=int.class, set = "setFk_grupo", get = "getFk_grupo")
private int fk_grupo;

@Anot_BD_Campo(nome = "fk_endereco", tipo=int.class, set = "setFk_endereco", get = "getFk_endereco")
private int fk_endereco;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=20)
@Anot_TB_Coluna(posicao=1, rotulo="Preço (R$)", comprimento = 20)
@Anot_BD_Campo(nome = "preco", set = "setPreco", get = "getPreco", prefixo="fornXprod", select_apenas=true)
private String preco;






@Anot_BD_Campo(nome = "tel_2", set = "setTel_2", get = "getTel_2")
private String tel_2;

@Anot_BD_Campo(nome = "fax", set = "setFax", get = "getFax")
private String fax;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;


@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "rg_ie", set = "setRg_ie", get = "getRg_ie")
private String rg_ie;

@Anot_BD_Campo(nome = "ativo", set = "setAtivo", get = "getAtivo")
private String ativo;




public int getId_fornecedor() {	return id_fornecedor;}
public void setId_fornecedor(int id_fornecedor) {	this.id_fornecedor = id_fornecedor;}

public int getFk_grupo() {	return fk_grupo;}
public void setFk_grupo(int fk_grupo) {	this.fk_grupo = fk_grupo;}

public int getFk_endereco() {	return fk_endereco;}
public void setFk_endereco(int fk_endereco) {	this.fk_endereco = fk_endereco;}

public String getNome_razao() {	return nome_razao;}
public void setNome_razao(String nome_razao) {	this.nome_razao = nome_razao;}

public String getCpf_cnpj() {	return cpf_cnpj;}
public void setCpf_cnpj(String cpf_cnpj) {	this.cpf_cnpj = cpf_cnpj;}

public String getTipo() {	return tipo;}
public void setTipo(String tipo) {	this.tipo = tipo;}

public String getNome_fantasia_apelido() {	return nome_fantasia_apelido;}
public void setNome_fantasia_apelido(String nome_fantasia_apelido) {	this.nome_fantasia_apelido = nome_fantasia_apelido;}

public String getTel_1() {	return tel_1;}
public void setTel_1(String tel_1) {	this.tel_1 = tel_1;}

public String getTel_2() {	return tel_2;}
public void setTel_2(String tel_2) {	this.tel_2 = tel_2;}

public String getFax() {	return fax;}
public void setFax(String fax) {	this.fax = fax;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public String getNome_responsavel() {	return nome_responsavel;}
public void setNome_responsavel(String nome_responsavel) {	this.nome_responsavel = nome_responsavel;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getRg_ie() {	return rg_ie;}
public void setRg_ie(String rg_ie) {	this.rg_ie = rg_ie;}

public String getAtivo() {	return ativo;}
public void setAtivo(String ativo) {	this.ativo = ativo;}

public String getPreco() {	return preco;}
public void setPreco(String preco) {	this.preco = preco;}





	
}
