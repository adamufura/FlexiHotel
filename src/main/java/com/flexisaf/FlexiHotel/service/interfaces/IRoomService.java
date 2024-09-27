package com.flexisaf.FlexiHotel.service.interfaces;

import com.flexisaf.FlexiHotel.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IRoomService {

    Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description);

    Response getAllRooms();

    List<String> getAllRoomTypes();

    Response deleteRoom(Long roomId);

    Response updateRoom(Long roomId, String roomType,  String description, BigDecimal roomPrice, MultipartFile photo);

    Response getRoomById(Long roomId);

    Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

    Response getAllAvailableRooms();

}
