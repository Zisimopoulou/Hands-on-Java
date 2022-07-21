public class OrderItem {
    private String orderItemID;
    private Order order;
    private Product product;
    private int quantity;
    public String getOrderItemID(){
        return orderItemID;
    }
    public int getQuantity(){
        return quantity;
    }
}
