package com.inc.lakio.androidapppdf.Model;

/**
 * Created by Carn on 15/06/2015.
 */
public final class User {
    private static User _instance;
    private int _id;
    private String _login;
    private String _pseudo;

    private User()
    {
    }

    public static User getInstance()
    {
        if (_instance == null)
        {
            _instance = new User();
            return _instance;
        }
        else
        {
            return _instance;
        }
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String _login) {
        this._login = _login;
    }

    public String getPseudo() {
        return _pseudo;
    }

    public void setPseudo(String _pseudo) {
        this._pseudo = _pseudo;
    }
}
