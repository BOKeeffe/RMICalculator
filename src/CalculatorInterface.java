import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author william o keeffe
 *
 */
public interface CalculatorInterface extends Remote {
	
	
	public long add(long a, long b) throws RemoteException; 

	public long subtract(long a, long b) throws RemoteException; 

	public long multiply(long a, long b)  throws RemoteException; 

	public double divide(double a, double b) throws RemoteException;
	
	public void setFirstNumber(int first) throws RemoteException;
	
	public void setSecondNumber(int second) throws RemoteException;
	
	public void setOperand(String op) throws RemoteException;
	
	public int getFirstNumber() throws RemoteException;
	
	public String getOperand() throws RemoteException;
	
	public void printInfo(int firstNum, String operand, int secNum) throws RemoteException;
	
	public String messageToClient() throws RemoteException;
	
	public void setClientNumber(int clientNum) throws RemoteException;
	
	public int getClientNumber() throws RemoteException;
	
	
}
