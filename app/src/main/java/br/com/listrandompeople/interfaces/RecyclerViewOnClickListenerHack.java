package br.com.listrandompeople.interfaces;

import android.view.View;

/**
 * Created by thiagozg on 22/10/2016.
 */

public interface RecyclerViewOnClickListenerHack {

    public void onClickListener(View view, int position);

    public void onLongPressClickListener(View view, int position);

}
