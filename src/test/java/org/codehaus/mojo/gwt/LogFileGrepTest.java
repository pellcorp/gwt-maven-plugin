package org.codehaus.mojo.gwt;

import static org.junit.Assert.fail;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class LogFileGrepTest extends Assert {
	@Test
	public void testSourceCodePattern() {
		Pattern pattern = Pattern.compile(".*No source code.*");
		assertTrue(pattern.matcher("         Line 9: No source code is available for type com.vedaadvantage.dp3.manager.studiomanager.serviceobjects.PackagedJarFile; did you forget to inherit a required module?").matches());
	}

	@Test
	public void testCompilePattern() {
		String[] matchOptions = new String[] {
				" Compiling module com.vedaadvantage.dp3.manager.studiomanager.webadmin.WebAdmin",
				"    Compiling 6 permutations",
				"       Compiling permutation 1...",
				"       Process output",
				"    Compile of permutations succeeded",
				"Linking into /home/pellcorp/Development/Work/dp3/Manager/studio-manager-webadmin/target/webadmin",
				"    Link succeeded",
				"    Compilation succeeded -- 53.456s"
		};
		
		String[] noMatchOptions = new String[] {
				"                     Loading inherited module 'com.google.gwt.emul.EmulationWithUserAgent'",
				"                     Module location: jar:file:/home/pellcorp/.m2/repository/com/google/gwt/gwt-dev/2.5.1/gwt-dev-2.5.1.jar!/com/google/gwt/lang/LongLib.gwt.xml",
		};
		
		Pattern pattern = Pattern.compile(".*Compil.*|.*Process.*|.*Link.*");
		
		for (String match : matchOptions) {
			assertTrue(match + " not matched", pattern.matcher(match).matches());
		}
		
		for (String match : noMatchOptions) {
			assertFalse(match + " matched", pattern.matcher(match).matches());
		}
	}
}
