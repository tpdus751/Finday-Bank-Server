package com.finday.bank.kookmin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 계좌 정보를 담는 DTO")
public class AccountDTO {

    @Schema(description = "계좌 번호", example = "111111-11-111111")
    private String accountNumber;

    @Schema(description = "계좌 별칭", example = "월급통장")
    private String alias;

    @Schema(description = "현재 잔액", example = "1200000")
    private Long balance;

    @Schema(description = "계좌 생성일시 (YYYY-MM-DDTHH:MM:SS)", example = "2024-01-01T12:00:00")
    private String createdAt;

    @Schema(description = "은행 이름", example = "국민은행")
    private String bankName;

    @Schema(description = "계좌 이름", example = "KB 별별통장")
    private String accountName;
}
