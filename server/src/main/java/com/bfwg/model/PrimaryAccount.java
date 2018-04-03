package com.bfwg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "PrimaryAccount")
@Getter
@Setter
@DiscriminatorValue("primary")
public class PrimaryAccount extends Account {

}
