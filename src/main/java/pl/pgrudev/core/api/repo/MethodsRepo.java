package pl.pgrudev.core.api.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import pl.pgrudev.core.api.annotations.AdminCommand;
import pl.pgrudev.core.api.annotations.LoginNotRequired;
import pl.pgrudev.core.api.annotations.PublicApi;

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
    private Map<Class, List<Method>> loginNotRequiredCommands = new HashMap<>();
    private Map<Class, List<Method>> adminOnlyCommands = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            findPublicApis();
            publicApis.forEach(this::findLoginNotRequiredCommands);
            publicApis.forEach(this::findAdminOnlyCommands);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
    }

    private void findPublicApis() throws ClassNotFoundException {
        AnnotationInterfacesScanner annotationInterfacesScanner = new AnnotationInterfacesScanner(PublicApi.class);
        this.publicApis = new HashSet<>();
        for (BeanDefinition bd : annotationInterfacesScanner.findCandidateComponents("pl.pgrudev")) {
            Class clazz = Class.forName(bd.getBeanClassName());
            publicApis.add(clazz);
            Map<String, Method> methodsMap = Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toMap(Method::getName, method -> method));
            ultimateInterfacesVsMethodsMap.put(clazz, methodsMap);
        }
    }

    private void findLoginNotRequiredCommands(Class<?> publicApi) {
        List<Method> methods = Arrays.stream(publicApi.getDeclaredMethods())
                .filter(method -> Arrays.stream(method.getAnnotations()).anyMatch(annotation -> LoginNotRequired.class.isAssignableFrom(annotation.annotationType())))
                .collect(Collectors.toList());
        this.loginNotRequiredCommands.put(publicApi, methods);
    }

    private void findAdminOnlyCommands(Class<?> publicApi) {
        List<Method> methods = Arrays.stream(publicApi.getDeclaredMethods())
                .filter(method -> Arrays.stream(method.getAnnotations()).anyMatch(annotation -> AdminCommand.class.isAssignableFrom(annotation.annotationType())))
                .collect(Collectors.toList());
        this.adminOnlyCommands.put(publicApi, methods);
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

    public List<Method> getLoginNotRequiredCommands(Class<?> publicApi) {
        return this.loginNotRequiredCommands.get(publicApi);
    }

    public List<Method> getAdminOnlyCommands(Class<?> publicApi) {
        return this.adminOnlyCommands.get(publicApi);
    }
}
