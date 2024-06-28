package ShootingStar;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class game extends Thread {
	
	 private Image player = new ImageIcon("src/images/player.png").getImage();
	 Image cplayer = player.getScaledInstance(130,100,Image.SCALE_SMOOTH);
	 
	 private Image enemy = new ImageIcon("src/images/player2.png").getImage();
	 Image cenemy = enemy.getScaledInstance(130,100,Image.SCALE_SMOOTH);
	 


	    private int cnt;


	    private int playerHeight = cplayer.getHeight(null);
	    private int playerY = (main.SCREEN_HEIGHT - playerHeight) / 2;
	    private int playerSpeed = 10;
	    

	    private int enemyY ;
	    private int win;
	    
	    
	    
	    private attack playerAttack;
	    
	    private ArrayList<attack> playerAttackList = new ArrayList<attack>();
	    
	    private enemyattack enemyAttack;
	    
	    private ArrayList<enemyattack> enemyAttackList = new ArrayList<enemyattack>();

	
	    private boolean up, down, shooting;
	    private int shoot ;
	    String message, inputMessage;

	
	    @Override
	    public void run() {
	    	   try {
		    		Socket sk = new Socket("localhost",8000);

		    		BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
		    		PrintWriter  out = new PrintWriter(sk.getOutputStream());

           reset();
	   
	            while (true) {
	              
	           
	                    try {
	                        Thread.sleep(10);
	                        keyProcess();
	                        if(shoot==1) {
	                        	 message="1";
	                        }else { 
	                         message=its(playerY);
	                        }
		                	out.println(message);
	                        out.flush();
	                         inputMessage = in.readLine();  
                   	        if(inputMessage.equals("end")) {
                   	        	break;
                   	        }                 
	                        playerAttackProcess();
	                        enemyAttackProcess();
	                        enemycontorl(inputMessage);
	                        cnt++;

	                    } catch (InterruptedException e) {
                       e.printStackTrace();
	                    }
                
	                if(win>=3) {
	                	out.println("end");
                        out.flush();
	                	break;
	                }
	            
	            
	            
	      } 
	    	     }
			      catch (IOException a) {
		  	    		// TODO Auto-generated catch block
		  	    		a.printStackTrace();
		  	    	}
	    
	    	   if(win>=3) {

		    	      shootingstar.isMainScreen= false;
		    	      shootingstar.isloseScreen =false ;
		    	      shootingstar.iswinScreen =true ;
               	
               }else {
	    		      shootingstar.isMainScreen= false;
		    	      shootingstar.isloseScreen =true ;
		    	      shootingstar.iswinScreen =false ;
	    		      
               }

	    }
	    
	    
	    private String its(int a) {
	    	
	    	String b=  Integer.toString(a);
	    	return  b;
	    	
	    }
	    
       private int sti(String a) {
	    	
	    	int b=Integer.parseInt(a);
	    	return  b;
	    	
	    }

	    
	

	    private void reset() {
	    
	        cnt = 0;
	        playerY = (main.SCREEN_HEIGHT - playerHeight) / 2;
	        enemyY = (main.SCREEN_HEIGHT - playerHeight) / 2;
	       
	        playerAttackList.clear();
	        enemyAttackList.clear();

			
		}
	    
	    private void playerAttackProcess() {
	        for (int i = 0; i < playerAttackList.size(); i++) {
	            playerAttack = playerAttackList.get(i);
	            playerAttack.fire();
	            if (playerAttack.x > 1100 && playerAttack.x < 1200 && playerAttack.y > enemyY-50 && playerAttack.y < enemyY + 50) {
	            	win++;
	                playerAttackList.remove(playerAttack);
	            }	            
	        }
       
	    }
	    

	    
	    private void enemyAttackProcess() {
	    	
	        for (int i = 0; i < enemyAttackList.size(); i++) {
	        	enemyAttack = enemyAttackList.get(i);
	        	enemyAttack.fire();
	        	 if (enemyAttack.x > 0 && enemyAttack.x < 100 && enemyAttack.y > playerY-50 && enemyAttack.y < playerY + 50) {	          
		                playerAttackList.remove(playerAttack);
		            }	
	

	        }
	    }
	    
	    
	    
	    private void enemycontorl(String a) {
	    	
	    	int b = sti(a);
	    	if(b==1 &&  cnt % 30 == 0)  {
	            enemyAttack = new enemyattack(1100, enemyY);
	            enemyAttackList.add(enemyAttack);
	    
	    		
	    	}else {
	    		this.enemyY = b;
	    		
	    	}
	    	
	    }



		public void gameDraw(Graphics g) {
	        playerDraw(g);
	        enemyDraw(g);
	        for (int i = 0; i < enemyAttackList.size(); i++) {
	        	enemyAttack = enemyAttackList.get(i);
	            g.drawImage(enemyAttack.cimage, enemyAttack.x, enemyAttack.y, null);
	        }
	        for (int i = 0; i < playerAttackList.size(); i++) {
	            playerAttack = playerAttackList.get(i);
	            g.drawImage(playerAttack.cimage, playerAttack.x, playerAttack.y, null);
	        }

	    }
		
	
		 public void enemyDraw(Graphics g) {
	        g.drawImage(cenemy, 1150, enemyY, null);
	    }
	 
	    
	    public void playerDraw(Graphics g) {
	        g.drawImage(cplayer, 0, playerY, null);
	    }
	    
	    public void keyProcess() {
	        if (up && playerY - playerSpeed > 0) playerY -= playerSpeed;
	        if (down && playerY + playerHeight + playerSpeed < 610) playerY += playerSpeed;
	        if (shooting && cnt % 30 == 0) {
		  
		        shoot =1;

	        	
	            playerAttack = new attack(100, playerY );
	            playerAttackList.add(playerAttack);
 
	        }else {shoot =0;}

	    }

	    

	    public void setUp(boolean up) {
	        this.up = up;
	

	    }

	    public void setDown(boolean down) {
	        this.down = down;


	    }
	    public void setShooting(boolean shooting) {
	      this.shooting = shooting;

	    }
	    

	 
}
