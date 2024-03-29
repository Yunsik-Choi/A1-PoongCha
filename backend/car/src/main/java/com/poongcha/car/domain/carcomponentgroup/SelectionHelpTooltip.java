package com.poongcha.car.domain.carcomponentgroup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SelectionHelpTooltip {
    @Column("selection_help_tooltip")
    private String value;
}
