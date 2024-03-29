package com.poongcha.car.application.carcolor;

import com.poongcha.car.application.dto.CarColorAddIncompatibleColorRequest;
import com.poongcha.car.application.dto.CarColorCreateRequest;
import com.poongcha.car.domain.carcolor.CarColor;
import com.poongcha.car.domain.carcolor.CarColorRepository;
import com.poongcha.car.exception.BadRequestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CarColorCommandService {
    private final CarColorRepository carColorRepository;
    private final CarColorMapper carColorMapper;

    public long create(final CarColorCreateRequest carColorCreateRequest) {
        CarColor carColor = carColorMapper.toEntity(carColorCreateRequest);

        return carColorRepository.save(carColor).getId();
    }

    public long add(
            final long carColorId,
            final CarColorAddIncompatibleColorRequest carColorAddIncompatibleColorRequest
    ) {
        CarColor carColor = carColorRepository.findByIdWithLock(carColorId)
                .orElseThrow(() -> new BadRequestException("차량 색상이 존재하지 않습니다."));

        List<CarColor> addInCompatibleRequestCarColor = carColorRepository
                .findAllByIdIn(carColorAddIncompatibleColorRequest.getIds());

        carColor.addIncompatibleColor(addInCompatibleRequestCarColor);

        return carColorRepository.save(carColor).getId();
    }
}
