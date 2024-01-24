import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import PostItem from '../components/PostItem';
import Header from '../components/Header';
import styles from './container.module.css';

function Home() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    getData();
  }, []);

  async function getData() {
    await axios
      .get('http://localhost:8080/posts')
      .then((res) => setPosts(res.data))
      .catch((error) => console.log(error));
  }

  return (
    <>
      <Header />
      <div className={styles.homeContainer}>
        {posts.map((data) => (
          <PostItem
            key={data.post.id}
            id={data.post.id}
            title={data.post.title}
            content={data.post.content}
            author={data.author}
          />
        ))}
      </div>
    </>
  );
}

export default Home;
