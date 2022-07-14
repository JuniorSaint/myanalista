package br.com.myanalista.configs;

public interface CP {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String ATTIBUTE_BEARER = "Bearer ";
    public static final String SIGNATURE_KEY = "QSBzZW5oYSDDiSBwaG9kYSBtZXNzIGRvbid0IGZvcmdldCBzw7MgcGFyYSB0ZXIgbsOjbyBlbnRlbmRvIHBvcnF1ZQ==";
    public static final Long EXPIRATION = 3600l * 24 * 7;  // this value represent time in seconds
    public static final String ROOT = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/";

    public static final String HOST= "smtp.gmail.com";
    public static final Integer PORT=587;
    public static final String USER_NAME = "junior.garbage@gmail.com";
    public static final String PASSWORD = "******************";
    public static final Boolean AUTH =true;
    public static final Boolean ENABLE =true;
}

