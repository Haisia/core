package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

  @Test
  public void statefulServiceSingleton() throws Exception{
    //given
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    //when
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    //then
    statefulService1.order("userA", 10000);
    statefulService2.order("userB", 20000);

    System.out.println("statefulService1.getPrice() = " + statefulService1.getPrice());

    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig{
    @Bean
    public StatefulService statefulService(){
      return new StatefulService();
    }
  }
}