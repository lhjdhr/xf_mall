package org.wlgzs.xf_mall.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import net.guerlab.sdk.alipay.controller.AlipayAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/30 10:20
 * @Description:
 */
@Controller
@RequestMapping("pay/alipay")
public class AlipayController extends AlipayAbstractController {
    @Autowired
    private AlipayClient client;//支付宝请求sdk客户端
    /**
     * 支付请求
     */
    /*@RequestMapping("/app")
    public String app() {

        String APP_ID="2016091600523365";
        String APP_PRIVATE_KEY="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCqsgUiRWr/IHJHLN0JCRr2gLrH7FXCR4SzMKJ5mCbrB2g4/m/cqGuZrIp4Bo4JDqJ5dvfOw9kkRAI+nHRsyeeGbD0maF7SieIIyTnt2MKnrsuyLmOuoPee6/h1CgYbfqHQneQ2jSgRiPI16NnknKh2haDP/L3WalOgaNxoxQ5QVSTgGTiW21ZN9rpgCOq7d9hAy19mg2IUVxGbLKk4choQzEmIYTwgy7bty8twawhT3Jn/cvKC8BzHMVQO9n/LbTlI83Kmsjy4huzXNlromc4p+47+Jf9gEmNLi1otDijcZNYFV46+e9j8l4XYq8zlhGzOCL37KhAz6eZ6pqi7wVM5AgMBAAECggEBAICrtQcCgQdUdBCHFw+Kl5Kdsy6lTngm5TcWQEOcmCmu6becxMH3CzVKRQ75EfRt3bfmICLyqyqVQYPk+DZRdH919BqRrnjSi1rIJSzPqtwv/UiRg37NxBs+EYpYRgZPzLavbqpYeY3OR5avsiCMnsjJLlHaWVfZyrQul4c6k/GlIWucnhe5HeCOE5ih/2PMc6Y09h0pYlb1Kusrjt0gqsHO7qF+omWIEGpnlBn2c0jff+7eE+d4hE1rpWNeocnQiZs7mSTgdxd2hy1e+U8jyjg/3F4yr5iCb5o+e4SCdIO2Qo3hMLgaepVaHsK/cH3euFRlkUHNYhKp3BHz6decXQECgYEA3igH6jXtwGQoCXICzJdGvjp9REgDR3PwUkmmfzNlcv6J+BIkIDOrF6bPDGdVYmQD33lT9cG1qApNrCgDb5iuvdrAlPEleogmInkZEu+EHaAQAIzbcBaoUdsTsQxbXZ/sd0nEjMR+NZdosstBxK4i5ishFMTX6TBOW4JXjyH2WlECgYEAxLMKd61dxwef7Z4OyCm/aXX+h6OzVeq+mmvIq2EILQ3V03m25X5yybHMWA0fpz8LOlyCfNW53yJvgLtD6rWg9U/OKv2fbz8jaW8hlspOd3bZnPudDCflqwTrlSwGvzk45lrMBu8k1MfFhAXTpQwYRcCRLPOgJExqKkkd50P3yGkCgYEA0t8spCSMiRKI7m5KOelePTNHtIimlCx48aL3Z8ZhhcYXMGIH6Iki3lB4OE3dvocxJe8Ew0IcLGWbVM6mdEd5lr0OEFLWQHj7ub05D8BKp9RHXD5YDQ10uD47ctNH5pbm3lQnPXdGVZhiyVQmGYsxlUlbHVu7lp1Dw5j1g+e48AECgYEAprpYcg8KwUzuO9zRDMuOSxdQM6sHtRjwjTY1d0cdRgtHJyUxaZ/gLqS94Bjnrr0aiFKn7KbBEak/uG+DZyyjjawixoAKp6JuIs2nPAipsEuWs6D3grlCzM6z3dLeuTII0AFAldQZdR8MhhbmSi1RWbabl+293WbESqmFZmVPX5kCgYARdBW+Sv4ucxwGnF188HpUaKoINhGUgK6kadqlH7qsALzrUaBbA9poTLgEzgdB2bOtgqFtBIkma4ygChRM5gTvBjZKdM27UdpQEF7y+TviiY8AMKiK43fa/jPN9UmXfVgAZpRJPfbdQU3Lrvdy54l1T0R5d4mIR9GmwVnITrQz0A==";
        String ALIPAY_PUBLIC_KEY="填写您的吧支付宝公钥";
        //签名方式
        String sign_type="RSA2";
        //编码格式
        String CHARSET="utf-8";
        //正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
        String url="https://openapi.alipaydev.com/gateway.do";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(url, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY,sign_type);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        //请保证OutTradeNo值每次保证唯一
        model.setOutTradeNo("2017090080001939235");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
        AlipayTradeAppPayResponse response = null;
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }*/
    @RequestMapping("/app/{orderId}")
    public String app(
            @PathVariable Long orderId,
            HttpServletResponse httpResponse) throws AlipayApiException {

        JSONObject data = new JSONObject();
        data.put("out_trade_no", "201701010000001234"); //商户订单号
        data.put("product_code", "QUICK_MSECURITY_PAY"); //产品码, APP支付 QUICK_MSECURITY_PAY, PC支付 FAST_INSTANT_TRADE_PAY, 移动H5支付 QUICK_WAP_PAY
        data.put("total_amount", "0.01"); //订单金额
        data.put("subject", "测试订单"); //订单标题
        //APP支付
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //PC支付
        //AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //移动H5支付
        //AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setNotifyUrl("http://demo/pay/alipay/notify/1"); //异步通知地址
        request.setBizContent(data.toJSONString()); //业务参数
        return client.sdkExecute(request).getBody();
    }
    @PostMapping("/notify/{orderId}")
    public String notify(
            @PathVariable Long orderId,
            HttpServletRequest request) {
        if (!notify0(request.getParameterMap())) {
            //这里处理验签失败
        }
        request.getParameter("trade_no");//获取请求参数中的商户订单号
        return "success";
    }
}
