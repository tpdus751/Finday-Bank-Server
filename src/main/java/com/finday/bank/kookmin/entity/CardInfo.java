package com.finday.bank.kookmin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "card_info")
@Data
public class CardInfo {

    @Id
    @Column(name = "card_no")
    private Integer cardNo;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "card_img_url", nullable = false)
    private String cardImgUrl;
}
