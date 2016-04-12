package com.gguzman.android.ggcoupons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter for the Chain Stores.
 * @see BaseAdapter
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.12
 */
public class ChainStoreAdapter extends BaseAdapter{

    private static ArrayList<ChainStore> searchArrayList;
    private LayoutInflater mInflater;

    /**
     * Constructor for the chain stores adapter.
     * @param context The context from which it's called.
     * @param results The list to inflate.
     * @since v1.2016.04.12
     */
    public ChainStoreAdapter (Context context, ArrayList<ChainStore> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Get the amount of items in the list.
     * @return The size of the list
     */
    public int getCount() {
        return searchArrayList.size();
    }

    /**
     * Get a specific item of the list according to the position.
     * @param position The position of the item in the list.
     * @return The item Object.
     */
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    /**
     * Get a specific item ID. In this case the position is the ID.<br><br>
     *     This is an auto imported method that needs to be implemented when
     *     you extend {@code BaseAdapter}
     * @param position The position of the item in the list.
     * @return The position.
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method that inflates the view.
     * @param position Position of a specific element in the list.
     * @param convertView The view to convert.
     * @param parent The parent ViewGroup.
     * @return The inflated view.
     * @since v1.2016.04.12
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.subcategories_list_item, parent,false);
            holder = new ViewHolder();
            holder.txtCsName = (TextView) convertView.findViewById(R.id.subcat_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtCsName.setText(searchArrayList.get(position).getCsName());
        // holder.txtCatID.setText(searchArrayList.get(position).getCatID());
        return convertView;
    }

    /**
     * Inner class for the holder to be used in the Chain Stores view
     * @since v1.2016.04.12
     */
    static class ViewHolder {
        TextView txtCsName;
        TextView txtCsID;
    }
}

