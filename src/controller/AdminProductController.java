package controller;

import dao.ProductDAO;
import dto.ProductInsertDTO;

public class AdminProductController {
    private ProductDAO productDAO = new ProductDAO();

    public boolean registerProduct(ProductInsertDTO dto) {
        return productDAO.insertProduct(dto);
    }

    public boolean updateProduct(ProductInsertDTO dto) {
        return productDAO.updateProduct(dto);
    }

    public boolean deleteProduct(String productCode) {
        return productDAO.deleteProduct(productCode);
    }

    public boolean updateProductQuantity(String productCode, int newStock) {
        return productDAO.updateProductQuantity(productCode, newStock);
    }

}
