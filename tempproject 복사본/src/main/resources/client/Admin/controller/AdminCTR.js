import { AdminView } from "../view/AdminView.js";
import { AdminService } from "../service/AdminService.js";

export class AdminCTR {
  constructor(service, view) {
    this.view = view;
    this.service = service;

    this.view.addCafe(() => {
      this.addCafe();
    });

    this.view.reviseCafe(() => {
      this.reviseCafe();
    });

    this.view.loadCafeList(() => {
      this.loadCafeList();
    });

    this.view.search(() => {
      this.search();
    });
    // event delegation
    this.view.headerMenu((e) => {
      this.headerMenu(e);
    });

    this.view.adminMenu((e) => {
      this.adminMenu(e);
    });
  }

  addCafe = () => {
    console.log("CTRaddCafe");
    var result;
    console.log(this.view.addcafe_form);
    this.view.addcafe_form.addEventListener("submit", async (e) => {
      e.preventDefault();

      let cafeData = new FormData(this.view.addcafe_form);
      for (let value of cafeData.values()) {
        console.log("value: " + value);
      }

      let service = new AdminService();
      result = await service.addCafe(cafeData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
        this.view.makeAddCafePage();
      } else {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeAddCafePage();
        alert("입력하신 카페가 성공적으로 등록되었습니다.");
      }
    });
  };

  reviseCafe = () => {
    var result;

    this.view.revisecafe_form.addEventListener("submit", async (e) => {
      e.preventDefault();

      let cafeData = new FormData(this.view.revisecafe_form);
      for (let value of cafeData.values()) {
        console.log("value: " + value);
      }

      let service = new AdminService();
      result = await service.reviseCafe(cafeData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
        this.view.makeReviseCafePage();
      } else {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeReviseCafePage();
        alert("입력하신 카페가 성공적으로 수정되었습니다.");
      }
    });
  };

  loadCafeList = () => {
    var result;

    this.service.loadCafeList();
  };

  search = () => {
    var result;

    this.view.search_form.addEventListener("click", async (e) => {
      e.preventDefault();

      let adminData = new FormData(this.view.search_form);
      for (let value of adminData.values()) {
        console.log("vlaue:" + value);
      }

      let service = new UserService();
      result = await service.search(adminData);

      if (result === undefined || result === "undefined") {
        console.log("CTR-return-error:" + result);
      } else {
        console.log("컨트롤러-서비스 결과값:" + result);
        this.view.makeSearchResultPage(result);
      }
    });
  };
  // event delegation
  headerMenu = async (e) => {
    e.preventDefault();
    console.log("headermenu-controller");

    if (
      e.target.tagname === "UL" ||
      e.target.tagName === "LI" ||
      e.target.tagName === "A"
    ) {
      // console.log('taget' + e.target.tagName)
      if (e.target.innerHTML.includes("Home")) {
        console.log("clicked Home");
        let result;

        try {
          result = await this.service.callMain();
        } catch (e) {
          console.log("error: " + e);
        }

        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeMainPage(result);
          console.log("received data:" + result);
        }
      } else if (e.target.innerHTML.includes("Search")) {
        console.log("clicked Search");
      } else if (e.target.innerHTML.includes("Admin")) {
        console.log("clicked Admin");

        let adminData = localStorage.getItem("admin_id");
        console.log("local data:" + adminData);
        let result;
        try {
          result = await this.service.callAdminPage(adminData);
        } catch (e) {
          console.log("error: " + e);
        }

        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeCafeListPage(result);
        }
      }
    } else {
      console.log("you clicked invalid area");
    }
  };

  adminMenu = async (e) => {
    e.preventDefault();
    console.log("admin menu - CTR");

    if (e.target.tagName === "A" || e.target.tagName === "I") {
      if (e.target.innerHTML.includes("Hidden Cafe List")) {
        console.log("clicked Hidden Cafe List");
        let result;

        try {
          result = await this.service.callCafeList();
        } catch (e) {
          console.log("error: " + e);
        }
        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeCafeListPage(result);
          console.log("cafe list:" + result);
        }
      } else if (e.target.innerHTML.includes("Member Management")) {
        console.log("clicked Member Management");
        let result;

        try {
          result = await this.service.callMemberList();
        } catch (e) {
          console.log("error: " + e);
        }
        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeMemberPage(result);
          console.log("user list:" + result);
        }
      } else if (e.target.innerHTML.includes("Add New Hidden Cafe")) {
        console.log("clicked Add Cafe");
        this.view.makeAddCafePage();
      } else if (e.target.innerHTML.includes("Revise Hidden Cafe")) {
        console.log("clicked Revise Cafe");
        let result;

        try {
          result = await this.service.callReviseCafe();
        } catch (e) {
          console.log("error: " + e);
        }
        if (result === undefined || result === "undefined") {
          console.log("CTR-result is undefined" + result);
          return;
        } else {
          this.view.makeReviseCafePage(result);
          console.log("user list:" + result);
        }
      }
    } else {
      console.log("you clicked ivalid area");
    }
  };
}
