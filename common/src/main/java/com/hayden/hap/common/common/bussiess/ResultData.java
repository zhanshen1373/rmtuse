package com.hayden.hap.common.common.bussiess;

import java.util.List;

/**
 * 接口返回数据标准格式
 * Created by liuyang on 2017/2/27.
 */

public class ResultData<T> {
    private Integer successfull;
    private List<String> messages;
    private T resultData;

    public Integer getSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(Integer successfull) {
        this.successfull = successfull;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
