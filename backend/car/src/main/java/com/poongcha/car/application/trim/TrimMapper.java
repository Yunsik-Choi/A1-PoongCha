package com.poongcha.car.application.trim;

import com.poongcha.car.application.dto.TrimCreateRequest;
import com.poongcha.car.application.dto.TrimDefaultResponse;
import com.poongcha.car.domain.common.ImageUrl;
import com.poongcha.car.domain.trim.MinPrice;
import com.poongcha.car.domain.trim.Trim;
import com.poongcha.car.domain.trim.TrimName;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TrimMapper {
    public Trim toEntity(final TrimCreateRequest trimCreateRequest) {
        return new Trim(
                new TrimName(trimCreateRequest.getTrimName()),
                new ImageUrl(trimCreateRequest.getImageUrl()),
                new MinPrice(trimCreateRequest.getMinPrice()),
                trimCreateRequest.getCarTypeId()
        );
    }

    public TrimDefaultResponse toDefaultResponse(final Trim trim) {
        return new TrimDefaultResponse(
                trim.getId(),
                trim.getTrimName().getValue(),
                trim.getImageUrl().getValue(),
                trim.getMinPrice().getValue(),
                trim.getCarType().getId()
        );
    }

    public List<TrimDefaultResponse> toDefaultResponse(final List<Trim> trims) {
        return trims.stream()
                .map(this::toDefaultResponse)
                .collect(Collectors.toUnmodifiableList());
    }
}
