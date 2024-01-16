import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import PostItem from '../components/PostItem';

function Home() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/posts')
      .then((res) => res.json())
      .then((res) => {
        console.log(1, res);
      });
  }, []);
  return (
    <div>
      <PostItem />
    </div>
  );
}

export default Home;
