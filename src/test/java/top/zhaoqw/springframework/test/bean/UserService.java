package top.zhaoqw.springframework.test.bean;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class UserService {
  public String name;

  public UserService(String name) {
    this.name = name;
  }

  public void queryUserInfo(){
    System.out.println("查询用户信息:" + "username:" + name);
  }

}
