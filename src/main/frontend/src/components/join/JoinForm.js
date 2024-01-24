import React, { useState } from 'react';
import styles from './JoinForm.module.css';

function JoinForm() {
  const formSubmit = async (e) => {};

  return (
    <div className={styles.join__root}>
      <div className={styles.join__textBox}>
        <h1 className={styles.join__text}>Sign Up</h1>
      </div>
      <form onSubmit={formSubmit} className={styles.join__form}>
        <div>
          <label htmlFor="loginId">Login ID</label>
          <input
            id="loginId"
            name="loginId"
            type="text"
            placeholder="아이디를 입력하세요"
            required
          />
        </div>

        <div>
          <label htmlFor="password">Password</label>
          <input
            id="password"
            name="password"
            type="password"
            placeholder="비밀번호를 입력하세요"
            required
          />
        </div>

        <div>
          <label htmlFor="name">Name</label>
          <input
            id="name"
            name="name"
            type="text"
            placeholder="이름을 입력하세요"
            required
          />
        </div>

        <div>
          <label htmlFor="email">Email</label>
          <input
            id="email"
            name="email"
            type="email"
            placeholder="이메일을 입력하세요"
            required
          />
        </div>

        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default JoinForm;
