package classes.estoque.relatorios.estoque_baixo;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;

import classes.componentes.impressao.Grupo;
import classes.componentes.impressao.RelatorioBase;
import classes.componentes.impressao.util.Util;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.comuns.Configuracoes;
import classes.comuns.Data;
import classes.dao.DAO;
import classes.estoque.beans.Categoria;
import classes.estoque.beans.Produto;








public class Rel__EstoqueBaixo extends RelatorioBase{
	
	
	
	
public Font fonte_micro;
public Font fonte_normal_negrito;
public Font fonte_titulo;	
	

private String orderby;




	public Rel__EstoqueBaixo(){

	this(null);
	}
		



	
	public Rel__EstoqueBaixo(String orderby){
		
	super("Relatório de Itens com Estoque Baixo");	
	
	this.fonte_micro = Configuracoes.preferencias.rel_fonte_micro;
	
	this.fonte_normal_negrito = Configuracoes.preferencias.rel_fonte_normal_negrito;
	
	this.fonte_titulo = Configuracoes.preferencias.rel_fonte_titulo;
	
	
	this.orderby  =orderby;
	
	this.preparaParaReceberRelatorio();
	
	this.gerar();
	}
	
	
	
	
	
	
	
	
	public void gerar(){
	
	Grupo grupo = new Grupo();	
	
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
	grupo.addRetangulo(130, 230, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Produto", 213, fonte_normal_negrito);
	
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(360, 65, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Custo (R$)",366, fonte_normal_negrito);
	
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(425, 65, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("QTDe Atual", 430, fonte_normal_negrito);
	
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(490, 65, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("QTDe Min.", 498, fonte_normal_negrito);
	
	
	
	List<Produto> produtos = new DAO<Produto>(Produto.class).get(null, "prod.ativo='S' and (prod.quantidade IS NULL OR prod.quantidade<prod.quant_min_estoque)", this.orderby);
	DAO<Categoria> dao_cat  = new DAO<Categoria>(Categoria.class);
	
		for(Produto produto: produtos){
		
		String categoria  = "";
		
			if(produto.getFk_categoria()>0){
			
			Categoria aux = dao_cat.get(produto.getFk_categoria()); 
			if(aux!=null)
			categoria = "("+aux.getCodigo()+") ";
			}	
			
		grupo.novaLinha();
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(Comuns.addPaddingAEsquerda(""+produto.getId_produto(), 8, "0"), 3);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(produto.getCodigo_ean()!=null?produto.getCodigo_ean():"", 53);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(categoria+produto.getDescricao_abreviada(), 133);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(Calculo.formataValor(produto.getValor_custo_medio()), 382);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(Comuns.addPaddingAEsquerda(""+produto.getQuantidade(), 3, "0"), 450);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(Comuns.addPaddingAEsquerda(""+produto.getQuant_min_estoque(), 3, "0"), 513);
		
		grupo.novaLinha();
		grupo.setAlturaDeLinha(4);
		grupo.addLinhaHorizontal(0, this, Configuracoes.preferencias.rel_cor_linha_separadora_tabela);	
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
	grupo.addTexto("ITENS COM ESTOQUE BAIXO", Util.getPosicaoXCentralizado(this, this.fonte_titulo, "ITENS COM ESTOQUE BAIXO"), this.fonte_titulo);
	grupo.addTexto("Gerado em:", 435, this.fonte_normal_negrito);
	grupo.addTexto(Data.getDataAtual("/"), 500);
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
