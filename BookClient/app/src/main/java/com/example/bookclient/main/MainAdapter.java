package com.example.bookclient.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookclient.R;
import com.example.bookclient.model.Book;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private final Context context;
    private final List<Book> books;
    private final ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Book> books, ItemClickListener itemClickListener) {
        this.context = context;
        this.books = books;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Book book=books.get(position);
        holder.tv_title.setText(book.getTitle());
        holder.tv_author.setText(book.getAuthor());
        holder.tv_rate.setText(book.getRating());
        holder.tv_numberOfPages.setText(book.getNumberOfPages());

    }
    @Override
    public int getItemCount() {
        return books.size();
    }



    public static class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView tv_title, tv_author, tv_rate,tv_numberOfPages;
        CardView card_item;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView,ItemClickListener itemClickListener){
            super(itemView);

            tv_title=itemView.findViewById(R.id.bookListItem_title);
            tv_author=itemView.findViewById(R.id.bookListItem_author);
            tv_rate=itemView.findViewById(R.id.bookListItem_rating);
            tv_numberOfPages=itemView.findViewById(R.id.bookListItem_numberOfPages);
            card_item=itemView.findViewById(R.id.card_item);

            this.itemClickListener=itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }

    }
    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }
}
