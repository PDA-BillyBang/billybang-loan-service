package com.billybang.loanservice.model.dto.response;

import com.billybang.loanservice.model.dto.property.PropertyInfoDto;
import com.billybang.loanservice.model.type.TradeType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropertyResDto {

    private String articleName;
    private TradeType tradeType;
    private int area2;
    private int price;

    public PropertyInfoDto toPropertyInfoDto(){
        return PropertyInfoDto.builder()
                .area2(area2)
                .price(price)
                .build();
    }

}
