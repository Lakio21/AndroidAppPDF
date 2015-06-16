package com.inc.lakio.androidapppdf.Model;

import java.util.ArrayList;

/**
 * Created by Carn on 15/06/2015.
 */
public class Restaurant extends Service
{
    private ArrayList<Menu> _menus;

    public ArrayList<Menu> getMenus() {
        return _menus;
    }

    public void setMenus(ArrayList<Menu> _menus) {
        this._menus = _menus;
    }

    public void setMenu(Menu _menu) {
        this._menus.add(_menu);
    }
}
