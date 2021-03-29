package com.hd.hse.entity.other;

import com.hd.hse.common.entity.SuperEntity;

/**
 * created by yangning on 2017/5/17 15:55.
 */

public class DailogDataBean extends SuperEntity {
    private String descrption;
    private String desValue;

    public Boolean getIstitle() {
        return istitle;
    }

    public void setIstitle(Boolean istitle) {
        this.istitle = istitle;
    }

    private Boolean istitle = false;

    public DailogDataBean() {
    }

    public DailogDataBean(String descrption, String desValue, boolean istitle) {
        this.descrption = descrption;
        this.desValue = desValue;
        this.istitle = istitle;
    }

    public DailogDataBean(String descrption, String desValue) {
        this.descrption = descrption;
        this.desValue = desValue;
    }



    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getDesValue() {
        return desValue;
    }

    public void setDesValue(String desValue) {
        this.desValue = desValue;
    }
}
