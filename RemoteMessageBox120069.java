package ponovo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import rs.ac.bg.etf.kdp.zaki.*;

public class RemoteMessageBox120069<T> implements MessageBox<T> {

	String host;
	int port;

	public RemoteMessageBox120069(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public void send(Message<T> msg, Prioritet prioritet, long ttl) {

		int i=0;
		while(i<3){
			try(Socket socket = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						socket.getInputStream());){
			out.writeObject("send");
			out.writeObject(msg);
			in.readObject();
			break;
			}catch(Exception e){
				i++;
				if(i==3)throw new RuntimeException("Send neuspesan!");
			}
			
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Message<T> receive(Status status, long ttd) {
		int i=0;
		while(i<3){
		Message<T> msg = null;
		try (Socket socket = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						socket.getInputStream());) {

			out.writeObject("receive");
			msg = (Message<T>) in.readObject();
			out.writeObject("OK");
			return msg;
		} catch (Exception e) {
			i++;
			if(i==3)throw new RuntimeException("Receive neuspesan!");
		}
		}
		return null;
	}

}
