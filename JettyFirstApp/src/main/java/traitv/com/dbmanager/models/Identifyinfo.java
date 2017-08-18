
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
    "value",
    "gamecode",
    "usertype",
    "datatype"
})
public class Identifyinfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private String value;
    @JsonProperty("gamecode")
    private String gamecode;
    @JsonProperty("usertype")
    private String usertype;
    @JsonProperty("datatype")
    private String datatype;
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

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("gamecode")
    public String getGamecode() {
        return gamecode;
    }

    @JsonProperty("gamecode")
    public void setGamecode(String gamecode) {
        this.gamecode = gamecode;
    }

    @JsonProperty("usertype")
    public String getUsertype() {
        return usertype;
    }

    @JsonProperty("usertype")
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @JsonProperty("datatype")
    public String getDatatype() {
        return datatype;
    }

    @JsonProperty("datatype")
    public void setDatatype(String datatype) {
        this.datatype = datatype;
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