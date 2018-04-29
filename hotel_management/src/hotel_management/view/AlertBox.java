package hotel_management.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
  public static void show(String msg)
  {
	  Stage s = new Stage();
	  s.initModality(Modality.APPLICATION_MODAL);
	  VBox lout= new VBox(10);
	  s.setMinWidth(300);
	  Button exit=new Button("Exit");
	  Label l= new Label();
	  l.setText(msg);
	  exit.setOnAction(e->s.close());
	  lout.getChildren().addAll(l,exit);
	  lout.setAlignment(Pos.CENTER);
	  Scene sc=new Scene(lout);
	  s.setScene(sc);
	  s.showAndWait();
  }
}
