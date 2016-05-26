

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * @author william o keeffe
 *
 */
public class Server extends UnicastRemoteObject 
implements CalculatorInterface{

	private JFrame frame;
	private static JTextArea textArea;
	
	private static final long serialVersionUID = 1L;

	private int firstNumber;
	private int secondNumber;
	private int clientNumber;
	
	private String operand;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						//LocateRegistry.createRegistry(1099);
						Server csg = new Server();
						//Naming.rebind("rmi://127.0.0.1:1099/CalculatorService", csg);
						Naming.rebind("CalculatorInterface", csg);
						System.out.println("Calculator bound in registry");
					} catch (Exception e) {
						System.out.println("Trouble: " + e);
					}
					Server window = new Server();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws RemoteException 
	 */
	public Server()throws java.rmi.RemoteException { 
		super(); 
		initialize();
	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 310);
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 444, 260);
		textArea.setBackground(new Color(240, 248, 255));
		textArea.setFont(new Font("Serif",Font.BOLD, 18));
		frame.getContentPane().add(textArea);

	}

	public void setText(String in){
		try{
			textArea.setText("Client " + clientNumber + " connected at IP :" + getClientHost() + "\n");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Server down");
		}
		textArea.append(in);
	}

	public long add(long a, long b) throws RemoteException { 
		return a + b; 
	} 

	public long subtract(long a, long b) throws RemoteException { 
		return a - b; 
	} 

	public long multiply(long a, long b) throws RemoteException { 
		return a * b; 
	} 

	public double divide(double a, double b) throws RemoteException { 
		return a / b; 
	}

	/**
	 * @return the firstNumber entered
	 */
	public int getFirstNumber() throws RemoteException{
		return firstNumber;
	}

	/**
	 * @param set firstNumber 
	 */
	public void setFirstNumber(int firstNumber) throws RemoteException{
		this.firstNumber = firstNumber;
	}

	/**
	 * @return the second Number
	 */
	public int getSecondNumber() throws RemoteException{
		return secondNumber;
	}

	/**
	 * @param set second number
	 */
	public void setSecondNumber(int secondNumber) throws RemoteException{
		this.secondNumber = secondNumber;
	}

	/**
	 * @return the operand
	 */
	public String getOperand() throws RemoteException{
		return operand;
	}

	/**
	 * @param oset the operand
	 */
	public void setOperand(String operand) throws RemoteException{
		this.operand = operand;
	}

	
	public String messageToClient(){
		return "Data from server";
	}

	/**
	 * @return the clientNum
	 */
	public int getClientNumber() {
		return clientNumber;
	}

	/**
	 * @param clientNum the clientNum to set
	 */
	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	public void printInfo(int firstNumber, String operand, int secondNumber){
		
		setText("First number :  " + firstNumber + "\n"  + "Second Number :  " + secondNumber + "\n" + "Operand :  " + operand + "\n" 
								   );
}

	
	
}

