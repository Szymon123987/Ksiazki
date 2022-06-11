package com.example.bookclient.editor;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookclient.R;
import com.example.bookclient.model.Book;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText et_title,et_author,et_rate,et_numberOfPages;
    ProgressDialog progressDialog;
    int id;
    String title,author,rate,numberOfPages;
    Menu actionMenu;

    EditorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        et_title=findViewById(R.id.title);
        et_author=findViewById(R.id.author);
        et_rate=findViewById(R.id.rate);
        et_numberOfPages=findViewById(R.id.numberOfPages);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter=new EditorPresenter(this);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        title=intent.getStringExtra("title");
        author=intent.getStringExtra("author");
        rate=intent.getStringExtra("rate");
        numberOfPages=intent.getStringExtra("numberOfPages");

        setDataFromIntentExtra();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu=menu;

        if(id!=0){
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(true);

        }
        else{
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);

        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Book book=new Book();
        String title=et_title.getText().toString().trim();
        String author=et_author.getText().toString().trim();
        String rate=et_rate.getText().toString().trim();
        String numberOfPages=et_numberOfPages.getText().toString().trim();


        switch (item.getItemId()){
            case R.id.save:
                if (title.isEmpty()) {
                    et_title.setError("Please enter a title");
                } else if (author.isEmpty()) {
                    et_author.setError("Please enter a author");
                }
                else if (rate.isEmpty()) {
                    et_rate.setError("Please enter a rate");
                }
                else if (numberOfPages.isEmpty()) {
                    et_numberOfPages.setError("Please enter a number of pages");
                }else {
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setNumberOfPages(numberOfPages);
                    book.setRating(rate);
                    presenter.saveBook(book);
                }
                return true;
            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);

                return true;


            case R.id.update:
                if (title.isEmpty()) {
                    et_title.setError("Please enter a title");
                } else if (author.isEmpty()) {
                    et_author.setError("Please enter a author");
                }
                else if (rate.isEmpty()) {
                    et_rate.setError("Please enter a rate");
                }
                else if (numberOfPages.isEmpty()) {
                    et_numberOfPages.setError("Please enter a number of pages");
                }else {
                    book.setId(id);
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setNumberOfPages(numberOfPages);
                    book.setRating(rate);
                    presenter.updateBook(book);
                }
                return true;

            case R.id.delete:
                //
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirm!");
                alertDialog.setMessage("Are you sure?");
                alertDialog.setNegativeButton("Yes",
                        (dialogInterface, i) -> {dialogInterface.dismiss(); presenter.deleteBook(id);});
                alertDialog.setPositiveButton("Cancel",
                        (dialogInterface, i) ->{dialogInterface.dismiss();});
                alertDialog.show();

                return true;


            default:
                actionMenu.findItem(R.id.update).setVisible(false);
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorActivity.this,message,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorActivity.this,message,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    private void setDataFromIntentExtra() {
        if(id!=0){
            et_title.setText(title);
            et_author.setText(author);
            et_rate.setText(rate);
            et_numberOfPages.setText(numberOfPages);

            getSupportActionBar().setTitle("Update book");
            readMode();
        }
        else{
            editMode();
        }
    }

    private void editMode() {
        et_title.setFocusableInTouchMode(true);
        et_author.setFocusableInTouchMode(true);
        et_rate.setFocusableInTouchMode(true);
        et_numberOfPages.setFocusableInTouchMode(true);
    }

    private void readMode() {

        et_title.setFocusableInTouchMode(false);
        et_author.setFocusableInTouchMode(false);
        et_rate.setFocusableInTouchMode(false);
        et_numberOfPages.setFocusableInTouchMode(false);
        et_title.setFocusable(false);
        et_author.setFocusable(false);
        et_rate.setFocusable(false);
        et_numberOfPages.setFocusable(false);
    }

}
