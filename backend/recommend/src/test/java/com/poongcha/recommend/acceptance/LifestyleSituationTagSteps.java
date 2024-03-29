package com.poongcha.recommend.acceptance;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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

public class LifestyleSituationTagSteps {
    public static ExtractableResponse<Response> 라이프스타일_상황_태그_생성_요청(final String tagName) {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        customRequestFields(
                                fieldWithPath("tagName").type(JsonFieldType.STRING).description("라이프스타일 상황 태그 이름")
                        )
                )).log().all()
                .when()
                .body(Map.of(
                        "tagName", tagName
                ))
                .contentType(ContentType.JSON)
                .post("/lifestyle-persona-situation-tag")
                .then().log().all()
                .extract();
    }

    public static void 라이프스타일_상황_태그_응답_검증(final ExtractableResponse<Response> response, final String location) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            assertions.assertThat(response.header(HttpHeaders.LOCATION)).isEqualTo(location);
        }
    }

    public static ExtractableResponse<Response> 라이프스타일_상황_태그_전체_조회_요청() {
        return given()
                .filter(document(
                        DEFAULT_RESTDOCS_PATH,
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("라이프스타일 상황 태그 ID"),
                                fieldWithPath("[].tagName").type(JsonFieldType.STRING).description("라이프스타일 상황 태그 이름")
                        )
                )).log().all()
                .when()
                .get("/lifestyle-persona-situation-tag")
                .then().log().all()
                .extract();
    }

    public static void 라이프스타일_상황_태그_전체_조회_응답_검증(
            final ExtractableResponse<Response> response,
            final List<Integer> ids,
            final List<String> tagNames
    ) {
        try (AutoCloseableSoftAssertions assertions = new AutoCloseableSoftAssertions()) {
            assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertions.assertThat(response.jsonPath().getList("id")).usingRecursiveComparison()
                    .isEqualTo(ids);
            assertions.assertThat(response.jsonPath().getList("tagName")).usingRecursiveComparison()
                    .isEqualTo(tagNames);
        }
    }
}
