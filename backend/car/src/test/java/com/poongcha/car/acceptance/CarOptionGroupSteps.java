package com.poongcha.car.acceptance;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static com.poongcha.car.util.DocumentationTest.DEFAULT_RESTDOCS_PATH;
import static com.poongcha.car.util.DocumentationTest.customRequestFields;
import static com.poongcha.car.util.DocumentationTest.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.springframework.restdocs.payload.JsonFieldType;

public class CarOptionGroupSteps {
    public static ExtractableResponse<Response> 차량_옵션_그룹_생성_요청(
            final String carOptionGroupName,
            final long additionalPrice,
            final String summaryDescription,
            final long[] ids
    ) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        customRequestFields(
                                fieldWithPath("carOptionGroupName").type(JsonFieldType.STRING)
                                        .description("차량 옵션 그룹 이름"),
                                fieldWithPath("additionalPrice").type(JsonFieldType.NUMBER)
                                        .description("차량 옵션 그룹 가격"),
                                fieldWithPath("summaryDescription").type(JsonFieldType.STRING)
                                        .description("차량 옵션 그룹 설명"),
                                fieldWithPath("carOptionIds").type(JsonFieldType.ARRAY)
                                        .description("차량 옵션 ID 목록")
                        )
                )).log().all()
                .when()
                .body(Map.of(
                        "carOptionGroupName", carOptionGroupName,
                        "additionalPrice", additionalPrice,
                        "summaryDescription", summaryDescription,
                        "carOptionIds", ids
                ))
                .contentType(ContentType.JSON)
                .post("/api/option-group")
                .then().log().all()
                .extract();
    }

    public static void 차량_옵션_그룹_생성_응답_검증(final ExtractableResponse<Response> response, final String location) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertions.assertThat(response.header(HttpHeaders.LOCATION)).isEqualTo(location);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_차량_옵션_ID로_차량_옵션_그룹_생성_요청(
            final String carOptionGroupName,
            final long additionalPrice,
            final String summaryDescription,
            final long[] ids
    ) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        customRequestFields(
                                fieldWithPath("carOptionGroupName").type(JsonFieldType.STRING)
                                        .description("차량 옵션 그룹 이름"),
                                fieldWithPath("additionalPrice").type(JsonFieldType.NUMBER)
                                        .description("차량 옵션 그룹 가격"),
                                fieldWithPath("summaryDescription").type(JsonFieldType.STRING)
                                        .description("차량 옵션 그룹 설명"),
                                fieldWithPath("carOptionIds").type(JsonFieldType.ARRAY)
                                        .description("차량 옵션 ID 목록")
                        )
                )).log().all()
                .when()
                .body(Map.of(
                        "carOptionGroupName", carOptionGroupName,
                        "additionalPrice", additionalPrice,
                        "summaryDescription", summaryDescription,
                        "carOptionIds", ids
                ))
                .contentType(ContentType.JSON)
                .post("/api/option-group")
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_차량_옵션_ID로_차량_옵션_그룹_생성_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        }
    }

    public static ExtractableResponse<Response> 차량_옵션_그룹_ID_조회_요청(final long id) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        pathParameters(
                                parameterWithName("id").description("차량 컴포넌트 그룹 ID")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER)
                                        .description("차량 옵션 그룹 id"),
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("차량 옵션 그룹 이름"),
                                fieldWithPath("additionalPrice").type(JsonFieldType.NUMBER)
                                        .description("차량 옵션 그룹 가격"),
                                fieldWithPath("summaryDescription").type(JsonFieldType.STRING)
                                        .description("차량 옵션 그룹 요약 설명"),
                                fieldWithPath("options").type(JsonFieldType.ARRAY)
                                        .description("차량 옵션 목록"),
                                fieldWithPath("options[].id").type(JsonFieldType.NUMBER)
                                        .description("차량 옵션 id"),
                                fieldWithPath("options[].name").type(JsonFieldType.STRING)
                                        .description("차량 옵션 이름"),
                                fieldWithPath("options[].imageUrl").type(JsonFieldType.STRING)
                                        .description("차량 옵션 이미지 URL"),
                                fieldWithPath("options[].installationLocation").type(JsonFieldType.STRING)
                                        .description("설치 위치"),
                                fieldWithPath("options[].detailDescription").type(JsonFieldType.STRING)
                                        .description("차량 옵션 자세한 설명")
                        )
                ))
                .log().all()
                .when()
                .get("/api/option-group/{id}", id)
                .then().log().all()
                .extract();
    }

    public static void 차량_옵션_그룹_ID_조회_응답_검증(
            final ExtractableResponse<Response> response,
            final long optionGroupId,
            final String carOptionGroupName,
            final long additionalPrice,
            final String summaryDescription,
            final List<Integer> optionIds,
            final List<String> optionNames,
            final List<String> imageUrls,
            final List<String> detailDescriptions,
            final List<String> installationLocations
    ) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertions.assertThat(response.jsonPath().getLong("id")).isEqualTo(optionGroupId);
            assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(carOptionGroupName);
            assertions.assertThat(response.jsonPath().getLong("additionalPrice")).isEqualTo(additionalPrice);
            assertions.assertThat(response.jsonPath().getString("summaryDescription")).isEqualTo(summaryDescription);
            assertions.assertThat(response.jsonPath().getList("options.id")).usingRecursiveComparison()
                    .isEqualTo(optionIds);
            assertions.assertThat(response.jsonPath().getList("options.name")).usingRecursiveComparison()
                    .isEqualTo(optionNames);
            assertions.assertThat(response.jsonPath().getList("options.imageUrl")).usingRecursiveComparison()
                    .isEqualTo(imageUrls);
            assertions.assertThat(response.jsonPath().getList("options.detailDescription")).usingRecursiveComparison()
                    .isEqualTo(detailDescriptions);
            assertions.assertThat(response.jsonPath().getList("options.installationLocation"))
                    .usingRecursiveComparison().isEqualTo(installationLocations);
        }
    }

    public static ExtractableResponse<Response> 존재하지_않는_차량_옵션_그룹_ID_조회_요청(final long id) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        pathParameters(
                                parameterWithName("id").description("차량 컴포넌트 그룹 ID")
                        )
                ))
                .log().all()
                .when()
                .get("/api/option-group/{id}", id)
                .then().log().all()
                .extract();
    }

    public static void 존재하지_않는_차량_옵션_그룹_ID_조회_응답_검증(final ExtractableResponse<Response> response) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        }
    }
}
