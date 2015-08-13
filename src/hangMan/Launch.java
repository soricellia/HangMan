package hangMan;

import javax.swing.JApplet;

public class Launch extends JApplet {
	    public void start() {
	       new Thread("application main Thread") {
	          public void run() { runApplication(); }
	       }.start();
	    }

	    private void runApplication() {
	       main(new String[0]);
	    }

	

	public static void main(String[] args) {
		HangManFrame hmf = new HangManFrame();
		
	}

}
