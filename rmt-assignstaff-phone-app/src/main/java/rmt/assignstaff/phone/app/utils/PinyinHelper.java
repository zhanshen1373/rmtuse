package rmt.assignstaff.phone.app.utils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PinyinHelper {
    private static PinyinHelper instance;
    private BufferedInputStream bos;
    private BufferedInputStream bis;
    private Properties properties = null;

    public static String[] getUnformattedHanyuPinyinStringArray(char ch) {
        return instance.getHanyuPinyinStringArray(ch);
    }

    public PinyinHelper(boolean qporjp) {
        if (qporjp) {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            initResourceqp();
        } else {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            initResourcejp();
        }
    }

    private PinyinHelper() {
        //默认使用全拼
//        initResourceqp();

    }

    public static PinyinHelper getInstance(boolean p) {
        if (p) {
            instance = new PinyinHelper(p);
        } else {
            instance = new PinyinHelper(p);
        }

        return instance;
    }


    //全拼
    private void initResourceqp() {
        try {

            final String resourceNamebos = "/assets/unicode_to_hanyunew_pinyin.txt";

            bos = new BufferedInputStream(PinyinHelper.class.getResourceAsStream(resourceNamebos));
            properties = new Properties();
            properties.load(bos);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //简拼
    private void initResourcejp() {
        try {

            final String resourceName = "/assets/unicode_to_simple_pinyin.txt";

            bis = new BufferedInputStream(PinyinHelper.class.getResourceAsStream(resourceName));
            properties = new Properties();
            properties.load(bis);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String[] getHanyuPinyinStringArray(char ch) {

        String pinyinRecord = getHanyuPinyinRecordFromChar(ch);

        if (null != pinyinRecord) {
            int indexOfLeftBracket = pinyinRecord.indexOf(Field.LEFT_BRACKET);
            int indexOfRightBracket = pinyinRecord.lastIndexOf(Field.RIGHT_BRACKET);

            String stripedString = pinyinRecord.substring(indexOfLeftBracket
                    + Field.LEFT_BRACKET.length(), indexOfRightBracket);

            return stripedString.split(Field.COMMA);

        } else
            return null;

    }

    private String getHanyuPinyinRecordFromChar(char ch) {
        int codePointOfChar = ch;
        String codepointHexStr = Integer.toHexString(codePointOfChar).toUpperCase();
        String foundRecord = properties.getProperty(codepointHexStr);
        return foundRecord;
    }

    class Field {
        static final String LEFT_BRACKET = "(";
        static final String RIGHT_BRACKET = ")";
        static final String COMMA = ",";
    }

}
