package br.com.myanalista.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.services.CalendarService;
import br.com.myanalista.services.CityIneService;
import br.com.myanalista.services.ClusterGecService;
import br.com.myanalista.services.NationalHolidayService;
import br.com.myanalista.services.RouteService;
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

   @Autowired
   private RouteService serviceRoute;

   @Autowired
   private CalendarService serviceCalendar;

   @Autowired
   private NationalHolidayService serviceNational;

   @PostMapping("/cityine")
   public void chargeCityIne() throws IOException {

      serviceCity.recordDataToDb();
   }

   @PostMapping("/clustergec")
   public void chargeCluster() throws IOException {

      serviceCluster.recordDataToDb();
   }

   @PostMapping("/route")
   public void chargeRoute() throws IOException {

      serviceRoute.recordDataToDb();
   }

   @PostMapping("/calendar")
   public void chargeCalendar() throws IOException {

      serviceCalendar.recordDataToDb();
   }

   @PostMapping("/nationalholidays")
   public void chargeNationalHolidays() throws IOException {

      serviceNational.recordDataToDb();
   }

}
