package com.github.venkateshamurthy.dozer.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import org.dozer.MappingException;

import com.google.common.collect.Lists;

/**
 * A dozer converter for converting an instance of source type S to a List of
 * target type T
 * 
 * @author vemurthy
 * @param <S>
 *            indicates source type of bean to be converted
 * @param <T>
 *            indicates target type to which source is getting mapped to.
 *            However a List&lt;T&gt; is returned during mapping
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SToTListConverter<S, T> extends
		AbstractMapperAwareConverter<S, List<T>> {

	/** klassT is the target type */
	@NonNull
	Class<T> klassT;

	/**
	 * A private constructor
	 * 
	 * @param prototypeS
	 * @param prototypeListT
	 * @param prototypeT
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private SToTListConverter(final Class<S> prototypeS,
			final Class<List<T>> prototypeListT, final Class<T> prototypeT)
			throws NoSuchFieldException, SecurityException {
		super(prototypeS, prototypeListT);
		klassT = prototypeT;
	}

	/**
	 * A public constructor for children classes to use.
	 * 
	 * @param prototypeS
	 *            is the class type S
	 * @param prototypeT
	 *            is the class type T
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public SToTListConverter(final Class<S> prototypeS,
			final Class<T> prototypeT) throws NoSuchFieldException,
			SecurityException {
		this(prototypeS, (Class<List<T>>) Collections.<T> emptyList()
				.getClass(), prototypeT);
	}

	/**
	 * {@inheritDoc}. This method passes a new {@link ArrayList} for the
	 * destination field to the underlying {@link #convertTo(Object, List)}
	 */
	@Override
	public List<T> convertTo(final S source) {
		return convertTo(source, Lists.<T> newArrayList());
	}

	/** {@inheritDoc} */
	@Override
	public S convertFrom(final List<T> destination) {
		return destination == null || destination.isEmpty() ? null
				: morphTToS(destination.iterator().next());
	}

	/**
	 * {@inheritDoc}. This method morphs source bean to a list of destination
	 * type passed.If the passed in list is null a new list is created and used.
	 */
	@Override
	public List<T> convertTo(final S source, List<T> destination) {
		if (destination == null) {
			destination = Lists.<T> newArrayList();
		}
		destination.add(morphSToT(source));
		return destination;
	}

	/**
	 * {@inheritDoc}. This method just directly passes over to underlying
	 * {@link #convertFrom(List)}.
	 */
	@Override
	public S convertFrom(final List<T> destination, final S source) {
		return convertFrom(destination);
	}

	/**
	 * morphSToT assumes a dozer mapping exists between source and target types.
	 * This method can be overridden in case if its otherwise
	 * 
	 * @param source
	 *            type of S to be converted to a target type T
	 * @return an instance of <b>destination</b> type T
	 * @throws MappingException
	 */
	protected T morphSToT(final S source) {
		return mapper.map(source, klassT, getParameter());
	}

	/**
	 * morphTToS assumes a dozer mapping exists between type source and target
	 * types. This method can be overridden in case if its otherwise
	 * 
	 * @param destination
	 *            type of T to be converted to a source type S
	 * @return an instance of type S
	 * @throws MappingException
	 */
	protected S morphTToS(final T destination) {
		return mapper.map(destination, klassS, getParameter());
	}

	public String getParameter() {
		//its always assumed that parameter is not null since a getParameter invoke will check
		String parameter = super.getParameter();
		String[] params = parameter.split(":"); 
		return params.length == 2 && params[0].equalsIgnoreCase("map-id") 
				? params[1]	: null;
	}
}