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
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customerId")
    @Getter
    @Setter
    private Integer customerId;
    @Getter
    @Setter
    private Integer customerIdentityNo;
    @Getter
    @Setter
    private String customerFullName;

}
