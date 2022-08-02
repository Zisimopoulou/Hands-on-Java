package com.team7.handsOnJava.enums;

import java.util.stream.Stream;

public enum TablesDropCreate {

    ORDERITEMDROP("drop","drop.table.orderitem"),
    ORDERDROP("drop","drop.table.orders"),
    PRODUCTDROP("drop","drop.table.product"),
    CUSTOMERADDRESSDROP("drop","drop.table.customeraddress"),
    CUSTOMERTYPEOFDROP("drop","drop.table.typeofcustomer"),
    CUSTOMERDROP("drop","drop.table.customer"),
    PRODUCTCREATE("create","create.table.product"),
    CUSTOMERCREATE("create","create.table.customer"),
    CUSTOMERADDRESSCREATE("create","create.table.customeraddress"),
    CUSTOMERTYPEOFCREATE("create","create.table.typeofcustomer"),
    ORDERCREATE("create","create.table.orders"),
    ORDERITEMCREAT("create","create.table.orderitem");

    private final String dropOrCreate, table;
    TablesDropCreate(String dropOrCreate, String table) {
        this.dropOrCreate = dropOrCreate;
        this.table = table;
    }

    public String getDropOrCreate() {
        return dropOrCreate;
    }

    public String getTable() {
        return table;
    }

    public static Stream<TablesDropCreate> stream() {
        return Stream.of(TablesDropCreate.values());
    }

}
