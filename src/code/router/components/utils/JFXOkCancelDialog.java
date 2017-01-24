package code.router.components.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 * Created by razvanolar on 24.01.2017
 */
public class JFXOkCancelDialog extends JFXDialogLayout {

  private JFXButton okButton;
  private JFXButton cancelButton;

  private String confirmText;
  private String title;
  private Node body;
  private JFXDialog dialog;

  public JFXOkCancelDialog(String title, String confirmText, Node body, JFXDialog dialog) {
    super();
    this.title = title;
    this.confirmText = confirmText;
    this.body = body;
    this.dialog = dialog;
    init();
    addListeners();
  }

  private void init() {
    okButton = new JFXButton(confirmText);
    cancelButton = new JFXButton("Cancel");
    setHeading(new Text(title));
    setBody(body);
    setActions(okButton, cancelButton);
  }

  private void addListeners() {
    cancelButton.setOnAction(event -> {
      if (dialog != null)
        dialog.close();
    });
  }

  public JFXButton getOkButton() {
    return okButton;
  }
}
