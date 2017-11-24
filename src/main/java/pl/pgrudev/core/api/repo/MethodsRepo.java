package pl.pgrudev.core.api.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import pl.pgrudev.core.api.PublicApi;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class MethodsRepo {
    private final static Logger logger = LogManager.getLogger(MethodsRepo.class);
    private Set<Class<?>> publicApis;
    private Map<Class, Map<String, Method>> ultimateInterfacesVsMethodsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            AnnotationInterfacesScanner annotationInterfacesScanner = new AnnotationInterfacesScanner(PublicApi.class);
            this.publicApis = new HashSet<>();


            for (BeanDefinition bd : annotationInterfacesScanner.findCandidateComponents("pl.pgrudev")) {
                Class clazz = Class.forName(bd.getBeanClassName());
                publicApis.add(clazz);
                Map<String, Method> methodsMap = Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toMap(Method::getName, method -> method));
                ultimateInterfacesVsMethodsMap.put(clazz, methodsMap);
            }

        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
    }


    public Map<Class, Map<String, Method>> getUltimateInterfacesVsMethodsMap() {
        return ultimateInterfacesVsMethodsMap;
    }

    public void setUltimateInterfacesVsMethodsMap(Map<Class, Map<String, Method>> ultimateInterfacesVsMethodsMap) {
        this.ultimateInterfacesVsMethodsMap = ultimateInterfacesVsMethodsMap;
    }

    public Map<String, Method> getMethodsMap(Class api){
        return ultimateInterfacesVsMethodsMap.get(api);
    }

    public Set<Class<?>> getPublicApis() {
        return publicApis;
    }

    public void setPublicApis(Set<Class<?>> publicApis) {
        this.publicApis = publicApis;
    }
}