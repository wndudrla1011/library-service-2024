import React, { useState } from 'react';
import styles from './Login.module.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [loginId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const formSubmit = async (e) => {
    e.preventDefault();

    const data = {
      loginId: loginId,
      password: password,
    };

    await axios
      .post('http://localhost:8080/login', data)
      .then((res) => {
        console.log(res);
        navigate('/');
      })
      .catch((error) => console.log(error));
  };

  const idChange = (e) => {
    e.preventDefault();
    setUserId(e.target.value);
  };

  const pwChange = (e) => {
    e.preventDefault();
    setPassword(e.target.value);
  };

  return (
    <div className={styles.login__root}>
      <div className={styles.login__text}>
        <h2>Rootable Library</h2>
      </div>
      <form onSubmit={formSubmit} className={styles.login__form}>
        <input
          name="loginId"
          type="text"
          value={loginId}
          onChange={idChange}
          placeholder="아이디를 입력하세요"
        ></input>
        <input
          name="password"
          type="password"
          value={password}
          onChange={pwChange}
          placeholder="비밀번호를 입력하세요"
        ></input>
        <button type="submit" onClick={formSubmit}>
          Login In
        </button>
      </form>
    </div>
  );
}

export default Login;
