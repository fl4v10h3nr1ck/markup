package componentes.beans;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;


@Anot_BD_Tabela(nome="unidade_empresarial", prefixo="uni_emp")
public class Empresa {


@Anot_BD_Campo(nome = "id_unidade", tipo=int.class, set = "setId_unidade", get = "getId_unidade", ehId=true)
private int id_unidade;

@Anot_BD_Campo(nome = "codigo", set = "setCodigo", get = "getCodigo")	
private String codigo;

@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")
private String nome;

@Anot_BD_Campo(nome = "subtitulo", set = "setSubtitulo", get = "getSubtitulo")
private String subtitulo;

@Anot_BD_Campo(nome = "nome_completo", set = "setNome_completo", get = "getNome_completo")
private String nome_completo;

@Anot_BD_Campo(nome = "tel_1", set = "setTel_1", get = "getTel_1")
private String tel_1;

@Anot_BD_Campo(nome = "tel_2", set = "setTel_2", get = "getTel_2")
private String tel_2;

@Anot_BD_Campo(nome = "site", set = "setSite", get = "getSite")
private String site;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "pais_padrao", set = "setPais_padrao", get = "getPais_padrao")
private String pais_padrao;

@Anot_BD_Campo(nome = "itens_por_pagina", tipo=int.class, set = "setItens_por_pagina", get = "getItens_por_pagina")
private int itens_por_pagina;

@Anot_BD_Campo(nome = "saldo_inicial_caixa", set = "setSaldo_inicial_caixa", get = "getSaldo_inicial_caixa")
private String saldo_inicial_caixa;

@Anot_BD_Campo(nome = "desconto_padrao", set = "setDesconto_padrao", get = "getDesconto_padrao")
private String desconto_padrao;

@Anot_BD_Campo(nome = "porcento_lucro_produto", set = "setPorcento_lucro_produto", get = "getPorcento_lucro_produto")
private String porcento_lucro_produto;

@Anot_BD_Campo(nome = "quant_min_estoque", tipo=int.class, set = "setQuant_min_estoque", get = "getQuant_min_estoque")
private int quant_min_estoque;

@Anot_BD_Campo(nome = "quant_min_inventario", tipo=int.class, set = "setQuant_min_inventario", get = "getQuant_min_inventario")
private int quant_min_inventario;

@Anot_BD_Campo(nome = "porcento_multa_padrao", set = "setPorcento_multa_padrao", get = "getPorcento_multa_padrao")
private String porcento_multa_padrao;

@Anot_BD_Campo(nome = "porcento_juros_ao_mes_padrao", set = "setPorcento_juros_ao_mes_padrao", get = "getPorcento_juros_ao_mes_padrao")
private String porcento_juros_ao_mes_padrao;

@Anot_BD_Campo(nome = "num_dias_mes_comercial", tipo=int.class, set = "setNum_dias_mes_comercial", get = "getNum_dias_mes_comercial")
private int num_dias_mes_comercial;

@Anot_BD_Campo(nome = "porcento_comissao_padrao", set = "setPorcento_comissao_padrao", get = "getPorcento_comissao_padrao")
private String porcento_comissao_padrao;

@Anot_BD_Campo(nome = "fk_endereco", tipo=int.class, set = "setFk_endereco", get = "getFk_endereco")
private int fk_endereco;

@Anot_BD_Campo(nome = "cnpj", set = "setCnpj", get = "getCnpj")
private String cnpj;

@Anot_BD_Campo(nome = "cnae", set = "setCnae", get = "getCnae")
private String cnae;

@Anot_BD_Campo(nome = "inscricao_estadual", set = "setInscricao_estadual", get = "getInscricao_estadual")
private String inscricao_estadual;

@Anot_BD_Campo(nome = "inscricao_municipal", set = "setInscricao_municipal", get = "getInscricao_municipal")
private String inscricao_municipal;

@Anot_BD_Campo(nome = "nome_fantasia", set = "setNome_fantasia", get = "getNome_fantasia")
private String nome_fantasia;

@Anot_BD_Campo(nome = "codigo_municipio_padrao", tipo=int.class, set = "setCodigo_municipio_padrao", get = "getCodigo_municipio_padrao")
private int codigo_municipio_padrao;

@Anot_BD_Campo(nome = "frase_lema", set = "setFrase_lema", get = "getFrase_lema")
private String frase_lema;




public int getId_unidade() {	return id_unidade;}
public void setId_unidade(int id_unidade) {	this.id_unidade = id_unidade;}

public String getCodigo() {	return codigo;}
public void setCodigo(String codigo) {	this.codigo = codigo;}

public String getNome() {	return nome;}
public void setNome(String nome) {	this.nome = nome;}

public String getSubtitulo() {	return subtitulo;}
public void setSubtitulo(String subtitulo) {	this.subtitulo = subtitulo;}

public String getNome_completo() {	return nome_completo;}
public void setNome_completo(String nome_completo) {	this.nome_completo = nome_completo;}

public String getTel_1() {	return tel_1;}
public void setTel_1(String tel_1) {	this.tel_1 = tel_1;}

public String getTel_2() {	return tel_2;}
public void setTel_2(String tel_2) {	this.tel_2 = tel_2;}

public String getSite() {	return site;}
public void setSite(String site) {	this.site = site;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public String getPais_padrao() {	return pais_padrao;}
public void setPais_padrao(String pais_padrao) {	this.pais_padrao = pais_padrao;}

public int getItens_por_pagina() {	return itens_por_pagina;}
public void setItens_por_pagina(int itens_por_pagina) {	this.itens_por_pagina = itens_por_pagina;}

public String getSaldo_inicial_caixa() {	return saldo_inicial_caixa;}
public void setSaldo_inicial_caixa(String saldo_inicial_caixa) {	this.saldo_inicial_caixa = saldo_inicial_caixa;}

public String getDesconto_padrao() {	return desconto_padrao;}
public void setDesconto_padrao(String desconto_padrao) {	this.desconto_padrao = desconto_padrao;}

public String getPorcento_lucro_produto() {	return porcento_lucro_produto;}
public void setPorcento_lucro_produto(String porcento_lucro_produto) {	this.porcento_lucro_produto = porcento_lucro_produto;}

public int getQuant_min_estoque() {	return quant_min_estoque;}
public void setQuant_min_estoque(int quant_min_estoque) {	this.quant_min_estoque = quant_min_estoque;}

public int getQuant_min_inventario() {	return quant_min_inventario;}
public void setQuant_min_inventario(int quant_min_inventario) {	this.quant_min_inventario = quant_min_inventario;}

public String getPorcento_multa_padrao() {	return porcento_multa_padrao;}
public void setPorcento_multa_padrao(String porcento_multa_padrao) {	this.porcento_multa_padrao = porcento_multa_padrao;}

public String getPorcento_juros_ao_mes_padrao() {	return porcento_juros_ao_mes_padrao;}
public void setPorcento_juros_ao_mes_padrao(String porcento_juros_ao_mes_padrao) {	this.porcento_juros_ao_mes_padrao = porcento_juros_ao_mes_padrao;}

public int getNum_dias_mes_comercial() {	return num_dias_mes_comercial;}
public void setNum_dias_mes_comercial(int num_dias_mes_comercial) {	this.num_dias_mes_comercial = num_dias_mes_comercial;}

public String getPorcento_comissao_padrao() {	return porcento_comissao_padrao;}
public void setPorcento_comissao_padrao(String porcento_comissao_padrao) {	this.porcento_comissao_padrao = porcento_comissao_padrao;}

public int getFk_endereco() {	return fk_endereco;}
public void setFk_endereco(int fk_endereco) {	this.fk_endereco = fk_endereco;}

public String getCnpj() {	return cnpj;}
public void setCnpj(String cnpj) {	this.cnpj = cnpj;}

public String getCnae() {	return cnae;}
public void setCnae(String cnae) {	this.cnae = cnae;}

public String getInscricao_estadual() {	return inscricao_estadual;}
public void setInscricao_estadual(String inscricao_estadual) {	this.inscricao_estadual = inscricao_estadual;}

public String getInscricao_municipal() {	return inscricao_municipal;}
public void setInscricao_municipal(String inscricao_municipal) {	this.inscricao_municipal = inscricao_municipal;}

public String getNome_fantasia() {	return nome_fantasia;}
public void setNome_fantasia(String nome_fantasia) {	this.nome_fantasia = nome_fantasia;}

public int getCodigo_municipio_padrao() {	return codigo_municipio_padrao;}
public void setCodigo_municipio_padrao(int codigo_municipio_padrao) {	this.codigo_municipio_padrao = codigo_municipio_padrao;}

public String getFrase_lema() {	return frase_lema;}
public void setFrase_lema(String frase_lema) {	this.frase_lema = frase_lema;}





	
	
	
	
}
