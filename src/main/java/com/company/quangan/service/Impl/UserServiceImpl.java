package com.company.quangan.service.Impl;

import com.company.quangan.payload.Enum.ERole;
import com.company.quangan.payload.entity.Role;
import com.company.quangan.payload.entity.User;
import com.company.quangan.payload.request.create.OrderRequest;
import com.company.quangan.payload.request.create.UserRequest;
import com.company.quangan.repository.RoleRepository;
import com.company.quangan.repository.UserRepository;
import com.company.quangan.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public String createUser(OrderRequest request) {
        // 2. Kiểm tra trùng email
        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        // 3. Kiểm tra trùng số điện thoại
        if (request.getPhone() != null && userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Số điện thoại đã được sử dụng!");
        }

        // 4. Lấy role mặc định (ví dụ role "USER")
        Role defaultRole = roleRepository.findByRoleName(ERole.CUSTOMER)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy role mặc định!"));

        // 5. Build entity User từ UserRequest
        User user = User.builder()
                .address(request.getAddress())
                .fullName(request.getFullName())
                .status(true) // mặc định kích hoạt tài khoản
                .phone(request.getPhone())
                .email(request.getEmail())
                .sex(request.isGender())
                .role(defaultRole)
                .build();
        userRepository.save(user);
        return "Success";
    }
}
