package com.mobabuild.api_build.controller.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserRequest {
    private String email;
    private String userName;
    private String pass;
    private List<String> authorityNames;
}
