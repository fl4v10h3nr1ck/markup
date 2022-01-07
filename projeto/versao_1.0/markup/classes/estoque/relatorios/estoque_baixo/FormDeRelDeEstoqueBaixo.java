package classes.estoque.relatorios.estoque_baixo;

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




public class FormDeRelDeEstoqueBaixo extends Dialogo{


private static final long serialVersionUID = 1L;


private ButtonGroup grupo;	

private JRadioButton bt_ordem_alfabetica;
private JRadioButton bt_menor_quantidade;
private JRadioButton bt_categoria;
private JRadioButton bt_maior_custo;	
private JRadioButton bt_menor_custo;	
	



	public FormDeRelDeEstoqueBaixo() {
		
	super("Relatório de Itens com Estoque Baixo", 500, 280);
	

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
	p1.add(new JLabel("<html><b>Ordernar lista por:</b></html>"));
	
	cons.insets = new Insets(3, 2, 3, 2);
	p1.add(this.bt_ordem_alfabetica = new JRadioButton("Ordem alfabética de nome de produto.", true), cons);
	p1.add(this.bt_menor_quantidade = new JRadioButton("Menor quantidade primeiro."), cons);
	p1.add(this.bt_categoria = new JRadioButton("Por categoria."), cons);
	p1.add(this.bt_maior_custo = new JRadioButton("Maior custo médio primeiro."), cons);
	p1.add(this.bt_menor_custo = new JRadioButton("Menor custo médio primeiro."), cons);
	
	this.grupo  = new ButtonGroup();
	this.grupo.add(this.bt_ordem_alfabetica);
	this.grupo.add(this.bt_menor_quantidade);
	this.grupo.add(this.bt_categoria);
	this.grupo.add(this.bt_maior_custo);
	this.grupo.add(this.bt_menor_custo);
	
	

	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	cons.insets = new Insets(5, 2, 5, 2);
	JButton bt_gerar  = new JButton("Gerar Relatório", new ImageIcon(getClass().getResource("/icons/pdf.png")));
	bt_gerar.setToolTipText("Gerar relatório de itens com estoque baixo.");
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
	else if(this.bt_menor_quantidade.isSelected())
	subquery += "prod.quantidade ASC";	
	else if(this.bt_categoria.isSelected())
	subquery += "prod.fk_categoria DESC";
	else if(this.bt_maior_custo.isSelected())
	subquery += "prod.valor_custo_medio DESC";
	else if(this.bt_menor_custo.isSelected())
	subquery += "prod.valor_custo_medio ASC";
	
		
	FormDeExibicao form = new FormDeExibicao(new Rel__EstoqueBaixo(subquery));
	form.mostrar();	
		
	return false;
	}

	
	
	
	
	
	
	
	
	
}
