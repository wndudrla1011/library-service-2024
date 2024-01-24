import React from 'react';
import Header from '../components/Header';
import LoginForm from '../components/login/LoginForm';
import styles from './container.module.css';

function Login() {
  return (
    <>
      <Header />
      <div className={styles.container}>
        <LoginForm />
      </div>
    </>
  );
}

export default Login;
