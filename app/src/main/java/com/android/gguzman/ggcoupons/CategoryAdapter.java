package com.android.gguzman.ggcoupons;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Custom adapter for the Categories ListView.
 * @see android.widget.BaseAdapter
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.03.22
 */
public class CategoryAdapter extends BaseAdapter{

    private static ArrayList<Category> searchArrayList;
    private LayoutInflater mInflater;
    private int[] imgIDs;

    /**
     * Constructor for the categories adapter.
     * @param context The context from which it's called.
     * @param results The list to inflate.
     * @param ids An array of ints with the IDs of the image resources for each category
     * @since v1.2016.03.22
     */
    public CategoryAdapter(Context context, ArrayList<Category> results, int[] ids) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
        imgIDs = ids;
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
            convertView = mInflater.inflate(R.layout.categories_list_item, parent,false);
            holder = new ViewHolder();
            holder.txtCatName = (TextView) convertView.findViewById(R.id.cat_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (imgIDs != null)
            convertView.setBackgroundResource(imgIDs[position]);
        holder.txtCatName.setText(searchArrayList.get(position).getCatName());
       // holder.txtCatID.setText(searchArrayList.get(position).getCatID());
        return convertView;
    }

    /**
     * Inner class for the holder to be used in the Categories ListView.
     * @since v1.2016.03.22
     */
    static class ViewHolder {
        TextView txtCatName;
        TextView txtCatID;
    }
}

