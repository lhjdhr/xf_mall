package org.wlgzs.xf_mall.util;

import java.io.FileWriter;
import java.io.IOException;
/**
 * @Auther: 阿杰
 * @Date: 2018/5/1 10:06
 * @Description:
 */
public class AlipayConfig{
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "APPID";//例：2016082600317257

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "商户私钥！！！！私钥！！！不是公钥！！！";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    // 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "支付宝公钥，记得是支付宝公钥!!!!!!!支付宝公钥";
    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 返回的时候此页面不会返回到用户页面，只会执行你写到控制器里的地址
     */
    public static String notify_url = "你的服务器地址/项目名称/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    /**
     * 此页面是同步返回用户页面，也就是用户支付后看到的页面，上面的notify_url是异步返回商家操作，谢谢
     * 要是看不懂就找度娘，或者多读几遍，或者去看支付宝第三方接口API，不看API直接拿去就用，遇坑不怪别人
     */
    public static String return_url = " 你的服务器地址/项目名称/return_url";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "gbk";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 日志地址
    public static String log_path = "D:/logs/";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord
     *        要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_"
                    + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
