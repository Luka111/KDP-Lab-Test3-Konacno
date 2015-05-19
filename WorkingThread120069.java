package ponovo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import rs.ac.bg.etf.kdp.zaki.*;

public class WorkingThread120069 extends Thread {
	Socket socket;
	MessageBox<Object> buffer;
	public WorkingThread120069(Socket socket, MessageBox<Object> buffer) {
		super();
		this.socket = socket;
		this.buffer = buffer;
	}

	@SuppressWarnings("unchecked")
	public void run() {

		try (Socket socket = this.socket;
				ObjectOutputStream out = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						socket.getInputStream());) {

			String opr = (String) in.readObject();
			Message<Object> msg;

			switch (opr) {
			case "send":
				msg = (Message<Object>)in.readObject();
				buffer.send(msg, null, 0);
				out.writeObject("OK");
				break;
			case "receive":
				msg = buffer.receive(null, 0);
				try{
					out.writeObject(msg);
				}catch(Exception e){
					buffer.send(msg, null, 0);
				}
				in.readObject();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
