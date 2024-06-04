package com.mobabuild.api_build.controller.comand;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginComand {
    private String email;
    private String pass;
}
