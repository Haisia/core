package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

  MemberService memberService;
  OrderService orderService;

  @BeforeEach
  public void beforeEach(){
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  @DisplayName("VIP 는 10% 할인이 적용되어야 한다")
  public void vip_o() throws Exception{
    //given
    Member member = new Member(1L,"memberA", Grade.VIP);
    memberService.join(member);
    Order order = orderService.createOrder(member.getId(), "itemA", 10000);

    //when
    int resultPrice = order.calculatePrice();

    //then
    assertThat(resultPrice).isEqualTo(9000);

  }

  @Test
  @DisplayName("VIP 가 아니면 할인이 적용되지 않아야 한다")
  public void vip_x() throws Exception{
    //given
    Member member = new Member(2L,"memberBASIC", Grade.BASIC);
    memberService.join(member);
    Order order = orderService.createOrder(member.getId(), "itemA", 10000);

    //when
    int discount = order.getDiscountPrice();

    //then
    assertThat(discount).isEqualTo(0);
  }
}