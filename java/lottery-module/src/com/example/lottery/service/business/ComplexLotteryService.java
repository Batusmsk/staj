package com.example.lottery.service.business;

import java.util.Arrays;
import java.util.List;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.Service;
import com.example.lottery.service.ServiceQuality;

@Service(ServiceQuality.FAST)
public class ComplexLotteryService implements LotteryService {
	
	@Override
	public List<Integer> draw(int min, int max, int size) {
		return Arrays.asList(4, 8, 15, 16,23,42);
	}

}
 