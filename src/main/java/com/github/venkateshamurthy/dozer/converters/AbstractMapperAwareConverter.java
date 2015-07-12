/**
 * 
 */
package com.github.venkateshamurthy.dozer.converters;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

/**
 * This is a helpful abstraction for mapper aware dozer converters.
 * @author vemurthy
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class AbstractMapperAwareConverter<S, T> extends
		DozerConverter<S, T> implements MapperAware {

	/** mapper represents dozer bean mapper instance */
	@NonFinal
	@NonNull
	Mapper mapper;

	/** klassS is the source type bean to be converted */
	@NonNull
	
	Class<S> klassS;

	/** klassT is the target bean type desired */
	@NonNull
	Class<T> klassT;

	/**
	 * Constructor
	 * @param prototypeS
	 * @param prototypeT
	 */
	public AbstractMapperAwareConverter(Class<S> prototypeS, Class<T> prototypeT) {
		super(prototypeS, prototypeT);
		klassS = prototypeS;
		klassT = prototypeT;
	}
}
