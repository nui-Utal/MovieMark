package dto;

import java.util.Date;

/**
 * created by Xu on 2023/11/16 15:19.
 * 保留部分user信息，仅传输基本字段
 */
public class UserDto {
    private String name;
    private String email;
    private Date lastTime;

    public UserDto() {}

    public UserDto(String name, String email, String lastIp, Date lastTime) {
        this.name = name;
        this.email = email;
        this.lastTime = lastTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
