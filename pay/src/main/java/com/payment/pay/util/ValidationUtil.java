//******************************************************************
//*** Property of FIS Solutions Pvt Ltd. or its affiliates,***
//*** all rights reserved.                FIS Confidential.\*\*\*
//******************************************************************
package com.payment.pay.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ValidationUtil {
	
	public static boolean isNullBlankEmpty(String inputData) {
		boolean returnedVal = false;
		if((inputData == null)||("".equals(inputData))||(inputData.trim().length()==0))
			returnedVal = true;
		return returnedVal;
	}
	
	/**
	 * 
	 * @param rawString The input string which is to be checked
	 * @return true if rawString is null/blank/empty or contain safe characters,
	 * false otherwise
	 * @throws IOException 
	 */
	public static boolean isStringSafe(final String rawString, String regEx) throws IOException {
		boolean isStringSafe = true;
		if(isNullBlankEmpty(rawString)) {
			isStringSafe = false;
		}
		//Regular expression to check unsafe characters.This regex should be 
		//modified from time to time depending on input range.
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(rawString);
		if(matcher.find()){
			//If an unsafe character is found in the rawString, 
			isStringSafe = false;
		}
		return isStringSafe;
	}

}
