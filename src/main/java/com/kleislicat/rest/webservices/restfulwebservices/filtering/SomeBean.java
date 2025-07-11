package com.kleislicat.rest.webservices.restfulwebservices.filtering;

//@JsonIgnoreProperties({"field1","field2"})  <-- Static filtering

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")    // (Dynamic filtering) <-- See FilteringController
public class SomeBean {

  private String field1;

  private String field2;

  //@JsonIgnore  <- Static Filtering
  private String field3;

  public SomeBean(String field1, String field2, String field3) {
    this.field1 = field1;
    this.field2 = field2;
    this.field3 = field3;
  }

  public String getField1() {
    return field1;
  }

  public String getField2() {
    return field2;
  }

  public String getField3() {
    return field3;
  }


  @Override
  public String toString() {
    return "SomeBean{" +
        "field1='" + field1 + '\'' +
        ", field2='" + field2 + '\'' +
        ", field3='" + field3 + '\'' +
        '}';
  }
}
