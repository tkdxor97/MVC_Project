import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
class MVC_Model extends JFrame
{
	int tmp;
	EtchedBorder eborder = new EtchedBorder(EtchedBorder.RAISED);
	model m;
	view v;
	class Attribute
	{
		int x;	//�簢���� ����x��
		int x_drag;	//�簢���� ������x��
		int y;	//�簢���� ����y��
		int y_drag;	//�簢���� ������y��
		int width;	//�簢���� �ʺ�
		int height;	//�簢���� ����
		String value;	//�簢�� ���ο� ���� �̸�(����)
		String type;	//�簢���� Ÿ��(JLabel, JButton, JTextField�� �ϳ�)
		String name;	//.java���� ������ �̿�Ǵ� �̸�
		JLabel[] Box = new JLabel[8];	//ũ�� ������ ���� ���� �簢�� 8��
		boolean isSelected = false;	//���� �簢���� ���õ��ִ����� ��Ÿ���� ����
	}
	class Label extends Attribute
	{
		JLabel JLabel_Component;
		Label()
		{
			for(int i=0; i<8; ++i)
			{
				Box[i] = new JLabel();
				Box[i].setBorder(eborder);
				Box[i].setBackground(Color.BLACK);
				m.Editor_Panel.add(Box[i]);
			}
		}
	}
	class Button extends Attribute
	{
		JButton JButton_Component;
		Button()
		{
			for(int i=0; i<8; ++i)
			{
				Box[i] = new JLabel();
				Box[i].setBorder(eborder);
				Box[i].setBackground(Color.BLACK);
				m.Editor_Panel.add(Box[i]);
			}
		}
	}
	class TextField extends Attribute
	{
		JTextField JTextField_Component;
		TextField()
		{
			for(int i=0; i<8; ++i)
			{
				Box[i] = new JLabel();
				Box[i].setBorder(eborder);
				Box[i].setBackground(Color.BLACK);
				m.Editor_Panel.add(Box[i]);
			}
		}
	}
	MVC_Model()
	{
		m = new model();
		v = new view(m);
	}
	public class view	//View
	{
		view(model m)	//�޴�, ����, �Ӽ��г�, �������г� �� ��ġ
		{
			setTitle("View");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(null);
			m.Menu.add(m.Button_Menu_New);
			m.Menu.add(m.Button_Menu_Open);
			m.Menu.add(m.Button_Menu_Save);
			m.Menu.add(m.Button_Menu_SaveDifferentName);
			m.Menu.add(m.Button_Menu_CreateJava);
			m.Menu.add(m.Button_Menu_Exit);
			m.mBar.add(m.Menu);
			m.tBar.add(m.Button_Tool_New);
			m.tBar.add(m.Button_Tool_Open);
			m.tBar.add(m.Button_Tool_Save);
			m.tBar.add(m.Button_Tool_SaveDifferentName);
			m.tBar.add(m.Button_Tool_CreateJava);
			m.tBar.add(m.Button_Tool_Exit);
			m.Label_X.setBounds(20,50,150,20);
			m.Label_Y.setBounds(20,140,150,20);
			m.Label_Width.setBounds(20,230,150,20);
			m.Label_Height.setBounds(20,320,150,20);
			m.Label_Value.setBounds(20,410,150,20);
			m.Label_Type.setBounds(20,500,150,20);
			m.Label_Name.setBounds(20,590,150,20);
			m.Attribute_Panel.setBackground(Color.PINK);
			m.Attribute_Panel.setLayout(null);
			m.Attribute_Panel.add(m.Label_X);
			m.Attribute_Panel.add(m.Label_Y);
			m.Attribute_Panel.add(m.Label_Width);
			m.Attribute_Panel.add(m.Label_Height);
			m.Attribute_Panel.add(m.Label_Value);
			m.Attribute_Panel.add(m.Label_Type);
			m.Attribute_Panel.add(m.Label_Name);
			m.Text_X.setBounds(180,50,100,20);
			m.Text_Y.setBounds(180,140,100,20);
			m.Text_Width.setBounds(180,230,100,20);
			m.Text_Height.setBounds(180,320,100,20);
			m.Text_Value.setBounds(180,410,100,20);
			m.Combo.setBounds(180,500,100,20);
			m.Text_Name.setBounds(180,590,100,20);
			m.Attribute_Panel.add(m.Text_X);
			m.Attribute_Panel.add(m.Text_Y);
			m.Attribute_Panel.add(m.Text_Width);
			m.Attribute_Panel.add(m.Text_Height);
			m.Attribute_Panel.add(m.Text_Value);
			m.Attribute_Panel.add(m.Combo);
			m.Attribute_Panel.add(m.Text_Name);
			m.Button_Change.setBounds(100,650,100,40);
			m.Attribute_Panel.add(m.Button_Change);
			m.Editor_Panel.setBackground(Color.WHITE);
			m.Editor_Panel.setLayout(null);
			m.Radio_Label.setSelected(true);
			m.Radio_Label.setBounds(20,30,90,30);
			m.Radio_Button.setBounds(120,30,90,30);
			m.Radio_TextField.setBounds(220,30,90,30);
			m.Radio_Label.setBackground(Color.WHITE);
			m.Radio_Button.setBackground(Color.WHITE);
			m.Radio_TextField.setBackground(Color.WHITE);
			m.Editor_Panel.add(m.Radio_Label);
			m.Editor_Panel.add(m.Radio_TextField);
			m.Editor_Panel.add(m.Radio_Button);
			m.Button_Draw.setBounds(330,30,100,30);
			m.Button_Delete.setBounds(440,30,100,30);
			m.Button_Select.setBounds(550, 30, 100, 30);
			m.Editor_Panel.add(m.Button_Draw);
			m.Editor_Panel.add(m.Button_Delete);
			m.Editor_Panel.add(m.Button_Select);
			m.tBar.setBounds(0,0,1000,30);
			m.Attribute_Panel.setBounds(0,30,300,770);
			m.Editor_Panel.setBounds(300,30,700,770);
			c.add(m.mBar);
			setJMenuBar(m.mBar);
			c.add(m.tBar);
			c.add(m.Attribute_Panel);
			c.add(m.Editor_Panel);
			Show_Box();
			setSize(1000,800);
			setVisible(true);
			Select();
			repaint();
		}

	}
	public class model	//model
	{
		JButton Button_Menu_New;
		JButton Button_Menu_Open;
		JButton Button_Menu_Save;
		JButton Button_Menu_SaveDifferentName;
		JButton Button_Menu_CreateJava;
		JButton Button_Menu_Exit;
		JButton Button_Tool_New;
		JButton Button_Tool_Open;
		JButton Button_Tool_Save;
		JButton Button_Tool_SaveDifferentName;
		JButton Button_Tool_CreateJava;
		JButton Button_Tool_Exit;
		JMenu Menu;
		JMenuBar mBar;
		JToolBar tBar;
		JButton Button_Change;
		String[] Combo_Item = {"JLabel","JButton","JTextField","JPanel"};
		JComboBox Combo;
		JPanel Attribute_Panel;
		JLabel Label_X;
		JLabel Label_Y;
		JLabel Label_Width;
		JLabel Label_Height;
		JLabel Label_Value;
		JLabel Label_Type;
		JLabel Label_Name;
		JTextField Text_X;
		JTextField Text_Y;
		JTextField Text_Width;
		JTextField Text_Height;
		JTextField Text_Value;
		JTextField Text_Type;
		JTextField Text_Name;
		JPanel Editor_Panel;
		JRadioButton Radio_Label;
		JRadioButton Radio_Button;
		JRadioButton Radio_TextField;
		ButtonGroup bg;
		JButton Button_Draw;
		JButton Button_Delete;
		JButton Button_Select;

		int cnt_Label=0;	//Label�� ��
		int cnt_Button=0;	//Button�� ��
		int cnt_TextField=0;	//TextField�� ��
		Label la[] = new Label[100];	//Label�� ������� �� ������ �����ϴ� ��ü
		Button bu[] = new Button[100];	//Button�� ������� �� ������ �����ϴ� ��ü
		TextField tf[] = new TextField[100];	//TextField�� ������� �� ������ �����ϴ� ��ü
		model()
		{
			Button_Menu_New = new JButton("���� �����");
			Button_Menu_Open = new JButton("����");
			Button_Menu_Save = new JButton("����");
			Button_Menu_SaveDifferentName = new JButton("�ٸ� �̸����� ����");
			Button_Menu_CreateJava = new JButton(".java ���� ����");
			Button_Menu_Exit = new JButton("�ݱ�");
			Button_Tool_New = new JButton("���� �����");
			Button_Tool_Open = new JButton("����");
			Button_Tool_Save = new JButton("����");
			Button_Tool_SaveDifferentName = new JButton("�ٸ� �̸����� ����");
			Button_Tool_CreateJava = new JButton(".java ���� ����");
			Button_Tool_Exit = new JButton("�ݱ�");
			Menu = new JMenu("����");
			mBar = new JMenuBar();
			tBar = new JToolBar();
			Button_Change = new JButton("����");
			Combo = new JComboBox(Combo_Item);
			Attribute_Panel = new JPanel();
			Label_X = new JLabel("x��ǥ");
			Label_Y = new JLabel("y��ǥ");
			Label_Width = new JLabel("�ʺ�");
			Label_Height = new JLabel("����");
			Label_Value = new JLabel("������Ʈ�� �ؽ�Ʈ �Ӽ���");
			Label_Type = new JLabel("������Ʈ Ÿ��");
			Label_Name = new JLabel("������Ʈ ������");
			Text_X = new JTextField();
			Text_Y = new JTextField();
			Text_Width = new JTextField();
			Text_Height = new JTextField();
			Text_Value = new JTextField();
			Text_Type = new JTextField();
			Text_Name = new JTextField();
			Editor_Panel = new JPanel();
			Radio_Label = new JRadioButton("Label");
			Radio_Button = new JRadioButton("Button");
			Radio_TextField = new JRadioButton("TextField");
			bg = new ButtonGroup();
			bg.add(Radio_Label);
			bg.add(Radio_Button);
			bg.add(Radio_TextField);
			Button_Draw = new JButton("�׸���");
			Button_Delete = new JButton("�����");
			Button_Select = new JButton("����/�̵�");
			control Button_Event = new control(this);
			Button_Menu_New.addActionListener(Button_Event);
			Button_Menu_Open.addActionListener(Button_Event);
			Button_Menu_Save.addActionListener(Button_Event);
			Button_Menu_SaveDifferentName.addActionListener(Button_Event);
			Button_Menu_CreateJava.addActionListener(Button_Event);
			Button_Menu_Exit.addActionListener(Button_Event);
			Button_Tool_New.addActionListener(Button_Event);
			Button_Tool_Open.addActionListener(Button_Event);
			Button_Tool_Save.addActionListener(Button_Event);
			Button_Tool_SaveDifferentName.addActionListener(Button_Event);
			Button_Tool_CreateJava.addActionListener(Button_Event);
			Button_Tool_Exit.addActionListener(Button_Event);
			Button_Delete.addActionListener(Button_Event);
			Button_Draw.addActionListener(Button_Event);
			Button_Change.addActionListener(Button_Event);
			Button_Select.addActionListener(Button_Event);
		}
	}
	class control implements ActionListener	//Control
	{
		model Model;
		control(model Model)
		{
			this.Model = Model;
		}
		public void actionPerformed(ActionEvent e)	//��ư Ŭ�� �� �̺�Ʈ �߻� ����
		{
			JFileChooser jfc = new JFileChooser("C:");
			if(e.getActionCommand()=="���� �����")	//���� ����� ��ư Ŭ����
			{	//��� ���� �ʱ�ȭ
				New_File();	//���� ����� �Լ�
				m.Text_X.setText(null);
				m.Text_Y.setText(null);
				m.Text_Height.setText(null);
				m.Text_Width.setText(null);
				m.Text_Value.setText(null);
				m.Text_Name.setText(null);
				m.Combo.setSelectedItem("JLabel");
			}
			
			else if(e.getActionCommand()=="����")	//���� ��ư Ŭ����
			{
				if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){// showopendialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
					New_File();//���� ����� �Լ�
					JSONParser parser = new JSONParser();//JSONFile�� �о���̱� ���� JSONParser
					try {
						Object obj = parser.parse(new FileReader(jfc.getSelectedFile()));//���õ� ������ ������ ������Ʈ�� ����
						JSONObject jsonObject = (JSONObject) obj;//������ JSONObject�� �ű�
						JSONArray Open_Label_Array = (JSONArray) jsonObject.get("Data_Label");//JSONObject ������ Label�� �����Ͱ� �� JSONArray�� ã�� ������ �޴� ��ü ����
						JSONArray Open_Button_Array = (JSONArray) jsonObject.get("Data_Button");//JSONObject ������ Button�� �����Ͱ� �� JSONArray�� ã�� ������ �޴� ��ü ����
						JSONArray Open_TextField_Array = (JSONArray) jsonObject.get("Data_TextField");//JSONObject ������ TextField�� �����Ͱ� �� JSONArray�� ã�� ������ �޴� ��ü ����
						Iterator<String> iterator_Label = Open_Label_Array.iterator();//Label�� �����Ͱ� �ִ� JSONArray�� �б� ���� Iterator
						while (iterator_Label.hasNext()) {
							m.la[m.cnt_Label] = new Label();//�ҷ��� Label�� ���� ������ ���� ��ü ����
							m.la[m.cnt_Label].x = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].x_drag = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].y = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].y_drag = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].width = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].height = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].value = iterator_Label.next();
                    	   	m.la[m.cnt_Label].type = iterator_Label.next();
                    	   	m.la[m.cnt_Label].name = iterator_Label.next();//Label�� ������ �о����
                    	   	m.la[m.cnt_Label].JLabel_Component = new JLabel(m.la[m.cnt_Label].value);
                    	   	m.la[m.cnt_Label].JLabel_Component.setBackground(Color.YELLOW);
                    	   	m.la[m.cnt_Label].JLabel_Component.setBorder(eborder);
                    	   	m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x, m.la[m.cnt_Label].y, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);
                    	   	m.Editor_Panel.add(m.la[m.cnt_Label].JLabel_Component);
                    	   	la_CreateBox(m.la[m.cnt_Label]);
                    	   	for(int i=0; i<8; ++i)
                    	   	{
                    	   		listener_box_la[m.cnt_Label][i] = new BoxListener_la(m.cnt_Label, i);
                    	   		m.la[m.cnt_Label].Box[i].addMouseListener(listener_box_la[m.cnt_Label][i]);
                    	   		m.la[m.cnt_Label].Box[i].addMouseMotionListener(listener_box_la[m.cnt_Label][i]);
                    	   	}//�о���� �������� ���� EditorPane�� Label ����
                    	   	++m.cnt_Label;//�̸� ���� ����Ǿ� �ִ� Label�� ������ �ľ��� �� ����
						}
						Iterator<String> iterator_Button = Open_Button_Array.iterator();//Button�� �����Ͱ� �ִ� JSONArray�� �б� ���� Iterator
						while (iterator_Button.hasNext()) {
							m.bu[m.cnt_Button] = new Button();//�ҷ��� Button�� ���� ������ ���� ��ü ����
							m.bu[m.cnt_Button].x = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].x_drag = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].y = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].y_drag = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].width = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].height = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].value = iterator_Button.next();
							m.bu[m.cnt_Button].type = iterator_Button.next();
							m.bu[m.cnt_Button].name = iterator_Button.next();//Button�� ������ �о����
							m.bu[m.cnt_Button].JButton_Component = new JButton(m.bu[m.cnt_Button].value);
							m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x, m.bu[m.cnt_Button].y, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);
							m.Editor_Panel.add(m.bu[m.cnt_Button].JButton_Component);
							bu_CreateBox(m.bu[m.cnt_Button]);
							for(int i=0; i<8; ++i)
							{
								listener_box_bu[m.cnt_Button][i] = new BoxListener_bu(m.cnt_Button, i);
								m.bu[m.cnt_Button].Box[i].addMouseListener(listener_box_bu[m.cnt_Button][i]);
								m.bu[m.cnt_Button].Box[i].addMouseMotionListener(listener_box_bu[m.cnt_Button][i]);
							}//�о���� �������� ���� EditorPane�� Button ����							
							++m.cnt_Button;//�̸� ���� ����Ǿ� �ִ� Button�� ������ �ľ��� �� ����
						}
						Iterator<String> iterator_TextField = Open_TextField_Array.iterator();//TextField�� �����Ͱ� �ִ� JSONArray�� �б� ���� Iterator
						while (iterator_TextField.hasNext()) {
							m.tf[m.cnt_TextField] = new TextField();//�ҷ��� TextField�� ���� ������ ���� ��ü ����
    	              	   	m.tf[m.cnt_TextField].x = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].x_drag = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].y = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].y_drag = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].width = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].height = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].value = iterator_TextField.next();
    	              	   	m.tf[m.cnt_TextField].type = iterator_TextField.next();
    	              	   	m.tf[m.cnt_TextField].name = iterator_TextField.next();//TextField�� ������ �о����
    	              	   	m.tf[m.cnt_TextField].JTextField_Component = new JTextField(m.tf[m.cnt_TextField].value);
    	              	   	m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x, m.tf[m.cnt_TextField].y, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);
    	              	   	m.Editor_Panel.add(m.tf[m.cnt_TextField].JTextField_Component);
    	              	   	tf_CreateBox(m.tf[m.cnt_TextField]);
    	              	   	for(int i=0; i<8; ++i)
    	              	   	{
    	              	   		listener_box_tf[m.cnt_TextField][i] = new BoxListener_tf(m.cnt_TextField, i);
    	              	   		m.tf[m.cnt_TextField].Box[i].addMouseListener(listener_box_tf[m.cnt_TextField][i]);
    	              	   		m.tf[m.cnt_TextField].Box[i].addMouseMotionListener(listener_box_tf[m.cnt_TextField][i]);
    	              	   	}//�о���� �������� ���� EditorPane�� TextField ����
    	              	   	++m.cnt_TextField;//�̸� ���� ����Ǿ� �ִ� TextField�� ������ �ľ��� �� ����
						}
					} catch (FileNotFoundException E) {
						System.err.println("������ ã�� �� �����ϴ�.");//E.printStackTrace();
					} catch (IOException E) {
						System.err.println("����� ������ �߻��߽��ϴ�.");//E.printStackTrace();
					} catch (ParseException E) {
						System.err.println("Parse ������ �߻��߽��ϴ�.");//E.printStackTrace();
					}                     
				}
				Make_Non_Selected();
				Show_Box();
				repaint();
			}
			else if(e.getActionCommand()=="����")	//���� ��ư Ŭ����
			{
				JSONObject obj = new JSONObject();//JSONArray���� ���� JSONObject
				JSONArray Save_Label_Array  = new JSONArray();//JLabel���� ������ ���� JSONArray
				JSONArray Save_Button_Array  = new JSONArray();//JButton���� ������ ���� JSONArray
				JSONArray Save_TextField_Array  = new JSONArray();//JTextField���� ������ ���� JSONArray
				
				if(m.cnt_Label != 0)//Label�� �����Ǿ� ���� �� �� ����ŭ for���� ������
				{
					for(int j = 0; j < m.cnt_Label; j++)
					{
						Save_Label_Array.add(String.valueOf(m.la[j].x));
						Save_Label_Array.add(String.valueOf(m.la[j].x_drag));
						Save_Label_Array.add(String.valueOf(m.la[j].y));
						Save_Label_Array.add(String.valueOf(m.la[j].y_drag));
						Save_Label_Array.add(String.valueOf(m.la[j].width));
						Save_Label_Array.add(String.valueOf(m.la[j].height));
						Save_Label_Array.add(m.la[j].value);
						Save_Label_Array.add(m.la[j].type);
						Save_Label_Array.add(m.la[j].name);
					}
				}//�󺧿� JSONArray�� ���ʴ�� Label�� �Ӽ����� ������ �߰�
				obj.put("Data_Label", Save_Label_Array);//JSONObject�� Label�� �Ӽ����� ������ ����� JSONArray�� �߰�
				if(m.cnt_Button != 0)//Button�� �����Ǿ� ���� �� �� ����ŭ for���� ������
				{
					for(int j = 0; j < m.cnt_Button; j++)
					{
						Save_Button_Array.add(String.valueOf(m.bu[j].x));
						Save_Button_Array.add(String.valueOf(m.bu[j].x_drag));
						Save_Button_Array.add(String.valueOf(m.bu[j].y));
						Save_Button_Array.add(String.valueOf(m.bu[j].y_drag));
						Save_Button_Array.add(String.valueOf(m.bu[j].width));
						Save_Button_Array.add(String.valueOf(m.bu[j].height));
						Save_Button_Array.add(m.bu[j].value);
						Save_Button_Array.add(m.bu[j].type);
						Save_Button_Array.add(m.bu[j].name);
					}
				}//��ư�� JSONArray�� ���ʴ�� Button�� �Ӽ����� ������ �߰�
				obj.put("Data_Button", Save_Button_Array);//JSONObject�� Button�� �Ӽ����� ������ ����� JSONArray�� �߰�
				if(m.cnt_TextField != 0)//TextField�� �����Ǿ� ���� �� �� ����ŭ for���� ������
				{
					for(int j = 0; j < m.cnt_TextField; j++)
					{
						Save_TextField_Array.add(String.valueOf(m.tf[j].x));
						Save_TextField_Array.add(String.valueOf(m.tf[j].x_drag));						
						Save_TextField_Array.add(String.valueOf(m.tf[j].y));
						Save_TextField_Array.add(String.valueOf(m.tf[j].y_drag));
						Save_TextField_Array.add(String.valueOf(m.tf[j].width));
						Save_TextField_Array.add(String.valueOf(m.tf[j].height));
						Save_TextField_Array.add(m.tf[j].value);
						Save_TextField_Array.add(m.tf[j].type);
						Save_TextField_Array.add(m.tf[j].name);
					}
				}//�ؽ�Ʈ�ʵ� �� JSONArray�� ���ʴ�� TextFeild�� �Ӽ����� ������ �߰�
				obj.put("Data_TextField", Save_TextField_Array);//JSONObject�� TextField�� �Ӽ����� ������ ����� JSONArray�� �߰�
				try (FileWriter file = new FileWriter("Data.txt")) {//Workspace�� Data��� �̸��� txt���Ͽ� ������ ����
					file.write(obj.toString());
					file.flush();
					file.close();
				}
				catch(IOException ioe)//����ó��
				{
					System.err.println("����� ������ �߻��߽��ϴ�.");//ioe.printStackTrace();
				}
			}
			else if(e.getActionCommand()=="�ٸ� �̸����� ����")	//�ٸ� �̸����� ���� ��ư Ŭ����
			{
				if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					JSONObject obj = new JSONObject();//JSONArray���� ���� JSONObject
					JSONArray Save_Label_Array  = new JSONArray();//JLabel���� ������ ���� JSONArray
					JSONArray Save_Button_Array  = new JSONArray();//JButton���� ������ ���� JSONArray
					JSONArray Save_TextField_Array  = new JSONArray();//JTextField���� ������ ���� JSONArray
					
					if(m.cnt_Label != 0)//Label�� �����Ǿ� ���� �� �� ����ŭ for���� ������
					{
						for(int j = 0; j < m.cnt_Label; j++)
						{
							Save_Label_Array.add(String.valueOf(m.la[j].x));
							Save_Label_Array.add(String.valueOf(m.la[j].x_drag));
							Save_Label_Array.add(String.valueOf(m.la[j].y));
							Save_Label_Array.add(String.valueOf(m.la[j].y_drag));
							Save_Label_Array.add(String.valueOf(m.la[j].width));
							Save_Label_Array.add(String.valueOf(m.la[j].height));
							Save_Label_Array.add(m.la[j].value);
							Save_Label_Array.add(m.la[j].type);
							Save_Label_Array.add(m.la[j].name);
						}
					}//�󺧿� JSONArray�� ���ʴ�� Label�� �Ӽ����� ������ �߰�
					obj.put("Data_Label", Save_Label_Array);//JSONObject�� Label�� �Ӽ����� ������ ����� JSONArray�� �߰�
					if(m.cnt_Button != 0)//Button�� �����Ǿ� ���� �� �� ����ŭ for���� ������
					{
						for(int j = 0; j < m.cnt_Button; j++)
						{
							Save_Button_Array.add(String.valueOf(m.bu[j].x));
							Save_Button_Array.add(String.valueOf(m.bu[j].x_drag));
							Save_Button_Array.add(String.valueOf(m.bu[j].y));
							Save_Button_Array.add(String.valueOf(m.bu[j].y_drag));
							Save_Button_Array.add(String.valueOf(m.bu[j].width));
							Save_Button_Array.add(String.valueOf(m.bu[j].height));
							Save_Button_Array.add(m.bu[j].value);
							Save_Button_Array.add(m.bu[j].type);
							Save_Button_Array.add(m.bu[j].name);
						}
					}//��ư�� JSONArray�� ���ʴ�� Button�� �Ӽ����� ������ �߰�
					obj.put("Data_Button", Save_Button_Array);//JSONObject�� Button�� �Ӽ����� ������ ����� JSONArray�� �߰�
					if(m.cnt_TextField != 0)//TextField�� �����Ǿ� ���� �� �� ����ŭ for���� ������
					{
						for(int j = 0; j < m.cnt_TextField; j++)
						{
							Save_TextField_Array.add(String.valueOf(m.tf[j].x));
							Save_TextField_Array.add(String.valueOf(m.tf[j].x_drag));
							Save_TextField_Array.add(String.valueOf(m.tf[j].y));
							Save_TextField_Array.add(String.valueOf(m.tf[j].y_drag));
							Save_TextField_Array.add(String.valueOf(m.tf[j].width));
							Save_TextField_Array.add(String.valueOf(m.tf[j].height));
							Save_TextField_Array.add(m.tf[j].value);
							Save_TextField_Array.add(m.tf[j].type);
							Save_TextField_Array.add(m.tf[j].name);
						}
					}//�ؽ�Ʈ�ʵ� �� JSONArray�� ���ʴ�� TextFeild�� �Ӽ����� ������ �߰�
					obj.put("Data_TextField", Save_TextField_Array);//JSONObject�� TextField�� �Ӽ����� ������ ����� JSONArray�� �߰�
					try (FileWriter file = new FileWriter(jfc.getSelectedFile()+".txt")) {//����ڰ� ������ �̸��� �ؽ�Ʈ ���Ͽ� ������ ����
						file.write(obj.toString());
						file.flush();
						file.close();
					}
					catch(IOException ioe)//����ó��
					{
						System.err.println("����� ������ �߻��߽��ϴ�.");//ioe.printStackTrace();
					}
				}
			}
			else if(e.getActionCommand()==".java ���� ����")	//.java ���� ���� Ŭ����
			{
				try {
					File f = new File("Make_JavaFile.java");//Make_JavaFile�̶�� �ڹ� ������ ����
					if(!f.exists())
					{
						f.createNewFile();
					}
					else
					{
						f.delete();
						f.createNewFile();
					}
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("import java.awt.*;");
					bw.newLine();
					bw.write("import javax.swing.*;");
					bw.newLine();
					bw.write("import javax.swing.border.EtchedBorder;");
					bw.newLine();
					bw.newLine();
					bw.write("public class Make_JavaFile extends JFrame {");
					bw.newLine();
					bw.write("	Make_JavaFile(){");
					bw.newLine();
					bw.write("		EtchedBorder eborder = new EtchedBorder(EtchedBorder.RAISED);");
					bw.newLine();
					bw.write("		setTitle(\"JavaFile\");");
					bw.newLine();
					bw.write("		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);");
					bw.newLine();
					bw.newLine();
					bw.write("		Container c = getContentPane();");
					bw.newLine();
					bw.write("		c.setLayout(null);");
					bw.newLine();
					bw.write("		c.setBackground(Color.WHITE);");
					bw.newLine();
					for(int i=0; i<m.cnt_Label; ++i)
					{
						bw.write("		JLabel " + m.la[i].name + " = new JLabel(\"" + m.la[i].value +"\");");
						bw.newLine();
						bw.write("		" + m.la[i].name + ".setBounds(" + m.la[i].x + ", " + m.la[i].y + ", " + m.la[i].width + ", " + m.la[i].height + ");");
						bw.newLine();
						bw.write("		" + m.la[i].name + ".setBackground(Color.YELLOW);");
						bw.newLine();
						bw.write("		" + m.la[i].name + ".setBorder(eborder);");
						bw.newLine();
						bw.write("		c.add(" + m.la[i].name + ");");
						bw.newLine();
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						bw.write("		JButton " + m.bu[i].name + " = new JButton(\"" + m.bu[i].value + "\");");
						bw.newLine();
						bw.write("		" + m.bu[i].name + ".setBounds(" + m.bu[i].x + ", " + m.bu[i].y + ", " + m.bu[i].width + ", " + m.bu[i].height + ");");
						bw.newLine();
						bw.write("		c.add(" + m.bu[i].name + ");");
						bw.newLine();
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						bw.write("		JTextField " + m.tf[i].name + " = new JTextField(\"" + m.tf[i].value + "\");");
						bw.newLine();
						bw.write("		" + m.tf[i].name + ".setBounds(" + m.tf[i].x + ", " + m.tf[i].y + ", " + m.tf[i].width + ", " + m.tf[i].height + ");");
						bw.newLine();
						bw.write("		c.add(" + m.tf[i].name + ");");
						bw.newLine();
					}
					bw.write("		setSize(700, 770);");
					bw.newLine();
					bw.write("		setVisible(true);");
					bw.newLine();
					bw.write("	}");
					bw.newLine();
					bw.write("	public static void main(String[] args){");
					bw.newLine();
					bw.write("		Make_JavaFile mjf = new Make_JavaFile();");
					bw.newLine();
					bw.write("	}");
					bw.newLine();
					bw.write("}");
					bw.flush();
					fw.flush();
					bw.close();
					fw.close();
				}//������Ʈ�� �׷��� �ڵ带 ������ ���� �ȿ� ����
				catch(IOException ioe)//����ó��
				{
					System.err.println("����� ������ �߻��߽��ϴ�.");//ioe.printStackTrace();
				}
			}
			else if(e.getActionCommand()=="�ݱ�")	//�ݱ� ��ư Ŭ����
			{
				System.exit(0);	//���α׷� ����
			}
			else if(e.getActionCommand()=="����")	//���� ��ư Ŭ����
			{
				int tmp=0;	//������ �Ǿ������� ��Ÿ���� ����. ������ �Ǿ����� 1�� ��ȯ
				if(m.Combo.getSelectedItem()=="JLabel")	//������Ʈ Ÿ�� �޺��ڽ��� JLabel�� �Ǿ����� ��
				{
					for(int i=0; i<m.cnt_Label; ++i)
					{
						if(m.la[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� Label�� ���
						{	//�Ӽ� �г��� TextField�� ���� �о� ���� ���õ� la��ü�� ������ �ű��.
							m.la[i].x = Integer.parseInt(m.Text_X.getText());
							m.la[i].y = Integer.parseInt(m.Text_Y.getText());
							m.la[i].width = Integer.parseInt(m.Text_Width.getText());
							m.la[i].height = Integer.parseInt(m.Text_Height.getText());
							m.la[i].value = m.Text_Value.getText();
							m.la[i].name = m.Text_Name.getText();
							m.la[i].JLabel_Component.setText(m.la[i].value);
							m.la[i].x_drag = m.la[i].x + m.la[i].width;
							m.la[i].y_drag = m.la[i].y + m.la[i].height;
							m.la[i].JLabel_Component.setBounds(m.la[i].x, m.la[i].y, m.la[i].width, m.la[i].height);
							la_CreateBox(m.la[i]);
							Show_Box();
							tmp = 1;	//���� �Ϸ�
						}
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						if(m.bu[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� Button�� ���
						{	//�Ӽ� �г��� TextField�� ���� �о� ���� ���õ� bu��ü�� ������ �ű��.
							m.bu[i].x = Integer.parseInt(m.Text_X.getText());
							m.bu[i].y = Integer.parseInt(m.Text_Y.getText());
							m.bu[i].width = Integer.parseInt(m.Text_Width.getText());
							m.bu[i].height = Integer.parseInt(m.Text_Height.getText());
							m.bu[i].value = m.Text_Value.getText();
							m.bu[i].name = m.Text_Name.getText();
							m.bu[i].JButton_Component.setText(m.bu[i].value);
							m.bu[i].x_drag = m.bu[i].x + m.bu[i].width;
							m.bu[i].y_drag = m.bu[i].y + m.bu[i].height;
							Button_to_Label(m.bu[i]);	//���� ���õ� bu��ü�� la��ü�� �����Ѵ�
							Remove_bu(m.bu[i]);	//���õ� bu��ü�� �����Ѵ�.
							Show_Box();
							--m.cnt_Button;	//Button�� ���� ���ҽ�Ų��.
							tmp = 1;	//���� �Ϸ�
						}
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						if(m.tf[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� TextField�� ���
						{	//�Ӽ� �г��� TextField�� ���� �о� ���� ���õ� tf��ü�� ������ �ű��. 
							m.tf[i].x = Integer.parseInt(m.Text_X.getText());
							m.tf[i].y = Integer.parseInt(m.Text_Y.getText());
							m.tf[i].width = Integer.parseInt(m.Text_Width.getText());
							m.tf[i].height = Integer.parseInt(m.Text_Height.getText());
							m.tf[i].value = m.Text_Value.getText();
							m.tf[i].name = m.Text_Name.getText();
							m.tf[i].JTextField_Component.setText(m.tf[i].value);
							m.tf[i].x_drag = m.tf[i].x + m.tf[i].width;
							m.tf[i].y_drag = m.tf[i].y + m.tf[i].height;
							TextField_to_Label(m.tf[i]);	//���� ���õ� tf��ü�� la��ü�� �����Ѵ�.
							Remove_tf(m.tf[i]);	//���õ� tf��ü�� �����Ѵ�.
							Show_Box();
							--m.cnt_TextField;	//TextField�� ���� ���ҽ�Ų��.
							tmp = 1;	//���� �Ϸ�
						}
					}
				}
				else if(m.Combo.getSelectedItem()=="JButton")	//������Ʈ Ÿ�� �޺��ڽ��� JButton���� ���õǾ��� ���
				{
					for(int i=0; i<m.cnt_Label; ++i)
					{
						if(m.la[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� Label�� ���
						{	//�Ӽ� �г��� TextField���� �о� ���� ���õ� la��ü�� ������ �ű��.
							m.la[i].x = Integer.parseInt(m.Text_X.getText());
							m.la[i].y = Integer.parseInt(m.Text_Y.getText());
							m.la[i].width = Integer.parseInt(m.Text_Width.getText());
							m.la[i].height = Integer.parseInt(m.Text_Height.getText());
							m.la[i].value = m.Text_Value.getText();
							m.la[i].name = m.Text_Name.getText();
							m.la[i].JLabel_Component.setText(m.la[i].value);
							m.la[i].x_drag = m.la[i].x + m.la[i].width;
							m.la[i].y_drag = m.la[i].y + m.la[i].height;
							Label_to_Button(m.la[i]);	//���� ���õ� la��ü�� bu��ü�� �����Ѵ�.
							Remove_la(m.la[i]);	//���õ� la��ü�� �����Ѵ�.
							Show_Box();
							--m.cnt_Label;	//Label�� ���� ���ҽ�Ų��.
							tmp = 1;	//���� �Ϸ�
						}
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						if(m.bu[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� Button�� ���
						{	//�Ӽ� �г��� TextField���� �о� ���� ���õ� bu��ü�� ������ �ű��.
							m.bu[i].x = Integer.parseInt(m.Text_X.getText());
							m.bu[i].y = Integer.parseInt(m.Text_Y.getText());
							m.bu[i].width = Integer.parseInt(m.Text_Width.getText());
							m.bu[i].height = Integer.parseInt(m.Text_Height.getText());
							m.bu[i].value = m.Text_Value.getText();
							m.bu[i].name = m.Text_Name.getText();
							m.bu[i].JButton_Component.setText(m.bu[i].value);
							m.bu[i].x_drag = m.bu[i].x + m.bu[i].width;
							m.bu[i].y_drag = m.bu[i].y + m.bu[i].height;
							m.bu[i].JButton_Component.setBounds(m.bu[i].x, m.bu[i].y, m.bu[i].width, m.bu[i].height);
							bu_CreateBox(m.bu[i]);
							Show_Box();
							tmp = 1;	//���� �Ϸ�
						}
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						if(m.tf[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� TextField�� ���
						{	//�Ӽ� �г��� TextField���� �о� ���� ���õ� tf��ü�� ������ �ű��.
							m.tf[i].x = Integer.parseInt(m.Text_X.getText());
							m.tf[i].y = Integer.parseInt(m.Text_Y.getText());
							m.tf[i].width = Integer.parseInt(m.Text_Width.getText());
							m.tf[i].height = Integer.parseInt(m.Text_Height.getText());
							m.tf[i].value = m.Text_Value.getText();
							m.tf[i].name = m.Text_Name.getText();
							m.tf[i].JTextField_Component.setText(m.tf[i].value);
							m.tf[i].x_drag = m.tf[i].x + m.tf[i].width;
							m.tf[i].y_drag = m.tf[i].y + m.tf[i].height;
							TextField_to_Button(m.tf[i]);	//���� ���õ� tf��ü�� bu��ü�� �����Ѵ�.
							Remove_tf(m.tf[i]);	//���� ���õ� tf��ü�� �����Ѵ�.
							Show_Box();
							--m.cnt_TextField;	//TextField�� ���� ���ҽ�Ų��.
							tmp = 1;	//���� �Ϸ�
						}
					}
				}
				else if(m.Combo.getSelectedItem()=="JTextField")	//������Ʈ Ÿ�� �޺��ڽ��� JTextField�� �Ǿ����� ��
				{
					for(int i=0; i<m.cnt_Label; ++i)
					{
						if(m.la[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� Label�� ���
						{	//�Ӽ� �г��� TextField���� �о� ���� ���õ� la��ü�� ������ �ű��.
							m.la[i].x = Integer.parseInt(m.Text_X.getText());
							m.la[i].y = Integer.parseInt(m.Text_Y.getText());
							m.la[i].width = Integer.parseInt(m.Text_Width.getText());
							m.la[i].height = Integer.parseInt(m.Text_Height.getText());
							m.la[i].value = m.Text_Value.getText();
							m.la[i].name = m.Text_Name.getText();
							m.la[i].JLabel_Component.setText(m.la[i].value);
							m.la[i].x_drag = m.la[i].x + m.la[i].width;
							m.la[i].y_drag = m.la[i].y + m.la[i].height;
							Label_to_TextField(m.la[i]);	//���� ���õ� la��ü�� tf��ü�� �����Ѵ�.
							Remove_la(m.la[i]);	//���� ���õ� la��ü�� �����Ѵ�.
							Show_Box();
							--m.cnt_Label;	//Label�� ���� ���ҽ�Ų��.
							tmp = 1;	//���� �Ϸ�
						}
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						if(m.bu[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� Button�� ���
						{	//�Ӽ� �г��� TextField���� �о� ���� ���õ� bu��ü�� ������ �ű��.
							m.bu[i].x = Integer.parseInt(m.Text_X.getText());
							m.bu[i].y = Integer.parseInt(m.Text_Y.getText());
							m.bu[i].width = Integer.parseInt(m.Text_Width.getText());
							m.bu[i].height = Integer.parseInt(m.Text_Height.getText());
							m.bu[i].value = m.Text_Value.getText();
							m.bu[i].name = m.Text_Name.getText();
							m.bu[i].JButton_Component.setText(m.bu[i].value);
							m.bu[i].x_drag = m.bu[i].x + m.bu[i].width;
							m.bu[i].y_drag = m.bu[i].y + m.bu[i].height;
							Button_to_TextField(m.bu[i]);	//���� ���õ� bu��ü�� tf��ü�� �����Ѵ�.
							Remove_bu(m.bu[i]);	//���� ���õ� bu��ü�� �����Ѵ�.
							Show_Box();
							--m.cnt_Button;	//Button�� ���� ���ҽ�Ų��.
							tmp = 1;	//���� �Ϸ�
						}
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						if(m.tf[i].isSelected == true && tmp == 0)	//���� ���õ� ������Ʈ�� TextField�� ���
						{	//�Ӽ� �г��� TextField���� �о� ���� ���õ� tf��ü�� ������ �ű��.
							m.tf[i].x = Integer.parseInt(m.Text_X.getText());
							m.tf[i].y = Integer.parseInt(m.Text_Y.getText());
							m.tf[i].width = Integer.parseInt(m.Text_Width.getText());
							m.tf[i].height = Integer.parseInt(m.Text_Height.getText());
							m.tf[i].value = m.Text_Value.getText();
							m.tf[i].name = m.Text_Name.getText();
							m.tf[i].JTextField_Component.setText(m.tf[i].value);
							m.tf[i].x_drag = m.tf[i].x + m.tf[i].width;
							m.tf[i].y_drag = m.tf[i].y + m.tf[i].height;
							m.tf[i].JTextField_Component.setBounds(m.tf[i].x, m.tf[i].y, m.tf[i].width, m.tf[i].height);
							tf_CreateBox(m.tf[i]);							
							Show_Box();
							tmp = 1;	//���� �Ϸ�
						}
					}
				}
				repaint();	//����� �͵��� ȭ�鿡 ǥ��
			}
			else if(e.getActionCommand()=="�׸���")	//�׸��� ��ư Ŭ����
			{
				RemoveMouseListener();
				tmp = 0;	//�׸��⸦ ������ �� ���� �׸� ������Ʈ�� ��Ÿ��(1 : Label, 2 : Button, 3 : TextField, 4 : �׸���Ϸ�)
				if(tmp==0)
				{
					if(m.Radio_Label.isSelected() == true)	//Label�� ���õǾ��� ��
					{
						tmp=1;	//���� �׸��� ������Ʈ�� Label�� ����
						m.la[m.cnt_Label] = new Label();	//la��ü�� �ϳ� ����
						m.Editor_Panel.addMouseListener(new MouseAdapter() {	//Editor_Panel������ ���콺 �̺�Ʈ
							public void mousePressed(MouseEvent me) {	//���콺�� ������ ��
								if(tmp==1)
								{
									m.Text_X.setText(String.valueOf(me.getX()));	//���콺�� X��ǥ�� �а� ǥ��
									m.Text_Y.setText(String.valueOf(me.getY()));	//���콺�� Y��ǥ�� �а� ǥ��
									m.la[m.cnt_Label].x = me.getX();	//���콺�� X��ǥ�� �а� ����
									m.la[m.cnt_Label].y = me.getY();	//���콺�� Y��ǥ�� �а� ����
									m.la[m.cnt_Label].value = "Label"+(m.cnt_Label+1);	//value�� �⺻������ Label##������ �� ��ȣ ����
									m.la[m.cnt_Label].type = "Label";	//type�� Label�� ����
									m.la[m.cnt_Label].name = "Label"+(m.cnt_Label+1);	//name�� �⺻������ Label##������ �� ��ȣ ����
									m.Combo.setSelectedItem(m.Combo_Item[0]);	//������Ʈ Ÿ���� �޺��ڽ��� JLabel�� ����
									m.la[m.cnt_Label].JLabel_Component = new JLabel("Label"+(m.cnt_Label+1));	//la�� JLabel_Component ����
									m.la[m.cnt_Label].JLabel_Component.setBackground(Color.YELLOW);	//�׵θ� ���� ����
									m.la[m.cnt_Label].JLabel_Component.setBorder(eborder);	//�׵θ� ��� ����
									m.Editor_Panel.add(m.la[m.cnt_Label].JLabel_Component);	//���� ������Ʈ�� Editor_Panel�� �߰�
								}
							}
							public void mouseReleased(MouseEvent e) {	//���콺�� ���� ��
								if(tmp==1)
								{
									if(m.la[m.cnt_Label].x > m.la[m.cnt_Label].x_drag)	//x���� x_drag������ Ŭ ���(x������ ���������� �׷����� ���)
									{	//x�� x_drag���� �ڹٲ۴�
										int tmp2 = m.la[m.cnt_Label].x;
										m.la[m.cnt_Label].x = m.la[m.cnt_Label].x_drag;
										m.la[m.cnt_Label].x_drag = tmp2;
									}
									if(m.la[m.cnt_Label].y > m.la[m.cnt_Label].y_drag)	//y���� y_drag������ Ŭ ���(y������ ���������� �׷����� ���)
									{	//y���� y_drag���� �ڹٲ۴�
										int tmp2 = m.la[m.cnt_Label].y;
										m.la[m.cnt_Label].y = m.la[m.cnt_Label].y_drag;
										m.la[m.cnt_Label].y_drag = tmp2;
									}
									la_CreateBox(m.la[m.cnt_Label]);	//Box�� ���� �������
									Make_Non_Selected();	//��� ��ü�� isSelected�� false�� ����
									m.la[m.cnt_Label].isSelected = true;	//���� �������� ��ü�� isSelected�� true�� ����
									for(int i=0; i<8; ++i)	//8���� Box�� ���콺�����ʸ� �߰���
									{
										listener_box_la[m.cnt_Label][i] = new BoxListener_la(m.cnt_Label, i);
										m.la[m.cnt_Label].Box[i].addMouseListener(listener_box_la[m.cnt_Label][i]);
										m.la[m.cnt_Label].Box[i].addMouseMotionListener(listener_box_la[m.cnt_Label][i]);
									}
									++m.cnt_Label;	//Label���� 1 ������Ų��
									Show_Box();	//���� ���õ� ������Ʈ�� Box�� ������
									tmp = 4;	//�׸��� �Ϸ�
									repaint();	//����� ������Ʈ��� ���� �׸�
								}
							}
						});
						m.Editor_Panel.addMouseMotionListener(new MouseMotionAdapter(){	//Editor_Panel������ ���콺 �̺�Ʈ
							public void mouseDragged(MouseEvent me){	//�巡�� ���� ��
								if(tmp==1)
								{
									int reverse_x=0;	//x������ ���������� �׷����� ��� 1
									int reverse_y=0;	//y������ ���������� �׷����� ��� 1
									m.la[m.cnt_Label].x_drag = me.getX();	//�巡�� �ϴ� ���� ���콺�� X��ǥ�� ����
									m.la[m.cnt_Label].y_drag = me.getY();	//�巡�� �ϴ� ���� ���콺�� Y��ǥ�� ����
									if(m.la[m.cnt_Label].x > m.la[m.cnt_Label].x_drag)	//x������ ���������� �׷����� ���
									{
										m.la[m.cnt_Label].width = -(m.la[m.cnt_Label].x_drag - m.la[m.cnt_Label].x);	//width = -(x_drag - x)
										reverse_x=1;
									}
									else	//x������ ���������� �׷����� ���
									{
										m.la[m.cnt_Label].width = m.la[m.cnt_Label].x_drag - m.la[m.cnt_Label].x;	//width = x_drag - x										
									}
									if(m.la[m.cnt_Label].y > m.la[m.cnt_Label].y_drag)	//y������ ���������� �׷����� ���
									{
										m.la[m.cnt_Label].height = -(m.la[m.cnt_Label].y_drag - m.la[m.cnt_Label].y);	//height = -(y_drag - y)
										reverse_y=1;
									}
									else	//y������ ���������� �׷����� ���
									{
										m.la[m.cnt_Label].height = m.la[m.cnt_Label].y_drag - m.la[m.cnt_Label].y;	//height = y - y_drag
									}
									m.Text_Width.setText(Integer.toString(m.la[m.cnt_Label].width));	//�Ӽ� �г��� Width�� �ʺ� ǥ��
									m.Text_Height.setText(Integer.toString(m.la[m.cnt_Label].height));	//�Ӽ� �г��� Height�� �ʺ� ǥ��
									if(reverse_x==0 && reverse_y==0)	//x, y���� ��� ���������� �׷����� ���
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x, m.la[m.cnt_Label].y, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x, y, width, height)
									}
									else if(reverse_x==1 && reverse_y==0)	//x�� ������, y�� ���������� �׷����� ���
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x_drag, m.la[m.cnt_Label].y, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x_drag, y, width, height)
									}
									else if(reverse_x==0 && reverse_y==1)	//x�� ������, y�� ���������� �׷����� ���
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x, m.la[m.cnt_Label].y_drag, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x, y_drag, width, height)
									}
									else if(reverse_x==1 && reverse_y==1)	//x, y��� ���������� �׷����� ���
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x_drag, m.la[m.cnt_Label].y_drag, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x_drag, y_drag, width, height)
									}
								}
							}
						});
					}
					else if(Model.Radio_Button.isSelected()==true)	//Button�� ���õǾ��� ���
					{
						tmp=2;	//���� �׸��� ������Ʈ�� Button���� ����
						m.bu[m.cnt_Button] = new Button();	//bu��ü�� �ϳ� ����
						m.Editor_Panel.addMouseListener(new MouseAdapter() {	//Editor_Panel������ ���콺 �̺�Ʈ
							public void mousePressed(MouseEvent me) {	//���콺�� �������� ��
								if(tmp==2)
								{
									m.Text_X.setText(String.valueOf(me.getX()));	//���� ���콺�� X��ǥ�� �Ӽ��гο� �Է�
								    m.Text_Y.setText(String.valueOf(me.getY()));	//���� ���콺�� Y��ǥ�� �Ӽ��гο� �Է�
								    m.bu[m.cnt_Button].x = me.getX();	//���� ���콺�� X��ǥ�� ����
								    m.bu[m.cnt_Button].y = me.getY();	//���� ���콺�� Y��ǥ�� ����
								    m.bu[m.cnt_Button].value = "Button"+(m.cnt_Button+1);	//value�� �⺻������ Button##�� ���� ��ư ��ȣ ����
								    m.bu[m.cnt_Button].type = "Button";	//type�� Button���� ����
								    m.bu[m.cnt_Button].name = "Button"+(m.cnt_Button+1);	//name�� �⺻������ Button##�� ���� ��ư ��ȣ ����
									m.Combo.setSelectedItem(m.Combo_Item[1]);	//������Ʈ Ÿ���� �޺��ڽ��� JButton���� ����
									m.bu[m.cnt_Button].JButton_Component = new JButton("Button"+(m.cnt_Button+1));	//bu��ü�� JButton_Component�� ����
									m.Editor_Panel.add(m.bu[m.cnt_Button].JButton_Component);	//Editor_Panel�� ������ ������Ʈ �߰�
								}
							}
							public void mouseReleased(MouseEvent e) {	//���콺�� ���� ��
								if(tmp==2)
								{
									if(m.bu[m.cnt_Button].x > m.bu[m.cnt_Button].x_drag)	//x������ ���������� �׷����� ���
									{	//x�� x_drag�� ���� �ڹٲ�
										int tmp2 = m.bu[m.cnt_Button].x;
										m.bu[m.cnt_Button].x = m.bu[m.cnt_Button].x_drag;
										m.bu[m.cnt_Button].x_drag = tmp2;
									}
									if(m.bu[m.cnt_Button].y > m.bu[m.cnt_Button].y_drag)	//y������ ���������� �׷����� ���
									{	//y�� y_drag�� ���� �ڹٲ�
										int tmp2 = m.bu[m.cnt_Button].y;
										m.bu[m.cnt_Button].y = m.bu[m.cnt_Button].y_drag;
										m.bu[m.cnt_Button].y_drag = tmp2;
									}
									bu_CreateBox(m.bu[m.cnt_Button]);	//Box��ġ ����
									Make_Non_Selected();	//��� ��ü�� isSelected�� false�� ����
									m.bu[m.cnt_Button].isSelected = true;	//���� ��ü�� isSelected�� true�� ����
									for(int i=0; i<8; ++i)	//Box 8���� ���콺������ �߰�
									{
										listener_box_bu[m.cnt_Button][i] = new BoxListener_bu(m.cnt_Button, i);
										m.bu[m.cnt_Button].Box[i].addMouseListener(listener_box_bu[m.cnt_Button][i]);
										m.bu[m.cnt_Button].Box[i].addMouseMotionListener(listener_box_bu[m.cnt_Button][i]);
									}
									tmp = 4;
									++m.cnt_Button;	//Button�� 1����
									Show_Box();	//���� ���õ� ������Ʈ�� Box�� ������
									m.Editor_Panel.repaint();	//������Ʈ�� ����� ��� ���� �׸�
								}
							}
						});
						m.Editor_Panel.addMouseMotionListener(new MouseMotionAdapter(){	//Editor_Panel���� ���콺�̺�Ʈ
							public void mouseDragged(MouseEvent me){	//���콺�� �巡�׵Ǿ��� ��
								if(tmp==2)
								{
									int reverse_x=0;	//x������ ���������� �׷����� ��� 1
									int reverse_y=0;	//y������ ���������� �׷����� ��� 1
									m.bu[m.cnt_Button].x_drag = me.getX();	//x_drag�� ���� �巡�׵Ǵ� ���콺�� X��ǥ ����
									m.bu[m.cnt_Button].y_drag = me.getY();	//y_drag�� ���� �巡�׵Ǵ� ���콺�� Y��ǥ ����
									if(m.bu[m.cnt_Button].x > m.bu[m.cnt_Button].x_drag)	//x������ ���������� �׷����� ���
									{
										m.bu[m.cnt_Button].width = -(m.bu[m.cnt_Button].x_drag - m.bu[m.cnt_Button].x);	//width = -(x_drag - x)										
										reverse_x=1;
									}
									else	//x������ ���������� �׷����� ���
									{
										m.bu[m.cnt_Button].width = m.bu[m.cnt_Button].x_drag - m.bu[m.cnt_Button].x;	//width = x_drag - x										
									}
									if(m.bu[m.cnt_Button].y > m.bu[m.cnt_Button].y_drag)	//y������ ���������� �׷����� ���
									{
										m.bu[m.cnt_Button].height = -(m.bu[m.cnt_Button].y_drag - m.bu[m.cnt_Button].y);	//height = -(y_drag - y)
										reverse_y=1;
									}
									else	//y������ ���������� �׷����� ���
									{
										m.bu[m.cnt_Button].height = m.bu[m.cnt_Button].y_drag - m.bu[m.cnt_Button].y;	//height = y_drag - y
									}
									m.Text_Width.setText(Integer.toString(m.bu[m.cnt_Button].width));	//�Ӽ� �гο� width�� ǥ��
									m.Text_Height.setText(Integer.toString(m.bu[m.cnt_Button].height));	//�Ӽ� �гο� height�� ǥ��
									
									if(reverse_x==0 && reverse_y==0)	//x, y���� ��� ���������� �׷����� ���
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x, m.bu[m.cnt_Button].y, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x, y, width, height)
									}
									else if(reverse_x==1 && reverse_y==0)	//x������, y������
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x_drag, m.bu[m.cnt_Button].y, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x_drag, y, width, height)
									}
									else if(reverse_x==0 && reverse_y==1)	//x������, y������
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x, m.bu[m.cnt_Button].y_drag, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x, y_drag, width, height)
									}
									else if(reverse_x==1 && reverse_y==1)	//x������, y������
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x_drag, m.bu[m.cnt_Button].y_drag, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x_drag, y_drag, width, height)
									}
								}
							}
						});
					}
					else if(Model.Radio_TextField.isSelected()==true)	//TextField�� ���õǾ��� ���
					{
						tmp = 3;	//TextField�׸���
						m.tf[m.cnt_TextField] = new TextField();	//tf��ü �߰�
						m.Editor_Panel.addMouseListener(new MouseAdapter() {	//Editor_Panel������ ���콺�̺�Ʈ
							public void mousePressed(MouseEvent me) {	//���콺�� ������ ��
								if(tmp==3)
								{
									m.Text_X.setText(String.valueOf(me.getX()));	//���� ���콺�� X��ǥ�� �Ӽ� �гο� ǥ��
								    m.Text_Y.setText(String.valueOf(me.getY()));	//���� ���콺�� Y��ǥ�� �Ӽ� �гο� ǥ��
								    m.tf[m.cnt_TextField].x = me.getX();	//���� ���콺�� X��ǥ�� ����
								    m.tf[m.cnt_TextField].y = me.getY();	//���� ���콺�� Y��ǥ�� ����
								    m.tf[m.cnt_TextField].value = "TextField"+(m.cnt_TextField+1);	//value�� �⺻������ TextField##ó�� TextField�� ��ȣ ���� 
									m.tf[m.cnt_TextField].type = "TextField";	//type�� TextField�� ����
								    m.tf[m.cnt_TextField].name = "TextField"+(m.cnt_TextField+1);	//name�� �⺻������ TextField##ó�� TextField�� ��ȣ ����
									m.Combo.setSelectedItem(m.Combo_Item[2]);	//������Ʈ Ÿ���� �޺��ڽ��� JTextField�� ��ȯ
									m.tf[m.cnt_TextField].JTextField_Component = new JTextField("TextField"+(m.cnt_TextField+1));	//tf�� JTextField_Component�� ���� ����
									m.Editor_Panel.add(m.tf[m.cnt_TextField].JTextField_Component);	//Editor_Panel�� ���� ���� ������Ʈ �߰�
								}
							}
							public void mouseReleased(MouseEvent e) {	//���콺�� ���� ��
								if(tmp==3)
								{
									if(m.tf[m.cnt_TextField].x > m.tf[m.cnt_TextField].x_drag)	//x������ ���������� �׷����� ��
									{	//x�� x_drag�� ���� �ڹٲ�
										int tmp2 = m.tf[m.cnt_TextField].x;
										m.tf[m.cnt_TextField].x = m.tf[m.cnt_TextField].x_drag;
										m.tf[m.cnt_TextField].x_drag = tmp2;
									}
									if(m.tf[m.cnt_TextField].y > m.tf[m.cnt_TextField].y_drag)	//y������ ���������� �׷����� ��
									{	//y�� y_drag�� ���� �ڹٲ�
										int tmp2 = m.tf[m.cnt_TextField].y;
										m.tf[m.cnt_TextField].y = m.tf[m.cnt_TextField].y_drag;
										m.tf[m.cnt_TextField].y_drag = tmp2;
									}
									tf_CreateBox(m.tf[m.cnt_TextField]);	//Box��ġ ����
									Make_Non_Selected();	//��� ��ü�� isSelected�� false�� ����
									m.tf[m.cnt_TextField].isSelected = true;	//���� ��ü�� isSelected�� true�� ����
									for(int i=0; i<8; ++i)	//�ڽ� 8���� ���콺������ �߰�
									{
										listener_box_tf[m.cnt_TextField][i] = new BoxListener_tf(m.cnt_TextField, i);
										m.tf[m.cnt_TextField].Box[i].addMouseListener(listener_box_tf[m.cnt_TextField][i]);
										m.tf[m.cnt_TextField].Box[i].addMouseMotionListener(listener_box_tf[m.cnt_TextField][i]);
									}
									tmp = 4;	//�׸��� �Ϸ�
									++m.cnt_TextField;	//TextField�� �� 1����
									Show_Box();	//���� ���õ� ������Ʈ�� Box�� ǥ��
									m.Editor_Panel.repaint();	//���� �׷���
								}
							}
						});
						m.Editor_Panel.addMouseMotionListener(new MouseMotionAdapter(){	//Editor_Panel���� ���콺�̺�Ʈ
							public void mouseDragged(MouseEvent me){	//���콺�� �巡�׵Ǿ��� ��
								if(tmp==3)
								{
									int reverse_x=0;	//x������ ���������� �׷����� �� 1
									int reverse_y=0;	//y������ ���������� �׷����� �� 1
									m.tf[m.cnt_TextField].x_drag = me.getX();	//x_drag�� �巡�׵Ǵµ����� ���콺 X��ǥ ����
									m.tf[m.cnt_TextField].y_drag = me.getY();	//y_drag�� �巡�׵Ǵµ����� ���콺 Y��ǥ ����
									if(m.tf[m.cnt_TextField].x > m.tf[m.cnt_TextField].x_drag)	//x������ ���������� �׷����� ���
									{
										m.tf[m.cnt_TextField].width = -(m.tf[m.cnt_TextField].x_drag - m.tf[m.cnt_TextField].x);	//width = -(x_drag - x)										
										reverse_x=1;
									}
									else	//x������ ���������� �׷����� ���
									{
										m.tf[m.cnt_TextField].width = m.tf[m.cnt_TextField].x_drag - m.tf[m.cnt_TextField].x;	//width = x_drag - x										
									}
									if(m.tf[m.cnt_TextField].y > m.tf[m.cnt_TextField].y_drag)	//y������ ���������� �׷����� ���
									{
										m.tf[m.cnt_TextField].height = -(m.tf[m.cnt_TextField].y_drag - m.tf[m.cnt_TextField].y);	//height = -(y_drag - y)
										reverse_y=1;
									}
									else	//y������ ���������� �׷����� ���
									{
										m.tf[m.cnt_TextField].height = m.tf[m.cnt_TextField].y_drag - m.tf[m.cnt_TextField].y;	//height = y_drag - y
									}
									m.Text_Width.setText(Integer.toString(m.tf[m.cnt_TextField].width));	//�Ӽ� �гο� width �� ǥ��
									m.Text_Height.setText(Integer.toString(m.tf[m.cnt_TextField].height));	//�Ӽ� �гο� height �� ǥ��
									if(reverse_x==0 && reverse_y==0)	//x, y ������
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x, m.tf[m.cnt_TextField].y, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x, y, width, height)
									}
									else if(reverse_x==1 && reverse_y==0)	//x������, y������
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x_drag, m.tf[m.cnt_TextField].y, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x_drag, y, width, height)
									}
									else if(reverse_x==0 && reverse_y==1)	//x������, y������
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x, m.tf[m.cnt_TextField].y_drag, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x, y_drag, width, height)
									}
									else if(reverse_x==1 && reverse_y==1)	//x, y ������
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x_drag, m.tf[m.cnt_TextField].y_drag, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x_drag, y_drag, width, height)
									}
								}
							}
						});
					}
				}
			}
			else if(e.getActionCommand()=="�����")	//����� ��ư Ŭ�� ��
			{
				for(int i=0; i<m.cnt_Label; ++i)
				{
					if(m.la[i].isSelected == true)	//Label�� ���õǾ��� ���
					{
						Remove_la(m.la[i]);	//����
						repaint();	//���� �׸�
						for(int j=i; j<m.cnt_Label-1; ++j)	//��ĭ�� �����
						{
							m.la[j] = m.la[j+1];
						}
						--m.cnt_Label;	//Label�� -1
						break;
					}
				}
				for(int i=0; i<m.cnt_Button; ++i)
				{
					if(m.bu[i].isSelected == true)	//Button�� ���õǾ��� ���
					{
						Remove_bu(m.bu[i]);	//����
						repaint();	//���� �׸�
						for(int j=i; j<m.cnt_Button-1; ++j)	//��ĭ�� �����
						{
							m.bu[j] = m.bu[j+1];
						}
						--m.cnt_Button;	//Button�� -1
						break;
					}
				}
				for(int i=0; i<m.cnt_TextField; ++i)
				{
					if(m.tf[i].isSelected == true)	//TextField�� ���õǾ��� ���
					{
						Remove_tf(m.tf[i]);	//����
						repaint();	//���� �׸�	
						for(int j=i; j<m.cnt_TextField-1; ++j)	//��ĭ�� �����
						{
							m.tf[j] = m.tf[j+1];
						}
						--m.cnt_TextField;	//TextField�� -1
						break;
					}
				}
				m.Text_X.setText("");	//�Ӽ� �г� �� �ʱ�ȭ
				m.Text_Y.setText("");
				m.Text_Height.setText("");
				m.Text_Width.setText("");
				m.Text_Value.setText("");
				m.Text_Name.setText("");
				m.Combo.setSelectedItem("JPanel");
				
			}
			else if(e.getActionCommand()=="����/�̵�")	//����/�̵� ��ư Ŭ�� ��
			{
				RemoveMouseListener();	//������Ʈ�� ���콺������ ����
				m.Editor_Panel.addMouseListener(listener_p);	//Editor_Panel ���콺������ �߰�
				Select();	//������Ʈ ���� �Լ�
				Show_Box();	//���õ� ������Ʈ�� Boxǥ��
				m.Editor_Panel.repaint();	//���� �׸�
			}
		}
	}
	void Make_Non_Selected()	//��� ��ü�� isSelected�� false�� ����� �Լ�
	{
		for(int i=0; i<m.cnt_Label; ++i)
		{
			m.la[i].isSelected = false;
		}
		for(int i=0; i<m.cnt_Button; ++i)
		{
			m.bu[i].isSelected = false;
		}
		for(int i=0; i<m.cnt_TextField; ++i)
		{
			m.tf[i].isSelected = false;
		}
	}
	void la_CreateBox(Label la)	//Label�� Box��ǥ ���� �Լ�
	{
		la.Box[0].setBounds(la.x-5, la.y-5, 10, 10);
		la.Box[1].setBounds((la.x + la.x_drag)/2 - 5, la.y - 5, 10, 10);
		la.Box[2].setBounds(la.x_drag - 5, la.y - 5, 10, 10);
		la.Box[3].setBounds(la.x - 5, (la.y + la.y_drag)/2 - 5, 10, 10);
		la.Box[4].setBounds(la.x_drag - 5, (la.y + la.y_drag)/2 - 5, 10, 10);
		la.Box[5].setBounds(la.x - 5, la.y_drag - 5, 10, 10);
		la.Box[6].setBounds((la.x + la.x_drag)/2 - 5, la.y_drag - 5, 10, 10);
		la.Box[7].setBounds(la.x_drag - 5, la.y_drag - 5, 10, 10);	
	}
	void bu_CreateBox(Button bu)	//Button�� Box��ǥ ���� �Լ�
	{
		bu.Box[0].setBounds(bu.x - 5, bu.y - 5, 10, 10);
		bu.Box[1].setBounds((bu.x + bu.x_drag)/2 - 5, bu.y - 5, 10, 10);
		bu.Box[2].setBounds(bu.x_drag - 5, bu.y - 5, 10, 10);
		bu.Box[3].setBounds(bu.x - 5, (bu.y + bu.y_drag)/2 - 5, 10, 10);
		bu.Box[4].setBounds(bu.x_drag - 5, (bu.y + bu.y_drag)/2 - 5, 10, 10);
		bu.Box[5].setBounds(bu.x - 5, bu.y_drag - 5, 10, 10);
		bu.Box[6].setBounds((bu.x + bu.x_drag)/2 - 5, bu.y_drag - 5, 10, 10);
		bu.Box[7].setBounds(bu.x_drag - 5, bu.y_drag - 5, 10, 10);
	}
	void tf_CreateBox(TextField tf)	//TextField�� Box��ǥ ���� �Լ�
	{
		tf.Box[0].setBounds(tf.x - 5, tf.y - 5, 10, 10);
		tf.Box[1].setBounds((tf.x + tf.x_drag)/2 - 5, tf.y - 5, 10, 10);
		tf.Box[2].setBounds(tf.x_drag - 5, tf.y - 5, 10, 10);
		tf.Box[3].setBounds(tf.x - 5, (tf.y + tf.y_drag)/2 - 5, 10, 10);
		tf.Box[4].setBounds(tf.x_drag - 5, (tf.y + tf.y_drag)/2 - 5, 10, 10);
		tf.Box[5].setBounds(tf.x - 5, tf.y_drag - 5, 10, 10);
		tf.Box[6].setBounds((tf.x + tf.x_drag)/2 - 5, tf.y_drag - 5, 10, 10);
		tf.Box[7].setBounds(tf.x_drag - 5, tf.y_drag - 5, 10, 10);

	}
	void Show_Box()	//���� ���õǾ��ִ� ������Ʈ�� Box�� ǥ���� �� �Ӽ��гο� �� ǥ��
	{
		for(int i=0; i<m.cnt_Label; ++i)
		{
			if(m.la[i].isSelected == false)
			{
				for(int j=0; j<8; ++j)	//���õ��� ���� Label�� Box�� Editor_Panel���� ����
				{
					m.Editor_Panel.remove(m.la[i].Box[j]);
				}
			}
			else
			{
				for(int j=0; j<8; ++j)	//���õ� Label Box�� Editor_Panel�� ǥ��
				{
					m.Editor_Panel.add(m.la[i].Box[j]);
				}
				m.Text_X.setText(Integer.toString(m.la[i].x));	//�Ӽ� �гο� �� ǥ��
				m.Text_Y.setText(Integer.toString(m.la[i].y));
				m.Text_Width.setText(Integer.toString(m.la[i].width));
				m.Text_Height.setText(Integer.toString(m.la[i].height));
				m.Text_Value.setText(m.la[i].value);
				m.Combo.setSelectedItem("JLabel");
				m.Text_Name.setText(m.la[i].name);
			}
		}
		for(int i=0; i<m.cnt_Button; ++i)
		{
			if(m.bu[i].isSelected == false)
			{
				for(int j=0; j<8; ++j)	//���õ��� ���� Button�� Box�� Editor_Panel���� ����
				{
					m.Editor_Panel.remove(m.bu[i].Box[j]);
				}
			}
			else
			{
				for(int j=0; j<8; ++j)	//���õ� Button�� Box�� Editor_Panel�� ǥ��
				{
					m.Editor_Panel.add(m.bu[i].Box[j]);
				}
				m.Text_X.setText(Integer.toString(m.bu[i].x));	//�Ӽ� �гο� �� ǥ��
				m.Text_Y.setText(Integer.toString(m.bu[i].y));
				m.Text_Width.setText(Integer.toString(m.bu[i].width));
				m.Text_Height.setText(Integer.toString(m.bu[i].height));
				m.Text_Value.setText(m.bu[i].value);
				m.Combo.setSelectedItem("JButton");
				m.Text_Name.setText(m.bu[i].name);
			}
		}
		for(int i=0; i<m.cnt_TextField; ++i)
		{
			if(m.tf[i].isSelected == false)	//���õ��� ���� TextField�� Box�� Editor_Panel���� ����
			{
				for(int j=0; j<8; ++j)
				{
					m.Editor_Panel.remove(m.tf[i].Box[j]);
				}
			}
			else
			{
				for(int j=0; j<8; ++j)	//���õ� TextField�� Box�� Editor_Panel�� ǥ��
				{
					m.Editor_Panel.add(m.tf[i].Box[j]);
				}
				m.Text_X.setText(Integer.toString(m.tf[i].x));	//�Ӽ� �гο� �� ǥ��
				m.Text_Y.setText(Integer.toString(m.tf[i].y));
				m.Text_Width.setText(Integer.toString(m.tf[i].width));
				m.Text_Height.setText(Integer.toString(m.tf[i].height));
				m.Text_Value.setText(m.tf[i].value);
				m.Combo.setSelectedItem("JTextField");
				m.Text_Name.setText(m.tf[i].name);
			}
		}
	}
	void Select()	//������Ʈ�� ���콺�����ʸ� �߰��� ��ġ �̵�, ũ�� ������ �� �� �ְ� ��
	{
		for(int i=0; i<m.cnt_Label; ++i)
		{
			listener_la[i] = new MouseEvent_la(i);
			m.la[i].JLabel_Component.addMouseListener(listener_la[i]);
			m.la[i].JLabel_Component.addMouseMotionListener(listener_la[i]);
			
		}
		for(int i=0; i<m.cnt_Button; ++i)
		{
			listener_bu[i] = new MouseEvent_bu(i);
			m.bu[i].JButton_Component.addMouseListener(listener_bu[i]);
			m.bu[i].JButton_Component.addMouseMotionListener(listener_bu[i]);
		}
		for(int i=0; i<m.cnt_TextField; ++i)
		{
			listener_tf[i] = new MouseEvent_tf(i);
			m.tf[i].JTextField_Component.addMouseListener(listener_tf[i]);
			m.tf[i].JTextField_Component.addMouseMotionListener(listener_tf[i]);
		}
	}
	MouseEvent_la listener_la[] = new MouseEvent_la[100];
	MouseEvent_bu listener_bu[] = new MouseEvent_bu[100];
	MouseEvent_tf listener_tf[] = new MouseEvent_tf[100];
	
	void RemoveMouseListener()	//������Ʈ�� ���콺�����ʸ� �����ϴ� �Լ�
	{
		for(int i=0; i<m.cnt_Label; ++i)
		{
			m.la[i].JLabel_Component.removeMouseListener(listener_la[i]);
			m.la[i].JLabel_Component.removeMouseMotionListener(listener_la[i]);
		}
		
		for(int i=0; i<m.cnt_Button; ++i)
		{
			m.bu[i].JButton_Component.removeMouseListener(listener_bu[i]);
			m.bu[i].JButton_Component.removeMouseMotionListener(listener_bu[i]);
		}
		for(int i=0; i<m.cnt_TextField; ++i)
		{
			m.tf[i].JTextField_Component.removeMouseListener(listener_tf[i]);
			m.tf[i].JTextField_Component.removeMouseMotionListener(listener_tf[i]);
		}		
		m.Editor_Panel.removeMouseListener(listener_p);
	}
	class MouseEvent_la implements MouseListener, MouseMotionListener	//Label�� ���콺������
	{
		int mouseX, mouseY;
		int tmp;
		MouseEvent_la(int num)	//num : �� ��ȣ
		{
			tmp = num;
		}
		public void mousePressed(MouseEvent e)	//������ ��
		{
			mouseX = e.getX();	//mouseX�� ������ �� X��ǥ ����
			mouseY = e.getY();	//mouseY�� ������ �� Y��ǥ ����
			Make_Non_Selected();	//���� ����
			m.la[tmp].isSelected = true;	//���� ��ü�� isSelected�� true�� �ٲ�
			Show_Box();	//Boxǥ��, �Ӽ��г� ǥ��
			m.Editor_Panel.repaint();	//���� �׸�
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//���콺 �巡���� ��
		{
			m.la[tmp].x += e.getX() - mouseX;	//���� ���콺 ��ǥ���� mouseX, mouseY��ǥ�� �� ���� �̿��� ��ǥ ����
			m.la[tmp].y += e.getY() - mouseY;
			m.la[tmp].x_drag += e.getX() - mouseX;
			m.la[tmp].y_drag += e.getY() - mouseY;
			m.Text_X.setText(Integer.toString(m.la[tmp].x));	//�Ӽ� �г� ����
			m.Text_Y.setText(Integer.toString(m.la[tmp].y));
			la_CreateBox(m.la[tmp]);	//Box��ǥ ����
			m.la[tmp].JLabel_Component.setBounds(m.la[tmp].x, m.la[tmp].y, m.la[tmp].width, m.la[tmp].height);	//������Ʈ ��ġ �̵�
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	class MouseEvent_bu implements MouseListener, MouseMotionListener	//Button�� ���콺������
	{
		int mouseX, mouseY;
		int tmp;
		MouseEvent_bu(int num)	//num : ��ư ��ȣ
		{
			tmp = num;
		}
		public void mousePressed(MouseEvent e)	//������ ��
		{
			mouseX = e.getX();	//mouseX�� ������ �� X��ǥ ����
			mouseY = e.getY();	//mouseY�� ������ �� Y��ǥ ����
			Make_Non_Selected();	//���� ����
			m.bu[tmp].isSelected = true;	//���� ��ü�� ����
			Show_Box();	//Boxǥ�� �� �Ӽ��г� ǥ��
			m.Editor_Panel.repaint();	//���� �׸�
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//���콺 �巡���� ��
		{
			m.bu[tmp].x += e.getX() - mouseX;	//���� ���콺 ��ǥ���� mouseX, mouseY��ǥ�� �� ���� �̿��� ��ǥ ����
			m.bu[tmp].y += e.getY() - mouseY;
			m.bu[tmp].x_drag += e.getX() - mouseX;
			m.bu[tmp].y_drag += e.getY() - mouseY;
			m.Text_X.setText(Integer.toString(m.bu[tmp].x));	//�Ӽ� �г� ����
			m.Text_Y.setText(Integer.toString(m.bu[tmp].y));
			bu_CreateBox(m.bu[tmp]);	//Box��ǥ ����
			m.bu[tmp].JButton_Component.setBounds(m.bu[tmp].x, m.bu[tmp].y, m.bu[tmp].width, m.bu[tmp].height);	//������Ʈ ��ġ �̵�
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	class MouseEvent_tf implements MouseListener, MouseMotionListener	//TextField�� ���콺������
	{
		int mouseX, mouseY;
		int tmp;
		MouseEvent_tf(int num)
		{
			tmp = num;
		}
		public void mousePressed(MouseEvent e)
		{
			mouseX = e.getX();	//mouseX�� ������ �� X��ǥ ����
			mouseY = e.getY();	//mouseY�� ������ �� Y��ǥ ����
			Make_Non_Selected();	//���� ����
			m.tf[tmp].isSelected = true;	//���� ��ü ����
			Show_Box();	//Box��ǥ ���� �� �Ӽ� �г� ǥ��
			m.Editor_Panel.repaint();	//���� �׸�
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)
		{
			m.tf[tmp].x += e.getX() - mouseX;	//���� ���콺 ��ǥ���� mouseX, mouseY��ǥ�� �� ���� �̿��� ��ǥ ����
			m.tf[tmp].y += e.getY() - mouseY;
			m.tf[tmp].x_drag += e.getX() - mouseX;
			m.tf[tmp].y_drag += e.getY() - mouseY;
			m.Text_X.setText(Integer.toString(m.tf[tmp].x));	//�Ӽ� �г� ����
			m.Text_Y.setText(Integer.toString(m.tf[tmp].y));
			tf_CreateBox(m.tf[tmp]);	//Box��ǥ ����
			m.tf[tmp].JTextField_Component.setBounds(m.tf[tmp].x, m.tf[tmp].y, m.tf[tmp].width, m.tf[tmp].height);	//������Ʈ ��ġ �̵�
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	BoxListener_la listener_box_la[][] = new BoxListener_la[100][8];
	BoxListener_bu listener_box_bu[][] = new BoxListener_bu[100][8];
	BoxListener_tf listener_box_tf[][] = new BoxListener_tf[100][8];
	class BoxListener_la implements MouseListener, MouseMotionListener	//Label�� Box�� ���콺������
	{
		int tmp1;	//tmp1 : ���° Label���� ����
		int tmp2;	//tmp2 : ���° Box���� ����(0 : ���� ��, 1 : ��, 2 : ������ ��, 3 : ����, 4 : ������, 5 : ���� �Ʒ�, 6 : �Ʒ�, 7 : ������ �Ʒ�)
		int mouseX, mouseY;
		BoxListener_la(int num1, int num2)
		{
			tmp1 = num1;
			tmp2 = num2;
		}
		public void mousePressed(MouseEvent e)
		{
			mouseX = e.getX();	//mouseX�� ���� ���콺 X��ǥ ����
			mouseY = e.getY();	//mouseY�� ���� ���콺 Y��ǥ ����
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)
		{
			if(tmp2==0)	//���� �� Box�� ��
			{
				m.la[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.la[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//�ʺ� ����
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//���� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==1)	//���� Box�� ��
			{
				m.la[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//���� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==2)	//������ �� Box�� ��
			{
				m.la[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.la[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//���� ����
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//���� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==3)	//���� Box�� ��
			{
				m.la[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//�ʺ� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==4)	//������ Box�� ��
			{
				m.la[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//�ʺ� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==5)	//���� �Ʒ� Box�� ��
			{
				m.la[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.la[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//�ʺ� ����
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//���� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==6)	//�Ʒ��� Box�� ��
			{
				m.la[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//���� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==7)	//������ �� Box�� ��
			{
				m.la[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.la[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//�ʺ� ����
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//���� ����
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				la_CreateBox(m.la[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�	
			}
			Show_Box();
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e)
		{	//���콺�� Box���� ���� ��� Ŀ�� ��� ����
			if(tmp2 == 0 || tmp2 == 7)
				setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			else if(tmp2 == 1 || tmp2 == 6)
				setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			else if(tmp2 == 2 || tmp2 == 5)
				setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			else if(tmp2 == 3 || tmp2 == 4)
				setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
		}
		public void mouseExited(MouseEvent e)
		{	//���콺�� Box������ ������ ��� Ŀ�� ��� ������� ����
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mouseMoved(MouseEvent e){}
	}
	class BoxListener_bu implements MouseListener, MouseMotionListener	//Button�� Box�� ���콺������
	{
		int tmp1;	//tmp1 : ��ư ��ȣ
		int tmp2;	//tmp2 : Box��ȣ(0 : ���� ��, 1 : ��, 2 : ������ ��, 3 : ����, 4 : ������, 5 : ���� �Ʒ�, 6 : �Ʒ�, 7 : ������ �Ʒ�)
		int mouseX, mouseY;
		BoxListener_bu(int num1, int num2)
		{
			tmp1 = num1;
			tmp2 = num2;
		}
		public void mousePressed(MouseEvent e)	//������ ��
		{
			mouseX = e.getX();	//X��ǥ ����
			mouseY = e.getY();	//Y��ǥ ����
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//�巡���� ��
		{
			if(tmp2==0)	//���� �� Box�� ��
			{

				m.bu[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.bu[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//�ʺ� ����
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//���� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==1)	//���� Box�� ��
			{
				m.bu[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//���� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==2)	//������ �� Box�� ��
			{
				m.bu[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.bu[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//�ʺ� ����
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//���� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
				
			}
			else if(tmp2==3)	//���� Box�� ��
			{
				m.bu[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//�ʺ� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==4)	//������ Box�� ��
			{
				m.bu[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//�ʺ� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==5)	//���� �Ʒ� Box�� ��
			{
				m.bu[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.bu[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//�ʺ� ����
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//���� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==6)	//�Ʒ��� Box�� ��
			{
				m.bu[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//���� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			else if(tmp2==7)	//������ �Ʒ� Box�� ��
			{
				m.bu[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.bu[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//�ʺ� ����
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//���� ����
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				bu_CreateBox(m.bu[tmp1]);	//Box�� ������Ʈ ��ġ�� ���缭 ���� �׸�
			}
			Show_Box();
			
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e)
		{	//���콺�� Box�ȿ� ���� �� Ŀ�� ����
			if(tmp2 == 0 || tmp2 == 7)
				setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			else if(tmp2 == 1 || tmp2 == 6)
				setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			else if(tmp2 == 2 || tmp2 == 5)
				setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			else if(tmp2 == 3 || tmp2 == 4)
				setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
		}
		public void mouseExited(MouseEvent e)
		{	//���콺�� Box������ ������ �� �⺻Ŀ���� ����
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mouseMoved(MouseEvent e){}
	}
	class BoxListener_tf implements MouseListener, MouseMotionListener	//TextField�� Box�� ���콺������
	{
		int tmp1;	//tmp1 : TextField�� ��ȣ
		int tmp2;	//tmp2 : Box�� ��ȣ(0 : ���� ��, 1 : ��, 2 : ������ ��, 3 : ����, 4 : ������, 5 : ���� �Ʒ�, 6 : �Ʒ�, 7 : ������ �Ʒ�)
		int mouseX, mouseY;
		BoxListener_tf(int num1, int num2)
		{
			tmp1 = num1;
			tmp2 = num2;
		}
		public void mousePressed(MouseEvent e)	//���콺 ������ ��
		{
			mouseX = e.getX();	//���� X��ǥ ����
			mouseY = e.getY();	//���� Y��ǥ ����
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//���콺 �巡���� ��
		{
			if(tmp2==0)	//���� �� Box
			{
				m.tf[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.tf[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//�ʺ� ����
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//���� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==1)	//���� Box
			{
				m.tf[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//���� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==2)	//������ �� Box
			{
				m.tf[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.tf[tmp1].y += e.getY() - mouseY;	//y�� ����
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//�ʺ� ����
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//���� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==3)	//���� Box
			{
				m.tf[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//�ʺ� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==4)	//������ Box
			{
				m.tf[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//�ʺ� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==5)	//���� �Ʒ� Box
			{
				m.tf[tmp1].x += e.getX() - mouseX;	//x�� ����
				m.tf[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//�ʺ� ����
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//���� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==6)	//�Ʒ��� Box
			{
				m.tf[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//���� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			else if(tmp2==7)	//������ �Ʒ� Box
			{
				m.tf[tmp1].x_drag += e.getX() - mouseX;	//x_drag�� ����
				m.tf[tmp1].y_drag += e.getY() - mouseY;	//y_drag�� ����
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//�ʺ� ����
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//���� ����
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//����� ���� �̿��� ������Ʈ�� ���� �׸�
				tf_CreateBox(m.tf[tmp1]);	//Box�� ������Ʈ ��ġ�� ���� ���� �׸�
			}
			Show_Box();
			
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e)
		{	//���콺�� Box�ȿ� ������ Ŀ�� ��� ����
			if(tmp2 == 0 || tmp2 == 7)
				setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			else if(tmp2 == 1 || tmp2 == 6)
				setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			else if(tmp2 == 2 || tmp2 == 5)
				setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			else if(tmp2 == 3 || tmp2 == 4)
				setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
		}
		public void mouseExited(MouseEvent e)
		{	//���콺�� Box������ ������ �� �⺻Ŀ���� ����
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mouseMoved(MouseEvent e){}
	}
	MouseListener_Panel listener_p = new MouseListener_Panel();
	class MouseListener_Panel implements MouseListener	//Editor_Panel�� ���콺������
	{
		public void mousePressed(MouseEvent e)	//������ ��
		{	//��� �Ӽ� �г��� �ʱ�ȭ �� �� ������ƮŸ���� �޺��ڽ��� JPanel�� �����
			m.Text_X.setText(null);
			m.Text_Y.setText(null);
			m.Text_Width.setText(null);
			m.Text_Height.setText(null);
			m.Text_Name.setText(null);
			m.Text_Value.setText(null);
			m.Combo.setSelectedItem("JPanel");
			Make_Non_Selected();	//�ƹ��͵� ���õ��� �ʰ� ��
			Show_Box();	//���õ� Boxǥ��(�ƹ��͵� ������ �ȵ������Ƿ� Box�� ǥ�õ��� ����)
			repaint();	//���� �׸�
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		
	}
	void Remove_la(Label la)	//Label�� ����� �Լ�
	{
		m.Editor_Panel.remove(la.JLabel_Component);	//Editor_Panel���� ������Ʈ�� ������
		for(int i=0; i<8; ++i)	//Box�� ����
			m.Editor_Panel.remove(la.Box[i]);
		RemoveMouseListener();	//������Ʈ�� ���콺�����ʸ� ������
	}
	void Remove_bu(Button bu)	//Button�� ����� �Լ�
	{
		m.Editor_Panel.remove(bu.JButton_Component);	//Editor_Panel���� ������Ʈ�� ������
		for(int i=0; i<8; ++i)	//Box�� ����
			m.Editor_Panel.remove(bu.Box[i]);
		RemoveMouseListener();	//������Ʈ�� ���콺�����ʸ� ������
	}
	void Remove_tf(TextField tf)	//TextField�� ����� �Լ�
	{
		m.Editor_Panel.remove(tf.JTextField_Component);	//Editor_Panel���� ������Ʈ�� ������
		for(int i=0; i<8; ++i)	//Box�� ����
			m.Editor_Panel.remove(tf.Box[i]);
		RemoveMouseListener();	//������Ʈ�� ���콺�����ʸ� ������
	}
	void New_File()	//���� ����� �Լ�
	{
		RemoveMouseListener();	//��� ������Ʈ�� ���콺�����ʸ� ����
		Make_Non_Selected();	//�ƹ��͵� ���õ��� ���� ���·� ����
		for(int i=0 ;i<m.cnt_Label; ++i)	//la��ü�� �ʱ�ȭ��
		{
			m.la[i].x=0;
			m.la[i].y=0;
			m.la[i].x_drag=0;
			m.la[i].y_drag=0;
			m.la[i].width=0;
			m.la[i].height=0;
			m.la[i].value="";
			m.la[i].name = "";
			for(int j=0; j<8; ++j)
			{
				m.Editor_Panel.remove(m.la[i].Box[j]);
			}
			m.Editor_Panel.remove(m.la[i].JLabel_Component);	//Editor_Panel���� ������Ʈ�� Box����
		}
		for(int i=0 ;i<m.cnt_Button; ++i)	//bu��ü�� �ʱ�ȭ��
		{
			m.bu[i].x=0;
			m.bu[i].y=0;
			m.bu[i].x_drag=0;
			m.bu[i].y_drag=0;
			m.bu[i].width=0;
			m.bu[i].height=0;
			m.bu[i].value="";
			m.bu[i].name = "";
			for(int j=0; j<8; ++j)
			{
				m.Editor_Panel.remove(m.bu[i].Box[j]);
			}
			m.Editor_Panel.remove(m.bu[i].JButton_Component);	//Editor_Panel���� ������Ʈ�� Box����
		}
		for(int i=0 ;i<m.cnt_TextField; ++i)	//tf��ü�� �ʱ�ȭ��
		{
			m.tf[i].x=0;
			m.tf[i].y=0;
			m.tf[i].x_drag=0;
			m.tf[i].y_drag=0;
			m.tf[i].width=0;
			m.tf[i].height=0;
			m.tf[i].value="";
			m.tf[i].name = "";
			for(int j=0; j<8; ++j)
			{
				m.Editor_Panel.remove(m.tf[i].Box[j]);
			}
			m.Editor_Panel.remove(m.tf[i].JTextField_Component);	//Editor_Panel���� ������Ʈ�� Box����
		}
		m.cnt_Label = 0;	//Label�� �� 0
		m.cnt_Button = 0;	//Button�� �� 0
		m.cnt_TextField = 0;	//TextField�� �� 0
		tmp = 4;
		repaint();	//���� �׸�(�ƹ��͵� ���� ����)
	}
	void Label_to_Button(Label la)	//Label�� ���� �״�� Button���� �ű�� �Լ�
	{
		m.bu[m.cnt_Button] = new Button();
		m.bu[m.cnt_Button].x = la.x;
		m.bu[m.cnt_Button].y = la.y;
		m.bu[m.cnt_Button].x_drag = la.x_drag;
		m.bu[m.cnt_Button].y_drag = la.y_drag;
		m.bu[m.cnt_Button].width = la.width;
		m.bu[m.cnt_Button].height = la.height;
		m.bu[m.cnt_Button].value = la.value;
		m.bu[m.cnt_Button].name = "Button"+(m.cnt_Button+1);
		m.bu[m.cnt_Button].JButton_Component = new JButton(m.bu[m.cnt_Button].value);
		m.bu[m.cnt_Button].JButton_Component.setBounds(la.x, la.y, la.width, la.height);
		bu_CreateBox(m.bu[m.cnt_Button]);
		Make_Non_Selected();
		m.Editor_Panel.add(m.bu[m.cnt_Button].JButton_Component);
		listener_bu[m.cnt_Button] = new MouseEvent_bu(m.cnt_Button);
		m.bu[m.cnt_Button].JButton_Component.addMouseListener(listener_bu[m.cnt_Button]);
		m.bu[m.cnt_Button].JButton_Component.addMouseMotionListener(listener_bu[m.cnt_Button]);
		m.bu[m.cnt_Button].isSelected = true;
		
		for(int i=0; i<8; ++i)
		{
			listener_box_bu[m.cnt_Button][i] = new BoxListener_bu(m.cnt_Button, i);
			m.bu[m.cnt_Button].Box[i].addMouseListener(listener_box_bu[m.cnt_Button][i]);
			m.bu[m.cnt_Button].Box[i].addMouseMotionListener(listener_box_bu[m.cnt_Button][i]);
		}
		++m.cnt_Button;
	}
	void Label_to_TextField(Label la)	//Label�� ���� �״�� TextField�� �ű�� �Լ�
	{
		m.tf[m.cnt_TextField] = new TextField();
		m.tf[m.cnt_TextField].x = la.x;
		m.tf[m.cnt_TextField].y = la.y;
		m.tf[m.cnt_TextField].x_drag = la.x_drag;
		m.tf[m.cnt_TextField].y_drag = la.y_drag;
		m.tf[m.cnt_TextField].width = la.width;
		m.tf[m.cnt_TextField].height = la.height;
		m.tf[m.cnt_TextField].value = la.value;
		m.tf[m.cnt_TextField].name = "TextField"+(m.cnt_TextField+1);
		m.tf[m.cnt_TextField].JTextField_Component = new JTextField(m.tf[m.cnt_TextField].value);
		m.tf[m.cnt_TextField].JTextField_Component.setBounds(la.x, la.y, la.width, la.height);
		tf_CreateBox(m.tf[m.cnt_TextField]);
		Make_Non_Selected();
		m.Editor_Panel.add(m.tf[m.cnt_TextField].JTextField_Component);
		listener_tf[m.cnt_TextField] = new MouseEvent_tf(m.cnt_TextField);
		m.tf[m.cnt_TextField].JTextField_Component.addMouseListener(listener_tf[m.cnt_TextField]);
		m.tf[m.cnt_TextField].JTextField_Component.addMouseMotionListener(listener_tf[m.cnt_TextField]);
		m.tf[m.cnt_TextField].isSelected = true;
		
		for(int i=0; i<8; ++i)
		{
			listener_box_tf[m.cnt_TextField][i] = new BoxListener_tf(m.cnt_TextField, i);
			m.tf[m.cnt_TextField].Box[i].addMouseListener(listener_box_tf[m.cnt_TextField][i]);
			m.tf[m.cnt_TextField].Box[i].addMouseMotionListener(listener_box_tf[m.cnt_TextField][i]);
		}
		++m.cnt_TextField;
	}
	void Button_to_Label(Button bu)	//Button�� ���� �״�� Label�� �ű�� �Լ�
	{
		m.la[m.cnt_Label] = new Label();
		m.la[m.cnt_Label].x = bu.x;
		m.la[m.cnt_Label].y = bu.y;
		m.la[m.cnt_Label].x_drag = bu.x_drag;
		m.la[m.cnt_Label].y_drag = bu.y_drag;
		m.la[m.cnt_Label].width = bu.width;
		m.la[m.cnt_Label].height = bu.height;
		m.la[m.cnt_Label].value = bu.value;
		m.la[m.cnt_Label].name = "Label"+(m.cnt_Label+1);
		m.la[m.cnt_Label].JLabel_Component = new JLabel(m.la[m.cnt_Label].value);
		m.la[m.cnt_Label].JLabel_Component.setBounds(bu.x, bu.y, bu.width, bu.height);
		la_CreateBox(m.la[m.cnt_Label]);
		Make_Non_Selected();
		m.Editor_Panel.add(m.la[m.cnt_Label].JLabel_Component);
		listener_la[m.cnt_Label] = new MouseEvent_la(m.cnt_Label);
		m.la[m.cnt_Label].JLabel_Component.addMouseListener(listener_la[m.cnt_Label]);
		m.la[m.cnt_Label].JLabel_Component.addMouseMotionListener(listener_la[m.cnt_Label]);
		m.la[m.cnt_Label].isSelected = true;
		m.la[m.cnt_Label].JLabel_Component.setBorder(eborder);
		m.la[m.cnt_Label].JLabel_Component.setBackground(Color.YELLOW);
		for(int i=0; i<8; ++i)
		{
			listener_box_la[m.cnt_Label][i] = new BoxListener_la(m.cnt_Label, i);
			m.la[m.cnt_Label].Box[i].addMouseListener(listener_box_la[m.cnt_Label][i]);
			m.la[m.cnt_Label].Box[i].addMouseMotionListener(listener_box_la[m.cnt_Label][i]);
		}
		++m.cnt_Label;
	}
	void Button_to_TextField(Button bu)	//Button�� ���� �״�� TextField�� �ű�� �Լ�
	{
		m.tf[m.cnt_TextField] = new TextField();
		m.tf[m.cnt_TextField].x = bu.x;
		m.tf[m.cnt_TextField].y = bu.y;
		m.tf[m.cnt_TextField].x_drag = bu.x_drag;
		m.tf[m.cnt_TextField].y_drag = bu.y_drag;
		m.tf[m.cnt_TextField].width = bu.width;
		m.tf[m.cnt_TextField].height = bu.height;
		m.tf[m.cnt_TextField].value = bu.value;
		m.tf[m.cnt_TextField].name = "TextField"+(m.cnt_TextField+1);
		m.tf[m.cnt_TextField].JTextField_Component = new JTextField(m.tf[m.cnt_TextField].value);
		m.tf[m.cnt_TextField].JTextField_Component.setBounds(bu.x, bu.y, bu.width, bu.height);
		tf_CreateBox(m.tf[m.cnt_TextField]);
		Make_Non_Selected();
		m.Editor_Panel.add(m.tf[m.cnt_TextField].JTextField_Component);
		listener_tf[m.cnt_TextField] = new MouseEvent_tf(m.cnt_TextField);
		m.tf[m.cnt_TextField].JTextField_Component.addMouseListener(listener_tf[m.cnt_TextField]);
		m.tf[m.cnt_TextField].JTextField_Component.addMouseMotionListener(listener_tf[m.cnt_TextField]);
		m.tf[m.cnt_TextField].isSelected = true;
		for(int i=0; i<8; ++i)
		{
			listener_box_tf[m.cnt_TextField][i] = new BoxListener_tf(m.cnt_TextField, i);
			m.tf[m.cnt_TextField].Box[i].addMouseListener(listener_box_tf[m.cnt_TextField][i]);
			m.tf[m.cnt_TextField].Box[i].addMouseMotionListener(listener_box_tf[m.cnt_TextField][i]);
		}		
		++m.cnt_TextField;
	}
	void TextField_to_Label(TextField tf)	//TextField�� ���� �״�� Label�� �ű�� �Լ�
	{
		m.la[m.cnt_Label] = new Label();
		m.la[m.cnt_Label].x = tf.x;
		m.la[m.cnt_Label].y = tf.y;
		m.la[m.cnt_Label].x_drag = tf.x_drag;
		m.la[m.cnt_Label].y_drag = tf.y_drag;
		m.la[m.cnt_Label].width = tf.width;
		m.la[m.cnt_Label].height = tf.height;
		m.la[m.cnt_Label].value = tf.value;
		m.la[m.cnt_Label].name = "Label"+(m.cnt_Label+1);
		m.la[m.cnt_Label].JLabel_Component = new JLabel(m.la[m.cnt_Label].value);
		m.la[m.cnt_Label].JLabel_Component.setBounds(tf.x, tf.y, tf.width, tf.height);
		la_CreateBox(m.la[m.cnt_Label]);
		Make_Non_Selected();
		m.Editor_Panel.add(m.la[m.cnt_Label].JLabel_Component);
		listener_la[m.cnt_Label] = new MouseEvent_la(m.cnt_Label);
		m.la[m.cnt_Label].JLabel_Component.addMouseListener(listener_la[m.cnt_Label]);
		m.la[m.cnt_Label].JLabel_Component.addMouseMotionListener(listener_la[m.cnt_Label]);
		m.la[m.cnt_Label].isSelected = true;
		m.la[m.cnt_Label].JLabel_Component.setBorder(eborder);
		m.la[m.cnt_Label].JLabel_Component.setBackground(Color.YELLOW);
		for(int i=0; i<8; ++i)
		{
			listener_box_la[m.cnt_Label][i] = new BoxListener_la(m.cnt_Label, i);
			m.la[m.cnt_Label].Box[i].addMouseListener(listener_box_la[m.cnt_Label][i]);
			m.la[m.cnt_Label].Box[i].addMouseMotionListener(listener_box_la[m.cnt_Label][i]);
		}		
		++m.cnt_Label;
	}
	void TextField_to_Button(TextField tf)	//TextField�� ���� �״�� Button���� �ű�� �Լ�
	{
		m.bu[m.cnt_Button] = new Button();
		m.bu[m.cnt_Button].x = tf.x;
		m.bu[m.cnt_Button].y = tf.y;
		m.bu[m.cnt_Button].x_drag = tf.x_drag;
		m.bu[m.cnt_Button].y_drag = tf.y_drag;
		m.bu[m.cnt_Button].width = tf.width;
		m.bu[m.cnt_Button].height = tf.height;
		m.bu[m.cnt_Button].value = tf.value;
		m.bu[m.cnt_Button].name = "Button"+(m.cnt_Button+1);
		m.bu[m.cnt_Button].JButton_Component = new JButton(m.bu[m.cnt_Button].value);
		m.bu[m.cnt_Button].JButton_Component.setBounds(tf.x, tf.y, tf.width, tf.height);
		bu_CreateBox(m.bu[m.cnt_Button]);
		Make_Non_Selected();
		m.Editor_Panel.add(m.bu[m.cnt_Button].JButton_Component);
		listener_bu[m.cnt_Button] = new MouseEvent_bu(m.cnt_Button);
		m.bu[m.cnt_Button].JButton_Component.addMouseListener(listener_bu[m.cnt_Button]);
		m.bu[m.cnt_Button].JButton_Component.addMouseMotionListener(listener_bu[m.cnt_Button]);
		m.bu[m.cnt_Button].isSelected = true;
		
		for(int i=0; i<8; ++i)
		{
			listener_box_bu[m.cnt_Button][i] = new BoxListener_bu(m.cnt_Button, i);
			m.bu[m.cnt_Button].Box[i].addMouseListener(listener_box_bu[m.cnt_Button][i]);
			m.bu[m.cnt_Button].Box[i].addMouseMotionListener(listener_box_bu[m.cnt_Button][i]);
		}		
		++m.cnt_Button;
		
	}
	
}
public class MVC_project{
	

	public static void main(String[] args)
	{
		MVC_Model mvc = new MVC_Model();
	}
}
