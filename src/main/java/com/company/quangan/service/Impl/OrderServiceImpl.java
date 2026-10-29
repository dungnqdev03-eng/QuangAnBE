package com.company.quangan.service.Impl;

import com.company.quangan.payload.entity.Order;
import com.company.quangan.payload.entity.Product;
import com.company.quangan.payload.entity.User;
import com.company.quangan.payload.request.create.OrderRequest;
import com.company.quangan.repository.OrderRepository;
import com.company.quangan.repository.ProductCategoryRepository;
import com.company.quangan.repository.ProductRepository;
import com.company.quangan.repository.UserRepository;
import com.company.quangan.service.OrderService;
import com.company.quangan.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @Override
    public String createOrder(OrderRequest createOrderRequest) {
        Order order = new Order();
        User user = userRepository.findUserByPhone(createOrderRequest.getPhone());
        if(user == null){
            userService.createUser(createOrderRequest);
        }
        order.setUser(user);

        Product product = productRepository.findByProductId(createOrderRequest.getProductId());
        order.setProduct(product);
        order.setOrderDate(LocalDate.now());
        order.setOrderName("Đơn hàng: " + createOrderRequest.getProductName());
        order.setStatus(false);
        order.setType(createOrderRequest.getPaymentMethod());
        order.setAmount(createOrderRequest.getQuantity());
        order.setAmount(createOrderRequest.getUnitPrice() * createOrderRequest.getQuantity());
        order.setOderCode(this.OrderCodeAutomationCreating(createOrderRequest.getPhone()));

        orderRepository.save(order);
        // 4. Gửi mail - bọc try/catch để lỗi gửi mail không làm fail cả đơn hàng
        try {
            // Gửi mail xác nhận cho khách (chỉ khi khách có điền email)
            if (createOrderRequest.getEmail() != null && !createOrderRequest.getEmail().isBlank()) {
                mailService.SendEmailOrderConfirmToCustomer(
                        createOrderRequest.getEmail(),
                        createOrderRequest.getFullName(),
                        createOrderRequest.getProductName(),
                        createOrderRequest.getQuantity(),
                        createOrderRequest.getUnitPrice(),
                        createOrderRequest.getPaymentMethod()
                );
            }

            // Gửi mail báo cho công ty
            mailService.SendEmailOrderNotification(
                    "chinh.quangantechnology@gmail.com",
                    createOrderRequest.getFullName(),
                    createOrderRequest.getPhone(),
                    createOrderRequest.getEmail(),
                    createOrderRequest.getAddress(),
                    createOrderRequest.getNote(),
                    createOrderRequest.getProductName(),
                    createOrderRequest.getQuantity(),
                    createOrderRequest.getUnitPrice(),
                    createOrderRequest.getPaymentMethod()
            );
        } catch (MessagingException e) {
            // Log lỗi nhưng không throw - đơn hàng đã lưu thành công rồi, không nên rollback vì lỗi mail
            log.error("Gửi email đơn hàng thất bại cho đơn {}: {}", this.OrderCodeAutomationCreating(createOrderRequest.getPhone()), e.getMessage());
        }
        return "success";
    }

    private String OrderCodeAutomationCreating(String phone) {
        // Lấy 3 số cuối của SĐT
        String last3Phone = phone.substring(phone.length() - 3);

        // Chỉ lấy ngày tháng năm, không lấy giờ phút giây
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // Thêm số ngẫu nhiên để tránh trùng khi nhiều đơn tạo cùng ngày
        String randomSuffix = String.format("%04d", new Random().nextInt(10000));

        return "DH" + date + last3Phone + randomSuffix;
    }
}
