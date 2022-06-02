import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Timers extends JFrame{
	final Label lab=new Label();
	Date now = new Date();
	@SuppressWarnings("deprecation")
	public Timers() {
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(0);
	}
}
