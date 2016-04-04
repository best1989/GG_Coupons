package com.android.gguzman.ggcoupons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter for the Subcategories ListView.
 * @see BaseAdapter
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.03
 */
public class SubcategoryAdapter extends BaseAdapter{

    //TODO: See if it's possible to extend CategoryAdapter and re-utilize code
    private static ArrayList<Subcategory> searchArrayList;
    private LayoutInflater mInflater;

    /**
     * Constructor for the subcategories adapter.
     * @param context The context from which it's called.
     * @param results The list to inflate.
     * @since v1.2016.03.22
     */
    public SubcategoryAdapter(Context context, ArrayList<Subcategory> results) {
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
     * @since v1.2016.03.22
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.subcategories_list_item, parent,false);
            holder = new ViewHolder();
            holder.txtSubcatName = (TextView) convertView.findViewById(R.id.subcat_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtSubcatName.setText(searchArrayList.get(position).getSubcatName());
       // holder.txtCatID.setText(searchArrayList.get(position).getCatID());
        return convertView;
    }

    /**
     * Inner class for the holder to be used in the Subcategories ListView.
     * @since v1.2016.03.22
     */
    static class ViewHolder {
        TextView txtSubcatName;
        TextView txtSubcatID;
    }
}

