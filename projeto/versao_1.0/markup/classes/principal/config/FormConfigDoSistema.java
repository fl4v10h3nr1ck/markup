package classes.principal.config;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import principal.cadastros.postos.Posto;
import principal.cadastros.tabelas.resultados.Resultado;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import componentes.FormDeSelecao;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import dao.DAO;



public class FormConfigDoSistema extends Dialogo{

	

private static final long serialVersionUID = 1L;



protected CampoLimitado tf_cidade_padrao;
protected CampoLimitado tf_pais_padrao;
protected CampoLimitado tf_cod_posto_padrao;
protected CampoMoeda tf_desconto_padrao;
protected CampoLimitado tf_resultado_padrao;


protected CampoLimitadoSoDigito tf_prazo_de_exame;
protected CampoLimitadoSoDigito tf_itens_por_pagina;

protected JComboBox<String> tipo_empressora;

protected JComboBox<String> num_de_mapas_por_pag;

protected JComboBox<String> necessita_autorizacao;


protected Config_do_Sistema config;


private int id_posto;

private int id_resultado;



	public FormConfigDoSistema(Config_do_Sistema config) {
		
	super("Configurações do Sistema", 700, 270);
	
	this.config = config;
	
	this.adicionarComponentes();
	
	
		if(this.config != null ){
		
		tf_cidade_padrao.setText(this.config.getCidade_padrao());
		tf_pais_padrao.setText(this.config.getPais_padrao());
		
		tf_desconto_padrao.setText(this.config.getDesconto_padrao());
		tf_prazo_de_exame.setText(""+this.config.getPrazo_de_exame());
		tf_itens_por_pagina.setText(""+this.config.getItens_por_pagina());	
		
		if(this.config.getImpressora_matricial()==null || 
				this.config.getImpressora_matricial().compareTo("NAO")==0)
		this.tipo_empressora.setSelectedIndex(0);
		else
		this.tipo_empressora.setSelectedIndex(1);
		
		
		if(this.config.getNum_de_mapas_por_pag()==4)
		this.num_de_mapas_por_pag.setSelectedIndex(1);
		else
		this.num_de_mapas_por_pag.setSelectedIndex(0);
		
		if(this.config.getAutorizacao_de_resultado()!=null && this.config.getAutorizacao_de_resultado().compareTo("SIM")==0)
		this.necessita_autorizacao.setSelectedIndex(1);
		else
		this.necessita_autorizacao.setSelectedIndex(0);
			
			
		
		
		
			if(this.config.getFk_posto_padrao()>0){
				
			Posto aux = new DAO<Posto>(Posto.class).get(this.config.getFk_posto_padrao());
				
				if(aux!=null){
				
				this.id_posto = this.config.getFk_posto_padrao();	
				this.tf_cod_posto_padrao.setText(aux.getDescricao());
				}
			}
			
			
			if(this.config.getFk_resultado_numerico()>0){
				
			Resultado aux = new DAO<Resultado>(Resultado.class).get(this.config.getFk_resultado_numerico());
					
				if(aux!=null){
					
				this.id_resultado = aux.getId_resultado();	
				this.tf_resultado_padrao.setText(aux.getDescricao());
				}
			}
		}
	}
	

	
	
	
	public void adicionarComponentes(){
			
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 5, 2);
		
	JPanel p1 = new JPanel(new GridBagLayout());
	this.add(p1, cons);
	p1.setBorder(BorderFactory.createTitledBorder("Valores Padrão"));  
	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("Cidade Padrão:"), cons);
	p1.add(new JLabel("País Padrão:"), cons);
	p1.add(new JLabel("Desconto Padrão (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("<html>Prazo de Exame (em dias):<font color=red>*</font></html>"), cons);
	

	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_cidade_padrao = new CampoLimitado(150);
	p1.add(this.tf_cidade_padrao, cons);
	
	this.tf_pais_padrao = new CampoLimitado(150);
	p1.add(this.tf_pais_padrao, cons);
	
	this.tf_desconto_padrao = new CampoMoeda(4);
	p1.add(this.tf_desconto_padrao, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_prazo_de_exame = new CampoLimitadoSoDigito(3);
	p1.add(this.tf_prazo_de_exame, cons);
	

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("Posto Padrão:"), cons);
	p1.add(new JLabel("<html>Itens por Página:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("Resultado Numérico Padrão:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("Tipo de Impressora:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_cod_posto_padrao = new CampoLimitado(150);
	this.tf_cod_posto_padrao.setEditable(false);
	p1.add(this.tf_cod_posto_padrao, cons);
	
	this.tf_itens_por_pagina = new CampoLimitadoSoDigito(3);
	p1.add(this.tf_itens_por_pagina, cons);
	
	this.tf_resultado_padrao = new CampoLimitado(150);
	this.tf_resultado_padrao.setEditable(false);
	p1.add(this.tf_resultado_padrao, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(this.tipo_empressora= new JComboBox<String>(new String[]{"JATO ou LASER", "MATRICIAL"}), cons);
	

	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("Num. Mapas p. Pag.:"), cons);
	p1.add(new JLabel("Autorização de resultado:"), cons);
	p1.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.num_de_mapas_por_pag= new JComboBox<String>(new String[]{"2", "4"}), cons);
	p1.add(this.necessita_autorizacao= new JComboBox<String>(new String[]{"NAO", "SIM"}), cons);
	p1.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.ipadx = 15;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/salvar.png")));
	bt_salvar.setToolTipText("Salvar dados da empresa");
	this.add(bt_salvar, cons);
		
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		


		this.tf_cod_posto_padrao.addMouseListener( new MouseAdapter(){		
		@Override
		public void mouseClicked(MouseEvent e) {
		
		if(!tf_cod_posto_padrao.isEnabled())	
		return;
			
		bt_salvar.requestFocusInWindow();
		addPosto();	
		}});
		
		
		
		this.tf_resultado_padrao.addMouseListener( new MouseAdapter(){		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		if(!tf_resultado_padrao.isEnabled())	
		return;
				
		bt_salvar.requestFocusInWindow();
		addResultado();	
		}});
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(this.tf_itens_por_pagina);
	textFieldList.add(this.tf_prazo_de_exame);
	
	Comuns.addEventoDeFoco(textFieldList);
	}
	
	
	
	
	
	
	public  boolean acaoPrincipal(){
		
	if(!this.validation())	
	return false;
	
	if(this.config==null)
	this.config = new Config_do_Sistema();
	
	this.config.setCidade_padrao(this.tf_cidade_padrao.getText());
	this.config.setPais_padrao(this.tf_pais_padrao.getText());
	this.config.setFk_posto_padrao(this.id_posto);
	this.config.setDesconto_padrao(this.tf_desconto_padrao.getText());
	this.config.setPrazo_de_exame(Integer.parseInt(this.tf_prazo_de_exame.getText()));
	this.config.setItens_por_pagina(Integer.parseInt(this.tf_itens_por_pagina.getText()));
	this.config.setFk_resultado_numerico(this.id_resultado);
	this.config.setImpressora_matricial(this.tipo_empressora.getSelectedIndex()==0?"NAO":"SIM");
	this.config.setNum_de_mapas_por_pag(this.num_de_mapas_por_pag.getSelectedIndex()==0?2:4);
	this.config.setAutorizacao_de_resultado(this.necessita_autorizacao.getSelectedItem().toString());

	
	boolean retorno = false;
	
	if(this.config.getId_configuracoes()<=0)
	retorno = new DAO<Config_do_Sistema>(Config_do_Sistema.class).novo(this.config)>0;
	else
	retorno = new DAO<Config_do_Sistema>(Config_do_Sistema.class).altera(this.config);
		

	if(retorno)
	Comuns.setInfosDeEmpresa();
	
	return retorno;
	}
	
	
	
	
	

	protected boolean validation(){
	
		
		if(this.tf_prazo_de_exame.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o prazo para entrega do exame.");
		Comuns.textFieldErroMode(this.tf_prazo_de_exame);
		return false;	
		}
		
		
		if(this.tf_itens_por_pagina.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o número de itens por página de pesquisa.");
		Comuns.textFieldErroMode(this.tf_itens_por_pagina);
		return false;	
		}
		
	return true;	
	}
	
	
	

	
	private void addPosto(){
		
	Posto retorno = new Posto();	
		
	FormDeSelecao<Posto> selectionItemForm = 
						new FormDeSelecao<Posto>("Adicionar Posto de Coleta Padrão", retorno, Posto.class, null);
	selectionItemForm.mostrar();
					
		if(retorno != null && retorno.getId_posto() > 0){
								
		this.id_posto = retorno.getId_posto();
		this.tf_cod_posto_padrao.setText(retorno.getDescricao());		
		}
	}

	
	
	
	
	private void addResultado(){
		
	Resultado retorno = new Resultado();	
		
	FormDeSelecao<Resultado> selectionItemForm = 
							new FormDeSelecao<Resultado>("Adicionar Resultado Padrão", retorno, Resultado.class, null);
	selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_resultado() > 0){
									
		this.id_resultado = retorno.getId_resultado();
		this.tf_resultado_padrao.setText(retorno.getDescricao());		
		}	
	}

	
}
