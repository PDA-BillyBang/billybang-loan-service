package com.billybang.loanservice.filter;

import com.billybang.loanservice.model.dto.response.UserResDto;
import com.billybang.loanservice.model.entity.loan.LoanPreferredItem;
import com.billybang.loanservice.model.type.CompanySize;
import com.billybang.loanservice.utils.DateUtil;

public class LoanPreferredItemFilter {

    public static boolean filterByUserInfo(LoanPreferredItem loanPreferredItem, UserResDto userInfo){
        int age = DateUtil.calcAge(userInfo.getBirthDate());
        Integer yearsOfMarriage = userInfo.getUserInfo().getYearsOfMarriage();
        Integer childrenCount = userInfo.getUserInfo().getChildrenCount();
        return switch (loanPreferredItem.getItemType()){
            case NEWLY_MARRIED -> yearsOfMarriage != null && yearsOfMarriage <= 7;
            case MULTIPLE_CHILDREN -> childrenCount != null && childrenCount >= 2;
            case YOUTH -> 19 <= age && age <= 34;
            case MEDIUM_SIZED -> userInfo.getUserInfo().getCompanySize() == CompanySize.INTERMEDIATE;
        };
    }

}
