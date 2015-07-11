package com.github.venkateshamurthy.dozer.converters;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * PreDefinedBeanConverter substitutes a pre-defined target bean defined in spring beans file.
 * 
 * @author vemurthy
 * @param <S> source bean type
 * @param <T> target bean type 
 */

@Data
@EqualsAndHashCode(callSuper=true)
public class PreDefinedBeanConverter<S, T> extends AbstractMapperAwareConverter<S, T> {
    /**
     * Constructor
     * 
     * @param clazzS
     * @param clazzT
     */
    public PreDefinedBeanConverter(final Class<S> clazzS, final Class<T> clazzT) {
        super(clazzS, clazzT);
    }


    /*
     * (non-Javadoc)
     * @see org.dozer.DozerConverter#convertTo(java.lang.Object,
     * java.lang.Object)
     */
    /**
     * {@inheritDoc}.
     */
    @Override
    public T convertTo(final S source, final T destination) {
        return convertTo(source);
    }
    @Override
    public T convertTo(final S source){
        T t= getBean(getParameter());
        return t;
    }

    /*
     * (non-Javadoc)
     * @see org.dozer.DozerConverter#convertFrom(java.lang.Object,
     * java.lang.Object)
     */
    /**
     * {@inheritDoc}.
     */
    @Override
    public S convertFrom(final T source, final S destination) {
        return null;
    }

    /**
     * getBean returns a bean if it is intended else null
     * 
     * @param parameter set on this converter which is expexted to be of the form <i>bean:bean-id</i>
     * @return bean based on parameter value set else null
     */
    protected T getBean(@NonNull final String parameter) {
        String[] nameParts =  parameter.split(":");
        return
        		parameter.startsWith("bean") && nameParts.length == 2
        		? Utils.getApplicationContext().getBean(nameParts[1], klassT)
        		: null;
    }
}