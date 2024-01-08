package com.pichincha.poc.system.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrackPostQuery {

    @NotNull
    private final Integer postTrackId;
}
