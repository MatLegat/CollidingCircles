import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Ticker implements ActionListener{

	public Ticker(Container1 c){
		this.c = c;
	}
	private Container1 c;
	public void actionPerformed(ActionEvent e){
		c.tick();
	}
}
