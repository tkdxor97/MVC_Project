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
		int x;	//사각형의 왼쪽x값
		int x_drag;	//사각형의 오른쪽x값
		int y;	//사각형의 왼쪽y값
		int y_drag;	//사각형의 오른쪽y값
		int width;	//사각형의 너비
		int height;	//사각형의 높이
		String value;	//사각형 내부에 들어가는 이름(내용)
		String type;	//사각형의 타입(JLabel, JButton, JTextField중 하나)
		String name;	//.java파일 생성시 이용되는 이름
		JLabel[] Box = new JLabel[8];	//크기 조절을 위한 작은 사각형 8개
		boolean isSelected = false;	//현재 사각형이 선택되있는지를 나타내는 변수
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
		view(model m)	//메뉴, 툴바, 속성패널, 에디터패널 등 배치
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

		int cnt_Label=0;	//Label의 수
		int cnt_Button=0;	//Button의 수
		int cnt_TextField=0;	//TextField의 수
		Label la[] = new Label[100];	//Label을 만들었을 때 정보를 저장하는 객체
		Button bu[] = new Button[100];	//Button을 만들었을 때 정보를 저장하는 객체
		TextField tf[] = new TextField[100];	//TextField를 만들었을 때 정보를 저장하는 객체
		model()
		{
			Button_Menu_New = new JButton("새로 만들기");
			Button_Menu_Open = new JButton("열기");
			Button_Menu_Save = new JButton("저장");
			Button_Menu_SaveDifferentName = new JButton("다른 이름으로 저장");
			Button_Menu_CreateJava = new JButton(".java 파일 생성");
			Button_Menu_Exit = new JButton("닫기");
			Button_Tool_New = new JButton("새로 만들기");
			Button_Tool_Open = new JButton("열기");
			Button_Tool_Save = new JButton("저장");
			Button_Tool_SaveDifferentName = new JButton("다른 이름으로 저장");
			Button_Tool_CreateJava = new JButton(".java 파일 생성");
			Button_Tool_Exit = new JButton("닫기");
			Menu = new JMenu("파일");
			mBar = new JMenuBar();
			tBar = new JToolBar();
			Button_Change = new JButton("변경");
			Combo = new JComboBox(Combo_Item);
			Attribute_Panel = new JPanel();
			Label_X = new JLabel("x좌표");
			Label_Y = new JLabel("y좌표");
			Label_Width = new JLabel("너비");
			Label_Height = new JLabel("높이");
			Label_Value = new JLabel("컴포넌트의 텍스트 속성값");
			Label_Type = new JLabel("컴포넌트 타입");
			Label_Name = new JLabel("컴포넌트 변수명");
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
			Button_Draw = new JButton("그리기");
			Button_Delete = new JButton("지우기");
			Button_Select = new JButton("선택/이동");
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
		public void actionPerformed(ActionEvent e)	//버튼 클릭 시 이벤트 발생 구분
		{
			JFileChooser jfc = new JFileChooser("C:");
			if(e.getActionCommand()=="새로 만들기")	//새로 만들기 버튼 클릭시
			{	//모든 내용 초기화
				New_File();	//새로 만들기 함수
				m.Text_X.setText(null);
				m.Text_Y.setText(null);
				m.Text_Height.setText(null);
				m.Text_Width.setText(null);
				m.Text_Value.setText(null);
				m.Text_Name.setText(null);
				m.Combo.setSelectedItem("JLabel");
			}
			
			else if(e.getActionCommand()=="열기")	//열기 버튼 클릭시
			{
				if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
					New_File();//새로 만들기 함수
					JSONParser parser = new JSONParser();//JSONFile을 읽어들이기 위한 JSONParser
					try {
						Object obj = parser.parse(new FileReader(jfc.getSelectedFile()));//선택된 파일의 내용을 오브젝트에 받음
						JSONObject jsonObject = (JSONObject) obj;//내용을 JSONObject로 옮김
						JSONArray Open_Label_Array = (JSONArray) jsonObject.get("Data_Label");//JSONObject 내에서 Label의 데이터가 든 JSONArray를 찾아 정보를 받는 객체 생성
						JSONArray Open_Button_Array = (JSONArray) jsonObject.get("Data_Button");//JSONObject 내에서 Button의 데이터가 든 JSONArray를 찾아 정보를 받는 객체 생성
						JSONArray Open_TextField_Array = (JSONArray) jsonObject.get("Data_TextField");//JSONObject 내에서 TextField의 데이터가 든 JSONArray를 찾아 정보를 받는 객체 생성
						Iterator<String> iterator_Label = Open_Label_Array.iterator();//Label의 데이터가 있는 JSONArray를 읽기 위한 Iterator
						while (iterator_Label.hasNext()) {
							m.la[m.cnt_Label] = new Label();//불러온 Label에 관한 정보를 담을 객체 생성
							m.la[m.cnt_Label].x = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].x_drag = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].y = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].y_drag = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].width = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].height = Integer.parseInt(iterator_Label.next());
							m.la[m.cnt_Label].value = iterator_Label.next();
                    	   	m.la[m.cnt_Label].type = iterator_Label.next();
                    	   	m.la[m.cnt_Label].name = iterator_Label.next();//Label의 정보를 읽어들임
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
                    	   	}//읽어들인 정보들을 토대로 EditorPane에 Label 생성
                    	   	++m.cnt_Label;//이를 통해 저장되어 있던 Label의 개수를 파악할 수 있음
						}
						Iterator<String> iterator_Button = Open_Button_Array.iterator();//Button의 데이터가 있는 JSONArray를 읽기 위한 Iterator
						while (iterator_Button.hasNext()) {
							m.bu[m.cnt_Button] = new Button();//불러온 Button에 관한 정보를 담을 객체 생성
							m.bu[m.cnt_Button].x = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].x_drag = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].y = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].y_drag = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].width = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].height = Integer.parseInt(iterator_Button.next());
							m.bu[m.cnt_Button].value = iterator_Button.next();
							m.bu[m.cnt_Button].type = iterator_Button.next();
							m.bu[m.cnt_Button].name = iterator_Button.next();//Button의 정보를 읽어들임
							m.bu[m.cnt_Button].JButton_Component = new JButton(m.bu[m.cnt_Button].value);
							m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x, m.bu[m.cnt_Button].y, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);
							m.Editor_Panel.add(m.bu[m.cnt_Button].JButton_Component);
							bu_CreateBox(m.bu[m.cnt_Button]);
							for(int i=0; i<8; ++i)
							{
								listener_box_bu[m.cnt_Button][i] = new BoxListener_bu(m.cnt_Button, i);
								m.bu[m.cnt_Button].Box[i].addMouseListener(listener_box_bu[m.cnt_Button][i]);
								m.bu[m.cnt_Button].Box[i].addMouseMotionListener(listener_box_bu[m.cnt_Button][i]);
							}//읽어들인 정보들을 토대로 EditorPane에 Button 생성							
							++m.cnt_Button;//이를 통해 저장되어 있던 Button의 개수를 파악할 수 있음
						}
						Iterator<String> iterator_TextField = Open_TextField_Array.iterator();//TextField의 데이터가 있는 JSONArray를 읽기 위한 Iterator
						while (iterator_TextField.hasNext()) {
							m.tf[m.cnt_TextField] = new TextField();//불러온 TextField에 관한 정보를 담을 객체 생성
    	              	   	m.tf[m.cnt_TextField].x = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].x_drag = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].y = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].y_drag = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].width = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].height = Integer.parseInt(iterator_TextField.next());
    	              	   	m.tf[m.cnt_TextField].value = iterator_TextField.next();
    	              	   	m.tf[m.cnt_TextField].type = iterator_TextField.next();
    	              	   	m.tf[m.cnt_TextField].name = iterator_TextField.next();//TextField의 정보를 읽어들임
    	              	   	m.tf[m.cnt_TextField].JTextField_Component = new JTextField(m.tf[m.cnt_TextField].value);
    	              	   	m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x, m.tf[m.cnt_TextField].y, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);
    	              	   	m.Editor_Panel.add(m.tf[m.cnt_TextField].JTextField_Component);
    	              	   	tf_CreateBox(m.tf[m.cnt_TextField]);
    	              	   	for(int i=0; i<8; ++i)
    	              	   	{
    	              	   		listener_box_tf[m.cnt_TextField][i] = new BoxListener_tf(m.cnt_TextField, i);
    	              	   		m.tf[m.cnt_TextField].Box[i].addMouseListener(listener_box_tf[m.cnt_TextField][i]);
    	              	   		m.tf[m.cnt_TextField].Box[i].addMouseMotionListener(listener_box_tf[m.cnt_TextField][i]);
    	              	   	}//읽어들인 정보들을 토대로 EditorPane에 TextField 생성
    	              	   	++m.cnt_TextField;//이를 통해 저장되어 있던 TextField의 개수를 파악할 수 있음
						}
					} catch (FileNotFoundException E) {
						System.err.println("파일을 찾을 수 없습니다.");//E.printStackTrace();
					} catch (IOException E) {
						System.err.println("입출력 에러가 발생했습니다.");//E.printStackTrace();
					} catch (ParseException E) {
						System.err.println("Parse 에러가 발생했습니다.");//E.printStackTrace();
					}                     
				}
				Make_Non_Selected();
				Show_Box();
				repaint();
			}
			else if(e.getActionCommand()=="저장")	//저장 버튼 클릭시
			{
				JSONObject obj = new JSONObject();//JSONArray들을 넣을 JSONObject
				JSONArray Save_Label_Array  = new JSONArray();//JLabel들의 정보를 담을 JSONArray
				JSONArray Save_Button_Array  = new JSONArray();//JButton들의 정보를 담을 JSONArray
				JSONArray Save_TextField_Array  = new JSONArray();//JTextField들의 정보를 담을 JSONArray
				
				if(m.cnt_Label != 0)//Label이 생성되어 있을 때 그 수만큼 for문을 돌린다
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
				}//라벨용 JSONArray에 차례대로 Label의 속성페인 값들을 추가
				obj.put("Data_Label", Save_Label_Array);//JSONObject에 Label의 속성페인 값들이 저장된 JSONArray를 추가
				if(m.cnt_Button != 0)//Button이 생성되어 있을 때 그 수만큼 for문을 돌린다
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
				}//버튼용 JSONArray에 차례대로 Button의 속성페인 값들을 추가
				obj.put("Data_Button", Save_Button_Array);//JSONObject에 Button의 속성페인 값들이 저장된 JSONArray를 추가
				if(m.cnt_TextField != 0)//TextField가 생성되어 있을 때 그 수만큼 for문을 돌린다
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
				}//텍스트필드 용 JSONArray에 차례대로 TextFeild의 속성페인 값들을 추가
				obj.put("Data_TextField", Save_TextField_Array);//JSONObject에 TextField의 속성페인 값들이 저장된 JSONArray를 추가
				try (FileWriter file = new FileWriter("Data.txt")) {//Workspace에 Data라는 이름의 txt파일에 정보를 쓴다
					file.write(obj.toString());
					file.flush();
					file.close();
				}
				catch(IOException ioe)//예외처리
				{
					System.err.println("입출력 에러가 발생했습니다.");//ioe.printStackTrace();
				}
			}
			else if(e.getActionCommand()=="다른 이름으로 저장")	//다른 이름으로 저장 버튼 클릭시
			{
				if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					JSONObject obj = new JSONObject();//JSONArray들을 넣을 JSONObject
					JSONArray Save_Label_Array  = new JSONArray();//JLabel들의 정보를 담을 JSONArray
					JSONArray Save_Button_Array  = new JSONArray();//JButton들의 정보를 담을 JSONArray
					JSONArray Save_TextField_Array  = new JSONArray();//JTextField들의 정보를 담을 JSONArray
					
					if(m.cnt_Label != 0)//Label이 생성되어 있을 때 그 수만큼 for문을 돌린다
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
					}//라벨용 JSONArray에 차례대로 Label의 속성페인 값들을 추가
					obj.put("Data_Label", Save_Label_Array);//JSONObject에 Label의 속성페인 값들이 저장된 JSONArray를 추가
					if(m.cnt_Button != 0)//Button이 생성되어 있을 때 그 수만큼 for문을 돌린다
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
					}//버튼용 JSONArray에 차례대로 Button의 속성페인 값들을 추가
					obj.put("Data_Button", Save_Button_Array);//JSONObject에 Button의 속성페인 값들이 저장된 JSONArray를 추가
					if(m.cnt_TextField != 0)//TextField가 생성되어 있을 때 그 수만큼 for문을 돌린다
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
					}//텍스트필드 용 JSONArray에 차례대로 TextFeild의 속성페인 값들을 추가
					obj.put("Data_TextField", Save_TextField_Array);//JSONObject에 TextField의 속성페인 값들이 저장된 JSONArray를 추가
					try (FileWriter file = new FileWriter(jfc.getSelectedFile()+".txt")) {//사용자가 지정한 이름의 텍스트 파일에 정보를 쓴다
						file.write(obj.toString());
						file.flush();
						file.close();
					}
					catch(IOException ioe)//예외처리
					{
						System.err.println("입출력 에러가 발생했습니다.");//ioe.printStackTrace();
					}
				}
			}
			else if(e.getActionCommand()==".java 파일 생성")	//.java 파일 생성 클릭시
			{
				try {
					File f = new File("Make_JavaFile.java");//Make_JavaFile이라는 자바 파일을 생성
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
				}//컴포넌트를 그려줄 코드를 생성한 파일 안에 쓴다
				catch(IOException ioe)//예외처리
				{
					System.err.println("입출력 에러가 발생했습니다.");//ioe.printStackTrace();
				}
			}
			else if(e.getActionCommand()=="닫기")	//닫기 버튼 클릭시
			{
				System.exit(0);	//프로그램 종료
			}
			else if(e.getActionCommand()=="변경")	//변경 버튼 클릭시
			{
				int tmp=0;	//변경이 되었는지를 나타내는 변수. 변경이 되었을시 1로 변환
				if(m.Combo.getSelectedItem()=="JLabel")	//컴포넌트 타입 콤보박스가 JLabel로 되어있을 때
				{
					for(int i=0; i<m.cnt_Label; ++i)
					{
						if(m.la[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 Label일 경우
						{	//속성 패널의 TextField의 값을 읽어 현재 선택된 la객체에 정보를 옮긴다.
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
							tmp = 1;	//변경 완료
						}
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						if(m.bu[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 Button일 경우
						{	//속성 패널의 TextField의 값을 읽어 현재 선택된 bu객체에 정보를 옮긴다.
							m.bu[i].x = Integer.parseInt(m.Text_X.getText());
							m.bu[i].y = Integer.parseInt(m.Text_Y.getText());
							m.bu[i].width = Integer.parseInt(m.Text_Width.getText());
							m.bu[i].height = Integer.parseInt(m.Text_Height.getText());
							m.bu[i].value = m.Text_Value.getText();
							m.bu[i].name = m.Text_Name.getText();
							m.bu[i].JButton_Component.setText(m.bu[i].value);
							m.bu[i].x_drag = m.bu[i].x + m.bu[i].width;
							m.bu[i].y_drag = m.bu[i].y + m.bu[i].height;
							Button_to_Label(m.bu[i]);	//현재 선택된 bu객체를 la객체로 복사한다
							Remove_bu(m.bu[i]);	//선택된 bu객체를 삭제한다.
							Show_Box();
							--m.cnt_Button;	//Button의 수를 감소시킨다.
							tmp = 1;	//변경 완료
						}
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						if(m.tf[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 TextField일 경우
						{	//속성 패널의 TextField의 값을 읽어 현재 선택된 tf객체에 정보를 옮긴다. 
							m.tf[i].x = Integer.parseInt(m.Text_X.getText());
							m.tf[i].y = Integer.parseInt(m.Text_Y.getText());
							m.tf[i].width = Integer.parseInt(m.Text_Width.getText());
							m.tf[i].height = Integer.parseInt(m.Text_Height.getText());
							m.tf[i].value = m.Text_Value.getText();
							m.tf[i].name = m.Text_Name.getText();
							m.tf[i].JTextField_Component.setText(m.tf[i].value);
							m.tf[i].x_drag = m.tf[i].x + m.tf[i].width;
							m.tf[i].y_drag = m.tf[i].y + m.tf[i].height;
							TextField_to_Label(m.tf[i]);	//현재 선택된 tf객체를 la객체로 복사한다.
							Remove_tf(m.tf[i]);	//선택된 tf객체를 삭제한다.
							Show_Box();
							--m.cnt_TextField;	//TextField의 수를 감소시킨다.
							tmp = 1;	//변경 완료
						}
					}
				}
				else if(m.Combo.getSelectedItem()=="JButton")	//컴포넌트 타입 콤보박스가 JButton으로 선택되었을 경우
				{
					for(int i=0; i<m.cnt_Label; ++i)
					{
						if(m.la[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 Label일 경우
						{	//속성 패널의 TextField값을 읽어 현재 선택된 la객체에 정보를 옮긴다.
							m.la[i].x = Integer.parseInt(m.Text_X.getText());
							m.la[i].y = Integer.parseInt(m.Text_Y.getText());
							m.la[i].width = Integer.parseInt(m.Text_Width.getText());
							m.la[i].height = Integer.parseInt(m.Text_Height.getText());
							m.la[i].value = m.Text_Value.getText();
							m.la[i].name = m.Text_Name.getText();
							m.la[i].JLabel_Component.setText(m.la[i].value);
							m.la[i].x_drag = m.la[i].x + m.la[i].width;
							m.la[i].y_drag = m.la[i].y + m.la[i].height;
							Label_to_Button(m.la[i]);	//현재 선택된 la객체를 bu객체로 복사한다.
							Remove_la(m.la[i]);	//선택된 la객체를 삭제한다.
							Show_Box();
							--m.cnt_Label;	//Label의 수를 감소시킨다.
							tmp = 1;	//변경 완료
						}
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						if(m.bu[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 Button일 경우
						{	//속성 패널의 TextField값을 읽어 현재 선택된 bu객체에 정보를 옮긴다.
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
							tmp = 1;	//변경 완료
						}
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						if(m.tf[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 TextField일 경우
						{	//속성 패널의 TextField값을 읽어 현재 선택된 tf객체에 정보를 옮긴다.
							m.tf[i].x = Integer.parseInt(m.Text_X.getText());
							m.tf[i].y = Integer.parseInt(m.Text_Y.getText());
							m.tf[i].width = Integer.parseInt(m.Text_Width.getText());
							m.tf[i].height = Integer.parseInt(m.Text_Height.getText());
							m.tf[i].value = m.Text_Value.getText();
							m.tf[i].name = m.Text_Name.getText();
							m.tf[i].JTextField_Component.setText(m.tf[i].value);
							m.tf[i].x_drag = m.tf[i].x + m.tf[i].width;
							m.tf[i].y_drag = m.tf[i].y + m.tf[i].height;
							TextField_to_Button(m.tf[i]);	//현재 선택된 tf객체를 bu객체로 복사한다.
							Remove_tf(m.tf[i]);	//현재 선택된 tf객체를 삭제한다.
							Show_Box();
							--m.cnt_TextField;	//TextField의 수를 감소시킨다.
							tmp = 1;	//변경 완료
						}
					}
				}
				else if(m.Combo.getSelectedItem()=="JTextField")	//컴포넌트 타입 콤보박스가 JTextField로 되어있을 때
				{
					for(int i=0; i<m.cnt_Label; ++i)
					{
						if(m.la[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 Label일 경우
						{	//속성 패널의 TextField값을 읽어 현재 선택된 la객체에 정보를 옮긴다.
							m.la[i].x = Integer.parseInt(m.Text_X.getText());
							m.la[i].y = Integer.parseInt(m.Text_Y.getText());
							m.la[i].width = Integer.parseInt(m.Text_Width.getText());
							m.la[i].height = Integer.parseInt(m.Text_Height.getText());
							m.la[i].value = m.Text_Value.getText();
							m.la[i].name = m.Text_Name.getText();
							m.la[i].JLabel_Component.setText(m.la[i].value);
							m.la[i].x_drag = m.la[i].x + m.la[i].width;
							m.la[i].y_drag = m.la[i].y + m.la[i].height;
							Label_to_TextField(m.la[i]);	//현재 선택된 la객체를 tf객체로 복사한다.
							Remove_la(m.la[i]);	//현재 선택된 la객체를 삭제한다.
							Show_Box();
							--m.cnt_Label;	//Label의 수를 감소시킨다.
							tmp = 1;	//변경 완료
						}
					}
					for(int i=0; i<m.cnt_Button; ++i)
					{
						if(m.bu[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 Button일 경우
						{	//속성 패널의 TextField값을 읽어 현재 선택된 bu객체에 정보를 옮긴다.
							m.bu[i].x = Integer.parseInt(m.Text_X.getText());
							m.bu[i].y = Integer.parseInt(m.Text_Y.getText());
							m.bu[i].width = Integer.parseInt(m.Text_Width.getText());
							m.bu[i].height = Integer.parseInt(m.Text_Height.getText());
							m.bu[i].value = m.Text_Value.getText();
							m.bu[i].name = m.Text_Name.getText();
							m.bu[i].JButton_Component.setText(m.bu[i].value);
							m.bu[i].x_drag = m.bu[i].x + m.bu[i].width;
							m.bu[i].y_drag = m.bu[i].y + m.bu[i].height;
							Button_to_TextField(m.bu[i]);	//현재 선택된 bu객체를 tf객체로 복사한다.
							Remove_bu(m.bu[i]);	//현재 선택된 bu객체를 삭제한다.
							Show_Box();
							--m.cnt_Button;	//Button의 수를 감소시킨다.
							tmp = 1;	//변경 완료
						}
					}
					for(int i=0; i<m.cnt_TextField; ++i)
					{
						if(m.tf[i].isSelected == true && tmp == 0)	//현재 선택된 컴포넌트가 TextField일 경우
						{	//속성 패널의 TextField값을 읽어 현재 선택된 tf객체에 정보를 옮긴다.
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
							tmp = 1;	//변경 완료
						}
					}
				}
				repaint();	//변경된 것들을 화면에 표시
			}
			else if(e.getActionCommand()=="그리기")	//그리기 버튼 클릭시
			{
				RemoveMouseListener();
				tmp = 0;	//그리기를 눌렀을 때 현재 그릴 컴포넌트를 나타냄(1 : Label, 2 : Button, 3 : TextField, 4 : 그리기완료)
				if(tmp==0)
				{
					if(m.Radio_Label.isSelected() == true)	//Label이 선택되었을 때
					{
						tmp=1;	//현재 그리는 컴포넌트를 Label로 설정
						m.la[m.cnt_Label] = new Label();	//la객체를 하나 만듬
						m.Editor_Panel.addMouseListener(new MouseAdapter() {	//Editor_Panel에서의 마우스 이벤트
							public void mousePressed(MouseEvent me) {	//마우스를 눌렀을 때
								if(tmp==1)
								{
									m.Text_X.setText(String.valueOf(me.getX()));	//마우스의 X좌표를 읽고 표시
									m.Text_Y.setText(String.valueOf(me.getY()));	//마우스의 Y좌표를 읽고 표시
									m.la[m.cnt_Label].x = me.getX();	//마우스의 X좌표를 읽고 저장
									m.la[m.cnt_Label].y = me.getY();	//마우스의 Y좌표를 읽고 저장
									m.la[m.cnt_Label].value = "Label"+(m.cnt_Label+1);	//value의 기본값으로 Label##식으로 라벨 번호 저장
									m.la[m.cnt_Label].type = "Label";	//type을 Label로 저장
									m.la[m.cnt_Label].name = "Label"+(m.cnt_Label+1);	//name의 기본값으로 Label##식으로 라벨 번호 저장
									m.Combo.setSelectedItem(m.Combo_Item[0]);	//컴포넌트 타입의 콤보박스를 JLabel로 변경
									m.la[m.cnt_Label].JLabel_Component = new JLabel("Label"+(m.cnt_Label+1));	//la의 JLabel_Component 생성
									m.la[m.cnt_Label].JLabel_Component.setBackground(Color.YELLOW);	//테두리 색을 지정
									m.la[m.cnt_Label].JLabel_Component.setBorder(eborder);	//테두리 모양 지정
									m.Editor_Panel.add(m.la[m.cnt_Label].JLabel_Component);	//만든 컴포넌트를 Editor_Panel에 추가
								}
							}
							public void mouseReleased(MouseEvent e) {	//마우스를 뗐을 때
								if(tmp==1)
								{
									if(m.la[m.cnt_Label].x > m.la[m.cnt_Label].x_drag)	//x값이 x_drag값보다 클 경우(x방향이 역방향으로 그려졌을 경우)
									{	//x와 x_drag값을 뒤바꾼다
										int tmp2 = m.la[m.cnt_Label].x;
										m.la[m.cnt_Label].x = m.la[m.cnt_Label].x_drag;
										m.la[m.cnt_Label].x_drag = tmp2;
									}
									if(m.la[m.cnt_Label].y > m.la[m.cnt_Label].y_drag)	//y값이 y_drag값보다 클 경우(y방향이 역방향으로 그려졌을 경우)
									{	//y값과 y_drag값을 뒤바꾼다
										int tmp2 = m.la[m.cnt_Label].y;
										m.la[m.cnt_Label].y = m.la[m.cnt_Label].y_drag;
										m.la[m.cnt_Label].y_drag = tmp2;
									}
									la_CreateBox(m.la[m.cnt_Label]);	//Box를 새로 만들어줌
									Make_Non_Selected();	//모든 객체의 isSelected를 false로 만듬
									m.la[m.cnt_Label].isSelected = true;	//현재 생성중인 객체의 isSelected를 true로 만듬
									for(int i=0; i<8; ++i)	//8개의 Box에 마우스리스너를 추가함
									{
										listener_box_la[m.cnt_Label][i] = new BoxListener_la(m.cnt_Label, i);
										m.la[m.cnt_Label].Box[i].addMouseListener(listener_box_la[m.cnt_Label][i]);
										m.la[m.cnt_Label].Box[i].addMouseMotionListener(listener_box_la[m.cnt_Label][i]);
									}
									++m.cnt_Label;	//Label수를 1 증가시킨다
									Show_Box();	//현재 선택된 컴포넌트의 Box만 보여줌
									tmp = 4;	//그리기 완료
									repaint();	//변경된 컴포넌트대로 새로 그림
								}
							}
						});
						m.Editor_Panel.addMouseMotionListener(new MouseMotionAdapter(){	//Editor_Panel에서의 마우스 이벤트
							public void mouseDragged(MouseEvent me){	//드래그 했을 때
								if(tmp==1)
								{
									int reverse_x=0;	//x방향이 역방향으로 그려졌을 경우 1
									int reverse_y=0;	//y방향이 역방향으로 그려졌을 경우 1
									m.la[m.cnt_Label].x_drag = me.getX();	//드래그 하는 동안 마우스의 X좌표를 저장
									m.la[m.cnt_Label].y_drag = me.getY();	//드래그 하는 동안 마우스의 Y좌표를 저장
									if(m.la[m.cnt_Label].x > m.la[m.cnt_Label].x_drag)	//x방향이 역방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].width = -(m.la[m.cnt_Label].x_drag - m.la[m.cnt_Label].x);	//width = -(x_drag - x)
										reverse_x=1;
									}
									else	//x방향이 정방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].width = m.la[m.cnt_Label].x_drag - m.la[m.cnt_Label].x;	//width = x_drag - x										
									}
									if(m.la[m.cnt_Label].y > m.la[m.cnt_Label].y_drag)	//y방향이 역방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].height = -(m.la[m.cnt_Label].y_drag - m.la[m.cnt_Label].y);	//height = -(y_drag - y)
										reverse_y=1;
									}
									else	//y방향이 정방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].height = m.la[m.cnt_Label].y_drag - m.la[m.cnt_Label].y;	//height = y - y_drag
									}
									m.Text_Width.setText(Integer.toString(m.la[m.cnt_Label].width));	//속성 패널의 Width에 너비 표시
									m.Text_Height.setText(Integer.toString(m.la[m.cnt_Label].height));	//속성 패널의 Height에 너비 표시
									if(reverse_x==0 && reverse_y==0)	//x, y방향 모두 정방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x, m.la[m.cnt_Label].y, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x, y, width, height)
									}
									else if(reverse_x==1 && reverse_y==0)	//x가 역방향, y가 정방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x_drag, m.la[m.cnt_Label].y, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x_drag, y, width, height)
									}
									else if(reverse_x==0 && reverse_y==1)	//x가 정방향, y가 역방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x, m.la[m.cnt_Label].y_drag, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x, y_drag, width, height)
									}
									else if(reverse_x==1 && reverse_y==1)	//x, y모두 역방향으로 그려졌을 경우
									{
										m.la[m.cnt_Label].JLabel_Component.setBounds(m.la[m.cnt_Label].x_drag, m.la[m.cnt_Label].y_drag, m.la[m.cnt_Label].width, m.la[m.cnt_Label].height);	//setBounds(x_drag, y_drag, width, height)
									}
								}
							}
						});
					}
					else if(Model.Radio_Button.isSelected()==true)	//Button이 선택되었을 경우
					{
						tmp=2;	//현재 그리는 컴포넌트를 Button으로 지정
						m.bu[m.cnt_Button] = new Button();	//bu객체를 하나 생성
						m.Editor_Panel.addMouseListener(new MouseAdapter() {	//Editor_Panel에서의 마우스 이벤트
							public void mousePressed(MouseEvent me) {	//마우스가 눌러졌을 때
								if(tmp==2)
								{
									m.Text_X.setText(String.valueOf(me.getX()));	//현재 마우스의 X좌표를 속성패널에 입력
								    m.Text_Y.setText(String.valueOf(me.getY()));	//현재 마우스의 Y좌표를 속성패널에 입력
								    m.bu[m.cnt_Button].x = me.getX();	//현재 마우스의 X좌표를 저장
								    m.bu[m.cnt_Button].y = me.getY();	//현재 마우스의 Y좌표를 저장
								    m.bu[m.cnt_Button].value = "Button"+(m.cnt_Button+1);	//value의 기본값으로 Button##와 같이 버튼 번호 저장
								    m.bu[m.cnt_Button].type = "Button";	//type을 Button으로 저장
								    m.bu[m.cnt_Button].name = "Button"+(m.cnt_Button+1);	//name의 기본값으로 Button##와 같이 버튼 번호 저장
									m.Combo.setSelectedItem(m.Combo_Item[1]);	//컴포넌트 타입의 콤보박스를 JButton으로 변경
									m.bu[m.cnt_Button].JButton_Component = new JButton("Button"+(m.cnt_Button+1));	//bu객체의 JButton_Component를 생성
									m.Editor_Panel.add(m.bu[m.cnt_Button].JButton_Component);	//Editor_Panel에 생성한 컴포넌트 추가
								}
							}
							public void mouseReleased(MouseEvent e) {	//마우스를 뗏을 때
								if(tmp==2)
								{
									if(m.bu[m.cnt_Button].x > m.bu[m.cnt_Button].x_drag)	//x방향이 역방향으로 그려졌을 경우
									{	//x와 x_drag의 값을 뒤바꿈
										int tmp2 = m.bu[m.cnt_Button].x;
										m.bu[m.cnt_Button].x = m.bu[m.cnt_Button].x_drag;
										m.bu[m.cnt_Button].x_drag = tmp2;
									}
									if(m.bu[m.cnt_Button].y > m.bu[m.cnt_Button].y_drag)	//y방향이 역방향으로 그려졌을 경우
									{	//y와 y_drag의 값을 뒤바꿈
										int tmp2 = m.bu[m.cnt_Button].y;
										m.bu[m.cnt_Button].y = m.bu[m.cnt_Button].y_drag;
										m.bu[m.cnt_Button].y_drag = tmp2;
									}
									bu_CreateBox(m.bu[m.cnt_Button]);	//Box위치 지정
									Make_Non_Selected();	//모든 객체의 isSelected를 false로 만듬
									m.bu[m.cnt_Button].isSelected = true;	//현재 객체의 isSelected를 true로 만듬
									for(int i=0; i<8; ++i)	//Box 8개에 마우스리스너 추가
									{
										listener_box_bu[m.cnt_Button][i] = new BoxListener_bu(m.cnt_Button, i);
										m.bu[m.cnt_Button].Box[i].addMouseListener(listener_box_bu[m.cnt_Button][i]);
										m.bu[m.cnt_Button].Box[i].addMouseMotionListener(listener_box_bu[m.cnt_Button][i]);
									}
									tmp = 4;
									++m.cnt_Button;	//Button수 1증가
									Show_Box();	//현재 선택된 컴포넌트의 Box만 보여줌
									m.Editor_Panel.repaint();	//컴포넌트가 변경된 대로 새로 그림
								}
							}
						});
						m.Editor_Panel.addMouseMotionListener(new MouseMotionAdapter(){	//Editor_Panel에서 마우스이벤트
							public void mouseDragged(MouseEvent me){	//마우스가 드래그되었을 때
								if(tmp==2)
								{
									int reverse_x=0;	//x방향이 역방향으로 그려졌을 경우 1
									int reverse_y=0;	//y방향이 역방향으로 그려졌을 경우 1
									m.bu[m.cnt_Button].x_drag = me.getX();	//x_drag에 현재 드래그되는 마우스의 X좌표 저장
									m.bu[m.cnt_Button].y_drag = me.getY();	//y_drag에 현재 드래그되는 마우스의 Y좌표 저장
									if(m.bu[m.cnt_Button].x > m.bu[m.cnt_Button].x_drag)	//x방향이 역방향으로 그려졌을 경우
									{
										m.bu[m.cnt_Button].width = -(m.bu[m.cnt_Button].x_drag - m.bu[m.cnt_Button].x);	//width = -(x_drag - x)										
										reverse_x=1;
									}
									else	//x방향이 정방향으로 그려졌을 경우
									{
										m.bu[m.cnt_Button].width = m.bu[m.cnt_Button].x_drag - m.bu[m.cnt_Button].x;	//width = x_drag - x										
									}
									if(m.bu[m.cnt_Button].y > m.bu[m.cnt_Button].y_drag)	//y방향이 역방향으로 그려졌을 경우
									{
										m.bu[m.cnt_Button].height = -(m.bu[m.cnt_Button].y_drag - m.bu[m.cnt_Button].y);	//height = -(y_drag - y)
										reverse_y=1;
									}
									else	//y방향이 정방향으로 그려졌을 경우
									{
										m.bu[m.cnt_Button].height = m.bu[m.cnt_Button].y_drag - m.bu[m.cnt_Button].y;	//height = y_drag - y
									}
									m.Text_Width.setText(Integer.toString(m.bu[m.cnt_Button].width));	//속성 패널에 width값 표시
									m.Text_Height.setText(Integer.toString(m.bu[m.cnt_Button].height));	//속성 패널에 height값 표시
									
									if(reverse_x==0 && reverse_y==0)	//x, y방향 모두 정방향으로 그려졌을 경우
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x, m.bu[m.cnt_Button].y, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x, y, width, height)
									}
									else if(reverse_x==1 && reverse_y==0)	//x역방향, y정방향
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x_drag, m.bu[m.cnt_Button].y, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x_drag, y, width, height)
									}
									else if(reverse_x==0 && reverse_y==1)	//x정방향, y역방향
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x, m.bu[m.cnt_Button].y_drag, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x, y_drag, width, height)
									}
									else if(reverse_x==1 && reverse_y==1)	//x역방향, y역방향
									{
										m.bu[m.cnt_Button].JButton_Component.setBounds(m.bu[m.cnt_Button].x_drag, m.bu[m.cnt_Button].y_drag, m.bu[m.cnt_Button].width, m.bu[m.cnt_Button].height);	//setBounds(x_drag, y_drag, width, height)
									}
								}
							}
						});
					}
					else if(Model.Radio_TextField.isSelected()==true)	//TextField가 선택되었을 경우
					{
						tmp = 3;	//TextField그리기
						m.tf[m.cnt_TextField] = new TextField();	//tf객체 추가
						m.Editor_Panel.addMouseListener(new MouseAdapter() {	//Editor_Panel에서의 마우스이벤트
							public void mousePressed(MouseEvent me) {	//마우스를 눌렀을 때
								if(tmp==3)
								{
									m.Text_X.setText(String.valueOf(me.getX()));	//현재 마우스의 X좌표를 속성 패널에 표시
								    m.Text_Y.setText(String.valueOf(me.getY()));	//현재 마우스의 Y좌표를 속성 패널에 표시
								    m.tf[m.cnt_TextField].x = me.getX();	//현재 마우스의 X좌표를 저장
								    m.tf[m.cnt_TextField].y = me.getY();	//현재 마우스의 Y좌표를 저장
								    m.tf[m.cnt_TextField].value = "TextField"+(m.cnt_TextField+1);	//value의 기본값으로 TextField##처럼 TextField의 번호 저장 
									m.tf[m.cnt_TextField].type = "TextField";	//type을 TextField로 저장
								    m.tf[m.cnt_TextField].name = "TextField"+(m.cnt_TextField+1);	//name의 기본값으로 TextField##처럼 TextField의 번호 저장
									m.Combo.setSelectedItem(m.Combo_Item[2]);	//컴포넌트 타입의 콤보박스를 JTextField로 변환
									m.tf[m.cnt_TextField].JTextField_Component = new JTextField("TextField"+(m.cnt_TextField+1));	//tf의 JTextField_Component를 새로 만듬
									m.Editor_Panel.add(m.tf[m.cnt_TextField].JTextField_Component);	//Editor_Panel에 새로 만든 컴포넌트 추가
								}
							}
							public void mouseReleased(MouseEvent e) {	//마우스를 뗏을 때
								if(tmp==3)
								{
									if(m.tf[m.cnt_TextField].x > m.tf[m.cnt_TextField].x_drag)	//x방향이 역방향으로 그려졌을 때
									{	//x와 x_drag의 값을 뒤바꿈
										int tmp2 = m.tf[m.cnt_TextField].x;
										m.tf[m.cnt_TextField].x = m.tf[m.cnt_TextField].x_drag;
										m.tf[m.cnt_TextField].x_drag = tmp2;
									}
									if(m.tf[m.cnt_TextField].y > m.tf[m.cnt_TextField].y_drag)	//y방향이 역방향으로 그려졌을 때
									{	//y와 y_drag의 값을 뒤바꿈
										int tmp2 = m.tf[m.cnt_TextField].y;
										m.tf[m.cnt_TextField].y = m.tf[m.cnt_TextField].y_drag;
										m.tf[m.cnt_TextField].y_drag = tmp2;
									}
									tf_CreateBox(m.tf[m.cnt_TextField]);	//Box위치 설정
									Make_Non_Selected();	//모든 객체의 isSelected를 false로 만듬
									m.tf[m.cnt_TextField].isSelected = true;	//현재 객체의 isSelected를 true로 만듬
									for(int i=0; i<8; ++i)	//박스 8개에 마우스리스너 추가
									{
										listener_box_tf[m.cnt_TextField][i] = new BoxListener_tf(m.cnt_TextField, i);
										m.tf[m.cnt_TextField].Box[i].addMouseListener(listener_box_tf[m.cnt_TextField][i]);
										m.tf[m.cnt_TextField].Box[i].addMouseMotionListener(listener_box_tf[m.cnt_TextField][i]);
									}
									tmp = 4;	//그리기 완료
									++m.cnt_TextField;	//TextField의 수 1증가
									Show_Box();	//현재 선택된 컴포넌트의 Box만 표시
									m.Editor_Panel.repaint();	//새로 그려줌
								}
							}
						});
						m.Editor_Panel.addMouseMotionListener(new MouseMotionAdapter(){	//Editor_Panel에서 마우스이벤트
							public void mouseDragged(MouseEvent me){	//마우스가 드래그되었을 때
								if(tmp==3)
								{
									int reverse_x=0;	//x방향이 역방향으로 그려졌을 때 1
									int reverse_y=0;	//y방향이 역방향으로 그려졌을 때 1
									m.tf[m.cnt_TextField].x_drag = me.getX();	//x_drag에 드래그되는동안의 마우스 X좌표 저장
									m.tf[m.cnt_TextField].y_drag = me.getY();	//y_drag에 드래그되는동안의 마우스 Y좌표 저장
									if(m.tf[m.cnt_TextField].x > m.tf[m.cnt_TextField].x_drag)	//x방향이 역방향으로 그려졌을 경우
									{
										m.tf[m.cnt_TextField].width = -(m.tf[m.cnt_TextField].x_drag - m.tf[m.cnt_TextField].x);	//width = -(x_drag - x)										
										reverse_x=1;
									}
									else	//x방향이 정방향으로 그려졌을 경우
									{
										m.tf[m.cnt_TextField].width = m.tf[m.cnt_TextField].x_drag - m.tf[m.cnt_TextField].x;	//width = x_drag - x										
									}
									if(m.tf[m.cnt_TextField].y > m.tf[m.cnt_TextField].y_drag)	//y방향이 역방향으로 그려졌을 경우
									{
										m.tf[m.cnt_TextField].height = -(m.tf[m.cnt_TextField].y_drag - m.tf[m.cnt_TextField].y);	//height = -(y_drag - y)
										reverse_y=1;
									}
									else	//y방향이 정방향으로 그려졌을 경우
									{
										m.tf[m.cnt_TextField].height = m.tf[m.cnt_TextField].y_drag - m.tf[m.cnt_TextField].y;	//height = y_drag - y
									}
									m.Text_Width.setText(Integer.toString(m.tf[m.cnt_TextField].width));	//속성 패널에 width 값 표시
									m.Text_Height.setText(Integer.toString(m.tf[m.cnt_TextField].height));	//속성 패널에 height 값 표시
									if(reverse_x==0 && reverse_y==0)	//x, y 정방향
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x, m.tf[m.cnt_TextField].y, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x, y, width, height)
									}
									else if(reverse_x==1 && reverse_y==0)	//x역방향, y정방향
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x_drag, m.tf[m.cnt_TextField].y, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x_drag, y, width, height)
									}
									else if(reverse_x==0 && reverse_y==1)	//x정방향, y역방향
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x, m.tf[m.cnt_TextField].y_drag, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x, y_drag, width, height)
									}
									else if(reverse_x==1 && reverse_y==1)	//x, y 역방향
									{
										m.tf[m.cnt_TextField].JTextField_Component.setBounds(m.tf[m.cnt_TextField].x_drag, m.tf[m.cnt_TextField].y_drag, m.tf[m.cnt_TextField].width, m.tf[m.cnt_TextField].height);	//setBounds(x_drag, y_drag, width, height)
									}
								}
							}
						});
					}
				}
			}
			else if(e.getActionCommand()=="지우기")	//지우기 버튼 클릭 시
			{
				for(int i=0; i<m.cnt_Label; ++i)
				{
					if(m.la[i].isSelected == true)	//Label이 선택되었을 경우
					{
						Remove_la(m.la[i]);	//삭제
						repaint();	//새로 그림
						for(int j=i; j<m.cnt_Label-1; ++j)	//한칸씩 당겨짐
						{
							m.la[j] = m.la[j+1];
						}
						--m.cnt_Label;	//Label수 -1
						break;
					}
				}
				for(int i=0; i<m.cnt_Button; ++i)
				{
					if(m.bu[i].isSelected == true)	//Button이 선택되었을 경우
					{
						Remove_bu(m.bu[i]);	//삭제
						repaint();	//새로 그림
						for(int j=i; j<m.cnt_Button-1; ++j)	//한칸씩 당겨짐
						{
							m.bu[j] = m.bu[j+1];
						}
						--m.cnt_Button;	//Button수 -1
						break;
					}
				}
				for(int i=0; i<m.cnt_TextField; ++i)
				{
					if(m.tf[i].isSelected == true)	//TextField가 선택되었을 경우
					{
						Remove_tf(m.tf[i]);	//삭제
						repaint();	//새로 그림	
						for(int j=i; j<m.cnt_TextField-1; ++j)	//한칸씩 당겨짐
						{
							m.tf[j] = m.tf[j+1];
						}
						--m.cnt_TextField;	//TextField수 -1
						break;
					}
				}
				m.Text_X.setText("");	//속성 패널 값 초기화
				m.Text_Y.setText("");
				m.Text_Height.setText("");
				m.Text_Width.setText("");
				m.Text_Value.setText("");
				m.Text_Name.setText("");
				m.Combo.setSelectedItem("JPanel");
				
			}
			else if(e.getActionCommand()=="선택/이동")	//선택/이동 버튼 클릭 시
			{
				RemoveMouseListener();	//컴포넌트의 마우스리스너 삭제
				m.Editor_Panel.addMouseListener(listener_p);	//Editor_Panel 마우스리스너 추가
				Select();	//컴포넌트 선택 함수
				Show_Box();	//선택된 컴포넌트의 Box표시
				m.Editor_Panel.repaint();	//새로 그림
			}
		}
	}
	void Make_Non_Selected()	//모든 객체의 isSelected를 false로 만드는 함수
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
	void la_CreateBox(Label la)	//Label의 Box좌표 갱신 함수
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
	void bu_CreateBox(Button bu)	//Button의 Box좌표 갱신 함수
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
	void tf_CreateBox(TextField tf)	//TextField의 Box좌표 갱신 함수
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
	void Show_Box()	//현재 선택되어있는 컴포넌트의 Box를 표시한 후 속성패널에 값 표시
	{
		for(int i=0; i<m.cnt_Label; ++i)
		{
			if(m.la[i].isSelected == false)
			{
				for(int j=0; j<8; ++j)	//선택되지 않은 Label의 Box를 Editor_Panel에서 제거
				{
					m.Editor_Panel.remove(m.la[i].Box[j]);
				}
			}
			else
			{
				for(int j=0; j<8; ++j)	//선택된 Label Box를 Editor_Panel에 표시
				{
					m.Editor_Panel.add(m.la[i].Box[j]);
				}
				m.Text_X.setText(Integer.toString(m.la[i].x));	//속성 패널에 값 표시
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
				for(int j=0; j<8; ++j)	//선택되지 않은 Button의 Box를 Editor_Panel에서 제거
				{
					m.Editor_Panel.remove(m.bu[i].Box[j]);
				}
			}
			else
			{
				for(int j=0; j<8; ++j)	//선택된 Button의 Box를 Editor_Panel에 표시
				{
					m.Editor_Panel.add(m.bu[i].Box[j]);
				}
				m.Text_X.setText(Integer.toString(m.bu[i].x));	//속성 패널에 값 표시
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
			if(m.tf[i].isSelected == false)	//선택되지 않은 TextField의 Box를 Editor_Panel에서 제거
			{
				for(int j=0; j<8; ++j)
				{
					m.Editor_Panel.remove(m.tf[i].Box[j]);
				}
			}
			else
			{
				for(int j=0; j<8; ++j)	//선택된 TextField의 Box를 Editor_Panel에 표시
				{
					m.Editor_Panel.add(m.tf[i].Box[j]);
				}
				m.Text_X.setText(Integer.toString(m.tf[i].x));	//속성 패널에 값 표시
				m.Text_Y.setText(Integer.toString(m.tf[i].y));
				m.Text_Width.setText(Integer.toString(m.tf[i].width));
				m.Text_Height.setText(Integer.toString(m.tf[i].height));
				m.Text_Value.setText(m.tf[i].value);
				m.Combo.setSelectedItem("JTextField");
				m.Text_Name.setText(m.tf[i].name);
			}
		}
	}
	void Select()	//컴포넌트에 마우스리스너를 추가해 위치 이동, 크기 변경을 할 수 있게 함
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
	
	void RemoveMouseListener()	//컴포넌트의 마우스리스너를 삭제하는 함수
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
	class MouseEvent_la implements MouseListener, MouseMotionListener	//Label의 마우스리스너
	{
		int mouseX, mouseY;
		int tmp;
		MouseEvent_la(int num)	//num : 라벨 번호
		{
			tmp = num;
		}
		public void mousePressed(MouseEvent e)	//눌렀을 때
		{
			mouseX = e.getX();	//mouseX에 눌렀을 때 X좌표 저장
			mouseY = e.getY();	//mouseY에 눌렀을 때 Y좌표 저장
			Make_Non_Selected();	//선택 해제
			m.la[tmp].isSelected = true;	//현재 객체의 isSelected를 true로 바꿈
			Show_Box();	//Box표시, 속성패널 표시
			m.Editor_Panel.repaint();	//새로 그림
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//마우스 드래그할 때
		{
			m.la[tmp].x += e.getX() - mouseX;	//현재 마우스 좌표에서 mouseX, mouseY좌표를 뺀 값을 이용해 좌표 갱신
			m.la[tmp].y += e.getY() - mouseY;
			m.la[tmp].x_drag += e.getX() - mouseX;
			m.la[tmp].y_drag += e.getY() - mouseY;
			m.Text_X.setText(Integer.toString(m.la[tmp].x));	//속성 패널 갱신
			m.Text_Y.setText(Integer.toString(m.la[tmp].y));
			la_CreateBox(m.la[tmp]);	//Box좌표 갱신
			m.la[tmp].JLabel_Component.setBounds(m.la[tmp].x, m.la[tmp].y, m.la[tmp].width, m.la[tmp].height);	//컴포넌트 위치 이동
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	class MouseEvent_bu implements MouseListener, MouseMotionListener	//Button의 마우스리스너
	{
		int mouseX, mouseY;
		int tmp;
		MouseEvent_bu(int num)	//num : 버튼 번호
		{
			tmp = num;
		}
		public void mousePressed(MouseEvent e)	//눌렀을 때
		{
			mouseX = e.getX();	//mouseX에 눌렀을 때 X좌표 저장
			mouseY = e.getY();	//mouseY에 눌렀을 때 Y좌표 저장
			Make_Non_Selected();	//선택 해제
			m.bu[tmp].isSelected = true;	//현재 객체만 선택
			Show_Box();	//Box표시 및 속성패널 표시
			m.Editor_Panel.repaint();	//새로 그림
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//마우스 드래그할 때
		{
			m.bu[tmp].x += e.getX() - mouseX;	//현재 마우스 좌표에서 mouseX, mouseY좌표를 뺀 값을 이용해 좌표 갱신
			m.bu[tmp].y += e.getY() - mouseY;
			m.bu[tmp].x_drag += e.getX() - mouseX;
			m.bu[tmp].y_drag += e.getY() - mouseY;
			m.Text_X.setText(Integer.toString(m.bu[tmp].x));	//속성 패널 갱신
			m.Text_Y.setText(Integer.toString(m.bu[tmp].y));
			bu_CreateBox(m.bu[tmp]);	//Box좌표 갱신
			m.bu[tmp].JButton_Component.setBounds(m.bu[tmp].x, m.bu[tmp].y, m.bu[tmp].width, m.bu[tmp].height);	//컴포넌트 위치 이동
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	class MouseEvent_tf implements MouseListener, MouseMotionListener	//TextField의 마우스리스너
	{
		int mouseX, mouseY;
		int tmp;
		MouseEvent_tf(int num)
		{
			tmp = num;
		}
		public void mousePressed(MouseEvent e)
		{
			mouseX = e.getX();	//mouseX에 눌렀을 때 X좌표 저장
			mouseY = e.getY();	//mouseY에 눌렀을 떄 Y좌표 저장
			Make_Non_Selected();	//선택 해제
			m.tf[tmp].isSelected = true;	//현재 객체 선택
			Show_Box();	//Box좌표 갱신 및 속성 패널 표시
			m.Editor_Panel.repaint();	//새로 그림
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)
		{
			m.tf[tmp].x += e.getX() - mouseX;	//현재 마우스 좌표에서 mouseX, mouseY좌표를 뺀 값을 이용해 좌표 갱신
			m.tf[tmp].y += e.getY() - mouseY;
			m.tf[tmp].x_drag += e.getX() - mouseX;
			m.tf[tmp].y_drag += e.getY() - mouseY;
			m.Text_X.setText(Integer.toString(m.tf[tmp].x));	//속성 패널 갱신
			m.Text_Y.setText(Integer.toString(m.tf[tmp].y));
			tf_CreateBox(m.tf[tmp]);	//Box좌표 갱신
			m.tf[tmp].JTextField_Component.setBounds(m.tf[tmp].x, m.tf[tmp].y, m.tf[tmp].width, m.tf[tmp].height);	//컴포넌트 위치 이동
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseMoved(MouseEvent e){}
	}
	BoxListener_la listener_box_la[][] = new BoxListener_la[100][8];
	BoxListener_bu listener_box_bu[][] = new BoxListener_bu[100][8];
	BoxListener_tf listener_box_tf[][] = new BoxListener_tf[100][8];
	class BoxListener_la implements MouseListener, MouseMotionListener	//Label의 Box의 마우스리스너
	{
		int tmp1;	//tmp1 : 몇번째 Label인지 저장
		int tmp2;	//tmp2 : 몇번째 Box인지 저장(0 : 왼쪽 위, 1 : 위, 2 : 오른쪽 위, 3 : 왼쪽, 4 : 오른쪽, 5 : 왼쪽 아래, 6 : 아래, 7 : 오른쪽 아래)
		int mouseX, mouseY;
		BoxListener_la(int num1, int num2)
		{
			tmp1 = num1;
			tmp2 = num2;
		}
		public void mousePressed(MouseEvent e)
		{
			mouseX = e.getX();	//mouseX에 현재 마우스 X좌표 저장
			mouseY = e.getY();	//mouseY에 현재 마우스 Y좌표 저장
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)
		{
			if(tmp2==0)	//왼쪽 위 Box일 떄
			{
				m.la[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.la[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//너비 변경
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//높이 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==1)	//위쪽 Box일 때
			{
				m.la[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//높이 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==2)	//오른쪽 위 Box일 때
			{
				m.la[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.la[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//너피 변경
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//높이 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==3)	//왼쪽 Box일 때
			{
				m.la[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//너비 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==4)	//오른쪽 Box일 때
			{
				m.la[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//너비 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==5)	//왼쪽 아래 Box일 때
			{
				m.la[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.la[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//너비 변경
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//높이 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==6)	//아래쪽 Box일 때
			{
				m.la[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//높이 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==7)	//오른쪽 위 Box일 때
			{
				m.la[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.la[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.la[tmp1].width = m.la[tmp1].x_drag - m.la[tmp1].x;	//너비 변경
				m.la[tmp1].height = m.la[tmp1].y_drag - m.la[tmp1].y;	//높이 변경
				m.la[tmp1].JLabel_Component.setBounds(m.la[tmp1].x, m.la[tmp1].y, m.la[tmp1].width, m.la[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				la_CreateBox(m.la[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림	
			}
			Show_Box();
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e)
		{	//마우스가 Box위에 있을 경우 커서 모양 변경
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
		{	//마우스가 Box밖으로 나갔을 경우 커서 모양 원래대로 변경
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mouseMoved(MouseEvent e){}
	}
	class BoxListener_bu implements MouseListener, MouseMotionListener	//Button의 Box의 마우스리스너
	{
		int tmp1;	//tmp1 : 버튼 번호
		int tmp2;	//tmp2 : Box번호(0 : 왼쪽 위, 1 : 위, 2 : 오른쪽 위, 3 : 왼쪽, 4 : 오른쪽, 5 : 왼쪽 아래, 6 : 아래, 7 : 오른쪽 아래)
		int mouseX, mouseY;
		BoxListener_bu(int num1, int num2)
		{
			tmp1 = num1;
			tmp2 = num2;
		}
		public void mousePressed(MouseEvent e)	//눌렀을 때
		{
			mouseX = e.getX();	//X좌표 저장
			mouseY = e.getY();	//Y좌표 저장
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//드래그할 때
		{
			if(tmp2==0)	//왼쪽 위 Box일 때
			{

				m.bu[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.bu[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//너비 변경
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//높이 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==1)	//위쪽 Box일 때
			{
				m.bu[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//높이 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==2)	//오른쪽 위 Box일 때
			{
				m.bu[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.bu[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//너비 변경
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//높이 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
				
			}
			else if(tmp2==3)	//왼쪽 Box일 때
			{
				m.bu[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//너비 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==4)	//오른쪽 Box일 때
			{
				m.bu[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//너비 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==5)	//왼쪽 아래 Box일 때
			{
				m.bu[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.bu[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//너비 변경
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//높이 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==6)	//아래쪽 Box일 때
			{
				m.bu[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//높이 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			else if(tmp2==7)	//오른쪽 아래 Box일 때
			{
				m.bu[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.bu[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.bu[tmp1].width = m.bu[tmp1].x_drag - m.bu[tmp1].x;	//너비 변경
				m.bu[tmp1].height = m.bu[tmp1].y_drag - m.bu[tmp1].y;	//높이 변경
				m.bu[tmp1].JButton_Component.setBounds(m.bu[tmp1].x, m.bu[tmp1].y, m.bu[tmp1].width, m.bu[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				bu_CreateBox(m.bu[tmp1]);	//Box를 컴포넌트 위치에 맞춰서 새로 그림
			}
			Show_Box();
			
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e)
		{	//마우스가 Box안에 있을 때 커서 변경
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
		{	//마우스가 Box밖으로 나왔을 때 기본커서로 변경
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mouseMoved(MouseEvent e){}
	}
	class BoxListener_tf implements MouseListener, MouseMotionListener	//TextField의 Box의 마우스리스너
	{
		int tmp1;	//tmp1 : TextField의 번호
		int tmp2;	//tmp2 : Box의 번호(0 : 왼쪽 위, 1 : 위, 2 : 오른쪽 위, 3 : 왼쪽, 4 : 오른쪽, 5 : 왼쪽 아래, 6 : 아래, 7 : 오른쪽 아래)
		int mouseX, mouseY;
		BoxListener_tf(int num1, int num2)
		{
			tmp1 = num1;
			tmp2 = num2;
		}
		public void mousePressed(MouseEvent e)	//마우스 눌렀을 때
		{
			mouseX = e.getX();	//현재 X좌표 저장
			mouseY = e.getY();	//현재 Y좌표 저장
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseDragged(MouseEvent e)	//마우스 드래그할 때
		{
			if(tmp2==0)	//왼쪽 위 Box
			{
				m.tf[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.tf[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//너비 변경
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//높이 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==1)	//위쪽 Box
			{
				m.tf[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//높이 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==2)	//오른쪽 위 Box
			{
				m.tf[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.tf[tmp1].y += e.getY() - mouseY;	//y값 변경
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//너비 변경
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//높이 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==3)	//왼쪽 Box
			{
				m.tf[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//너비 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==4)	//오른쪽 Box
			{
				m.tf[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//너비 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==5)	//왼쪽 아래 Box
			{
				m.tf[tmp1].x += e.getX() - mouseX;	//x값 변경
				m.tf[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//너비 변경
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//높이 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==6)	//아래쪽 Box
			{
				m.tf[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//높이 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			else if(tmp2==7)	//오른쪽 아래 Box
			{
				m.tf[tmp1].x_drag += e.getX() - mouseX;	//x_drag값 변경
				m.tf[tmp1].y_drag += e.getY() - mouseY;	//y_drag값 변경
				m.tf[tmp1].width = m.tf[tmp1].x_drag - m.tf[tmp1].x;	//너비 변경
				m.tf[tmp1].height = m.tf[tmp1].y_drag - m.tf[tmp1].y;	//높이 변경
				m.tf[tmp1].JTextField_Component.setBounds(m.tf[tmp1].x, m.tf[tmp1].y, m.tf[tmp1].width, m.tf[tmp1].height);	//변경된 값을 이용해 컴포넌트를 새로 그림
				tf_CreateBox(m.tf[tmp1]);	//Box를 컴포넌트 위치에 따라서 새로 그림
			}
			Show_Box();
			
		}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e)
		{	//마우스가 Box안에 있을때 커서 모양 변경
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
		{	//마우스가 Box밖으로 나왔을 때 기본커서로 변경
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		public void mouseMoved(MouseEvent e){}
	}
	MouseListener_Panel listener_p = new MouseListener_Panel();
	class MouseListener_Panel implements MouseListener	//Editor_Panel의 마우스리스너
	{
		public void mousePressed(MouseEvent e)	//눌렀을 때
		{	//모든 속성 패널을 초기화 한 후 컴포넌트타입의 콤보박스만 JPanel로 만든다
			m.Text_X.setText(null);
			m.Text_Y.setText(null);
			m.Text_Width.setText(null);
			m.Text_Height.setText(null);
			m.Text_Name.setText(null);
			m.Text_Value.setText(null);
			m.Combo.setSelectedItem("JPanel");
			Make_Non_Selected();	//아무것도 선택되지 않게 함
			Show_Box();	//선택된 Box표시(아무것도 선택이 안되있으므로 Box가 표시되지 않음)
			repaint();	//새로 그림
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		
	}
	void Remove_la(Label la)	//Label을 지우는 함수
	{
		m.Editor_Panel.remove(la.JLabel_Component);	//Editor_Panel에서 컴포넌트를 제거함
		for(int i=0; i<8; ++i)	//Box를 없앰
			m.Editor_Panel.remove(la.Box[i]);
		RemoveMouseListener();	//컴포넌트의 마우스리스너를 삭제함
	}
	void Remove_bu(Button bu)	//Button을 지우는 함수
	{
		m.Editor_Panel.remove(bu.JButton_Component);	//Editor_Panel에서 컴포넌트를 제거함
		for(int i=0; i<8; ++i)	//Box를 없앰
			m.Editor_Panel.remove(bu.Box[i]);
		RemoveMouseListener();	//컴포넌트의 마우스리스너를 삭제함
	}
	void Remove_tf(TextField tf)	//TextField를 지우는 함수
	{
		m.Editor_Panel.remove(tf.JTextField_Component);	//Editor_Panel에서 컴포넌트를 제거함
		for(int i=0; i<8; ++i)	//Box를 없앰
			m.Editor_Panel.remove(tf.Box[i]);
		RemoveMouseListener();	//컴포넌트의 마우스리스너를 삭제함
	}
	void New_File()	//새로 만들기 함수
	{
		RemoveMouseListener();	//모든 컴포넌트의 마우스리스너를 삭제
		Make_Non_Selected();	//아무것도 선택되지 않은 상태로 만듬
		for(int i=0 ;i<m.cnt_Label; ++i)	//la객체를 초기화함
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
			m.Editor_Panel.remove(m.la[i].JLabel_Component);	//Editor_Panel에서 컴포넌트와 Box삭제
		}
		for(int i=0 ;i<m.cnt_Button; ++i)	//bu객체를 초기화함
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
			m.Editor_Panel.remove(m.bu[i].JButton_Component);	//Editor_Panel에서 컴포넌트와 Box삭제
		}
		for(int i=0 ;i<m.cnt_TextField; ++i)	//tf객체를 초기화함
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
			m.Editor_Panel.remove(m.tf[i].JTextField_Component);	//Editor_Panel에서 컴포넌트와 Box삭제
		}
		m.cnt_Label = 0;	//Label의 수 0
		m.cnt_Button = 0;	//Button의 수 0
		m.cnt_TextField = 0;	//TextField의 수 0
		tmp = 4;
		repaint();	//새로 그림(아무것도 없는 상태)
	}
	void Label_to_Button(Label la)	//Label의 정보 그대로 Button으로 옮기는 함수
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
	void Label_to_TextField(Label la)	//Label의 정보 그대로 TextField로 옮기는 함수
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
	void Button_to_Label(Button bu)	//Button의 정보 그대로 Label로 옮기는 함수
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
	void Button_to_TextField(Button bu)	//Button의 정보 그대로 TextField로 옮기는 함수
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
	void TextField_to_Label(TextField tf)	//TextField의 정보 그대로 Label로 옮기는 함수
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
	void TextField_to_Button(TextField tf)	//TextField의 정보 그대로 Button으로 옮기는 함수
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
