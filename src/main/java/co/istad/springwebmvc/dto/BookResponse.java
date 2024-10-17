package co.istad.springwebmvc.dto;

import lombok.Builder;

@Builder
public record BookResponse(
        String code,
        String title,
        String desc,
        String thumbnail,
        Boolean status
) {
}
