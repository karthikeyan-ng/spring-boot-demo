/**
 * 
 */
package com.techstack.spring.typeconvertion;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * @author KARTHIKEYAN N
 *
 */
public class MainForConditionalGenericConverterExample {

	public static void main(String[] args) {
		DefaultConversionService service = new DefaultConversionService();
		service.addConverter(new NumberToBigDecimalConverter());

		BigDecimal bd = service.convert(Double.valueOf("2222.336"), BigDecimal.class);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);

		// this will return the same BigDecimal instance without conversion
		bd = service.convert(new BigDecimal("898.33"), BigDecimal.class);
		System.out.println(bd);
	}

	/**
	 * NumberToBigDecimalConverter converts values from Source to Target only
	 * when matches return true. 
	 * 
	 * @author KARTHIKEYAN N
	 *
	 */
	public static class NumberToBigDecimalConverter implements ConditionalGenericConverter {

	    @Override
	    public boolean matches (TypeDescriptor sourceType, TypeDescriptor targetType) {
	        return sourceType.getType() != BigDecimal.class;
	    }

	    @Override
	    public Set<ConvertiblePair> getConvertibleTypes () {
	        return Collections.singleton(new ConvertiblePair(Number.class,
	                            BigDecimal.class));
	    }

	    @Override
	    public Object convert (Object source, TypeDescriptor sourceType,
	                        TypeDescriptor targetType) {

	        Number number = (Number) source;
	        return new BigDecimal(number.doubleValue());
	    }
	}
}
