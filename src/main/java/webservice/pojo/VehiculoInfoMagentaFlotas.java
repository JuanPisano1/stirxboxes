package webservice.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehiculoInfoMagentaFlotas extends VehiculoMagentaFlotas {
    @JsonProperty("chassis_number")
    private String chassis_number;
    @JsonProperty("engine_number")
    private String engine_number;

    public String getChasis() {
        return chassis_number;
    }

    public VehiculoInfoMagentaFlotas setChasis(String chasis) {
        this.chassis_number = chasis;
        return this;
    }

    public String getEngine() {
        return engine_number;
    }

    public VehiculoInfoMagentaFlotas setEngine(String engine) {
        this.engine_number = engine;
        return this;
    }


}
