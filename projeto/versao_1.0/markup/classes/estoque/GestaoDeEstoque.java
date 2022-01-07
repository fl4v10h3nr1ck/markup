package classes.estoque;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import classes.componentes.tabelas.MotorDePesquisa;
import classes.comuns.Mensagens;
import classes.dao.DAO;
import classes.estoque.beans.Produto;
import classes.estoque.cadastro.balanca.FormBalanca;
import classes.estoque.cadastro.categoria.GestaoDeCategorias;
import classes.estoque.cadastro.etiqueta.FormDeEtiqueta;
import classes.estoque.cadastro.fornecedores.GestaoDeFornecedores;
import classes.estoque.cadastro.produto.FormAlterProduto;
import classes.estoque.cadastro.produto.FormNovoProduto;
import classes.estoque.cadastro.subcategoria.GestaoDeSubcategorias;
import classes.estoque.relatorios.estoque_baixo.FormDeRelDeEstoqueBaixo;
import classes.estoque.relatorios.lista_de_produtos.FormDeRelDeListaDeProdutos;
import classes.principal.FormDeGestaoBase;





public class GestaoDeEstoque extends FormDeGestaoBase<Produto>{

	
private static final long serialVersionUID = 1L;



	public GestaoDeEstoque(){
	
	super("Gestão de Estoque", 
			GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
			GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);	
		
	this.motorDePesquisa = new MotorDePesquisa<Produto>("Produtos", Produto.class);
	this.motorDePesquisa.modelo.setOrderBy("order by prod.data_cadastro DESC");
	this.motorDePesquisa.modelo.setWhere("prod.ativo='S'");
	
	this.adicionarComponentes();
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.weighty  = 0;
	cons.ipadx = 0;
	cons.gridwidth = 1;
	cons.insets = new Insets(0, 5, 0, 5);
	
	
	JButton categorias = new JButton(new ImageIcon(getClass().getResource("/icons/categoria.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(categorias, cons);
	categorias.setToolTipText("Gestão de categorias de produtos");
	
	JButton subcategorias = new JButton(new ImageIcon(getClass().getResource("/icons/subcategorias.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(subcategorias, cons);
	subcategorias.setToolTipText("Gestão de subcategorias de produtos");
	
	JButton fornecedores = new JButton(new ImageIcon(getClass().getResource("/icons/fornecedor.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(fornecedores, cons);
	fornecedores.setToolTipText("Gestão de fornecedores");
	
	/*JButton balanca = new JButton("Balança", new ImageIcon(getClass().getResource("/icons/balanca.png")));
	balanca.setHorizontalTextPosition(JButton.CENTER);
	balanca.setVerticalTextPosition(JButton.BOTTOM);
	balanca.setFont(Configuracoes.preferencias.fonte_janela);*/
	
	JButton balanca = new JButton(new ImageIcon(getClass().getResource("/icons/balanca.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(balanca, cons);
	balanca.setToolTipText("Configurar balança de medida.");
	
	JButton etiquetas = new JButton(new ImageIcon(getClass().getResource("/icons/etiqueta.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(etiquetas, cons);
	etiquetas.setToolTipText("Configurar e gerar etiquetas de preço.");
	
	JButton rel_estoque_baixo = new JButton(new ImageIcon(getClass().getResource("/icons/atencao.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(rel_estoque_baixo, cons);
	rel_estoque_baixo.setToolTipText("Gerar relatório de itens com estoque baixo.");
	
	JButton rel_lista = new JButton(new ImageIcon(getClass().getResource("/icons/lista.png")));
	this.motorDePesquisa.painel_menu_de_opcoes.add(rel_lista, cons);
	rel_lista.setToolTipText("Gerar relatório de lista de produtos.");
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.ipadx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.motorDePesquisa.painel_menu_de_opcoes.add(new JLabel(""), cons);
	
		
		categorias.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				  
		GestaoDeCategorias gestao = new GestaoDeCategorias();
		gestao.mostrar();
		}});
		
		
		subcategorias.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					  
		GestaoDeSubcategorias gestao = new GestaoDeSubcategorias();
		gestao.mostrar();
		}});
		
	
	
		fornecedores.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
						  
		GestaoDeFornecedores gestao = new GestaoDeFornecedores();
		gestao.mostrar();
		}});
	
		
		balanca.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							  
		FormBalanca form = new FormBalanca();
		form.mostrar();
		}});
		
		
		etiquetas.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
								  
		FormDeEtiqueta form = new FormDeEtiqueta();
		form.mostrar();
		}});
		
		
		rel_estoque_baixo.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		FormDeRelDeEstoqueBaixo form = new FormDeRelDeEstoqueBaixo();
		form.mostrar();
		}});
		
		
		rel_lista.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		FormDeRelDeListaDeProdutos form = new FormDeRelDeListaDeProdutos();
		form.mostrar();
		}});
		
		
	motorDePesquisa.atualizar();
	}





	@Override
	public void novoForm() {
		
	FormNovoProduto form = new FormNovoProduto();	
	form.mostrar();
		
	motorDePesquisa.atualizar();	
	}





	@Override
	public void alterarForm() {
	
	Produto selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
		if(selecionado != null){
		
		FormAlterProduto alterForm = new FormAlterProduto(selecionado);
		alterForm.mostrar();
		
		motorDePesquisa.atualizar();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para alteração.");	
		
	}





	@Override
	public void deletarForm() {
	
	Produto selecionado= this.motorDePesquisa.modelo.getLinha(motorDePesquisa.tabela.getSelectedRow());
		
	if(selecionado == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para exclusão.");
		else{
			
		selecionado.setAtivo("N");	
				
		new DAO<Produto>(Produto.class).altera(selecionado);
					
		motorDePesquisa.atualizar();
		}		
	}





	@Override
	public boolean acaoPrincipal() {return false;}
	
	

	
	
	
	
	
	
	
}
