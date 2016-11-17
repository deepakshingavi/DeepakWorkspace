package com.appdirect.challenge.logging;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * Bean processor that inject {@link Logger} into beans with field annotated with AutowiredLogger.
 */
@Component
public class AutowiredLoggerBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AutowiredLoggerBeanPostProcessor.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) {
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) {
        ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
            @Override
            public void doWith(final Field field) throws IllegalAccessException {
                if (field.getAnnotation(AutowiredLogger.class) != null) {
                    if (field.getType().isAssignableFrom(Logger.class)) {
                        Logger logger = LoggerFactory.getLogger(field.getDeclaringClass());
                        ReflectionUtils.makeAccessible(field);
                        field.set(bean, logger);
                    } else {
                        logger.warn("The annotated field '{}' in class '{}' must be of type '{}'.",
                                    new Object[]{ field.getName(), bean.getClass().getName(), Logger.class.getName() });
                    }
                }
            }
        });
        return bean;
    }

}
