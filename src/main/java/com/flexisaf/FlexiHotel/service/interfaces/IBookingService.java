package com.flexisaf.FlexiHotel.service.interfaces;

import com.flexisaf.FlexiHotel.dto.Response;
import com.flexisaf.FlexiHotel.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}