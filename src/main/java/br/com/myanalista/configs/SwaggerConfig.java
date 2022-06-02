// package br.com.myanalista.configs;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.service.VendorExtension;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;

// import java.util.ArrayList;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public Docket api() {
//         return new Docket()
//                 .select()
//                 .apis(RequestHandlerSelectors.any())
//                 .paths(PathSelectors.any())
//                 .build()
//                 .apiInfo(metaInfo());
//     }

//     private ApiInfo metaInfo() {

//         ApiInfo apiInfo = new ApiInfo(
//                 "My Analista",
//                 "API to consume document from doc",
//                 "1.0",
//                 "Terms of Service",
//                 new Contact("José Atanazio", "https://www.idip.com/",
//                         "junior@idip.com"),
//                 "Apache License Version 2.0",
//                 "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>());

//         return apiInfo;
//     }
// }