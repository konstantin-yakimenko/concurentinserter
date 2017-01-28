package ru.jakimenko.concurentinserter;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author kyyakime
 */
public class ApplicationContextUnit {

    private static ClassPathXmlApplicationContext context;

    public static void load() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext();

            Resource dataSourceResource = new ClassPathResource("properties/datasource.properties");
            Resource[] resources = {
                dataSourceResource
//                new ClassPathResource("properties/queries.properties"),
            };
            PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
            configurer.setLocations(resources);
            context.addBeanFactoryPostProcessor(configurer);
            context.setConfigLocation("spring/applicationContext.xml");
            context.refresh();
        }
    }

    public static <T> T getBean(final Class<T> clazz) {
        return (T) context.getBean(clazz);
    }

    public static <T> T getBean(String name, final Class<T> clazz) {
        return (T) context.getBean(name, clazz);
    }

    private ApplicationContextUnit() {
        super();
    }
    
}
