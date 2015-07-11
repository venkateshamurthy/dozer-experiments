package com.github.venkateshamurthy.dozer.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import lombok.extern.java.Log;

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

@Log
public class TestDozerExperiments {

	private static ApplicationContext ctx;
	private static Mapper mapper;
	@Rule public TestWatcher testWatchMan = new TestWatcher() {
		@Override protected void starting(Description description) {
			log.info("Starting .." + description.getMethodName());
		}
	};
	@BeforeClass
	public static void beforeClass() {
		ctx = Utils.getApplicationContext();
		mapper = ctx.getBean("org.dozer.Mapper", Mapper.class);
	}

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

	@Test
	public void testTargetWithFewConstants() {
		SourceHolder sourceHolder = ctx.getBean("source-holder",
				SourceHolder.class);
		TargetHolder targetHolder = mapper.map(sourceHolder,
				TargetHolder.class, "source-to-target-with-few-constants");
		log.info(targetHolder.toString());
	}

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
		assertEquals(source.getPhone(),target.getPhone());
		log.info(targetHolder.getTargets().toString());
	}
}
