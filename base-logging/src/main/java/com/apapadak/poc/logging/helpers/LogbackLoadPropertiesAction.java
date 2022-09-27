package com.apapadak.poc.logging.helpers;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.xml.sax.Attributes;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ActionUtil;
import ch.qos.logback.core.joran.action.ActionUtil.Scope;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;

/**
 * Logback {@link Action} to support loading external properties (IF they exist), using variable substitution
 */
public class LogbackLoadPropertiesAction extends Action {

    private static final String SOURCE_ATTRIBUTE = "source";
    
    private static final String PREFIX_ATTRIBUTE = "prefix";

    @Override
    public void begin(InterpretationContext context, String elementName, Attributes attributes) throws ActionException {
        String source = attributes.getValue(SOURCE_ATTRIBUTE);
        if (OptionHelper.isEmpty(source) || OptionHelper.isEmpty(source)) {
            addError("The \"source\" attributes of <loadProperties> must be set");
        }

        Scope scope = ActionUtil.stringToScope(attributes.getValue(SCOPE_ATTRIBUTE));
        
        String prefix = attributes.getValue(PREFIX_ATTRIBUTE);
        if(prefix == null) {
            prefix = "";
        }
        final String p = prefix;
        
        if (OptionHelper.isEmpty(source)) {
            return;
        }
        final String location = context.getJoranInterpreter().getInterpretationContext().subst(source);
        if (OptionHelper.isEmpty(location)) {
            return;
        }
        Properties buildProps = load(location);
        if (buildProps != null) {
            buildProps.forEach((k, v) -> {
                if (k != null && v != null) {
                    ActionUtil.setProperty(context, p + k.toString(), v.toString(), scope);
                }
            });
        }
    }

    @Override
    public void end(InterpretationContext context, String name) throws ActionException {
    }

    private Properties load(String location) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(this.getClass().getClassLoader());
        
        try (InputStream is = resourceLoader.getResource(location).getInputStream()) {
            if (is != null) {
                Properties buildProps = new Properties();
                buildProps.load(is);
                return buildProps;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
