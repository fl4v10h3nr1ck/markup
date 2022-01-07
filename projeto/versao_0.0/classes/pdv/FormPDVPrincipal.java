package pdv;

import inicio.BarraDeInformacoes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import principal.caixa.FormVerCaixa;
import DAO.DAO;
import classes.CampoLimitado;
import componentes.Barra_De_Progresso;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Caixa;
import componentes.beans.Convenio;
import componentes.beans.Entrada_Saida;
import componentes.beans.FormaDePagamento;
import componentes.beans.Item;
import componentes.beans.Movimento;
import componentes.beans.Movimento_X_Produto;
import componentes.beans.Movimentos_x_FormasPag;
import componentes.beans.Parcela;
import componentes.beans.Produto;
import componentes.janelas.Dialogo;
import bemajava.Bematech;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Configuracao;
import comuns.Data;
import comuns.Mensagens;
import comuns.Preferencias;





public class FormPDVPrincipal extends JFrame{

	

private static final long serialVersionUID = 1L;


private JPanel panelPrincipal;

private JScrollPane scroll_cupom_virtual;

private JPanel cupom;

/*****************************************************************************/


private Rotulo rot_descricao_produto;

private Rotulo rot_nome_pdv;


private CampoLimitado tf_ean_produto;
private Rotulo rot_ean;
private Rotulo rot_quantidade;
private Rotulo rot_preco;
private Rotulo rot_preco_total;
private Rotulo rot_valor_final;
private Rotulo rot_val_recebido;
private Rotulo rot_troco;



private Rotulo rot_cliente;
private Rotulo rot_forma_pagamento;
private Rotulo rot_desconto;


private int largura;
private int altura;


/*****************************************************************************/


private List<Produto> lista_de_produtos_temp;

private Movimento movimento_temp;

private String valor_bruto_temp;

private String valor_final_temp;




private Convenio convenio_temp;

private String tipo_desconto_temp;

private String valor_desconto_temp;




private List<Item> formas_de_pagamento_temp;


private String cpf_consumidor_temp;


private String valor_recebido_temp;

private final String CAIXA_LIVRE = "Caixa Livre!!!";

private DAO<Produto> produtoDAO;



private final boolean nao_gerar_cupom = true;




	public FormPDVPrincipal(){
		
	super();
	
	this.setTitle("Markup PDV - Ponto de Venda");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(this.getToolkit().getScreenSize());
	
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);   	
    
    this.setLocationRelativeTo(null);
	this.setUndecorated(true);
	this.setLayout(new GridBagLayout());	
	
	try {setIconImage(ImageIO.read(getClass().getResource("/icons/favicon.png" )));} catch (IOException e) {e.printStackTrace();} 
	
    
    this.requestFocusInWindow();
   
    
    this.addKeyListener(new OuvintePrincipal());
    
    
    this.lista_de_produtos_temp = new ArrayList<Produto>();
   
    
    this.produtoDAO  =new DAO<Produto>(Produto.class);
    
    
    	this.addWindowListener(new WindowAdapter(){  
        public void windowClosing (WindowEvent e){  
        
        if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja sair do sistema?"))
    	encerrar();
        	          
        }});  
  	
    
    this.largura = this.getWidth();
    this.altura = this.getHeight();
    
    this.getContentPane().setBackground(Preferencias.COR_DE_FUNDO_FRAME_CAIXA);
    
    adicionarComponentes();
    
    this.limpaDadosTemporarios();
	}
	
	
	
	
	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();   
	
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets= new Insets(2, 2, 2, 2);
	panelPrincipal = new JPanel(null);	
	panelPrincipal.setBackground(Preferencias.COR_DE_FUNDO_PANE_CAIXA);
	this.add(panelPrincipal, cons);	
	
	
	cons.insets= new Insets(0, 0, 0, 0);
	JPanel p_titulo = new JPanel(new GridBagLayout());	
	p_titulo.setBackground(Preferencias.COR_DE_FUNDO_PANE_CAIXA);
	p_titulo.setLocation(0, 0);
	p_titulo.setSize(this.largura-4, 95);
	p_titulo.setBorder(BorderFactory.createTitledBorder("Ponto de Venda"));  
	panelPrincipal.add(p_titulo, cons);	
	
	JPanel p1 = new JPanel(new GridBagLayout());		
	p1.setBackground(Color.WHITE);
	p1.setLocation(0, p_titulo.getY()+p_titulo.getHeight()+2);
	p1.setSize((this.largura*36)/100, this.altura - (p_titulo.getHeight() + 40));
	p1.setBorder(BorderFactory.createTitledBorder("Cupom Fiscal"));  
	panelPrincipal.add(p1, cons);
	
	JPanel p_produto = new JPanel(null);
	p_produto.setBackground(Color.WHITE);
    p_produto.setLocation(p1.getX()+p1.getWidth(), p1.getY());
	p_produto.setSize(((this.largura*64)/100)-4, p1.getHeight());
	p_produto.setBorder(BorderFactory.createTitledBorder("Detalhes"));  
	panelPrincipal.add(p_produto, cons);	

	if(Configuracao.barraDeInformacoes==null)
	Configuracao.barraDeInformacoes = new BarraDeInformacoes();
	Configuracao.barraDeInformacoes.setSize(this.largura, 30);
	Configuracao.barraDeInformacoes.setLocation(0, this.altura-Configuracao.barraDeInformacoes.getHeight());
	panelPrincipal.add(Configuracao.barraDeInformacoes, cons);	
	
	JPanel panel_descricao = new JPanel(new GridBagLayout());	
	panel_descricao.setBackground(Color.WHITE);
	panel_descricao.setLocation(10, 25);
	panel_descricao.setSize(p_produto.getWidth() - 20, 60);
	p_produto.add(panel_descricao, cons);	
	
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Color.WHITE);
	p2.setLocation(10, panel_descricao.getY()+panel_descricao.getHeight());
	p2.setSize(((p_produto.getWidth()*25)/100)-20, p_produto.getHeight() - panel_descricao.getHeight() - 40);
	p_produto.add(p2, cons);	
	
	
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Color.WHITE);
	p3.setLocation(p2.getX()+p2.getWidth(), p2.getY());
	p3.setSize((p_produto.getWidth()*75)/100, p2.getHeight());
	p_produto.add(p3, cons);	
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.gridwidth = 1;
	p_titulo.add(new JLabel(new ImageIcon(getClass().getResource("/icons/markup.png"))), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.rot_nome_pdv = new Rotulo("<html><b>CAIXA "+Comuns.descricao_terminal+"</b></html>");
	rot_nome_pdv.setHorizontalAlignment(SwingConstants.RIGHT);
	rot_nome_pdv.setForeground(Color.RED);
	rot_nome_pdv.setFont(new Font("Dialog", Font.PLAIN, 30));
	p_titulo.add(rot_nome_pdv, cons);
	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.anchor = GridBagConstraints.CENTER;
	cons.ipady = 25;
	cons.weightx = 1;
	cons.insets = new Insets(5, 0, 0, 0);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.rot_descricao_produto = new Rotulo(CAIXA_LIVRE);
	this.rot_descricao_produto.setHorizontalAlignment(SwingConstants.CENTER);
	panel_descricao.add(this.rot_descricao_produto, cons);
	this.rot_descricao_produto.setFont(new Font("Dialog", Font.PLAIN, 36));
	
	cons.insets = new Insets(5, 0, 5, 0);
	panel_descricao.add(new JSeparator(SwingConstants.HORIZONTAL), cons);


	Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.ipady = 0;
	cons.insets = new Insets(15,10,0,5);
	p3.add(new JLabel("<html><b>Produto:</b></html>"), cons);
	
	cons.ipady = 15;
	cons.insets = new Insets(0,5,5,5);
	this.rot_ean = new Rotulo("");
	this.rot_ean.setOpaque(true);
	this.rot_ean.setBackground(Color.white);
	this.rot_ean.setHorizontalAlignment(SwingConstants.CENTER);
	this.rot_ean.setFont(new Font("Dialog", Font.PLAIN, 36));
	this.rot_ean.setBorder(border);
	p3.add(this.rot_ean, cons);
	
	cons.ipady = 0;
	cons.insets = new Insets(15,10,0,5);
	cons.gridwidth = 1;
	p3.add(new JLabel("<html><b>QTDe:</b></html>"), cons);
	p3.add(new JLabel("<html><b>Valor Unitário (R$):</b></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p3.add(new JLabel("<html><b>Valor total (R$):</b></html>"), cons);
	
	
	cons.ipady = 15;
	cons.insets = new Insets(0,5,5,5);
	cons.gridwidth = 1;
	cons.anchor = GridBagConstraints.CENTER;
	cons.weightx  = 0.4;
	this.rot_quantidade = new Rotulo("");
	this.rot_quantidade.setOpaque(true);
	this.rot_quantidade.setBackground(Color.white);
	this.rot_quantidade.setHorizontalAlignment(SwingConstants.CENTER);
	this.rot_quantidade.setFont(new Font("Dialog", Font.PLAIN, 36));
	this.rot_quantidade.setBorder(border);
	p3.add(this.rot_quantidade, cons);
	

	cons.weightx  = 0.6;
	this.rot_preco = new Rotulo("");
	this.rot_preco.setBorder(new EmptyBorder(0, 10, 0, 10) );
	this.rot_preco.setOpaque(true);
	this.rot_preco.setBackground(Color.WHITE);
	this.rot_preco.setHorizontalAlignment(SwingConstants.RIGHT);
	this.rot_preco.setFont(new Font("Dialog", Font.PLAIN, 36));
	this.rot_preco.setBorder(border);
	p3.add(this.rot_preco, cons);
	

	cons.ipady = 15;
	cons.insets = new Insets(0,5,5,5);
	cons.weightx  = 1;
	cons.ipadx = 10;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.rot_preco_total = new Rotulo("");
	this.rot_preco_total.setBorder(new EmptyBorder(0, 10, 0, 10) );
	this.rot_preco_total.setOpaque(true);
	this.rot_preco_total.setBackground(Color.WHITE);
	this.rot_preco_total.setHorizontalAlignment(SwingConstants.RIGHT);
	this.rot_preco_total.setFont(new Font("Dialog", Font.PLAIN, 36));
	this.rot_preco_total.setBorder(border);
	p3.add(this.rot_preco_total, cons);
	
	cons.ipady = 0;
	cons.insets = new Insets(15,10,0,5);
	p3.add(new JLabel("<html><b>Valor Total (R$):</b></html>"), cons);
	
	cons.ipady = 15;
	cons.insets = new Insets(0,5,5,5);
	this.rot_valor_final = new Rotulo("");
	this.rot_valor_final.setHorizontalAlignment(SwingConstants.RIGHT);
	this.rot_valor_final.setFont(new Font("Dialog", Font.BOLD, 70));
	p3.add(this.rot_valor_final, cons);
	
	cons.ipady = 0;
	cons.insets = new Insets(15,10,0,5);
	cons.gridwidth = 1;
	p3.add(new JLabel("<html><b>Valor Recebido (R$):</b></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p3.add(new JLabel("<html><b>Troco (R$):</b></html>"), cons);
	
	cons.ipady = 15;
	cons.insets = new Insets(0,5,5,5);
	cons.weightx  = 1;
	cons.ipadx = 10;
	cons.gridwidth = 1;
	this.rot_val_recebido = new Rotulo("");
	this.rot_val_recebido.setBorder(new EmptyBorder(0, 10, 0, 10) );
	this.rot_val_recebido.setOpaque(true);
	this.rot_val_recebido.setBackground(Color.WHITE);
	this.rot_val_recebido.setHorizontalAlignment(SwingConstants.RIGHT);
	this.rot_val_recebido.setFont(new Font("Dialog", Font.PLAIN, 36));
	this.rot_val_recebido.setBorder(border);
	p3.add(this.rot_val_recebido, cons);
	

	cons.ipady = 15;
	cons.insets = new Insets(0,5,5,5);
	cons.weightx  = 1;
	cons.ipadx = 10;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	this.rot_troco = new Rotulo("");
	this.rot_troco.setBorder(new EmptyBorder(0, 10, 0, 10) );
	this.rot_troco.setOpaque(true);
	this.rot_troco.setBackground(Color.WHITE);
	this.rot_troco.setHorizontalAlignment(SwingConstants.RIGHT);
	this.rot_troco.setFont(new Font("Dialog", Font.PLAIN, 36));
	this.rot_troco.setBorder(border);
	p3.add(this.rot_troco, cons);
	

	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	p3.add(new JLabel(""), cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weightx = 0;	
	cons.weighty = 0;
	cons.ipadx = 5;
	cons.insets = new Insets(2,1,2,1);
	JButton bt_mostrar_opcoes=  new JButton("Mostrar Opções (Home)", new ImageIcon(getClass().getResource("/icons/menu.png")));
	p3.add(bt_mostrar_opcoes, cons);
	bt_mostrar_opcoes.setFocusable(false);
	
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(15, 15, 15, 15);
	this.cupom = new JPanel(new GridBagLayout());
	this.cupom.setBackground(new Color(255, 255, 201));
	p1.add(this.scroll_cupom_virtual = new JScrollPane(this.cupom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), cons);	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.ipady = 0;
	cons.insets = new Insets(0,5,5,5);
	cons.gridwidth = 1;
	cons.weightx  = 0.3;
	p1.add(new JLabel("<html><b>Cliente:</b></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.7;
	p1.add(this.rot_cliente = new Rotulo(""), cons);
	
	cons.gridwidth = 1;
	cons.weightx  = 0.3;
	p1.add(new JLabel("<html><b>Forma de Pagamento:</b></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.7;
	p1.add(this.rot_forma_pagamento = new Rotulo(""), cons);

	cons.gridwidth = 1;
	cons.weightx  = 0.3;
	p1.add(new JLabel("<html><b>Desconto:</b></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx  = 0.7;
	p1.add(this.rot_desconto = new Rotulo(""), cons);
	
	cons.weightx  = 1;
	this.tf_ean_produto = new CampoLimitado(18, "");
	this.tf_ean_produto.setBorder(new EmptyBorder(0, 0, 0, 0) );
	p1.add(this.tf_ean_produto, cons);
	
	
	this.tf_ean_produto.addActionListener( new ActionListener(){		
	@Override
	public void actionPerformed( ActionEvent event ){
		
	addItem(tf_ean_produto.getText());
	}});
	

	
		bt_mostrar_opcoes.addActionListener( new ActionListener(){		
		@Override
		public void actionPerformed( ActionEvent event ){
						
		mostrarOpcoes();
		}});
		
	}






	public void mostrar(){
	
	this.addOuvinte(this);	
		
	super.setVisible(true);

	this.tf_ean_produto.grabFocus();
	}




	
	private void limpaDadosTemporarios(){
		
	this.convenio_temp = null;	
			
	this.cpf_consumidor_temp = "";	
				
	this.lista_de_produtos_temp.clear();

	this.movimento_temp = new Movimento();
			
	formas_de_pagamento_temp = new ArrayList<Item>();
		
	this.tipo_desconto_temp = "";

	this.valor_desconto_temp = "0.00";
		
	this.valor_recebido_temp = "0,00";
	
	this.valor_bruto_temp = "0,00";

	this.valor_final_temp = "0,00";

	
	
	/*	
		this.crediario_temp = new Entrada_Saida();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);	
		this.crediario_temp.setPrimeiro_vencimento(Comuns.converteDataParaString(calendar.getTime()));
		
		
		this.crediario_temp.setPorcento_juros(Comuns.porcentjurosPadraoAoMes);
		this.crediario_temp.setPorcento_multa(Comuns.porcentMultaPadrao);
	*/	
		
	this.rot_ean.setText("#############");
	this.rot_quantidade.setText("1");
	this.rot_preco.setText("0,00");
	this.rot_preco_total.setText("0,00");
	this.rot_valor_final.setText("0,00");
	this.rot_val_recebido.setText("0,00");
	this.rot_troco.setText("0,00");
		
	this.rot_cliente.setText("");
	this.rot_forma_pagamento.setText("");
	this.rot_desconto.setText("");
		
		
	this.setCaixaLivre();	
		
	this.atualizaPrecoFinal();
	}
	
	
	



	private void addItem(String ean){
	
	if(ean ==null)
	return;
	
	ean =   ean.replace("*", "###").
				replace("/", "###").
				replace("-", "###").
				replace("+", "###");
	
	boolean quant_e_cod = ean.contains("###")?true:false;
	
	String[] aux =ean.split("###");
	

	int quant;	
	String cod;		
		

		if(aux.length==1){	
		
		quant = 1;		
			
		if(aux[0]==null || aux[0].length()==0 || quant_e_cod)
		cod = "0";	
		else	
		cod  = aux[0].replaceAll("\\D", "");	
		}
		else{
			
		if(aux[1]==null || aux[1].length()==0)
		cod = "0";	
		else
		cod  = aux[1].replaceAll("\\D", "");

		if(aux[0]==null || aux[0].length()==0)
		quant = 1;
		else
		quant = Integer.parseInt(aux[0].replaceAll("\\D", ""));	
			
		}
	
		
		if(cod.length()==13){	
		
		List<Produto> produtos = this.produtoDAO.get(null, "prod.codigo_ean='"+cod+"' and prod.status='ATIVO' and prod.quantidade>0 and prod.quantidade IS NOT NULL", null);
			if(produtos!= null && produtos.size()>0){
				
			addItem(produtos.get(0), quant);
			return;
			}
		}
		
	Produto produto;
		
	
	//limitar o tamanho do inteiro (ID) a 999.999.999 (senao há excessao na conversao de string para inteiro) 
		if(cod.length()<=9){
		
		
		List<Produto> produtos = this.produtoDAO.get(null, "prod.status='ATIVO' and prod.quantidade>0 and prod.quantidade IS NOT NULL and prod.id_produto="+cod, null);
				
			if(produtos!= null && produtos.size()>0){
			
			addItem(produtos.get(0), quant);
			return;
			}
		}
		
		
	produto = new Produto();
		
	FormDeSelecao<Produto> form =  new FormDeSelecao<Produto>("Seleção de produto", 
																				produto,
																				Produto.class,
																				"prod.status='ATIVO' and prod.quantidade>0 and prod.quantidade IS NOT NULL");	
	form.mostrar();	
		
	
	if(produto!=null && produto.getId_produto()>0)
	addItem(produto, quant);
	}



	
	
	private void addItem(Produto aux, int quant){
	
	Produto produto = 	produtoDAO.get(aux.getId_produto());
		
		
		if(this.lista_de_produtos_temp.size()==0){
		
		GregorianCalendar calendar = new GregorianCalendar();
			
		this.movimento_temp.setData_abertura(new java.sql.Date( new Date().getTime()));
		this.movimento_temp.setMin_abertura(calendar.get(Calendar.MINUTE));
		this.movimento_temp.setHora_abertura(calendar.get(Calendar.HOUR_OF_DAY));	
			
		}	
		

	this.tf_ean_produto.setText("");

	produto.setQuantidade(quant);
	
	this.rot_quantidade.setText(""+produto.getQuantidade());
	this.rot_descricao_produto.setText(produto.getDescricao_abreviada());	
	this.rot_ean.setText(produto.getCodigo_ean());	
	this.rot_preco.setText(produto.getValor_final());	
	this.rot_preco_total.setText(Calculo.formataValor(Calculo.multiplica(produto.getQuantidade()+"", produto.getValor_final())));
			
	this.lista_de_produtos_temp.add(produto);

	this.setCupomVirtual();
			
	this.atualizaPrecoFinal();
	}
	
	
	
	
	

	
	private void setCaixaLivre(){
		
	GridBagConstraints cons = new GridBagConstraints();   
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;	
	cons.gridwidth = GridBagConstraints.REMAINDER;
		
		
	this.cupom.removeAll();
		
	cons.ipady = 15;
	cons.insets = new Insets(5,5,0,5);
	JLabel lb1 = new JLabel(CAIXA_LIVRE);
	lb1.setHorizontalAlignment(SwingConstants.CENTER);
	lb1.setFont(new Font("Dialog", Font.BOLD, 24));
	this.cupom.add(lb1, cons);	
		
	this.rot_descricao_produto.setText(CAIXA_LIVRE);
		
	this.cupom.revalidate();	
	this.cupom.repaint();
	}

	
	
	
	
	
	private void atualizaPrecoFinal(){
		
	this.valor_bruto_temp = "0,00";
		
	for(Produto aux : this.lista_de_produtos_temp)
	this.valor_bruto_temp = Calculo.soma(this.valor_bruto_temp, Calculo.multiplica(aux.getQuantidade()+"", aux.getValor_final()));	
	
	
	String descontos = "0.00";
	
		if(this.convenio_temp!=null && this.convenio_temp.getId_convenio()>0){
			
		if(this.convenio_temp.getTipo_desconto().compareTo("TOTAL")==0)
		descontos = this.valor_bruto_temp;	
			
		if(this.convenio_temp.getTipo_desconto().compareTo("PORCENTO")==0)
		descontos  = Calculo.calcPorcentagem(this.valor_bruto_temp, this.convenio_temp.getValor_desconto());
			
		if(this.convenio_temp.getTipo_desconto().compareTo("VALOR")==0)
		descontos  = this.convenio_temp.getValor_desconto();	
		}
	

		if(!Calculo.stringZero(this.tipo_desconto_temp)){
		
		if(this.tipo_desconto_temp.compareTo("PORCENTAGEM")==0)	
		descontos  = Calculo.soma(descontos, Calculo.calcPorcentagem(this.valor_bruto_temp, this.valor_desconto_temp));
		else
		descontos  = Calculo.soma(descontos, this.valor_desconto_temp);
		}
		
	System.out.println(descontos);
		
		
	this.valor_final_temp = Calculo.subtrai(this.valor_bruto_temp, descontos);
		
		if(Calculo.stringParaDouble(this.valor_final_temp)<0){
			
		this.valor_final_temp = "0.00";	
		this.rot_valor_final.setText("0,00");	
		}
		else
		this.rot_valor_final.setText(Calculo.formataValor(this.valor_final_temp));	
	}

	
	

	
	
	private void setCupomVirtual(){
	
	if(this.lista_de_produtos_temp.size() == 0)
	return;
			
	GridBagConstraints cons = new GridBagConstraints();   
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;	
	cons.gridwidth = GridBagConstraints.REMAINDER;	
			
		if(this.lista_de_produtos_temp.size() == 1){
		
		this.cupom.removeAll();
		
		
		cons.ipady = 15;
		cons.insets = new Insets(5,5,0,5);
		cons.anchor = GridBagConstraints.CENTER;
		this.cupom.add(new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -", SwingConstants.CENTER), cons);	
		JLabel lb1 = new JLabel("CUPOM FISCAL");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Dialog", Font.BOLD, 24));
		this.cupom.add(lb1, cons);	
		
		if(this.cpf_consumidor_temp != null && this.cpf_consumidor_temp.length()>0)
		this.cupom.add(new JLabel("CPF consumidor: "+this.cpf_consumidor_temp, SwingConstants.CENTER), cons);	
		this.cupom.add(new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -", SwingConstants.CENTER), cons);	
		
		cons.anchor = GridBagConstraints.WEST;
		cons.ipady = 15;
		this.cupom.add(new JLabel("<html>ITEM&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CÓDIGO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DESCRIÇÃO</html>", SwingConstants.LEFT), cons);	
		this.cupom.add(new JLabel("<html>QTD.UN.VL_UNIT(R$)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ST&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VL_ITEM(R$)</html>", SwingConstants.LEFT), cons);	
		
		this.movimento_temp = new Movimento();
		this.movimento_temp.setCpf_cliente(this.cpf_consumidor_temp);
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		this.movimento_temp.setData_abertura(new java.sql.Date( new Date().getTime()));
		this.movimento_temp.setMin_abertura(calendar.get(Calendar.MINUTE));
		this.movimento_temp.setHora_abertura(calendar.get(Calendar.HOUR_OF_DAY));
		
		this.cupom.revalidate();	
		this.cupom.repaint();
		}

		
	Produto produto = this.lista_de_produtos_temp.get(this.lista_de_produtos_temp.size()-1);	
	
	produto.setUnidade_de_medida(Comuns.getUnidadeDeMedidaDeProduto(produto));	
	
	cons.insets = new Insets(10,5,0,5);	
	cons.ipady = 0;
	this.cupom.add(new JLabel(
			"<html>"+
			Comuns.addPaddingAEsquerda(""+this.lista_de_produtos_temp.size(),3, "0")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			Comuns.addPaddingAEsquerda(""+produto.getId_produto(),6, "0") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			(produto.getDescricao_abreviada()==null?"":produto.getDescricao_abreviada()), SwingConstants.LEFT), cons);			
	
	this.cupom.add(new JLabel("<html>"+
			produto.getQuantidade()+" "+produto.getUnidade_de_medida()+"&nbsp;&nbsp;&nbsp;x&nbsp;&nbsp;&nbsp;"+
			produto.getValor_final()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			Calculo.formataValor(Calculo.multiplica(produto.getQuantidade()+"", produto.getValor_final())+"</html>"), SwingConstants.LEFT), cons);			
	
	
	                
	Point p = new Point((int) scroll_cupom_virtual.getViewport().getViewPosition().getX(), (this.lista_de_produtos_temp.size()*60)+100);  
	scroll_cupom_virtual.getViewport().setViewPosition(p);
	}
	
	
	

	
	
	
	
	
	
	
/********************** operacoes **************************/	
	
	
	

	
	public void getCPF(){
		
		if(this.lista_de_produtos_temp.size() == 0){	
			
		Item item = new Item();
		FormCPF form  = new FormCPF(item);	
		form.mostrar();
			
			if(item.getParamentro("cpf") != null){
			
			this.cpf_consumidor_temp = String.valueOf(item.getParamentro("cpf"));	
			this.rot_cliente.setText(this.cpf_consumidor_temp);
			}
		}
	}
	
	
	
		

	
	
	public void addFormaDePagamento(){				

	FormFormasDePagamento form = new FormFormasDePagamento(this.formas_de_pagamento_temp, this.rot_valor_final.getText());
	form.mostrar();
	
	String aux = "";
	
	for(Item item: this.formas_de_pagamento_temp)
	aux += item!=null?item.getParamentro("descricao")+" / ":"";
	
	this.rot_forma_pagamento.setText(aux.length()>0?aux.substring(0, aux.length()-1):aux);
	}
		
		
	
	
	
	
	
	public void addDesconto(){
		
	Item item = new Item();
	FormDeDescontos form  = new FormDeDescontos(item);	
	form.mostrar();
			
		if(item.getParamentro("tipo") != null){
			
		this.tipo_desconto_temp = item.getParamentro("tipo").toString();
		this.valor_desconto_temp = 	item.getParamentro("valor").toString();
			
		this.atualizaInfoDeDesconto();
		
		this.atualizaPrecoFinal();
		}
	}
		
	
	
	


	public void addConvenio(){
		
	Convenio convenio= new Convenio();
		
	String data_atual = Data.getDataAtual();
	data_atual  = data_atual.substring(6, 10)+"-"+data_atual.substring(3, 5)+"-"+data_atual.substring(0, 2);
	
	FormDeSelecao<Convenio> selectionItemForm = 
								new FormDeSelecao<Convenio>("Adicionar Convênio", convenio, Convenio.class, 
										"conv.status='ATIVO' and (conv.inicio is NULL OR conv.inicio<= '"+data_atual+"') and (conv.fim is NULL OR conv.fim>= '"+data_atual+"')");
	selectionItemForm.mostrar();
							
	if(convenio != null && convenio.getId_convenio()>0)
	this.convenio_temp = new DAO<Convenio>(Convenio.class).get(convenio.getId_convenio());
	
	this.atualizaInfoDeDesconto();
	
	this.atualizaPrecoFinal();
	}
		
	
	
	
	
	
	public void atualizaInfoDeDesconto(){
	
	String rot1 = "";	
	String rot2 = "";
	
	if(!Calculo.stringZero(this.valor_desconto_temp))
	rot1 = (this.tipo_desconto_temp.compareTo("PORCENTAGEM") == 0? "(%) ":"(R$) ")+this.valor_desconto_temp;
		
	
	if(this.convenio_temp!=null && this.convenio_temp.getId_convenio()>0)
	rot2 = (this.convenio_temp.getTipo_desconto().compareTo("PORCENTAGEM") == 0 ||
			this.convenio_temp.getTipo_desconto().compareTo("TOTAL") == 0? "(%) ":"(R$) ")+this.convenio_temp.getValor_desconto();
			
		
	this.rot_desconto.setText(rot1+(rot1.length()>0 && rot2.length()>0?" + ":"")+rot2);		
	}
	
	
	
	
	
	
	public void getCancelarItem(){
		
		if(this.lista_de_produtos_temp.size()> 0){
		
		this.lista_de_produtos_temp.remove(this.lista_de_produtos_temp.size()-1);
			
		GridBagConstraints cons = new GridBagConstraints();   
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.weightx = 1;	
		cons.gridwidth = GridBagConstraints.REMAINDER;	

		this.cupom.add(new JLabel("*********** Último item removido ***********", SwingConstants.CENTER), cons);		
		
		int iRetorno = this.cancelaItemAnterior();
		
		System.out.println("cancelamento de ultimo item add: "+iRetorno);
		
		this.atualizaPrecoFinal();
		
		this.cancelaItemAnterior();
		}	
	}
	
	
	
	
	
	
	
	
	public void getCancelarCupom(){
		
	this.lista_de_produtos_temp.clear();
			
	this.limpaDadosTemporarios();
	
	if(this.nao_gerar_cupom)
	System.out.println("cancelamento de nota: "+this.cancelaCupom());

	}

		
		
		
	
	
	
	
	public void addValorRecebido(){
			
	Item item = new Item();
	FormDeValorRecebido form  = new FormDeValorRecebido(item, this.valor_bruto_temp);	
	form.mostrar();
				
		if(item.getParamentro("valor") != null){
				
		this.valor_recebido_temp = item.getParamentro("valor").toString();	
		this.rot_val_recebido.setText(Calculo.formataValor(this.valor_recebido_temp));
		this.rot_troco.setText(Calculo.formataValor(Calculo.subtrai(this.valor_recebido_temp, this.valor_bruto_temp)));
		}		
	}
	
	
	
	
	
	
	
	public boolean finaliza(){
		
	this.atualizaPrecoFinal();
	
		if(lista_de_produtos_temp.size()==0){
			
		Mensagens.msgDeErro("Compra vazia, nenhum item selecionado.");
		return false;		
		}
	
	DAO<Produto> prod_DAO = new DAO<Produto>(Produto.class);
			
		
		for(Produto produto: lista_de_produtos_temp){
		
		Produto aux = prod_DAO.get(produto.getId_produto()); 
				
			if(aux==null){
			
			Mensagens.msgDeErro("O produto "+produto.getDescricao_abreviada()+" está indisponível.");
			return false;		
			}

			if(aux.getQuantidade() < produto.getQuantidade()){
				
			Mensagens.msgDeErro("A menos em estoque do produto "+produto.getDescricao_abreviada()+" que a quantidade informada.");
			return false;		
			}
		}
		

	
		if(this.formas_de_pagamento_temp.size()==0){
			
		List<FormaDePagamento> padrao = new DAO<FormaDePagamento>(FormaDePagamento.class).get(null, "f_pag.padrao='SIM' and f_pag.status='ATIVO'", null);
		
		
			if(padrao.size()==0){
				
			Mensagens.msgDeErro("Não há forma de pagamento padrão definida. Clique em F2 para definir a forma de pagamento.");
			return false;	
			}
			
		Item forma  = new Item();
		forma.addParamentro("id", padrao.get(0).getId_forma_pag());	
		forma.addParamentro("valor", this.valor_final_temp);
		forma.addParamentro("descricao", padrao.get(0).getDescricao());
		forma.addParamentro("valor_descritivo", "R$: "+Calculo.formataValor(this.valor_final_temp));
		forma.addParamentro("tipo", padrao.get(0).getTipo());
				
		this.formas_de_pagamento_temp.add(forma);	
		}
		else{
	
		String aux = "0.00";
		for(Item item: this.formas_de_pagamento_temp)
		aux = Calculo.soma(aux, item.getParamentro("valor").toString());	
					
			if(Comuns.comparaValorMonetario(this.valor_final_temp, aux)!=0){
			
			Mensagens.msgDeErro("O valor total da compra é diferente da soma dos meios de pagamento.");
			return false;
			}
		}
		
		
		
	GregorianCalendar calendar = new GregorianCalendar();


	this.movimento_temp.setData_fechamento(new java.sql.Date( new Date().getTime()));
	this.movimento_temp.setMin_fechamento(calendar.get(Calendar.MINUTE));
	this.movimento_temp.setHora_fechamento(calendar.get(Calendar.HOUR_OF_DAY));
	
	this.movimento_temp.setCpf_cliente(this.cpf_consumidor_temp);
	this.movimento_temp.setValor_bruto(this.valor_bruto_temp);
	this.movimento_temp.setStatus_pagamento("FECHADO");
	this.movimento_temp.setValor_final(this.valor_final_temp);
	
	if(this.tipo_desconto_temp.compareTo("PORCENTAGEM")==0)
	this.movimento_temp.setDesconto(Calculo.calcPorcentagem(this.valor_bruto_temp, this.valor_desconto_temp));
	else
	this.movimento_temp.setDesconto(this.valor_desconto_temp);
	
	this.movimento_temp.setFk_convenio(this.convenio_temp!=null?this.convenio_temp.getId_convenio():0);
	
		if(this.movimento_temp.getFk_convenio()>0){
		
		if(this.convenio_temp.getTipo_desconto().compareTo("TOTAL")==0)
		this.movimento_temp.setDesconto_convenio(this.valor_bruto_temp);
		
		if(this.convenio_temp.getTipo_desconto().compareTo("PORCENTO")==0)
		this.movimento_temp.setDesconto_convenio(Calculo.calcPorcentagem(this.valor_bruto_temp, this.convenio_temp.getValor_desconto()));
			
		if(this.convenio_temp.getTipo_desconto().compareTo("VALOR")==0)
		this.movimento_temp.setDesconto_convenio(this.convenio_temp.getValor_desconto());
		}
	
	this.movimento_temp.setValor_comissao(Comuns.porcentComissaoPadrao);
	this.movimento_temp.setFk_caixa(Comuns.iDCaixa);
	this.movimento_temp.setFk_vendedor(Comuns.iDVendedor);

	
	int id_movimento = new DAO<Movimento>(Movimento.class).novo(this.movimento_temp);
	
	if(id_movimento==0)
	return false;
	
	DAO<Movimento_X_Produto> movXProd_DAO = new DAO<Movimento_X_Produto>(Movimento_X_Produto.class);
	
		for(Produto produto: lista_de_produtos_temp){
			
		Movimento_X_Produto	movXProd = new Movimento_X_Produto();
		movXProd.setFk_movimento(id_movimento);
		movXProd.setFk_produto(produto.getId_produto());
		movXProd.setQuantidade(produto.getQuantidade());
		movXProd.setPreco(produto.getValor_final());
		
		movXProd_DAO.novo(movXProd);
		
		Produto aux = prod_DAO.get(produto.getId_produto()); 
			
			if(aux!=null){
				
			aux.setQuantidade(aux.getQuantidade() - movXProd.getQuantidade());	
			prod_DAO.altera(aux);
			}
		}
	

		try {
	
			for(Item item: this.formas_de_pagamento_temp){
			
			Movimentos_x_FormasPag x = new Movimentos_x_FormasPag();	
				
			x.setFk_movimento(id_movimento);
	
			x.setFk_forma_de_pagamento(Integer.parseInt(item.getParamentro("id").toString()));
	
			if(item.getParamentro("tipo").toString().compareTo("CREDIARIO")==0)
			x.setData_primeiro_vencimento(new SimpleDateFormat("dd/MM/yyyy").parse(item.getParamentro("vencimento").toString()));
	
			
			if(item.getParamentro("tipo").toString().compareTo("CREDIARIO")==0 ||
								item.getParamentro("tipo").toString().compareTo("CREDITO")==0)
			x.setNum_de_parcelas(Integer.parseInt(item.getParamentro("num_parcela").toString()));
	
					
			x.setValor_total(item.getParamentro("valor").toString());
				
			if(new DAO<Movimentos_x_FormasPag>(Movimentos_x_FormasPag.class).novo(x)<=0)
			return false;	
				
				if(item.getParamentro("tipo").toString().compareTo("CREDIARIO")==0){
					
				Entrada_Saida entrada = new Entrada_Saida();
					
				entrada.setFk_credor(0);	
				entrada.setFk_fornecedor(0);
				entrada.setFk_colaborador(Comuns.iDVendedor);
				entrada.setFk_movimento(id_movimento);
				entrada.setFk_cliente(Integer.parseInt(item.getParamentro("id_cliente").toString()));
				entrada.setTipo_pagamento("PARCELADO");
				entrada.setTipo("ENTRADA");
				entrada.setReferente("CREDIARIO "+Comuns.getCod(Movimento.class, id_movimento));
				entrada.setPorcento_multa(Comuns.porcentMultaPadrao);
				entrada.setPorcento_juros(Comuns.porcentjurosPadraoAoMes);
				entrada.setValor_entrada("0,00");
				entrada.setPorcento_comissao(Comuns.porcentoComissaoVendedor);
				entrada.setValor_total(x.getValor_total());	
				
				try {entrada.setPrimeiro_vencimento(new SimpleDateFormat("dd/MM/yyyy").parse(item.getParamentro("vencimento").toString()));} 
				catch (ParseException e1) {e1.printStackTrace();}
					
				entrada.setData_cadastro(new Date());
				entrada.setStatus("ABERTO");
				entrada.setNum_de_parcelas(x.getNum_de_parcelas());	
					
				int id = new DAO<Entrada_Saida>(Entrada_Saida.class).novo(entrada);
					
				if(id== 0)
				return false;	
						
				DAO<Parcela> parcela_DAO = new DAO<Parcela>(Parcela.class);
					
				calendar.setTime(entrada.getPrimeiro_vencimento());
				
				String valor_parcela = Calculo.dividi(entrada.getValor_total(), ""+x.getNum_de_parcelas());
				
					for(int i = 0, 
								mes = calendar.get(Calendar.MONTH)+1, 
								ano = calendar.get(Calendar.YEAR); i < x.getNum_de_parcelas(); i++){
						
					Parcela parcela = new	Parcela();
					
					try {parcela.setData_vencimento(new SimpleDateFormat("dd/MM/yyyy").parse(
								String.format("%02d/%02d/%04d", calendar.get(Calendar.DAY_OF_MONTH), mes, ano)));}
					catch (ParseException e) {parcela.setData_vencimento(null);}
					
					parcela.setValor_parcela(valor_parcela); 
					parcela.setIndice(i+1);
					parcela.setFk_entrada_saida(id);
					parcela.setStatus("ABERTO");
					
					if(mes < 12)
					mes++;
						else{
						mes = 1;
						ano++;
						}
					
					if(parcela_DAO.novo(parcela)<=0)
					return false;
					}
				}
			}
		}
		catch (NumberFormatException | ParseException e) {e.printStackTrace();}		
		
		
	this.setCupomFiscal();
	
	this.limpaDadosTemporarios();
	
	return true;
	}
	
	
	
	

	
	
	public void setCupomFiscal(){
	
	if(this.nao_gerar_cupom)	
	return;
		
	Barra_De_Progresso progressBar = new Barra_De_Progresso();	
	progressBar.mostrar();
	progressBar.atualizar(1);	
	
	int iRetorno	=  0;

	iRetorno = 	Bematech.AbreCupom(this.cpf_consumidor_temp);
		
	progressBar.atualizar(2);		
	System.out.println("Abertura de cupom: "+iRetorno);
		
		for(Produto produto: this.lista_de_produtos_temp){
		
		iRetorno = Bematech.UsaUnidadeMedida(produto.getUnidade_de_medida());
		
		System.out.println("Unidade de medida ("+produto.getUnidade_de_medida()+"): "+iRetorno);
		
		iRetorno = Bematech.VendeItem(
					produto.getCodigo_ean(), 
					produto.getDescricao_abreviada().length()>25?produto.getDescricao_abreviada().substring(0, 25):produto.getDescricao_abreviada(), 
							Comuns.aliquota_ICMS, 
								produto.getVenda_fracionada().compareTo("SIM")==0?"F":"I", 
										""+produto.getQuantidade(), 
											2, 
											produto.getValor_final(), "%", "00,00");	
		
		System.out.println("vende item "+produto.getCodigo_ean()+": "+iRetorno);
		}
		
	progressBar.atualizar(3);	
			
	iRetorno = Bematech.IniciaFechamentoCupom(
				"D", 
				this.tipo_desconto_temp.compareTo("PORCENTAGEM")==0?"%":"$", 
				this.tipo_desconto_temp.compareTo("PORCENTAGEM")==0?this.valor_desconto_temp.replace(",", "").replace(".", ""):this.valor_desconto_temp);
		
	progressBar.atualizar(1);	
	System.out.println("inicio fechamento: "+iRetorno);	
		

		for(Item aux: this.formas_de_pagamento_temp){
			
		iRetorno = Bematech.EfetuaFormaPagamento(
				aux.getParamentro("descricao").toString().length()>16?aux.getParamentro("descricao").toString().substring(0, 16):aux.getParamentro("descricao").toString(), 
					Calculo.formataValor(aux.getParamentro("valor").toString()));
															
		System.out.println("Aplica forma de pagamento: "+iRetorno);	
		}

	progressBar.atualizar(2);	
	iRetorno = Bematech.TerminaFechamentoCupom(Comuns.frase);

	progressBar.atualizar(1);	
	
	System.out.println("fechamento finalizado: "+iRetorno);
		
	progressBar.dispose();	
	} 
	
	
	
	
	
	
	
	public void getMenuFiscal(){
	
	if(this.nao_gerar_cupom)	
	return;	
		
	FormDeMenuFiscal form = new FormDeMenuFiscal();
	form.mostrar();
	}
		
	
	
	
	
	
	
	public void fecharCaixa(){
		
		if(Mensagens.dialogoDeConfirmacao("Você tem certeza que deseja fechar este caixa? (Não poderá ser reaberto).")){
		
		DAO<Caixa> caixa_DAO = 	new DAO<Caixa>(Caixa.class);
			
		Caixa caixa = caixa_DAO.get(Comuns.iDCaixa);	
			
			if(caixa!=null){	
			
			DAO<Movimento> movimento_DAO = 	new DAO<Movimento>(Movimento.class);
			
			List<Movimento> movimentos = movimento_DAO.get(null, 
						"mov.fk_caixa="+caixa.getId_caixa()+" and mov.status_pagamento='ABERTO'", null);	
	
				if(movimentos.size()>0){
					
				Mensagens.msgDeErro("Ainda Há movimentos abertos neste caixa.");	
				return;
				}
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
				
			caixa.setData_fechamento(calendar.getTime());
			caixa.setHora_fechamento(calendar.get(Calendar.HOUR_OF_DAY));
			caixa.setMin_fechamento(calendar.get(Calendar.MINUTE));
			
			caixa.setFk_user_fechamento(Comuns.iDVendedor);
			caixa.setStatus("FECHADO");
			
			
			movimentos = new DAO<Movimento>(Movimento.class).get(null, 
					"mov.fk_caixa="+caixa.getId_caixa()+" and mov.status_pagamento='FECHADO'", null);

			String valor_parcial = caixa.getValor_inicial();
			
			for(Movimento movimento: movimentos)
			valor_parcial = Calculo.soma(valor_parcial, movimento.getValor_final());
			
			caixa.setValor_fechamento(valor_parcial);
			
			if(!caixa_DAO.altera(caixa))
			return;
	
			
			FormVerCaixa form  = new FormVerCaixa(caixa);
			form.mostrar();
			
			
			encerrar();
			}
			else
			Mensagens.msgDeErro("Referência ao caixa não encontrada.");
		}
			
	}
	
	
	
	
	
	
	
	public void encerrar(){
		
	Configuracao.barraDeInformacoes.relogioStop();
    System.exit(0);	
	}
	
	
	
	
	
	private void removerDescontos(){
		
	this.convenio_temp = null;

	this.tipo_desconto_temp = "";

	this.valor_desconto_temp = "";

	this.atualizaInfoDeDesconto();
	
	this.atualizaPrecoFinal();
	}
	
	
	
	
	
	
	private void mostrarOpcoes(){
		
	FormOpcoes	form = new FormOpcoes();
	form.mostrar();
	}
	
	
	
	

/*************************************************************************************************/	
	
	
	
	

	
	private void addOuvinte(Container container) {  
		
	for (Component c : container.getComponents())       
	addOuvinte((Container)c); 
		
	container.addKeyListener(new OuvintePrincipal());
	} 

	
	
	
	
	
	
	
	private class OuvinteBase extends KeyAdapter{		
		public void keyPressed(KeyEvent ek){	
		
			
		if(ek.getKeyCode() == KeyEvent.VK_F1)
		getCPF();
		

		if(ek.getKeyCode() == KeyEvent.VK_F2)
		addFormaDePagamento();
		

		if(ek.getKeyCode() == KeyEvent.VK_F3)
		addDesconto();
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F4)
		addConvenio();
		
		
		
		
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F5)
		getCancelarItem();
			
		
		if(ek.getKeyCode() == KeyEvent.VK_F6)
		getCancelarCupom();
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F7)
		removerDescontos();
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F8)
		getMenuFiscal();
		
		
		
		
		
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F9)
		fecharCaixa();
		
		
		if(ek.getKeyCode() == KeyEvent.VK_F12)
		finaliza();
		}
	} 
	
	
	

	

	private class OuvintePrincipal extends OuvinteBase{		
		public void keyPressed(KeyEvent ek){
		
		super.keyPressed(ek);
			
		
		if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
		encerrar();		
		
		
		if(ek.getKeyCode() == KeyEvent.VK_HOME)
		mostrarOpcoes();	
		
		}
	} 
	
	
	
	
	
/********************** emissor fiscal **************************/	
	
	

	
	private int cancelaItemAnterior(){
	
	if(this.nao_gerar_cupom)	
	return 0;	
	
	return Bematech.CancelaItemAnterior();	
	}
	
	
	
	
	private int cancelaCupom(){
	
	if(this.nao_gerar_cupom)	
	return 0;	
		
	return Bematech.CancelaCupom();	
	}
		
	
	
	
	
	
	
	/********************** classes internas **************************/	
	
	
	
	
	
	private class FormOpcoes extends Dialogo{

		
	private static final long serialVersionUID = 1L;




		public FormOpcoes() {
			
		super("Opções do Sistema", 800, 220, false);

		this.adicionarComponentes();
		
		this.addOuvinte(this);   
		}

		
		
		
		@Override
		public void adicionarComponentes() {
		
		GridBagConstraints cons = new GridBagConstraints();   
			
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weighty  = 1;
		cons.weightx = 1;
		cons.insets= new Insets(2, 2, 2, 2);
				
		JPanel panel_opcoes = new JPanel(new GridBagLayout());	
		panel_opcoes.setBackground(Preferencias.COR_DE_FUNDO_PANE_CAIXA);
		panel_opcoes.setBorder(BorderFactory.createTitledBorder("Opções"));  
		this.add(panel_opcoes, cons);	
			
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 1;	
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		
		cons.ipadx = 5;
		JButton bt1=  new JButton("Informar CPF (F1)", new ImageIcon(getClass().getResource("/icons/cpf.png")));
		panel_opcoes.add(bt1, cons);
		bt1.setFocusable(false);
		
		JButton bt4=  new JButton("Form. Pag. (F2)", new ImageIcon(getClass().getResource("/icons/credores.png")));
		panel_opcoes.add(bt4, cons);
		bt4.setFocusable(false);
			
		JButton bt5=  new JButton("Descontos (F3)", new ImageIcon(getClass().getResource("/icons/desconto.png")));
		panel_opcoes.add(bt5, cons);
		bt5.setFocusable(false);
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		JButton bt3=  new JButton("Convênio (F4)", new ImageIcon(getClass().getResource("/icons/convenio_mini.png")));
		panel_opcoes.add(bt3, cons);
		bt3.setFocusable(false);
			
			
	
		cons.gridwidth = 1;	
		JButton bt7=  new JButton("Canc. Item (F5)", new ImageIcon(getClass().getResource("/icons/remover.png")));
		panel_opcoes.add(bt7, cons);
		bt7.setFocusable(false);
			
		JButton bt8=  new JButton("Canc. Nota (F6)", new ImageIcon(getClass().getResource("/icons/pagamentos_mini.png")));
		panel_opcoes.add(bt8, cons);
		bt8.setFocusable(false);
			
		JButton bt10=  new JButton("Exc. Descontos (F7)", new ImageIcon(getClass().getResource("/icons/pagamentos_mini.png")));
		panel_opcoes.add(bt10, cons);
		bt10.setFocusable(false);
			
		cons.gridwidth = GridBagConstraints.REMAINDER;
		JButton bt6=  new JButton("M. Fiscal (F8)", new ImageIcon(getClass().getResource("/icons/menu.png")));
		panel_opcoes.add(bt6, cons);
		bt6.setFocusable(false);

			
			
		cons.gridwidth = 1;
		JButton bt2=  new JButton("Fechar Caixa (F9)", new ImageIcon(getClass().getResource("/icons/fechar.png")));
		panel_opcoes.add(bt2, cons);
		bt6.setFocusable(false);
			
		panel_opcoes.add(new JLabel(""), cons);
			
		
		panel_opcoes.add(new JLabel(""), cons);
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		JButton bt9=  new JButton("Finalizar (F12)", new ImageIcon(getClass().getResource("/icons/icon_confirma.png")));
		panel_opcoes.add(bt9, cons);
		bt9.setFocusable(false);
		
		
			bt1.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
					
			getCPF();
			}});
			
			
			
			bt4.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			addFormaDePagamento();
			}});	
			
			
			
			
			bt5.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			addDesconto();
			}});

			
			

			bt3.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			addConvenio();
			}});
			
			

			
			
			bt7.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			getCancelarItem();
			}});
			
			
			
			
			bt8.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			getCancelarCupom();
			}});
			
			
			
			
			bt9.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			finaliza();
			}});
			
			
			
			
			bt6.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
							
			getMenuFiscal();
			}});	
				
				
			
			
			bt2.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
								
			fecharCaixa();
			}});	
					
			
			
			
			bt10.addActionListener( new ActionListener(){		
			@Override
			public void actionPerformed( ActionEvent event ){
								
			removerDescontos();
			}});	
					
			
			
				
		}

		
		
		
		@Override
		public boolean acaoPrincipal() {return false;}
		
		
	

		public void fecharForm(){
			
		dispose();	
		}
		
		
		
		
		private class Ouvinte extends OuvinteBase{		
			public void keyPressed(KeyEvent ek){
			
			super.keyPressed(ek);
				
			
			if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
			fecharForm();
			
			}
		} 
		
		
		
		

		
		private void addOuvinte(Container container) {  
			
		for (Component c : container.getComponents())       
		addOuvinte((Container)c); 
			
		container.addKeyListener(new Ouvinte());
		} 
		
		
	}
	
	
	
	
	
	
	
	
}
