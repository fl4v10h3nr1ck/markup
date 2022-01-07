package classes.compras.relatorios;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;

import classes.componentes.beans.Item;
import classes.componentes.impressao.Grupo;
import classes.componentes.impressao.RelatorioBase;
import classes.componentes.impressao.util.Util;
import classes.compras.beans.Compra;
import classes.compras.beans.ItemDeCompra;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.comuns.Configuracoes;
import classes.comuns.Data;
import classes.dao.DAO;
import classes.estoque.beans.Fornecedor;
import classes.estoque.beans.Produto;
import classes.financeiro.PainelDeFormasDePagamento;
import classes.financeiro.beans.Entrada_Saida;








public class Rel__Compra extends RelatorioBase{
	
	
		
public Font fonte_micro;
public Font fonte_normal_negrito;
public Font fonte_titulo;	
	


public Compra compra;

	


	
	public Rel__Compra(Compra compra){
		
	super("Lista de Compra");	
	
	this.compra = compra;
	
	this.fonte_micro = Configuracoes.preferencias.rel_fonte_micro;
	
	this.fonte_normal_negrito = Configuracoes.preferencias.rel_fonte_normal_negrito;
	
	this.fonte_titulo = Configuracoes.preferencias.rel_fonte_titulo;
	
	this.preparaParaReceberRelatorio();
	
	this.gerar();
	}
	
	
	
	
	
	
	
	
	public void gerar(){
	
	Grupo grupo = new Grupo();	
	
	grupo.novaLinha();
	grupo.setAlturaDeLinha(12);
	grupo.addTexto("FORMAS DE PAGAMENTO", 0, this.fonte_titulo);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(4);
	grupo.addTexto("", 20, fonte_normal_negrito);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(0, 54, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Código", 10, fonte_normal_negrito);
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(54, 180, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Forma de Pagamento", 94, fonte_normal_negrito);
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(234, 80, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Tipo", 264, fonte_normal_negrito);
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(314, 80, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Num. Parc.", 328, fonte_normal_negrito);
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(394, 80, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Val. Parc. (R$)", 402, fonte_normal_negrito);
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(474, 80, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("total (R$)", 492, fonte_normal_negrito);
	
	
	Entrada_Saida dao_ent_sai = new DAO<Entrada_Saida>(Entrada_Saida.class).get(this.compra.getFk_pagamento());	
	
	List<Item> lista_formas = PainelDeFormasDePagamento.getFormasDePagamentos(dao_ent_sai);
	
		for(Item item: lista_formas){
			
		grupo.novaLinha();
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(Comuns.addPaddingAEsquerda(item.getValor("id_forma_pagamento"), 8, "0"), 5);
		grupo.addTexto(item.getValor("nome_forma_pagamento"), 59);
		grupo.addTexto("PARCELADO", 242);
		grupo.addTexto(Comuns.addPaddingAEsquerda(item.getValor("num_parcelas"), 2, "0"), 348);
		grupo.addTexto(Calculo.formataValor(item.getValor("valor_parcela")), 415);
		grupo.addTexto(Calculo.formataValor(item.getValor("valor")), 495);
		
		grupo.novaLinha();
		grupo.setAlturaDeLinha(4);
		grupo.addLinhaHorizontal(0, this, lista_formas.indexOf(item)< (lista_formas.size()-1)?new Color(220, 220, 220):Color.red);
		}
	
	grupo.novaLinha();
	grupo.setAlturaDeLinha(14);
	grupo.addTexto("", 20, fonte_normal_negrito);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(12);
	grupo.addTexto("PRODUTOS", 0, this.fonte_titulo);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(4);
	grupo.addTexto("", 20, fonte_normal_negrito);
	
	grupo.novaLinha();
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(0, 50, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Código", 8, fonte_normal_negrito);
		
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(50, 80, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("EAN", 80, fonte_normal_negrito);
			
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(130, 170, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Produto", 198, fonte_normal_negrito);
		
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(300, 140, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Fornecedor",345, fonte_normal_negrito);
		
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(440, 50, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("QTDe", 450, fonte_normal_negrito);
		
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(490, 63, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Preço (R$)", 496, fonte_normal_negrito);
			
		
	List<ItemDeCompra> lista_produtos = new DAO<ItemDeCompra>(ItemDeCompra.class).get(null, "it_comp.fk_compra="+this.compra.getId_compra(), null);
	
	DAO<Produto> dao_prod =new DAO<Produto>(Produto.class);
	DAO<Fornecedor> dao_forn =new DAO<Fornecedor>(Fornecedor.class);
	
		for(ItemDeCompra aux: lista_produtos){
			
		Produto produto = dao_prod.get(aux.getFk_produto());
		
			if(produto!=null){

			Fornecedor fornecedor = dao_forn.get(aux.getFk_fornecedor());
					
			grupo.novaLinha();
			grupo.setAlturaDeLinha(14);
			grupo.addTexto(Comuns.addPaddingAEsquerda(""+produto.getId_produto(), 8, "0"), 3);
			
			grupo.setAlturaDeLinha(14);
			grupo.addTexto(produto.getCodigo_ean()!=null?produto.getCodigo_ean():"", 53);
			
			grupo.setAlturaDeLinha(14);
			grupo.addTexto(produto.getDescricao_abreviada(), 133);
			
			grupo.setAlturaDeLinha(14);
			grupo.addTexto(fornecedor!=null?fornecedor.getNome_fantasia_apelido():"", 305);
			
			grupo.setAlturaDeLinha(14);
			grupo.addTexto(Comuns.addPaddingAEsquerda(""+aux.getQuantidade(), 4, "0"), 454);
			
			grupo.setAlturaDeLinha(14);
			grupo.addTexto(Calculo.formataValor(aux.getPreco()), 505);
			
			grupo.novaLinha();
			grupo.setAlturaDeLinha(4);
			grupo.addLinhaHorizontal(0, this, lista_produtos.indexOf(aux)< (lista_produtos.size()-1)?Configuracoes.preferencias.rel_cor_linha_separadora_tabela:Color.red);		
			}	
		}
	
	this.add(grupo);
	}
	
	
	

	
	
	
	@Override
	public Grupo cabecalho() {
		
		
	Grupo grupo = new Grupo();			
		
		
	BufferedImage logo = this.getLogo();
		if(logo!=null){
			
				
		grupo.setAlturaDeLinha(logo.getHeight()+10);
		grupo.addImagem(logo, Util.getPosicaoXCentralizado(this, logo));
		grupo.novaLinha();	
		}
		
	grupo.setAlturaDeLinha(12);
	grupo.addTexto("LISTA DE COMPRAS", Util.getPosicaoXCentralizado(this, this.fonte_titulo, "LISTA DE COMPRAS"), this.fonte_titulo);
	grupo.addTexto("Gerado em:", 435, this.fonte_normal_negrito);
	grupo.addTexto(Data.getDataAtual("/"), 500);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(4);
	grupo.addLinhaHorizontal(0, this);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(14);
	grupo.addTexto("Compra:", 0, this.fonte_normal_negrito);
	grupo.addTexto(Comuns.addPaddingAEsquerda(""+this.compra.getId_compra(), 8, "0"), 45);
	grupo.addTexto("Status:", 100, this.fonte_normal_negrito);
	grupo.addTexto(this.compra.getStatus(), 139);
	grupo.addTexto("Fechamento:", 199, this.fonte_normal_negrito);
	grupo.addTexto(Data.converteDataParaString(this.compra.getData_compra()), 265);
	grupo.addTexto("Entrega:", 327, this.fonte_normal_negrito);
	grupo.addTexto(this.compra.getStatus_entrega(), 373);
	grupo.addTexto("Data Entrega:", 437, this.fonte_normal_negrito);
	grupo.addTexto(Data.converteDataParaString(this.compra.getData_entrega()), 505);
	
	grupo.novaLinha();
	grupo.addTexto("Valor Total (R$):", 0, this.fonte_normal_negrito);
	grupo.addTexto(Calculo.formataValor(this.compra.getValor_total()), 84);
	grupo.addTexto("QTDe Total:", 150, this.fonte_normal_negrito);
	grupo.addTexto(""+Comuns.addPaddingAEsquerda(""+this.compra.getQuant_total(), 5, "0"), 214);
	grupo.novaLinha();
	grupo.setAlturaDeLinha(4);
	grupo.addLinhaHorizontal(0, this);
	
		
	return grupo;		
	}

		
	
	
	
	
		
	@Override
	public Grupo rodape() {
		
		
	Grupo grupo = new Grupo();		
	
	grupo.novaLinha();
	grupo.setAlturaDeLinha(4);
	grupo.addLinhaHorizontal(0, this);
	
	this.getInfosDaEmpresa(grupo);
	
	return grupo;		
	}

	
	
	
	
	
}
