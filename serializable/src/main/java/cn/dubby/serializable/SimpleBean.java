package cn.dubby.serializable;

import java.io.Serializable;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class SimpleBean implements Serializable {

    private static final long serialVersionUID = -7658138321944526208L;

    private int id;

    private String name;

    private Long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
