package com.duc;

import com.duc.config.Environment;
import com.duc.enums.RequestType;
import com.duc.models.PaymentResponse;
import com.duc.models.QueryStatusTransactionResponse;
import com.duc.processor.CreateOrderMoMo;
import com.duc.processor.QueryTransactionStatus;

public class AllInOne {

    public static void main(String... args) throws Exception {
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        long amount = 50000;
        String orderInfo = "Pay With MoMo";
        String returnURL = "https://google.com.vn";
        String notifyURL = "https://hieu.com.vn";

        Environment environment = Environment.selectEnv("dev");
        /***
         * create payment with Momo's ATM type
         */
        PaymentResponse captureATMMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.PAY_WITH_ATM, null);
        System.out.println("pay url: "+captureATMMoMoResponse.getPayUrl());
        System.out.println("-----------------------------------------------------------");

        /***
         * query transaction successful or init
         */
        QueryStatusTransactionResponse queryStatusTransactionResponse = QueryTransactionStatus.process(environment, orderId, requestId);
        System.out.println("qqqq-----------------------------------------------------------");

    }

}
