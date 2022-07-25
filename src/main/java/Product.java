public     class Product {

    private String productID;
    private String productPrice;
    private String productName;


    public Product(String productName, String productPrice, String productID) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getproductID() {
        return productID;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }


    public void setProductName() {
        this.productName = productName;
    }

    public void setproductID() {
        this.productID = productID;
    }

    public void setProductPrice() {
        this.productPrice = productPrice;
    }
}

