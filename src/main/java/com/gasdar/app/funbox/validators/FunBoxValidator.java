package com.gasdar.app.funbox.validators;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gasdar.app.funbox.models.FunBox;
import com.gasdar.app.funbox.models.User;
import com.gasdar.app.funbox.models.dtos.UserDto;
import com.gasdar.app.funbox.repositories.UserRepository;

@Component
public class FunBoxValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

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

        validateBoxName(box.getName());
        validateBoxOption(box.getRangeOption());
        validateBoxSize(box.getBoxSize());
        validateBoxUserDto(box.getUserDto());
    }

    public void validate(Object target, Errors errors, ObjectId userId) {
        FunBox box = (FunBox) target;
        errorsBox = errors;

        assignUser(box, userId);
        validateBoxName(box.getName());
        validateBoxOption(box.getRangeOption());
        validateBoxSize(box.getBoxSize());
        validateBoxUserDto(box.getUserDto());
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

    public boolean assignBoxValues(FunBox box) {
        // Verficamos que existan la cantidad de productos adecuados para generar la caja
        Integer option = box.getRangeOption();
        String size = box.getBoxSize();
        if(!isBoxAssignable(size, option)) {
            return false;
        }
        //Asignamos los valores en caso de estar correcto
        assignProdPriceAndBoxPrice(box);
        box.setState("Pagando");
        return true;
    }

    private boolean isBoxAssignable(String size, int option) {
        int totalProds =    (size.equalsIgnoreCase("small")) ? 3 :
                            (size.equalsIgnoreCase("medium")) ? 5 : 10;
        totalProds = totalProds * 2;
        int min=0, max=0;


        return true;
    }

    private void assignProdPriceAndBoxPrice(FunBox box) {
        Integer option = box.getRangeOption();
        String size = box.getBoxSize();


    }

}
