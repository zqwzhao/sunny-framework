package com.itranswarp.sunny.utils;

import com.itranswarp.sunny.jdbc.transactions.TransactionStatus;
import com.itranswarp.sunny.jdbc.transactions.impl.DataSourceTransactionManager;
import jakarta.annotation.Nullable;

import java.sql.Connection;

/**
 * @author zhaoqw
 * @date 2024/6/26
 */
public class TransactionalUtils {
    @Nullable
    public static Connection getCurrentConnection() {
        TransactionStatus ts = DataSourceTransactionManager.transactionStatus.get();
        return ts == null ? null : ts.connection;
    }
}
