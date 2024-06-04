package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.utils.AuthorityName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDTO {
    private Long id;
    private AuthorityName name;
}
