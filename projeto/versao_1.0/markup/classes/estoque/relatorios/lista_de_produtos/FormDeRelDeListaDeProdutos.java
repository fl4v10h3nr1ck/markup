package classes.estoque.relatorios.lista_de_produtos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import classes.componentes.impressao.FormDeExibicao;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Mensagens;




public class FormDeRelDeListaDeProdutos extends Dialogo{


private static final long serialVersionUID = 1L;


private ButtonGroup grupo;	

private JRadioButton bt_ordem_alfabetica;
private JRadioButton bt_categoria;
private JRadioButton bt_maior_preco;	
private JRadioButton bt_menor_preco;	
private JRadioButton bt_fracao_primeiro;	
private JRadioButton bt_Fracao_ultimo;



	public FormDeRelDeListaDeProdutos() {
		
	super("Relatório de Lista de Produtos", 500, 280);
	
	this.adicionarComponentes();
	}



	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);		
	JPanel p1= new JPanel(new GridBagLayout());
	p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	this.add(p1, cons);	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(5, 2, 5, 2);
	p1.add(new JLabel("<html><b>Ordernar lista por:</b></html>"), cons);
	
	cons.insets = new Insets(3, 2, 3, 2);
	p1.add(this.bt_ordem_alfabetica = new JRadioButton("Ordem alfabética de nome de produto.", true), cons);
	p1.add(this.bt_categoria = new JRadioButton("Por categoria."), cons);
	p1.add(this.bt_maior_preco = new JRadioButton("Maior custo preço primeiro."), cons);
	p1.add(this.bt_menor_preco = new JRadioButton("Menor custo preço primeiro."), cons);
	p1.add(this.bt_fracao_primeiro = new JRadioButton("Fracionados primeiro."), cons);
	p1.add(this.bt_Fracao_ultimo = new JRadioButton("Fracionados por último."), cons);
	
	
	this.grupo  = new ButtonGroup();
	this.grupo.add(this.bt_ordem_alfabetica);
	this.grupo.add(this.bt_categoria);
	this.grupo.add(this.bt_maior_preco);
	this.grupo.add(this.bt_menor_preco);
	this.grupo.add(this.bt_fracao_primeiro);
	this.grupo.add(this.bt_Fracao_ultimo);
	
	

	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.insets = new Insets(5, 2, 5, 2);
	JButton bt_gerar  = new JButton("Gerar Relatório", new ImageIcon(getClass().getResource("/icons/pdf.png")));
	bt_gerar.setToolTipText("Gerar relatório de lista de produtos.");
	this.add(bt_gerar, cons);
	
	
		bt_gerar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
			
	}


	
	
	

	@Override
	public boolean acaoPrincipal() {
	
	String subquery = "";	
		
	if(this.bt_ordem_alfabetica.isSelected())
	subquery += "prod.descricao_abreviada ASC";	
	else if(this.bt_categoria.isSelected())
	subquery += "prod.fk_categoria DESC";
	else if(this.bt_maior_preco.isSelected())
	subquery += "prod.valor_custo_medio DESC";
	else if(this.bt_menor_preco.isSelected())
	subquery += "prod.valor_custo_medio ASC";
	else if(this.bt_fracao_primeiro.isSelected())
	subquery += "prod.venda_fracionada ASC";
	else if(this.bt_Fracao_ultimo.isSelected())
	subquery += "prod.venda_fracionada DESC";
		

	
	FormDeExibicao form = new FormDeExibicao(new Rel__ListaDeProdutos(subquery));
	form.mostrar();	
		
	return false;
	}

	
	
	
	
	
	
	
	
	
}
