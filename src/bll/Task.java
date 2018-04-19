package bll;

import java.util.Date;

public class Task {

	public enum FAECHER {
		AM, RK, POS, E, BWM_2, BWM_RW, DBI, BSPK, TINF, GGP_GEO, GGP_GPB, SYP, NW_CH
	}
	
	private FAECHER fach;
	private String aufgabe;
	private Date bisDatum;
	
	public Task(FAECHER fach, String aufgabe, Date bisDatum) {
		this.fach = fach;
		this.aufgabe = aufgabe;
		this.bisDatum = bisDatum;
	}

	public FAECHER getFach() {
		return fach;
	}

	public void setFach(FAECHER fach) {
		this.fach = fach;
	}

	public String getAufgabe() {
		return aufgabe;
	}

	public void setAufgabe(String aufgabe) {
		this.aufgabe = aufgabe;
	}

	public Date getBisDatum() {
		return bisDatum;
	}

	public void setBisDatum(Date bisDatum) {
		this.bisDatum = bisDatum;
	}
	
}
