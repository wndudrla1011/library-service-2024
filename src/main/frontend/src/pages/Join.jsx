import React from 'react';
import Header from '../components/Header';
import styles from './container.module.css';
import JoinForm from '../components/join/JoinForm';

function Join() {
  return (
    <>
      <Header />
      <div className={styles.container}>
        <JoinForm />
      </div>
    </>
  );
}

export default Join;
