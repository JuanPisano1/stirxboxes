package webservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiculoMagentaFlotas {
    @JsonProperty("things")
    private String[] things;

    @JsonProperty("info")
    private VehiculoInfoMagentaFlotas info;

    public String[] getThings() {
        return things;
    }

    public VehiculoMagentaFlotas setThings(String[] things) {
        this.things = things;
        return this;
    }

    public VehiculoInfoMagentaFlotas getInfo() {
        return info;
    }

    public VehiculoMagentaFlotas setInfo(VehiculoInfoMagentaFlotas info) {
        this.info = info;
        return this;
    }
}




