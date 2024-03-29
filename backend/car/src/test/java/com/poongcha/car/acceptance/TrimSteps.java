package com.poongcha.car.acceptance;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static util.DocumentationTest.DEFAULT_RESTDOCS_PATH;
import static util.DocumentationTest.customRequestFields;
import static util.DocumentationTest.given;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.springframework.restdocs.payload.JsonFieldType;

public class TrimSteps {
    public static ExtractableResponse<Response> 트림_생성_요청(
            final String trimName,
            final String imageUrl,
            final long minPrice,
            final long carTypeId
    ) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        customRequestFields(
                                fieldWithPath("trimName").type(JsonFieldType.STRING).description("트림명"),
                                fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("트림 이미지 URL"),
                                fieldWithPath("minPrice").type(JsonFieldType.NUMBER).description("트림 최소 가격"),
                                fieldWithPath("carTypeId").type(JsonFieldType.NUMBER).description("차종 ID")
                        )
                ))
                .log().all()
                .when()
                .body(
                        Map.of(
                                "trimName", trimName,
                                "imageUrl", imageUrl,
                                "minPrice", minPrice,
                                "carTypeId", carTypeId
                        )
                )
                .contentType(ContentType.JSON)
                .post("/trim")
                .then().log().all()
                .extract();
    }

    public static void 트림_생성_응답_검증(final ExtractableResponse<Response> response, final String location) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertions.assertThat(response.header(HttpHeaders.LOCATION)).isEqualTo(location);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_차종으로_트림_생성_요청(
            final String trimName,
            final String imageUrl,
            final long minPrice,
            final long carTypeId
    ) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        customRequestFields(
                                fieldWithPath("trimName").type(JsonFieldType.STRING).description("트림명"),
                                fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("트림 이미지 URL"),
                                fieldWithPath("minPrice").type(JsonFieldType.NUMBER).description("트림 최소 가격"),
                                fieldWithPath("carTypeId").type(JsonFieldType.NUMBER).description("차종 ID")
                        )
                ))
                .log().all()
                .when()
                .body(
                        Map.of(
                                "trimName", trimName,
                                "imageUrl", imageUrl,
                                "minPrice", minPrice,
                                "carTypeId", carTypeId
                        )
                )
                .contentType(ContentType.JSON)
                .post("/trim")
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_차종으로_트림_생성_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        }
    }

    public static ExtractableResponse<Response> 트림_ID_조회_요청(final long id) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("트림 ID"),
                                fieldWithPath("trimName").type(JsonFieldType.STRING).description("트림명"),
                                fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("트림 이미지 URL"),
                                fieldWithPath("minPrice").type(JsonFieldType.NUMBER).description("트림 최소 가격"),
                                fieldWithPath("carTypeId").type(JsonFieldType.NUMBER).description("차종 ID"),
                                fieldWithPath("optionGroups").type(JsonFieldType.ARRAY).description("트림 대표 옵션 그룹"),
                                fieldWithPath("optionGroups[].id").type(JsonFieldType.NUMBER).description("트림 대표 옵션 ID"),
                                fieldWithPath("optionGroups[].name").type(JsonFieldType.STRING).description("트림 대표 옵션 이름")
                        )
                ))
                .log().all()
                .when()
                .get("/trim/{id}", id)
                .then().log().all()
                .extract();
    }

    public static void 트림_ID_조회_응답_검증(
            final ExtractableResponse<Response> response,
            final long trimId,
            final String trimName,
            final String imageUrl,
            final long minPrice,
            final long carTypeId,
            final List<Integer> optionGroupIds,
            final List<String> optionGroupName
    ) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertions.assertThat(response.jsonPath().getLong("id")).isEqualTo(trimId);
            assertions.assertThat(response.jsonPath().getString("trimName")).isEqualTo(trimName);
            assertions.assertThat(response.jsonPath().getString("imageUrl")).isEqualTo(imageUrl);
            assertions.assertThat(response.jsonPath().getLong("minPrice")).isEqualTo(minPrice);
            assertions.assertThat(response.jsonPath().getLong("carTypeId")).isEqualTo(carTypeId);
            assertions.assertThat(response.jsonPath().getList("optionGroups.id")).usingRecursiveComparison()
                    .isEqualTo(optionGroupIds);
            assertions.assertThat(response.jsonPath().getList("optionGroups.name")).usingRecursiveComparison()
                    .isEqualTo(optionGroupName);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_트림_ID_조회_요청(final long id) {
        return given()
                .filter(document(DEFAULT_RESTDOCS_PATH))
                .log().all()
                .when()
                .get("/trim/{id}", id)
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_트림_ID_조회_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        }
    }

    public static ExtractableResponse<Response> 차종_ID로_트림_목록_조회_요청(final long carTypeId) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        pathParameters(
                                parameterWithName("car-type-id").description("차종 ID")
                        ),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("트림 ID"),
                                fieldWithPath("[].trimName").type(JsonFieldType.STRING).description("트림명"),
                                fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("트림 이미지 URL"),
                                fieldWithPath("[].minPrice").type(JsonFieldType.NUMBER).description("트림 최소 가격"),
                                fieldWithPath("[].carTypeId").type(JsonFieldType.NUMBER).description("차종 ID"),
                                fieldWithPath("[].optionGroups").type(JsonFieldType.ARRAY).description("차종 트림 대표 옵션"),
                                fieldWithPath("[].optionGroups[].id").type(JsonFieldType.NUMBER).optional()
                                        .description("차종 대표 옵션 ID"),
                                fieldWithPath("[].optionGroups[].name").type(JsonFieldType.NUMBER).optional()
                                        .description("차종 대표 옵션 이름")
                        )
                ))
                .log().all()
                .when()
                .get("/car-type/{car-type-id}/trim", carTypeId)
                .then().log().all()
                .extract();
    }

    public static void 차종_ID로_트림_목록_조회_응답_검증(
            final ExtractableResponse<Response> response,
            final Long[] ids,
            final String[] trimNames,
            final String[] imageUrls,
            final Long[] minPrices,
            final Long[] carTypeIds
    ) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertions.assertThat(response.jsonPath().getList("id", Long.class)).containsExactly(ids);
            assertions.assertThat(response.jsonPath().getList("trimName", String.class)).containsExactly(trimNames);
            assertions.assertThat(response.jsonPath().getList("imageUrl", String.class)).containsExactly(imageUrls);
            assertions.assertThat(response.jsonPath().getList("minPrice", Long.class)).containsExactly(minPrices);
            assertions.assertThat(response.jsonPath().getList("carTypeId", Long.class)).containsExactly(carTypeIds);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_차종_ID로_트림_목록_조회_요청(final long carTypeId) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        pathParameters(
                                parameterWithName("car-type-id").description("차종 ID")
                        )
                ))
                .log().all()
                .when()
                .get("/car-type/{car-type-id}/trim", carTypeId)
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_차종_ID로_트림_목록_조회_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        }
    }

    public static ExtractableResponse<Response> 트림에_차량_색상_설정_요청(
            final long carColorId,
            final long trimId,
            final String trimExteriorImageUrl,
            final String trimInteriorImageUrl,
            final String trimRotationImageBaseUrl
    ) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        requestFields(
                                fieldWithPath("colorId").type(JsonFieldType.NUMBER).description("차량 색상 ID"),
                                fieldWithPath("trimExteriorImageUrl").type(JsonFieldType.STRING)
                                        .description("차량 외장 이미지 URL"),
                                fieldWithPath("trimInteriorImageUrl").type(JsonFieldType.STRING)
                                        .description("차량 내장 이미지 URL"),
                                fieldWithPath("trimRotationImageBaseUrl").type(JsonFieldType.STRING)
                                        .description("차량 회전 이미지 URL")
                        )
                )).log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "colorId", carColorId,
                        "trimExteriorImageUrl", trimExteriorImageUrl,
                        "trimInteriorImageUrl", trimInteriorImageUrl,
                        "trimRotationImageBaseUrl", trimRotationImageBaseUrl
                ))
                .post("/trim/{id}/color", trimId)
                .then().log().all()
                .extract();
    }

    public static void 트림에_차량_색상_설정_응답_검증(final ExtractableResponse<Response> response, final String location) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertions.assertThat(response.header(HttpHeaders.LOCATION)).isEqualTo(location);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_트림에_차량_색상_설정_요청(final long carColorId, final long trimId) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        requestFields(
                                fieldWithPath("colorId").type(JsonFieldType.NUMBER).description("차량 색상 ID")
                        )
                )).log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "colorId", carColorId
                ))
                .post("/trim/{id}/color", trimId)
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_트림에_차량_색상_설정_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        }
    }

    public static ExtractableResponse<Response> 트림에_존재하지_않는_차량_색상_설정_요청(final long carColorId, final long trimId) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        requestFields(
                                fieldWithPath("colorId").type(JsonFieldType.NUMBER).description("차량 색상 ID")
                        )
                )).log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "colorId", carColorId
                ))
                .post("/trim/{id}/color", trimId)
                .then().log().all()
                .extract();
    }

    public static void 트림에_존재하지_않는_차량_색상_설정_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        }
    }

    public static ExtractableResponse<Response> 차종에_차량_색상_조회_요청(final long carTypeId) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        pathParameters(parameterWithName("id").description("차종 ID")),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("트림 ID"),
                                fieldWithPath("[].colors").type(JsonFieldType.ARRAY).description("차량 색상 정보"),
                                fieldWithPath("[].colors[].id").type(JsonFieldType.NUMBER).description("차량 색상 ID"),
                                fieldWithPath("[].colors[].name").type(JsonFieldType.STRING).description("차량 색상 이름"),
                                fieldWithPath("[].colors[].imageUrl").type(JsonFieldType.STRING)
                                        .description("차량 색상 이미지 URL"),
                                fieldWithPath("[].colors[].type").type(JsonFieldType.STRING).description("차량 색상 타입"),
                                fieldWithPath("[].colors[].trimExteriorImageUrl").optional()
                                        .type(JsonFieldType.STRING)
                                        .description("차량 외장 이미지 URL"),
                                fieldWithPath("[].colors[].trimInteriorImageUrl").optional()
                                        .type(JsonFieldType.STRING)
                                        .description("차량 내장 이미지 URL"),
                                fieldWithPath("[].colors[].trimRotationImageBaseUrl").optional()
                                        .type(JsonFieldType.STRING)
                                        .description("차량 회전 이미지 URL"),
                                fieldWithPath("[].colors[].incompatibleColorIds[]").type(JsonFieldType.ARRAY)
                                        .description("양립 불가능 색상 ID 목록")
                        )
                )).log().all()
                .when()
                .get("/car-type/{id}/color", carTypeId)
                .then().log().all()
                .extract();
    }

    public static void 차종에_차량_색상_조회_응답_검증(
            final ExtractableResponse<Response> response,
            final List trimIds,
            final List<List> carColorIds,
            final List<List> carColorNames,
            final List<List> carColorImages,
            final List<List> carColorTypes,
            final List<List> incompatibleColorIds
    ) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode())
                    .isEqualTo(HttpStatus.SC_OK);
            assertions.assertThat(response.jsonPath().getList("id")).usingRecursiveComparison()
                    .isEqualTo(trimIds);
            assertions.assertThat(response.jsonPath().getList("colors.id")).usingRecursiveComparison()
                    .isEqualTo(carColorIds);
            assertions.assertThat(response.jsonPath().getList("colors.name")).usingRecursiveComparison()
                    .isEqualTo(carColorNames);
            assertions.assertThat(response.jsonPath().getList("colors.imageUrl")).usingRecursiveComparison()
                    .isEqualTo(carColorImages);
            assertions.assertThat(response.jsonPath().getList("colors.type")).usingRecursiveComparison()
                    .isEqualTo(carColorTypes);
            assertions.assertThat(response.jsonPath().getList("colors.incompatibleColorIds")).usingRecursiveComparison()
                    .isEqualTo(incompatibleColorIds);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_차종에_차량_색상_조회_요청(final long carTypeId) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        pathParameters(parameterWithName("id").description("차종 ID"))
                )).log().all()
                .when()
                .get("/car-type/{id}/color", carTypeId)
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_차종에_차량_색상_조회_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        }
    }


    public static ExtractableResponse<Response> 트림_대표_옵션_그룹_추가_요청(final long trimId, final long... optionGroupIds) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        requestFields(
                                fieldWithPath("optionGroupIds").type(JsonFieldType.ARRAY).description("옵션 그룹 ID 목록")
                        )
                )).log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "optionGroupIds", optionGroupIds
                ))
                .post("/trim/{id}/representative", trimId)
                .then().log().all()
                .extract();
    }

    public static void 트림_대표_옵션_그룹_추가_응답_검증(final ExtractableResponse<Response> response, final String location) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertions.assertThat(response.header(HttpHeaders.LOCATION)).isEqualTo(location);
        }
    }
}
