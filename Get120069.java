package ponovo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import rs.ac.bg.etf.kdp.zaki.*;


public class Get120069 extends JFrame {

	private static final long serialVersionUID = 1L;

	MessageBox<byte[]> buffer;

	public Get120069(MessageBox<byte[]> buffer) {
		super("Get");

		this.buffer = buffer;

		JTextArea jta = new JTextArea();

		JButton jb = new JButton("Get");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jb.setEnabled(false);
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

					@Override
					protected String doInBackground() throws Exception {
						Message<byte[]> msg = buffer.receive(null, 0);
						byte[] body = msg.getBody();
						String text="";
						for(int i=0;i<body.length;i++)
							text+=(char)body[i];
						return text;
					}

					@Override
					protected void done() {
						try {
							String body = get();
							jta.setText(body);
							jb.setEnabled(true);
						} catch (Exception ignore) {
						}
					}

				};
				worker.execute();
				
				/*Thread t = new Thread() {
					public void run() {
						Message<String> msg = buffer.receive(null, 0);
						String body = msg.getBody();
						jta.setText(body); // ??
						jb.setEnabled(true);// ??
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
