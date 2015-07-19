package com.github.venkateshamurthy.dozer.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.dozer.Mapper;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.venkateshamurthy.dozer.converters.examples.Source;
import com.github.venkateshamurthy.dozer.converters.examples.SourceHolder;
import com.github.venkateshamurthy.dozer.converters.examples.Target;
import com.github.venkateshamurthy.dozer.converters.examples.TargetHolder;
import com.github.venkateshamurthy.dozer.converters.examples.TargetListHolder;

/**
 * This class experiments / tests the dozer mapper invocation of various things.
 * 
 * @author vemurthy
 *
 */
@Slf4j
@FieldDefaults(level=AccessLevel.PRIVATE)
public class TestDozerExperiments {
	/** A spring application context that has possession of bean objects , converters to use */
	static ApplicationContext ctx;
	/** A dozer mapper configured in beans configuration file */
	static Mapper mapper;
	/**
	 * A test watcher that just emits when the next test started.. otherwise its
	 * harder to differentiate the output for which test case.
	 */
	@Rule
	public TestWatcher testWatchMan = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			log.info("Starting ..{}", description.getMethodName());
		}
	};

	@BeforeClass
	public static void beforeClass() {
		ctx = Utils.getApplicationContext();
		mapper = ctx.getBean("org.dozer.Mapper", Mapper.class);
	}

	/** test a predefined target substitution */
	@Test
	public void testPreDefinedTargetSubstitution() throws NoSuchFieldException,
			SecurityException {
		SourceHolder sourceHolder = ctx.getBean("source-holder",
				SourceHolder.class);
		TargetHolder targetHolder = mapper.map(sourceHolder,
				TargetHolder.class, "source-to-substitute-target");
		Target expectedTarget = ctx.getBean("pre-defined-target", Target.class);
		assertEquals(expectedTarget, targetHolder.getTarget());
	}

	/** test a field (of type date/int/long etc) for a constant substitution */
	@Test
	public void testTargetWithFewConstants() {
		SourceHolder sourceHolder = ctx.getBean("source-holder",
				SourceHolder.class);
		TargetHolder targetHolder = mapper.map(sourceHolder,
				TargetHolder.class, "source-to-target-with-few-constants");
		log.info(targetHolder.toString());
	}

	/** test a singular element to a destination list type */
	@Test
	public void testTargetList() {
		SourceHolder sourceHolder = ctx.getBean("source-holder",
				SourceHolder.class);
		Source source = sourceHolder.getSource();
		TargetListHolder targetHolder = mapper.map(sourceHolder,
				TargetListHolder.class, "source-to-target-list");
		List<Target> targets = targetHolder.getTargets();
		assertFalse(targets.isEmpty());
		assertTrue(targets.size() == 1);
		Target target = targets.get(0);
		assertEquals(source.getAddress1(), target.getStreet1());
		assertEquals(source.getAddress2(), target.getStreet2());
		assertEquals(source.getPhone(), target.getPhone());
		log.info(targetHolder.getTargets().toString());
	}
}
