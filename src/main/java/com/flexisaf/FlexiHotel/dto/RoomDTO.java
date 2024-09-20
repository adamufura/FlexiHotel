package com.flexisaf.FlexiHotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.flexisaf.FlexiHotel.entity.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    private long id;
    private String roomType;
    private BigDecimal roomPrice;
    private String roomPhotoUrl;
    private String roomDescription;
    private List<BookingDTO> bookings;
}
