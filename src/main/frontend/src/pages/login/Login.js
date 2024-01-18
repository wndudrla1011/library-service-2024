import React, { useState } from 'react';
import styles from './Login.module.css';
import axios from 'axios';

function Login() {
  const [id, setId] = useState('');
  const [pw, setPw] = useState('');

  const formSubmit = async (e) => {
    e.preventDefault();
    await axios.post('http://localhost:8080/login', { id: id, pw: pw });
  };

  const idChange = (e) => {
    setId(e.target.value);
  };

  const pwChange = (e) => {
    setPw(e.target.value);
  };

  return (
    <div className={styles.login__root}>
      <div className={styles.login__text}>
        <h2>Rootable Library</h2>
      </div>
      <form onSubmit={formSubmit} className={styles.login__form}>
        <input
          name="id"
          value={id}
          onChange={idChange}
          placeholder="아이디를 입력하세요"
        ></input>
        <input
          name="pw"
          value={pw}
          onChange={pwChange}
          placeholder="비밀번호를 입력하세요"
        ></input>
        <button>Login In</button>
      </form>
    </div>
  );
}

export default Login;
