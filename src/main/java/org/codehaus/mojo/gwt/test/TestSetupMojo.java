package org.codehaus.mojo.gwt.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.mojo.gwt.shell.AbstractGwtShellMojo;

/**
* @goal test-setup
* @phase test-compile
* @requiresDependencyResolution test
*/
public class TestSetupMojo extends AbstractGwtShellMojo {
	@Override
	public void doExecute() throws MojoExecutionException, MojoFailureException {
		List<Artifact> artifactList = new ArrayList<Artifact>();
    	addCompileSourceArtifacts(artifactList);
    	
    	List<String> additionalClasspathList = new ArrayList<String>();
    	for (Artifact source : artifactList) {
    		additionalClasspathList.add(source.getFile().getAbsolutePath());
    	}
    	
    	String[] additionalClasspathElements = additionalClasspathList.toArray(new String[0]);
    	if (getLog().isDebugEnabled()) {
	    	for (String additionalClasspath : additionalClasspathElements) {
	    		getLog().debug("Additional Classpath: " + additionalClasspath);
	    	}
    	}
    	
    	getProject().getProperties().put("maven.test.additionalClasspath", additionalClasspathElements);
	}
}	
