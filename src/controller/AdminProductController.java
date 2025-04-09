package controller;

import dao.ProductDAO;
import dto.ProductInsertDTO;
import model.Product;

public class AdminProductController {
    private ProductDAO productDAO = new ProductDAO();

    public String diplayProductName(Product product) {
        if (product.getSaleStatus() == 0) return "<판매 중지> " + product.getProductName();
        if (product.getStock() == 0) return "<품절> " + product.getProductName();
        return product.getProductName();
    }

    public boolean registerProduct(ProductInsertDTO dto) {
        return productDAO.insertProduct(dto);
    }

    public boolean updateProduct(ProductInsertDTO dto) {
        return productDAO.updateProduct(dto);
    }

    public boolean deleteProduct(String productCode) {
        return productDAO.deleteProduct(productCode);
    }

    public boolean updateProductQuantity(String productCode, int updateStock) {
        return productDAO.updateProductQuantity(productCode, updateStock);
    }

    public boolean updateProductSaleStatus(String productCode, int saleStatus) {
        return productDAO.updateSaleStatus(productCode, saleStatus);
    }

}
