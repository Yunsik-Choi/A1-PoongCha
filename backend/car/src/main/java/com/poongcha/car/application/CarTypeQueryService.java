package com.poongcha.car.application;

import com.poongcha.car.application.dto.CarTypeDefaultResponse;
import com.poongcha.car.application.mapper.CarTypeMapper;
import com.poongcha.car.domain.CarType;
import com.poongcha.car.domain.CarTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarTypeQueryService {
    private final CarTypeRepository carTypeRepository;
    private final CarTypeMapper carTypeMapper;

    public CarTypeDefaultResponse findById(final long id) {
        CarType carType = carTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CarType ID " + id + " 이 존재하지 않습니다."));
        return carTypeMapper.toDefaultResponse(carType);
    }
}
