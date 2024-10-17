package co.istad.springwebmvc.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse<T>(
        Integer code,
        String message,
        LocalDateTime requestedtime,
        T reason //generic
) {
}
