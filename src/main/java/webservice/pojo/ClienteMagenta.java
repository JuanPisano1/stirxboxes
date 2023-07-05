package webservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteMagenta {
    public String getName() {
        return name;
    }

    public ClienteMagenta setName(String name) {
        this.name = name;
        return this;
    }

    public Services getServices() {
        return services;
    }

    public ClienteMagenta setServices(Services services) {
        this.services = services;
        return this;
    }

    private String name;
    private Services services;
}




