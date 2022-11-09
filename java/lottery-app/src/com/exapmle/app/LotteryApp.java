package com.exapmle.app;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.Service;
import com.example.lottery.service.ServiceQuality;

import java.util.Optional;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.function.Predicate;

public class LotteryApp {
    public static void main(String[] args) {
        LotteryService lotterySrv;

        ServiceLoader<LotteryService> services = ServiceLoader.load(LotteryService.class);
        lotterySrv = services.findFirst().get();
        
        Predicate<LotteryService> isFast = impl -> {Class<?> clazz = impl.getClass();
        if (clazz.isAnnotationPresent(Service.class)) {
        	Service service = clazz.getAnnotation(Service.class);
        	if(service.value() == ServiceQuality.FAST) {
        		return true;
        	}
        }
        return false;
        }; 
        
        lotterySrv = services.stream().map(Provider::get).filter(isFast).findFirst();
        
        
        lotterySrv.ifPresentOrElse(srv -> srv.draw(1,49,6,10).forEach(System.out::println),
        () -> System.Out.Println("Connot fint a matching service!"));
    }
}
