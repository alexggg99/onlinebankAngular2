package com.bfwg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
@Entity(name = "SavingAccount")
@Getter
@Setter
public class SavingAccount extends Account {
    //by default currency is RUR

    @Override
    public void setCurrency(Currency currency) {
        //do nothing
    }
}
