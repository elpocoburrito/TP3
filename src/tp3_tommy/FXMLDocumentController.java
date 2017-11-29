/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3_tommy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

/**
 *
 * @author usager
 */
public class FXMLDocumentController implements Initializable {
    
    int defaultTextSize = 12;
    String textToFillCMB[] = {"L'Étranger de Camus", "Testez Votre Cerveau", "Test du prof"};
    
    @FXML
    private TextArea txaText;
    
    @FXML
    private TextArea txaPosNum;
    
    @FXML
    private ComboBox cmbTextLoader;
    
    @FXML
    private ComboBox cmbLetters;
    
    @FXML
    private CheckBox chbConfirm;
    
    @FXML
    private void btnTextSizeChanger(ActionEvent event) {
        Button btnSource = (Button) event.getSource();

        char btnSourceLastChar = btnSource.getText().charAt(1);
        
        if (btnSourceLastChar == '+')
            defaultTextSize++;
        else
            defaultTextSize--;
        txaText.setFont(Font.font(defaultTextSize));
    }
    
    @FXML
    private void tgbColorChanger(ActionEvent event) {
        ToggleButton tgbColor = (ToggleButton) event.getSource();
        String textColor = tgbColor.getTextFill().toString().substring(2, 8); // Code de couleur
        txaText.setStyle(txaText.getStyle()+"-fx-control-inner-background:#" + textColor+";");
        System.out.println(txaText.getStyle());
    }
    
    @FXML
    private void copColorChanger(ActionEvent event) {
        ColorPicker copSource = (ColorPicker) event.getSource();
        String textColor = copSource.getValue().toString().substring(2, 8);
        txaText.setStyle(txaText.getStyle()+"-fx-text-fill:#"+textColor+";"); 
        System.out.println(txaText.getStyle());
    }
    
    @FXML
    private void btnQuit(ActionEvent event) {
        boolean checked = chbConfirm.isSelected();

        if (checked == true) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) 
                System.exit(0);
        } else
            System.exit(0);
    }
    
    @FXML
    private void cmbTextLoad(ActionEvent event) {
        ComboBox cmbText = (ComboBox) event.getSource();
        String selectedText = cmbText.getValue().toString();
        if (selectedText == textToFillCMB[0])
            txaText.setText(Texte.etrangerCamus);
        else if (selectedText == textToFillCMB[1])
            txaText.setText(Texte.testezVotreCerveay);
        else
            txaText.setText(Texte.testDuProf);
    }
    @FXML
    private void btnTextClearAction(ActionEvent event) {
        txaText.clear();
    }
    
    public void getAllLetters() {
        for (int i = 65; i < 91; i++) {
            String stringLetter = Character.toString((char) i);
            cmbLetters.getItems().addAll(stringLetter);
        }
        for (int i = 97; i < 123; i++) {
            String stringLetter = Character.toString((char) i);
            cmbLetters.getItems().addAll(stringLetter);
        }
    }
    
    @FXML
    private void sWordsToCap(ActionEvent event) {
        
    }
    @FXML
    private void btnExtractPosNumAction(ActionEvent event) {
        ArrayList<String> wordsList = new ArrayList();
        
        
        String[] tab = txaText.getText().split("");

        String tempWord = "";
        for (int i = 0; i < tab.length; i++) {
            if (Character.isDigit(tab[i].charAt(0)))
                tempWord += tab[i];
            else {
                wordsList.add(tempWord);
                tempWord = "";
            }
        }
        //txaText.setText(tempWord);
        
        String finalString = "";
        for (int i = 0; i < wordsList.size(); i++) {
            System.out.println(wordsList.get(i));
            finalString += wordsList.get(i)+"\n";
        }
        System.out.println(finalString);
        txaPosNum.setText(finalString);
        /*for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
        int nbPos = 0;
        for (int i = 0; i < tab.length; i++) {
            if (Integer.parseInt(tab[i]) >= 0) {
                txaPosNum.setText(tab[i]);
                nbPos++;
            }
        }*/
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTextLoader.getItems().addAll(textToFillCMB);
        txaText.setStyle(txaText.getStyle() + "-fx-control-inner-background: grey;");
        getAllLetters();
    }    
    
}
