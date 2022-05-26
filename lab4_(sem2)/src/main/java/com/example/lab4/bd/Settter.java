package com.example.lab4.bd;

import com.example.lab4.bd.classBD.*;

import java.util.ArrayList;

public class Settter {

    public Settter(DBProvider provider) {
        settSite(provider);
        settCompany(provider);
        settCountry(provider);
        settRegion(provider);
    }

    public void settSite(DBProvider provider) {

        ArrayList<Unit> units = provider.getUnits();
        ArrayList<Site> sites = provider.getSites();

        for (Site site: sites) {
            for (Unit unit : units) {
                if (site.getId() == unit.getSiteID()) {
                    site.setConsumption(unit.getConsumption());
                }
            }
        }
    }

    public void settCompany(DBProvider provider) {

        ArrayList<Site> sites = provider.getSites();
        ArrayList<Company> companies = provider.getCompanies();

        for (Site site: sites) {
            for (Company company : companies) {
                if (company.getId() == site.getCompanyID()) {
                    company.setConsumption(site.getConsumption());
                }
            }
        }
    }

    public void settCountry(DBProvider provider) {

        ArrayList<Site> sites = provider.getSites();
        ArrayList<Country> countries = provider.getCountries();

        for (Site site: sites) {
            for (Country country : countries) {
                if (site.getCountryID() == country.getId()) {
                    country.setConsumption(site.getConsumption());

                }
            }
        }
    }

    public void settRegion(DBProvider provider) {

        ArrayList<Region> regions = provider.getRegions();
        ArrayList<Country> countries = provider.getCountries();

        for (Region region: regions) {
            for (Country country : countries) {
                if (region.getId() == country.getRegionID()) {
                    region.setConsumption(country.getConsumption());

                }
            }
        }
    }



}
