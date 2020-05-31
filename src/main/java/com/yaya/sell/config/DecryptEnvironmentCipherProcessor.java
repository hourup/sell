package com.yaya.sell.config;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yaya.sell.utils.AESUtil;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author yaomengya
 * @create 2020-04-10 9:56
 */
@Component
public class DecryptEnvironmentCipherProcessor implements EnvironmentPostProcessor {

    private final String CIPHER_PREFIX = "+{cipher}";

    private final String KEY_FILE_NAME = "classpath:key.json";

    private final String PROPERTY_SOURCE_NAME = "applicationConfig: [classpath:/application.yml]";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        File file;
        try {
            file = ResourceUtils.getFile(KEY_FILE_NAME);
        } catch (FileNotFoundException e) {
            return;
        }

        byte[] cipherKey;
        try {
            KeyResource keyResource = new Gson().fromJson(FileUtils.readFileToString(file, StandardCharsets.UTF_8), KeyResource.class);
            cipherKey = Hex.decode(keyResource.getCipherKey());
        } catch (IOException e) {
            throw new IllegalStateException("Cloud not read cipher key from resource file [" + KEY_FILE_NAME + "]", e);
        }

        MutablePropertySources propertySources = environment.getPropertySources();

        PropertySource<?> myPropertySource = null;
        for (PropertySource<?> propertySource : propertySources) {
            String name = propertySource.getName();
            if (PROPERTY_SOURCE_NAME.equals(name)) {

                MapPropertySource source = (MapPropertySource) propertySource;

                String[] propertyNames = source.getPropertyNames();
                Map<String, Object> newSource = Maps.newHashMap();
                for (String propertyName : propertyNames) {
                    if (source.getProperty(propertyName) instanceof String) {
                        String value = (String) source.getProperty(propertyName);
                        assert value != null;
                        if (value.startsWith(CIPHER_PREFIX)) {
                            String substring = value.substring(CIPHER_PREFIX.length());
                            String sourceValue = new String(AESUtil.decrypt(Hex.decode(substring), cipherKey), StandardCharsets.UTF_8);
                            newSource.put(propertyName, sourceValue);
                        } else {
                            newSource.put(propertyName, source.getProperty(propertyName));
                        }
                    } else {
                        newSource.put(propertyName, source.getProperty(propertyName));
                    }

                }
                myPropertySource = new MapPropertySource(name, newSource);
            }
        }

        if (myPropertySource != null) {
            propertySources.replace(PROPERTY_SOURCE_NAME, myPropertySource);
        }
    }

    public static class KeyResource {

        private String cipherKey;

        public String getCipherKey() {
            return cipherKey;
        }

        public void setCipherKey(String cipherKey) {
            this.cipherKey = cipherKey;
        }

    }
}
