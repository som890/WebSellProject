package com.duc.processor;

import com.duc.config.Environment;
import com.duc.enums.Language;
import com.duc.models.HttpResponse;
import com.duc.models.RefundMoMoRequest;
import com.duc.models.RefundMoMoResponse;
import com.duc.shared.constants.Parameter;
import com.duc.shared.exception.MoMoException;
import com.duc.shared.utils.Encoder;
import com.duc.shared.utils.LogUtils;

public class RefundTransaction extends AbstractProcess<RefundMoMoRequest, RefundMoMoResponse> {
    public RefundTransaction(Environment environment) {
        super(environment);
    }

    public static RefundMoMoResponse process(Environment env, String orderId, String requestId, String amount, Long transId, String description) throws Exception {
        try {
            RefundTransaction m2Processor = new RefundTransaction(env);

            RefundMoMoRequest request = m2Processor.createRefundTransactionRequest(orderId, requestId, amount, transId, description);
            RefundMoMoResponse response = m2Processor.execute(request);

            return response;
        } catch (Exception exception) {
            LogUtils.error("[RefundTransactionProcess] "+ exception);
        }
        return null;
    }

    @Override
    public RefundMoMoResponse execute(RefundMoMoRequest request) throws MoMoException {
        try {

            String payload = getGson().toJson(request, RefundMoMoRequest.class);

            HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint().getRefundUrl(), payload);

            if (response.getStatus() != 200) {
                throw new MoMoException("[RefundResponse] [" + request.getOrderId() + "] -> Error API");
            }

            System.out.println("uweryei7rye8wyreow8: "+ response.getData());

            RefundMoMoResponse refundMoMoResponse = getGson().fromJson(response.getData(), RefundMoMoResponse.class);
            String responserawData = Parameter.REQUEST_ID + "=" + refundMoMoResponse.getRequestId() +
                    "&" + Parameter.ORDER_ID + "=" + refundMoMoResponse.getOrderId() +
                    "&" + Parameter.MESSAGE + "=" + refundMoMoResponse.getMessage() +
                    "&" + Parameter.RESULT_CODE + "=" + refundMoMoResponse.getResultCode();

            LogUtils.info("[RefundResponse] rawData: " + responserawData);

            return refundMoMoResponse;

        } catch (Exception exception) {
            LogUtils.error("[RefundResponse] "+ exception);
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public RefundMoMoRequest createRefundTransactionRequest(String orderId, String requestId, String amount, Long transId, String description) {

        try {
            String requestRawData = new StringBuilder()
                    .append(Parameter.ACCESS_KEY).append("=").append(partnerInfo.getAccessKey()).append("&")
                    .append(Parameter.AMOUNT).append("=").append(amount).append("&")
                    .append(Parameter.DESCRIPTION).append("=").append(description).append("&")
                    .append(Parameter.ORDER_ID).append("=").append(orderId).append("&")
                    .append(Parameter.PARTNER_CODE).append("=").append(partnerInfo.getPartnerCode()).append("&")
                    .append(Parameter.REQUEST_ID).append("=").append(requestId).append("&")
                    .append(Parameter.TRANS_ID).append("=").append(transId)
                    .toString();

            String signRequest = Encoder.signHmacSHA256(requestRawData, partnerInfo.getSecretKey());
            LogUtils.debug("[RefundRequest] rawData: " + requestRawData + ", [Signature] -> " + signRequest);

            return new RefundMoMoRequest(partnerInfo.getPartnerCode(), orderId, requestId, Language.EN, Long.valueOf(amount), transId, signRequest, description);
        } catch (Exception e) {
            LogUtils.error("[RefundResponse] "+ e);
        }

        return null;
    }
}
