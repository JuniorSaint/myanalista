 package br.com.myanalista.controllers;

 import br.com.myanalista.models.response.CustomerResponse;
 import br.com.myanalista.services.*;
 import io.swagger.v3.oas.annotations.tags.Tag;
 import lombok.AllArgsConstructor;
 import org.springframework.web.bind.annotation.*;

 import java.io.IOException;

 @RestController
 @CrossOrigin(origins = "*", maxAge = 60 * 60)
 @RequestMapping("/v1/chargeunique")
 @AllArgsConstructor
 @Tag(name = "Unique charge", description = "Charge only once the files")
 public class UniqueChargeController {
    private CityIneService serviceCity;
    private ClusterGecService serviceCluster;
    private VisitDayService serviceVisitDay;
    private CalendarService serviceCalendar;
    private NationalHolidayService serviceNational;
    private SubChannelService serviceSub;
    private CustomerService serviceCustomer;
    private ChannelService serviceChannel;
    private TeamsService serviceTeams;
    private DistributorService serviceDistributor;
    private ProductService serviceProduct;
    private TurnoverService serviceTurnover;
    private CategoryService serviceCategory;

    @PostMapping("/cityine")
    public void chargeCityIne() throws IOException {
       serviceCity.recordDataToDb();
    }
    @PostMapping("/category")
    public void chargeCategory() throws IOException {
       serviceCategory.recordDataToDb();
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

    @PostMapping("/channel")
    public void chargeChannel() throws IOException {

       serviceChannel.recordDataToDb();
    }

    @PostMapping("/subchannel")
    public void chargeSubChannel() throws IOException {

       serviceSub.recordDataToDb();
    }

    @PostMapping("/turnover")
    public void chargeTurnover() throws IOException {

       serviceTurnover.recordDataToDb();
    }

    @PostMapping("/seller")
    public void chargeSeller() throws IOException {

       serviceTeams.recordDataToDb();
    }

    @PostMapping("/distributor")
    public void chargeDistributor() throws IOException {

       serviceDistributor.recordDataToDb();
    }

    @PostMapping("/product")
    public void chargeProduct() throws IOException {

       serviceProduct.recordDataToDb();
    }

    @GetMapping("/{code}")
    public CustomerResponse findByCode(@PathVariable(value = "code") String code) {
       return serviceCustomer.findCustomerByCode(code);
    }
 }
