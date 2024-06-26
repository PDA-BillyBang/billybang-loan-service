package com.billybang.loanservice.model.dto.response;

import com.billybang.loanservice.model.type.UserStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResDto {

    private Long userId;
    private String email;
    private LocalDate birthDate;
    private String nickname;
    private UserInfoResDto userInfo;
    private UserStatus userStatus;

}
