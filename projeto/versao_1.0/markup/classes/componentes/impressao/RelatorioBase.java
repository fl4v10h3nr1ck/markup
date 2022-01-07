package classes.componentes.impressao;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.componentes.endereco.beans.Endereco;
import classes.componentes.impressao.util.Util;
import classes.comuns.Comuns;
import classes.comuns.Configuracoes;
import classes.dao.DAO;
import classes.principal.empresa.Empresa;






public abstract class RelatorioBase extends Relatorio{




	public RelatorioBase(String titulo){
		
	super(titulo);	
	
	this.setFonteGeral(Configuracoes.preferencias.rel_fonte_normal);
	
	this.setPapel_de_parede(this.getMarcadagua());
	}
	
	
	
	
	
	public BufferedImage getLogo(){
		
	BufferedImage logo = null;
		try {
		
		if(new File(Configuracoes.preferencias.path_logo_relatorios).exists())		
		logo = ImageIO.read(new File(Configuracoes.preferencias.path_logo_relatorios));
		else
		logo = ImageIO.read(getClass().getResource("/icons/logo.png"));
		} 
		catch (IOException e) {
		
		e.printStackTrace();
		return null;
		}
	
	return logo;
	}
	
	
	
	

	
	public BufferedImage getMarcadagua(){
		
	BufferedImage logo = null;
		try {
		
		if(new File(Configuracoes.preferencias.path_marcadagua_relatorios).exists())
		logo = ImageIO.read(new File(Configuracoes.preferencias.path_marcadagua_relatorios));
		else
		logo = ImageIO.read(getClass().getResource("/icons/logo_pp.png"));
		} 
		catch (IOException e) {
		
		e.printStackTrace();
		return null;
		}
	
	return logo;
	}
	
		

	
	
	public void getInfosDaEmpresa(Grupo grupo){
		
	if(grupo==null)
	return;
	
	Empresa empresa= new DAO<Empresa>(Empresa.class).getPrimeiroOuNada(null, null, "emp.id_empresa DESC");		

	if(empresa == null)
	empresa = new Empresa();
	
	Font fonte_micro = Configuracoes.preferencias.rel_fonte_micro;
	Font fonte_normal_negrito = Configuracoes.preferencias.rel_fonte_normal_negrito;
	
	String aux = empresa.getRazao_social()!= null?empresa.getRazao_social():"";
	
	grupo.novaLinha();
	
		if(aux.length()>0){
		
		grupo.setAlturaDeLinha(12);
		grupo.addTexto(aux, Util.getPosicaoXCentralizado(this, fonte_normal_negrito, aux)+3, fonte_normal_negrito);
		}

	grupo.setAlturaDeLinha(10);
	
	aux = empresa.getCnpj() != null && empresa.getCnpj().length()>0?empresa.getCnpj():"";
	
		if(aux.length()>0){
		
		grupo.novaLinha();
		grupo.addTexto(aux, Util.getPosicaoXCentralizado(this, fonte_micro, aux), fonte_micro);
		}
	
	aux = Comuns.getDescricaoEndereco(new DAO<Endereco>(Endereco.class).get(empresa.getFk_endereco()));
			
		if(aux.length()>0){
		
		grupo.novaLinha();
		grupo.addTexto(aux, Util.getPosicaoXCentralizado(this, fonte_micro, aux), fonte_micro);
		}
		
	aux =  "";
	
	if(empresa.getTel_1()!=null && empresa.getTel_1().length()>0)
	aux +=	empresa.getTel_1();
	
	if(empresa.getTel_2()!=null && empresa.getTel_2().length()>0)
	aux +=	(aux.length()>0?" | ":"")+empresa.getTel_2();
			
	if(empresa.getEmail()!=null && empresa.getEmail().length()>0)
	aux +=	(aux.length()>0?" | ":"")+empresa.getEmail();
	
	
		if(aux.length()>0){
		
		grupo.novaLinha();		
		grupo.addTexto(aux, Util.getPosicaoXCentralizado(this, fonte_micro, aux), fonte_micro);
		}
		
	aux = empresa.getSite() != null && empresa.getSite().length()>0 ? "visite nosso site: "+empresa.getSite():"";
		
		if(aux.length()>0){
			
		grupo.novaLinha();
		grupo.addTexto(aux, Util.getPosicaoXCentralizado(this, fonte_micro, aux), fonte_micro);	
		}
	}

	

	
	
}
