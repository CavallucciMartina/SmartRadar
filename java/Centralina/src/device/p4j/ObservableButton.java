package device.p4j;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import device.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ObservableButton extends device.ObservableButton {

	private boolean isPressed;
	private ButtonFrame buttonFrame;
	
	public ObservableButton(int pinNum){
		try {
			buttonFrame = new ButtonFrame(pinNum);
			buttonFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean isPressed() {
		return isPressed;
	}
	
	void setPressed(boolean state){
		isPressed = state;
		if (isPressed){
			this.notifyEvent(new ButtonPressed(this));
		} else {
			this.notifyEvent(new ButtonReleased(this));
		}
	}
	
	class ButtonFrame extends JFrame implements MouseListener {

		private static final long serialVersionUID = 1L;

		public ButtonFrame(int pin){
		    super("Button Emu");
		    setSize(200,50);
		    JButton button = new JButton("Button on pin: "+pin);
		    button.addMouseListener(this);
		    getContentPane().add(button);
		    addWindowListener(new WindowAdapter(){
		      public void windowClosing(WindowEvent ev){
		        System.exit(-1);
		      }
		    });
		  }

		public void mousePressed(MouseEvent e) {
			setPressed(true);
		}

		public void mouseReleased(MouseEvent e) {
			setPressed(false);
		}

		public void mouseEntered(MouseEvent e) {
		}
		
		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {			
		}
	}

}
