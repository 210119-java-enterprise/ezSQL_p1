package com.revature.util;

import java.util.Objects;

public class Database {
    private String database;
    private String url;
    private String username;
    private String password;

    public Database(){
        super();
    }

    public static boolean validate(Database db) {
        return false;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Database database1 = (Database) o;
        return database.equals(database1.database)
                && url.equals(database1.url)
                && username.equals(database1.username)
                && password.equals(database1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database, url, username, password);
    }

    @Override
    public String toString() {
        return "Database{" +
                "database='" + database + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
