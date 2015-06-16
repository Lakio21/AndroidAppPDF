package com.inc.lakio.androidapppdf.Controller;

import com.inc.lakio.androidapppdf.Model.Entity;
import com.inc.lakio.androidapppdf.Model.Restaurant;
import com.inc.lakio.androidapppdf.Model.Shop;
import com.inc.lakio.androidapppdf.Model.Show;

/**
 * Created by Lakio on 15/06/2015.
 */
public class EntityFactory
{

    public Entity getEntity(String entityType)
    {
        Entity entity = null;


        switch (entityType)
        {
            case "shop":
                entity = new Shop();

            case "show":
                entity = new Show();

            case "restaurant":
                entity = new Restaurant();

        }

        return entity;

    }
}
