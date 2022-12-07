package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
  ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
  public void findBeanByParentTypeDuplicate() throws Exception{
    //then
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
  }
  @Test
  @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
  public void findBeanByParentTypeBeanName() throws Exception{
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  public void findBeanBySubType() throws Exception{
    //given
    RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    //then
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회")
  public void findAllBeanByParentType() throws Exception{
    //given
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    //then
    assertThat(beansOfType.size()).isEqualTo(2);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key);
      System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
      System.out.println("#############");
    }
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회하기 - Object")
  public void findAllBeanByObjectType() throws Exception{
    //given
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
    //when

    //then
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key);
      System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
      System.out.println("####################################################");
    }
  }

  @Configuration
  static class TestConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy(){
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy(){
      return new FixDiscountPolicy();
    }
  }

}
