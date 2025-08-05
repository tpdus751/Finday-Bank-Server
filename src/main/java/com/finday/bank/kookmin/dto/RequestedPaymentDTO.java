package com.finday.bank.kookmin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestedPaymentDTO {
    @Schema(description = "사용처", example = "GS25")
    private String placeOfUse;

    @Schema(description = "입금 or 출금", example = "출금")
    private String transactionType;

    @Schema(description = "은행 이름", example = "kookmin")
    private String bankName;

    @Schema(description = "카테고리 유형", example = "생활")
    private String category;

    @Schema(description = "결제 금액", example = "5200")
    private Long amount;

    @Schema(description = "계좌 or 카드 결제 구분", example = "account")
    private String methodType;

    @Schema(description = "계좌 결제일 경우 계좌 번호, 카드 결제일 경우 카드 번호")
    private String methodId;

    @Schema(description = "사용자 고유번호(생년월일+성별(남:1, 3, 여:2, 4)) 조합")
    private String userSpecificNo;
}
