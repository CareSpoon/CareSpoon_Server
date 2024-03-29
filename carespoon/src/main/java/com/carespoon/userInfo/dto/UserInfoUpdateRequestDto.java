package com.carespoon.userInfo.dto;

import com.carespoon.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoUpdateRequestDto {
    private User user;

    private double height;

    private int age;

    private double weight;

    private double metabolicRate;

    private int sex;

    @Builder
    public UserInfoUpdateRequestDto(User user, int age, int sex, double height, double weight){
        this.user = user;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        if(sex == 0){
            //man
            this.metabolicRate = 88.4 + (13.4*weight) + 4.8* height - 5.68 * age;
        }else{
            //woman
            this.metabolicRate = 447.6 + (9.25*weight)+ 3.1*height - 4.33*age;
        }
    }
}
