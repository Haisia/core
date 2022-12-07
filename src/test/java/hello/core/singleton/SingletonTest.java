package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
  @Test
  @DisplayName("스프링 없는 순수한 DI 컨테이너")
  public void pureContainer() throws Exception{
    //given
    AppConfig appConfig = new AppConfig();
    MemberService memberService1 = appConfig.memberService();
    MemberService memberService2 = appConfig.memberService();

    //when
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    //then
    Assertions.assertThat(memberService1).isNotSameAs(memberService2);

  }

  @Test
  @DisplayName("싱글톤 패턴을 적용한 객체 사용")
  public void singletonServiceTest() throws Exception{
    SingletonService instance1 = SingletonService.getInstance();
    SingletonService instance2 = SingletonService.getInstance();

    System.out.println("instance1 = " + instance1);
    System.out.println("instance2 = " + instance2);

    Assertions.assertThat(instance1).isSameAs(instance2);
  }

  @Test
  @DisplayName("스프링 컨테이너와 싱글톤")
  public void springContainer() throws Exception{
    //given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    //when
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    //then
    Assertions.assertThat(memberService1).isSameAs(memberService2);

  }
}
