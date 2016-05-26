import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.Naming; 
import java.rmi.RemoteException;



/**
 * @author william o keeffe
 * 
 * Client Calculator class which implements action listener.
 * 
 *  This class creates the RMI calculator GUI and regulates which buttons have been
 *  pressed on the calculator GUI
 *
 */
public class ClientCalculator implements ActionListener {

	
	private int firstNumber;
	private int secondNumber = -1;
	
	private String operand;
	private String msg = "A number must be entered to procced";
	private static String clientMessage;
	
	
	private JFrame frame;
	private JTextArea dataMsg;
	private JTextArea mainCalculatorScreen;
	
	//flip boolean
	private boolean submit = false;
	static CalculatorInterface calculatorInterface = null;
	
	//Operand Buttons
	JButton divideButton;
	JButton multiplyButton;
	JButton subtractButton;
	JButton addButton;
	
	//Integer Buttons (0-9)
	JButton zeroButton;
	JButton oneButton;
	JButton twoButton;
	JButton threeButton;
	JButton fourButton;
	JButton fiveButton;
	JButton sixButton;
	JButton sevenButton;
	JButton eightButton;
	JButton nineButton;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientCalculator window = new ClientCalculator();
					window.frame.setVisible(true);
					calculatorInterface = (CalculatorInterface)Naming.lookup("rmi://localhost/CalculatorInterface");
					clientMessage = calculatorInterface.messageToClient();
					calculatorInterface.setClientNumber(calculatorInterface.getClientNumber()+1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * initalize the application.
	 */
	public ClientCalculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("RMI Calculator");
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(100, 100, 312, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mainCalculatorScreen = new JTextArea();
		mainCalculatorScreen.setEditable(false);
		mainCalculatorScreen.setFont(new Font("Serif",Font.BOLD, 18));
		mainCalculatorScreen.setBounds(10, 11, 288, 82);
		frame.getContentPane().add(mainCalculatorScreen);
		
		dataMsg = new JTextArea();
		dataMsg.setEditable(false);
		dataMsg.setBounds(10, 270, 288, 80);
		frame.getContentPane().add(dataMsg);
		
		//divide button
		divideButton = new JButton("/");
		divideButton.setBounds(10, 104, 64, 23);
		frame.getContentPane().add(divideButton);
		divideButton.addActionListener(this);
		
		//Create Multiply
		multiplyButton = new JButton("*");
		multiplyButton.setBounds(10, 139, 64, 23);
		frame.getContentPane().add(multiplyButton);
		multiplyButton.addActionListener(this);
		
		//Create Subtract Button
		subtractButton = new JButton("-");
		subtractButton.setBounds(10, 177, 64, 23);
		frame.getContentPane().add(subtractButton);
		subtractButton.addActionListener(this);
		
		//Addition Button
		addButton = new JButton("+");
		addButton.setBounds(10, 211, 64, 23);
		frame.getContentPane().add(addButton);
		addButton.addActionListener(this);

		zeroButton = new JButton("0");
		zeroButton.setBounds(86, 211, 64, 23);
		frame.getContentPane().add(zeroButton);
		zeroButton.addActionListener(this);
		
		//creating integer buttons (0-9)
		oneButton = new JButton("1");
		oneButton.setBackground(new Color(240, 248, 255));
		oneButton.setBounds(86, 177, 64, 23);
		frame.getContentPane().add(oneButton);
		oneButton.addActionListener(this);
		
		twoButton = new JButton("2");
		twoButton.setBounds(162, 177, 64, 23);
		frame.getContentPane().add(twoButton);
		twoButton.addActionListener(this);
		
		threeButton = new JButton("3");
		threeButton.setBounds(232, 177, 64, 23);
		frame.getContentPane().add(threeButton);
		threeButton.addActionListener(this);
		
		fourButton = new JButton("4");
		fourButton.setBounds(84, 139, 64, 23);
		frame.getContentPane().add(fourButton);
		fourButton.addActionListener(this);
		
		fiveButton = new JButton("5");
		fiveButton.setBounds(162, 139, 64, 23);
		frame.getContentPane().add(fiveButton);
		fiveButton.addActionListener(this);
		
		sixButton = new JButton("6");
		sixButton.setBounds(234, 139, 64, 23);
		frame.getContentPane().add(sixButton);
		sixButton.addActionListener(this);

		sevenButton = new JButton("7");
		sevenButton.setBounds(84, 104, 64, 23);
		frame.getContentPane().add(sevenButton);
		sevenButton.addActionListener(this);
		
		eightButton = new JButton("8");
		eightButton.setBounds(162, 104, 64, 23);
		frame.getContentPane().add(eightButton);
		eightButton.addActionListener(this);
	
		nineButton = new JButton("9");
		nineButton.setBounds(234, 104, 64, 23);
		frame.getContentPane().add(nineButton);
		nineButton.addActionListener(this);
		
	
		//Create submit button 
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(164, 210, 128, 52);
		frame.getContentPane().add(submitButton);
		
		//ActionListner and actionPerformed methods to carry out submit button Actions
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataMsg.getText().length()<1){
					firstNumber = 0;
					dataMsg.setText(msg);
				}
				else{
					secondNumber = Integer.parseInt(dataMsg.getText());
					if(secondNumber < 0){
						dataMsg.setText(msg);
					}
					else{
						dataMsg.setText("");
						submit = true;
						submit();
					}
				}
			}
		});	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//divide Operand was pressed perform action
		if(e.getSource() == divideButton){
				
			firstNumber = Integer.parseInt(dataMsg.getText());
			dataMsg.setText("");
			operand = ("/");
			mainCalculatorScreen.append( operand);
		}
		
		// Multiply button pressed perform action
		else if(e.getSource() == multiplyButton){
			
			firstNumber = Integer.parseInt(dataMsg.getText());
			dataMsg.setText("");
			operand = ("*");
			mainCalculatorScreen.append(operand);
			
		}
		
		// subtract button pressed perform action
		else if(e.getSource() == subtractButton) {
			
			firstNumber = Integer.parseInt(dataMsg.getText());
			dataMsg.setText("");
			operand = ("-");
			mainCalculatorScreen.append(operand);
		}
		
		// Addition button pressed perform action
		else if(e.getSource() == addButton) {
			
			firstNumber = Integer.parseInt(dataMsg.getText());
			dataMsg.setText("");
			operand = ("+");
			mainCalculatorScreen.append(operand);
		}
		
		/**
		 * Integer Button pressed
		 */
		//Button number 0 was presses execute actions
		if (e.getSource() == zeroButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.append("");
			
			mainCalculatorScreen.append("0");
			submit = false;
		}
		
		//Button number 1 was presses execute actions
		else if (e.getSource() == oneButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
				
			dataMsg.append("1");
			mainCalculatorScreen.append("1");
			submit = false;
		}
		
		//Button number 2 was presses execute actions
		else if (e.getSource() == twoButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
			
			dataMsg.append("2");
			mainCalculatorScreen.append("2");
			submit = false;
		}
		
		//Button number 3 was presses execute actions
		else if (e.getSource() == threeButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
			
			dataMsg.append("3");
			mainCalculatorScreen.append("3");
			submit = false;
		}
		
		//Button number 4 was presses execute actions
		else if (e.getSource() == fourButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");

			dataMsg.append("4");
			mainCalculatorScreen.append("4");
			submit = false;
		}
		
		//Button number 5 was presses execute actions
		else if (e.getSource() == fiveButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
		
			dataMsg.append("5");
			mainCalculatorScreen.append("5");
			submit = false;
		}
		
		//Button number 6 was presses execute actions
		else if (e.getSource() == sixButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
		
			dataMsg.append("6");
			mainCalculatorScreen.append("6");
			submit = false;
		}
		
		//Button number 7 was presses execute actions
		else if (e.getSource() == sevenButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
		
			dataMsg.append("7");
			mainCalculatorScreen.append("7");
			submit = false;
		}
		
		//Button number 8 was presses execute actions
		else if (e.getSource() == eightButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");

			dataMsg.append("8");
			mainCalculatorScreen.append("8");
			submit = false;
		}
		
		//Button number 9 was presses execute actions
		else if (e.getSource() == nineButton) {
			
			if (dataMsg.getText().equals(msg) || dataMsg.getText().equals(clientMessage)){
				dataMsg.setText("");
			}
			if(submit)
				mainCalculatorScreen.setText("");
		
			dataMsg.append("9");
			mainCalculatorScreen.append("9");
			submit = false;
		}		
	}

	public void submit(){
		try { 
			if(operand.contentEquals("*")){
				calculatorInterface.printInfo(firstNumber, operand, secondNumber);
				calculatorInterface.setOperand(operand);
				calculatorInterface.getOperand();
				mainCalculatorScreen.setText("" + calculatorInterface.multiply(firstNumber, secondNumber));
				dataMsg.setText("" + calculatorInterface.messageToClient());
			}
			if(operand.contentEquals("/")){
				calculatorInterface.printInfo(firstNumber, operand, secondNumber);
				calculatorInterface.setOperand(operand);
				calculatorInterface.getOperand();
				mainCalculatorScreen.setText("" + calculatorInterface.divide(firstNumber, secondNumber));
				dataMsg.setText("" + calculatorInterface.messageToClient());
			}
			if(operand.contentEquals("-")){
				calculatorInterface.printInfo(firstNumber, operand, secondNumber);
				calculatorInterface.setOperand(operand);
				calculatorInterface.getOperand();
				mainCalculatorScreen.setText("" + calculatorInterface.subtract(firstNumber, secondNumber));
				dataMsg.setText("" + calculatorInterface.messageToClient());
			}
			if(operand.contentEquals("+")){
				calculatorInterface.printInfo(firstNumber, operand, secondNumber);
				calculatorInterface.setOperand(operand);
				calculatorInterface.getOperand();
				mainCalculatorScreen.setText("" + calculatorInterface.add(firstNumber, secondNumber));
				dataMsg.setText("" + calculatorInterface.messageToClient());
			}
		} 
		catch (RemoteException re) { 
			System.out.println("RemoteException"); 
			System.out.println(re); 
		}  
		catch (java.lang.ArithmeticException ae) { 
			System.out.println("java.lang.ArithmeticException"); 
			
		}
	}


	/**
	 * @return the firstNumber
	 */
	public int getFirstNumber() {
		return firstNumber;
	}

	/**
	 * @param firstNumber to set
	 */
	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	/**
	 * @return the secondNumber
	 */
	public int getSecondNumber() {
		return secondNumber;
	}

	/**
	 * @param secondNumber to set
	 */
	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

	/**
	 * @return the operand
	 */
	public String getOperand() {
		return operand;
	}

	/**
	 * @param operand to set
	 */
	public void setOperand(String operand) {
		this.operand = operand;
	}
	
}
