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
		Label1.setBounds(208, 132, 311, 158);
		Label1.setBackground(Color.YELLOW);
		Label1.setBorder(eborder);
		c.add(Label1);
		JTextField TextField1 = new JTextField("TextField1");
		TextField1.setBounds(98, 360, 450, 266);
		c.add(TextField1);
		setSize(700, 770);
		setVisible(true);
	}
	public static void main(String[] args){
		Make_JavaFile mjf = new Make_JavaFile();
	}
}