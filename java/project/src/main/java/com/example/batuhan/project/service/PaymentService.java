package com.example.batuhan.project.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batuhan.project.entity.Payment;
import com.example.batuhan.project.entity.Person;
import com.example.batuhan.project.repository.PaymentRepository;
import com.example.batuhan.project.repository.PersonRepository;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	PersonService personService;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	FeeService feeService;
	
	public boolean createPayment(Integer id, String email, Integer amount) {
		try {
			Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
			Optional<Person> person = personService.getPerson(email);
			Payment payment = new Payment();
			payment.setFee(feeService.getFee(id).get());
			payment.setPaymentAmount(amount);
			payment.setPaymentDate(ft.format(date));
			payment.setPerson(personService.getPerson(email).get());
			paymentRepository.save(payment);
			
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}

	}
	public List<Payment> getPayments() {
		return paymentService.findAll();
	}
}
