package pdv;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import principal.clientes.FormNovoCliente;
import DAO.DAO;
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.beans.Cliente;
import componentes.beans.FormaDePagamento;
import componentes.beans.Item;
import componentes.janelas.Dialogo;
import comuns.Calculo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;




public class FormFormasDePagamento extends Dialogo{

	

private static final long serialVersionUID = 1L;



private CampoMoeda tf_valor;
private CampoLimitado tf_valor_parcela;
private JComboBox<String> cb_formas_pag;

private CampoLimitado tf_cliente;
private CampoLimitadoSoDigito tf_n_parcelas;
private CampoData tf_1_vencimento;
private int idCliente;


private JButton bt_novo_cliente;
private JButton bt_salvar;

protected JTable tb_formas;		
private DefaultTableModel modelo;		

private String valor_total;

private List<FormaDePagamento> lista_de_formas;

private List<Item> lista_retorno;




	public FormFormasDePagamento(List<Item> lista_retorno, String valor_total) {
	
	super("Selecione a Forma de Pagamento", 600, 410, false);
	
	this.lista_retorno = lista_retorno;
	
	this.valor_total =  valor_total;
	
	this.modelo = new DefaultTableModel(null, new String[]{"Código", "Forma de Pagamento", "Valor"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	
	
	this.tb_formas = new JTable(this.modelo);
	this.tb_formas.setRowHeight(20); 
	
	this.tb_formas.getColumnModel().getColumn(0).setPreferredWidth(100);
	this.tb_formas.getColumnModel().getColumn(1).setPreferredWidth(300);
	this.tb_formas.getColumnModel().getColumn(2).setPreferredWidth(120);
	
	this.adicionarComponentes();
	
	
	this.atualizaTabela();
	
	
	this.tf_valor.setText(this.getValorRestante());
	}

	
	
	
	
	@Override
	public void adicionarComponentes() {
	
	GridBagConstraints cons = new GridBagConstraints();   
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	
	cons.insets = new Insets(2,2,2,2);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	JPanel	p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);	
	
	
	cons.weightx = 1;
	cons.insets = new Insets(0,2,2,2);
	JPanel	p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.insets = new Insets(0,2,2,2);
	JPanel	p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p3, cons);	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.insets = new Insets(2,2,0,2);
	cons.gridwidth = 1;
	cons.gridheight = 2;
	
	JLabel lb_valor = new JLabel(Calculo.formataValor(this.valor_total));
	lb_valor.setFont(new Font("Dialog", Font.PLAIN, 35));
	p1.add(lb_valor, cons);
	
	cons.gridheight = 1;
	p1.add(new JLabel("<html>Meio de pagamento:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html>Valor (R$)<font color=red>*</font></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("<html>Vl. Parcela (R$)<font color=red>*</font></html>"), cons);
	
	cons.gridwidth = 1;
	cons.insets = new Insets(0,2,2,2);
	this.cb_formas_pag = new JComboBox<String>(this.getFormasDePagamento());
	p1.add(this.cb_formas_pag, cons);

	tf_valor = new CampoMoeda(10);
	p1.add(this.tf_valor, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	tf_valor_parcela = new CampoLimitado(10);
	p1.add(this.tf_valor_parcela, cons);
	tf_valor_parcela.setEnabled(false);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.insets = new Insets(2,2,0,2);
	cons.gridwidth = 1;
	cons.weightx = 4;
	p2.add(new JLabel("<html>Cliente:<font color=red>*</font></html>"), cons);
	cons.weightx = 0;
	p2.add(new JLabel(""), cons);
	cons.weightx = 2;
	p2.add(new JLabel("<html>Nº Parcelas:<font color=red>*</font></html>"), cons);
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p2.add(new JLabel("<html>1º vencimemto:<font color=red>*</font></html>"), cons);
	
	
	cons.gridwidth = 1;
	cons.weightx = 4;
	cons.insets = new Insets(0,2,2,2);
	tf_cliente = new CampoLimitado(10);
	p2.add(this.tf_cliente, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	bt_novo_cliente  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_novo_cliente.setToolTipText("Cadastrar um novo cliente.");
	p2.add(bt_novo_cliente, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 2;
	tf_n_parcelas = new CampoLimitadoSoDigito(10);
	p2.add(this.tf_n_parcelas, cons);
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	tf_1_vencimento = new CampoData();
	p2.add(this.tf_1_vencimento, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weightx = 0;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	bt_salvar  = new JButton("Adicionar ", new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_salvar.setToolTipText("Cadastrar forma de pagamento");
	p2.add(bt_salvar, cons);
	
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2,2,2,2);
	p3.add(new JScrollPane(this.tb_formas), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.weighty  = 0;	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.anchor = GridBagConstraints.WEST;
	JButton bt_deleta  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_remove_mini.png")));
	bt_deleta.setToolTipText("Remover forma de pagamento selecionada");
	p3.add(bt_deleta, cons);
	
	

		bt_novo_cliente.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		addNovoCliente();
		}});

	
	
		this.tf_cliente.addFocusListener(new FocusAdapter(){
				
			@Override 
			public void focusGained(FocusEvent e) {
				
			bt_novo_cliente.requestFocusInWindow();
			
			Comuns.removeIndicadoresDeErro(tf_cliente);
			
			addCliente();
			}	
		});
		
		
		
		
		
		this.tf_n_parcelas.addFocusListener(new FocusAdapter(){
			
			@Override 
			public void focusLost(FocusEvent e) {
				
			calculaParcela();
			}	
		});
		
		
		
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		acaoPrincipal();
		}});

		
		
		
		
		bt_deleta.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			
		int index = 	tb_formas.getSelectedRow();
			
			if(index >= 0){
			
			lista_retorno.remove(index);
			
			tf_valor.setText(Calculo.formataValor(getValorRestante()));

			atualizaTabela();
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela para remoção.");

		}});
		
		
		
		
		
		cb_formas_pag.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
			
			setOpcoesDePagamento();
			}	
		});
		
		

	
	this.addAcoesDeTeclas();
		
		
		
	this.setOpcoesDePagamento();	
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
		
	textFieldList.add(this.tf_valor);
	textFieldList.add(this.tf_1_vencimento);
	textFieldList.add(this.tf_n_parcelas);
	textFieldList.add(this.tf_cliente);
	
	Comuns.addEventoDeFoco(textFieldList);
	}

	

	
	private void addCliente(){
		
	this.tf_n_parcelas.requestFocusInWindow();
			
	Comuns.removeIndicadoresDeErro(this.tf_cliente);
			
	Cliente retorno = new Cliente();
					
	FormDeSelecao<Cliente> selectionItemForm = 
							new FormDeSelecao<Cliente>("Adicionar Cliente", retorno, Cliente.class, null);
	selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_cliente() > 0){
									
		this.idCliente = retorno.getId_cliente();
		this.tf_cliente.setText(retorno.getNome_razao());
		}
	}
	
	
	
	
	private void addNovoCliente(){
	
	Cliente cliente = new Cliente();	
		
	FormNovoCliente	form =  new FormNovoCliente(cliente);
	form.mostrar();
	
		if(cliente.getId_cliente()>0){
		
		this.idCliente = cliente.getId_cliente();
		this.tf_cliente.setText(cliente.getNome_razao());
		}
	}
	
	
	
	
	private void setOpcoesDePagamento(){
		
		if(this.ehCrediario()){
		

		this.tf_cliente.setEnabled(true);
		this.tf_n_parcelas.setEnabled(true);
		this.tf_1_vencimento.setEnabled(true);
		this.bt_novo_cliente.setEnabled(true);
		this.tf_n_parcelas.setText("1");
		}
		else{
		
			if(this.ehCredito()){
			this.tf_n_parcelas.setEnabled(true);
			this.tf_n_parcelas.setText("1");
			}
			else{
							
			this.tf_n_parcelas.setEnabled(false);
			this.tf_n_parcelas.setText("");
			}
							
			
		this.tf_cliente.setEnabled(false);
		this.tf_1_vencimento.setEnabled(false);
		this.bt_novo_cliente.setEnabled(false);	
		
		this.idCliente=  0;
		this.tf_cliente.setText("");
		this.tf_1_vencimento.setText("");
		this.tf_valor_parcela.setText("");
		}
		
	}
	
	
	
	
	
	private String[] getFormasDePagamento(){
		
	this.lista_de_formas = new DAO<FormaDePagamento>(FormaDePagamento.class).get(null, "f_pag.status ='ATIVO' ORDER BY f_pag.padrao ASC", null);
	
	String[] formas = new String[this.lista_de_formas.size()];
		
	for(int i= 0; i < this.lista_de_formas.size() ; i++ )
	formas[i] = this.lista_de_formas.get(i).getDescricao();
	
	return formas;
	}
	

	
	
	
	
	private void atualizaTabela(){
		
	this.modelo.setNumRows(0);
	String[] dados = new String[3];
		
		for(Item aux: this.lista_retorno){
		
		dados[0] = Comuns.getCod(FormaDePagamento.class, (int)(aux.getParamentro("id")));
		dados[1] = aux.getParamentro("descricao").toString();
		dados[2] = "R$: "+Calculo.formataValor(aux.getParamentro("valor_descritivo").toString());
	
		this.modelo.addRow(dados);	
		}
	}
	
	
	
	
	
	
	private String getValorRestante(){
		
	String valor_restante = this.valor_total;
		
	for(Item aux: this.lista_retorno)
	valor_restante = Calculo.subtrai(valor_restante, aux.getParamentro("valor").toString());
				
	return valor_restante;
	}





	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;	
	
	int indice = this.cb_formas_pag.getSelectedIndex();			
	
	Item forma  = new Item();
	forma.addParamentro("id", this.lista_de_formas.get(indice).getId_forma_pag());	
	forma.addParamentro("valor", this.tf_valor.getText());
	forma.addParamentro("descricao", this.lista_de_formas.get(indice).getDescricao());
	forma.addParamentro("tipo", this.lista_de_formas.get(indice).getTipo());
	
	
		if(this.ehCrediario()){
		
		forma.addParamentro("id_cliente", this.idCliente);
		forma.addParamentro("vencimento", this.tf_1_vencimento.getText());		
		}
		
		if(this.ehCredito() || this.ehCrediario()){

		forma.addParamentro("num_parcela", this.tf_n_parcelas.getText());		
		forma.addParamentro("valor_parcela", this.tf_valor_parcela.getText());		
		forma.addParamentro("valor_descritivo", this.tf_valor_parcela.getText()+" x "+Calculo.formataValor(this.tf_valor.getText()));
		
		this.tf_n_parcelas.setText("1");
		}	
		else
		forma.addParamentro("valor_descritivo", "R$: "+Calculo.formataValor(this.tf_valor.getText()));
		

	this.lista_retorno.add(forma);	
	
	String valor_restante = this.getValorRestante();
	this.tf_valor.setText(Calculo.formataValor(Calculo.subtrai(valor_restante, this.tf_valor.getText())));
		
	this.atualizaTabela();	
	
	this.tf_valor.setText(valor_restante);
	
	return true;
	}
	
	
	
	
	
	
	private boolean validacao(){
	
		
		if(this.tf_valor.getText().length() == 0 || Calculo.stringZero(this.tf_valor.getText())){
			
		Mensagens.msgDeErro("Informe o valor para o meio de pagamento selecionado.");
		Comuns.textFieldErroMode(this.tf_valor);
		return false;	
		}
	
		
	String valor_restante = this.getValorRestante();

		if(Calculo.stringParaDouble(this.tf_valor.getText()) > Calculo.stringParaDouble(valor_restante)){
				
		Mensagens.msgDeErro("O valor total para os meios de pagamento não deve ser maior que o valor total da compra.");
		Comuns.textFieldErroMode(this.tf_valor);
		return false;	
		}	
		
		
		
		if(this.ehCrediario()){
			
			
			if(this.idCliente==0){
			
			Mensagens.msgDeErro("Selecione ou cadastre um novo cliente.");
			Comuns.textFieldErroMode(this.tf_cliente);
			return false;	
			}
			

			if(!this.tf_1_vencimento.validacao()){
				
			Mensagens.msgDeErro("A data para o primeiro vencimento é inválida.");
			Comuns.textFieldErroMode(this.tf_1_vencimento);
			return false;	
			}
		}	
		
		if(this.ehCrediario() || this.ehCredito()){	
			
			if(this.tf_n_parcelas.getText().length()==0 || Integer.parseInt(this.tf_n_parcelas.getText())==0){
				
			Mensagens.msgDeErro("Informe o número de parcelas.");
			Comuns.textFieldErroMode(this.tf_n_parcelas);
			return false;	
			}		
		}
		
	return true;
	}
	
	
	
	
	
	
	
	private void addAcoesDeTeclas(){
		
		
		cb_formas_pag.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
			if(ek.getKeyCode() == KeyEvent.VK_RIGHT || ek.getKeyCode() == KeyEvent.VK_ENTER)
			tf_valor.requestFocus();		
			
			if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
			dispose();
			}
		});
	
	
	
		tf_valor.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
				if(ek.getKeyCode() == KeyEvent.VK_ENTER){
				
				if(ehCrediario())
				tf_cliente.requestFocus();
				else if(ehCredito())
				tf_n_parcelas.requestFocus();	
				else
				bt_salvar.requestFocus();
				}
					
			
			if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
			dispose();
			}
		});
	
	
		
		
	
		bt_salvar.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
				if(ek.getKeyCode() == KeyEvent.VK_ENTER){
				
				acaoPrincipal();
				
				cb_formas_pag.requestFocus();
				}
				
				
				if(ek.getKeyCode() == KeyEvent.VK_UP){
					
				if(ehCrediario())
				tf_1_vencimento.requestFocus();
				else if(ehCredito())
				tf_n_parcelas.requestFocus();	
				else
				cb_formas_pag.requestFocus();
				}	
				
			if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
			dispose();
			}
		});
		
		
		
		
		this.bt_novo_cliente.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
					
				if(ek.getKeyCode() == KeyEvent.VK_ENTER){
				
				addNovoCliente();
				
				tf_n_parcelas.requestFocus();
				}
			
			}
		});
		
		
		
		KeyAdapter padrao  = 	new KeyAdapter(){
			
			public void keyPressed(KeyEvent ek){
		
			if(/*ek.getKeyCode() == KeyEvent.VK_RIGHT || */ek.getKeyCode() == KeyEvent.VK_ENTER)
			ek.getComponent().transferFocus();
			
			if(ek.getKeyCode() == KeyEvent.VK_ESCAPE)
			dispose();
			}
		};
		
		
		
	this.tf_1_vencimento.addKeyListener(padrao);	
	this.tf_n_parcelas.addKeyListener(padrao);	
	this.tf_cliente.addKeyListener(padrao);	
	}
	
	
	
	
	
	
	private boolean ehCrediario(){
		
	return this.lista_de_formas.get(this.cb_formas_pag.getSelectedIndex()).getTipo()!=null &&
		   this.lista_de_formas.get(this.cb_formas_pag.getSelectedIndex()).getTipo().compareTo("CREDIARIO")==0;		
	}
	
	
	
	
	
	

	private boolean ehCredito(){
		
	return this.lista_de_formas.get(this.cb_formas_pag.getSelectedIndex()).getTipo()!=null &&
		   this.lista_de_formas.get(this.cb_formas_pag.getSelectedIndex()).getTipo().compareTo("CREDITO")==0;		
	}
	
	
	
	
	
	
	private void calculaParcela(){
	
	String valor = "";
			
		if(this.ehCrediario()){
		
		valor = "0,00";
			

		if(!(this.tf_n_parcelas.getText().length()==0 || Integer.parseInt(this.tf_n_parcelas.getText())==0))
		valor = Calculo.formataValor(
				Calculo.dividi(this.getValorRestante(), this.tf_n_parcelas.getText()));			
		}
	
	this.tf_valor_parcela.setText(Calculo.formataValor(valor));
	}
	
	
	
	
	
}
