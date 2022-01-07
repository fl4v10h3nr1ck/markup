package principal.inventario;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.DAO;

import componentes.Rotulo;
import componentes.beans.Fornecedor;
import componentes.beans.Inventario;
import componentes.beans.Inventario_Fornecedor;
import componentes.beans.Item;
import comuns.Comuns;
import comuns.Data;
import comuns.Preferencias;




public class FormAlterInventario extends FormInventarioBase{




private static final long serialVersionUID = 1L;




	public FormAlterInventario(Inventario inventario) {
	
	super("Item de Inventário", 650, 500, inventario);	
	
	this.setInfosAdicionais();
	
	this.adicionarComponentes();
	
	this.setValores();
	
	List<Inventario_Fornecedor> lista_aux= 
			new DAO<Inventario_Fornecedor>(Inventario_Fornecedor.class).get(null, "invXforn.fk_inventario="+inventario.getId_inventario(), null);

	DAO<Fornecedor> fornecedor_dao = new DAO<Fornecedor>(Fornecedor.class);		
			for(Inventario_Fornecedor aux: lista_aux){
			
			Fornecedor fornecedor = fornecedor_dao.get(aux.getFk_fornecedor());
			
				if(fornecedor !=null){
					
				Item item = new Item();
					
				item.addParamentro("id", fornecedor.getId_fornecedor());
				
				item.addParamentro("descricao", fornecedor.getNome_razao());
				item.addParamentro("valor", aux.getValor());
			
				this.lista_fornecedores.add(item);
				}
			}
			
			
	this.atualizaTabelaDeFornecedores();
	}


	
	
	
	private void setInfosAdicionais(){
		
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	JPanel infoPanel = new JPanel(new GridBagLayout());
	infoPanel.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(infoPanel, cons);
			
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	infoPanel.add(new Rotulo("<html><b>Cadastrado em:</b> "+Data.converteDataParaString(this.inventario.getData_cadastro())+" | <b>Código: </b>"+Comuns.getCod(Inventario.class, this.inventario.getId_inventario())+"</html>"), cons);

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.weightx = 1;
	infoPanel.add(new JLabel(""), cons);
	}
	
	
	
	

	public void setValores(){
	
	this.tf_nome.setText(this.inventario.getNome());
	this.tf_descricao.setText(this.inventario.getDescricao());	
	this.tf_ean.setText(this.inventario.getCod_ean());	
	this.tf_valor_custo.setText(this.inventario.getValor_custo());
	}
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validation())
	return false;
	
	this.inventario.setNome(tf_nome.getText());
	this.inventario.setDescricao(this.tf_descricao.getText());
	this.inventario.setCod_ean(this.tf_ean.getText());
	this.inventario.setValor_custo(this.tf_valor_custo.getText());
	
	if(!new DAO<Inventario>(Inventario.class).altera(this.inventario))
	return false;	


	DAO<Inventario_Fornecedor> x_DAO = new DAO<Inventario_Fornecedor>(Inventario_Fornecedor.class);
		
	x_DAO.removeSemConfirmacao("fk_inventario="+this.inventario.getId_inventario());
		
		
		for(Item item: this.lista_fornecedores){
				
		Inventario_Fornecedor aux = new Inventario_Fornecedor();
				
		aux.setFk_fornecedor(Integer.parseInt(item.getParamentro("id").toString()));
		aux.setFk_inventario(this.inventario.getId_inventario());
		aux.setValor(item.getParamentro("valor").toString());
			
		x_DAO.novo(aux);
		}
		
	return true;	
	}





	
	
	
}
