package com.flexisaf.FlexiHotel.service.interfaces;

import com.flexisaf.FlexiHotel.dto.LoginRequest;
import com.flexisaf.FlexiHotel.dto.Response;
import com.flexisaf.FlexiHotel.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
