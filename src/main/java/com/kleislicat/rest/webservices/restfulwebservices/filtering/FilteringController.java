package com.kleislicat.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public MappingJacksonValue filtering() {
    SomeBean someBean = new SomeBean("field1", "field2", "field3");

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

    createFilters(mappingJacksonValue, "field1", "field3");

    return mappingJacksonValue;
  }

  @GetMapping("/filtering-list")
  public MappingJacksonValue filteringList() {
    List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
        new SomeBean("value4", "value5", "value6"));

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

    createFilters(mappingJacksonValue, "field2", "field3");

    return mappingJacksonValue;

  }

  private static void createFilters(MappingJacksonValue mappingJacksonValue,
      String... includedFields) {
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(includedFields);

    FilterProvider filters =
        new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

    mappingJacksonValue.setFilters(filters);
  }

}
