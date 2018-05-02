package org.wlgzs.xf_mall.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.wlgzs.xf_mall.util.AlipayConfig;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/30 10:20
 * @Description:
 */
@Controller
@RequestMapping("pay/alipay")
public class AlipayController {
    /*@RequestMapping("alipaySum")
    public Object alipayIumpSum(Model model, String payables, String subject, String body, HttpServletResponse response)
            throws Exception {
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigInfo.gatewayUrl, AlipayConfigInfo.app_id,
                AlipayConfigInfo.merchant_private_key, "json", AlipayConfigInfo.charset,
                AlipayConfigInfo.alipay_public_key, AlipayConfigInfo.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfigInfo.return_url);
        alipayRequest.setNotifyUrl(AlipayConfigInfo.notify_url2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = sdf.format(new Date());
        // 付款金额，必填
        String total_amount = payables.replace(",", "");
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // System.out.println(result);
        AlipayConfigInfo.logResult(result);// 记录支付日志
        response.setContentType("text/html; charset=gbk");
        PrintWriter out = response.getWriter();
        out.print(result);
        return null;
    }*/
}
