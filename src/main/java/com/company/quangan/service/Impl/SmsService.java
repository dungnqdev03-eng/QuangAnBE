package com.company.quangan.service.Impl;

import com.company.quangan.config.SmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.Normalizer;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SmsService {

    private static final String ESMS_URL =
            "https://rest.esms.vn/MainService.svc/json/SendMultipleMessage_V4_post_json/";

    private final SmsConfig props;
    private final RestTemplate restTemplate;

    public SmsService(SmsConfig props) {
        this.props = props;

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);   // 5 giây
        factory.setReadTimeout(10000);     // 10 giây
        this.restTemplate = new RestTemplate(factory);
    }

    public void sendThankYou(String phone, String fullName) {
        String content = "Quang An Technology cam on " + removeAccent(fullName)
                + " da lien he. Nhan vien se goi lai ban som nhat.";
        send(phone, content);
    }

    public void send(String phone, String content) {
        if (!props.isEnabled()) {
            log.info("SMS đang tắt, bỏ qua gửi tới {}", phone);
            return;
        }

        Map<String, Object> body = new HashMap<>();
        body.put("ApiKey", props.getApiKey());
        body.put("SecretKey", props.getSecretKey());
        body.put("Phone", normalizePhone(phone));
        body.put("Content", content);
        body.put("SmsType", String.valueOf(props.getSmsType()));
        body.put("IsUnicode", "0");
        body.put("Sandbox", props.isSandbox() ? "1" : "0");
        // chặn gửi trùng trong 24h nếu cùng RequestId, tối đa 50 ký tự
        body.put("RequestId", UUID.randomUUID().toString().replace("-", ""));

        if (props.getSmsType() == 2) {
            body.put("Brandname", props.getBrandname());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<Map> res = restTemplate.postForEntity(
                    ESMS_URL, new HttpEntity<>(body, headers), Map.class);

            Map<?, ?> data = res.getBody();
            String code = data != null ? String.valueOf(data.get("CodeResult")) : "null";

            if ("100".equals(code)) {
                log.info("Gửi SMS tới {} thành công, SMSID={}", phone, data.get("SMSID"));
            } else {
                // 101 = sai ApiKey/SecretKey, các mã khác xem bảng mã lỗi của eSMS
                log.error("eSMS trả lỗi: code={}, msg={}", code,
                        data != null ? data.get("ErrorMessage") : null);
            }
        } catch (Exception e) {
            log.error("Gọi eSMS thất bại", e);
        }
    }

    /** +84901234567 hoặc 84901234567 -> 0901234567 */
    private String normalizePhone(String phone) {
        String p = phone.replaceAll("[\\s.\\-]", "");
        if (p.startsWith("+84")) return "0" + p.substring(3);
        if (p.startsWith("84") && p.length() == 11) return "0" + p.substring(2);
        return p;
    }

    /** "Nguyễn Văn Đạt" -> "Nguyen Van Dat" (SMS không dấu rẻ hơn, không bị tách tin) */
    private String removeAccent(String s) {
        if (s == null) return "";
        String n = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return n.replace('đ', 'd').replace('Đ', 'D');
    }
}