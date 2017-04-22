/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author siddhartha
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblDisplay;
    @FXML
    private Button btn1,btn2,
            btn3,btn4,
            btn5,btn6,
            btn7,btn8,
            btn9,btnCc,
            clkCls,btnPlus;
    
    private ArrayList<Float> arrayList;
    private ArrayList<String> arrayListOperators;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        lblDisplay.setText("0");
       arrayList = new ArrayList<>();
       arrayListOperators = new ArrayList<>();
        
    } 
    
    @FXML
    private void clkBtn(Event e){
        Button btn = (Button) e.getSource();
        if((!lblDisplay.getText().equals("Error!"))&&!(lblDisplay.getText().equals("0"))&&(lblDisplay.getText().length()!=0)){
            lblDisplay.setText(lblDisplay.getText()+btn.getText());
        }else{
            lblDisplay.setText(btn.getText());
        }
    }
    
    @FXML
    private void clkBtnCc(Event e){
        
        String s = lblDisplay.getText();
        String s2 = "";
        for(int i=0;i<s.length()-1;i++){
            s2 = s2.concat(String.valueOf(s.charAt(i)));
        }
        
        if(s2.isEmpty()){
            s2 = "0";
        }
    lblDisplay.setText(s2);
}
    
    @FXML
    private void clkCls(Event e){
       if((lblDisplay.getText().length()>1)||(!lblDisplay.getText().equals("0"))||(lblDisplay.getText().equals("Error!"))){
           lblDisplay.setText("0");
           arrayList.clear();
           arrayListOperators.clear();
       }
    
}
    
    @FXML
    private void btnPercent(Event e){
       lblDisplay.setText(String.valueOf(Float.parseFloat(lblDisplay.getText())/100)); 
    }
    
    @FXML
    private void btnEq(Event e){
        
      try{
        stringToMathConverter(lblDisplay.getText());
      //lblDisplay.setText(String.valueOf());
     // System.out.println("hi");
     
     float sum = 0;
            int position = 0;
   
      for(String s:arrayListOperators){
        if(s.equals("+")){
         
                 if(sum==0){
                     
         sum =sum+ arrayList.get(position)+arrayList.get(position+1);
         position = position+2;
            }else{
                sum =sum+ arrayList.get(position);
                position = position+1;
            }  
         
     } else  if(s.equals("-")){
         
                 if(sum==0){
                     
         sum =sum+ (arrayList.get(position)-arrayList.get(position+1));
         position = position+2;
            }else{
                sum =sum- arrayList.get(position);
                position = position+1;
            }  
         
     } else  if(s.equals("x")){
         
                 if(sum==0){
                     
         sum =sum+ (arrayList.get(position)*arrayList.get(position+1));
         position = position+2;
            }else{
                sum =sum* arrayList.get(position);
                position = position+1;
            }  
         
     } else  if(s.equals("/")){
         
                 if(sum==0){
                     
         sum =sum+ (arrayList.get(position)/arrayList.get(position+1));
         position = position+2;
            }else{
                sum =sum/ arrayList.get(position);
                position = position+1;
            }  
         
     } 
     }
      
      
    //System.out.println(sum);
    lblDisplay.setText(String.valueOf(sum));
    arrayList.clear();
      arrayListOperators.clear();
      }catch(Throwable ex){
          lblDisplay.setText("Error!");
      }
    }
    
    @FXML
    private void btnPnM(Event e){
        lblDisplay.setText(String.valueOf(-Float.parseFloat(lblDisplay.getText())));
    }

    private void stringToMathConverter(String s) {
        
        int operatableNumberCounter = 1;
        String scon = "";
        for(int x=0;x<s.length();x++){
     
           if(!isNumber(String.valueOf(s.charAt(x)),x)){
               arrayListOperators.add(String.valueOf(s.charAt(x)));
               for(int q=0;q<operatableNumberCounter+2;q++){
            if(operatableNumberCounter>arrayList.size()){
                arrayList.add(Float.valueOf(scon));
            }
        }
               operatableNumberCounter++;
                
               scon = "";
             
           } else {
               String ss = String.valueOf(s.charAt(x)).trim();
            scon = scon.concat(ss);
        
           }
           
           
        }
        
      
        arrayList.add(Float.valueOf(scon));
       System.out.println(arrayList);   
        
    }

    private boolean isNumber(String s, int x) {
        
        if(x!=0){
             if(!s.equals(".")){
            Pattern pattern = Pattern.compile("^[0-9]+$");
Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
        }else{
            return true;
        } 
        }else{
            return true;
        }
      

    }
}
