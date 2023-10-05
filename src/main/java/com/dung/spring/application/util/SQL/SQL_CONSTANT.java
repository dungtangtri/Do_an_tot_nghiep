package com.dung.spring.application.util.SQL;

public final class SQL_CONSTANT {
    public static final String GET_USER_PROFILE_BY_ID =
            "SELECT tbl.user_id," +
                    "tbl.order_id," +
                    "tbl.username," +
                    "tbl.email," +
                    "tbl.customer_name," +
                    "tbl.create_time," +
                    "tbl.order_description," +
                    "scm.amount," +
                    "scm.product_name " +
                    "FROM " +
                    "(SELECT u.id AS user_id," +
                    "od.id AS order_id,u.username," +
                    "u.email," +
                    "od.customer_name," +
                    "od.create_time," +
                    "od.order_description " +
                    "FROM users u LEFT JOIN order_detail od ON u.username = od.username where u.id = :id )" +
                    "AS tbl LEFT JOIN shopping_cart_model scm ON tbl.order_id = scm.order_id";
}
