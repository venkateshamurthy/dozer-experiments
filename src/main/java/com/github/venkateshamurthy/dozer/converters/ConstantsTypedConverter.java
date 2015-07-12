package com.github.venkateshamurthy.dozer.converters;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * A custom dozer converter for converting a string value to a constant of given type
 * 
 * @author vemurthy
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(fluent=true)
@FieldDefaults(level=AccessLevel.PRIVATE,makeFinal=true)
public class ConstantsTypedConverter<T> extends AbstractMapperAwareConverter<String, T> {
	
	/** a date format */
	static DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	
    /** klassT is the constant type converted */
    Class<T> klassT;

    /**
     * Constructor
     * 
     * @param prototypeT class type of &lt;T&gt:
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public ConstantsTypedConverter(final Class<T> prototypeT) throws NoSuchFieldException, SecurityException {
        super(String.class, prototypeT);
        klassT = prototypeT;
    }

    // Method first checks exact type matches and only then checks for
    // assignement
    @Override
    public Object convert(final Object existingDestinationFieldValue, final Object sourceFieldValue,
            final Class<?> destinationClass, final Class<?> sourceClass) {
        if (klassT.equals(String.class)) return getParameter();
        return super.convert(existingDestinationFieldValue, sourceFieldValue, destinationClass, sourceClass);
    }

    /**
     * {@inheritDoc}. In specific this method converts a string to a primitive type.
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Override
    public T convertTo(final String source, final T destination) {
    	
        @NonNull  final String constantValue = getParameter();
        
        switch (klassT.getSimpleName()) {
        case "Date":
        	return (T) dateFormat.parse(constantValue);
        case "Byte":
            return (T) new Byte(constantValue);
        case "Short":
            return (T) new Short(constantValue);
        case "Long":
            return (T) new Long(constantValue);
        case "Integer":
            return (T) new Integer(constantValue);
        case "Float":
            return (T) new Float(constantValue);
        case "Double":
            return (T) new Double(constantValue);
        case "BigInteger":
            return (T) new BigInteger(constantValue);
        case "BigDecimal":
            return (T) new BigDecimal(constantValue);
        case "String":
            return (T) constantValue;
        case "Boolean":
            return (T) new Boolean(constantValue);
        default:
            return null;
        }
    }

    @Override
    public String convertFrom(final T source, final String destination) {
        return source.toString();
    }

}