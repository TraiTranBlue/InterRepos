
package traitv.com.dbmanager.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "productid",
    "productname",
    "productcode",
    "productmain"
})
public class Productinfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("productid")
    private String productid;
    @JsonProperty("productname")
    private String productname;
    @JsonProperty("productcode")
    private String productcode;
    @JsonProperty("productmain")
    private String productmain;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("productid")
    public String getProductid() {
        return productid;
    }

    @JsonProperty("productid")
    public void setProductid(String productid) {
        this.productid = productid;
    }

    @JsonProperty("productname")
    public String getProductname() {
        return productname;
    }

    @JsonProperty("productname")
    public void setProductname(String productname) {
        this.productname = productname;
    }

    @JsonProperty("productcode")
    public String getProductcode() {
        return productcode;
    }

    @JsonProperty("productcode")
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    @JsonProperty("productmain")
    public String getProductmain() {
        return productmain;
    }

    @JsonProperty("productmain")
    public void setProductmain(String productmain) {
        this.productmain = productmain;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
