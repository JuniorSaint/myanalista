package br.com.myanalista.models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TeamTypeEnum {

SUPERVISOR(1, "supervisor"),
VENDEDOR(2, "vendedor");

  private Integer guid;
  private String descriptionText;

  public static TeamTypeEnum toEnum(Integer guid) {
    if (guid == null) {
      return null;
    }
    for (TeamTypeEnum e : TeamTypeEnum.values()) {
      if (guid.equals(e.getGuid())) {
        return e;
      }
    }
    throw new IllegalArgumentException("Invalid Id" + guid);
  }
}