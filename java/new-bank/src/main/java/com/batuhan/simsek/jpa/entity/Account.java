package com.batuhan.simsek.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;





@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="accountId")
    @Getter
    @Setter
    private Integer accountId;
    @Getter
    @Setter
    private Integer customerIdentityNo;
    @Getter
    @Setter
    private Integer balance;
    @Getter
    @Setter
    private String iban;

}
