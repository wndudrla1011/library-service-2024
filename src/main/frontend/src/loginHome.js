import React from "react";

function loginHome() {
  return (
    <div>
      <header>
        <div>
          <h3>Rootable Library</h3>
          <div>
            <button>관리 페이지</button>
          </div>
        </div>
      </header>

      <main>
        <h1>반갑습니다! 회원님</h1>
        <p>메인 페이지에서 서비스를 이용할 수 있습니다.</p>
        <p>
          <button>메인으로</button>
          <form>
            <button>로그아웃</button>
          </form>
        </p>
      </main>

      <footer>
        <p>
          Developed by <a>Joo Yound, Kim</a>, <p>Ver.2024V2</p>
        </p>
      </footer>
    </div>
  );
}

export default loginHome;
