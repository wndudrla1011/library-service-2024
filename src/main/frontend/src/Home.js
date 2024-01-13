import React, { useEffect, useState } from "react";
import axios from "axios";

function Home() {
  const [model, setModel] = useState("");
  useEffect(() => {
    axios
      .get("/")
      .then((res) => setModel(res.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <div>
      {model}
      <header>
        <div>
          <h3>Rootable Library</h3>
        </div>
      </header>

      <main>
        <h1>
          편리한 도서 신청 서비스를 <br />
          지금
          <br /> 이용해 보세요!
        </h1>
        <p>
          서비스 이용을 위해
          <br />
          회원가입 또는 로그인이 필요합니다.
          <br />
        </p>
        <p>
          <button>회원가입</button>
          <button>로그인</button>
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

export default Home;
