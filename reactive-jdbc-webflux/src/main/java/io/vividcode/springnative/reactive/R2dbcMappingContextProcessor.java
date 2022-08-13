package io.vividcode.springnative.reactive;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.stereotype.Component;

@Component
class R2dbcMappingContextProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof R2dbcMappingContext) {
      ((R2dbcMappingContext) bean).setForceQuote(true);
    }
    return bean;
  }
}
