package com.example.bookclient.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bookclient.R;
import com.example.bookclient.editor.EditorActivity;
import com.example.bookclient.model.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<Book> book;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeRefresh=findViewById(R.id.swipe_refresh);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));

        fab=findViewById(R.id.add);
        fab.setOnClickListener(view -> startActivityForResult(new Intent(this, EditorActivity.class),INTENT_ADD));

        presenter=new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(()->presenter.getData());


        itemClickListener=((view, position) -> {
            int id=book.get(position).getId();
            String title=book.get(position).getTitle();
            String author=book.get(position).getAuthor();
            String rate=book.get(position).getRating();
            String numberOfPages=book.get(position).getNumberOfPages();

            Intent intent =new Intent(this,EditorActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("title",title);
            intent.putExtra("author",author);
            intent.putExtra("rate",rate);
            intent.putExtra("numberOfPages",numberOfPages);
            startActivityForResult(intent,INTENT_EDIT);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==INTENT_ADD&&resultCode==RESULT_OK){
            presenter.getData();//reload data
        }
        else if (requestCode==INTENT_EDIT&&resultCode==RESULT_OK){
            presenter.getData();//reload data
        }
    }

    @Override
    public void showLoading(){
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading(){
        swipeRefresh.setRefreshing(false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onGetResult(List<Book> books){
        adapter=new MainAdapter(this,books,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        book=books;

    }
    @Override
    public void onErrorLoading(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}