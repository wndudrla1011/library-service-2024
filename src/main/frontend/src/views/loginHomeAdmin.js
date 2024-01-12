import React from "react";

function loginHomeAdmin() {
  return (
    <div>
      <header>
        <div>
          <h3>Rootable Library</h3>
          <form>
            <button>로그아웃</button>
          </form>
        </div>
      </header>

      <main>
        <h1>접속 권한 : </h1>
        <p>도서관 관리자 또는 직원 전용 페이지</p>
        <p>
          <button>회원관리</button>
          <button>도서관리</button>
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

export default loginHomeAdmin;
