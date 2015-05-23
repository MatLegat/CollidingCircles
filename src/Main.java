import java.awt.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		InteracaoUsuario iu= new InteracaoUsuario();
		
		int nroCirculos=iu.perguntaInt("Gerar quantos circulos?", "5");
		int raio = iu.perguntaInt("Qual o raio dos circulos?", "30");
		Container c = new Container1(nroCirculos, raio);
		f.setContentPane(c);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(820,650+35);
		f.setVisible(true);
			
	}

}
