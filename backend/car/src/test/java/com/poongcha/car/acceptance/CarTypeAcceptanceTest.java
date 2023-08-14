package com.poongcha.car.acceptance;

import static com.poongcha.car.acceptance.CarTypeSteps.차종_ID_조회_요청;
import static com.poongcha.car.acceptance.CarTypeSteps.차종_ID_조회_응답_검증;
import static com.poongcha.car.acceptance.CarTypeSteps.차종_생성_요청;
import static com.poongcha.car.acceptance.CarTypeSteps.차종_생성_응답_검증;
import static com.poongcha.car.acceptance.CarTypeSteps.차종_전체_조회_요청;
import static com.poongcha.car.acceptance.CarTypeSteps.차종_전체_조회_응답_검증;

import com.poongcha.car.util.DocumentationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("차종 인수 테스트")
public class CarTypeAcceptanceTest extends DocumentationTest {
    @DisplayName("차종 생성")
    @Test
    void 차종_생성() {
        // GIVEN
        var car_type_name = "palisade";
        var image_url = "https://www.hyundai.com/static/images/model/palisade/24my/mo/palisade_highlights_design_m.jpg";

        // WHEN
        var response = 차종_생성_요청(car_type_name, image_url);

        // THEN
        차종_생성_응답_검증(response, "/api/car-type/1");
    }

    @DisplayName("차종 ID 조회")
    @Test
    void 차종_ID_조회() {
        // GIVEN
        var car_type_name = "palisade";
        var image_url = "https://www.hyundai.com/static/images/model/palisade/24my/mo/palisade_highlights_design_m.jpg";
        차종_생성_요청(car_type_name, image_url);

        // WHEN
        var response = 차종_ID_조회_요청(1L);

        // THEN
        차종_ID_조회_응답_검증(response, 1L, car_type_name, image_url);
    }

    @DisplayName("차종 전체 조회")
    @Test
    void 차종_전체_조회() {
        // GIVEN
        var carTypeName1 = "palisade";
        var imageUrl1 = "https://www.hyundai.com/static/images/model/palisade/24my/mo/palisade_highlights_design_m.jpg";
        차종_생성_요청(carTypeName1, imageUrl1);
        var carTypeName2 = "sonata";
        var imageUrl2 = "https://www.hyundai.com/static/images/model/sonata-hybrid/23fl/mo/sonata_the_edge_hybrid_highlights_hybrid_m.jpg";
        차종_생성_요청(carTypeName2, imageUrl2);

        // WHEN
        var response = 차종_전체_조회_요청();

        // THEN
        차종_전체_조회_응답_검증(
                response,
                new Long[]{1L, 2L},
                new String[]{carTypeName1, carTypeName2},
                new String[]{imageUrl1, imageUrl2}
        );
    }
}
