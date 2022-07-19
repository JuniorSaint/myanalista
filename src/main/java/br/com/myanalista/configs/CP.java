package br.com.myanalista.configs;

public interface CP {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String ATTIBUTE_BEARER = "Bearer ";
    public static final String SIGNATURE_KEY = "QSBzZW5oYSDDiSBwaG9kYSBtZXNzIGRvbid0IGZvcmdldCBzw7MgcGFyYSB0ZXIgbsOjbyBlbnRlbmRvIHBvcnF1ZQ==";
    public static final Long EXPIRATION = 3600l * 24 * 7;  // this value represent time in seconds
    public static final String ROOT = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/";


    public static final String ACCESS_KEY_ID = "AKIAVHMEDDNN5WO6QQZC";
    public static final String SECRET_ACCESS_KEY = "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";
    public static final String BUCKET = "myanalista-dev";
    public static final String REGION = "us-east-2";
    public static final Boolean STACK_AUTO = false;
}

