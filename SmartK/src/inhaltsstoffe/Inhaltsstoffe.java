package inhaltsstoffe;

import java.io.Serializable;

public class Inhaltsstoffe implements Serializable{
	
	/**
	 * Klasse aus dem Entwurf, kann später zur Implementierungdes Ernährungsmodus genutzt werden
	 */
	private static final long serialVersionUID = 1L;
	private String[] ihst;

	public Inhaltsstoffe(String[] ihst){
		this.ihst = ihst;
		
	}

	/**
	 * @return the ihst
	 */
	public String[] getIhst() {
		return ihst;
	}

	/**
	 * @param ihst the ihst to set
	 */
	public void setIhst(String[] ihst) {
		this.ihst = ihst;
	}
	
	
	
}
