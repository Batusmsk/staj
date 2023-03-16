package com.example.batuhan.project.service;

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
	
	public String createPayment(Integer amount,String date, String email) {
		if(!personService.getPerson(email).isPresent()) return "person not found";
		Optional<Person> person = personService.getPerson(email);
		if(person.get().getDebt() < amount) return "The person wants to pay a debt smaller than the debt you wanted to get paid";
		Integer debt = person.get().getDebt();
		person.get().setDebt(debt - amount);
		personRepository.save(person.get());
		
		Payment payment = new Payment();
		payment.setPaymentAmount(amount);
		payment.setPaymentDate(date);
		payment.setPerson(person.get());
		paymentRepository.save(payment);
		return "successfully";
	}

}
