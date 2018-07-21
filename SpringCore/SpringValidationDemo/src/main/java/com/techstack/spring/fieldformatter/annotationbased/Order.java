/**
 * 
 */
package com.techstack.spring.fieldformatter.annotationbased;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;

/**
 * @author KARTHIKEYAN N
 *
 */
public class Order {
	
	@NumberFormat(style = NumberFormat.Style.PERCENT)
	private Double price;

	@DateTimeFormat(iso=ISO.DATE, pattern = "yyyy")
	private Date date;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [price=" + price + ", date=" + date + "]";
	}

}
