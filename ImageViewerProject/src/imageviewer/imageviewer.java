package imageviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;




public class imageviewer extends JFrame{
	private  int width;
	private  int height;
	private int [] image;
	private FileInputStream fis;
	private DrawingPanel dp;
	private javax.swing.Timer timer;
	private Color imageColor[];
	private int i=0,j=0;
	private int colorNum;
	private int whiteSpace;
	private String fileNo;
	private JButton filebutton,animation1,animation2,animation3;
	
	imageviewer() throws IOException{
		dp=new DrawingPanel();
		filebutton = new JButton("こんいちわ私の名前はドガです");
		animation1=new JButton("➡");
		animation2=new JButton("⬇");
		animation3=new JButton("🔄");
		
		insidefile();
		
		this.setLayout(null);
		this.add(dp);
		this.add(filebutton);
		filebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				try {
						
					insidefile();
					i=0;
					j=0;
					dp.repaint();
						
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		this.add(animation1);
		animation1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				i=0;
				j=height;
				dp.repaint();
						
			}
		});	
		this.add(animation2);
		animation2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				i=width;
				j=0;
				dp.repaint();
						
			}
		});	
		this.add(animation3);
		animation3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				i=0;
				j=0;
				dp.repaint();
						
			}
		});	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
//--------------------------------------------------------------------------------------------------------------		
	}
	private void insidefile() throws IOException{	
	try {
			
			JFileChooser filechooser=new JFileChooser("/eclipse/Workspace/ImageViewerProject") ;
			if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File file=filechooser.getSelectedFile();
				fis=new FileInputStream(file);
				fileNo=getFileNumber();
				System.out.println(fileNo);
				
				skipWhiteSpace();
				
				width=readInteger();
				System.out.println(width);
				
				
				height=readInteger();
				System.out.println(height);
				
				timer=new Timer(10 ,new TimerListener());
				timer.start();
				
				
				if(fileNo.equals("P1")) {
					dp.setSize(width*2,height*2);
					this.setSize(width*2+16, height*2+170);
					filebutton.setBounds((width*2-90)/2, height*2+20, 140, 100);
					animation1.setBounds((width*2-180)/4, height*2+5, 55, 40);
					animation2.setBounds((width*2-180)/4, height*2+45, 55, 40);
					animation3.setBounds((width*2-180)/4, height*2+85, 55, 40);
					
					image =new int[width*height];
					for(int i=0;i<width*height;i++) {
								image[i]=readInteger();
								
						}
	
				}
				if(fileNo.equals("P2")) {
					dp.setSize(width,height);
					this.setSize(width+16, height+170);
					filebutton.setBounds((width-90)/2, height+20, 140, 100);
					animation1.setBounds((width-180)/4, height+5, 55, 40);
					animation2.setBounds((width-180)/4, height+45, 55, 40);
					animation3.setBounds((width-180)/4, height+85, 55, 40);
					
					
					colorNum=readInteger();
					System.out.println(colorNum);
					
					image=new int[width*height];
					imageColor=new Color[width*height];
					for(int i=0;i<width*height;i++) {
							image[i]=readInteger();	
							
						}
					for (int i=0;i<width*height;i++) {
						imageColor[i] = new Color((255/colorNum)*image[i],(255/colorNum)*image[i],(255/colorNum)*image[i]);
						}
		
				}
				if(fileNo.equals("P3")) {
					
					dp.setSize(width,height);
					this.setSize(width+16, height+170);
					filebutton.setBounds((width-90)/2, height+20, 140, 100);
					animation1.setBounds((width-180)/4, height+5, 55, 40);
					animation2.setBounds((width-180)/4, height+45, 55, 40);
					animation3.setBounds((width-180)/4, height+85, 55, 40);
					
					colorNum=readInteger();
					System.out.println(colorNum);
					
					
					imageColor=new Color[width*height];
					for (int i=0;i<width*height;i++) {
						
						imageColor[i] = new Color((255/colorNum)*readInteger(),(255/colorNum)*readInteger(), (255/colorNum)*readInteger());				
					}
				}
				if(fileNo.equals("P4")) {
					dp.setSize(width,height);
					this.setSize(width+16, height+170);
					filebutton.setBounds((width-90)/2, height+20, 140, 100);
					animation1.setBounds((width-180)/4, height+5, 55, 40);
					animation2.setBounds((width-180)/4, height+45, 55, 40);
					animation3.setBounds((width-180)/4, height+85, 55, 40);
					
					int bitcounter=128;
					imageColor=new Color[width*height];
					image=new int[width*height];
					int indexct=0;
					byte[] pixelcolor = new byte[((width/8)+1)*height];
					pixelcolor[0]=(byte) whiteSpace;
					for(int i=1;i<((width/8)+1)*height;i++) {
						
						pixelcolor[i]=(byte)fis.read();	
					}
					for(int i=0;i<((width/8)+1)*height;i++) {
						for(int j=0;j<8;j++) {
							
							image[indexct]=(pixelcolor[i] & bitcounter);
							bitcounter=bitcounter/2;
							indexct++;
							
							if((indexct) % width==0){
								break;
							}
						}
						bitcounter=128;
					}	
				}
				if(fileNo.equals("P5")) {
				
					dp.setSize(width,height);
					this.setSize(width+16, height+170);
					filebutton.setBounds((width-90)/2, height+20, 140, 100);
					animation1.setBounds((width-180)/4, height+5, 55, 40);
					animation2.setBounds((width-180)/4, height+45, 55, 40);
					animation3.setBounds((width-180)/4, height+85, 55, 40);
					
					colorNum=readInteger();
					System.out.println(colorNum);
					
					image=new int[width*height];
					imageColor=new Color[width*height];
					image[0]=whiteSpace;
					for(int i=1;i<width*height;i++) {
							try {
								image[i]=(255/colorNum)*fis.read();
							} catch (IOException e) {
								e.printStackTrace();
							}	
							
						}
					for (int i=0;i<width*height;i++) {
						imageColor[i] = new Color((255/colorNum)*image[i],(255/colorNum)*image[i],(255/colorNum)*image[i]);
						}
					
				}	
				
				if(fileNo.equals("P6")) {
					dp.setSize(width*2,height*2);
					this.setSize(width*2+16, height*2+170);
					filebutton.setBounds((width*2-90)/2, height*2+20, 140, 100);
					animation1.setBounds((width*2-180)/4, height*2+5, 55, 40);
					animation2.setBounds((width*2-180)/4, height*2+45, 55, 40);
					animation3.setBounds((width*2-180)/4, height*2+85, 55, 40);
					
					colorNum=readInteger();
					System.out.println(colorNum);
						
					imageColor=new Color[width*height];
					int color1=whiteSpace;
					int color2= fis.read();
					int color3=fis.read();
					imageColor[0] =new Color (color1, color2, color3);
					
					for (int i=1;i<width*height;i++) {
						color1=fis.read();
						color2= fis.read();
						color3=fis.read();
						imageColor[i] = new Color((255/colorNum)*color1,(255/colorNum)*color2, (255/colorNum)*color3);				
					}
				}	
				
			}
			dp.setBackground(new Color(255, 255, 255));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			}		
		
	
//--------------------------------------------------------------------------------------	
	}
	private String getFileNumber() {		
		byte[] colorNum = new byte[2];
		try {
			fis.read(colorNum);
		} catch (IOException e) {}	
		
		return new String(colorNum);
	}
	
	private void skipWhiteSpace() {
		try {
			whiteSpace = fis.read();			
			while(Character.isWhitespace(whiteSpace)) {
				whiteSpace = fis.read();			
			}			
		} catch (IOException e1) {}
	}
	
	private int readInteger() {	
		String String = "";
			
		while(!Character.isWhitespace(whiteSpace)) {
			String = String + (whiteSpace - '0');
			try {
				whiteSpace = fis.read();
			} catch (IOException e) {}
			
		}
		
		skipWhiteSpace();
		return Integer.parseInt(String);
	}
	
	//------------------------------------------------------------------------------------------------
	
	class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
				dp.repaint();
	
				if( j<height) {
					j=j+(1);

					}
					if(j>height) {
						j = height;
						}		
				if(i<width ) {
					i=i+(1);
					if(i>width) {
						i = width;
						}
				}

			}		
		}	
		
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(fileNo.equals("P1")) {
				for(int r=0;r<i;r++) {
					for(int c=0;c<j;c++) {
						g.setColor(image[c*width+r]==0 ? Color.WHITE: Color.BLACK);
						g.fillRect(r*2,c*2,2,2);	
			}
			}
			}
			if(fileNo.equals("P2")) {
				for(int r=0;r<i;r++) {
					for(int c=0;c<j;c++) {
						g.setColor(imageColor[(c*height+r)]);
						g.fillRect(r, c, 1, 1);
						}	
					}	
				}
			if(fileNo.equals("P3")) {
				for(int c=0;c<j;c++) {
					for(int r=0;r<i;r++) {
						g.setColor(imageColor[(c*height+r)]);
						g.fillRect(r,c, 1, 1);
					}
				}
				}
			if(fileNo.equals("P4")) {
				for(int c=0;c<j;c++) {
					for(int r=0;r<i;r++) {
						g.setColor(image[c*width+r]==0 ? Color.WHITE: Color.BLACK);
						g.fillRect(r,c,1,1);	
			}
			}
			}
			
			if(fileNo.equals("P5")) {
				for(int c=0;c<j;c++) {
					for(int r=0;r<i;r++) {
						g.setColor(imageColor[c*height+r]);
						g.fillRect(r, c, 1, 1);
						}	
					}	
				}
			if(fileNo.equals("P6")) {
				for(int c=0;c<i;c++) {
					for(int r=0;r<j;r++) {
						g.setColor(imageColor[r*height+c]);
						g.fillRect(c*2, r*2, 2, 2);
						}	
					}	
				}
			
		}
	}
	public static void main(String[] args) throws IOException {
		new imageviewer();
	}
}

