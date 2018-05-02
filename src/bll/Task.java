package bll;

import java.util.Date;

import gui.MainFrame;

public class Task {

	public enum FAECHER {
		AM, D, RK, POS, E, BWM_2, BWM_RW, DBI, BSPK, TINF, GGP_GEO, GGP_GPB, SYP, NW_CH
	}

	public enum STATUS {
		OFFEN, ERLEDIGT, VERGESSEN, NICHTGESCHAFT
	}

	private FAECHER fach;
	private String aufgabe;
	private Date bisDatum;
	private STATUS status;

	public Task(FAECHER fach, String aufgabe, Date bisDatum, STATUS status) {
		this.fach = fach;
		this.aufgabe = aufgabe;
		this.bisDatum = bisDatum;
		this.status = status;
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

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public String toString() {
		return fach.toString() + ", " + aufgabe + ", " + MainFrame.dateFormat(bisDatum) + " Status: "
				+ status.toString();
	}

}
