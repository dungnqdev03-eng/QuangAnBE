package com.company.quangan.service.Impl;

import com.company.quangan.payload.Enum.EOrderType;
import com.company.quangan.payload.dto.ContactRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDate;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    private static final String ADMIN_EMAIL = "chinh.quangantechnology@gmail.com";

    public void sendNewMail(String to, String subject, String body,String fullname) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("globietechgear@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body.replace("{recipient_email}", fullname), true); // Replace {recipient_email} with actual recipient email

        mailSender.send(message);
    }


public void SendEmailOrderConfirmToCustomer(String to, String fullName, String productName,
                                            int quantity, long unitPrice, EOrderType paymentMethod) throws MessagingException {

    long total = unitPrice * quantity;

    String subject = "✅ Xác nhận đơn hàng: " + productName + " - Quang An Technology";

    String body = "<html>" +
            "<body>" +
            "<h2 style=\"color: #0D6EFD;\">Cảm ơn bạn đã đặt hàng tại Quang An Technology!</h2>" +
            "<p>Xin chào " + fullName + ",</p>" +
            "<p>Chúng tôi đã nhận được đơn hàng của bạn. Dưới đây là thông tin chi tiết:</p>" +

            "<p><b>*** Thông tin đơn hàng:</b></p>" +
            "<p>&nbsp;&nbsp;&nbsp;. Sản phẩm: " + productName + "</p>" +
            "<p>&nbsp;&nbsp;&nbsp;. Số lượng: " + quantity + "</p>" +
            "<p>&nbsp;&nbsp;&nbsp;. Đơn giá: " + String.format("%,d", unitPrice) + " đ</p>" +
            "<p>&nbsp;&nbsp;&nbsp;. Tổng cộng: <b style=\"color:#e65100;\">" + String.format("%,d", total) + " đ</b></p>" +
            "<p>&nbsp;&nbsp;&nbsp;. Hình thức thanh toán: " + paymentMethod + "</p>" +

            "<p style=\"padding:10px; background:#e8f5e9; border-left:4px solid #2e7d32;\">" +
            "📞 Nhân viên của chúng tôi sẽ sớm gọi điện xác nhận với bạn trước khi giao hàng." +
            "</p>" +

            "<p>Nếu bạn có bất kỳ thắc mắc nào, vui lòng trả lời email này hoặc liên hệ hotline của chúng tôi.</p>" +
            "<p>Trân trọng,<br/>Quang An Technology</p>" +
            "</body>" +
            "</html>";

    sendNewMail(to, subject, body, fullName);
}

    public void SendEmailOrderNotification(String to, String fullName, String phone, String email,
                                           String address, String note, String productName,
                                           int quantity, long unitPrice, EOrderType paymentMethod) throws MessagingException {

        long total = unitPrice * quantity;

        String subject = "🛒 Đơn hàng mới: " + productName + " - " + fullName;

        String body = "<html>" +
                "<body>" +
                "<h2 style=\"color: #0D6EFD;\">Đơn hàng mới trên Quang An Technology</h2>" +
                "<p>Có một đơn hàng mới vừa được khách hàng gửi, chi tiết như sau:</p>" +

                "<p><b>*** Thông tin sản phẩm:</b></p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Sản phẩm: " + productName + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Số lượng: " + quantity + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Đơn giá: " + String.format("%,d", unitPrice) + " đ</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Tổng cộng: <b style=\"color:#e65100;\">" + String.format("%,d", total) + " đ</b></p>" +

                "<p><b>*** Thông tin khách hàng:</b></p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Họ tên: " + fullName + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Số điện thoại: <b style=\"color:#e65100;\">" + phone + "</b></p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Email: " + (email == null || email.isBlank() ? "(không có)" : email) + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Địa chỉ nhận hàng: " + (address == null || address.isBlank() ? "(không có)" : address) + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Ghi chú: " + (note == null || note.isBlank() ? "(không có)" : note) + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Hình thức thanh toán: " + paymentMethod + "</p>" +

                "<p style=\"padding:10px; background:#fff3e0; border-left:4px solid #e65100;\">" +
                "⚠️ Vui lòng gọi điện xác nhận với khách hàng trước khi giao." +
                "</p>" +

                "<p>Trân trọng,<br/>Hệ thống Quang An Technology</p>" +
                "</body>" +
                "</html>";

        sendNewMail(to, subject, body, fullName);
    }


    public void sendContactThankYouToCustomer(String to, String fullName) throws MessagingException {

        String subject = "✅ Cảm ơn bạn đã liên hệ - Quang An Technology";

        String body = "<html>" +
                "<body>" +
                "<h2 style=\"color: #0D6EFD;\">Cảm ơn bạn đã liên hệ với Quang An Technology!</h2>" +
                "<p>Xin chào " + HtmlUtils.htmlEscape(fullName) + ",</p>" +
                "<p>Chúng tôi đã nhận được thông tin liên hệ của bạn.</p>" +

                "<p style=\"padding:10px; background:#e8f5e9; border-left:4px solid #2e7d32;\">" +
                "📞 Nhân viên của chúng tôi sẽ sớm liên hệ lại với bạn trong thời gian sớm nhất." +
                "</p>" +

                "<p>Nếu cần hỗ trợ gấp, vui lòng trả lời email này hoặc liên hệ hotline của chúng tôi.</p>" +
                "<p>Trân trọng,<br/>Quang An Technology</p>" +
                "</body>" +
                "</html>";

        sendNewMail(to, subject, body, fullName);
    }

    // Email thông báo có khách liên hệ, gửi admin
    public void sendContactNotifyToAdmin(ContactRequest req) throws MessagingException {

        String email = (req.getEmail() == null || req.getEmail().isBlank())
                ? "(không cung cấp)" : HtmlUtils.htmlEscape(req.getEmail());

        String subject = "🔔 Khách hàng mới liên hệ: " + req.getFullName();

        String body = "<html>" +
                "<body>" +
                "<h2 style=\"color: #FF6B35;\">Có khách hàng mới liên hệ!</h2>" +
                "<p><b>*** Thông tin khách hàng:</b></p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Họ và tên: " + HtmlUtils.htmlEscape(req.getFullName()) + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Số điện thoại: <b>" + HtmlUtils.htmlEscape(req.getPhone()) + "</b></p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Email: " + email + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Chủ đề: " + HtmlUtils.htmlEscape(String.valueOf(req.getTopic())) + "</p>" +
                "<p>&nbsp;&nbsp;&nbsp;. Nội dung:</p>" +
                "<p style=\"padding:10px; background:#fff3e0; border-left:4px solid #e65100; white-space:pre-wrap;\">" +
                HtmlUtils.htmlEscape(req.getContent()) +
                "</p>" +
                "<p>Vui lòng liên hệ lại khách hàng sớm nhất.</p>" +
                "</body>" +
                "</html>";

        sendNewMail(ADMIN_EMAIL, subject, body, "Quang An Technology");
    }
}
