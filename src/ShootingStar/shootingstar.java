package ShootingStar;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class shootingstar extends JFrame{
	
	 private Image bufferImage;
	 private Graphics screenGraphic;
	
	 private Image mainScreen = new ImageIcon("src/images/우주배경.PNG").getImage();
	 Image cimage = mainScreen.getScaledInstance(1280,720,Image.SCALE_SMOOTH); 
	 
	 static boolean isMainScreen;
	 
		
	 private Image winScreen = new ImageIcon("src/images/youwin.png").getImage();
	 Image wimage = winScreen.getScaledInstance(1280,720,Image.SCALE_SMOOTH);
	 
	 static boolean iswinScreen;
	 
	 private Image loseScreen = new ImageIcon("src/images/youlose.png").getImage();
	 Image limage = loseScreen.getScaledInstance(1280,720,Image.SCALE_SMOOTH);
	 
	 static boolean isloseScreen;
	 
	 
     private game game = new game();
    
	   public shootingstar() {
	        setTitle("Shooting Game");
	        setUndecorated(true);
	        setSize(main.SCREEN_WIDTH, main.SCREEN_HEIGHT);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	        setLayout(null);      
	        addKeyListener(new KeyListener());     
	        gameStart();
  
	    }
	   
   

	    private void gameStart() {
	

	    	      isMainScreen= true;
	    	      iswinScreen = false;
	    	      isloseScreen = false;
	                game.start();
	        
	    }
	 

	   
	   public void paint(Graphics g) {
	        bufferImage = createImage(main.SCREEN_WIDTH, main.SCREEN_HEIGHT);
	        screenGraphic = bufferImage.getGraphics();
	        screenDraw(screenGraphic);
	        g.drawImage(bufferImage, 0, 0, null);
	       
	    }
	   
	    public void screenDraw(Graphics g) {
	    	
	        if (isMainScreen) {
		        g.drawImage(cimage, 0, 0, null);
		        game.gameDraw(g);
	        }
	        if (iswinScreen) {
	            g.drawImage(wimage, 0, 0, null);
	        }
	        if (isloseScreen) {
	            g.drawImage(limage, 0, 0, null);
	        }
	     
	     
	        this.repaint();
	    }
	    
	    
	    class KeyListener extends KeyAdapter {
	    	    public void keyPressed(KeyEvent e) {
	        	  
	            switch (e.getKeyCode()) {
	                case KeyEvent.VK_W:
	                    game.setUp(true);                     
	                    break;
	                case KeyEvent.VK_S:
	                    game.setDown(true);
	                 
	                    break;
	                case KeyEvent.VK_SPACE:
	                    game.setShooting(true);
	                     break;
            
	            
	            }
	    
	            }
	        
	    public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    game.setUp(false);
       
                    break;
                case KeyEvent.VK_S:
                    game.setDown(false);
            
                    break;
                case KeyEvent.VK_SPACE:
                    game.setShooting(false);
         
                    break;
                    
                    
               
              
            }
            
	    
        }}}
	        
	   
	    	
	  

    
	    
	    
	    
	    
	    


