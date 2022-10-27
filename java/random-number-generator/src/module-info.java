import com.example.numgen.service.RandomNumberService;
import com.example.numgen.service.business.SimpleRandomNumberService;

module com.example.randomnumgen {
    exports com.example.numgen.service;
    provides RandomNumberService with SimpleRandomNumberService;
}