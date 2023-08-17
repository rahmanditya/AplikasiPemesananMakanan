package com.example.aplikasipemesananmakanan.Helper;

import android.content.Context;

import com.example.aplikasipemesananmakanan.Interface.ChangeNumberItemsListener;
import com.example.aplikasipemesananmakanan.Model.Menu;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public ArrayList<Menu> getListCart(){
        return tinyDB.getListObject("CartList");
    }



    public void plusNumberFood(ArrayList<Menu>menulist, int position, ChangeNumberItemsListener changeNumberItemsListener){
        menulist.get(position).setNumberInCart(menulist.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",menulist);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<Menu>menulist, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(menulist.get(position).getNumberInCart()==1){
            menulist.remove(position);
        }else{
            menulist.get(position).setNumberInCart(menulist.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",menulist);
        changeNumberItemsListener.changed();
    }


    public double getTotalHarga(){
        ArrayList<Menu>menulist = getListCart();
        double total = 0;
        for (int i = 0; i < menulist.size(); i++) {
            total=total+(menulist.get(i).getHarga()*menulist.get(i).getNumberInCart());
        }
        return total;
    }

    public int getTotalItem(){
        ArrayList<Menu>menulist = getListCart();
        int item=0;
        for (int i = 0; i < menulist.size(); i++) {
            item=item+(menulist.get(i).getNumberInCart());
        }
        return item;
    }
}
