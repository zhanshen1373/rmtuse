package com.hd.hse.dc.business.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: NumberValidationUtils (数字校验工具)<br/>
 * date: 2015年7月2日  <br/>
 *
 * @author lxf
 * @version 
 */
public class NumberValidationUtils {
	private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
  
    /**
     * isPositiveInteger:(判断是否正数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author lxf
     * @param orginal
     * @return
     */
    public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);  
    }  
  
    /**
     * isNegativeInteger:(判断是否负数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author lxf
     * @param orginal
     * @return
     */
    public static boolean isNegativeInteger(String orginal) {  
        return isMatch("^-[1-9]\\d*", orginal);  
    }  
  
    /**
     * isWholeNumber:(是否整数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author lxf
     * @param orginal
     * @return
     */
    public static boolean isWholeNumber(String orginal) {  
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);  
    }  
      
    /**
     * isPositiveDecimal:(是否正数小数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author lxf
     * @param orginal
     * @return
     */
    public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    /**
     * isNegativeDecimal:(是否负数小数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author lxf
     * @param orginal
     * @return
     */
    public static boolean isNegativeDecimal(String orginal){  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    /**
     * isDecimal:(是否小数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author Administrator
     * @param orginal
     * @return
     */
    public static boolean isDecimal(String orginal){  
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);  
    }  
      
    /**
     * isRealNumber:(是否小数或正数). <br/>
     * date: 2015年7月2日 <br/>
     *
     * @author lxf
     * @param orginal
     * @return
     */
    public static boolean isRealNumber(String orginal){  
        return isWholeNumber(orginal) || isDecimal(orginal);  
    }  
}
