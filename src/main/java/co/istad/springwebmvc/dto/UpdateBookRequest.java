package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateBookRequest(
        @NotBlank
        @Size(min = 5, max = 250)
        String title,

        String desc
) {
}
