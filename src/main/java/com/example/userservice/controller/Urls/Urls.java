package com.example.userservice.controller.Urls;

public interface Urls {
    String ROOT = "api";

    interface User{
        String NAME = "user";

        String FULL = ROOT + "/" + NAME;

        interface id{
            String NAME = "{id}";
            String FULL = User.FULL + "/" + NAME;
        }
    }
    interface Role{
        String NAME = "role";
        String FULL = ROOT + "/" + NAME;

        interface id{
            String NAME = "{id}";
            String FULL = Role.FULL + "/" + NAME;
        }
    }
}
