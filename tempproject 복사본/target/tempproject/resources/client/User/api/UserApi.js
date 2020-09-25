// import { UserDTO } from "../model/UserDTO.js";
import { Ajax } from "../../Common/lib/Ajax.js";

export class UserApi {
  constructor() {
    this.ajax = new Ajax();
  }

  //화면
  async callHome() {
    console.log("ajax-callhome");
    let result;

    try {
      result = await this.ajax.sendAjaxGet("http://192.168.1.131:8080/hiddenc");
    } catch (e) {
      console.log("error:" + e);
    }
    return result;
  } //로그인 실패시, 로그 아웃시

  async callMain() {
    console.log("ajax-callmain");

    let result;
    try {
      result = await this.ajax.sendAjaxGet(
        "http://192.168.1.131:8080/hiddenc/login/main"
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async callAdminPage() {
    console.log("ajax-adminpage");
    let result;
    try {
      result = await this.ajax.sendAjaxGet(
        `http://192.168.1.131:8080/hiddenc/admin/cafe`
      );
    } catch (e) {
      console.log("error:" + e);
    }
    return result;
  }

  //정보
  async signin(userData) {
    console.log("ajax-signin");
    let result;

    try {
      result = await this.ajax.sendAjaxPostFile(
        "http://192.168.1.131:8080/hiddenc/login",
        userData
      );
    } catch (e) {
      console.log("error:" + e);
    }

    console.log("value type" + typeof result);
    return result;
  } //url 131로 바꿔줘야함

  async signup(userData) {
    console.log("ajax-signup");
    let result;
    // let str = JSON.stringify(userData);

    try {
      result = await this.ajax.sendAjaxPostFile(
        "http://192.168.1.131:8080/hiddenc/signup",
        userData
      );
    } catch (e) {
      console.log("error:" + e);
    }
    return result;
  }

  async addCafe(userData) {
    console.log("ajax-addCafe");

    let result;

    try {
      result = await this.ajax.sendAjaxPostFile(
        "http://192.168.1.131:8080/hiddenc/user/addcafe",
        userData
      ); // from here have to check whether the address is correct or not
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async search(userData) {
    console.log("ajax-search");
    let result;

    let str = JSON.stringify(userData);
    try {
      result = await this.ajax.sendAjaxPostFile(
        "http://192.168.1.131:8080/hiddenc/login/search/result",
        str
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  } //search main이랑 search result 뭐가 다름?

  async searchCafeInfo(userData) {
    console.log("ajax-cafe-info-search");
    let result;

    let str = JSON.stringify(userData);
    try {
      result = await this.ajax.sendAjaxPost(
        "http://192.168.1.131:8080/hiddenc/login/search/result/cafeinfo",
        userData
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async settings(userData) {
    console.log("ajax-settings");
    let result;

    try {
      result = await this.ajax.sendAjaxPostFile(
        "http://192.168.1.131:8080/hiddenc/login/setting",
        userData
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async addReview(userData) {
    console.log("ajax-addReview");
    let result;

    try {
      result = await this.ajax.sendAjaxPostFile(
        "http://192.168.1.131:8080/hiddenc/login/addreview",
        userData
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async deleteReview(userData) {
    console.log("ajax-addReview");
    let result;
    let str = JSON.stringify(userData);
    try {
      result = await this.ajax.sendAjaxPost(
        "http://192.168.1.131:8080/hiddenc/login/deletereview",
        str
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async callDashboard(userData) {
    let result;
    let str = JSON.stringify(userData);
    try {
      result = await this.ajax.sendAjaxPost(
        "http://192.168.1.131:8080/hiddenc/login/mypage",
        str
      );
    } catch (e) {
      console.log("error:" + e);
    }

    return result;
  }

  async callVisitedCafe(userData) {
    let result;
    let str = JSON.stringify(userData);
    try {
      result = await this.ajax.sendAjaxPost(
        "http://192.168.1.131:8080/hiddenc/login/visited/cafeinfo",
        str
      );
    } catch (e) {
      console.log("error:" + e);
    }
    return result;
  }

  async callSubscription(userData) {
    let result;

    let str = JSON.stringify(userData);

    try {
      result = await this.ajax.sendAjaxPost(
        "http://192.168.1.131:8080/hiddenc/login/Likelist",
        str
      );
    } catch (e) {
      console.log("error:" + e);
    }
    return result;
  }
}

//callsubscription, visitedCafe, callDashboard

//Put은 Post로?
