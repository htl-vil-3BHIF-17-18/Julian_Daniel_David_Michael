package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	private static DateHelper instance;
	
	private DateHelper() {
	}
	
	public static DateHelper getInstance() {
		if(DateHelper.instance == null)
			instance = new DateHelper();
		return instance;
	}

	public Date dateformatParse(String date) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String dateFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(date);
	}

}
