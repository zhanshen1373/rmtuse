package com.hd.hse.common.component.phone.util;

import com.hd.hse.entity.sys.RmtSysUser;

import java.util.Comparator;

public class PinyinComparator implements Comparator<RmtSysUser> {

	public int compare(RmtSysUser o1, RmtSysUser o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
