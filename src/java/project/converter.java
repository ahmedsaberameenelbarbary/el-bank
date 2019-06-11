package project;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dateco")
public class converter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      SimpleDateFormat sdf = new  SimpleDateFormat("dd-mm-yyyy");
      Date date;
     if(value==null||"".equals(value)){
     FacesMessage fm = new FacesMessage("you must insert value");
    throw new ConverterException(fm);
     }else{
    try {
        date = sdf.parse(value);
        return date;
    } catch (ParseException ex) {

  
    }
    
    FacesMessage fm1 = new FacesMessage("there is problem");
    throw new ConverterException(fm1);
     }
     }
   

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      String s ;
      Date date;
       SimpleDateFormat sdf = new  SimpleDateFormat("dd-mm-yyyy");
       date = (Date)value;
       s=sdf.format(date);

return s;
    }
    
}
