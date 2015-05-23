import javax.swing.JOptionPane;


public class InteracaoUsuario {

	public int perguntaInt(String pergunta, String caixa){
		return Integer.parseInt(JOptionPane.showInputDialog(pergunta, caixa));
	}
	
}
