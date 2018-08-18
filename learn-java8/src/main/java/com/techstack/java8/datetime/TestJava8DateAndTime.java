/**
 * 
 */
package com.techstack.java8.datetime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * @author KARTHIKEYAN N
 *
 */
public class TestJava8DateAndTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		work_WithLocalDate();
		work_withLocalTime();
		work_withLocalDateAndTime();
		using_ZonedDateTime();
		wrok_WithPeriods();
		work_WithDuration();
		work_WithCalendarAndDate();
		format_LocalDateAndTime();
		using_DateTimeFormatterBuilder();
	}

	/**
	 * - The LocalDate represents a date in ISO format (yyyy-MM-dd) without time.
	 * 
	 */
	private static void work_WithLocalDate() {
		
		// An instance of current date can be created from the system clock as below:
		LocalDate localDate = LocalDate.now();
		System.out.println("Simple Local Date : " + localDate);	//==> yyyy-MM-dd
		
		/**
		 * The LocalDate representing a specific day, month and year can be obtained
		 * using the "of" method or by using the "parse" method. For example the below
		 * code snippets represents the LocalDate for 20 February 2015:
		 * 
		 * Month and Date value can be given in 01 or 1 upto 9.
		 */
		localDate = LocalDate.of(2015, 1, 1);
		System.out.println("How to use 'of' method in LocalDate : " + localDate);
		
		// Month and Date value should be given in 01 upto 09.
		localDate = LocalDate.parse("2015-02-20");	//
		System.out.println("How to parse string date : " + localDate);
		
		/**
		 * The LocalDate provides various utility methods to obtain a variety of
		 * information. Let’s have a quick peek at some of these APIs methods.
		 * 
		 * - "PLUS" functionality
		 * plusDays
		 * plusMonths
		 * plusWeeks
		 * plusYears
		 * 
		 * The following code snippet gets the current local date and adds one day:
		 */
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		System.out.println("How to plys days : " + tomorrow);
		
		/**
		 * This example obtains the current date and subtracts one month. Note how it accepts an enum as the time unit:
		 * 
		 * - "MINUS" functionality
		 * minusDays
		 * minusMonths
		 * minusWeeks
		 * minusYears 
		 * 
		 */
		LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		System.out.println("How to use minus functionality in LocalDate : " + previousMonthSameDay);

		/**
		 * In the following two code examples we parse the date "2016-06-12" and get the
		 * day of the week and the day of the month respectively. Note the return
		 * values, the first is an object representing the DayOfWeek while the second in
		 * an int representing the ordinal value of the month:
		 */
		DayOfWeek dayOfWeek = LocalDate.parse("2018-08-16").getDayOfWeek();
		System.out.println("How to get the Day of Week : " + dayOfWeek);
		
		int dayOfMonth = LocalDate.parse("2018-08-16").getDayOfMonth();
		System.out.println("How to get Day of Month : " + dayOfMonth);
		
		/**
		 * We can test if a date occurs in a leap year. In this example we test if the current date occurs is a leap year:
		 */
		boolean isLeapYear = LocalDate.now().isLeapYear();
		System.out.println("How to check the year is leap year? " + isLeapYear);
		
		isLeapYear = LocalDate.parse("2016-08-16").isLeapYear();
		System.out.println("How to check the year is leap year? " + isLeapYear);
		
		/**
		 * The relationship of a date to another can be determined to occur before or after another date:
		 * 
		 * - "IS" functionality
		 * isAfter
		 * isBefore
		 * isEqual
		 * isLeapYear
		 * isSupported
		 */
		boolean isBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
		System.out.println("Is given date come before another date? " + isBefore);
		
		boolean isAfter = LocalDate.parse("2019-06-12").isAfter(LocalDate.parse("2016-06-11"));
		System.out.println("Is given date come after another date? " + isAfter);
		
		boolean isEqual = LocalDate.parse("2016-08-16").isEqual(LocalDate.parse("2016-08-16"));
		System.out.println("Is given date is equal with another date? " + isEqual);
		
		/**
		 * Date boundaries can be obtained from a given date. In the following two
		 * examples we get the LocalDateTime that represents the beginning of the day
		 * (2016-06-12T00:00) of the given date and the LocalDate that represents the
		 * beginning of the month (2016-06-01) respectively:
		 */
		LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
		System.out.println("At start of Day : " + beginningOfDay);
		
		LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
		System.out.println("How to get the first day of month for the given date? " + firstDayOfMonth);
	}
	
	/**
	 * The LocalTime represents time without a date.
	 * 
	 * Similar to LocalDate an instance of LocalTime can be created from system
	 * clock or by using "parse" and "of" method. Quick look at some of the commonly
	 * used APIs below.
	 */
	private static void work_withLocalTime() {
		
		// An instance of current LocalTime can be created from the system clock as below:
		LocalTime now = LocalTime.now();
		System.out.println("What is the thime now? " + now); //==>hh:mm:ss.nanosec
		
		/**
		 * In the below code sample, we create a LocalTime representing 06:30 AM by parsing a string representation:
		 * FORMAT: hh:mm:ss.millisec
		 * hh : 0-23
		 * mm : 0-59
		 * ss : 0-59
		 * nanosec: 0-999_999_999
		 */
		LocalTime sixThirty = LocalTime.parse("00:30");
		System.out.println("Using parse method : " + sixThirty);
		
		/**
		 * The Factory method "of" can be used to create a LocalTime. For example the
		 * below code creates LocalTime representing 06:30 AM using the factory method:
		 */
		sixThirty = LocalTime.of(6, 30);
		System.out.println("Using factory method 'of' : " + sixThirty);
		
		/**
		 * The below example creates a LocalTime by parsing a string and adds an hour to
		 * it by using the "plus" API. The result would be LocalTime representing 07:30
		 * AM:
		 * 
		 * --PLUS functionality
		 * 	plus
		 *  plusHours
		 *  plusMinutes
		 *  plusSeconds
		 *  plusNanos
		 */
		LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
		System.out.println("How to plus some hours for the given time? " + sevenThirty);
		
		/**
		 * Various getter methods are available which can be used to get specific units
		 * of time like hour, min and secs like below:
		 * 
		 * -- GET functionality
		 *  get
		 *  getHour
		 *  getMinute
		 *  getSecond
		 *  getNano
		 *  getLong
		 */
		int six = LocalTime.parse("06:30").getHour();
		System.out.println("Extracted Hour value for the given time : " + six);
		
		/**
		 * We can also check if a specific time is before or after another specific
		 * time. The below code sample compares two LocalTime for which the result would
		 * be true:
		 * 
		 * -- IS functionality
		 *  isAfter
		 *  isBefore
		 *  isSupported
		 */
		boolean isBefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
		System.out.println("Is given time come before another time? " + isBefore);
		
		boolean isAfter = LocalTime.parse("07:30").isAfter(LocalTime.parse("06:30"));
		System.out.println("Is given time come after another time? " + isAfter);
		
		/**
		 * The max, min and noon time of a day can be obtained by constants in LocalTime
		 * class. This is very useful when performing database queries to find records
		 * within a given span of time. For example, the below code represents
		 * 23:59:59.99:
		 */
		LocalTime maxTime = LocalTime.MAX;
		System.out.println("Maximum Time : " + maxTime);
		
		LocalTime minTime = LocalTime.MIN;
		System.out.println("Minimum Time : " + minTime);
		
		LocalTime midNight = LocalTime.MIDNIGHT;
		System.out.println("Midnight Time " + midNight);

	}
	
	/**
	 * The LocalDateTime is used to represent a combination of date and time.
	 * 
	 * This is the most commonly used class when we need a combination of date and
	 * time. The class offers a variety of APIs and we will look at some of the most
	 * commonly used ones.
	 */
	private static void work_withLocalDateAndTime() {
		
		// An instance of LocalDateTime can be obtained from the system clock similar to LocalDate and LocalTime:
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Current Date and Time is : " + now);
		
		/**
		 * The below code samples explain how to create an instance using the factory
		 * "of" and "parse" methods. The result would be a LocalDateTime instance
		 * representing 20 February 2015, 06:30 AM:
		 */
		LocalDateTime usingOf = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		System.out.println("Created Date and Time using 'of' factory method : " + usingOf);
		
		LocalDateTime usingParse = LocalDateTime.parse("2015-02-20T06:30:00");
		System.out.println("Created Date and Time using 'parse' method : " + usingParse);
		
		/**
		 * There are utility APIs to support addition and subtraction of specific units
		 * of time like days, months, year and minutes are available. The below code
		 * samples demonstrates the usage of "plus" and "minus" methods. These APIs
		 * behave exactly like their counterparts in LocalDate and LocalTime:
		 */
		System.out.println("Incremnt 1 day from current date and time : " + now.plusDays(1));
		System.out.println("Minus 2 hours from current date and time : " + now.minusHours(2));
		
		/**
		 * Getter methods are available to extract specific units similar to the date
		 * and time classes. Given the above instance of LocalDateTime, the below code
		 * sample will return the month current Month:
		 */
		System.out.println("Get the Month value from current Date and Time : " + now.getMonth());
	}
	
	/**
	 * Java 8 provides ZonedDateTime when we need to deal with time zone specific
	 * date and time. The ZoneId is an identifier used to represent different zones.
	 * There are about 40 different time zones and the ZoneId are used to represent
	 * them as follows.
	 */
	private static void using_ZonedDateTime() {
		
		// In this code snippet we create a Zone for Paris:
		ZoneId zoneId = ZoneId.of("Europe/Paris");
		System.out.println("How to create a ZoneId from factory method 'of' : " + zoneId);
		
		// A set of all zone ids can be obtained as below:
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println("All available Zone Ids : " + allZoneIds);
		
		// The LocalDateTime can be converted to a specific zone:
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
		System.out.println("Convert : " + zonedDateTime);
		
		// The ZonedDateTime provides parse method to get time zone specific date time:
		zonedDateTime = ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Europe/Paris]");
		System.out.println("Convert string based ZonedDateTime to object : " + zonedDateTime);
		
		/**
		 * Another way to work with time zone is by using OffsetDateTime. The
		 * OffsetDateTime is an immutable representation of a date-time with an offset.
		 * This class stores all date and time fields, to a precision of nanoseconds, as
		 * well as the offset from UTC/Greenwich.
		 * 
		 * The OffSetDateTime instance can be created as below using ZoneOffset. Here we
		 * create a LocalDateTime representing 6:30 am on 20th February 2015:
		 */
		LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		
		// Then we add two hours to the time by creating a ZoneOffset and setting for the localDateTime instance:
		ZoneOffset offset = ZoneOffset.of("+02:00");
		OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime, offset);
		System.out.println("OffsetDateTime info : " + offSetByTwo);
	}
	
	/**
	 * Using Period and Duration
	 * 
	 * The Period class represents a quantity of time in terms of years, months and days.
	 * The Duration class represents a quantity of time in terms of seconds and nano seconds.
	 */
	private static void wrok_WithPeriods() {
		
		// The Period class is widely used to modify values of given a date or to obtain the difference between two dates:
		LocalDate initialDate = LocalDate.parse("2007-05-10");
		System.out.println("Initial Date : " + initialDate);
		
		// The Date can be manipulated using Period as shown in the following code snippet:
		LocalDate finalDate = initialDate.plus(Period.ofDays(5));
		System.out.println("Final Date : " + finalDate);
		
		// The Period class has various getter methods such as getYears, getMonths and
		// getDays to get values from a Period object. The below code example returns an
		// int value of 5 as we try to get difference in terms of days:
		int five = Period.between(initialDate, finalDate).getDays();
		System.out.println("Days different between two dates : " + five);
		
		// The Period between two dates can be obtained in a specific unit such as days
		// or month or years, using ChronoUnit.between:
		long daysBetween = ChronoUnit.DAYS.between(initialDate , finalDate);
		System.out.println("Days different between two dates using ChronoUnit : " + daysBetween);
	}
	
	/**
	 * Similar to Period, the Duration class is use to deal with Time. In the
	 * following code we create a LocalTime of 6:30 am and then add a duration of 30
	 * seconds to make a LocalTime of 06:30:30am:
	 */
	private static void work_WithDuration() {
		LocalTime initialTime = LocalTime.of(6, 30, 0);
		LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
		
		// The Duration between two instants can be obtained either as a Duration or as
		// a specific unit. In the first code snippet we use the between() method of the
		// Duration class to find the time difference between finalTime and initialTime
		// and return the difference in seconds:
		long thirty = Duration.between(initialTime, finalTime).getSeconds();
		System.out.println("Difference between two times in seconds : " + thirty);
		
		// In the second example we use the between() method of the ChronoUnit class to
		// perform the same operation:
		thirty = ChronoUnit.SECONDS.between(initialTime, finalTime);
		System.out.println("Difference between two times using ChronoUnit : " + thirty);
	}
	
	/**
	 * Compatibility with Date and Calendar
	 * 
	 * how to convert existing Date and Calendar to new Date/Time. 
	 * 
	 * Java 8 has added the toInstant() method which helps to convert existing Date and Calendar
	 * instance to new Date Time API as in the following code snippet:
	 * 
	 */
	private static void work_WithCalendarAndDate() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		LocalDateTime now = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		System.out.println("Convert java.util.Date to java.time.LocalDateTime : " + now);
		
		now  = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
		System.out.println("Convert java.util.Calendar to java.time.LocalDateTime : " + now);
		
		// The LocalDateTime can be constructed from epoch seconds as below. The result
		// of the below code would be a LocalDateTime representing 2016-06-13T11:34:50:
		now = LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);
		System.out.println("Usage of epoch " + now);
	}
	
	/**
	 * Date and Time Formatting
	 * 
	 * Java 8 provides APIs for the easy formatting of Date and Time:
	 * 
	 */
	private static void format_LocalDateAndTime() {
		
		LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);
		
		// The below code passes an ISO date format to format the local date. The result would be 2015-01-25 :
		String localDateString = localDateTime.format(DateTimeFormatter.ISO_DATE);
		System.out.println(localDateString);
		
		// The DateTimeFormatter provides various standard formatting options. Custom
		// patterns can be provided to format method as well, like below, which would
		// return a LocalDate as 2015/01/25:
		String formattedValue = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		System.out.println(formattedValue);
		
		// We can pass in formatting style either as SHORT, LONG or MEDIUM as part of
		// the formatting option. The below code sample would give an output
		// representing LocalDateTime in 25-Jan-2015 06:30:00:
		String formattedDate = localDateTime
									  .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
									  .withLocale(Locale.UK));
		System.out.println(formattedDate);
	}
	
	/**
	 * How to create custom DateTimeFormatterBuilder to format a given 
	 * LocalDateTime object
	 */
	private static void using_DateTimeFormatterBuilder() {
		final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			    .parseCaseInsensitive()
			    .appendValue(ChronoField.YEAR)
			    .appendLiteral('-')
			    .appendValue(ChronoField.MONTH_OF_YEAR)
			    .appendLiteral('-')
			    .appendValue(ChronoField.DAY_OF_MONTH)
			    .optionalStart()
			        .appendLiteral(' ')
			        .appendValue(ChronoField.HOUR_OF_DAY)
			        .appendLiteral(':')
			        .appendValue(ChronoField.MINUTE_OF_HOUR)
			        .appendLiteral(':')
			        .appendValue(ChronoField.SECOND_OF_MINUTE)
			    .optionalEnd()
			    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			    .toFormatter();
		
		System.out.println("Using DateTimeFormatterBuilder : " + LocalDateTime.now().format(formatter));
	}
	
}
