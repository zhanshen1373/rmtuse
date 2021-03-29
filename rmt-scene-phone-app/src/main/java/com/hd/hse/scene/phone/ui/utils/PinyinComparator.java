package com.hd.hse.scene.phone.ui.utils;

import com.hd.hse.entity.resultdata.EqptType;
import com.hd.hse.entity.resultdata.WorkSite;

import java.util.Comparator;

public class PinyinComparator<T> implements Comparator<T> {


    public int compare(T o1, T o2) {
        if (o1 instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean &&
                o2 instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {

            WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean p1 = (WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) o1;
            WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean p2 = (WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) o2;

            if (p1.getSortLetters().equals("@")
                    || p2.getSortLetters().equals("#")) {
                return -1;
            } else if (p1.getSortLetters().equals("#")
                    || p2.getSortLetters().equals("@")) {
                return 1;
            } else {
                return p1.getSortLetters().compareTo(p2.getSortLetters());
            }

        } else if (o1 instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean &&
                o2 instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) {
            EqptType.MainvoBean.HeadVOBean.SYDICTDATABean p1 = (EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) o1;
            EqptType.MainvoBean.HeadVOBean.SYDICTDATABean p2 = (EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) o2;

            if (p1.getSortLetters().equals("@")
                    || p2.getSortLetters().equals("#")) {
                return -1;
            } else if (p1.getSortLetters().equals("#")
                    || p2.getSortLetters().equals("@")) {
                return 1;
            } else {
                return p1.getSortLetters().compareTo(p2.getSortLetters());
            }

        } else {
            return -1;
        }


    }


}
