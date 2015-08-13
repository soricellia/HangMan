package hangMan;
	import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
		public class PaintImage extends JPanel{
			
			private Image img;
			
			public PaintImage(){
				img=null;
			}
			public PaintImage(String image){
				img=Toolkit.getDefaultToolkit().createImage(image);
			}//end PaintImage
			public void paintComponent(Graphics g){
				g.drawImage(img,0, 0, null);
				repaint();
			}
			
		}//end class PaintImage
		
