package com.busines.model.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "SavingAccount")
@Getter
@Setter
public class SavingAccount extends Account {
    //by default currency is RUR
}
