package com.apapadak.poc.logging.helpers;

import java.util.HashMap;
import java.util.Map;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;


public class MapHelper {
    public static Map<String, Object> unflatten(Map<String, Object> flattened) {
        Map<String, Object> unflattened = new HashMap<>();
        for (String key : flattened.keySet()) {
            doUnflatten(unflattened, key, flattened.get(key));
        }
        return unflattened;
    }

    private static void doUnflatten(Map<String, Object> current, String key, Object originalValue) {
        String[] parts = StringUtils.split(key, ".");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == (parts.length - 1)) {
                current.put(part, originalValue);
                return;
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> nestedMap = (Map<String, Object>) current.get(part);
            if (nestedMap == null) {
                nestedMap = new HashMap<>();
                current.put(part, nestedMap);
            }

            current = nestedMap;
        }
    }
}
