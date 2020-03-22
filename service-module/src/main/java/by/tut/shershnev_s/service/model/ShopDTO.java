package by.tut.shershnev_s.service.model;

import javax.validation.constraints.NotEmpty;

public class ShopDTO {

    private Long id;
    @NotEmpty(message = "Cant be empty")
    private String name;
    @NotEmpty(message = "Cant be empty")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
