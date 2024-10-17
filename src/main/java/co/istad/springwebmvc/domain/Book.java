package co.istad.springwebmvc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Book {
    private Integer id;
    private String code;
    private String title;
    private String desc;
    private String thumbnail;
    private Boolean status;

}
