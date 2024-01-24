import React, { useContext, useState } from 'react';
import styles from './LoginForm.module.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { LoginContext } from '../../contexts/LoginContextProvider';

function Login() {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsgId, setErrorMsgId] = useState('');
  const [errorMsgPw, setErrorMsgPw] = useState('');
  const navigate = useNavigate();
  const { setIsLogin } = useContext(LoginContext);

  const formSubmit = async (e) => {
    e.preventDefault();
    setErrorMsgId('');
    setErrorMsgPw('');

    const data = {
      loginId: loginId,
      password: password,
    };

    await axios
      .post('http://localhost:8080/login', data)
      .then((res) => {
        console.log(res);
        setIsLogin(true);
        navigate('/');
      })
      .catch((error) => {
        console.log(error);
        if (error.response.data.field === 'global') {
          //회원 정보 불일치
          alert(error.response.data.message);
        } else {
          error.response.data.map((errors) => {
            if (errors.field === 'loginId') {
              setErrorMsgId(errors.message);
            } else {
              setErrorMsgPw(errors.message);
            }
          });
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
      <div className={styles.login__textBox}>
        <h1 className={styles.login__text}>LOGIN</h1>
      </div>
      <form onSubmit={formSubmit} className={styles.login__form}>
        <label htmlFor="loginId">Login ID</label>
        <input
          style={{
            borderColor: errorMsgId !== '' ? 'red' : 'white',
          }}
          id="loginId"
          name="loginId"
          type="text"
          value={loginId}
          onChange={idChange}
          placeholder="아이디를 입력하세요"
        />
        <div className={styles.error__ID}>
          {errorMsgId !== null ? errorMsgId : null}
        </div>
        <label htmlFor="password">Password</label>
        <input
          style={{
            borderColor: errorMsgPw !== '' ? 'red' : 'white',
          }}
          id="password"
          name="password"
          type="password"
          value={password}
          onChange={pwChange}
          placeholder="비밀번호를 입력하세요"
          //TODO: 아이디 저장 기능 구현 후 추가
        />
        <div className={styles.error__PW}>
          {errorMsgPw !== null ? errorMsgPw : null}
        </div>
        <button type="submit">Login In</button>
      </form>
    </div>
  );
}

export default Login;
