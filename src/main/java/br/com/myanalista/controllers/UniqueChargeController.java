package br.com.myanalista.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.response.CustomerFromCustomerResponse;
import br.com.myanalista.services.CalendarService;
import br.com.myanalista.services.CityIneService;
import br.com.myanalista.services.ClusterGecService;
import br.com.myanalista.services.CustomerFromCustomerService;
import br.com.myanalista.services.NationalHolidayService;
import br.com.myanalista.services.RouteService;
import br.com.myanalista.services.SubChannelService;
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

   @Autowired
   private SubChannelService serviceSub;

   @Autowired
   private CustomerFromCustomerService serviceCustomer;

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

   @PostMapping("/test")
   public void save() throws IOException {

        serviceSub.recordDataToDb();
   }

   @PostMapping("/clients")
   public void chargeClients() throws IOException {

      serviceCustomer.recordDataToDb();
   }

   @GetMapping("/{code}")
   public CustomerFromCustomerResponse findByCode(@PathVariable(value = "code") String code){
      return serviceCustomer.findCustomerByCode(code);
   }

}
