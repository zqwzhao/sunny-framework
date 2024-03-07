package com.itranswarp.scan.proxy;

import com.itranswarp.scan.proxy.OriginBean;

/**
 * @author zhaoqw
 * @date 2024/3/7
 */
public class FirstProxyBean extends OriginBean {
    final OriginBean target;

    public FirstProxyBean(OriginBean target) {
        this.target = target;
    }

    @Override
    public void setVersion(String version) {
        target.setVersion(version);
    }

    @Override
    public String getName() {
        return target.getName();
    }

    @Override
    public String getVersion() {
        return target.getVersion();
    }
}
