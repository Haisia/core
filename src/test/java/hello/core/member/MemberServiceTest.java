package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
  // MemberServiceImpl 이라는 구현체에 의존하기 떄문에 DIP 에 위배된다!
  MemberService memberService = new MemberServiceImpl();

  @Test
  public void join() throws Exception{
    //given
    Member member = new Member(1L, "MemberA", Grade.VIP);

    //when
    memberService.join(member);
    Member findMember = memberService.findMember(1L);

    //then
    Assertions.assertThat(member).isEqualTo(findMember);
  }
}
