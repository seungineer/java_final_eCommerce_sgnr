package dto;

public class ProductInsertDTO {
    private String productCode;    // 상품 코드 (직접 입력 or 자동 생성)
    private String productName;
    private String detail;
    private int salePrice;
    private int stock;
    private String startDate;
    private String endDate;
    private int saleStatus;

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public int getSalePrice() { return salePrice; }
    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public int getSaleStatus() { return saleStatus; }
    public void setSaleStatus(int saleStatus) { this.saleStatus = saleStatus; }
}
