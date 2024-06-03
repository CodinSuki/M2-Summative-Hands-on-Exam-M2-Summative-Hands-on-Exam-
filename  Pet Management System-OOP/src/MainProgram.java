import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.time.Month;
import java.time.Year;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class MainProgram extends MyFrame implements ActionListener, MouseListener, KeyListener, WindowListener{
	
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JTable tbl_Pet;
	private DefaultTableModel model_pet;
	private Vector columns, rowData;
	private TableRowSorter tbl_sort;
	private JLabel lblAge;
	private JTextField txtAge;
	private JComboBox cboMonth, cboDay, cboYear;
	private int age;
	
	private int current_year = Year.now().getValue();
	
	private Database db=new Database("F:\\Eclipse\\ Pet Management System-OOP\\pet.txt");
	
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnClose;
	private JLabel lblID,lblName,lblType,lblGender,lblColor,lblBreed,lblPrice;
	private JTextField txtID, txtName, txtColor, txtPrice;
	private JComboBox cboGender, cboType, cboBreed;
	private Font f=new Font("Arial", Font.BOLD, 20); 
	
	
	private JPanel panelPetInfo, panelBirthdate, panelButtons, panelSearch, panelTable;
	
	public MainProgram() {
		
		initializedComponents();
		loadToComboBox();
		petInfo();
		add(panelPetInfo).setBounds(10,10,300,250);
		panelPetBirthdate();
		add(panelBirthdate).setBounds(10,260,300,80);
		panelPetbuttons();
		add(panelButtons).setBounds(40,340,600,30);
		add(panelPetSearch()).setBounds(320,20,300,30);
		add(panelPetTable()).setBounds(320,50,550,290);
		Database db=new Database("F:\\Eclipse\\ Pet Management System-OOP\\pet.txt");
		db.displayRecords(model_pet);
		txtID.setText(getRowCount());
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnClose.addActionListener(this);
		tbl_Pet.addMouseListener(this);
		
		txtSearch.addKeyListener(this);
		txtName.addKeyListener(this);
		txtPrice.addKeyListener(this);
		txtColor.addKeyListener(this);
		txtColor.addMouseListener(this);

		addWindowListener(this);
		setBackgroundColor(235, 210, 52, 100);
		add(setBackgroundImage("F:\\Eclipse\\ Pet Management System-OOP\\IMAGES\\bgImage\\bg1.jpg"));
		setMyFrame("Pet Registration Form", 900, 500,true,DISPOSE_ON_CLOSE,false);
	
		setLocationRelativeTo(null);
		
		db.displayRecords(model_pet);
		resetComponents();
	}
	
	public void initializedComponents() {
		
		lblID=new JLabel("ID: ");
		lblName=new JLabel("Name: ");
		lblType=new JLabel("Type:");
		lblGender=new JLabel("Gender:");
		lblColor=new JLabel("Color:");
		lblBreed=new JLabel("Breed:");
		lblPrice=new JLabel("Price:");
		
		txtID=new JTextField(20);
		txtID.setEditable(false);
		
		txtName=new JTextField(20);
		txtColor=new JTextField(20);
		txtPrice=new JTextField(20);
		
		cboGender=new JComboBox();
		cboType=new JComboBox();
		cboBreed=new JComboBox();
		
		btnAdd=new JButton("Add New", new ImageIcon("F:\\Eclipse\\ Pet Management System-OOP\\IMAGES\\icon\\add_user.png"));
		btnClear=new JButton("Clear", new ImageIcon("F:\\Eclipse\\ Pet Management System-OOP\\IMAGES\\icon\\clear.png"));
		btnUpdate=new JButton("Update", new ImageIcon("F:\\Eclipse\\ Pet Management System-OOP\\IMAGES\\icon\\edit_user.png"));
		btnDelete=new JButton("Delete", new ImageIcon("F:\\Eclipse\\ Pet Management System-OOP\\IMAGES\\icon\\delete_user.png"));
		btnClose=new JButton("Close", new ImageIcon("F:\\Eclipse\\ Pet Management System-OOP\\IMAGES\\icon\\close.png"));
		
	}
	
	public void panelPetbuttons() {
		panelButtons=new JPanel();
		panelButtons.setLayout(new GridLayout(1, 5,4,2));
		panelButtons.add(btnAdd);
		panelButtons.add(btnClear);
		panelButtons.add(new JLabel(""));
		panelButtons.add(btnUpdate);
		panelButtons.add(btnDelete);
		panelButtons.add(btnClose);
	}
	public void petInfo() {
		panelPetInfo=new JPanel();
		panelPetInfo.setBorder(BorderFactory.createTitledBorder("Pet Registration Form"));
		panelPetInfo.setLayout(new GridLayout(7,2));
		panelPetInfo.setFont(f);
		panelPetInfo.setOpaque(false);
	
		panelPetInfo.add(lblID); panelPetInfo.add(txtID);
		panelPetInfo.add(lblName); panelPetInfo.add(txtName);
		panelPetInfo.add(lblGender);panelPetInfo.add(cboGender);
		panelPetInfo.add(lblType);panelPetInfo.add(cboType);
		panelPetInfo.add(lblBreed);panelPetInfo.add(cboBreed);
		panelPetInfo.add(lblColor);panelPetInfo.add(txtColor);
		panelPetInfo.add(lblPrice);panelPetInfo.add(txtPrice);
	}
	
	public void loadToComboBox() {
		cboGender.addItem("Male");
		cboGender.addItem("Female");
		
		db = new Database("F:\\Eclipse\\ Pet Management System-OOP\\Type.txt");
		db.fillToComboBox(cboType);
		db = new Database("F:\\Eclipse\\ Pet Management System-OOP\\Pet.txt");
		db.fillToComboBox(cboBreed);
		
		
	}
	
	public void panelPetBirthdate() {
		panelBirthdate = new JPanel();
		lblAge=new JLabel("Age");
		txtAge=new JTextField("0",5);
		txtAge.setEditable(false);
		txtAge.setToolTipText("Age");
		
		cboMonth=new JComboBox(Month.values());
		cboDay=new JComboBox();
		cboYear=new JComboBox();
		
		panelBirthdate.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
		panelBirthdate.setBorder(BorderFactory.createTitledBorder("Birthdate"));
		panelBirthdate.add(cboMonth);
		panelBirthdate.add(cboDay);
		panelBirthdate.add(cboYear);
		panelBirthdate.add(lblAge);
		panelBirthdate.add(txtAge);
		
		cboYear.addActionListener(this);
		for (int i=1;i<=31;i++) {
			cboDay.addItem(i);
			cboYear.addItem(i+1970);

		}
		cboYear.setEditable(true);
				
	}
	
	public JPanel panelPetSearch() {
		panelSearch=new JPanel();
		lblSearch=new JLabel("Search");
		txtSearch=new JTextField(10);
		panelSearch.add(lblSearch);
		panelSearch.add(txtSearch);
		panelSearch.setOpaque(false);
		return panelSearch;
	}
	
	public JPanel panelPetTable() {
		panelTable=new JPanel();
		tbl_Pet=new JTable();
		model_pet=new DefaultTableModel();
		
		panelTable.setLayout(new BorderLayout());
		panelTable.add(new JScrollPane(tbl_Pet), BorderLayout.CENTER);
		
		String cols[]= {"ID", "Name", "Gender", "Type",
				"Breed","Color","Price","Month","Day","Year","Age"};
		
		columns=new Vector<>();
		for(String val:cols) {
			
			columns.add(val);
		}
		
		model_pet.setColumnIdentifiers(columns);
		tbl_Pet.setModel(model_pet);
		tbl_Pet.setAutoResizeMode(tbl_Pet.AUTO_RESIZE_OFF);
		
		return panelTable;
		
	}

	public String getRowCount() {
		return "10"+model_pet.getRowCount();
		}
	
	public void getData() {
		rowData=new Vector<String>();
		rowData.add(txtID.getText());
		rowData.add(txtName.getText());
		rowData.add(cboGender.getSelectedItem());
		rowData.add(cboType.getSelectedItem());
		rowData.add(cboBreed.getSelectedItem());
		rowData.add(txtColor.getText());
		rowData.add(txtPrice.getText());
		rowData.add(cboMonth.getSelectedItem());
		rowData.add(cboDay.getSelectedItem());
		rowData.add(cboYear.getSelectedItem());
		rowData.add(txtAge.getText());
		
	}

		
		
	
		
	public void resetComponents() {
		//set the components to its default values public void resetComponents() { txtID.setText(getRowCount());
		btnAdd.setEnabled(true);
		btnClear.setEnabled(true);
		btnClose.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		//clear the swing components
		txtName.setText("");
		txtPrice.setText("");
		txtColor.setText("'");
		txtAge.setText("0");
		//setting values by default to first index cboGender.setSelectedIndex(0);
		cboType.setSelectedIndex(0);
		cboBreed.setSelectedIndex(0);
		cboMonth.setSelectedIndex(0);
		cboDay.setSelectedIndex(0);
		cboYear.setSelectedIndex(0);
		//end of method
	}
	public void tableClick() {
		txtID.setText(getRowCount());
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		
	}
	
	public void process() {
		String records = "";
		for(int r=0; r<model_pet.getRowCount();r++) {
			for(int c=0;c<model_pet.getColumnCount();c++) {
				records+=model_pet.getValueAt(r, c)+"#";
			}
			records +="\n";
		}
		db.storeToFile(records);
	}
	public static void main (String[] args) {
		new MainProgram();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(tbl_Pet)) {
			int i = tbl_Pet.getSelectedRow();
			//JOptionPane.showMessageDialog(null, "Row"+i+" is selected.");
	
			txtID.setText(tbl_Pet.getValueAt(i, 0)+"'");
			txtName.setText(tbl_Pet.getValueAt(i, 1)+"");
			cboGender.setSelectedItem(tbl_Pet.getValueAt(i, 2)+"");
			cboType.setSelectedItem(tbl_Pet.getValueAt(i, 3)+"");
			cboBreed.setSelectedItem(tbl_Pet.getValueAt(i, 4)+""); 
			txtColor.setText(tbl_Pet.getValueAt(i, 5)+"");
			txtPrice.setText(tbl_Pet.getValueAt(i, 6)+""); 
			cboMonth.setSelectedItem(tbl_Pet.getValueAt(i, 7)+"'"); 
			cboDay.setSelectedItem(tbl_Pet.getValueAt(i, 8)+"'"); 
			cboYear.setSelectedItem(tbl_Pet.getValueAt(i, 9)+"'"); 
			txtAge.setText(tbl_Pet.getValueAt(i, 10)+"'");
			
			tableClick();
		}else if(e.getSource().equals(txtColor)) {
			Color color = JColorChooser.showDialog(null, "Choose",Color.black);
			txtColor.setBackground(color);
			txtColor.setText("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cboYear)) {
			age=current_year-Integer.parseInt(cboYear.getSelectedItem().toString());
			txtAge.setText(age+"");
			
		}else if(e.getSource().equals(btnAdd)) {
			
			getData();
			model_pet.addRow(rowData);
			resetComponents();
			txtID.setText(getRowCount());
			
			
		}else if(e.getSource().equals(btnClear)) {
			resetComponents();
			
		}else if(e.getSource().equals(btnUpdate)) {
			int i = tbl_Pet.getSelectedRow();
			
			getData();
			for(int col=1;col<tbl_Pet.getColumnCount();col++) {
				tbl_Pet.setValueAt(rowData.get(col),i,col);
			}
				resetComponents();
				
			
			
			
		}else if(e.getSource().equals(btnDelete)) {
			int i = tbl_Pet.getSelectedRow();
			model_pet.removeRow(i);
			resetComponents();
			
		}else if(e.getSource().equals(btnClose)) {
			process();
			System.exit(0);
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if(e.getSource().equals(txtPrice)) {
			if((e.getKeyChar()>='a'&& e.getKeyChar()<='z')) {
				e.consume();
			}
			
		}else if (e.getSource().equals(txtName)|| e.getSource().equals(txtColor)) {
			char ch=e.getKeyChar();
			if(!((Character.isWhitespace(ch) || e.getKeyChar()>='a'||e.getKeyChar()>='A')
				&&(e.getKeyChar()<='z'||e.getKeyChar()<='Z'))) {
				e.consume();	
		}
	}
}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(txtSearch)){
			String search = txtSearch.getText();
			tbl_sort=new TableRowSorter(model_pet);
			tbl_Pet.setRowSorter(tbl_sort);
			tbl_sort.setRowFilter(RowFilter.regexFilter(search,0,1));
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		process();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}