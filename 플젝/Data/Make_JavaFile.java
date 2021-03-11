import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Make_JavaFile extends JFrame {
	Make_JavaFile(){
		EtchedBorder eborder = new EtchedBorder(EtchedBorder.RAISED);
		setTitle("JavaFile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		JLabel Label1 = new JLabel("Label1");
		Label1.setBounds(231, 143, 180, 179);
		Label1.setBackground(Color.YELLOW);
		Label1.setBorder(eborder);
		c.add(Label1);
		setSize(700, 770);
		setVisible(true);
	}
	public static void main(String[] args){
		Make_JavaFile mjf = new Make_JavaFile();
	}
}