package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBookRequest(
        @NotBlank
                @Size(min = 5, max = 250)
        String title,

        String desc,

        @NotBlank
                @Size(max = 150)
        String thumbnail
) {
}
