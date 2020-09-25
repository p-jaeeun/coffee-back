// import { UserComponent } from "../view/UserComponent.js";
import { UserService } from "../service/UserService.js";

export class UserCTR {
  constructor(service, view) {
    this.service = service;
    this.view = view;
    this.self = this;

    // this.view.makeCafeInfo();
    // this.view.makeDashboard();
    // this.view.makeAddCafe();
    // this.view.makeSettings();
    // this.view.makeLoginMain();

    this.view.signin(() => {
      this.signin();
    });
    this.view.signup(() => {
      this.signup();
    });
    this.view.addCafe(() => {
      alert("CTR_BUTTON_ALRIGHT!");
      this.addCafe();
    });
    this.view.search(() => {
      this.search();
    });
    this.view.settings(() => {
      alert("CTR-setting-checked!");
      this.settings();
    });
    this.view.addReview(() => {
      this.addReview();
    });

    //event delegation
    this.view.headerMenu((e) => {
      this.headerMenu(e);
    });
    this.view.userMenu((e) => {
      this.userMenu(e);
    });
    this.view.caffeineList((e) => {
      this.caffeineList(e);
    });
    this.view.cafeList((e) => {
      this.cafeList(e);
    });
  }

  signin = () => {
    console.log("CTR-this:" + this);
    var result;

    this.view.signin_form.addEventListener("submit", async (e) => {
      e.preventDefault();

      let userData = new FormData(this.view.signin_form);
      for (let value of userData.values()) {
        console.log("value:" + value);
      }

      let service = new UserService();
      result = await service.signin(userData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
      } else if (result[0] === "member") {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeLoginMain();
      } else if (result[0] === "admin") {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeAdminMain(result[1]);
      }
    });
  };

  signup = () => {
    var result;

    this.view.signup_form.addEventListener("submit", async (e) => {
      e.preventDefault();

      let userData = new FormData(this.view.signup_form);
      for (let value of userData.values()) {
        console.log("value:" + value);
      }

      let service = new UserService();
      result = await service.signup(userData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
      } else {
        console.log("컨트롤러-서비스 결과값:" + result); //화면은 만들필요 없음, 서비스에서 통신해서 가져옴
      }
    });
  };

  addCafe = () => {
    var result;

    this.view.add_cafe_form.addEventListener("submit", async (e) => {
      e.preventDefault();

      let userData = new FormData(this.view.add_cafe_form);
      for (let value of userData.values()) {
        console.log("value:" + value);
      }

      let service = new UserService();
      result = await service.addCafe(userData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
        this.view.makeAddCafe();
      } else {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeAddCafe();
        alert("추가 요청이 성공적으로 전달되었습니다.");
        //화면은 필요하지 않음
      }
    });
  };

  search = () => {
    var result;

    this.view.search_form.addEventListener("click", async (e) => {
      e.preventDefault();

      let userData = new FormData(this.view.search_form);
      for (let value of userData.values()) {
        console.log("vlaue:" + value);
      }

      let service = new UserService();
      result = await service.search(userData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
      } else {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeSearchResult(result);
      }
    });
  };

  settings = () => {
    var result;
    console.log("clicked settings");

    this.view.settings_form.addEventListener("click", async (e) => {
      e.preventDefault();
      console.log("setting-form-set-ready");
      let userData = new FormData(this.view.settings_form);
      for (let value of userData.values()) {
        console.log("vlaue: " + value);
      }

      let input_1 = userData.get("user_changed_pw1");
      let input_2 = userData.get("user_changed_pw2");

      if (
        input_1 !== input_2 ||
        input_1 === "" ||
        input_2 === "" ||
        isNaN(input_1) == true ||
        isNaN(input_2) == true
      ) {
        alert("입력값이 다릅니다.");
        return;
      }

      let service = new UserService();
      result = await service.settings(userData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
        this.view.makeSettings(); //to make the page user id and user image should be needed
      } else {
        console.log("컨트롤러-서비스 결과값:" + result);
        alert("성공적으로 변경되었습니다.");
        this.view.makeSettings();
      }
    });
  };

  addReview = () => {
    var result;

    this.view.review_form.addEventListener("click", async (e) => {
      e.preventDefault();

      let userData = new FormData(this.view.review_form);
      for (let value of userData.values()) {
        console.log("value:" + value);
      }

      let service = new UserService();
      result = await service.settings(userData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
        this.view.makeCafeInfo();
      } else {
        console.log("컨트롤러-서비스 결과값: " + result);
        alert("성공적으로 등록되었습니다.");
        this.view.makeCafeInfo(); //새로 더한 리뷰 추가해서 페이지 다시 만들기
      }
    });
  };

  //event delegation
  headerMenu = async (e) => {
    e.preventDefault();
    console.log("headermenu-ctr");

    if (
      e.target.tagname === "UL" ||
      e.target.tagName === "LI" ||
      e.target.tagName === "A"
    ) {
      // console.log('taget' + e.target.tagName)
      if (e.target.innerHTML.includes("Home")) {
        console.log("Home");
        this.service.callMain();
      } else if (e.target.innerHTML.includes("User")) {
        console.log("user");

        let userData = localStorage.getItem("user_id");
        let result;
        try {
          result = await this.service.callDashboard(userData);
        } catch (e) {
          console.log("error: " + e);
        }

        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeDashboard(result);
        }
      } else if (e.target.innerHTML.includes("Search")) {
        console.log("search");
      }
    } else {
      console.log("you clicked invalid area");
    }
  };

  userMenu = async (e) => {
    e.preventDefault();
    console.log("usermenu-ctr");

    if (e.target.tagName === "A" || e.target.tagName === "I") {
      if (e.target.innerHTML.includes("Dashboard")) {
        console.log("clicked Dashboard");
        let userData = localStorage.getItem("user_id");
        console.log("local data:" + userData);
        let result;
        try {
          result = await this.service.callDashboard(userData);
        } catch (e) {
          console.log("error: " + e);
        }

        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeDashboard(result);
        }
      } else if (e.target.innerHTML.includes("My Hidden Cafe")) {
        console.log("clicked My hidden cafe(visited cafe)");
        let userData = localStorage.getItem("user_id");
        console.log("local data:" + userData);

        let result;

        try {
          result = await this.service.callVisitedCafe(userData);
        } catch (e) {
          console.log("error: " + e);
        }

        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeVisitedCafe(result);
        }
      } else if (e.target.innerHTML.includes("My Subscription")) {
        console.log("clicked My subscription");
        let userData = localStorage.getItem("user_id");
        console.log("local data:" + userData);
        let result;

        try {
          result = await this.service.callSubscription(userData);
        } catch (e) {
          console.log("error:" + e);
        }

        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeSubscription(result);
        }
      } else if (e.target.innerHTML.includes("Add New Hidden Cafe")) {
        console.log("clicked Add new hidden cafe");
        let user_id = localStorage.getItem("user_id");
        let user_img = localStorage.getItem("user_img");
        console.log("local data:" + user_id);
        console.log("local data:" + user_img);

        this.view.makeAddCafe(user_id, user_img);
      } else if (e.target.innerHTML.includes("Bookmark")) {
        console.log("clicked Bookmark");
        //
      } else if (e.target.innerHTML.includes("Settings")) {
        console.log("clicked Settings");
        // let user_id = localStorage.getItem("user_id");
        // let user_img = localStorage.getItem("user_img");
        // console.log("local data:" + user_id);
        // console.log("local data:" + user_img);

        this.view.makeSettings();
      }
    } else {
      console.log("you clicked invalid area: " + e.target.tagName);
    }
  };

  caffeineList = async (e) => {
    console.log("CTR-caffeine-list");
    console.log("CTR-CAFFEINE: " + e.target.innerHTML);

    if (e.target.tagName === "H3" || e.target.tagName === "A") {
      let str = String(e.target.innerHTML);
      let pattern = /(?!value=")\d.*(?<!\")/;
      let found = str.match(pattern);
      var result;

      try {
        result = this.service.searchOtherUser(found);
      } catch (e) {
        console.log("error:" + e);
      }
      this.view.makeVisitedCafe(result);
    } else {
      console.log("you clicked invalid area:" + e.target.tagName);
    }
  };

  cafeList = async (e) => {
    console.log("CTR-cafe-list");
    console.log("CTR-CAFE: " + e.target.innerHTML);

    if (e.target.tagName === "H3" || e.target.tagName === "A") {
      let str = String(e.target.innerHTML);
      let pattern = /(?!value=")\d.*(?<!\")/;
      let found = str.match(pattern);
      var result;

      try {
        result = this.service.searchCafeInfo(found);
      } catch (e) {
        console.log("error:" + e);
      }
      this.view.makeCafeInfo(result);
    } else {
      console.log("you clicked invalid area:" + e.target.tagName);
    }
  };
}

//실패시 페이지를 되돌려야 하는데 항상 필요한 데이터는 아이디랑 이미지?, 카페 넘버?? 유저 아이디 정수값은 뭐지
