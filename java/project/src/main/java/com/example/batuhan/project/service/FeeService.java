package com.example.batuhan.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.batuhan.project.dto.FeeDto;
import com.example.batuhan.project.entity.Apartment;
import com.example.batuhan.project.entity.Fee;
import com.example.batuhan.project.entity.Person;
import com.example.batuhan.project.repository.FeeRepository;
import com.example.batuhan.project.request_response.FeeRequest;

@Service
public class FeeService {

	@Value("${fee}")
	private Integer fee;
	
	@Autowired
	PersonService personService;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	FeeRepository feeRepository;
	@Autowired
	PaymentService paymentService;

	public String createFee(FeeRequest request) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");

		if (!personService.getPerson(request.getEmail()).isPresent())
			return "Kullanıcı bulunamadı";

		Optional<Person> person = personService.getPerson(request.getEmail());

		if (!apartmentService.getApartment(request.getBlockName(), request.getApartmentNo()).isPresent())
			return "Daire bulunamadı";
		if (!personService.getPerson(personService.findOwnerTheApartment(request.getBlockName(), request.getApartmentNo()).getEmail()).isPresent())
			return "Kullanıcıya ait daire bulunamadı.";
		Optional<Apartment> apartment = apartmentService.getApartment(request.getBlockName(), request.getApartmentNo());
		Integer feeAmount = fee * (apartment.get().getBaseArea());
		Fee fee = new Fee();

		fee.setApartment(apartment.get());
		fee.setPerson(person.get());
		fee.setPaidAmount(0);
		fee.setFeeAmount(feeAmount);
		fee.setFeeDate(ft.format(date));
		fee.setStatus(true);
		feeRepository.save(fee);
		return "Aidat eklendi.";
	}

	public List<Fee> getFees() {
		return feeRepository.findAll();
	}

	public Optional<Fee> getFee(Integer id) {
		return feeRepository.findById(id);
	}

	public List<FeeDto> findByPerson(String email) {
		if (!personService.getPerson(email).isPresent())
			return null;
		List<FeeDto> list = new ArrayList<>();
		for (var i : getFees()) {
			if (i.getPerson().getEmail().equals(email)) {
				FeeDto feeDto = new FeeDto();
				feeDto.setId(i.getId());
				feeDto.setApartmentNo(i.getApartment().getApartmentNo());
				feeDto.setBlockName(i.getApartment().getBlock().getBlockName());
				feeDto.setEmail(email);
				feeDto.setFeeDate(i.getFeeDate());
				feeDto.setFeeAmount(i.getFeeAmount());
				feeDto.setStatus(i.getStatus());
				list.add(feeDto);
			}
		}
		return list;
	}

	public List<FeeDto> findByBlockNameAndApartmentNo(String blockName, Integer ApartmentNo) {
		if (!apartmentService.getApartment(blockName, ApartmentNo).isPresent())
			return null;
		List<FeeDto> list = new ArrayList<>();
		for (var i : feeRepository.findByApartment(apartmentService.getApartment(blockName, ApartmentNo).get())) {
			FeeDto feeDto = new FeeDto();
			feeDto.setApartmentNo(i.getApartment().getApartmentNo());
			feeDto.setBlockName(i.getApartment().getBlock().getBlockName());
			feeDto.setEmail(i.getPerson().getEmail());
			feeDto.setFeeDate(i.getFeeDate());
			feeDto.setStatus(i.getStatus());
			feeDto.setFeeAmount(i.getFeeAmount());
			list.add(feeDto);
		}
		return list;
	}

	public String payFee(Integer id, Integer amount) {
		if (!getFee(id).isPresent())
			return "Böyle bir aidat bulunmuyor";
		if (getFee(id).get().getStatus() == false)
			return "Bu aidat borcu önceden ödenmiş";

		Optional<Fee> fee = getFee(id);
		
		var xxx = amount == 0 ? fee.get().getFeeAmount() : amount;
		
		var payment = paymentService.createPayment(id, fee.get().getPerson().getEmail(), xxx);
		if (payment == false)
			return "Hata";
		fee.get().setStatus(false);
		fee.get().setPaidAmount(fee.get().getFeeAmount());
		feeRepository.save(fee.get());
		return "Aidat ödendi";
	}

	public long topla(long a, long b) {
		return a+b;
	}
	public String amountWithPayment(Integer id, Integer amount) {
		if (!getFee(id).isPresent())
			return "Böyle bir aidat borcu bulunmuyor";
		if (getFee(id).get().getStatus() == false)
			return "bu aidat daha önce ödendi";
		Fee fee = getFee(id).get();
		if(!fee.getStatus()) return "bu aidat daha önce ödendi";
		
		if(amount >= (fee.getFeeAmount() - fee.getPaidAmount())) {
			
			if(amount > (fee.getFeeAmount() - fee.getPaidAmount())) return "ödemek istediğiniz tutar borcunuzdan fazladır";
					
			return payFee(id, amount);
		} 
		
		
		fee.setPaidAmount(fee.getPaidAmount() + amount);
		if( fee.getFeeAmount() <= fee.getPaidAmount()) {
			fee.setStatus(false);
		}
		
		var payment = paymentService.createPayment(id, fee.getPerson().getEmail(), amount);
		if (payment == false)
			return "Hata";
		feeRepository.save(fee);
		return "Aidat ödendi";
	
	}

}
