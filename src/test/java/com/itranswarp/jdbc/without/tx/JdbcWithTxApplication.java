package com.itranswarp.jdbc.without.tx;

import com.itranswarp.sunny.annotation.ComponentScan;
import com.itranswarp.sunny.annotation.Configuration;
import com.itranswarp.sunny.annotation.Import;
import com.itranswarp.sunny.jdbc.jdbc.JdbcConfiguration;

@ComponentScan
@Configuration
@Import(JdbcConfiguration.class)
public class JdbcWithTxApplication {

}