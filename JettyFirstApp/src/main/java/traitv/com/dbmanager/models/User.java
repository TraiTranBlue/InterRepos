package traitv.com.dbmanager.models;

import com.fasterxml.jackson.annotation.*;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpu11118-local on 10/08/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true,value = { })
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "avatar",
        "fullname",
        "jobtitle",
        "gender",
        "createdby",
        "createddateno",
        "lastupdatedateno",
        "customercode",
        "lastupdateby",
        "identifyinfo",
        "taginfo",
        "emailcontact",
        "phonecontact",
        "socialinfo",
        "addressinfo",
        "cidinfo",
        "notes",
        "productinfo",
        "contactowner",
        "attachfile",
        "birthday",
        "profile_type",
        "categories_list",
        "tenant_id",
        "source_contact_id",
        "status",
        "favorite_list",
        "profileid"
})
public class User {

    @JsonProperty("_id")
    private Object id;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("jobtitle")
    private String jobtitle;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("createdby")
    private String createdby;
    @JsonProperty("createddateno")
    private String createddateno;
    @JsonProperty("lastupdatedateno")
    private String lastupdatedateno;
    @JsonProperty("customercode")
    private String customercode;
    @JsonProperty("lastupdateby")
    private String lastupdateby;
    @JsonProperty("identifyinfo")
    private List<Identifyinfo> identifyinfo = null;
    @JsonProperty("taginfo")
    private List<Taginfo> taginfo = null;
    @JsonProperty("emailcontact")
    private List<Emailcontact> emailcontact = null;
    @JsonProperty("phonecontact")
    private List<Object> phonecontact = null;
    @JsonProperty("socialinfo")
    private List<Socialinfo> socialinfo = null;
    @JsonProperty("addressinfo")
    private List<Addressinfo> addressinfo = null;
    @JsonProperty("cidinfo")
    private List<Cidinfo> cidinfo = null;
    @JsonProperty("notes")
    private List<Note> notes = null;
    @JsonProperty("productinfo")
    private List<Productinfo> productinfo = null;
    @JsonProperty("contactowner")
    private List<Contactowner> contactowner = null;
    @JsonProperty("attachfile")
    private List<Attachfile> attachfile = null;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("profile_type")
    private String profileType;
    @JsonProperty("categories_list")
    private List<String> categoriesList = null;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("source_contact_id")
    private String sourceContactId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("favorite_list")
    private List<FavoriteList> favoriteList = null;
    @JsonProperty("profileid")
    private String profileid;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_id")
    public Object getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(Object id) {
        this.id = id;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("fullname")
    public String getFullname() {
        return fullname;
    }

    @JsonProperty("fullname")
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @JsonProperty("jobtitle")
    public String getJobtitle() {
        return jobtitle;
    }

    @JsonProperty("jobtitle")
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("createdby")
    public String getCreatedby() {
        return createdby;
    }

    @JsonProperty("createdby")
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @JsonProperty("createddateno")
    public String getCreateddateno() {
        return createddateno;
    }

    @JsonProperty("createddateno")
    public void setCreateddateno(String createddateno) {
        this.createddateno = createddateno;
    }

    @JsonProperty("lastupdatedateno")
    public String getLastupdatedateno() {
        return lastupdatedateno;
    }

    @JsonProperty("lastupdatedateno")
    public void setLastupdatedateno(String lastupdatedateno) {
        this.lastupdatedateno = lastupdatedateno;
    }

    @JsonProperty("customercode")
    public String getCustomercode() {
        return customercode;
    }

    @JsonProperty("customercode")
    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @JsonProperty("lastupdateby")
    public String getLastupdateby() {
        return lastupdateby;
    }

    @JsonProperty("lastupdateby")
    public void setLastupdateby(String lastupdateby) {
        this.lastupdateby = lastupdateby;
    }

    @JsonProperty("identifyinfo")
    public List<Identifyinfo> getIdentifyinfo() {
        return identifyinfo;
    }

    @JsonProperty("identifyinfo")
    public void setIdentifyinfo(List<Identifyinfo> identifyinfo) {
        this.identifyinfo = identifyinfo;
    }

    @JsonProperty("taginfo")
    public List<Taginfo> getTaginfo() {
        return taginfo;
    }

    @JsonProperty("taginfo")
    public void setTaginfo(List<Taginfo> taginfo) {
        this.taginfo = taginfo;
    }

    @JsonProperty("emailcontact")
    public List<Emailcontact> getEmailcontact() {
        return emailcontact;
    }

    @JsonProperty("emailcontact")
    public void setEmailcontact(List<Emailcontact> emailcontact) {
        this.emailcontact = emailcontact;
    }

    @JsonProperty("phonecontact")
    public List<Object> getPhonecontact() {
        return phonecontact;
    }

    @JsonProperty("phonecontact")
    public void setPhonecontact(List<Object> phonecontact) {
        this.phonecontact = phonecontact;
    }

    @JsonProperty("socialinfo")
    public List<Socialinfo> getSocialinfo() {
        return socialinfo;
    }

    @JsonProperty("socialinfo")
    public void setSocialinfo(List<Socialinfo> socialinfo) {
        this.socialinfo = socialinfo;
    }

    @JsonProperty("addressinfo")
    public List<Addressinfo> getAddressinfo() {
        return addressinfo;
    }

    @JsonProperty("addressinfo")
    public void setAddressinfo(List<Addressinfo> addressinfo) {
        this.addressinfo = addressinfo;
    }

    @JsonProperty("cidinfo")
    public List<Cidinfo> getCidinfo() {
        return cidinfo;
    }

    @JsonProperty("cidinfo")
    public void setCidinfo(List<Cidinfo> cidinfo) {
        this.cidinfo = cidinfo;
    }

    @JsonProperty("notes")
    public List<Note> getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @JsonProperty("productinfo")
    public List<Productinfo> getProductinfo() {
        return productinfo;
    }

    @JsonProperty("productinfo")
    public void setProductinfo(List<Productinfo> productinfo) {
        this.productinfo = productinfo;
    }

    @JsonProperty("contactowner")
    public List<Contactowner> getContactowner() {
        return contactowner;
    }

    @JsonProperty("contactowner")
    public void setContactowner(List<Contactowner> contactowner) {
        this.contactowner = contactowner;
    }

    @JsonProperty("attachfile")
    public List<Attachfile> getAttachfile() {
        return attachfile;
    }

    @JsonProperty("attachfile")
    public void setAttachfile(List<Attachfile> attachfile) {
        this.attachfile = attachfile;
    }

    @JsonProperty("birthday")
    public String getBirthday() {
        return birthday;
    }

    @JsonProperty("birthday")
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("profile_type")
    public String getProfileType() {
        return profileType;
    }

    @JsonProperty("profile_type")
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    @JsonProperty("categories_list")
    public List<String> getCategoriesList() {
        return categoriesList;
    }

    @JsonProperty("categories_list")
    public void setCategoriesList(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @JsonProperty("tenant_id")
    public String getTenantId() {
        return tenantId;
    }

    @JsonProperty("tenant_id")
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @JsonProperty("source_contact_id")
    public String getSourceContactId() {
        return sourceContactId;
    }

    @JsonProperty("source_contact_id")
    public void setSourceContactId(String sourceContactId) {
        this.sourceContactId = sourceContactId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("favorite_list")
    public List<FavoriteList> getFavoriteList() {
        return favoriteList;
    }

    @JsonProperty("favorite_list")
    public void setFavoriteList(List<FavoriteList> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @JsonProperty("profileid")
    public String getProfileid() {
        return profileid;
    }

    @JsonProperty("profileid")
    public void setProfileid(String profileid) {
        this.profileid = profileid;
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
