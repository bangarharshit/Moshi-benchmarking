package com.benchmark.model;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalExperience {

  public Company primary_company;
  public List<Company> companies = new ArrayList<>();
  public List<Award> awards = new ArrayList<>();

  @Override
  public String toString() {
    return "ProfessionalExperience{" +
        "primary_company=" + primary_company +
        ", companies=" + companies +
        ", awards=" + awards +
        '}';
  }
}
