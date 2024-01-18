import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import PostItem from '../components/PostItem';

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
    <div
      style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(3, 1fr)',
        gridAutoRows: 'minmax(100px, auto)',
        gap: '2rem',
        gridAutoFlow: 'dense',
        justifyItems: 'center',
        margin: '22% 0px',
        padding: '0px 13%',
      }}
    >
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
