package br.com.myanalista.models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserTypeEnum {

  ADMINISTRATOR(1, "administrator"),
  SECONDARY(2, "secundary"),
  VISITOR(3, "visitor");

  private Integer guid;
  private String descriptionText;

  public static CompanyTypeEnum toEnum(Integer guid) {
    if (guid == null) {
      return null;
    }
    for (CompanyTypeEnum e : CompanyTypeEnum.values()) {
      if (guid.equals(e.getGuid())) {
        return e;
      }
    }
    throw new IllegalArgumentException("Invalid Id" + guid);
  }
}
