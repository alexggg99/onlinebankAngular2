package com.busines.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "PrimaryAccount")
@Getter
@Setter
public class PrimaryAccount extends Account {

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
