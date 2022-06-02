package 五子棋;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class board extends JPanel{
	private static final long serialVersionUID=1l;
	private final int rows=19,cols=19;
	private final int BLOCKWIDTH=20,BLOCKHEIGHT=20;
	private int clickcount;
	private Color[] colors= {Color.RED,Color.YELLOW};
	private JLabel[][] labels=new JLabel[rows][cols];
	private JButton[][] buttons=new JButton[rows][cols];
	public board() {
		this.labels=new JLabel[rows][cols];
		this.buttons = new MyButton2[rows][cols];
		this.clickcount=0;
		this.setLayout(null);
		initButtons();	
		initlabels();			
	}
	private void initlabels() {
		for(int i=0;i<this.rows;i++) {
			for(int j=0;j<this.cols;j++) {
				JLabel jl=new JLabel("",JLabel.CENTER);
				jl.setBounds(i*BLOCKWIDTH, j*BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jl.setOpaque(true);
				jl.setBackground(Color.GRAY);
				this.add(jl);
				this.labels[i][j]=jl;
			}
		}
	}
	public JFrame congrats(Color color) {
		JFrame answer=new JFrame();
		answer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		answer.setSize(200,200);
		JLabel a=new JLabel("Congratulations!",JLabel.CENTER);
		a.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		a.setOpaque(true);
		a.setBackground(color);
		answer.add(a);		
		answer.setVisible(true);
		return answer;
	}
	private void initButtons() {
		for(int i=0;i<this.rows;i++) {
			for(int j=0;j<this.cols;j++) {
				MyButton2 jb=new MyButton2();
				jb.row=i;
				jb.col=j;
				jb.setBounds(i*BLOCKWIDTH, j*BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
				this.add(jb);
				buttons[i][j]=jb;
				jb.addActionListener(new ActionListener() {
				     @Override
				     public void actionPerformed(ActionEvent e) {
				    	MyButton2 m=(MyButton2) e.getSource();
				    	open(m);
				    	if(isWin(m.row,m.col)) {
				    		congrats(colors[clickcount%2]);
				    	}
				     }
				});
			}
		}
	}
	private void open(MyButton2 a) {
		 clickcount++;
		 a.setVisible(false);
    	 if(clickcount%2==0) {				    		
    		 labels[a.row][a.col].setBackground(Color.RED);
    	 }
    	 else {
    		 labels[a.row][a.col].setBackground(Color.YELLOW);
    	 }  	 
	}
	private int[] Returnsize() {
		int[] a = {cols * BLOCKWIDTH + 40, rows * BLOCKHEIGHT + 20};
		return a;
	}
	private boolean verify(int row,int col) {
		return (row>=0&&row<this.rows&&col>=0&&col<this.cols);
	}	
	private boolean checkrow(int row,int col) {
		for(int i=row-4;i<=row;i++) {			
			if(verify(i,col)) {				
				if((labels[i][col].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+1][col].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+2][col].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+3][col].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+4][col].getBackground()).equals(colors[clickcount%2])){
						return true;
				}						
		}
		}
		return false;
	}
	private boolean checkcol(int row,int col) {
		for(int i=col-4;i<=col;i++) {			
			if(verify(row,i)) {
				if((labels[row][i].getBackground()).equals(colors[clickcount%2])
				 &&(labels[row][i+1].getBackground()).equals(colors[clickcount%2])
				 &&(labels[row][i+2].getBackground()).equals(colors[clickcount%2])
				 &&(labels[row][i+3].getBackground()).equals(colors[clickcount%2])
				 &&(labels[row][i+4].getBackground()).equals(colors[clickcount%2])){
					return true;
			}			
	}
	}
	return false;
	}
	private boolean checkdiagonal(int row,int col) {
		for(int i=row-4;i<=row;i++) {
			for(int j=col-4;j<col;j++) {
			if(verify(i,j)&&(row-i)==(col-j)) {
				if((labels[i][j].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+1][j+1].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+2][j+2].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+3][j+3].getBackground()).equals(colors[clickcount%2])
				 &&(labels[i+4][j+4].getBackground()).equals(colors[clickcount%2])){
					return true;
			}			
	}
			}
			}
	
	return false;
	}
	private boolean isWin(int row,int col) {
		return (checkrow(row,col)||checkcol(row,col)||checkdiagonal(row,col));
	}
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		board game=new board();
		int[] a=game.Returnsize();
		frame.setSize(a[1], a[0]);
		frame.setTitle("五子棋");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=frame.getContentPane();
		c.add(game);
		frame.setVisible(true);
	}

}
