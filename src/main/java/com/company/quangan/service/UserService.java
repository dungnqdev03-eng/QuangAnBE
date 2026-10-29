package com.company.quangan.service;

import com.company.quangan.payload.request.create.OrderRequest;
import com.company.quangan.payload.request.create.UserRequest;

public interface UserService {
    String createUser(OrderRequest userRequest);
}
