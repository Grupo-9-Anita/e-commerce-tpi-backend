package tpi.backend.e_commerce.dto;


public class CreateProductDTO {

    private String name;

    private String description;

    private Double price;

    private Long SubCategory;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(Long SubCategory) {
        this.SubCategory = SubCategory;
    }
    
}
