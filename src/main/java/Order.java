public class Order {
    private String orderID;
    private Customer customer;

    public Order(String orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
