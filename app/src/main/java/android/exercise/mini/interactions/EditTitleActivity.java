package android.exercise.mini.interactions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {
      fabStartEdit.setVisibility(View.GONE);
      fabEditDone.setVisibility(View.VISIBLE);
      textViewTitle.setVisibility(View.GONE);
      editTextTitle.setVisibility(View.VISIBLE);
      editTextTitle.setText(textViewTitle.getText().toString());

      InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
      inputMethodManager.showSoftInput(editTextTitle, InputMethodManager.SHOW_FORCED);
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      fabEditDone.setVisibility(View.GONE);
      fabStartEdit.setVisibility(View.VISIBLE);
      textViewTitle.setText(editTextTitle.getText().toString());
      textViewTitle.setVisibility(View.VISIBLE);
      editTextTitle.setVisibility(View.GONE);

      InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(editTextTitle.getWindowToken(), 0);
    });
  }

  @Override
  public void onBackPressed() {
    // BACK button was clicked
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    if (editTextTitle.getVisibility() == View.VISIBLE){
      editTextTitle.setVisibility(View.GONE);
      textViewTitle.setVisibility(View.VISIBLE);
      fabEditDone.setVisibility(View.GONE);
      fabStartEdit.setVisibility(View.VISIBLE);
    }
    else{
      super.onBackPressed();
    }
  }
}