package fastcampus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {

    private Long id;
    private String name;
    private String profileImageUrl;
}
