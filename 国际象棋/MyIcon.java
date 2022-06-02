package ¹ú¼ÊÏóÆå;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MyIcon extends ImageIcon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String pathname;
	protected boolean hasmoved=false;
    public MyIcon(String str) {
    	super(str);
    	this.pathname=str;   	
    }
    public MyIcon(Image img) {
    	super(img);
    }
    public String getPath() {
    	return pathname;
    }
}