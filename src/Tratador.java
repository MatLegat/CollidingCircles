import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Tratador implements ActionListener {
	Container1 c;
	public void actionPerformed(ActionEvent e){
		c.tick();
	}

}
