package com.poongcha.recommend.application.carestimaterecommandation;

import com.poongcha.recommend.application.dto.CarEstimateRecommendationCreateRequest;
import com.poongcha.recommend.domain.carestimaterecommendation.CarEstimateRecommendation;
import com.poongcha.recommend.domain.carestimaterecommendation.CarEstimateRecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CarEstimateRecommendationCommandService {
    private final CarEstimateRecommendationRepository carEstimateRecommendationRepository;
    private final CarEstimateRecommendationMapper carEstimateRecommendationMapper;

    public long create(final CarEstimateRecommendationCreateRequest carEstimateRecommendationRequest) {
        CarEstimateRecommendation carEstimateRecommendation = carEstimateRecommendationMapper
                .toEntity(carEstimateRecommendationRequest);

        return carEstimateRecommendationRepository.save(carEstimateRecommendation).getId();
    }
}
