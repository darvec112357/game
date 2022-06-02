package ∂∑ ﬁ∆Â;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ∂∑ ﬁ∆Â extends JPanel {
	private static final long serialVersionUID=1l;
	private final static int rows=7;
	private final static int cols=9;
	private final int BLOCKWIDTH=50,BLOCKHEIGHT=50;
	private static HashMap<String,Integer> power;
	private int clickcount;
	private static String sidetomove;
	private MyIcon movingpiece;
	private static MyLabel[][] labels;
	private MyLabel movinglabel;
	private static String[] paths= {"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ1.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ10.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ11.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ12.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ13.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ14.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ15.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ2.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ3.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ4.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ5.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ6.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ7.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ8.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ9.png",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ16.jpg",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/timgG78XVZO0_∏±±æ.jpg",
			"C:/Users/Lenovo/eclipse-workspace/xiaoyouxi/src/∂∑ ﬁ∆Â/dsq_∏±±æ.jpg"};
	
    public ∂∑ ﬁ∆Â() {
    	sidetomove="red";
    	this.clickcount=0;
    	labels=new MyLabel[rows+1][cols+1];
    	this.setLayout(null);
    	power=setpower();
    	initlabels();
    }
    private HashMap<String,Integer> setpower(){
    	HashMap<String,Integer> answer=new HashMap<>();
    	answer.put("mouse", 0);
    	answer.put("cat", 1);
    	answer.put("dog", 2);
    	answer.put("wolf", 3);
    	answer.put("leopard", 4);
    	answer.put("tiger", 5);
    	answer.put("lion", 6);
    	answer.put("elephant", 7);
    	return answer;
    }
    private void initlabels() {
    	for(int i=0;i<rows;i++) {
    		for(int j=0;j<cols;j++) {
    			MyLabel jl=new MyLabel("",JLabel.CENTER);
				jl.row=i;
				jl.col=j;
				jl.setBounds(i*BLOCKWIDTH, j*BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jl.setOpaque(true);
				jl.setBackground(Color.WHITE);
    			if(i==0&&j==0) {
    				jl.setIcon(new MyIcon(paths[0]));
    			}
    			if(i==6&&j==0) {
    				jl.setIcon(new MyIcon(paths[10]));
    			}
    			if(i==1&&j==1) {
    				jl.setIcon(new MyIcon(paths[1]));
    			}
    			if(i==0&&j==2) {
    				jl.setIcon(new MyIcon(paths[8]));
    			}
    			if(i==2&&j==2) {
    				jl.setIcon(new MyIcon(paths[9]));
    			}
    			if(i==5&&j==1) {
    				jl.setIcon(new MyIcon(paths[11]));
    			}
    			if(i==4&&j==2) {
    				jl.setIcon(new MyIcon(paths[12]));
    			}
    			if(i==6&&j==2) {
    				jl.setIcon(new MyIcon(paths[13]));
   			    }
    			if(i==0&&j==6) {
    				jl.setIcon(new MyIcon(paths[14]));
    			}
    			if(i==2&&j==6) {
    				jl.setIcon(new MyIcon(paths[3]));	
    			}
    			if(i==4&&j==6) {
    				jl.setIcon(new MyIcon(paths[4]));
    			}
    			if(i==6&&j==6) {
    				jl.setIcon(new MyIcon(paths[5]));
    			}
    			if(i==1&&j==7) {
    				jl.setIcon(new MyIcon(paths[2]));
    			}
    			if(i==5&&j==7) {
    				jl.setIcon(new MyIcon(paths[6]));
    			}
    			if(i==0&&j==8) {
    				jl.setIcon(new MyIcon(paths[15]));
    			}    			
    			if(i==6&&j==8) {
    				jl.setIcon(new MyIcon(paths[7]));
    			}
    			if(((i==2||i==4)&&(j==0||j==8))||(i==3&&(j==1||j==7))) {
    				jl.setIcon(new MyIcon(paths[16]));
    			}
    			if(i==3&&j==0||(i==3&&j==8)) {
    				jl.setIcon(new MyIcon(paths[18]));
    			}    		
    			if((i==1||i==2||i==4||i==5)&&(j==3||j==4||j==5)) {
    				jl.setIcon(new MyIcon(paths[17]));
    			}
    			this.add(jl);				
				labels[i][j]=jl;						
				jl.addMouseListener(new MouseAdapter() {					
					public void mouseClicked(MouseEvent e) {
						if(e.getButton()==MouseEvent.BUTTON1){	
							move(jl);		
							//System.out.println(((MyIcon) jl.getIcon()).istrapped);
							if(isWin()) {
								System.out.println("congratulations!");
							}
							}						
						}						
					});		
    	}
    }
    	}    
	private int[] Returnsize() {
		int[] a = {cols * BLOCKWIDTH + 70, rows * BLOCKHEIGHT + 20};
		return a;
	}
	private static String getname(MyIcon icon) {
		if(icon.getPath()==paths[0]||icon.getPath()==paths[7]) {
			return "lion";
		}
		if(icon.getPath()==paths[1]||icon.getPath()==paths[6]) {
			return "dog";
		}
		if(icon.getPath()==paths[5]||icon.getPath()==paths[8]) {
			return "mouse";
		}
		if(icon.getPath()==paths[4]||icon.getPath()==paths[9]) {
			return "leopard";
		}
		if(icon.getPath()==paths[3]||icon.getPath()==paths[12]) {
			return "wolf";
		}
		if(icon.getPath()==paths[2]||icon.getPath()==paths[11]) {
			return "cat";
		}
		if(icon.getPath()==paths[10]||icon.getPath()==paths[15]) {
			return "tiger";
		}
		if(icon.getPath()==paths[13]||icon.getPath()==paths[14]) {
			return "elephant";
		}
		if(icon.getPath()==paths[16]) {
			return "trap";
		}
		if(icon.getPath()==paths[17]) {
			return "river";
		}
		if(icon.getPath()==paths[18]) {
			return "camp";
		}
		return "";
	}
	private static String getside(MyIcon icon) {
		if(icon.getPath()==paths[0]||icon.getPath()==paths[1]||icon.getPath()==paths[8]||icon.getPath()==paths[9]
		   ||icon.getPath()==paths[10]||icon.getPath()==paths[11]||icon.getPath()==paths[12]||icon.getPath()==paths[13]) {
			return "blue";
		}
		if(icon.getPath()==paths[2]||icon.getPath()==paths[3]||icon.getPath()==paths[4]||icon.getPath()==paths[5]
		   ||icon.getPath()==paths[6]||icon.getPath()==paths[7]||icon.getPath()==paths[14]||icon.getPath()==paths[15]) {
			return "red";
		}
		return "";
	}
	private void move(MyLabel jl) {		
		MyIcon icon1=(MyIcon) jl.getIcon();
		this.clickcount++;
		if(clickcount%2==1) {
			if(jl.getIcon()==null||
				(getname(icon1)=="trap"&&getname(icon1)=="river"&&getname(icon1)=="camp")) {
				clickcount--;
			}
			else {							    
			    jl.setBackground(Color.YELLOW);	
			    movingpiece=icon1;	
			    this.movinglabel=jl;
				if(getside(movingpiece)=="red"&&sidetomove=="red") {
			    sidetomove="blue";
			}			
				else if(getside(movingpiece)=="blue"&&sidetomove=="blue") {
					sidetomove="red";
				}
			    else {
				clickcount--;
	            System.out.println("not your turn");
	            jl.setBackground(Color.white);
			}
			}
		}		
		else {				
			if(islegalmove(movinglabel,jl)) {
				if(jl.getIcon()!=null&&getname(icon1)=="trap") {
					movingpiece.istrapped=true;
				}
			jl.setIcon(movingpiece);
			for(int i=0;i<rows;i++) {
				for(int j=0;j<cols;j++) {
					if((labels[i][j].getBackground()).equals(Color.YELLOW)) {					
						labels[i][j].setIcon(null);				
						labels[i][j].setBackground(Color.white);
						if(((i==2||i==4)&&(j==0||j==8))||(i==3&&(j==1||j==7))) {
		    				labels[i][j].setIcon(new MyIcon(paths[16]));		  
		    			}
						else if((i==1||i==2||i==4||i==5)&&(j==3||j==4||j==5)) {
							labels[i][j].setIcon(new MyIcon(paths[17]));							
						}						
					}
				}
			}
			}			
			else {
				System.out.println("illegal move");				
				movinglabel.setBackground(Color.white);
	            if(sidetomove=="red") {
            	sidetomove="blue";
            }
	            else if(sidetomove=="blue") {
            	sidetomove="red";
            }
			}
		}		
	}
    private static boolean riverlegal(MyLabel a,MyLabel b) {
    	MyIcon icon=(MyIcon) a.getIcon();
    	if(getname(icon)=="mouse") {
    		return true;
    	}
    	else {
    		if(b.getIcon()!=null) {
    			MyIcon icon1=(MyIcon) b.getIcon();
    			return(!(getname(icon1)=="river"));
    		}
    		return true;
    	}
    }
    private static boolean occupycamp(MyLabel a,MyLabel b) {
    	if(b.row==3&&b.col==0) {
    		if(getside((MyIcon) a.getIcon())=="red") {    		
    			return true;
    		}
    		else return false;
    	}
    	else if(b.row==3&&b.col==8) {
    		if(getside((MyIcon) a.getIcon())=="blue") {
    			return true;
    		}
    		else return false;
    	}
    	return true;
    }
    private static boolean distancelegal(MyLabel a,MyLabel b) {   	
    	return(Math.abs(a.row-b.row)+Math.abs(a.col-b.col)==1);
    }
    private static boolean jumplegal(MyLabel a,MyLabel b) {
    	int left=Math.min(a.row, b.row);
    	int top=Math.min(a.col, b.col);
    	Set<Integer> row=new HashSet<Integer>();
    	row.add(a.row);
    	row.add(b.row);
    	Set<Integer> col=new HashSet<Integer>();
    	col.add(a.col);
    	col.add(b.col);
    	Set<Integer> rowstandard1=new HashSet<Integer>();
    	rowstandard1.add(0);
    	rowstandard1.add(3);
    	Set<Integer> rowstandard2=new HashSet<Integer>();
    	rowstandard2.add(6);
    	rowstandard2.add(3);
    	Set<Integer> colstandard1=new HashSet<Integer>();
    	colstandard1.add(2);
    	colstandard1.add(6);
    	MyIcon icon=(MyIcon) a.getIcon(); 
    	if(((a.col==3&&b.col==3)||(a.col==4&&b.col==4)||(a.col==5&&b.col==5))&&(row.equals(rowstandard1)||(row.equals(rowstandard2)))) {
    			for(int i=left+1;i<=left+2;i++) {	 
    				MyIcon c=(MyIcon) labels[i][a.col].getIcon();
    				if(c!=null&&(getname(c)!="river")) {
    					return false;
    				}
    			}
    			if(getname(icon)=="tiger"||getname(icon)=="lion") {
    				return eatlegal(a,b);
    			}
    			else return false;
    		}   	
    	else if	(((a.row==1&&b.row==1)||(a.row==2&&b.row==2)||(a.row==4&&b.row==4)||
    			(a.row==5&&b.row==5))&&(col.equals(colstandard1))) { 	   		
    		for(int i=top+1;i<=top+3;i++) {		    
    			MyIcon d=(MyIcon) labels[a.row][i].getIcon();
				if(d!=null&&(getname(d)!="river")) {
					return false;
				}
    		}
    		if(getname(icon)=="tiger"||getname(icon)=="lion") {
				return eatlegal(a,b);
			}
			else return false;
		}   	
    	else {
    	return distancelegal(a,b);
    	}
    }
    private static boolean eatlegal(MyLabel a,MyLabel b) {
    	if(b.getIcon()!=null) {
    	MyIcon icon=(MyIcon) a.getIcon();
    	MyIcon icon1=(MyIcon) b.getIcon();
    	if(getname(icon)!="river"&&(getname(icon1)=="river")) {
    		return riverlegal(a,b);
    	}
    	else {
    		 if(getname(icon)=="mouse"&&getname(icon1)=="elephant") {  	
    			 if((a.row==1||a.row==2||a.row==4||a.row==5)
    					 &&(a.col==3||a.col==4||a.col==5)){
    				 return false;
    			 }
    			 return true;
    		 }
    		 else if(getname(icon)=="elephant"&&getname(icon1)=="mouse") {  	
    			 return false;
    		 }
    		 else if(((b.row==2||b.row==4)&&(b.col==0||b.col==8))||
    				 ((b.row==3)&&(b.col==1||b.col==7))) {
    			 return true;
    		 }
    		 else {
    			 if(getname(icon1)!="trap"&&getname(icon1)!="river"&&getname(icon1)!="camp") {
    			 return(power.get(getname(icon))>=power.get(getname(icon1)));
    			 }
    			 else return true;
    		 }
    	}
    	}
    	else {
    		return true;
    	}
    }
    private static boolean islegalmove(MyLabel a,MyLabel b) {    	
    	 return (occupycamp(a,b)&&riverlegal(a,b)&&eatlegal(a,b)&&jumplegal(a,b));    	
    }
    private static boolean campoccupied() {
    	if(getside((MyIcon)labels[3][0].getIcon())=="red") {
    		return true;
    	}
    	else if(getside((MyIcon)labels[3][8].getIcon())=="blue") {
    		return true;
    	}
    	return false;
    }
    private static boolean haspieceleft() {
    	boolean red=false;
    	boolean blue=false;
    	for(int i=0;i<rows;i++) {
    		for(int j=0;j<cols;j++) {
    			if(labels[i][j].getIcon()!=null&&getside((MyIcon) labels[i][j].getIcon())=="red") {
    				red=true;   				
    			}
    			else if(labels[i][j].getIcon()!=null&&getside((MyIcon) labels[i][j].getIcon())=="blue") {
    				blue=true;   				
    			}
    		}
    	}
    	return (red&&blue);
    }
    private static boolean haslegalmove() {
    	boolean red=false;
    	boolean blue=false;
    	for(int i=0;i<rows;i++) {
    		for(int j=0;j<cols;j++) {
    			if(labels[i][j].getIcon()!=null&&getside((MyIcon) labels[i][j].getIcon())=="red") {
    				for(int x=0;x<rows;x++) {
    		    		for(int y=0;y<cols;y++) {
    		    			if(islegalmove(labels[i][j],labels[x][y])) {
    		    				red=true;
    		    			}
    		    		}   		    		
    		    		}
    		}
    		}
    		}
    	for(int i=0;i<rows;i++) {
    		for(int j=0;j<cols;j++) {
    			if(labels[i][j].getIcon()!=null&&getside((MyIcon) labels[i][j].getIcon())=="blue") {
    				for(int x=0;x<rows;x++) {
    		    		for(int y=0;y<cols;y++) {
    		    			if(islegalmove(labels[i][j],labels[x][y])) {
    		    				blue=true;
    		    			}
    		    		}   		    		
    		    		}
    		}
    		}
    		}
    	return (red&&blue);
    }
    private static boolean isWin() {
    	return (campoccupied()||(!haspieceleft())||(!haslegalmove()));
    }
	private static JFrame constructmenu1() {
		∂∑ ﬁ∆Â game=new ∂∑ ﬁ∆Â();
		int[] a=game.Returnsize();		
		JFrame frame=new JFrame();
		JMenuBar menubar=new JMenuBar();
		frame.setJMenuBar(menubar);
		JMenu menu=new JMenu("≤Àµ•");
		menubar.add(menu);
		JMenuItem starter=new JMenuItem("–¬”Œœ∑");
		menu.add(starter);
		starter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame=constructmenu1();			
			}			
		});
		frame.setSize(a[1], a[0]);
		frame.setTitle("∂∑ ﬁ∆Â");
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
