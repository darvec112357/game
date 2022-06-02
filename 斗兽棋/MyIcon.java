package ¶·ÊÞÆå;

import javax.swing.ImageIcon;

public class MyIcon extends ImageIcon{
	private static final long serialVersionUID = 1L;
	private String pathname;
	protected boolean istrapped;
    public MyIcon(String str) {
    	super(str);
    	this.pathname=str;   	
    }
    public String getPath() {
    	return pathname;
    }
}
