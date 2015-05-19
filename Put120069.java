package ponovo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import rs.ac.bg.etf.kdp.zaki.*;


public class Put120069 extends JFrame {

	private static final long serialVersionUID = 1L;

	MessageBox<byte[]> buffer;

	//MessageBox<Object> buffer;
	public Put120069(MessageBox<byte[]> buffer) {
		super("Put");

		this.buffer = buffer;

		JTextArea jta = new JTextArea();

		JButton jb = new JButton("Put");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jb.setEnabled(false);
				//String body = jta.getText();
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

					@SuppressWarnings("unchecked")
					@Override
					protected Void doInBackground() throws Exception {

						FileInputStream fis=null;
						try{
							String name=jta.getText();
							File file=new File(name);
							byte[] txt=new byte[(int)file.length()];
							fis=new FileInputStream(file);
							fis.read(txt);
							Message<byte[]> msg = new FileMessage();
							msg.setBody(txt);
							buffer.send(msg, null, 0);
						}catch(Exception e){
							jta.setText("Fajl nije pronadjen!");
						}finally{
							fis.close();
						}
						return null;
					}

					@Override
					protected void done() {
						try {
							jb.setEnabled(true);
						} catch (Exception ignore) {
						}
					}

				};
				worker.execute();

				/*Thread t = new Thread() {
					public void run() {
						String body = jta.getText();
						Message<String> msg = new TextMessage();
						msg.setBody(body);
						buffer.send(msg, null, 0);
						jb.setEnabled(true);
					}
				};*/
				// t.start();
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(jta);
		this.add(jb);
	}
}
