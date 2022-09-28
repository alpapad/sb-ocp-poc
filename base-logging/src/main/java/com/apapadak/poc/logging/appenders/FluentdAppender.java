package com.apapadak.poc.logging.appenders;

import java.util.Map;

import com.apapadak.poc.logging.helpers.MapHelper;

/**
 * Extension to FluencyLogbackAppender so convert dotted additionalField (a.b.c) to {a:b{c:}}.
 * 
 * Note that no error checking is applied (ie a.b defined as scalar and later a.b.c is defined)
 */
public class FluentdAppender<E> extends ch.qos.logback.more.appenders.FluencyLogbackAppender<E>{

    protected Map<String, Object> createData(E event) {
        Map<String, Object> fromParent = super.createData(event);
        return MapHelper.unflatten(fromParent);
    }
}
