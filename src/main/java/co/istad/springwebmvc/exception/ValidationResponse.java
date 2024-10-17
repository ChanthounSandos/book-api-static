package co.istad.springwebmvc.exception;

import lombok.Builder;

@Builder
public record ValidationResponse(
        String field,
        String desc
) {
}
