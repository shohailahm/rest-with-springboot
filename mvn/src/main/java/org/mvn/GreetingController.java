package org.mvn;

import org.mvn.math.SimpleMath;
import org.mvn.request.convertors.NumberConvertor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.mvn.request.convertors.NumberConvertor.convertToDouble;
import static org.mvn.request.convertors.NumberConvertor.isnumeric;

public class GreetingController {
//    public final String template="Hello %s";
//    public final AtomicLong id=new AtomicLong();
//    private SimpleMath math  = new SimpleMath();
//    @GetMapping("/sum/{numberone}/{numbertwo}")
//    public Double greeting (@PathVariable("numberone") String numberone,@PathVariable("numbertwo") String numbertwo) throws Exception {
//     //return  new Greetings(id.incrementAndGet(),String.format(template,name));
//        if(!isnumeric(numberone) || !isnumeric(numbertwo)){
//            throw new UnSupportedMathException("Please Set A numeric value");
//        }
//         Double sum=math.add(convertToDouble(numberone), convertToDouble(numbertwo));
//          return sum;
//
//    }

    @GetMapping("/subtraction/{numberone}/{numbertwo}")
    public Double subtraction (@PathVariable("numberone") String numberone,@PathVariable("numbertwo") String numbertwo) throws Exception {

        if(!isnumeric(numberone) || !isnumeric(numbertwo)){
            //throw new UnSupportedMathException("Please Set A numeric value");
        }
        Double sum= 1D;//math.subtract(convertToDouble(numberone), convertToDouble(numbertwo));
        return sum;

    }

    @GetMapping("/multiplication/{numberone}/{numbertwo}")
    public Double multiplication (@PathVariable("numberone") String numberone,@PathVariable("numbertwo") String numbertwo) throws Exception {

        if(!isnumeric(numberone) || !isnumeric(numbertwo)){
            //throw new UnSupportedMathException("Please Set A numeric value");
        }
        Double sum=1D;//math.multiply(convertToDouble(numberone),convertToDouble(numbertwo));
        return sum;

    }
    @GetMapping("/division/{numberone}/{numbertwo}")
    public Double division (@PathVariable("numberone") String numberone,@PathVariable("numbertwo") String numbertwo) throws Exception {
        if(!isnumeric(numberone) || !isnumeric(numbertwo)){
          //  throw new UnSupportedMathException("Please Set A numeric value");
        }
        Double sum=1D;//math.divide(convertToDouble(numberone),convertToDouble(numbertwo));
        return sum;

    }



}
