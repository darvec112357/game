package 国际象棋;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class chess extends JPanel{
	private static int rows=8,cols=8;
	private int BLOCKWIDTH=55,BLOCKHEIGHT=55;
	private int SMALLERWIDTH=15,SMALLERHEIGHT=15;
	private static int clickcount;
	private static MyIcon movingpiece;
	private static String sidetomove;
	private static boolean ischecking;
	private static MyIcon capturedpiece;
	private static MyIcon capturedpiece2;
	private static boolean justcastled;
	private static MyLabel movinglabel;
	private static Color[] colors= {Color.WHITE,Color.GRAY};
	private static ArrayList<MyLabel[]> recordedmove;
	private String[] str={"a","b","c","d","e","f","g","h"};
	private static String[] paths= {
		    "C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本6_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本12_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本5_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本4_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本3_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本2_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本11.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本10_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本9_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本7_副本.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/国际象棋/timg_副本8_副本.png"
	};
	private static MyLabel[][] labels;	
	private static MyIcon selectedpiece;
	private static boolean settingmode;
	public chess() {
		clickcount=0;
		settingmode=false;
		justcastled=false;
		ischecking=false;
		sidetomove="white";
		recordedmove=new ArrayList<MyLabel[]>();
		labels=new MyLabel[rows+1][cols+1];
		this.setLayout(null);
		initlabels();
	}	
	private void initlabels() {
		for(int i=0;i<rows+1;i++) {
			for(int j=0;j<cols+1;j++) {
				if(!(i==0&&j==0)) {
				MyLabel jl=new MyLabel("",JLabel.CENTER);
				jl.row=i;
				jl.col=j;
				jl.setBounds(i*BLOCKWIDTH-40, j*BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jl.setOpaque(true);
				jl.setBackground(colors[(i+j)%2]);
				if(i==0) {
					jl.setBounds(i*BLOCKWIDTH, j*BLOCKHEIGHT, SMALLERHEIGHT, BLOCKWIDTH);
					for(int x=1;x<cols+1;x++) {
						jl.setBackground(Color.WHITE);
						if(x==j) {
						jl.setText(String.valueOf(9-x));
					}
					}
				}
				if(j==0) {
					jl.setBounds(i*BLOCKWIDTH-40, j*BLOCKHEIGHT+40, BLOCKHEIGHT, SMALLERWIDTH);
					for(int x=1;x<cols+1;x++) {
						jl.setBackground(Color.WHITE);
						if(x==i) {
						jl.setText(str[x-1]);
						}
					}
				}
				if(j==2&&i!=0) {
					MyIcon icon=new MyIcon(paths[0]);
					jl.setIcon(icon);
				}
				if(j==7&&i!=0) {
					MyIcon icon=new MyIcon(paths[1]);
					jl.setIcon(icon);
				}
				if(j==1){
					if((i==1||i==8)) {
					    MyIcon icon=new MyIcon(paths[2]);
					    jl.setIcon(icon);
				    }
					if(i==2||i==7) {
					    MyIcon icon=new MyIcon(paths[3]);
					    jl.setIcon(icon);
					}
					if(i==3||i==6) {
						MyIcon icon=new MyIcon(paths[4]);
					    jl.setIcon(icon);
					}
					if(i==4) {
						MyIcon icon=new MyIcon(paths[5]);
					    jl.setIcon(icon);
					}
					if(i==5) {
						MyIcon icon=new MyIcon(paths[6]);
					    jl.setIcon(icon);
					}
				}
				if(j==8) {
					if((i==1||i==8)) {
						MyIcon icon=new MyIcon(paths[7]);
					    jl.setIcon(icon);				
				}
					if(i==2||i==7) {
					    MyIcon icon=new MyIcon(paths[8]);
					    jl.setIcon(icon);
				}
				    if(i==3||i==6) {						
					    MyIcon icon=new MyIcon(paths[9]);
					    jl.setIcon(icon);
				}
					if(i==4) {					
						MyIcon icon=new MyIcon(paths[10]);
						jl.setIcon(icon);
					}
					if(i==5) {
						MyIcon icon=new MyIcon(paths[11]);
						jl.setIcon(icon);
					}
				}
				this.add(jl);				
				labels[i][j]=jl;						
				jl.addMouseListener(new MouseAdapter() {					
					public void mouseClicked(MouseEvent e) {
						if(e.getButton()==MouseEvent.BUTTON1){	
							if(!settingmode) {
								if(jl.row!=0&&jl.col!=0) {
									move(jl);	
									if(clickcount%2==0) {	
										if(ischeckmate()) {
											System.out.println("Congratulations!");   
										}	 
										if(isstalemate()) {
											System.out.println("Draw by stalemate!");
										}
									}	
								}	
							}
							else {
								jl.setIcon(selectedpiece);
							}
						}						
					}
				});				
				}		
			}
		}
	}
	private static void move(MyLabel jl){		
		MyIcon icon1=(MyIcon) jl.getIcon();		
		clickcount++;
		if(clickcount%2==1) {
			if(jl.getIcon()==null) {
				clickcount--;
			}
			else {			
			    jl.setBackground(Color.YELLOW);	
			    movingpiece=icon1;				   
			    movinglabel=jl;
				if(getside(movingpiece)=="white"&&sidetomove=="white") {
			        sidetomove="black";
			    }			
				else if(getside(movingpiece)=="black"&&sidetomove=="black") {
					sidetomove="white";
				}
			    else {
				clickcount--;
	            System.out.println("not your turn");
	            jl.setBackground(colors[(movinglabel.col+movinglabel.row)%2]);
			    }				
			}
		}		
		else {					
			if(islegalmove(movinglabel,jl)) {
				capturedpiece=(MyIcon) jl.getIcon();				
				if(getname(movingpiece)=="pawn"&&enpassant(movinglabel,jl)) {
					eatenpassant(movinglabel,jl);
				}
				if(getname(movingpiece)=="pawn"&&(jl.col==1||jl.col==8)) {
					promote(jl);				
				}
				if(getname(movingpiece)=="king"&&cancastle(movinglabel,jl)) {
					castle(movinglabel,jl);				
				}
				else{
					jl.setIcon(movingpiece);			
					for(int i=1;i<rows+1;i++) {
						for(int j=1;j<cols+1;j++) {
							if((labels[i][j].getBackground()).equals(Color.YELLOW)) {					
								labels[i][j].setIcon(null);				
								labels[i][j].setBackground(colors[(i+j)%2]);										
							}
						}
					}						
					if(getname(movingpiece)=="king"||(getname(movingpiece)=="rook")){
						movingpiece.hasmoved=true;
					}
					justcastled=false;
				}					
				MyLabel[] record=new MyLabel[2];
				record[0]=movinglabel;
				record[1]=jl;
				recordedmove.add(record);
				ischecking=ischecking2(labels);
				if(ischecking(labels)) {
					undo();					
					System.out.println("illegal move");
				}	
			}											
			else {
				System.out.println("illegal move");		
				movinglabel.setBackground(colors[(movinglabel.row+movinglabel.col)%2]);
		        if(sidetomove=="white") {
	               sidetomove="black";
	               movingpiece.hasmoved=false;
	            }
		        else if(sidetomove=="black") {
	               sidetomove="white";
	               movingpiece.hasmoved=false;
	            }
			}						
		}
	}	
	private static String getname(MyIcon icon) {
		if(icon.getPath()==paths[0]||(icon.getPath()==paths[1])) {
			return "pawn";
		}
		if(icon.getPath()==paths[2]||(icon.getPath()==paths[7])) {
			return "rook";
		}
		if(icon.getPath()==paths[3]||(icon.getPath()==paths[8])) {
			return "knight";
		}
		if(icon.getPath()==paths[4]||(icon.getPath()==paths[9])) {
			return "bishop";
		}
		if(icon.getPath()==paths[5]||(icon.getPath()==paths[10])) {
			return "queen";
		}
		if(icon.getPath()==paths[6]||(icon.getPath()==paths[11])) {
			return "king";
		}
		return "";
	}	
	private static String getside(MyIcon icon) {
		if(icon.getPath()==paths[0]||icon.getPath()==paths[2]||icon.getPath()==paths[3]
			||icon.getPath()==paths[5]||icon.getPath()==paths[6]||icon.getPath()==paths[4]) {
			return "black";
		}
		if(icon.getPath()==paths[1]||icon.getPath()==paths[7]||icon.getPath()==paths[8]
				||icon.getPath()==paths[11]||icon.getPath()==paths[10]||icon.getPath()==paths[9]) {
				return "white";
		}	
		return "";
	}
	private int[] Returnsize() {
		int[] a = {(cols+1) * BLOCKWIDTH +80, (rows+1) * BLOCKHEIGHT};
		return a;
	}
	private static boolean pawnlegal(MyLabel a,MyLabel b) {
		MyIcon icon=(MyIcon) a.getIcon();
		MyIcon icon1=(MyIcon) b.getIcon();
		if(a.col==7&&getside(icon)=="white") {
			if(b.row==a.row&&(b.col==5||b.col==6)) {
				for(int i=b.col;i<a.col;i++) {
					if(labels[a.row][i].getIcon()!=null) {
						return false;
					}
				}
				return true;
			}
			return pawneatlegal(a,b);
		}
		if(a.col==2&&getside(icon)=="black") {
			if(b.row==a.row&&(b.col==3||b.col==4)) {
				for(int i=b.col;i>a.col;i--) {
					if(labels[a.row][i].getIcon()!=null) {
						return false;
					}
				}
				return true;
			}
			return pawneatlegal(a,b);
		}
		else {
			if(a.row==b.row) {
				if(getside(icon)=="white") {
					return(a.col-b.col==1&&icon1==null);
				}
				else return(b.col-a.col==1&&icon1==null);
			}
			else {
				return pawneatlegal(a,b);
			}
		}
	}
	private static boolean rooklegal(MyLabel a,MyLabel b) {
		MyIcon a1=(MyIcon) a.getIcon();
		MyIcon b1=(MyIcon) b.getIcon();
		int r1=Math.min(a.row, b.row);
		int r2=Math.max(a.row, b.row);
		int c1=Math.min(a.col, b.col);
		int c2=Math.max(a.col, b.col);
		if(a.row==b.row&&a.col==b.col) {
			return false;
		}
		if(a.row==b.row) {		
			for(int i=c1;i<=c2;i++) {
				if(labels[a.row][i]!=a) {
					if(labels[a.row][i]==b&&b1!=null) {
						if(getside(a1)==getside(b1)) {
							return false;
						}
					}
					else if(labels[a.row][i].getIcon()!=null) {
						return false;
					}
				}
			}
			return true;
		}
		if(a.col==b.col) {		
			for(int i=r1;i<=r2;i++) {
				if(labels[i][a.col]!=a) {
					if(labels[i][a.col]==b&&b1!=null) {
						if(getside(a1)==getside(b1)) {
							return false;
						}
					}
					else if(labels[i][a.col].getIcon()!=null) {
						return false;
					}
				}
			}
			return true;
		}		
		return false;
	}
	private static boolean knightlegal(MyLabel a,MyLabel b) {
		int r=Math.abs(a.row-b.row);
		int c=Math.abs(a.col-b.col);		
		MyIcon a1=(MyIcon) a.getIcon();
		MyIcon b1=(MyIcon) b.getIcon();
		if((r==1&&c==2)||(r==2&&c==1)) {
			if(b1!=null) {
				return (getside(a1)!=getside(b1));
			}
			return true;
		}
		return false;
	}
	private static boolean samediagonal(MyLabel a,MyLabel b) {
		return (Math.abs(a.row-b.row)==Math.abs(a.col-b.col));
	}
	private static boolean bishoplegal(MyLabel a,MyLabel b) {
		MyIcon a1=(MyIcon) a.getIcon();
		MyIcon b1=(MyIcon) b.getIcon();
		int r1=Math.min(a.row, b.row);
		int r2=Math.max(a.row, b.row);
		int c1=Math.min(a.col, b.col);
		int c2=Math.max(a.col, b.col);
		if(!samediagonal(a,b)) {
			return false;
		}
		if(r1==r2&&c1==c2) {
			return false;
		}
		for(int i=r1;i<=r2;i++) {
			for(int j=c1;j<=c2;j++) {
				if(labels[i][j]!=a&&samediagonal(labels[i][j],a)) {
					if(labels[i][j]==b&&b1!=null) {
						if(getside(a1)==getside(b1)) {
							return false;
						}
					}
					else if(labels[i][j].getIcon()!=null) {
						return false;
					}
				}
			}
		}
		return true;
	}
	private static boolean enpassant(MyLabel a,MyLabel b) {
		if(clickcount>2) {
			MyLabel[] record=recordedmove.get(recordedmove.size()-1);
			MyIcon a1=(MyIcon) a.getIcon();
			MyIcon a2=(MyIcon) record[1].getIcon();
			if(getside(a1)=="white"&&a.col==4) {
				if(record[0].col==2&&record[1].col==4&&getname(a2)=="pawn"
					&&record[1].row==b.row&&getside(a2)=="black") {
					return true;
				}
			}
			if(getside(a1)=="black"&&a.col==5) {
				if(record[0].col==7&&record[1].col==5&&getname(a2)=="pawn"
					&&record[1].row==b.row&&getside(a2)=="white") {
					return true;
				}
			}
			return false;	
		}
		return false;
	}
	private static boolean pawneatlegal(MyLabel a,MyLabel b) {
		MyIcon a1=(MyIcon) a.getIcon();
		MyIcon b1=(MyIcon) b.getIcon();
		if(getside(a1)=="white") {
			if(a.col-b.col==1&&Math.abs(a.row-b.row)==1) {
				if(b1!=null) {
					return (getside(b1)=="black");
				}
				return enpassant(a,b);
			}
			return false;
		}
		if(getside(a1)=="black") {
			if(b.col-a.col==1&&Math.abs(a.row-b.row)==1) {
				if(b1!=null) {
					return (getside(b1)=="white");
				}
				return enpassant(a,b);
			}
			return false;
		}
		return false;
	}
	private static boolean ischecking(MyLabel[][] a) {
		for(int i=1;i<rows+1;i++) {
			for(int j=0;j<cols+1;j++) {
				MyIcon icon=(MyIcon) a[i][j].getIcon();
				if(icon!=null&&getname(icon)=="king") {
					for(int x=1;x<rows+1;x++) {
						for(int y=1;y<cols+1;y++) {
							MyIcon icon1=(MyIcon) a[x][y].getIcon();								
							if(icon1!=null&&getside(icon1)!=getside(icon)
							   &&getside(icon1)==sidetomove
							   &&islegalmove(a[x][y],a[i][j])) {	
								return true;					
							}			
						}
					}								
				}
			}				
		}
		return false;
	}
	private static boolean ischecking2(MyLabel[][] a) {
		for(int i=1;i<rows+1;i++) {
			for(int j=0;j<cols+1;j++) {
				MyIcon icon=(MyIcon) a[i][j].getIcon();
				if(icon!=null&&getname(icon)=="king") {
					for(int x=1;x<rows+1;x++) {
						for(int y=1;y<cols+1;y++) {
							MyIcon icon1=(MyIcon) a[x][y].getIcon();																
							if(icon1!=null&&getside(icon1)!=getside(icon)
							   &&getside(icon1)!=sidetomove
							   &&islegalmove(a[x][y],a[i][j])) {	
								return true;
							}		
						}
					}								
				}
			}				
		}
		return false;
	}
	private static boolean kinglegal(MyLabel a,MyLabel b) {
		MyIcon a1=(MyIcon) a.getIcon();
		MyIcon b1=(MyIcon) b.getIcon();
		if((Math.abs(a.row-b.row)+Math.abs(a.col-b.col)!=1)&&
				!(Math.abs(a.row-b.row)==1&&samediagonal(a,b))) {		
			return cancastle(a,b);
		}
		else {			
			if(b1!=null) {
				return (getside(a1)!=getside(b1));
			}
			return true;
		}
	}
	private static boolean isthreatened(MyLabel a) {
		for(int i=1;i<rows+1;i++) {
			for(int j=1;j<cols+1;j++) {
				MyIcon icon=(MyIcon) labels[i][j].getIcon();
				if(icon!=null) {
					if(getside(icon)==sidetomove) {
						if(islegalmove(labels[i][j],a)) {
							return true;
						}
					}
				}				
			}
		}
		return false;
	}
	private static boolean cancastle(MyLabel a,MyLabel b) {
		MyIcon icon=(MyIcon) a.getIcon();
		if(ischecking) {
			return false;
		}
		if(a.col==8&&getside(icon)=="white"&&!icon.hasmoved) {
			if(a.row==5&&b.row==7) {
				MyIcon icon1=(MyIcon) labels[8][8].getIcon();
				if(getname(icon1)=="rook"&&getside(icon1)=="white"&&!icon1.hasmoved) {
					if(isthreatened(labels[6][8])) {
						return false;
					}
				}				
				return (labels[6][8].getIcon()==null&&labels[7][8].getIcon()==null);
			}
			if(a.row==5&&b.row==3) {
				MyIcon icon1=(MyIcon) labels[8][8].getIcon();
				if(getname(icon1)=="rook"&&getside(icon1)=="white"&&!icon1.hasmoved) {
					if(isthreatened(labels[6][8])) {
						return false;
					}
				}				
				return (labels[6][8].getIcon()==null&&labels[7][8].getIcon()==null);
			}
			return false;
		}
		if(a.col==1&&getside(icon)=="black"&&!icon.hasmoved) {
				if(a.row==5&&b.row==7) {
					MyIcon icon1=(MyIcon) labels[8][1].getIcon();
					if(getname(icon1)=="rook"&&getside(icon1)=="white"&&!icon1.hasmoved) {
						if(isthreatened(labels[6][1])) {
							return false;
						}
					}				
					return (labels[6][1].getIcon()==null&&labels[7][1].getIcon()==null);
				}
				if(a.row==5&&b.row==3) {
					MyIcon icon1=(MyIcon) labels[1][1].getIcon();
					if(getname(icon1)=="rook"&&getside(icon1)=="white"&&!icon1.hasmoved) {
						if(isthreatened(labels[4][1])) {
							return false;
						}
					}				
					return (labels[4][1].getIcon()==null&&labels[3][1].getIcon()==null);
				}
				return false;
			}
		return false;
	}
	private static boolean islegalmove(MyLabel a,MyLabel b) {
		MyIcon icon=(MyIcon) a.getIcon();
		if(icon!=null) {
			if(getname(icon)=="pawn") {
				return pawnlegal(a,b);
			}
			if(getname(icon)=="rook") {
				return rooklegal(a,b);
			}
			if(getname(icon)=="knight") {
				return knightlegal(a,b);
			}
			if(getname(icon)=="bishop") {
				return bishoplegal(a,b);
			}
			if(getname(icon)=="king") {
				return (kinglegal(a,b)||cancastle(a,b));
			}
			else return (bishoplegal(a,b)||rooklegal(a,b));
		}
		return false;
	}
	private static void castle(MyLabel a,MyLabel b) {
		MyIcon icon=(MyIcon) a.getIcon();
		if(a.col==8&&b.row==7) {
			MyIcon icon1=(MyIcon) labels[8][8].getIcon();
			labels[6][8].setIcon(icon1);
			labels[7][8].setIcon(icon);
			a.setIcon(null);
			labels[8][8].setIcon(null);		
		}
		if(a.col==8&&b.row==3) {
			MyIcon icon2=(MyIcon) labels[1][8].getIcon();
			labels[3][8].setIcon(icon);
			labels[4][8].setIcon(icon2);
			a.setIcon(null);
			labels[1][8].setIcon(null);			
		}
		if(a.col==1&&b.row==7) {
			MyIcon icon3=(MyIcon) labels[8][1].getIcon();
			labels[6][1].setIcon(icon3);
			labels[7][1].setIcon(icon);
			a.setIcon(null);
			labels[8][1].setIcon(null);
		}
		if(a.col==1&&b.row==3) {
			MyIcon icon4=(MyIcon) labels[1][1].getIcon();
			labels[4][1].setIcon(icon4);
			labels[3][1].setIcon(icon);
			a.setIcon(null);
			labels[1][1].setIcon(null);
		}
		justcastled=true;
		if(ischecking(labels)) {
			uncastle(b);
			justcastled=false;
		}
		for(int i=1;i<rows+1;i++) {
			for(int j=1;j<cols+1;j++) {
				if((labels[i][j].getBackground()).equals(Color.YELLOW)) {								
					labels[i][j].setBackground(colors[(i+j)%2]);										
				}
			}
		}	
	}
	private static void uncastle(MyLabel a) {
		MyIcon icon=new MyIcon(paths[7]);
		MyIcon icon1=new MyIcon(paths[2]);
		if(a.row==7&&a.col==8) {
			labels[5][8].setIcon(movingpiece);
            labels[7][8].setIcon(null);
			labels[8][8].setIcon(icon);
			labels[6][8].setIcon(null);
			sidetomove="white";
		}
		if(a.row==3&&a.col==8) {
			labels[5][8].setIcon(movingpiece);
            labels[3][8].setIcon(null);
			labels[1][8].setIcon(icon);
			labels[4][8].setIcon(null);
			sidetomove="white";
		}
		if(a.row==7&&a.col==1) {
			labels[5][1].setIcon(movingpiece);
            labels[7][1].setIcon(null);
			labels[8][1].setIcon(icon1);
			labels[6][1].setIcon(null);
			sidetomove="black";
		}
		if(a.row==3&&a.col==1) {
			labels[5][1].setIcon(movingpiece);
            labels[3][1].setIcon(null);
			labels[1][1].setIcon(icon1);
			labels[4][1].setIcon(null);
			sidetomove="black";
		}
	}
	private static void promote(MyLabel jl) {
		JFrame f=new JFrame();
		f.setLayout(new FlowLayout());
		f.setSize(250, 250);
		Dimension a=new Dimension(100,100);
		JButton queen=new JButton();
		JButton bishop=new JButton();
		JButton knight=new JButton();
		JButton rook=new JButton();
		if(getside(movingpiece)=="white") {
			queen.setIcon(new MyIcon(paths[10]));
			bishop.setIcon(new MyIcon(paths[9]));
			knight.setIcon(new MyIcon(paths[8]));
			rook.setIcon(new MyIcon(paths[7]));
		}	
		if(getside(movingpiece)=="black") {
			queen.setIcon(new MyIcon(paths[5]));
			bishop.setIcon(new MyIcon(paths[4]));
			knight.setIcon(new MyIcon(paths[3]));
			rook.setIcon(new MyIcon(paths[2]));
		}
		ArrayList<JButton> buttons=new ArrayList<>();
		buttons.add(queen);
		buttons.add(bishop);
		buttons.add(knight);
		buttons.add(rook);
		for(int i=0;i<4;i++) {
			int x=i;
			buttons.get(x).setPreferredSize(a);
			buttons.get(x).setBorder(BorderFactory.createLineBorder(Color.BLACK));
			buttons.get(x).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jl.setIcon(buttons.get(x).getIcon());
					jl.setBackground(colors[(jl.row+jl.col)%2]);
					f.dispose();
				}			
			});
		}	
		f.add(queen);
		f.add(bishop);
		f.add(knight);
		f.add(rook);
		f.setVisible(true);
	}
	private static void eatenpassant(MyLabel a,MyLabel b){
		int r=b.row;
		MyIcon a1=(MyIcon) a.getIcon(); 
		if(a.col==4) {
			MyIcon b1=(MyIcon) labels[r][4].getIcon();
			b.setIcon(a1);
			labels[r][4].setIcon(null);
			capturedpiece=b1;
		}
		if(a.col==5) {
			MyIcon b1=(MyIcon) labels[r][4].getIcon();
			b.setIcon(a1);
			labels[r][5].setIcon(null);
			capturedpiece=b1;
		}	
	}
	private static void advancepawn(MyLabel a) {
		MyIcon icon=(MyIcon) a.getIcon();
		if(icon!=null) {
			if(getside(icon)=="white"&&a.col==6) {
				a.setIcon(null);
				labels[a.row][5].setIcon(icon);
			}
			if(getside(icon)=="black"&&a.col==3) {
				a.setIcon(null);
				labels[a.row][4].setIcon(icon);
			}
		}		
	}
	private static void undo() {
		MyLabel a=recordedmove.get(recordedmove.size()-1)[1];
		MyLabel c=recordedmove.get(recordedmove.size()-1)[0];
		for(int i=1;i<rows+1;i++) {
			for(int j=1;j<cols+1;j++) {
				if(!(i==0&&j==0)) {
					if(i==c.row&&j==c.col) {
						labels[i][j].setIcon(movingpiece);
						movingpiece.hasmoved=false;
					}					
					if(i==a.row&&j==a.col) {
						labels[i][j].setIcon(capturedpiece);
						if(getname(movingpiece)=="pawn"&&Math.abs(c.row-a.row)==1
							&&getname(capturedpiece)=="pawn") {
							MyLabel b=recordedmove.get(recordedmove.size()-2)[1];
							if((getside(movingpiece)=="white"&&c.col==4&&b.col==4
								)||(getside(movingpiece)=="black"&&c.col==5&&b.col==5)) {
								advancepawn(labels[i][j]);	
							}
						}
					}
					
				}
			}
		}
		if(sidetomove=="white") {
			sidetomove="black";
		}
		else if(sidetomove=="black") {
			sidetomove="white";
		}		  
	}
	private static MyLabel[][] trymove(MyLabel a,MyLabel b,MyLabel[][] c) {
		MyLabel[][] answer=c;
		MyIcon icon=(MyIcon) a.getIcon();
		capturedpiece2=(MyIcon) b.getIcon();
		answer[b.row][b.col].setIcon(icon);
		answer[a.row][a.col].setIcon(null);
		return answer;
	}
	private static MyLabel[][] undo2(MyLabel a,MyLabel b,MyLabel[][] c,MyIcon piece){
		MyIcon icon=(MyIcon) b.getIcon();
		c[b.row][b.col].setIcon(piece);
		c[a.row][a.col].setIcon(icon);
		return c;		
	}
	private static boolean canrun() {
		if(ischecking2(labels)) {
			for(int i=1;i<rows+1;i++) {
				for(int j=1;j<cols+1;j++) {
					MyIcon icon=(MyIcon) labels[i][j].getIcon();
					if(icon!=null&&getside(icon)==sidetomove&&getname(icon)=="king") {
						for(int x=1;x<rows+1;x++) {
							for(int y=1;y<cols+1;y++) {
								if(islegalmove(labels[i][j],labels[x][y])) {
									MyLabel[][] resultboard=trymove(labels[i][j],labels[x][y],labels);										
									if(!ischecking2(resultboard)) {
										resultboard=undo2(labels[i][j],labels[x][y],labels,capturedpiece2);
										return true;
									}
									resultboard=undo2(labels[i][j],labels[x][y],labels,capturedpiece2);
								}
							}
						}
					}
				}
			}
			return false;
		}
		return true;
	}
	private static ArrayList<MyLabel> checkingpieces(MyLabel[][] a) { 
		ArrayList<MyLabel> answer=new ArrayList<>();
		for(int i=1;i<rows+1;i++) {
			for(int j=0;j<cols+1;j++) {
				MyIcon icon=(MyIcon) a[i][j].getIcon();
				if(icon!=null&&getname(icon)=="king") {
					for(int x=1;x<rows+1;x++) {
						for(int y=1;y<cols+1;y++) {
							MyIcon icon1=(MyIcon) a[x][y].getIcon();																
							if(icon1!=null&&getside(icon1)!=getside(icon)
							   &&getside(icon1)!=sidetomove
							   &&islegalmove(a[x][y],a[i][j])) {
								answer.add(a[x][y]);
								if(answer.size()==2) {
									return answer;
								}
							}		
						}
					}								
				}
			}				
		}
		return answer;
	}
	private static boolean cantake() {
		int k=checkingpieces(labels).size();
		if(k==2) {
			return false;
		}
		if(k==1) {
			MyLabel label=(MyLabel) checkingpieces(labels).get(0);
			MyIcon icon=(MyIcon) label.getIcon();
			for(int i=1;i<rows+1;i++) {
				for(int j=1;j<cols+1;j++) {
					MyIcon icon1=(MyIcon) labels[i][j].getIcon();
					if(icon1!=null&&getside(icon1)!=getside(icon)&&getname(icon1)!="king") {
						if(islegalmove(labels[i][j],label)) {
							return true;
						}
					}
				}
			}
			return false;
		}
		return true;
	}
	private static boolean sameline(MyLabel a,MyLabel b) {
		return((a.row==b.row&&a.col!=b.col)||(a.col==b.col&&a.row!=b.row));				
	}
	private static boolean canblock() {
		int k=checkingpieces(labels).size();
		if(k==2) {
			return false;
		}
		if(k==1) {
			MyLabel label=(MyLabel) checkingpieces(labels).get(0);
			MyIcon icon=(MyIcon) label.getIcon();
			if(getname(icon)=="knight"||getname(icon)=="pawn") {
				return false;
			}
			for(int i=1;i<rows+1;i++) {
				for(int j=1;j<cols+1;j++) {
					MyIcon icon1=(MyIcon) labels[i][j].getIcon();
					if(icon1!=null&&getside(icon1)!=getside(icon)&&getname(icon1)=="king"
						&&(Math.abs(i-label.row)!=1||(Math.abs(j-label.col)==1))) {
						if(sameline(labels[i][j],label)) {					
							if(label.row==i) {
								int c1=Math.max(label.col, j);
								int c2=Math.min(label.col, j);
								for(int x1=1;x1<rows+1;x1++) {
									for(int y1=1;y1<cols+1;y1++) {
										MyIcon icon2=(MyIcon) labels[x1][y1].getIcon();
										if(icon2!=null&&getside(icon2)==getside(icon1)&&getside(icon2)!="king") {
											for(int m=c2+1;m<=c1-1;m++) {
												if(islegalmove(labels[x1][y1],labels[i][m])) {
													return true;
												}
											}
										}
									}
								}																		
							}
							if(label.col==j) {
								int r1=Math.max(label.row,i);
								int r2=Math.min(label.row,i);
								for(int x2=1;x2<rows+1;x2++) {
									for(int y2=1;y2<cols+1;y2++) {
										MyIcon icon3=(MyIcon) labels[x2][y2].getIcon();
										if(icon3!=null&&getside(icon3)==getside(icon1)
										    &&getname(icon3)!="king") {
											for(int m=r2+1;m<=r1-1;m++) {
												if(islegalmove(labels[x2][y2],labels[i][m])) {
													return true;
												}
											}
										}
									}
								}			
							}
						}
						if(samediagonal(labels[i][j],label)) {
							int r1=Math.max(label.row,i);
							int r2=Math.min(label.row,i);
							int c1=Math.max(label.col, j);
							int c2=Math.min(label.col, j);
							for(int x3=1;x3<rows+1;x3++) {
								for(int y3=1;y3<cols+1;y3++) {
									MyIcon icon4=(MyIcon) labels[x3][y3].getIcon();
									if(icon4!=null&&getside(icon4)==getside(icon1)
									    &&getname(icon4)!="king") {
										for(int n1=r2+1;n1<+r1-1;n1++) {
											for(int n2=c2+1;n2<c1-1;n2++) {
												if(samediagonal(label,labels[n1][n2])
													&&islegalmove(labels[x3][y3],labels[n1][n2])) {
													return true;
												}
											}
										}
									}
								}
							}
						}
						return false;
					}
				}			
			}
		}
		return true;
	}
	private static boolean ischeckmate() {
		if(ischecking2(labels)) {
			return !(canrun()||cantake()||canblock());
		}
		return false;		
	}
	private static boolean isstalemate() {
        for(int i=1;i<rows+1;i++) {
        	for(int j=1;j<cols+1;j++) {
        		MyIcon icon=(MyIcon) labels[i][j].getIcon();
        		if(icon!=null&&getside(icon)==sidetomove) {
        			for(int x=1;x<rows+1;x++) {
        				for(int y=1;y<cols+1;y++) {
        					if(islegalmove(labels[i][j],labels[x][y])) {
        						trymove(labels[i][j],labels[x][y],labels);
        						if(!ischecking2(labels)) {
        							undo2(labels[i][j],labels[x][y],labels,capturedpiece2);
        							return false;
        						}
        						undo2(labels[i][j],labels[x][y],labels,capturedpiece2);
        					}
        				}
        			}
        		}
        	}
        }
		return true;
	}
	private static void clearboard() {
		for(int i=1;i<rows+1;i++) {
			for(int j=1;j<cols+1;j++) {
				labels[i][j].setIcon(null);
			}
		}
	}
	private static JFrame setboard() {
		JFrame f=new JFrame();
		Dimension d=new Dimension(50,50);
		Dimension d2=new Dimension(80,50);
		f.setLayout(new FlowLayout());
		f.setSize(350,300);
		ArrayList<JButton> buttons=new ArrayList<>();
		JButton whitepawn=new JButton();
		whitepawn.setIcon(new MyIcon(paths[1]));
		JButton whiteknight=new JButton();
		whiteknight.setIcon(new MyIcon(paths[8]));
		JButton whitebishop=new JButton();
		whitebishop.setIcon(new MyIcon(paths[9]));
		JButton whiterook=new JButton();
		whiterook.setIcon(new MyIcon(paths[7]));
		JButton whitequeen=new JButton();
		whitequeen.setIcon(new MyIcon(paths[10]));
		JButton whiteking=new JButton();
		whiteking.setIcon(new MyIcon(paths[11]));
		JButton blackpawn=new JButton();
		blackpawn.setIcon(new MyIcon(paths[0]));
		JButton blackknight=new JButton();
		blackknight.setIcon(new MyIcon(paths[3]));
		JButton blackbishop=new JButton();
		blackbishop.setIcon(new MyIcon(paths[4]));
		JButton blackrook=new JButton();
		blackrook.setIcon(new MyIcon(paths[2]));
		JButton blackqueen=new JButton();
		blackqueen.setIcon(new MyIcon(paths[5]));
		JButton blackking=new JButton();
		blackking.setIcon(new MyIcon(paths[6]));
		buttons.add(whitepawn);
		buttons.add(whiteknight);
		buttons.add(whitebishop);
		buttons.add(whiterook);
		buttons.add(whitequeen);
		buttons.add(whiteking);
		buttons.add(blackpawn);
		buttons.add(blackknight);
		buttons.add(blackbishop);
		buttons.add(blackrook);
		buttons.add(blackqueen);
		buttons.add(blackking);
		for(int i=0;i<12;i++) {
			JButton b=buttons.get(i);
			b.setPreferredSize(d);
			b.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedpiece=(MyIcon) b.getIcon();
				}			
			});
			f.add(b);
		}
		JButton clearboard=new JButton("clear");
		clearboard.setPreferredSize(d2);
		clearboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearboard();
			}				
		});
		JButton confirm=new JButton("确锟斤拷");
		confirm.setPreferredSize(d2);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingmode=false;
				f.dispose();
			}		
		});
		JButton white=new JButton("white");
		white.setPreferredSize(d2);
		white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sidetomove="white";				
			}		
		});
		JButton black=new JButton("black");
		black.setPreferredSize(d2);
		black.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sidetomove="black";				
			}		
		});
		f.add(white);
		f.add(black);
		f.add(clearboard);
		f.add(confirm);
		f.setVisible(true);
		return f;
	}
	private static JFrame constructmenu1() { 
		chess game=new chess();
		int[] a=game.Returnsize();		
		JFrame frame=new JFrame();
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		final JMenu menu = new JMenu("菜单");
		menuBar.add(menu);
		final JMenuItem starter = new JMenuItem("新游戏");
		final JMenuItem undo=new JMenuItem("悔棋");
		final JMenuItem customise=new JMenuItem("自定义");
		menu.add(starter);
		menu.add(undo);
		menu.add(customise);
		starter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f=constructmenu1();
			}		
		});
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyLabel a=recordedmove.get(recordedmove.size()-1)[1];
				if(justcastled) {
					uncastle(a);
				}				
				else {
					undo();	
				}							
			}			
		});
		customise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingmode=true;
				setboard();
			}		
		});
		frame.setSize(a[1], a[0]);
		frame.setTitle("国际象棋");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=frame.getContentPane();
		c.add(game);
		frame.setVisible(true);
		return frame;
	}
	public static void main(String[] args) {
		JFrame frame=constructmenu1(); 
}
}
