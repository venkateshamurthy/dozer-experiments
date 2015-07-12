
/**
 * This package consists of configuration files to enable dozer custom converters to work and serve as demonstration. <br>
 * this basically helps user in understanding how the bean configuration file and the dozerBeanMapping.xml needs to be setup for getting the custom converters to work 
 * <p>
 * 
 * <pre>
 * Change Log
 * ===============================================================================
 * Date				Description
 * ---------		---------------------------------------------------------------
 * July-2015		Added beans.xml which contain all constant converters and example objects.
 * 					Used bean-default.properties to cut-short the repeating package names in the class field of bean definition
 * 					Used to set system properties(have also added logging.properties) for java.util.Logger to format the log lines to my taste
 * 					Added dozerBeanMapping.xml to capture the example object conversions of different types.
 * </pre>
 * The dozer bean mapping xml needs to be looked at the following specifics to get these custom converters working
 * <br>
 * The custom-converter-param values have the following special prefixes for a purpose:
 * <ll>
 * <li> A value for such as custom-converter-param=<b><i>bean:bean-name</i></b> indicates that the 
 * 		target object will be assigned directly from a bean whose id is <i>bean-name</i> from the bean configuration file. 
 * 		This is used for complete substitution of target fields. 
 * 		Please refer to map-id="source-to-substitute-target" in the dozerBeanMapping.xml for completeness.
 * <li> A value for such as custom-converter-id="to-date" indicates that the
 * 		field of a class will be assigned a constant (date in this case) object.
 * <li> A value for such as custom-converter-param="map-id:source-to-target" indicates that the
 *      singular element type to be mapped to a list of destination target type; 
 *      it is imperative a mapping between singular elements of source and target mapping must alread exists
 * </ll>
				
 */
