package com.inc.lakio.androidapppdf.Controller;

import android.content.Context;
import android.content.Intent;

import com.inc.lakio.androidapppdf.Model.Entity;

/**
 * Created by Lakio on 15/06/2015.
 */
public final class ActivitiesController
{
    public static void navigate(Context source, Class<?> arrival, Entity e)
    {
        Entity _entity = e;
        Intent _i = new Intent(source, arrival);
        _i.putExtra("entityObject", _entity);
        _i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        source.startActivity(_i);
    }

    public static void navigate(Context source, Class<?> arrival)
    {
        Intent _i = new Intent(source, arrival);
        _i.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        source.startActivity(_i);
    }

}
