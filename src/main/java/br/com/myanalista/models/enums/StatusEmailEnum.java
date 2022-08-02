package br.com.myanalista.models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StatusEmailEnum {
    PROCESSING(1, "processing"),
    SENT(2, "sent"),
    ERROR(3, "error");
    private Integer guid;
    private String description;

    public static StatusEmailEnum toEnum(Integer guid) {
        if (guid == null) {
            return null;
        }
        for (StatusEmailEnum e : StatusEmailEnum.values()) {
            if (guid.equals(e.getGuid())) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid Id" + guid);
    }
}

