package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  public void configurationTest() throws Exception{
    //given
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository3 = ac.getBean("memberRepository", MemberRepository.class);

    //when
    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();


    //then
    System.out.println("memberRepository1 = " + memberRepository1);
    System.out.println("memberRepository2 = " + memberRepository2);
    System.out.println("memberRepository3 = " + memberRepository3);

    Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    Assertions.assertThat(memberRepository1).isSameAs(memberRepository3);
  }

  @Test
  public void configurationDeep() throws Exception{
    //given
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);

    //when

    //then
    System.out.println("bean.getClass() = " + bean.getClass());

  }
}













