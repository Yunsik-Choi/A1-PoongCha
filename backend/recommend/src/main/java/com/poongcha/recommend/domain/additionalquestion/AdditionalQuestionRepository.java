package com.poongcha.recommend.domain.additionalquestion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface AdditionalQuestionRepository extends Repository<AdditionalQuestion, Long> {
    AdditionalQuestion save(final AdditionalQuestion additionalQuestion);

    Optional<AdditionalQuestion> findById(final long id);

    List<AdditionalQuestion> findAllByIdIn(final List<Long> ids);
}
