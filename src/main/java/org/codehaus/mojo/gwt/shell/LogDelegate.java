package org.codehaus.mojo.gwt.shell;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.logging.Log;

public class LogDelegate implements Log {
    private final StringBuilder builder = new StringBuilder();
    private final File logFile;
    private final Pattern logPattern;
    private final Log delegate;
    
    public LogDelegate(Log delegate, final File logFile, final Pattern logPattern) {
        this.delegate = delegate;
        this.logPattern = logPattern;
        this.logFile = logFile;
    }

    public boolean isDebugEnabled() {
        return delegate.isDebugEnabled();
    }

    public void debug(CharSequence content) {
        delegate.debug(content);
    }

    public void debug(CharSequence content, Throwable error) {
        delegate.debug(content, error);
    }

    public void debug(Throwable error) {
        delegate.debug(error);
    }

    public boolean isInfoEnabled() {
        return delegate.isInfoEnabled();
    }

    public void info(CharSequence content) {
        if (logPattern == null || logPattern.matcher(content).matches()) {
            if (logFile != null) {
                builder.append(content.toString().trim() + "\n");
            } else {
                delegate.info(content.toString().trim());
            }
        }
    }

    public void flush() {
        if (logFile != null) {
            try {
                delegate.info("Debug output written to ... " + logFile.getPath());
                
                FileUtils.writeStringToFile(logFile, builder.toString());
            } catch (IOException ioe) {
                // ignore
            } finally {
                builder.setLength(0);
            }
        }
    }
    
    public void info(CharSequence content, Throwable error) {
        delegate.info(content, error);
    }

    public void info(Throwable error) {
        delegate.info(error);
    }

    public boolean isWarnEnabled() {
        return delegate.isWarnEnabled();
    }

    public void warn(CharSequence content) {
        delegate.warn(content);
    }

    public void warn(CharSequence content, Throwable error) {
        delegate.warn(content, error);
    }

    public void warn(Throwable error) {
        delegate.warn(error);
    }

    public boolean isErrorEnabled() {
        return delegate.isErrorEnabled();
    }

    public void error(CharSequence content) {
        delegate.error(content);
    }

    public void error(CharSequence content, Throwable error) {
        delegate.error(content, error);
    }

    public void error(Throwable error) {
        delegate.error(error);
    }

}
