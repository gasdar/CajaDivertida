package com.gasdar.app.funbox.validators;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gasdar.app.funbox.models.FunBox;
import com.gasdar.app.funbox.models.Product;
import com.gasdar.app.funbox.models.User;
import com.gasdar.app.funbox.models.dtos.UserDto;
import com.gasdar.app.funbox.repositories.ProductRepository;
import com.gasdar.app.funbox.repositories.UserRepository;

@Component
public class FunBoxValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository prodRepository;

    @Autowired
    private Environment environment;
    
    private Errors errorsBox;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return FunBox.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        FunBox box = (FunBox) target;
        errorsBox = errors;

        this.validateBoxName(box.getName());
        this.validateBoxOption(box.getRangeOption());
        this.validateBoxSize(box.getBoxSize());
        this.validateBoxUserDto(box.getUserDto());
    }

    public void validate(Object target, Errors errors, ObjectId userId) {
        FunBox box = (FunBox) target;
        errorsBox = errors;

        this.assignUser(box, userId);
        this.validateBoxName(box.getName());
        this.validateBoxOption(box.getRangeOption());
        this.validateBoxSize(box.getBoxSize());
        this.validateBoxUserDto(box.getUserDto());
    }

    private void assignUser(FunBox box, ObjectId userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            box.setUserDto(new UserDto(userId, user.getName(), user.getEmail()));
        } else {
            box.setUserDto(null);;
        }
    }

    @SuppressWarnings("null")
    private void validateBoxName(String name) {
        if(name == null || name.isBlank()) {
            errorsBox.rejectValue("name", null, environment.getProperty("message.null"));
        } else if(name.trim().length() < 4) {
            errorsBox.rejectValue("name", null, environment.getProperty("message.box.name.min"));
        }
    }

    @SuppressWarnings("null")
    private void validateBoxOption(Integer rangeOption) {
        if(rangeOption == null) {
            errorsBox.rejectValue("rangeOption", null, environment.getProperty("message.null"));
        } else {
            int option = rangeOption.intValue();
            if(option != 1 && option != 2 && option != 3) {
                errorsBox.rejectValue("rangeOption", null, environment.getProperty("message.box.range-option"));
            }
        }
    }

    @SuppressWarnings("null")
    private void validateBoxSize(String boxSize) {
        if(boxSize == null) {
            errorsBox.rejectValue("boxSize", null, environment.getProperty("message.null"));
        } else if(  !boxSize.equalsIgnoreCase("small") &&
                    !boxSize.equalsIgnoreCase("medium") &&
                    !boxSize.equalsIgnoreCase("large") ) {
            errorsBox.rejectValue("boxSize", null, environment.getProperty("message.box.box-size"));
        }
    }

    @SuppressWarnings("null")
    private void validateBoxUserDto(UserDto userDto) {
        if(userDto == null) {
            errorsBox.rejectValue("userDto", null, environment.getProperty("message.null"));
        }
    }

    public boolean assignOthersValues(FunBox box) {
        // Verficamos que existan la cantidad de productos adecuados para generar la caja
        Integer option = box.getRangeOption();
        String size = box.getBoxSize();
        if(!this.isBoxAssignable(size, option)) {
            return false;
        }
        //Asignamos los valores en caso de estar correcto
        this.assignProdPriceAndBoxPrice(box);
        box.setState("Pagando");
        return true;
    }

    private boolean isBoxAssignable(String size, int option) {
        int[] dataBox = this.getProdsAndRange(size, option);
        int totalMinProds=dataBox[0]*2, priceMin=dataBox[1], priceMax=dataBox[2];
        // long totalProdsBD = prodRepository.countBetweenMinAndMax(priceMin, priceMax); // Corregir
        List<Product> listProds = prodRepository.findByPriceBetween(priceMin, priceMax);
        long totalProdsBD = listProds.size();
        return totalProdsBD >= totalMinProds;
    }

    private void assignProdPriceAndBoxPrice(FunBox box) {
        int[] dataBox = this.getProdsAndRange(box.getBoxSize(), box.getRangeOption());
        int totalProds=dataBox[0], priceMin=dataBox[1], priceMax=dataBox[2];
        
        int priceByProd =   (priceMin==0 && priceMax==2499) ? 1699 :
                            (priceMin==2500 && priceMax==4999) ? 4199 : 7999;
        priceByProd =   (totalProds==3) ? priceByProd :
                        (totalProds==5) ? priceByProd-200 : priceByProd-400;

        box.setProdPrice(priceByProd);
        box.setBoxPrice(priceByProd*totalProds);
    }

    private int[] getProdsAndRange(String size, int option) {
        int totalProds =    (size.equalsIgnoreCase("small")) ? 3 :
                            (size.equalsIgnoreCase("medium")) ? 5 : 10;
        int priceMin, priceMax;
        if(option==1) {
            priceMin=0;
            priceMax=2499;
        } else if(option==2) {
            priceMin=2500;
            priceMax=4999;
        } else {
            priceMin=5000;
            priceMax=10000;
        }
        int[] data = {totalProds, priceMin, priceMax};
        return data;
    } 

}
