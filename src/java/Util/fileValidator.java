/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
/**
 *
 * @author ABRA
 */
@FacesValidator(value="fileUploadValidator")
public class fileValidator implements Validator,Serializable{

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        Part file = (Part) arg2;
 
        FacesMessage message=null;
 
        try {
            if(file==null){
            }
            else{
            
 
           
                
             if (!(file.getContentType().endsWith("image/png"))&&!(file.getContentType().endsWith("jpeg"))){
                message=new FacesMessage(file.getContentType());
             }
 
            if (  message!=null)
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message );
                }
            }
 
        } catch (ValidatorException ex) {
               throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
    }
    
}
