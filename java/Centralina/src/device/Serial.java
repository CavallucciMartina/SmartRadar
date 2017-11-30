package device;

public interface Serial {
	
	/* async interface */
	boolean isMsgAvailable();
	String 	readMsg();
	void	sendMsg(String msg);
	
	/* sync interface */
	String 	waitForMsg() throws InterruptedException;
}
