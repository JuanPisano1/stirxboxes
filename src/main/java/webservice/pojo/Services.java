package webservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Services {
    @JsonProperty("mrn:service:vehicle_premium")
    public VehiclePremium service;

    public VehiclePremium getVehiclePremium() {
        return service;
    }

    public VehiclePremium setEscortPremium(VehiclePremium service) {
        this.service = service;
        return (VehiclePremium) this;
    }


}