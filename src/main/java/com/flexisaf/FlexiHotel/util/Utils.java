package com.flexisaf.FlexiHotel.util;

import com.flexisaf.FlexiHotel.dto.RoomDTO;
import com.flexisaf.FlexiHotel.dto.UserDTO;
import com.flexisaf.FlexiHotel.entity.Room;
import com.flexisaf.FlexiHotel.entity.User;

import java.security.SecureRandom;
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



}
