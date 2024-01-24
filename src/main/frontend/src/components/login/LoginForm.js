import React, { useContext, useState } from 'react';
import styles from './LoginForm.module.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { LoginContext } from '../../contexts/LoginContextProvider';

function Login() {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const { setIsLogin } = useContext(LoginContext);

  const formSubmit = async (e) => {
    e.preventDefault();
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
      <div className={styles.login__textBox}>
        <h1 className={styles.login__text}>LOGIN</h1>
      </div>
      <form onSubmit={formSubmit} className={styles.login__form}>
        <label htmlFor="loginId">Login ID</label>
        <input
          id="loginId"
          name="loginId"
          type="text"
          value={loginId}
          onChange={idChange}
          placeholder="아이디를 입력하세요"
        />
        <label htmlFor="password">Password</label>
        <input
          id="password"
          name="password"
          type="password"
          value={password}
          onChange={pwChange}
          placeholder="비밀번호를 입력하세요"
          //TODO: 아이디 저장 기능 구현 후 추가
        />
        <button type="submit">Login In</button>
      </form>
    </div>
  );
}

export default Login;
