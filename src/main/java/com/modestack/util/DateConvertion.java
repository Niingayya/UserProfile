package com.modestack.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertion {

	public static String dateConvertion(String completionDate1) throws ParseException {
		
	        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        Date date = new Date();
	        date = df.parse(completionDate1);
	        DateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
	       String datee=df1.format(date);
			
	        return datee;

	}

}
