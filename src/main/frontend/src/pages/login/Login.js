import React, { useState } from 'react';
import styles from './Login.module.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsgId, setErrorMsgId] = useState('');
  const [errorMsgPw, setErrorMsgPw] = useState('');
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
      .catch((error) => {
        console.log(error);
        if (error.response.data.length === 2) {
          setErrorMsgId(error.response.data[1].message);
          setErrorMsgPw(error.response.data[0].message);
        } else if (error.response.data.length === 1) {
          if (error.response.data[0].field === 'loginId') {
            setErrorMsgId(error.response.data[0].message);
          } else if (error.response.data[0].field === 'password') {
            setErrorMsgPw(error.response.data[0].message);
          }
        }
      });
  };

  const idChange = (e) => {
    e.preventDefault();
    setLoginId(e.target.value);
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
        <div>{errorMsgId !== null ? errorMsgId : null}</div>
        <input
          name="password"
          type="password"
          value={password}
          onChange={pwChange}
          placeholder="비밀번호를 입력하세요"
        ></input>
        <div>{errorMsgPw !== null ? errorMsgPw : null}</div>
        <button type="submit" onClick={formSubmit}>
          Login In
        </button>
      </form>
    </div>
  );
}

export default Login;
