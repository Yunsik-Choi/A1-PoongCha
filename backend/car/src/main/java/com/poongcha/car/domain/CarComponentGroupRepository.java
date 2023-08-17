package com.poongcha.car.domain;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface CarComponentGroupRepository extends Repository<CarComponentGroup, Long> {
    CarComponentGroup save(final CarComponentGroup carComponentGroup);

    @Query("select * from car_component_groups where id = :id")
    Optional<CarComponentGroup> findByIdWithLock(final long id);
}
