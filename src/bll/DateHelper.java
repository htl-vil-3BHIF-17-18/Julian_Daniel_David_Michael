package bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static Date dateformatParse(String date) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(date);
	}
	
}
