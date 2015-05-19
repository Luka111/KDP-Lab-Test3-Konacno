package ponovo;
import rs.ac.bg.etf.kdp.zaki.*;


public class Test120069 {

	public static void main(String[] args) {
		// MessageBox<String> buffer = new ListMessageBox<String>(2);
		// MessageBox<String> buffer = new LockMessageBox<String>(2);
		MessageBox<byte[]> buffer = new RemoteMessageBox120069<byte[]>(args[0],
				Integer.parseInt(args[1]));

		Put120069 put = new Put120069(buffer);
		Get120069 get = new Get120069(buffer);
		put.setVisible(true);
		get.setVisible(true);
	}

}
