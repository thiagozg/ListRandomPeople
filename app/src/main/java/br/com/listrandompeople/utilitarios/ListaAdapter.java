package br.com.listrandompeople.utilitarios;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.listrandompeople.R;
import br.com.listrandompeople.interfaces.RecyclerViewOnClickListenerHack;
import br.com.listrandompeople.model.Pessoa;


/**
 * Created by thiagozg on 22/10/2016.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {

    private List<Pessoa> mListPessoas;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public ListaAdapter(Context context, List<Pessoa> mListPessoas) {
        this.mListPessoas = mListPessoas;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = this.mLayoutInflater.inflate(R.layout.item_lista, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNome.setText(this.mListPessoas.get(position).getNome());
        holder.tvUser.setText(this.mListPessoas.get(position).getUser());
        holder.tvEmail.setText(this.mListPessoas.get(position).getEmail());
        holder.tvTelefone.setText(this.mListPessoas.get(position).getTelefone());
    }

    @Override
    public int getItemCount() {
        return this.mListPessoas.size();
    }

    public void addListItem(Pessoa pessoa, int position) {
        this.mListPessoas.add(pessoa);
        notifyItemInserted(position);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        this.mRecyclerViewOnClickListenerHack = r;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvNome;
        public TextView tvUser;
        public TextView tvEmail;
        public TextView tvTelefone;
        public TextView tvCidade;
        public TextView tvEstado;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            this.tvUser = (TextView) itemView.findViewById(R.id.tvUser);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            this.tvTelefone = (TextView) itemView.findViewById(R.id.tvTelefone);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }

    }

}
