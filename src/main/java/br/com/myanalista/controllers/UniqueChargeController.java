package br.com.myanalista.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.response.CustomerResponse;
import br.com.myanalista.services.CalendarService;
import br.com.myanalista.services.ChannelService;
import br.com.myanalista.services.CityIneService;
import br.com.myanalista.services.ClusterGecService;
import br.com.myanalista.services.CustomerService;
import br.com.myanalista.services.LendingService;
import br.com.myanalista.services.NationalHolidayService;
import br.com.myanalista.services.VisitDayService;
import br.com.myanalista.services.SellOutService;
import br.com.myanalista.services.SubChannelService;
import br.com.myanalista.services.TurnoverService;
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
   private VisitDayService serviceVisitDay;

   @Autowired
   private CalendarService serviceCalendar;

   @Autowired
   private NationalHolidayService serviceNational;

   @Autowired
   private SubChannelService serviceSub;

   @Autowired
   private CustomerService serviceCustomer;

   @Autowired
   private ChannelService serviceChannel;

   @Autowired
   private SellOutService serviceSellOut;

   @Autowired
   private LendingService serviceLending;

@Autowired
private TurnoverService serviceTurnover;

   @PostMapping("/cityine")
   public void chargeCityIne() throws IOException {

      serviceCity.recordDataToDb();
   }

   @PostMapping("/clustergec")
   public void chargeCluster() throws IOException {

      serviceCluster.recordDataToDb();
   }

   @PostMapping("/visitday")
   public void chargeRoute() throws IOException {

      serviceVisitDay.recordDataToDb();
   }

   @PostMapping("/calendar")
   public void chargeCalendar() throws IOException {

      serviceCalendar.recordDataToDb();
   }

   @PostMapping("/nationalholidays")
   public void chargeNationalHolidays() throws IOException {

      serviceNational.recordDataToDb();
   }


   @PostMapping("/clients")
   public void chargeClients() throws IOException {

      serviceCustomer.recordDataToDb();
   }
   @PostMapping("/channel")
   public void chargeChannel() throws IOException {

      serviceChannel.recordDataToDb();
   }

   @PostMapping("/subchannel")
   public void chargeSubChannel() throws IOException {

      serviceSub.recordDataToDb();
   }

   @PostMapping("/sellout")
   public void chargeSellOut() throws IOException {

      serviceSellOut.recordDataToDb();
   }

   @PostMapping("/lending")
   public void chargeLending() throws IOException {

      serviceLending.recordDataToDb();
   }

   @PostMapping("/turnover")
   public void chargeTurnover() throws IOException {

      serviceTurnover.recordDataToDb();
   }

   @GetMapping("/{code}")
   public CustomerResponse findByCode(@PathVariable(value = "code") String code){
      return serviceCustomer.findCustomerByCode(code);
   }

}
