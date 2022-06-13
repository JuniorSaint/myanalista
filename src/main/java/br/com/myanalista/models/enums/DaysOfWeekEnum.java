package br.com.myanalista.models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum DaysOfWeekEnum {

  DOM(1, "doming"),
  SEG(2, "segunda-feira"),
  TER(3, "terça-feira"),
  QUA(4, "quarta-feira"),
  QUI(5, "quinta-feira"),
  SEX(6, "sexta-feira"),
  SAB(6, "sábado");


  private Integer guid;
  private String descriptionText;

  public static DaysOfWeekEnum toEnum(Integer guid) {
      if (guid == null) {
          return null;
      }
      for (DaysOfWeekEnum e : DaysOfWeekEnum.values()) {
          if (guid.equals(e.getGuid())) {
              return e;
          }
      }
      throw new IllegalArgumentException("Invalid Id" + guid);
  }
  
}
