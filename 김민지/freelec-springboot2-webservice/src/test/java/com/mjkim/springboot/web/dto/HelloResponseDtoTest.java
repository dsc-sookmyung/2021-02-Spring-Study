package com.mjkim.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        // assertThat: assertj(테스트 검증 라이브러리)의 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 받는
        // isEquelTo: assertThat의 값과 비교해서 같을 때 성공
    }
}
