package com.poongcha.car.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "car_types")
public class CarType {
    @Column(value = "id")
    @Id
    private Long id;

    @Embedded.Empty
    private CarTypeName carTypeName;

    @Embedded.Empty
    private ImageUrl imageUrl;

    public CarType(final String carTypeName, final String imageUrl) {
        this.carTypeName = new CarTypeName(carTypeName);
        this.imageUrl = new ImageUrl(imageUrl);
    }
}
