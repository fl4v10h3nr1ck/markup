
package comuns;

import javax.swing.JOptionPane;





public class Mensagens {


	



	public static void msgDeErroAoSalvar(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao salvar as informa��es.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}


	
	public static void msgDeErroAoDeletar(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao excluir as informa��es.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}

	
	
	public static void msgDeErroAoAlterar(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao alterar as informa��es.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}

	
	
	public static void msgDeErroAoObter(){
		
	JOptionPane.showMessageDialog(null, "Um erro ocorreu ao recuperar as informa��es.", "ERRO", JOptionPane.ERROR_MESSAGE);	
	}
	
	
	
	public static void msgDeSucessoAoSalvar(){
		
	JOptionPane.showMessageDialog(null, "Informa��es salvas com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	
	

	public static void msgDeSucessoAoAlterar(){
		
	JOptionPane.showMessageDialog(null, "Informa��es atualizadas com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
		
	
	
	

	public static void msgDeSucessoAoDeletar(){
		
	JOptionPane.showMessageDialog(null, "Remo��o realizada com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
		
	
	
	
	public static void msgDeRelatorioGeradoComSucesso(){
		
	JOptionPane.showMessageDialog(null, "Relat�rio gerado com sucesso.", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
		
	
	
	
	
	public static void msgDeErro(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.ERROR_MESSAGE);	
	}
	
	
	
	public static void msgDeSucesso(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	
	
	
	public static void msgDeAlerta(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "Aten��o", JOptionPane.WARNING_MESSAGE);	
	}
		
	
	
	
	public static boolean dialogoDeConfirmacao(String msg){
	
	int dialogButton = JOptionPane.YES_NO_OPTION;
	int dialogResult = JOptionPane.showConfirmDialog(null, msg, "Confirma��o",dialogButton);
	
	return dialogResult==0? true:false;
	}
	
	
	
}	