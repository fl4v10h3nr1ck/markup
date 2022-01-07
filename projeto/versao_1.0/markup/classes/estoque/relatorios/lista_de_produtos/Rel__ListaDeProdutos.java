package classes.estoque.relatorios.lista_de_produtos;

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








public class Rel__ListaDeProdutos extends RelatorioBase{
	
	
	
	
public Font fonte_micro;
public Font fonte_normal_negrito;
public Font fonte_titulo;	
	

private String orderby;


private int num_reg;



	public Rel__ListaDeProdutos(){

	this(null);
	}
		



	
	public Rel__ListaDeProdutos(String orderby){
		
	super("Relatório de Lista de Produtos");	
	
	this.fonte_micro = Configuracoes.preferencias.rel_fonte_micro;
	
	this.fonte_normal_negrito = Configuracoes.preferencias.rel_fonte_normal_negrito;
	
	this.fonte_titulo = Configuracoes.preferencias.rel_fonte_titulo;
	
	this.orderby  =orderby;
	
	List<Produto> produtos = new DAO<Produto>(Produto.class).get(null, "prod.ativo='S'", this.orderby);
	
	this.num_reg = produtos.size();
	
	this.preparaParaReceberRelatorio();
	
	this.gerar(produtos);
	}
	
	
	
	
	
	
	
	
	private void gerar(List<Produto> produtos){
	
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
	grupo.addRetangulo(130, 210, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Produto", 213, fonte_normal_negrito);
	
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(340, 90, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Categoria",365, fonte_normal_negrito);
	
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(430, 60, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Fracionado", 434, fonte_normal_negrito);
	
	grupo.setAlturaDeLinha(14);
	grupo.addRetangulo(490, 65, Configuracoes.preferencias.rel_cor_cabecalho_de_tabela);
	grupo.setAlturaDeLinha(10);
	grupo.addTexto("Preço (R$)", 492, fonte_normal_negrito);
	
	
	DAO<Categoria> dao_cat  = new DAO<Categoria>(Categoria.class);
	
		for(Produto produto: produtos){
		
		String categoria  = "";
		
			if(produto.getFk_categoria()>0){
			
			Categoria aux = dao_cat.get(produto.getFk_categoria()); 
			if(aux!=null)
			categoria = aux.getCodigo();
			}	
		
		String fracionado = "";	
		String preco = "";	
		
			if(produto.getVenda_fracionada()!=null && produto.getVenda_fracionada().compareTo("S")==0){
				
			fracionado = "SIM";	
			preco = Calculo.formataValor(produto.getValor_fracionado())+"/"+produto.getUnidade_de_medida();
			}
			else
			preco = Calculo.formataValor(produto.getValor_final());
		
		
		grupo.novaLinha();
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(Comuns.addPaddingAEsquerda(""+produto.getId_produto(), 8, "0"), 3);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(produto.getCodigo_ean()!=null?produto.getCodigo_ean():"", 53);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(produto.getDescricao_abreviada(), 133);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(categoria, 343);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(fracionado, 446);
		
		grupo.setAlturaDeLinha(14);
		grupo.addTexto(preco, 495);
		
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
	grupo.addTexto("LISTA DE PRODUTOS", Util.getPosicaoXCentralizado(this, this.fonte_titulo, "LISTA DE PRODUTOS"), this.fonte_titulo);
	grupo.addTexto("Cadastrados:", 0, this.fonte_normal_negrito);
	grupo.addTexto(""+num_reg, 68);
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
