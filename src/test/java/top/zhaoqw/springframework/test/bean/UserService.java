package top.zhaoqw.springframework.test.bean;

/**
 * @author zhaoqw
 * @date 2022/08/31
 */
public class UserService {
  public String name;

  public String uId;

  private UserDao userDao;

  public UserService() {
  }

  public UserService(String name) {
    this.name = name;
  }

  public UserService(String name, String uId) {
    this.name = name;
    this.uId = uId;
  }

  public void queryUserInfo(){
    System.out.println("查询用户信息:" + "username:" + name);
  }


  public String queryUserInfoByUId() {
    return userDao.queryUserName(uId);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }
}
