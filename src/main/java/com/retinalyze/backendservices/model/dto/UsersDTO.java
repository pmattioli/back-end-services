package com.retinalyze.backendservices.model.dto;

import java.util.List;

import com.retinalyze.backendservices.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private List<String> usersFound;

}
