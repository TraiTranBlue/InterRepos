package traitv.com.dbmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;

/**
 * Created by cpu11118-local on 10/08/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private ObjectId id;
    private String avatar;
    private String fullname;
    private int age;
    private String jobtitle;
    private String gender;
    private long createddateno;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getCreateddateno() {
        return createddateno;
    }

    public void setCreateddateno(long createddateno) {
        this.createddateno = createddateno;
    }
}
