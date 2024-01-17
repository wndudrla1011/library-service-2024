import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import PostItem from '../components/PostItem';

function Home() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/posts', {
      method: 'GET',
    })
      .then((res) => res.json())
      .then((res) => {
        setPosts(res);
      });
  }, []);
  return (
    <div>
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
  );
}

export default Home;
