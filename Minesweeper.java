import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Minesweeper extends JPanel{
	private static final long serialVersionUID=1l;
	private static int rows;
	private static int cols;
	private static int bombCount;
	private int b;//current bomb count
	private int clickcount;
	private JLabel remainingmines;
	private ArrayList<Integer> allmines=new ArrayList<>();
	private ArrayList<Integer> foundmines=new ArrayList<>();
	private final static int BlockWidth=20;
	private final static int BlockHeight=20;
	private MyButton[][] buttons;
	private JLabel[][] labels;
	private final int[][] offset = 
		{{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
	
	public Minesweeper(int rows,int cols,int bombcount) {
		this.rows=rows;
		this.cols=cols;	
		bombCount=bombcount;
		this.b=bombCount;
		this.clickcount=0;
		this.labels=new JLabel[rows][cols];
		this.buttons = new MyButton[rows][cols];
		this.remainingmines=new JLabel("",JLabel.CENTER);
		this.remainingmines.setText(String.valueOf(bombCount));
		this.remainingmines.setBounds(rows*BlockWidth, 0, 3*BlockWidth, 3*BlockHeight);
		this.remainingmines.setOpaque(true);
		this.remainingmines.setBackground(Color.WHITE);
		Font font=new Font("宋体",Font.PLAIN,40);
		this.remainingmines.setFont(font);
		this.add(remainingmines);
		this.setLayout(null);	
		this.initButtons();	
		this.initPanel();
	}
	public static JFrame constructmenu1(int row,int col,int bombcount) {
		Minesweeper mainpanel=new Minesweeper(row,col,bombcount);
		JFrame answer=new JFrame();
		int[] a=mainpanel.Returnsize();
		answer.setSize(a[0], a[1]+25);
		answer.setTitle("扫雷");
		JMenuBar menuBar = new JMenuBar();
		answer.setJMenuBar(menuBar);
		final JMenu menu1 = new JMenu("菜单");
		final JMenu menu2 =new JMenu("帮助");
		menuBar.add(menu1);
		menuBar.add(menu2);
		final JMenuItem starter = new JMenuItem("新游戏");
		menu1.add(starter);
		final JMenuItem rules=new JMenuItem("游戏说明");
		menu2.add(rules);
		rules.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextArea a=new JTextArea();
				Font font=new Font("宋体",Font.PLAIN,25);
				a.setText("本扫雷操作方法和大多数扫雷一样，左键翻开，右键插旗，"
						+ "双击已翻开的数字可以翻开周围的格子。同时可以自定义设置游戏的规模。"
						+ "右上角是剩余雷的数量。唯一的不足是没有计时功能。");	
				a.setBackground(Color.WHITE);
				a.setLineWrap(true);
				a.setFont(font);
				a.setVisible(true);
				JFrame f=new JFrame();				
				f.setBackground(Color.WHITE);
				f.setSize(300,300);
				f.add(a);	
				f.setVisible(true);
			}			
		});
		starter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f=constructmenu1(rows,cols,bombCount);
			}		
		});
		final JMenuItem customise=new JMenuItem("自定义");
		customise.addActionListener(new ActionListener() {		     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame f=new JFrame();
				f.setLayout(new FlowLayout());
				JButton length=new JButton();
				length.setText("设置长度：");
				length.setBackground(Color.YELLOW);
				length.setBounds(50,50,80,100);
				JButton width=new JButton();
				width.setText("设置宽度：");
				width.setBounds(50, 100, 80, 50);
				width.setBackground(Color.YELLOW);	
				JButton mines=new JButton();
				mines.setBackground(Color.YELLOW);
				mines.setText("设置雷数：");
				mines.setSize(80,50);
				f.setBackground(Color.WHITE);
				f.setSize(300,200);
				f.setTitle("设置");
				length.addActionListener(new ActionListener(){		
					@Override
					public void actionPerformed(ActionEvent e) {
                        JButton b1=new JButton();
                        b1.setText("确定");
                        b1.setBackground(Color.YELLOW);
						JTextField t1=new JTextField();
						t1.setBackground(Color.WHITE);
						t1.setVisible(true);
						JFrame f=new JFrame();
						f.setLayout(new BorderLayout());
						f.setBackground(Color.WHITE);
						f.setSize(200,200);
						f.add(t1);		
						f.add(b1,BorderLayout.EAST);
						f.setVisible(true);
						b1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								Minesweeper.rows=Integer.parseInt(t1.getText());
								Minesweeper.constructmenu1(rows,cols,bombCount);								
							}						
						});
					}				
				});
				width.addActionListener(new ActionListener(){		
					@Override
					public void actionPerformed(ActionEvent e) {
                        JButton b1=new JButton();
                        b1.setText("确定");
                        b1.setBackground(Color.YELLOW);
						JTextField t1=new JTextField();
						t1.setBackground(Color.WHITE);
						t1.setVisible(true);
						JFrame f=new JFrame();
						f.setLayout(new BorderLayout());
						f.setBackground(Color.WHITE);
						f.setSize(200,200);
						f.add(t1);		
						f.add(b1,BorderLayout.EAST);
						f.setVisible(true);
						b1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								Minesweeper.cols=Integer.parseInt(t1.getText());
								Minesweeper.constructmenu1(rows,cols,bombCount);								
							}						
						});
					}				
				});
				mines.addActionListener(new ActionListener(){		
					@Override
					public void actionPerformed(ActionEvent e) {
                        JButton b1=new JButton();
                        b1.setText("确定");
                        b1.setBackground(Color.YELLOW);
						JTextField t1=new JTextField();
						t1.setBackground(Color.WHITE);
						t1.setVisible(true);
						JFrame f=new JFrame();
						f.setLayout(new BorderLayout());
						f.setBackground(Color.WHITE);
						f.setSize(200,200);
						f.add(t1);		
						f.add(b1,BorderLayout.EAST);
						f.setVisible(true);
						b1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								Minesweeper.bombCount=Integer.parseInt(t1.getText());
								Minesweeper.constructmenu1(rows,cols,bombCount);								
							}						
						});
					}				
				});
				f.add(length);
				f.add(width);
				f.add(mines);
				f.setVisible(true);			
			}
		});
		menu1.add(customise);
		answer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		Container c=answer.getContentPane();
		c.add(mainpanel);
		answer.setVisible(true);
		return answer;		
	}
	private void initPanel() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				int a=i;
				int b=j;
				JLabel grid=new JLabel("",JLabel.CENTER);
				grid.setBounds(i*BlockWidth, j*BlockHeight, BlockWidth, BlockHeight);
				grid.setBorder(BorderFactory.createLineBorder(Color.gray,2));
				grid.setOpaque(true);
				grid.setBackground(Color.YELLOW);
				this.add(grid);
				labels[i][j]=grid;
				grid.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {//快速翻开功能			
					if(e.getButton()==MouseEvent.BUTTON1) {				
						clickcount++;
						if(clickcount%2==1) {//点击一下选中
							grid.setBackground(Color.RED);
						}
						else {//点击两下翻开
							grid.setBackground(Color.YELLOW);
							int count=0;
							for(int[] off:offset) {
						        int newrow=a+off[0];
						        int newcol=b+off[1];
						        if(verify(newrow,newcol)&&(buttons[newrow][newcol].isVisible()
						        		&&buttons[newrow][newcol].getBackground().equals(Color.RED))) {
						        	count++;
						        }
							}
							if(count==Integer.valueOf(grid.getText())){
								for(int[] off:offset) {
							        int newrow=a+off[0];
							        int newcol=b+off[1];
							        if(verify(newrow,newcol)&&(buttons[newrow][newcol].isVisible()
							        		&&!buttons[newrow][newcol].getBackground().equals(Color.RED))) {
							        	open(buttons[newrow][newcol]);
							        }
								}	
							}
						}
					}	
					if(e.getButton()==MouseEvent.BUTTON3&&grid.getBackground().equals(Color.RED)) {
						grid.setBackground(Color.YELLOW);
						clickcount--;//右键取消左键点击
					}
				}				
				});
		}
		}
		setBombs();
		writeNumbers();
	}
	public static int[] Returnsize() {
		int[] a = {rows * BlockWidth + 80, cols * BlockHeight + 40};
		return a;
	}
	private Set<Integer> generatenumbers(int a,int b){
		Set<Integer> answer=new HashSet<>();
		while(answer.size()<a) {
			int n=(int)(Math.random()*b);
			answer.add(n);
		}
		for(int i:answer) {
			allmines.add(i);
		}
	
		return answer;
	}
	private void setBombs() {
		Set<Integer> list=generatenumbers(bombCount, rows*cols);	
		for(int j:list) {
			int a=j/(rows);
			int b=j%(rows);
			for(int x=0;x<rows;x++) {
				for(int y=0;y<cols;y++) {
					if(x==b&&y==a) {
						this.labels[x][y].setText("*");
						this.labels[x][y].setBackground(Color.BLACK);
						this.labels[x][y].setForeground(Color.RED);
					}
				}
			}
		}
	}
	private void writeNumbers() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				if(!(labels[i][j].getText().equals("*"))) {		
					int answer=0;
					for(int[] off:offset) {
						int row1=i+off[0];
						int col1=j+off[1];						
						if(verify(row1,col1)&&labels[row1][col1].getText().equals("*")) {
							answer++;
						}
					}				
					if(answer>0) {
					labels[i][j].setText(String.valueOf(answer));
				}
				}
			}
			}		
	}
	private void initButtons() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				MyButton btn=new MyButton();
				btn.setBounds(i*BlockWidth, j*BlockHeight, BlockWidth, BlockHeight);
				btn.row=i;
				btn.col=j;
				this.add(btn);
				this.buttons[i][j]=btn;
				btn.addMouseListener(new MouseAdapter() {					
					public void mouseClicked(MouseEvent e) {						
						if(e.getButton()==MouseEvent.BUTTON3
								&&!btn.getBackground().equals(Color.RED)) {
							 b--;
					         flag(btn);	          													
							 remainingmines.setText(String.valueOf(b));
					}		
						else if(e.getButton()==MouseEvent.BUTTON1){					
							 if(btn.getBackground().equals(Color.RED)) {
								 b++;
								 remainingmines.setText(String.valueOf(b));
								 foundmines.remove(new Integer(btn.col*rows+btn.row));
							 }
							 open(btn);
						}
					}
				});
			}
		}
	}
	private boolean findall() {
		if(b!=0) {
			return false;
		}
		Collections.sort(allmines);
		Collections.sort(foundmines);
		System.out.println(allmines);
		System.out.println(foundmines);
		for(int i=0;i<bombCount;i++) {
			if(!allmines.get(i).equals(foundmines.get(i))) {
				System.out.println(allmines.get(i));
				System.out.println(foundmines.get(i));
				return false;
			}
		}
		return true;
	}
	private boolean verify(int row,int col) {
		return (row>=0&&row<rows&&col>=0&&col<cols);
	}
	private void open(MyButton a) {
		a.setVisible(false);
	    switch(this.labels[a.row][a.col].getText()) {
	    case "*":
			for(int i=0;i<rows;i++) {
				for(int j=0;j<cols;j++) {
					this.buttons[i][j].setVisible(false);
				}
			}	    
			System.out.println("Gameover!");		   
	    case "":
			for(int[] off:offset) {
				int newrow=a.row+off[0];
				int newcol=a.col+off[1];				
				if(verify(newrow,newcol)) {
					MyButton b=this.buttons[newrow][newcol];
					if(b.isVisible()) {	
						open(b);
				}					
				}															
				}			
        default:
	    }
	}	
	private void flag(MyButton a) {
		a.setText("#");
		a.setBackground(Color.RED);			
		foundmines.add(a.col*rows+a.row);
		if(findall()) {
			congrats();
		}
	}	
	public static JFrame congrats() {
		System.out.println(123);
		JFrame answer=new JFrame();
		answer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		answer.setSize(400,200);
		JLabel a=new JLabel("Congratulations!",JLabel.CENTER);
		Font font=new Font("宋体",Font.PLAIN,40);
		a.setFont(font);
		a.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		a.setOpaque(true);
		a.setBackground(Color.GRAY);
		answer.add(a);
		answer.setVisible(true);
		return answer;
	}
	public static void main(String[] args) {		
		Minesweeper game=new Minesweeper(16,30,99);
		//congrats();
	}
}
	
	