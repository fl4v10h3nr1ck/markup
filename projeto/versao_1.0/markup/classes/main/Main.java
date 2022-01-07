
package classes.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;









import principal.ConfigDAO;
import classes.comuns.Comuns;
import classes.comuns.Configuracoes;
import classes.principal.Principal;





public class Main {

	
	
	
	public static void main(String[] args){
		
	try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}
	catch ( ClassNotFoundException | 
			InstantiationException | 
			IllegalAccessException | 
		    UnsupportedLookAndFeelException e) {e.printStackTrace();}	
			
		
	Comuns.num_compilacao = "23082016100";
			
	Comuns.software_code  = "4910";
			
	Comuns.nome_sistema = "markup100";

	Comuns.versao_sistema = "1.0.0";
			
			
	Configuracoes.connector = new ConfigDAO(Comuns.nome_sistema).getConexao();
			
	if(Configuracoes.connector==null)
	return;

/*
			PagueOAluguel registro = new PagueOAluguel();
			
			PagueOAluguel.insertRegisterKeyEnabled = true;
			
			PagueOAluguel.SYSTEM_NAME = "sis"+Comuns.nome_sistema;
			
			PagueOAluguel.SYSTEM_VERSION = Comuns.versao_sistema;
			
			PagueOAluguel.serverURL = "http://www.mscsolucoes.com.br/util/licenseServer/";
			
			PagueOAluguel.accessKey2Server = "61fFndxo4s1Z0x00ad2c7gC9sAw5rmNH";

			Configuracoes.registroAtual= registro.getRegistroAtual();

			
				if(registro.validacao(Configuracoes.registroAtual)){   
				
				VoceNaoVaiPassar gestorDeUsuarios = new VoceNaoVaiPassar(Configuracoes.connector, new RecursosDoSistema());
					
					if(gestorDeUsuarios.prepara()){
			
					boolean login = false;
					
					if(login)	
					Configuracoes.usuarioAtual = gestorDeUsuarios.login("dev", "d5g03dm2q10dcpa3");
						else{	
			
						Configuracoes.usuarioAtual = new 	UsuarioAtual();
						Configuracoes.usuarioAtual.setId(1);
						Configuracoes.usuarioAtual.setNome("admin");
						}
					
					
						if(Configuracoes.usuarioAtual!=null && Configuracoes.usuarioAtual.getId() > 0){
							*/
	
						Comuns.setInfos();
	
						Principal principal = new Principal();
						principal.mostrar();
						
						//FormDeExibicao form = new FormDeExibicao(new Rel__Compra(new DAO<Compra>(Compra.class).get(2)));
						//form.mostrar();	
						
						
						/*
						Configuracoes.painelDeSincronizacao = new PainelDeStatusDeSincronismo(new DiaLabMensageiro());
						
						Principal desktop = new Principal();	
						desktop.mostrar();
						
						Configuracoes.painelDeSincronizacao.getMonitor().servidor.getMensageiro().envia();
						}
					}
				}*/
						
						
	}	




}
