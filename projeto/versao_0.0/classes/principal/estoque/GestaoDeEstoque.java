package principal.estoque;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import principal.FormDeGestaoBase;
import principal.estoque.categoria.GestaoDeCategorias;
import principal.estoque.subcategoria.GestaoDeSubCategorias;
import DAO.DAO;
import componentes.beans.Produto;
import componentes.tabelas.MotorDePesquisa;
import comuns.Mensagens;





public class GestaoDeEstoque extends FormDeGestaoBase<Produto>{




private static final long serialVersionUID = 1L;






	public GestaoDeEstoque(){
		
	super("Gestão de Estoque");
	
	this.motorDePesquisa = new MotorDePesquisa<Produto>("Produtos", Produto.class);
	this.motorDePesquisa.tableModel.setWhere("prod.status='ATIVO'");
	this.motorDePesquisa.tableModel.setOrderBy("order by prod.data_cadastro DESC");
	
	adicionarComponentes();	
	
	
	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = 1;
	cons.insets = new Insets(5, 5, 0, 5);
	JButton btCategorias = new JButton(new ImageIcon(getClass().getResource("/icons/categorias_mini.png")));
	btCategorias.setToolTipText("Gerenciar categorias");
	this.painelNovosItens.add(btCategorias, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	JButton btSubcategoria = new JButton(new ImageIcon(getClass().getResource("/icons/subcategoria.png")));
	btSubcategoria.setToolTipText("Gerenciar subcategorias");
	this.painelNovosItens.add(btSubcategoria, cons);
	
	
	cons.insets = new Insets(0, 5, 0, 5);
	cons.gridwidth = 1;
	this.painelNovosItens.add(new JLabel("Categorias") , cons);
	this.painelNovosItens.add(new JLabel("Subcategorias") , cons);
	
	
	
		btCategorias.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				    	
		GestaoDeCategorias form = new GestaoDeCategorias();
		form.mostrar();
			
		motorDePesquisa.update();	
		}});
	
	
		btSubcategoria.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					    	
		GestaoDeSubCategorias form = new GestaoDeSubCategorias();
		form.mostrar();
				
		motorDePesquisa.update();	
		}});		
	
	
	
	
	this.motorDePesquisa.update();
	}
	
	
	
	
	

	@Override
	public void novoForm() {
	
	FormNovoProduto novo = new FormNovoProduto();
	novo.mostrar();
		
	motorDePesquisa.update();	
	}





	@Override
	public void alterarForm() {
		
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GEREREC", "EDITAR"))
	//return;
	
	Produto selectedItem= (Produto) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
		
		if(selectedItem != null){
		
		FormAlterProduto alterForm = new FormAlterProduto( new DAO<Produto>(Produto.class).get(selectedItem.getId_produto()));
		alterForm.mostrar();
		
		motorDePesquisa.update();
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para alteração.");	
	}





	@Override
	public void deletarForm() {
	
	//if(!Configuracao.usuarioAtual.isAllowed(Configuracao.connector, "GCADCL", "EXCLUIR"))
	//return;	
		
	Produto selectedItem= (Produto) this.motorDePesquisa.tableModel.getLinha(motorDePesquisa.table.getSelectedRow());
				
	if(selectedItem == null)
	Mensagens.msgDeErro("Selecione uma linha da tabela de produtos para exclusão.");
		else{
		
		if( new DAO<Produto>(Produto.class).desativar(selectedItem.getId_produto()))
		motorDePesquisa.update();
		}
	}




	@Override
	public boolean acaoPrincipal() {return false;}
	
	
	
}
