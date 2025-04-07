package controller;

import dao.ProductDAO;
import dto.ProductInsertDTO;

public class AdminProductController {
    private ProductDAO productDAO = new ProductDAO();

    public boolean registerProduct(ProductInsertDTO dto) {
        return productDAO.insertProduct(dto);
    }
}
