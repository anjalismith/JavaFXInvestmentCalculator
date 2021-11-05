import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FinalProject extends Application {
  private TextField ia;
  private TextField noy;
  private TextField air;
  private TextField fv;
  private Button calc;
  private Double investmentA;
  private Double numYears;
  private Double annualInRate;
  private Double futVal;
  
  @Override
  public void start ( Stage myStage ) {
//This creates a VBox full of TextFields and one Button object. It sets the alignment and spacings of the controls in the VBox and it also sets the TextField that displays the future value to uneditable.
     VBox text = new VBox();
     Region bottom = new Region();
     ia= new TextField();
     noy = new TextField();
     air = new TextField();
     fv = new TextField();
     fv.setEditable(false);
     calc = new Button("Calculate");
     text.getChildren().addAll(ia,noy,air,fv);
     text.getChildren().add(calc);
     text.setAlignment(Pos.TOP_RIGHT);
     text.setSpacing(10);
     
//It then creates a VBox of labels for each TextField and sets its spacing to 20 pixels.
     VBox labels = new VBox(20);
     labels.getChildren().addAll(new Label("Investment Amount: "), new Label("Number of Years: "), new Label("Annual Interest Rate: "), new Label("Future Value: "));
     
//A GridPane is then created and centered, and the two VBoxes are added to it with their specified coordinates. 
     GridPane rootNode = new GridPane();
     rootNode.add(text,1,1);
     rootNode.add(labels,0,1);
     rootNode.setAlignment(Pos.CENTER);
     
//The event handler is registered with the event source.
     calc.setOnAction(new calcHandler());
     
//Lastly, the start() method sets the title for the stage, creates a new scene, and adds the scene to the stage.  
     myStage.setTitle("Future Value Calculator");
     Scene myScene = new Scene(rootNode, 300, 200);
     myStage.setScene(myScene);
     myStage.show();
  }
//This program has an inner class that handles the action event fired when the calculate button is pressed. 
  class calcHandler implements EventHandler<ActionEvent> {
     public void handle (ActionEvent e) {
        investmentA = Double.valueOf(ia.getText());
        numYears = Double.valueOf(noy.getText());
        annualInRate = (Double.valueOf(air.getText()))/100;
        fv.setText((calcFutureVal(investmentA,numYears,annualInRate)).toString());
     }
//This method calculates the future value, rounds it to the hundredths place, and returns it.
     public Double calcFutureVal(Double ia, Double noy,Double air) {
        futVal = ia * Math.pow(( 1 + air/12 ),(12 * noy));
        Double roundedVal = Math.round(futVal * 100.0) / 100.0;
        return roundedVal;
     }
  }
}   
     