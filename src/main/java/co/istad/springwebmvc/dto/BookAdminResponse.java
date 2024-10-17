package co.istad.springwebmvc.dto;

public record BookAdminResponse(
        String code,
        String title,
        String desc,
        String thumbnail
) {
}
