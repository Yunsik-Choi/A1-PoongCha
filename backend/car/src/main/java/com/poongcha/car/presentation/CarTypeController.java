package com.poongcha.car.presentation;

import com.poongcha.car.application.CarTypeCommandService;
import com.poongcha.car.application.CarTypeQueryService;
import com.poongcha.car.application.dto.CarTypeCreateRequest;
import com.poongcha.car.application.dto.CarTypeDefaultResponse;
import java.net.URI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CarTypeController {
    private final CarTypeCommandService carTypeCommandService;
    private final CarTypeQueryService carTypeQueryService;

    @PostMapping("/api/car-type")
    public ResponseEntity createCarType(@RequestBody final CarTypeCreateRequest carTypeCreateRequest) {
        long createCarTypeId = carTypeCommandService.create(carTypeCreateRequest);
        return ResponseEntity.created(URI.create("/api/car-type/" + createCarTypeId)).build();
    }

    @GetMapping("/api/car-type/{id}")
    public ResponseEntity<CarTypeDefaultResponse> findById(@PathVariable(value = "id") final long id) {
        CarTypeDefaultResponse carTypeDefaultResponse = carTypeQueryService.findById(id);
        return ResponseEntity.ok().body(carTypeDefaultResponse);
    }
}
