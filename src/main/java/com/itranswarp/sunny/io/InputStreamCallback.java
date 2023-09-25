package com.itranswarp.sunny.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * @author zhaoqw
 * @date 2023/9/25
 */
@FunctionalInterface
public interface InputStreamCallback<T> {
    T doWithInputStream(InputStream inputStream) throws IOException;
}
