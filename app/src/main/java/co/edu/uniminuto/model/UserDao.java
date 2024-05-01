package co.edu.uniminuto.model;

import android.content.Context;
import android.view.View;

import co.edu.uniminuto.entity.User;

public class UserDao {
    private ManagerDataBase managerDataBase;
    Context context;
    View view;
    private User user;

    public UserDao(Context context, View view) {
        this.context = context;
        this.view = view;
        managerDataBase= new ManagerDataBase(this.context);
    }

}
