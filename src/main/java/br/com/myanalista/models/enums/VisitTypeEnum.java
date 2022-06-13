package br.com.myanalista.models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum VisitTypeEnum {
  NODIACERTO(1, "No Dia Certo"),
  NEGATIVO(2, "Negativo"),
  FORADAROTA(3, "Fora de Rota");

  private Integer guid;
  private String descriptionText;

  public static VisitTypeEnum toEnum(Integer guid) {
    if (guid == null) {
      return null;
    }
    for (VisitTypeEnum e : VisitTypeEnum.values()) {
      if (guid.equals(e.getGuid())) {
        return e;
      }
    }
    throw new IllegalArgumentException("Invalid Id" + guid);
  }
}
