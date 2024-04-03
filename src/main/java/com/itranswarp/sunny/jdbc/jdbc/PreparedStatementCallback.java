package com.itranswarp.sunny.jdbc.jdbc;

import jakarta.annotation.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhaoqw
 * @date 2024/3/20
 */
@FunctionalInterface
public interface PreparedStatementCallback<T> {
    @Nullable
    T doInPreparedStatement(PreparedStatement ps) throws SQLException;
}
