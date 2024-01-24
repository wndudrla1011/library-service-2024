import React, { useState } from 'react';
import styles from './JoinForm.module.css';

function JoinForm() {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');

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
    <div className={styles.join__root}>
      <div className={styles.join__textBox}>
        <h1 className={styles.join__text}>Sign Up</h1>
      </div>
      <form onSubmit={formSubmit} className={styles.join__form}>
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
        />
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default JoinForm;
