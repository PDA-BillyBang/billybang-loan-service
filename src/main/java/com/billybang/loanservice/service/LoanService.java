package com.billybang.loanservice.service;

import com.billybang.loanservice.client.UserServiceClient;
import com.billybang.loanservice.exception.common.BError;
import com.billybang.loanservice.exception.common.CommonException;
import com.billybang.loanservice.model.dto.response.*;
import com.billybang.loanservice.model.filter.LoanFilter;
import com.billybang.loanservice.model.mapper.LoanCategoryMapper;
import com.billybang.loanservice.model.dto.loan.LoanCategoryDto;
import com.billybang.loanservice.model.entity.loan.Loan;
import com.billybang.loanservice.model.type.LoanType;
import com.billybang.loanservice.model.type.TradeType;
import com.billybang.loanservice.repository.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserServiceClient userServiceClient;
    private final LoanFilter loanFilter;
//    private final PropertyServiceClient propertyServiceClient;

    @Transactional
    public LoanResDto getLoans(PropertyResponseDto propertyInfo, UserResponseDto userInfo) {
        LoanType loanType = toLoanType(propertyInfo.getTradeType());
        List<LoanType> loanTypes = Arrays.asList(loanType, LoanType.PERSONAL);
        List<Loan> loans = loanRepository.findAllByLoanTypeIn(loanTypes)
                .stream().filter(loan -> loanFilter.filterByPropertyAndUser(loan, propertyInfo, userInfo))
                .toList();

        log.info("loans: {}", loans);
        List<LoanCategoryDto> loanCategoryDtos = LoanCategoryMapper.loansToLoanCategoryDtos(loans, userInfo.getUserId());
        return LoanResDto.builder()
                .buildingName(propertyInfo.getArticleName())
                .sumCount(loans.size())
                .loanCategories(loanCategoryDtos)
                .build();
    }

    @Transactional
    public LoanSimpleResDto getLoanSimple(PropertyResponseDto propertyInfo, UserResponseDto userInfo) {
        LoanType loanType = toLoanType(propertyInfo.getTradeType());
        List<Loan> loans = loanRepository.findAllByLoanType(loanType);
        if(loans.isEmpty()) throw new CommonException(BError.NOT_EXIST, "LoansByLoanType");
        //TODO 부동산과 사용자에 맞춰서 필터링한 후, 랜덤으로 하나 추출 -> 일단은 첫 번째 것을 가져온다.
        Loan filteredRandomLoan = loans.get(0);
        return filteredRandomLoan.toLoanSimpleResDto();
    }

    @Transactional
    public LoanDetailResDto getLoanDetail(Long loanId, UserResponseDto userInfo) {
        Loan resultLoan = loanRepository.findById(loanId)
            .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "Loan"));
        return resultLoan.toLoanDetailResDto(userInfo);
    }

    @Transactional
    public Loan getLoanByLoanId(Long loanId){
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "Loan"));
    }

    public UserResponseDto getUserInfo() {
        return userServiceClient.getUserInfo().getResponse();
    }

    public PropertyResponseDto getPropertyInfo(Long propertyId){
//        return propertyServiceClient.getPropertyInfo(propertyId).getResponse();
        return PropertyResponseDto.builder()
                .articleName("신한투자증권건물")
                .tradeType(TradeType.DEAL)
                .area2(100)
                .price(500)
                .build();
    }

    private LoanType toLoanType(TradeType tradeType){
        return switch(tradeType){
            case DEAL -> LoanType.MORTGAGE;
            case LEASE -> LoanType.JEONSE;
        };
    }

}
