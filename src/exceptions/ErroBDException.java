package exceptions;

public class ErroBDException extends Exception{
	private static final long serialVersionUID = -2445927297355732508L;
	
	public ErroBDException(String mensagem) {
		super(mensagem);
	}
	
	public ErroBDException(String mensagem, Throwable causaErro) {
		super(mensagem, causaErro);
	}
}
