package classes.estoque.beans;

import java.util.Date;

import classes.componentes.anotacoes.Anot_ParametrosDePesquisa;
import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;




@Anot_BD_Tabela(nome="produtos", prefixo="prod")
public class Produto {

	
@Anot_ParametrosDePesquisa(pesquisaComoNumero=true)	
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=12)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)
@Anot_BD_Campo(nome = "id_produto", tipo=int.class, set = "setId_produto", get = "getId_produto", ehId=true)			
private int id_produto;	

@Anot_BD_Campo(nome = "fk_categoria", tipo=int.class, set = "setFk_categoria", get = "getFk_categoria")			
private int fk_categoria;

@Anot_BD_Campo(nome = "fk_sub_categoria", tipo=int.class, set = "setFk_sub_categoria", get = "getFk_sub_categoria")			
private int fk_sub_categoria;

@Anot_ParametrosDePesquisa(pesquisaComoString=true)	
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")			
private String nome;

@Anot_ParametrosDePesquisa(pesquisaComoString=true)
@Anot_TB_Coluna_Selecao(posicao=2, comprimento=70)
@Anot_TB_Coluna(posicao=1, rotulo="Produto", comprimento = 35)
@Anot_BD_Campo(nome = "descricao_abreviada", set = "setDescricao_abreviada", get = "getDescricao_abreviada")			
private String descricao_abreviada;

@Anot_ParametrosDePesquisa(pesquisaComoString=true)
@Anot_TB_Coluna(posicao=4, rotulo="Marca", comprimento = 20)	
@Anot_BD_Campo(nome = "marca", set = "setMarca", get = "getMarca")			
private String marca;

@Anot_BD_Campo(nome = "especificacao", set = "setEspecificacao", get = "getEspecificacao")			
private String especificacao;

@Anot_BD_Campo(nome = "secao", set = "setSecao", get = "getSecao")			
private String secao;

@Anot_BD_Campo(nome = "cod_ncm", set = "setCod_ncm", get = "getCod_ncm")			
private String cod_ncm;	

@Anot_BD_Campo(nome = "codigo_ean", set = "setCodigo_ean", get = "getCodigo_ean")			
private String codigo_ean;	

@Anot_BD_Campo(nome = "codigo_cfop", set = "setCodigo_cfop", get = "getCodigo_cfop")
private String codigo_cfop;

@Anot_BD_Campo(nome = "unidade_de_medida", set = "setUnidade_de_medida", get = "getUnidade_de_medida")
private String unidade_de_medida;

@Anot_BD_Campo(nome = "medida", set = "setMedida", get = "getMedida")			
private String medida;

@Anot_BD_Campo(nome = "garantia_da_loja_em_dias", tipo=int.class, set = "setGarantia_da_loja_em_dias", get = "getGarantia_da_loja_em_dias")			
private int garantia_da_loja_em_dias;



@Anot_BD_Campo(nome = "unidades_pacote", tipo=int.class, set = "setUnidades_pacote", get = "getUnidades_pacote")			
private int unidades_pacote;

@Anot_BD_Campo(nome = "venda_fracionada", set = "setVenda_fracionada", get = "getVenda_fracionada")			
private String venda_fracionada;

@Anot_BD_Campo(nome = "quant_min_estoque", tipo=int.class, set = "setQuant_min_estoque", get = "getQuant_min_estoque")			
private int quant_min_estoque;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "ativo", set = "setAtivo", get = "getAtivo")
private String ativo;

@Anot_TB_Coluna(posicao=3, rotulo="QTDe. Estoque", comprimento = 15)
@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade", getTab = "getQuantidadeTab")			
private int quantidade;

@Anot_BD_Campo(nome = "valor_fracionado", set = "setValor_fracionado", get = "getValor_fracionado")			
private String valor_fracionado;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=18)
@Anot_TB_Coluna(posicao=2, rotulo="Preço (R$)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_final", set = "setValor_final", get = "getValor_final")
private String valor_final;

@Anot_BD_Campo(nome = "lucro_porcento", set = "setLucro_porcento", get = "getLucro_porcento")
private String lucro_porcento;

@Anot_BD_Campo(nome = "valor_custo_medio", set = "setValor_custo_medio", get = "getValor_custo_medio")
private String valor_custo_medio;

@Anot_BD_Campo(nome = "img", set = "setImg", get = "getImg")
private String img;

@Anot_BD_Campo(nome = "usa_balanca", set = "setUsa_balanca", get = "getUsa_balanca")
private String usa_balanca;

@Anot_BD_Campo(nome = "codigo_balanca", set = "setCodigo_balanca", get = "getCodigo_balanca")
private String codigo_balanca;

@Anot_BD_Campo(nome = "modelo_etiqueta", set = "setModelo_etiqueta", get = "getModelo_etiqueta")
private String modelo_etiqueta;




public String getCodigo_ean() {	return codigo_ean;}
public void setCodigo_ean(String codigo_ean) {	this.codigo_ean = codigo_ean;}

public String getNome() {	return nome;}
public void setNome(String nome) {	this.nome = nome;}

public String getDescricao_abreviada() {	return descricao_abreviada;}
public void setDescricao_abreviada(String descricao_abreviada) {	this.descricao_abreviada = descricao_abreviada;}

public int getUnidades_pacote() {	return unidades_pacote;}
public void setUnidades_pacote(int unidades_pacote) {	this.unidades_pacote = unidades_pacote;}

public String getVenda_fracionada() {	return venda_fracionada;}
public void setVenda_fracionada(String venda_fracionada) {	this.venda_fracionada = venda_fracionada;}

public String getValor_fracionado() {	return valor_fracionado;}
public void setValor_fracionado(String valor_fracionado) {	this.valor_fracionado = valor_fracionado;}

public String getValor_final() {	return valor_final;}
public void setValor_final(String valor_final) {	this.valor_final = valor_final;}

public String getUnidade_de_medida() {	return unidade_de_medida;}
public void setUnidade_de_medida(String unidade_de_medida) {	this.unidade_de_medida = unidade_de_medida;}

public int getId_produto() {	return id_produto;}
public void setId_produto(int id_produto) {	this.id_produto = id_produto;}

public int getQuantidade() {	return quantidade;}
public void setQuantidade(int quantidade) {	this.quantidade = quantidade;}
public String getQuantidadeTab() { return "<html><font color="+(this.quantidade< this.getQuant_min_estoque()?"red>":"green>")+this.quantidade+"</font></html>";}

public int getFk_categoria() {	return fk_categoria;}
public void setFk_categoria(int fk_categoria) {	this.fk_categoria = fk_categoria;}

public int getFk_sub_categoria() {	return fk_sub_categoria;}
public void setFk_sub_categoria(int fk_sub_categoria) {	this.fk_sub_categoria = fk_sub_categoria;}

public String getMarca() {	return marca;}
public void setMarca(String marca) {	this.marca = marca;}

public String getEspecificacao() {	return especificacao;}
public void setEspecificacao(String especificacao) {	this.especificacao = especificacao;}

public String getCod_ncm() {	return cod_ncm;}
public void setCod_ncm(String cod_ncm) {	this.cod_ncm = cod_ncm;}

public String getSecao() {	return secao;}
public void setSecao(String secao) {	this.secao = secao;}

public int getGarantia_da_loja_em_dias() {	return garantia_da_loja_em_dias;}
public void setGarantia_da_loja_em_dias(int garantia_da_loja_em_dias) {	this.garantia_da_loja_em_dias = garantia_da_loja_em_dias;}

public int getQuant_min_estoque() {	return quant_min_estoque;}
public void setQuant_min_estoque(int quant_min_estoque) {	this.quant_min_estoque = quant_min_estoque;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getCodigo_cfop() {	return codigo_cfop;}
public void setCodigo_cfop(String codigo_cfop) {	this.codigo_cfop = codigo_cfop;}

public String getAtivo() {	return ativo;}
public void setAtivo(String ativo) {	this.ativo = ativo;}

public String getLucro_porcento() {	return lucro_porcento;}
public void setLucro_porcento(String lucro_porcento) {	this.lucro_porcento = lucro_porcento;}

public String getValor_custo_medio() {	return valor_custo_medio;}
public void setValor_custo_medio(String valor_custo_medio) {	this.valor_custo_medio = valor_custo_medio;}

public String getImg() {	return img;}
public void setImg(String img) {	this.img = img;}

public String getMedida() {	return medida;}
public void setMedida(String medida) {	this.medida = medida;}

public String getUsa_balanca() {	return usa_balanca;}
public void setUsa_balanca(String usa_balanca) {	this.usa_balanca = usa_balanca;}

public String getCodigo_balanca() {	return codigo_balanca;}
public void setCodigo_balanca(String codigo_balanca) {	this.codigo_balanca = codigo_balanca;}

public String getModelo_etiqueta() {	return modelo_etiqueta;}
public void setModelo_etiqueta(String modelo_etiqueta) {	this.modelo_etiqueta = modelo_etiqueta;}

	
	
	
}
