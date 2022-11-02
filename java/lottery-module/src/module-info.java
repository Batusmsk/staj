import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.ComplexLotteryService;
import com.example.lottery.service.business.SimpleLotteryService;

module com.example.lottery {
    requires com.example.randomnumgen;

    exports com.example.lottery.service.business;
    exports com.example.lottery.service;
    provides LotteryService with SimpleLotteryService, ComplexLotteryService;
    uses com.example.numgen.service.RandomNumberService;
}