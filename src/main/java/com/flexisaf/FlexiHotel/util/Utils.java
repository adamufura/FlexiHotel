package com.flexisaf.FlexiHotel.util;

import com.flexisaf.FlexiHotel.dto.BookingDTO;
import com.flexisaf.FlexiHotel.dto.RoomDTO;
import com.flexisaf.FlexiHotel.dto.UserDTO;
import com.flexisaf.FlexiHotel.entity.Booking;
import com.flexisaf.FlexiHotel.entity.Room;
import com.flexisaf.FlexiHotel.entity.User;

import java.awt.print.Book;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPKRQSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomAplhaNumeric(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++){
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomCharacter = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomCharacter);
        }
        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(roomDTO.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());

        return roomDTO;
    }

    public static BookingDTO mapBookingsEntityToBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setTotalNumOfGuests(booking.getTotalNumOfGuests());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        return bookingDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTOWithBookings(Room room){
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(roomDTO.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());

        if (room.getBookings() != null){
            roomDTO.setBookings(room.getBookings().stream()
                    .map(Utils::mapBookingsEntityToBookingDTO).collect(Collectors.toList()));
        }

        return roomDTO;
    }

    public static UserDTO mapUserEntityToUserDTOWithBookingsAndRooms(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()){
            userDTO.setBookings(user.getBookings().stream()
                    .map(booking -> mapBookingEntityToBookingDTOWithBookedRooms(booking, false)).collect(Collectors.toList()));

        }

        return userDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTOWithBookedRooms(Booking booking, boolean mapUser){
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setTotalNumOfGuests(booking.getTotalNumOfGuests());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        if (mapUser){
            bookingDTO.setUser(Utils.mapUserEntityToUserDTO(booking.getUser()));
        }

        if (booking.getRoom() != null){
            RoomDTO roomDTO = new RoomDTO();

            roomDTO.setId(booking.getRoom().getId());
            roomDTO.setRoomType(booking.getRoom().getRoomType());
            roomDTO.setRoomPrice(booking.getRoom().getRoomPrice());
            roomDTO.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDTO.setRoomDescription(booking.getRoom().getRoomDescription());

            bookingDTO.setRoom(roomDTO);
        }

        return bookingDTO;
    }

    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList){
        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }

    public static List<RoomDTO> mapRoomListEntityToRoomListDTO(List<Room> roomList){
        return roomList.stream().map(Utils::mapRoomEntityToRoomDTO).collect(Collectors.toList());
    }

    public static List<BookingDTO> mapBookingListEntityToBookingListDTO(List<Booking> bookingList){
        return bookingList.stream().map(Utils::mapBookingsEntityToBookingDTO).collect(Collectors.toList());
    }



}
