package com.hayden.hap.common.common.bussiess;

/**
 * Created by owlla on 2017/2/27.
 */

public interface HttpCallBack<T> {
    public void success(T t);

    public void warning(String msg);

    public void error(Throwable t);
}
