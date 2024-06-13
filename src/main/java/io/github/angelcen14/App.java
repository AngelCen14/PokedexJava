package io.github.angelcen14;

import io.github.angelcen14.controllers.ApiController;

public class App {
    public static void main(String[] args) {
        for (String dir : ApiController.getApiDirList()) {
            System.out.println(dir);
        }
    }
}
