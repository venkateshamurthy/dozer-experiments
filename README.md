# dozer-experiments
Some experiments in using dozer custom converters along with spring bean based declarations.

## Description
This project constitutes a set of dozer based special purpose custom converters for the following use cases. The source class and target class are the 
mappable entities which the [Dozer](http://dozer.sourceforge.net "Dozer") [Mapper](http://dozer.sourceforge.net/apidocs/org/dozer/Mapper.html "mapper") will invoke to get the mapping done. 

1.  A field of target class to be substituted by a type specific constant (mostly the primitives and date). Here the particular target field is simply substituted with a constant.
2.  A field of target class to be substituted by a pre-defined bean in the spring beans xml.Here the particular target field is a complex custom type which is a bean defined with appropriate values for its attributes. 
3.  A target field which is of a java collection type (for now it only List type is supported). Here the source bean needs to be converted to a target type and further added as an element into a collection of the target type. 


## Custom converters
The different converters considered here are as follows:

### Primitive converters:

The date and other primitive types are a set of simple converters where a _custom-converter-param_ contains a value in string form. The _custom-converter-id_ contains the type specific converter name of the form such as 

	*   to-date   for converting a string value to date
	*   to-int    for converting a string value to int
	*   to-long   for converting a string value to long
	*   to-short  for converting a string value to short
	*   to-byte   for converting a string value to byte
	*   to-string for converting a string value to another string
	
	An example :

	<mapping map-id="source-to-target-with-few-constants">
			<class-a>com.github.venkateshamurthy.dozer.converters.examples.SourceHolder</class-a>
			<class-b>com.github.venkateshamurthy.dozer.converters.examples.TargetHolder</class-b>
			<field map-id="source-to-target"><a>source</a><b>target</b></field>
			<!-- the custom-converter-ids are primitive converters but valueas are strings to be converted -->
			<field custom-converter-id="to-date" custom-converter-param="1947-08-15"><a>dob</a><b>dob</b></field>
			<field custom-converter-id="to-int" custom-converter-param="24"><a>age</a><b>age</b></field>
			<field custom-converter-id="to-double" custom-converter-param="500000.0"><a>salary</a><b>salary</b></field>
			<field custom-converter-id="to-long" custom-converter-param="100300500700"><a>uan</a><b>uan</b></field>
	</mapping>


### Pre-defined bean substitution

Some of the target fields (of complex types) in a class might need a direct bean substitution. This is a convenient way rather than populating the constant values in dozer bean xml file.

	*   source-to-target-pre-defined: for a direct substitution of a target bean that is pre defined in beans.xml

	An Example:

		<!-- the converters are defined in beans.xml. please refer for their definitions before reading this xml -->
		<mapping map-id="source-to-substitute-target">
			<class-a>com.github.venkateshamurthy.dozer.converters.examples.SourceHolder</class-a>
			<class-b>com.github.venkateshamurthy.dozer.converters.examples.TargetHolder</class-b>
			<!-- the custom-converter-param values are prefixed here with bean:bean-name as the target bean for substituting must be in beans.xml -->
			<field  custom-converter-id="source-to-target-pre-defined"	custom-converter-param="bean:pre-defined-target">
				<a>source</a>	<b>target</b>
			</field>
		</mapping>
	
### Source to target list

	*   source-to-target-list: for converting a source to a list of target type containing singleton target element
	
	An Example:
	
		<mapping map-id="source-to-target-list" >		
			<class-a>com.github.venkateshamurthy.dozer.converters.examples.SourceHolder</class-a>
			<class-b>com.github.venkateshamurthy.dozer.converters.examples.TargetListHolder</class-b>
			<!-- the converter is source to a target list and custom-converter-param values are prefixed here with map-id: as the singular element mapping is required in dozer bean mapping definition -->
			<field  custom-converter-param="map-id:source-to-target" custom-converter-id="source-to-target-list">
					<a>source</a>	<b>targets</b>
			</field>
		</mapping>