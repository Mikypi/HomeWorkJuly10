package com.example.michaeliverson.homeworkjuly10;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by michaeliverson on 7/10/17.
 */

public class DataWrapper implements Serializable {

    private ArrayList<GithubRepo> parliaments;

    public DataWrapper(ArrayList<GithubRepo> data) {
        this.parliaments = data;
    }

    public ArrayList<GithubRepo> getParliaments() {
        return this.parliaments;
    }
}
