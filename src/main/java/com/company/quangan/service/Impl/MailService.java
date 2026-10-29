package com.company.quangan.service.Impl;

import com.company.quangan.payload.Enum.EOrderType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendNewMail(String to, String subject, String body,String fullname) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("globietechgear@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body.replace("{recipient_email}", fullname), true); // Replace {recipient_email} with actual recipient email

        mailSender.send(message);
    }

//    public void sendOTPtoActiveAccount(String to, String otp, String fullname) throws MessagingException {
//        String subject = "OTP kích hoạt tài khoản - Globie";
//        String body = "<html>" +
//                "<body>" +
//                "<h2 style=\"color: #0D6EFD;\">OTP kích hoạt tài khoản</h2>" +
//                "<p>Xin chào " + fullname + ",</p>" +
//                "<p>Chúng tôi đã nhận được yêu cầu kích hoạt tài khoản Globie của bạn liên kết với địa chỉ email này. Nếu bạn không yêu cầu thay đổi này, bạn có thể bỏ qua email này.</p>" +
//                "<p>Để kích hoạt tài khoản của bạn, vui lòng sử dụng mã OTP sau:</p>" +
//                "<h3 style=\"color: #0D6EFD;\">" + otp + "</h3>" +
//                "<p>Mã OTP này sẽ hết hạn sau 15 phút.</p>" +
//                "<p>Cảm ơn bạn đã đồng hành cùng Globie !</p>" +
//                "<p>Trân trọng,<br/>Globie</p>" +
//                "</body>" +
//                "</html>";
//        sendNewMail(to, subject, body, fullname);
//    }
//
//    public void sendOTPtoChangePasswordAccount(String to, String otp, String fullname) throws MessagingException {
//        String subject = "OTP để đặt lại mật khẩu - Globie";
//        String body = "<html>" +
//                "<body>" +
//                "<h2 style=\"color: #0D6EFD;\">Mã OTP</h2>" +
//                "<p>Xin chào " + fullname + ",</p>" +
//                "<p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn. Sử dụng mã OTP dưới đây để đặt lại mật khẩu của bạn:</p>" +
//                "<h3 style=\"color: #0D6EFD;\">" + otp + "</h3>" +
//                "<p>Mã OTP này sẽ hết hạn sau 15 phút.</p>" +
//                "<p>Trân trọng,<br/>Globie</p>" +
//                "</body>" +
//                "</html>";
//        sendNewMail(to, subject, body, fullname);
//    }

//    public void SendEmailBannedStatusMember(String to, String fullname) throws MessagingException {
//        String subject = "Cấm khỏi nền tảng - Globie";
//        String body = "<html>" +
//                "<body>" +
//                "<h2 style=\"color: #0D6EFD;\">Cấm Người Dùng</h2>" +
//                "<p>Xin chào " + fullname + ",</p>" +
//                "<p>Sau khi chúng tôi nhận được nhiều đơn khiếu nại về các sản phẩm của bạn trên nền tảng của chúng tôi.</p>" +
//                "<p>Chúng tôi rất tiếc phải thông báo với bạn rằng bạn đã bị cấm sử dụng nền tảng Globie.</p>" +
//                "<p>Nếu bạn có bất kỳ phản hồi nào, vui lòng trả lời mail này!</p>" +
//                "<p>Trân trọng,<br/>Globie</p>" +
//                "</body>" +
//                "</html>";
//        sendNewMail(to, subject, body, fullname);
//    }

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
}
