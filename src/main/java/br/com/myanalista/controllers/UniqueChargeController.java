package br.com.myanalista.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.services.CityIneService;
import br.com.myanalista.services.ClusterGecService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/chargeunique")
@AllArgsConstructor
public class UniqueChargeController {

  @Autowired
  private CityIneService serviceCity;

  @Autowired
  private ClusterGecService serviceCluster;


  @PostMapping("/cityine")
  public void chargeCityIne() throws IOException {
        
     serviceCity.recordDataToDb();
  }
  @PostMapping("/clustergec")
  public void chargeCluster() throws IOException {
        
     serviceCluster.recordDataToDb();
  }
  
}
