package pl.pgrudev.core.api.repo;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

public class AnnotationInterfacesScanner extends ClassPathScanningCandidateComponentProvider {

    AnnotationInterfacesScanner(Class<? extends Annotation> annotationClass) {
        super(false);
        addIncludeFilter(new AnnotationTypeFilter(annotationClass, false));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
