package classes.estoque.cadastro.etiqueta;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Mensagens;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.componentes.FormDeSelecao;
import classes.componentes.GestorDeCodigoDeBarra;
import classes.componentes.janelas.Dialogo;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.dao.DAO;
import classes.estoque.beans.Produto;




public class FormDeEtiqueta extends Dialogo{

private static final long serialVersionUID = 1L;


private int id_prod;
private String preço;
private String subtitulo;
private CampoLimitado tf_produto;
private CampoLimitado tf_id;
private CampoLimitado tf_ean;
private CampoLimitado tf_preco;	

private CampoLimitadoSoDigito tf_quant;


private JComboBox<String> modelo;

	
private VisualizadorDeEtiqueta visualizador;	
	
	


	
	public FormDeEtiqueta() {
		
	super("Gerador de Etiquetas", 700, 500);
	
	this.adicionarComponentes();
	}

	
	
	
	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);		
	JPanel p1= new JPanel(new GridBagLayout());
	p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	
	this.add(p1, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weightx = 1;
	cons.weighty  = 1;
	JPanel p2= new JPanel(new GridBagLayout());
	p2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	this.add(p2, cons);
	
	cons.gridwidth  = 1;
	cons.weightx = 0.25;
	JPanel p3= new JPanel(new GridBagLayout());
	p2.add(p3, cons);
	
	cons.weightx = 0.75;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(this.visualizador = new VisualizadorDeEtiqueta(), cons);		
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.weighty  = 0;
	JPanel p4= new JPanel(new GridBagLayout());
	p4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	this.add(p4, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.weighty  = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("Selecionar Produto:"), cons);

	
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_produto = new CampoLimitado(250), cons);
	

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("ID:"), cons);
	p1.add(new JLabel("EAN:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Preço:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tf_id = new CampoLimitado(200), cons);
	p1.add(this.tf_ean = new CampoLimitado(200), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(this.tf_preco = new CampoLimitado(200), cons);
	
	this.tf_produto.setEditable(false);
	this.tf_id.setEnabled(false);
	this.tf_ean.setEnabled(false);
	this.tf_preco.setEnabled(false);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new JLabel("Tamanho da etiqueta:"), cons);
	
	cons.insets = new Insets(2, 2, 2, 2);
	p3.add(this.modelo = new JComboBox<String>(new String[]{"25,4mm x 66,7mm (Pimaco 6280)", 
															 "15,0mm x 26,0mm (Pimaco A4249)"}), cons);

	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.anchor = GridBagConstraints.EAST;
	JButton bt_salvar_modelo  = new JButton("Salvar Modelo", new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_salvar_modelo.setToolTipText("Salvar este modelo para o produto selecionado");
	p2.add(bt_salvar_modelo, cons);
	
	bt_salvar_modelo.setEnabled(false);
	
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = 1;
	cons.anchor = GridBagConstraints.WEST;
	cons.insets = new Insets(2, 2, 2, 2);
	p4.add(new JLabel("Gerar PDF com "), cons);
	
	cons.ipadx = 50;
	p4.add(this.tf_quant = new CampoLimitadoSoDigito(3), cons);
	cons.ipadx = 0;
	p4.add(new JLabel(" Etiquetas."), cons);
	p4.add(new JLabel(""), cons);
	
	
	JButton bt_imprimir  = new JButton("Impressão", new ImageIcon(getClass().getResource("/icons/pdf.png")));
	bt_imprimir.setToolTipText("Preparar para impressão");
	p4.add(bt_imprimir, cons);
	
	bt_imprimir.setEnabled(false);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p4.add(new JLabel(""), cons);
	
	
		bt_imprimir.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
			if(acaoPrincipal()){
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}
		}});
	
	
	
	
	

		bt_salvar_modelo.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
			if(id_prod>0){
			
			DAO<Produto> dao = new DAO<Produto>(Produto.class);
				
			Produto produto = dao.get(id_prod);	
			
				if(produto!=null){
				produto.setModelo_etiqueta(getModeloDeEtiquetaSelecionado());	
				
				if(dao.altera(produto))
				Mensagens.msgDeSucesso("Modelo salvo com sucesso.");
				else
				Mensagens.msgDeErroAoSalvar();
				}
				else
				Mensagens.msgDeErro("Produto não encontrado.");
			}
			else
			Mensagens.msgDeErro("Nenhum produto selecionado.");
		}});
		
	
	
	
	
		this.modelo.addItemListener(new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent e) {
		
		visualizador.repaint();
		}});
	
	
	
		this.tf_produto.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
		
		if(!tf_produto.isEnabled())
		return;	
	
		Produto retorno = new Produto();
		
		FormDeSelecao<Produto> selectionItemForm = 
							new FormDeSelecao<Produto>("Adicionar Produto", retorno, Produto.class, "prod.ativo='S'");
		selectionItemForm.mostrar();
						
			if(retorno != null && retorno.getId_produto() > 0){
			
			retorno	 = new DAO<Produto>(Produto.class).get(retorno.getId_produto());
				
			id_prod = retorno.getId_produto();
			tf_produto.setText(retorno.getDescricao_abreviada());
			tf_id.setText(""+Comuns.addPaddingAEsquerda(""+retorno.getId_produto(), 8, "0"));
			tf_ean.setText(retorno.getCodigo_ean());
			
			subtitulo = retorno.getMarca()!=null?retorno.getMarca():"";
			subtitulo += (subtitulo.length()>0 && retorno.getSecao()!=null && retorno.getSecao().length()>0?" - ":"");
			subtitulo += retorno.getSecao()!=null?retorno.getSecao():"";
			
			
	
				if(retorno.getVenda_fracionada()!=null && retorno.getVenda_fracionada().compareTo("S")==0){
				
				preço = Calculo.formataValor(retorno.getValor_fracionado());
				tf_preco.setText("R$: "+preço+" / "+retorno.getUnidade_de_medida());
				}
				else{
				
				preço = Calculo.formataValor(retorno.getValor_final());
				
				tf_preco.setText("R$: "+preço);
				}
				
			
			bt_salvar_modelo.setEnabled(true);
			
			bt_imprimir.setEnabled(true);
			
			visualizador.repaint();
			}		
		}});
	
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	textFieldList.add(this.tf_produto);
	textFieldList.add(this.tf_quant);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	
	}

	
	
	
	
	
	
	private String getModeloDeEtiquetaSelecionado(){
		
	if(this.modelo.getSelectedIndex()==0)
	return "25,4x66,7_modelo_1";
	
	if(this.modelo.getSelectedIndex()==1)
	return "15,0x26,0_modelo_1";
		
	return null;
	}
	
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
	
		
		if(this.id_prod==0){
		
		Mensagens.msgDeErro("Nenhum produto selecionado.");
		Comuns.textFieldErroMode(this.tf_produto);
		return false;
		}
		
		
		if(Calculo.stringZero(this.tf_quant.getText())){
			
		Mensagens.msgDeErro("Informe a quantidade de etiquetas para impressão.");
		Comuns.textFieldErroMode(this.tf_quant);
		return false;	
		}
		
		
	return false;
	}

	
	
	
	
	
	private class VisualizadorDeEtiqueta extends JPanel{
		
		
	private static final long serialVersionUID = 1L;

	private final int MARGEM = 5;
	
	
	
		@Override
	    public void paintComponent(Graphics g) {
	       
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//quantidade de pixels em 1mm
		int comprimento = 4;
		int altura = 4;
		
			if(modelo.getSelectedIndex()==0){
		
			comprimento *= 66.7;	
			altura *=25.4;
			}
			else if(modelo.getSelectedIndex()==1){
			
			comprimento *= 26;	
			altura *=15;
			}
		
		g2d.setColor(Color.BLACK);
		g2d.drawRect(MARGEM, MARGEM, comprimento - MARGEM, altura - MARGEM);
			
			if(id_prod>0){
			
			Image cod_barra = new GestorDeCodigoDeBarra().getCodEAN13(tf_ean.getText());
			
				if(modelo.getSelectedIndex()==0){
				
				g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
				g2d.drawString(tf_produto.getText(), MARGEM+4, MARGEM+20);	
				
				g2d.setFont(new Font("SansSerif", Font.PLAIN, 11));
				g2d.drawString(subtitulo, MARGEM+5, MARGEM+33);	

				g2d.drawImage(cod_barra, MARGEM+4, altura-46, null);
				
				g2d.setFont(new Font("SansSerif", Font.BOLD, 26));
				g2d.drawString("R$:"+preço, 
						comprimento - (Comuns.getComprimentoDeString("R$:"+preço, g2d.getFont())+5), altura-10);
				
				g2d.setFont(new Font("SansSerif", Font.PLAIN, 11));
				g2d.drawString(tf_ean.getText(), MARGEM+5, altura-10);
				}
				else if(modelo.getSelectedIndex()==1){
						
				g2d.drawString("R$:"+preço, MARGEM+3, MARGEM+14);	
				g2d.drawImage(cod_barra, MARGEM+2, MARGEM+17, null);
				g2d.drawString(tf_ean.getText(), MARGEM+3, altura-2);	
				}
			}	
		}
	}	
}
