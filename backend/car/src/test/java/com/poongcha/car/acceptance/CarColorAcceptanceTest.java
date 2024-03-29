package com.poongcha.car.acceptance;

import static com.poongcha.car.acceptance.CarColorSteps.같은_ID로_양립_불가능한_차량_색상_설정_요청;
import static com.poongcha.car.acceptance.CarColorSteps.같은_ID로_양립_불가능한_차량_색상_설정_응답_검증;
import static com.poongcha.car.acceptance.CarColorSteps.같은_차량_색상_타입으로_양립_불가능한_차량_색상_설정_요청;
import static com.poongcha.car.acceptance.CarColorSteps.같은_차량_색상_타입으로_양립_불가능한_차량_색상_설정_응답_검증;
import static com.poongcha.car.acceptance.CarColorSteps.양립_불가능한_차량_색상_설정_요청;
import static com.poongcha.car.acceptance.CarColorSteps.양립_불가능한_차량_색상_설정_응답_검증;
import static com.poongcha.car.acceptance.CarColorSteps.존재하지_않는_차량_색상에_양립_불가능한_차량_색상_설정_요청;
import static com.poongcha.car.acceptance.CarColorSteps.존재하지_않는_차량_색상에_양립_불가능한_차량_색상_설정_응답_검증;
import static com.poongcha.car.acceptance.CarColorSteps.차량_색상_생성_요청;
import static com.poongcha.car.acceptance.CarColorSteps.차량_색상_생성_응답_검증;

import com.poongcha.car.util.CarAcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("차량 색상 관련 기능")
public class CarColorAcceptanceTest extends CarAcceptanceTest {
    @DisplayName("차량 색상 생성")
    @Test
    void 차량_색상_생성() {
        // GIVEN
        var carColorName = "palisade";
        var imageUrl = "https://www.hyundai.com/static/images/model/palisade/24my/mo/palisade_highlights_design_m.jpg";
        var carColorType = "INTERIOR";

        // WHEN
        var response = 차량_색상_생성_요청(carColorName, imageUrl, carColorType);

        // THEN
        차량_색상_생성_응답_검증(response, "/color/1");
    }

    @DisplayName("양립 불가능한 차량 색상 설정")
    @Test
    void 양립_불가능한_차량_색상_설정() {
        // GIVEN
        차량_색상_생성_요청("red", "www.naver.com/color/red.png", "INTERIOR");
        차량_색상_생성_요청("green", "www.naver.com/color/green.png", "EXTERIOR");
        차량_색상_생성_요청("blue", "www.naver.com/color/blue.png", "EXTERIOR");
        차량_색상_생성_요청("orange", "www.naver.com/color/orange.png", "EXTERIOR");

        // WHEN
        var response = 양립_불가능한_차량_색상_설정_요청(1L, 2L, 4L);

        // THEN
        양립_불가능한_차량_색상_설정_응답_검증(response, "/color/1");
    }

    @DisplayName("존재하지 않는 차량 색상에 양립 불가능한 차량 색상 설정")
    @Test
    void 존재하지_않는_차량_색상에_양립_불가능한_차량_색상_설정() {
        // GIVEN
        차량_색상_생성_요청("red", "www.naver.com/color/red.png", "INTERIOR");
        차량_색상_생성_요청("green", "www.naver.com/color/green.png", "EXTERIOR");

        // WHEN
        var response = 존재하지_않는_차량_색상에_양립_불가능한_차량_색상_설정_요청(120313029312830L, 1L, 2L);

        // THEN
        존재하지_않는_차량_색상에_양립_불가능한_차량_색상_설정_응답_검증(response);
    }

    @DisplayName("같은 ID로 양립 불가능한 차량 색상 설정")
    @Test
    void 같은_ID로_양립_불가능한_차량_색상_설정() {
        // GIVEN
        차량_색상_생성_요청("red", "www.naver.com/color/red.png", "INTERIOR");

        // WHEN
        var response = 같은_ID로_양립_불가능한_차량_색상_설정_요청(1L, 1L);

        // THEN
        같은_ID로_양립_불가능한_차량_색상_설정_응답_검증(response);
    }

    @DisplayName("같은 차량 색상 타입으로 양립 불가능한 차량 색상 설정")
    @Test
    void 같은_차량_색상_타입으로_양립_불가능한_차량_색상_설정() {
        // GIVEN
        차량_색상_생성_요청("red", "www.naver.com/color/red.png", "INTERIOR");
        차량_색상_생성_요청("blue", "www.naver.com/color/blue.png", "EXTERIOR");
        차량_색상_생성_요청("orange", "www.naver.com/color/orange.png", "INTERIOR");

        // WHEN
        var response = 같은_차량_색상_타입으로_양립_불가능한_차량_색상_설정_요청(1L, 2L, 3L);

        // THEN
        같은_차량_색상_타입으로_양립_불가능한_차량_색상_설정_응답_검증(response);
    }
}
