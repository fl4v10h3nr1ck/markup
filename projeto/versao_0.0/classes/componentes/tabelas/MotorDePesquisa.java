
package componentes.tabelas;



import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import componentes.anotacoes.Anot_TB_Coluna;





public class MotorDePesquisa<T> extends JPanel{


	

private static final long serialVersionUID = 1L;

		

public JTextField tf_termos;
public JButton bt_pesquisar;
public JComboBox <Object>  locais;

public JTable table;
public ModeloDeTabela<T> tableModel;

	
private String titulo;

public JLabel infoTable;

public JPanel painel_de_opcoes;

public JScrollPane scroll;


	public MotorDePesquisa(String titulo, Class<?> tipoDeClasse){
	
	this(titulo, tipoDeClasse, true);
	}
	

	
	
	
	public MotorDePesquisa(String titulo, Class<?> TipoDeclasse, boolean paginacao){
		
	this.titulo = titulo;
	this.setLayout(new GridBagLayout());
	this.setBackground(Color.white);

	this.tableModel = new ModeloDeTabela<T>(TipoDeclasse, paginacao);
	
	this.adicionarComponentes();

		for (Field field : TipoDeclasse.getDeclaredFields()) {
		
		if (field.isAnnotationPresent(Anot_TB_Coluna.class))
		this.table.getColumnModel().getColumn(field.getAnnotation(Anot_TB_Coluna.class).posicao()).setPreferredWidth(field.getAnnotation(Anot_TB_Coluna.class).comprimento()*(1400/100));	
		}
	}
		

	
	
	
	
	public void adicionarComponentes(){
				
	    
	GridBagConstraints cons = new GridBagConstraints();   
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(4, 5, 4, 2);
	this.add(new JLabel("<html><b>"+this.titulo+"</b></html>"), cons);
	cons.insets = new Insets(4, 1, 4, 0);
	this.add(new JSeparator(SwingConstants.HORIZONTAL), cons);

	cons.gridwidth = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Color.WHITE);
	this.add(p1, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	this.painel_de_opcoes = new JPanel(new GridBagLayout());
	this.painel_de_opcoes.setBackground(Color.WHITE);	
	this.add(this.painel_de_opcoes, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.gridwidth = 1;
	cons.weightx = 0.7;
	p1.add(new JLabel("Termos:"), cons);
	cons.weightx = 0.3;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("Local:"), cons);

	
	cons.gridwidth = 1;
	cons.weightx = 0.7;
	this.tf_termos = new JTextField();
	p1.add(this.tf_termos, cons);
		
	cons.weightx = 0.25;
	this.locais = new JComboBox<Object>(tableModel.getLocais());
	p1.add(this.locais, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.ipadx = 40;
	this.bt_pesquisar = new JButton("Pesquisar", new ImageIcon(getClass().getResource("/icons/pesquisa.png")));
	p1.add(this.bt_pesquisar, cons);

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty  = 1;
	cons.weightx = 1;	
	cons.ipadx = 0;
	cons.insets = new Insets(2, 1, 0, 0);
	this.table = new JTable( tableModel);
	this.table.setRowHeight(25);
	
	scroll = new JScrollPane(this.table, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	this.add(scroll, cons);
	this.table.getParent().setBackground(new Color(221, 221, 221 ));	
	
    TableCellRenderer defRenderer = this.table.getTableHeader().getDefaultRenderer();  
    	
    	this.table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {  
     
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,  
		Object value,  
        boolean isSelected,  
        boolean hasFocus,  
        int row,  
        int column) {  	
			
        Component c = defRenderer.getTableCellRendererComponent(  
        table,  
        value,  
        isSelected,  
        hasFocus,  
        row,  
        column);  
         
        	if (c instanceof DefaultTableCellRenderer) {  
                
        	DefaultTableCellRenderer dtcr = (DefaultTableCellRenderer) c;  
            dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
            dtcr.setBorder(new CompoundBorder(dtcr.getBorder(), new EmptyBorder(0, 4, 0, 4)));  
            dtcr.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 
            }  
     	
        return c;  
        }});  
/*	
    	class BordaCelula extends JLabel implements TableCellRenderer{
    	
		private static final long serialVersionUID = 1L;

			public BordaCelula(){
	    	setOpaque(true);
	    	}
	    	
	    	public Component getTableCellRendererComponent (JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column){
	   
	    
	    	this.setBorder(BorderFactory.createLineBorder(Preferencias.COR_BORDA_CELULA_DE_TABELA));
	    	
	    	this.setText(value==null?"":value.toString());
	        return this;
	    	}
    	}
     
    	
    	this.table.setDefaultRenderer(Object.class, new BordaCelula());	
*/    	
    	
    this.infoTable = new JLabel("");
    	if(this.tableModel.paginacao){
    		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty  = 0;
		cons.insets = new Insets(2, 0, 0, -1);
			JPanel panel = new JPanel(){
			
			private static final long serialVersionUID = 1L;
	
			@Override   
	        public void paintComponent(Graphics g){    
	      
			
			Graphics2D g2 = (Graphics2D) g.create();	
	
			GradientPaint paint = new GradientPaint(  getWidth()/2, 0, new Color(79, 149, 209),  getWidth()/2,  getHeight(), Color.black);					
			g2.setPaint( paint);
			g2.fillRect( 0 , 0 ,  getWidth(),  getHeight());		
	        }};
		panel.setLayout(new GridBagLayout());
		this.add(panel, cons);
		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = 1;
		cons.weighty  = 0;
		cons.weightx = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		JButton bt_prev = new JButton(new ImageIcon(getClass().getResource("/icons/prev.png")));
		bt_prev.setToolTipText("Página Anterior");
		panel.add(bt_prev, cons);
		
		JButton bt_pri_page = new JButton(new ImageIcon(getClass().getResource("/icons/pri_pag.png")));
		bt_pri_page.setToolTipText("Primeira Página");
		panel.add(bt_pri_page, cons);
		
		cons.insets = new Insets(0, 5, 0, 5);
		panel.add(infoTable, cons);
	
		cons.insets = new Insets(0, 0, 0, 0);
		JButton bt_utl_page = new JButton(new ImageIcon(getClass().getResource("/icons/ult_pag.png")));
		bt_utl_page.setToolTipText("Última Página");
		panel.add(bt_utl_page, cons);
		
		JButton bt_next = new JButton(new ImageIcon(getClass().getResource("/icons/next.png")));
		bt_next.setToolTipText("Próxima Página");
		panel.add(bt_next, cons);
    	
    	
		bt_prev.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
			    	
			prevPage();
			}});
		
			
			
			bt_pri_page.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
			firstPage();
			}});

			
			
			bt_utl_page.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
			lastPage();
			}});

			
			
			bt_next.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent event ){
				    	
			nextPage();	
			}});
    	}
	
		bt_pesquisar.addActionListener( new ActionListener(){
		public void actionPerformed( ActionEvent event ){
			    	
		search();
		}});
	
		
		
		this.tf_termos.addActionListener(new ActionListener (){
		@Override 	
		public void actionPerformed(ActionEvent e) {  
			
		search();
		}});
	}
	
	
	
	
	
	public void update(){
			
	this.tableModel.atualiza();	
	this.setInfoTable();
	}
	
	
	
	
	
	public void search() {
			
	this.tableModel.atualiza(this.tf_termos.getText(), String.valueOf(this.locais.getSelectedItem()));	
	this.setInfoTable();
	}
	
	

	
	public void nextPage(){
		
	this.tableModel.nextPage(this.tf_termos.getText(), String.valueOf(this.locais.getSelectedIndex()));	
	this.setInfoTable();
	}
		


		
	public void prevPage(){
		
	this.tableModel.prevPage(this.tf_termos.getText(), String.valueOf(this.locais.getSelectedIndex()));	
	this.setInfoTable();
	}
			
		
		
		
	public void firstPage(){
		
	this.tableModel.firstPage(this.tf_termos.getText(), String.valueOf(this.locais.getSelectedIndex()));	
	this.setInfoTable();
	}
				
		
		
		
	public void lastPage(){
		
	this.tableModel.lastPage(this.tf_termos.getText(), String.valueOf(this.locais.getSelectedIndex()));	
	this.setInfoTable();
	}
	
	
	
	
	public void setInfoTable(){
	
	this.infoTable.setText("<html><font color=white><b>Mostrando "+this.tableModel.currentIndex+" à "+(this.tableModel.currentIndex+this.tableModel.getRowCount())+" de "+this.tableModel.countItens+" iten(s)</b></font></html>");	
	}
	
	
	
}		