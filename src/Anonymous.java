import java.math.BigInteger;

public class Anonymous {

    private Integer id;
    private String email;
    private String country;

    public Anonymous() {
    }

    public Anonymous(String email, String country) {
        this.email = email;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Anonymous{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
