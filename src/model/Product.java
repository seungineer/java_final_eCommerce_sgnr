package model;

public class Product {
    private String productCode;
    private String productName;
    private String detailExplain;
    private int salePrice;
    private int stock;
    private String startDate;
    private String endDate;

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getSalePrice() { return salePrice; }
    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return startDate; }
    public void setEndDate(String endDate) { this.startDate = endDate; }

    public String getDetailExplain() { return detailExplain; }
    public void setDetailExplain(String detailExplain) {this.detailExplain = detailExplain; }
}
