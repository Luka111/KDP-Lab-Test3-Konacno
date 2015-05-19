package ponovo;
import rs.ac.bg.etf.kdp.zaki.*;

public class FileMessage implements Message<byte[]> {
	
	byte[] body;
	long id;
	@Override
	public void setId(long id) {
		this.id=id;
		
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setBody(byte[] body) {
		this.body=body;
		
	}

	@Override
	public byte[] getBody() {
		return body;
	}
	
}
