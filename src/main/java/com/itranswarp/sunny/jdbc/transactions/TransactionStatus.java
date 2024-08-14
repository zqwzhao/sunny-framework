package com.itranswarp.sunny.jdbc.transactions;

import java.sql.Connection;

/**
 * @author zhaoqw
 * @date 2024/6/25
 */
public class TransactionStatus {
    public final Connection connection;

    public TransactionStatus(Connection connection) {
        this.connection = connection;
    }
}
