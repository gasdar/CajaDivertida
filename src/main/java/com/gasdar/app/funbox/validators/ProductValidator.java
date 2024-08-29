package com.gasdar.app.funbox.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gasdar.app.funbox.models.Product;

@Component
public class ProductValidator implements Validator {

    private Errors errorsProd;

    @Autowired
    private Environment env;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Product prod = (Product) target;
        this.errorsProd = errors;

        validateProdName(prod.getName());
        validateProdDesc(prod.getDesc());
        validateProdCat(prod.getCategory());
        validateProdStock(prod);
        validateProdPrice(prod.getPrice());
    }

    public void validateList(Errors errors, List<Product> prods) {
        Product prod;
        this.errorsProd = errors;
        boolean flagError = false;

        for(int i=0; i<prods.size(); i++) {
            prod = prods.get(i);
            flagError = (!validateProdName(prod.getName(), (i+1))) ? true : flagError;
            flagError = (!validateProdDesc(prod.getDesc(), (i+1))) ? true : flagError;
            flagError = (!validateProdCat(prod.getCategory(), (i+1))) ? true : flagError;
            flagError = (!validateProdStock(prod, (i+1))) ? true : flagError;
            flagError = (!validateProdPrice(prod.getPrice(), (i+1))) ? true : flagError;
            if(flagError) {
                return;
            }
        }
    }

    @SuppressWarnings("null")
    private void validateProdName(String name) {
        if(name == null || name.isBlank()) {
            errorsProd.rejectValue("name", null, env.getProperty("message.null"));
        } else if(name.trim().length() < 4) {
            errorsProd.rejectValue("name", null, env.getProperty("message.min.characters"));
        }
    }

    @SuppressWarnings("null")
    private boolean validateProdName(String name, int index) {
        if(name == null || name.isBlank()) {
            errorsProd.rejectValue("name", null, env.getProperty("message.null.list") + index);
            return false;
        } else if(name.trim().length() < 4) {
            errorsProd.rejectValue("name", null, env.getProperty("message.min.characters.list") + index);
            return false;
        }
        return true;
    }

    @SuppressWarnings("null")
    private void validateProdDesc(String desc) {
        if(desc == null || desc.isBlank()) {
            errorsProd.rejectValue("desc", null, env.getProperty("message.null"));
        } else if(desc.trim().length() < 4) {
            errorsProd.rejectValue("desc", null, env.getProperty("message.min.characters"));
        }
    }

    @SuppressWarnings("null")
    private boolean validateProdDesc(String desc, int index) {
        if(desc == null || desc.isBlank()) {
            errorsProd.rejectValue("desc", null, env.getProperty("message.null.list") + index);
            return false;
        } else if(desc.trim().length() < 4) {
            errorsProd.rejectValue("desc", null, env.getProperty("message.min.characters.list") + index);
            return false;
        }
        return true;
    }

    @SuppressWarnings("null")
    private void validateProdCat(String category) {
        if(category == null || category.isBlank()) {
            errorsProd.rejectValue("category", null, env.getProperty("message.null"));
        } else if(category.trim().length() < 4) {
            errorsProd.rejectValue("category", null, env.getProperty("message.min.characters"));
        }
    }

    @SuppressWarnings("null")
    private boolean validateProdCat(String category, int index) {
        if(category == null || category.isBlank()) {
            errorsProd.rejectValue("category", null, env.getProperty("message.null.list") + index);
            return false;
        } else if(category.trim().length() < 4) {
            errorsProd.rejectValue("category", null, env.getProperty("message.min.characters.list") + index);
            return false;
        }
        return true;
    }

    @SuppressWarnings("null")
    private void validateProdStock(Product prod) {
        Integer stock = prod.getStock();
        if(stock == null) {
            prod.setStock(1000);
        } else if(stock <= 0) {
            errorsProd.rejectValue("stock", null, env.getProperty("message.prod.stock"));
        }
    }

    @SuppressWarnings("null")
    private boolean validateProdStock(Product prod, int index) {
        Integer stock = prod.getStock();
        if(stock == null) {
            prod.setStock(1000);
        } else if(stock <= 0) {
            // Error de rejectValue, dependiendo de estructura bindingResult
            errorsProd.rejectValue("stock", null, env.getProperty("message.prod.stock.list") + index);
            return false;
        }
        return true;
    }

    @SuppressWarnings("null")
    private void validateProdPrice(Integer price) {
        if(price == null) {
            errorsProd.rejectValue("price", null, env.getProperty("message.null"));
        } else if(price < 500 || price > 10000) {
            errorsProd.rejectValue("price", null, env.getProperty("message.prod.price"));
        }
    }

    @SuppressWarnings("null")
    private boolean validateProdPrice(Integer price, int index) {
        if(price == null) {
            errorsProd.rejectValue("price", null, env.getProperty("message.null.list") + index);
            return false;
        } else if(price < 500 || price > 10000) {
            errorsProd.rejectValue("price", null, env.getProperty("message.prod.price.list") + index);
            return false;
        }
        return true;
    }

}
