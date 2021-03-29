package com.hayden.hap.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * SHA512算法加密/解密工具类
 *
 * @author huangyu
 *         2016-4-18
 */
public class SHAUtils {
    private SHAUtils() {
    }

    ;
    private static final LogUtil LOGGER = new LogUtil(SHAUtils.class);
    /**
     * 算法名称
     */
    private static final String ALGORITHOM_SHA512 = "SHA-512";
    /**
     * 算法名称
     */
    private static final String ALGORITHOM_SHA256 = "SHA-256";
    /**
     * 算法名称
     */
    private static final String ALGORITHOM_HMAC_SHA256 = "HmacSHA256";
    /**
     * 算法名称
     */
    private static final String ALGORITHOM_HMAC_SHA512 = "HmacSHA512";

    /**
     * SHA-256 不可逆加密
     *
     * @param content 加密内容
     * @return 加密后的密文字符串
     */
    public static String sha256Encrypt(String content) {
        return encryptPub(ALGORITHOM_SHA256, content);
    }

    /**
     * SHA-512 不可逆加密
     *
     * @param content 加密内容
     * @return 加密后的密文字符串
     */
    public static String sha512Encrypt(String content) {
        return encryptPub(ALGORITHOM_SHA512, content);
    }

    /**
     * 加密方法
     *
     * @param algorithom 加密类型
     * @param content    加密内容
     * @return 加密后的密文字符串
     */
    private static String encryptPub(String algorithom, String content) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(algorithom);
            byte[] bt = messageDigest.digest(content.getBytes());
            String resultCont = SecurityPubUtils.parseByte2HexStr(bt);
            return resultCont.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private static KeyGenerator kgen256 = null;
    private static KeyGenerator kgen512 = null;

    static {
        try {
            kgen256 = KeyGenerator.getInstance(ALGORITHOM_HMAC_SHA256);
            kgen512 = KeyGenerator.getInstance(ALGORITHOM_HMAC_SHA512);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 生成一个新的HmacSHA256密钥。
     *
     * @return 256位密钥对象
     */
    public static SecretKey generateKey256() {
        return generateKey(kgen256);
    }

    /**
     * 生成一个新的HmacSHA512密钥。
     *
     * @return 512位密钥对象
     */
    public static SecretKey generateKey512() {
        return generateKey(kgen512);
    }

    /**
     * 生成一个新的HmacSHA密钥。
     *
     * @return 密钥对象
     */
    private static SecretKey generateKey(KeyGenerator kgen) {
        return kgen.generateKey();
    }

    /**
     * 获取密钥的密码字符串
     *
     * @param secretKey
     * @return 密钥字符串
     */
    public static String getSecretKeyStr(SecretKey secretKey) {
        return Hex.encodeHexString(secretKey.getEncoded());
    }

    /**
     * 生成一个新的HmacSHA256密钥的密码字符串
     *
     * @return 密钥字符串
     */
    public static String get256NewSecretKeyStr() {
        return getSecretKeyStr(generateKey256());
    }

    /**
     * 生成一个新的HmacSHA512密钥的密码字符串
     *
     * @return 密钥字符串
     */
    public static String get512NewSecretKeyStr() {
        return getSecretKeyStr(generateKey512());
    }

    /**
     * HMAC_SHA256不可逆加密方法
     *
     * @param key     密钥字符串
     * @param content 加密类容
     * @return 加密后的密文
     */
    public static String hmacSha256Encrypt(String key, String content) {
        try {
            Mac sha256_HMAC = Mac.getInstance(ALGORITHOM_HMAC_SHA256);
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), ALGORITHOM_HMAC_SHA256);
            sha256_HMAC.init(secret_key);
            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(content.getBytes()));
            String enEontent = "";
            if (hash != null) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(hash);
                enEontent = m.replaceAll("");
            }
            return enEontent.toUpperCase();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * @param key     密钥字符串
     * @param content 加密类容
     * @param charSet 内容编码方式
     * @return 加密后的密文
     */
    public static String hmacSha512EncryptByCharSet(String key, String content, String charSet) {
        try {
            Mac sha512_HMAC = Mac.getInstance(ALGORITHOM_HMAC_SHA512);
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), ALGORITHOM_HMAC_SHA512);
            sha512_HMAC.init(secret_key);
            byte[] contByte = content.getBytes();
            if (charSet != null && !"".equals(charSet)) {
                contByte = content.getBytes(charSet);
            }
            String hash = Base64.encodeBase64String(sha512_HMAC.doFinal(contByte));
            String enEontent = "";
            if (hash != null) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(hash);
                enEontent = m.replaceAll("");
            }
            return enEontent.toUpperCase();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * HMAC_SHA512不可逆加密方法
     *
     * @param key     密钥字符串
     * @param content 加密类容
     * @return 加密后的密文
     */
    public static String hmacSha512Encrypt(String key, String content) {
        return hmacSha512EncryptByCharSet(key, content, "");
    }
}
