
import com.ib.client.*;
import com.ib.contracts.*;

public class theAlgo {
    
    static EWrapper wrapper = new MyWrapper2();
    static EJavaSignal readerSignal = new EJavaSignal();
    static EClientSocket clientSocket = new EClientSocket(wrapper, readerSignal);

    static Contract contract = new Contract();
   
    public static void main(String[] args) throws InterruptedException {

	contract.symbol("IBKR");
        contract.secType("STK");
        contract.currency("USD");
        contract.exchange("ISLAND");
	
	clientSocket.eConnect("127.0.0.1", 7497, 0);
	
	final EReader reader = new EReader(clientSocket, readerSignal);
	reader.start();
	
        
        new Thread(() -> {
            while (clientSocket.isConnected()) {
                readerSignal.waitForSignal();
                try {
                    reader.processMsgs();
                } catch (Exception e) {
                    System.out.println("Exception: "+e.getMessage());
                }
            }
        }).start();

	while (true) {
	     clientSocket.reqMktData(1008, ContractSamples.USStock(), "mdoff,292:MT", false, null);

	    Thread.sleep(10000);
	    
            clientSocket.cancelMktData(1005);

	}
    }
}
