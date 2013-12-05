package org.codehaus.mojo.gwt;

import static org.junit.Assert.fail;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class LogFileGrepTest extends Assert {
	@Test
	public void testPattern() {
		Pattern pattern = Pattern.compile(".*No source code.*");
		assertTrue(pattern.matcher("         Line 9: No source code is available for type com.vedaadvantage.dp3.manager.studiomanager.serviceobjects.PackagedJarFile; did you forget to inherit a required module?").matches());
	}

}
