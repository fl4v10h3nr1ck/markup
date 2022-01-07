package componentes.beans;

import java.util.Date;

import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.anotacoes.Anot_TB_Coluna;
import componentes.anotacoes.Anot_TB_Coluna_Selecao;
import comuns.Comuns;



@Anot_BD_Tabela(nome="produtos", prefixo="prod")
public class Produto {

	
	
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=12)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)
@Anot_BD_Campo(nome = "id_produto", tipo=int.class, set = "setId_produto", get = "getId_produto", ehId=true)			
private int id_produto;	
		
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")			
private String nome;

@Anot_TB_Coluna_Selecao(posicao=2, comprimento=70)
@Anot_TB_Coluna(posicao=1, rotulo="Produto", comprimento = 35)
@Anot_BD_Campo(nome = "descricao_abreviada", set = "setDescricao_abreviada", get = "getDescricao_abreviada")			
private String descricao_abreviada;

@Anot_BD_Campo(nome = "codigo_ean", set = "setCodigo_ean", get = "getCodigo_ean")			
private String codigo_ean;	

@Anot_BD_Campo(nome = "unidades_pacote", tipo=int.class, set = "setUnidades_pacote", get = "getUnidades_pacote")			
private int unidades_pacote;

@Anot_BD_Campo(nome = "venda_fracionada", set = "setVenda_fracionada", get = "getVenda_fracionada")			
private String venda_fracionada;

@Anot_BD_Campo(nome = "tipo_venda_fracionada", set = "setTipo_venda_fracionada", get = "getTipo_venda_fracionada")			
private String tipo_venda_fracionada;

@Anot_BD_Campo(nome = "valor_fracionado", set = "setValor_fracionado", get = "getValor_fracionado")			
private String valor_fracionado;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=18)
@Anot_TB_Coluna(posicao=2, rotulo="Preço (R$)", comprimento = 15)
@Anot_BD_Campo(nome = "valor_final", set = "setValor_final", get = "getValor_final")
private String valor_final;

@Anot_BD_Campo(nome = "unidade_de_medida", set = "setUnidade_de_medida", get = "getUnidade_de_medida")
private String unidade_de_medida;

@Anot_TB_Coluna(posicao=3, rotulo="QTDe. Estoque", comprimento = 15)
@Anot_BD_Campo(nome = "quantidade", tipo=int.class, set = "setQuantidade", get = "getQuantidade", getTab = "getQuantidadeTab")			
private int quantidade;


@Anot_BD_Campo(nome = "fk_categoria", tipo=int.class, set = "setFk_categoria", get = "getFk_categoria")			
private int fk_categoria;

@Anot_BD_Campo(nome = "fk_sub_categoria", tipo=int.class, set = "setFk_sub_categoria", get = "getFk_sub_categoria")			
private int fk_sub_categoria;

@Anot_BD_Campo(nome = "descricao", set = "setDescricao", get = "getDescricao")			
private String descricao;

@Anot_TB_Coluna(posicao=4, rotulo="Marca", comprimento = 20)	
@Anot_BD_Campo(nome = "marca", set = "setMarca", get = "getMarca")			
private String marca;

@Anot_BD_Campo(nome = "especificacao", set = "setEspecificacao", get = "getEspecificacao")			
private String especificacao;

@Anot_BD_Campo(nome = "cod_ncm", set = "setCod_ncm", get = "getCod_ncm")			
private String cod_ncm;	

@Anot_BD_Campo(nome = "secao", set = "setSecao", get = "getSecao")			
private String secao;

@Anot_BD_Campo(nome = "garantia_da_loja_em_dias", tipo=int.class, set = "setGarantia_da_loja_em_dias", get = "getGarantia_da_loja_em_dias")			
private int garantia_da_loja_em_dias;

@Anot_BD_Campo(nome = "volume_m3", set = "setVolume_m3", get = "getVolume_m3")			
private String volume_m3;

@Anot_BD_Campo(nome = "peso_em_gramas", tipo=int.class, set = "setPeso_em_gramas", get = "getPeso_em_gramas")			
private int peso_em_gramas;

@Anot_BD_Campo(nome = "quant_min_estoque", tipo=int.class, set = "setQuant_min_estoque", get = "getQuant_min_estoque")			
private int quant_min_estoque;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;

@Anot_BD_Campo(nome = "lucro", set = "setLucro", get = "getLucro")
private String lucro;

@Anot_BD_Campo(nome = "valor_de_custo", set = "setValor_de_custo", get = "getValor_de_custo")			
private String valor_de_custo;

@Anot_BD_Campo(nome = "markup", set = "setMarkup", get = "getMarkup")
private String markup;

@Anot_BD_Campo(nome = "valor_icms_porcento", set = "setValor_icms_porcento", get = "getValor_icms_porcento")
private String valor_icms_porcento;

@Anot_BD_Campo(nome = "valor_ipi_porcento", set = "setValor_ipi_porcento", get = "getValor_ipi_porcento")
private String valor_ipi_porcento;

@Anot_BD_Campo(nome = "valor_pis_porcento", set = "setValor_pis_porcento", get = "getValor_pis_porcento")
private String valor_pis_porcento;

@Anot_BD_Campo(nome = "valor_cofins_porcento", set = "setValor_cofins_porcento", get = "getValor_cofins_porcento")
private String valor_cofins_porcento;

@Anot_BD_Campo(nome = "valor_csll_porcento", set = "setValor_csll_porcento", get = "getValor_csll_porcento")
private String valor_csll_porcento;

@Anot_BD_Campo(nome = "valor_irpj_porcento", set = "setValor_irpj_porcento", get = "getValor_irpj_porcento")
private String valor_irpj_porcento;

@Anot_BD_Campo(nome = "valor_comissao_porcento", set = "setValor_comissao_porcento", get = "getValor_comissao_porcento")
private String valor_comissao_porcento;

@Anot_BD_Campo(nome = "valor_administrativa_porcento", set = "setValor_administrativa_porcento", get = "getValor_administrativa_porcento")
private String valor_administrativa_porcento;

@Anot_BD_Campo(nome = "valor_issqn_porcento", set = "setValor_issqn_porcento", get = "getValor_issqn_porcento")
private String valor_issqn_porcento;

@Anot_BD_Campo(nome = "codigo_cfop", set = "setCodigo_cfop", get = "getCodigo_cfop")
private String codigo_cfop;




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

public String getTipo_venda_fracionada() {	return tipo_venda_fracionada;}
public void setTipo_venda_fracionada(String tipo_venda_fracionada) {	this.tipo_venda_fracionada = tipo_venda_fracionada;}

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
public String getQuantidadeTab() { return "<html><font color="+(this.quantidade<= Comuns.quantMinPadraoEstoque?"red>":"green>")+this.quantidade+"</font></html>";}


public int getFk_categoria() {	return fk_categoria;}
public void setFk_categoria(int fk_categoria) {	this.fk_categoria = fk_categoria;}

public int getFk_sub_categoria() {	return fk_sub_categoria;}
public void setFk_sub_categoria(int fk_sub_categoria) {	this.fk_sub_categoria = fk_sub_categoria;}

public String getDescricao() {	return descricao;}
public void setDescricao(String descricao) {	this.descricao = descricao;}

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

public String getVolume_m3() {	return volume_m3;}
public void setVolume_m3(String volume_m3) {	this.volume_m3 = volume_m3;}

public int getPeso_em_gramas() {	return peso_em_gramas;}
public void setPeso_em_gramas(int peso_em_gramas) {	this.peso_em_gramas = peso_em_gramas;}

public int getQuant_min_estoque() {	return quant_min_estoque;}
public void setQuant_min_estoque(int quant_min_estoque) {	this.quant_min_estoque = quant_min_estoque;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

public String getLucro() {	return lucro;}
public void setLucro(String lucro) {	this.lucro = lucro;}

public String getValor_de_custo() {	return valor_de_custo;}
public void setValor_de_custo(String valor_de_custo) {	this.valor_de_custo = valor_de_custo;}

public String getMarkup() {	return markup;}
public void setMarkup(String markup) {	this.markup = markup;}

public String getValor_icms_porcento() {	return valor_icms_porcento;}
public void setValor_icms_porcento(String valor_icms_porcento) {	this.valor_icms_porcento = valor_icms_porcento;}

public String getValor_ipi_porcento() {	return valor_ipi_porcento;}
public void setValor_ipi_porcento(String valor_ipi_porcento) {	this.valor_ipi_porcento = valor_ipi_porcento;}

public String getValor_pis_porcento() {	return valor_pis_porcento;}
public void setValor_pis_porcento(String valor_pis_porcento) {	this.valor_pis_porcento = valor_pis_porcento;}

public String getValor_cofins_porcento() {	return valor_cofins_porcento;}
public void setValor_cofins_porcento(String valor_cofins_porcento) {	this.valor_cofins_porcento = valor_cofins_porcento;}

public String getValor_csll_porcento() {	return valor_csll_porcento;}
public void setValor_csll_porcento(String valor_csll_porcento) {	this.valor_csll_porcento = valor_csll_porcento;}

public String getValor_irpj_porcento() {	return valor_irpj_porcento;}
public void setValor_irpj_porcento(String valor_irpj_porcento) {	this.valor_irpj_porcento = valor_irpj_porcento;}

public String getValor_comissao_porcento() {	return valor_comissao_porcento;}
public void setValor_comissao_porcento(String valor_comissao_porcento) {	this.valor_comissao_porcento = valor_comissao_porcento;}

public String getValor_administrativa_porcento() {	return valor_administrativa_porcento;}
public void setValor_administrativa_porcento(		String valor_administrativa_porcento) {	this.valor_administrativa_porcento = valor_administrativa_porcento;}

public String getValor_issqn_porcento() {	return valor_issqn_porcento;}
public void setValor_issqn_porcento(String valor_issqn_porcento) {	this.valor_issqn_porcento = valor_issqn_porcento;}

public String getCodigo_cfop() {	return codigo_cfop;}
public void setCodigo_cfop(String codigo_cfop) {	this.codigo_cfop = codigo_cfop;}
	



	
	
	
}
