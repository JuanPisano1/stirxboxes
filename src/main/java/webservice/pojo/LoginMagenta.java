package webservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginMagenta {
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
    public LoginMagenta setAccess_token(String access_token) {
        this.access_token = access_token;
        return this;
    }

}
