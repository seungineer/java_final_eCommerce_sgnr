package exception;

public class NotValidModifyQuantityCommandException extends Exception {
    public NotValidModifyQuantityCommandException() {
        super("올바른 상품 재고 및 수량을 입력해주세요");
    }

    public NotValidModifyQuantityCommandException(String message) {
        super(message);
    }
}
