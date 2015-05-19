package ponovo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import rs.ac.bg.etf.kdp.zaki.*;


public class Server120069 {

	static ExecutorService executor;
	
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		MessageBox<Object> buffer = (MessageBox<Object>) new ListMessageBox<Object>(2);

		executor=Executors.newFixedThreadPool(5);
		
		try (ServerSocket server = new ServerSocket(port);) {

			while (true) {
				Socket socket = server.accept();
				Future<Void> fut = executor.submit(new Callable<Void>(){
					public Void call(){
						new WorkingThread120069(socket,buffer).run();
						return null;
					}
				});

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
